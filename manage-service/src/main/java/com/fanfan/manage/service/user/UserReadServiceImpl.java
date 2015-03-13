package com.fanfan.manage.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.demo.AbstractService;
import com.fanfan.manage.dao.user.UserReadDaoImpl;
import com.fanfan.manage.modle.user.UserRead;

@Service
@Transactional
public class UserReadServiceImpl extends AbstractService{
	
	@Autowired
	private UserReadDaoImpl userReadDaoImpl;
	
	public UserRead get(String table,int id)  throws Exception{
		
		return userReadDaoImpl.get(table,id);
		
	}
	
	public List<UserRead> list(String table,String userId,int start,int pageSize)  throws Exception{
		
		return userReadDaoImpl.list(table, userId, start, pageSize);
				
	}
	
	public int count(String table,String userId)  throws Exception{
		return userReadDaoImpl.count(table,userId);
	}
	
	

}
