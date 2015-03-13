package com.fanfan.manage.dao.user;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.user.dao.UserBaseDaoItl;
import com.fanfan.manage.modle.book.Book;
import com.fanfan.manage.modle.user.User;

@Repository
public class UserBaseDaoImpl implements UserBaseDaoItl{

	@Autowired
	private SessionFactory  sessionFactory;

	public User get(int id) {
		User user =null ;
		Session session  =  sessionFactory.getCurrentSession();
		user =  (User) session.get(User.class, id);		
		// TODO Auto-generated method stub
		return user;
	}

	public List<User> list() {
		List<User> list =null;
		Session session = sessionFactory.getCurrentSession();		
		Query query = session.createQuery("from User");
		list = query.list();				
		return list;
	}
	
	public List<User> list(int start ,int pageSize) {
    	
		List<User> list =null;
		Session session = sessionFactory.getCurrentSession();		
		Query query = session.createQuery("from User");
		query.setFirstResult(start);
		query.setMaxResults(pageSize);		
		list = query.list();		
		
		return list;
		
	}
	
	public List<User> list(int platId,int start,int pageSize){
		List<User> list =null;
		Session session = sessionFactory.getCurrentSession();
		String hql =  "from User u where 1=1 ";
		//Query query = session.createQuery("from User u where u.regPlatFormId="+platId);
		Query query = session.createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);		
		list = query.list();				
		return list;
	}
	
	
	public int count(){
		int count = 0;
		Session session = sessionFactory.getCurrentSession();		
		String  hql  ="select count(*) from User ";
		Query query = session.createQuery(hql);
		List list =  query.list();		
		if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}			
		
		return count;
	}
	
	public List<User> list(int start,int pageSize,Map map){
    	List<User> list = null;
    	Session session  = sessionFactory.getCurrentSession();
    	String hql ="from User user where 1=1 ";
    	for (Object key : map.keySet()) {
    		if(!key.toString().equals("pageIndex")){
    			
	    		if(key.toString().equals("search")){
	    			
					hql += " and user.nickName like :user_nickName";
					
				}else{	
					
					hql +=" and user."+key.toString()+"="+map.get(key.toString());
					
				}
    		}	
    	} 
    	
    	hql += " order by user.id desc";
    	Query query = session.createQuery(hql);
    	if(map.get("search")!=null){
    		query.setString("user_nickName", "%"+map.get("search")+"%");
    	}
    	query.setFirstResult(start);
		query.setMaxResults(pageSize);	
    	list = query.list();
    	return list;
    }
	
	public int count(Map map){
    	
    	int count = 0;
    	String  sql  ="select count(*) from User user where 1=1";   
    	Session session  =  sessionFactory.getCurrentSession();
    	
    	for (Object key : map.keySet()) {
    		
    		if(!key.toString().equals("pageIndex")){
    			
    			if(key.toString().equals("search")){
    				
    				sql += " and user.nickName like :user_nickName";
    				
    			}else{
    				
    				sql +=" and user."+key.toString()+"="+map.get(key.toString());
    				
    			}
    			
    		}
    	}
    	
    	Query query = session.createQuery(sql);
    	if(map.get("search")!=null){
    		query.setString("user_nickName", "%"+map.get("search")+"%");
    	}
    	
    	List list =  query.list();
    	if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}				
		return count;
   	
    }
	
	public int count(int platId){
		int count = 0;
		Session session = sessionFactory.getCurrentSession();		
		String hql =  "select count(*) from User u where 1=1 ";
		//Query query = session.createQuery("from User u where u.regPlatFormId="+platId);
		Query query = session.createQuery(hql);
		List list =  query.list();		
		if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}			
		
		return count;
	}
	

	public void put(User obj) {
		Session session  =  sessionFactory.getCurrentSession();
		session.save(obj);
		
	}

	public void update(User obj) {
		// TODO Auto-generated method stub
		
	}

	public void delete(User obj) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
