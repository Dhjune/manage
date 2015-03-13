package com.fanfan.manage.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.demo.AbstractService;
import com.fanfan.manage.dao.user.UserInfoDaoImpl;
import com.fanfan.manage.modle.user.UserInfo;
@Service
@Transactional
public class UserInfoServiceImpl extends AbstractService{
	
	@Autowired
	private UserInfoDaoImpl userInfoDaoImpl;
	
	
	public UserInfo  get(int userId)  throws Exception{
		return userInfoDaoImpl.get(userId);
	}
	
	public UserInfo get(int userId,int platId)  throws Exception{
		
		return  userInfoDaoImpl.get(userId,platId);
				
	}
	
	
	
}
