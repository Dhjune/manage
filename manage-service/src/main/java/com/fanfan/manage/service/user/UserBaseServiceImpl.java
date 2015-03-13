package com.fanfan.manage.service.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.user.service.UserBaseServiceItl;
import com.fanfan.manage.dao.user.UserBaseDaoImpl;
import com.fanfan.manage.modle.book.Book;
import com.fanfan.manage.modle.user.User;

@Service
@Transactional
public class UserBaseServiceImpl implements UserBaseServiceItl{

	@Autowired
	private UserBaseDaoImpl userBaseDaoImpl;
	
	public User get() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public User get(int id){
		return  userBaseDaoImpl.get(id);
	}

	public List<User> list() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<User> list(int start,int pageSize) {
		
		return userBaseDaoImpl.list(start,pageSize);	
		
	}
	
	public List<User> list(int platId,int start,int pageSize){
		
		return userBaseDaoImpl.list(platId,start, pageSize);
		
	}
	
	public List<User> list(int start,int pageSize,Map map){
		return userBaseDaoImpl.list(start,pageSize,map);
	}
		
	public int count(Map map){
		return userBaseDaoImpl.count(map);
	}
	
	public int count(){
			
		return userBaseDaoImpl.count();
			
	}
	
	public int count(int platId){
		return userBaseDaoImpl.count(platId);
	}

	public void put(User obj) {
		userBaseDaoImpl.put(obj);
		
	}

	public void delete(User obj) {
		// TODO Auto-generated method stub
		
	}

	public void update(User obj) {
		// TODO Auto-generated method stub
		
	}

}
