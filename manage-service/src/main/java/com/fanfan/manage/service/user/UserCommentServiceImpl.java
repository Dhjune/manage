package com.fanfan.manage.service.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.demo.AbstractService;
import com.fanfan.manage.dao.user.UserCommentDaoImpl;
import com.fanfan.manage.modle.user.UserComment;

@Service
@Transactional
public class UserCommentServiceImpl extends AbstractService{

	@Autowired
	private UserCommentDaoImpl userCommentDaoImpl;
	
	public UserComment get(int id)  throws Exception{
		
		return userCommentDaoImpl.get(id);
		
	}
	
	public List<UserComment> list(int userId,int start,int pageSize)  throws Exception{
		
		return userCommentDaoImpl.list(userId, start, pageSize);
		
	}
	
	
	public List<UserComment> list(int start,int pageSize,Map map)  throws Exception{
		
		return userCommentDaoImpl.list(start, pageSize, map);
		
	}
	
	public int count(Map map)  throws Exception{
		return userCommentDaoImpl.count(map);
	}
	
	public int count(int userId){
		return userCommentDaoImpl.count(userId);
	}
	
	
}
