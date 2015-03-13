package com.fanfan.manage.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.demo.AbstractService;
import com.fanfan.manage.dao.user.UserScoreDaoImpl;
import com.fanfan.manage.modle.user.ScoreHistory;

@Service
@Transactional
public class UserScoreServiceImpl extends AbstractService{
	
	@Autowired
	private UserScoreDaoImpl userScoreDaoImpl ;
	
	public ScoreHistory get(int id)  throws Exception{
		return userScoreDaoImpl.get(id);
	}
	
	public List<ScoreHistory> list(int userId,int start, int pageSize){
		
		return userScoreDaoImpl.list(userId, start, pageSize);
		
	}
	
	public int count(int userId)  throws Exception{
		return userScoreDaoImpl.count(userId);
	}

}
