package com.fanfan.manage.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserOnlineDaoImpl {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private List list(String table,int userId,int start,int pageSize){
		List list = null;
		
		String sql = "select * from "+table+" where user_id="+userId+" order by asc create_time";
		
		Session session = sessionFactory.getCurrentSession();
		//session.getNamedQuery("")
		Query query = session.createSQLQuery(sql);
		return list;
	}
	
}
