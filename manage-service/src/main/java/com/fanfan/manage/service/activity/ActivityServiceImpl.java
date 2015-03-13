package com.fanfan.manage.service.activity;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.activity.service.ActivityServiceItl;
import com.fanfan.manage.dao.activity.ActivityDaoImpl;
import com.fanfan.manage.modle.activity.Activity;
import com.fanfan.manage.modle.base.PlatForm;
import com.fanfan.manage.service.base.PlatFormServiceImpl;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityServiceItl{

	@Autowired
	private ActivityDaoImpl activityDaoImpl;
	
	@Autowired 
	private PlatFormServiceImpl platFormServiceImpl;

    public Activity get() {
		
		return null;
	}
	
	public Activity get(int id)  throws Exception{
		
		return activityDaoImpl.get(id);
		
	}
	
	public int last()  throws Exception{
		return activityDaoImpl.last();
	}

	public List<Activity> list()  throws Exception{
		
		return activityDaoImpl.list();
	}
	
    public List<Activity> list(int start,int pageSize)  throws Exception{
		
		return activityDaoImpl.list(start,pageSize);
		
	}
    
    public List<Activity> list(int start,int pageSize,int status)  throws Exception{
    	return activityDaoImpl.list(start,pageSize,status);
    }
	
	public int count()  throws Exception{
		
		return activityDaoImpl.count();		
	}
	public int count(int status)  throws Exception{
		
		return activityDaoImpl.count(status);		
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor=RuntimeException.class)
	public void put(Activity obj)  throws Exception{
		
		int last = last();		
		obj.setDispOrder(last+1);
		obj.setPlatForm(platFormServiceImpl.get(obj.getPlatForm().getId()));
	    activityDaoImpl.put(obj);	
	    
	}

	public void delete(Activity obj)  throws Exception{
		
		activityDaoImpl.delete(obj);	
		
	}

	public void update(Activity obj)  throws Exception{
		
		obj.setUpdateTime(new Date());
		activityDaoImpl.update(obj);
		
	}
	
	public void update(Activity obj,int platId)  throws Exception{
		
		obj.setUpdateTime(new Date());
		PlatForm plat =  platFormServiceImpl.get(platId);
		obj.setPlatForm(plat);
		activityDaoImpl.update(obj);
		
	}
	
	public boolean confirm(Activity obj)  throws Exception{
		
		return activityDaoImpl.confirem(obj);
		
	}
	
	
	

}
