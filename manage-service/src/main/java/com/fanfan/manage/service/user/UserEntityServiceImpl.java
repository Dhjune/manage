package com.fanfan.manage.service.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.demo.AbstractService;
import com.fanfan.manage.dao.user.UserEntityDaoImpl;
import com.fanfan.manage.modle.user.UserEntity;

@Service
@Transactional
public class UserEntityServiceImpl extends AbstractService{
	
	@Autowired
	private UserEntityDaoImpl userEntityDaoImpl;
	
	public List<UserEntity> list(int start,int pageSize,Map map)  throws Exception{
		
		return userEntityDaoImpl.list(start, pageSize, map);
		
	}
	
	public int count(Map map)  throws Exception{
		
		return userEntityDaoImpl.count(map);
		
	}
	
}
