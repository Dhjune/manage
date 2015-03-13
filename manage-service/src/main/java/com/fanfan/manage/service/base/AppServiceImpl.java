package com.fanfan.manage.service.base;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.base.service.AppServiceItl;
import com.fanfan.manage.dao.base.AppDaoImpl;
import com.fanfan.manage.modle.base.Application;
import com.fanfan.manage.modle.base.TransTeam;

@Service
@Transactional
public class AppServiceImpl implements AppServiceItl{

	@Autowired
	private AppDaoImpl appDaoImpl;
	
	public Application get() {
		// TODO Auto-generated method stub
		return null;
	}

	public Application get(int id) throws Exception{
		
		return appDaoImpl.get(id);
		
	}

	public List<Application> list() throws Exception{
		
		return appDaoImpl.list();
	}
	
    public List<Application> list(int start,int pageSize) throws Exception{
		
		return appDaoImpl.list(start,pageSize);
		
	}
	
	public int count() throws Exception{
		
		return appDaoImpl.count();		
	}
	
	public void put(Application obj) throws Exception{
				
		appDaoImpl.put(obj);	
		
	}

	public void delete(Application obj) throws Exception{
		
		appDaoImpl.delete(obj);	
		
	}

	public void update(Application obj) throws Exception{
		appDaoImpl.update(obj);		
	}
	
	
	

}
