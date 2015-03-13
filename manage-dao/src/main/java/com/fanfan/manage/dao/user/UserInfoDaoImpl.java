package com.fanfan.manage.dao.user;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.demo.AbstractDao;
import com.fanfan.manage.modle.user.UserInfo;

@Repository
public class UserInfoDaoImpl extends AbstractDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public  UserInfo get(int userId,int platId) throws Exception{
		
		UserInfo userInfo = null;		
		Session session = sessionFactory.getCurrentSession();
		Query query =  session.createQuery("from UserInfo info where info.uk.user="+userId+" and info.uk.platForm="+platId);
		List<UserInfo> list = query.list();		
		if(list!=null && list.size()>0){
			userInfo  = list.get(0);
		}
		return userInfo;		
	}
	
	public UserInfo get(int userId) throws Exception{
		UserInfo userInfo = null;
		Session session  =  sessionFactory.getCurrentSession();
		Query	query  =  session.createQuery("from UserInfo info where info.uk.user="+userId);
		List<UserInfo> list = query.list();
		
		if(list!=null && list.size()>0){
			userInfo  = list.get(0);
		}
		return userInfo;	
		
	}
	
	
}
