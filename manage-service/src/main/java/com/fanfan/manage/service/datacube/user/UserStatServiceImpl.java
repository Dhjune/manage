package com.fanfan.manage.service.datacube.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.demo.AbstractService;
import com.fanfan.manage.dao.datacube.user.UserStatDaoImpl;
import com.fanfan.manage.modle.user.UserStat;


@Service
@Transactional
public class UserStatServiceImpl extends AbstractService{

	@Autowired
	private UserStatDaoImpl userStatDaoImpl;
	
	public UserStat get(){
		
		return userStatDaoImpl.get();
		
	}
	
	public List<UserStat> list(int start,int pageSize,Map map) throws Exception{
		
		return userStatDaoImpl.list(start,pageSize,map);
		
	}
	
	public int count(Map map)  throws Exception{
		
		return userStatDaoImpl.count(map);
		
	}
	
}
