package com.fanfan.manage.dao.user;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.modle.book.StatMonthbook;
import com.fanfan.manage.modle.user.UserEntity;


@Repository
public class UserEntityDaoImpl {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<UserEntity> list(int start,int pageSize,Map map) throws Exception{
		
		List<UserEntity> list = null;
		Session session  = sessionFactory.getCurrentSession();
		String order ="";
		
		String sql = "select u.user_id,u.user_name,u.nick_name,u.sex,u.email,u.head_image,u.mobile_no,"
				+ "u.telephone,u.is_vip,u.status,info.score,info.used_score,info.all_score,info.ticket,info.all_ticket,info.used_ticket,info.platform_id from app_user_base u left join app_user_info info on "
				+ "info.user_id=u.user_id where 1=1";
    	
    	for (Object key : map.keySet()) {
    		
    		if(!key.toString().equals("pageIndex")){
    			
	    		if(key.toString().equals("search")){
					
	    			sql += " and u.nick_name like :nick_name";
					
				}else if(key.toString().equals("status") || key.toString().equals("is_vip")){
					
					sql +=" and u."+key.toString()+"="+map.get(key.toString());
					
				}else if(key.toString().equals("platform_id")){
					
					sql += " and info."+key.toString()+"="+map.get(key.toString()); 
					
				}else if(key.toString().equals("order")){
						
					order= " order by info."+map.get(key.toString());
					
				}
    		}	
    	} 
    	if(!order.equals("")){
    		
    		sql+=order;
    	}else{
    		
    		sql+=" order by u.user_id desc";
    	}
    	
    	Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(UserEntity.class));;
    	if(map.get("search")!=null){
    		query.setString("nick_name", "%"+map.get("search")+"%");
    	}
    	query.setFirstResult(start);
		query.setMaxResults(pageSize);	
		
    	list = query.list();
		return list;
		
	}
	
	
	public int count(Map map) throws Exception{
		int count = 0;
		String  sql  ="select count(*) from app_user_base u left join app_user_info info on info.user_id=u.user_id where 1=1";   
    	Session session  =  sessionFactory.getCurrentSession();
    	
    	for (Object key : map.keySet()) {
    		
    		if(!key.toString().equals("pageIndex")){
    			
    			if(key.toString().equals("search")){
    				
    				sql += " and u.nick_name like :nick_name";
    				
    			}else if(key.toString().equals("status") || key.toString().equals("is_vip") ){
					
					sql += " and u."+key.toString()+"="+map.get(key.toString());
					
				}else if(key.toString().equals("platform_id")){
					sql += " and info."+key.toString()+"="+map.get(key.toString()); 
				}
    			
    		}
    	}
    	
    	Query query = session.createSQLQuery(sql);
    	if(map.get("search")!=null){
    		query.setString("nick_name", "%"+map.get("search")+"%");
    	}
    	
    	List list =  query.list();
    	if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}	
		return count;
	}
	
	
}
