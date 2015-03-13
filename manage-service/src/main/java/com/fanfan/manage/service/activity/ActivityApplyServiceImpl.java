package com.fanfan.manage.service.activity;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.activity.service.ActivityApplyServiceItl;
import com.fanfan.manage.dao.activity.ActivityApplyDaoImpl;
import com.fanfan.manage.modle.activity.Activity;
import com.fanfan.manage.modle.activity.ActivityApply;
import com.fanfan.manage.modle.activity.ActivityImage;

@Service
@Transactional
public class ActivityApplyServiceImpl implements ActivityApplyServiceItl{

	@Autowired
	private ActivityApplyDaoImpl activityApplyDaoImpl;

	public ActivityApply get() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ActivityApply get(int id ,boolean show)  throws Exception{
		return activityApplyDaoImpl.get(id,show);
	}

	public List<ActivityApply> list() {
		// TODO Auto-generated method stub
		return null;
	}

	public void put(ActivityApply obj) {
		// TODO Auto-generated method stub
		
	}

	public void delete(ActivityApply obj) {
		// TODO Auto-generated method stub
		
	}

	public void update(ActivityApply obj)  throws Exception{
		
		obj.setUpdateTime(new Date());
		activityApplyDaoImpl.update(obj);
		
	}

	public List<ActivityApply> list(String acId, String status, int start,
			int pageSize)  throws Exception{
		
		return activityApplyDaoImpl.list(acId,status,start,pageSize);
	}

	public int count(String acId, String status) {
			
		return activityApplyDaoImpl.count(acId,status);
	}
	
	
	public void examine(int id,int status ,String result)  throws Exception{
		
		ActivityApply apply = null;
		apply = activityApplyDaoImpl.get(id, false);
		apply.setStatus(status);
		apply.setResult(result);
		activityApplyDaoImpl.update(apply);
		
	}
	

}
