package com.fanfan.manage.dao.book;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.book.dao.AuthorDaoItl;
import com.fanfan.manage.modle.book.Author;
import com.fanfan.manage.modle.book.Book;

@Repository
public class AuthorDaoImpl implements AuthorDaoItl{

	@Autowired
	private SessionFactory sessionFactory;
	
	public Author get(int id) throws Exception{
		Author author = null ;
        Session session = sessionFactory.getCurrentSession();
		author =  (Author) session.get(Author.class, id);
		
        return author; 
				
	}

	public List<Author> list() throws Exception{
		List<Author> list =null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Author au where au.status=1");
		list = query.list();
		return list;
	}
	
	public List<Author> list(int start ,int pageSize,int status) throws Exception{
		List<Author> list =null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Author au where au.status="+status+" order by au.id desc");
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		list = query.list();
		return list;
	}
	
	public List<Author> list(int start ,int pageSize,Map map) throws Exception{
		List<Author> list =null;
		Session session = sessionFactory.getCurrentSession();
		String hql ="from Author author  where 1=1 ";
    	for (Object key : map.keySet()) {
    		
    		if(!key.toString().equals("pageIndex")){
    			
	    		if(key.toString().equals("search")){
					
					hql += " and author.name like :author_name";
					
				}else{
					
					hql +=" and author."+key.toString()+"="+map.get(key.toString());
					
				}
    		}	
    	} 
    	hql += " order by author.id desc";
    	Query query = session.createQuery(hql);
    	if(map.get("search")!=null){
    		query.setString("author_name", "%"+map.get("search")+"%");
    	}
    	query.setFirstResult(start);
		query.setMaxResults(pageSize);	
//		query.setCacheable(true);
    	list = query.list();
		return list;
	}
	
	public List<Author> search(int start,int pageSize,Map map) throws Exception{
    	List<Author> list = null;
    	Session session  =  sessionFactory.getCurrentSession();
    	String hql =  "from Author au where au.name like :name or au.realName like :realname  order by au.id desc";
    	Query query = session.createQuery(hql);
    	query.setString("name", "%"+map.get("value")+"%");
    	query.setString("realname", "%"+map.get("value")+"%");
    	query.setFirstResult(start);
		query.setMaxResults(pageSize);	
		list = query.list();
    	return list;
    }
	
	public int count(Map map) throws Exception{
		int count = 0;
		String  sql  ="select count(*) from Author author where 1=1";   
    	Session session  =  sessionFactory.getCurrentSession();
    	
    	for (Object key : map.keySet()) {
    		
    		if(!key.toString().equals("pageIndex")){
    			
    			if(key.toString().equals("search")){
    				
    				sql += " and author.name like :author_name";
    				
    			}else{
    				
    				sql +=" and author."+key.toString()+"="+map.get(key.toString());
    				
    			}
    			
    		}
    	}
    	
    	Query query = session.createQuery(sql);
    	if(map.get("search")!=null){
    		query.setString("author_name", "%"+map.get("search")+"%");
    	}
    	
    	List list =  query.list();
    	if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}	
		return count;
	}
	
    public int countSearch(Map map) throws Exception{
    	
    	int count = 0;
    	Session session  =  sessionFactory.getCurrentSession();
    	String  sql  ="select count(*) from app_book_author au where au.author_name like :name or au.real_name like :realname";   	
    //	String hql =  "from Author au where au.name like :name or au.realName like :realname  order by au.id desc";
    	Query query = session.createSQLQuery(sql);
    	query.setString("name", "%"+map.get("value")+"%");
    	query.setString("realname", "%"+map.get("value")+"%");
    	List list =  query.list();
    	if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}				
		return count;
   	
    }
	
	public int count() throws Exception{
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		String  hql  ="select count(*) from Author au where au.status=1";
		Query query = session.createQuery(hql);
		List list =  query.list();		
		if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}
		
		return count;
	}

	public int count(int status) throws Exception{
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		String  hql  ="select count(*) from Author au where au.status="+status;
		Query query = session.createQuery(hql);
		List list =  query.list();		
		if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}
		
		return count;
	}
	
	public void put(Author obj) throws Exception{
		Session session = sessionFactory.getCurrentSession();
		session.save(obj);
	}

	public void update(Author obj) throws Exception{
		
		Session session = sessionFactory.getCurrentSession();
		session.update(obj);	
		
	}

	public void delete(Author obj) throws Exception{
		Session session = sessionFactory.getCurrentSession();
		session.delete(obj);
						
	}
	
	public boolean confirm(Author obj) throws Exception{
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("from Author a where a.name = '"+obj.getName()+"' and a.realName = '"+obj.getRealName()+"'");
		List<Author> list = query.list();
		
		if ( list != null && list.size()>0){
			return true;
		}else{
			return false;
		}
		
	}

}
