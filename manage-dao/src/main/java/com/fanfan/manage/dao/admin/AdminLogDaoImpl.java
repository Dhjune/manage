package com.fanfan.manage.dao.admin;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.demo.AbstractDao;
import com.fanfan.manage.modle.admin.AdminLog;

@Repository
public class AdminLogDaoImpl extends AbstractDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public AdminLog get(int id) throws Exception{
		
		AdminLog adminLog = null;
		
		return adminLog;
	}
	
	public void put(AdminLog adminLog) throws Exception{
		Session session = sessionFactory.getCurrentSession();
		session.save(adminLog);
		
	}
	
	
}
