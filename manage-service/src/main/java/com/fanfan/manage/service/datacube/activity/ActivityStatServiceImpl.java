package com.fanfan.manage.service.datacube.activity;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.demo.AbstractService;
import com.fanfan.manage.dao.datacube.activity.ActivityStatDaoImpl;
import com.fanfan.manage.modle.activity.ActivityStat;

@Service
@Transactional
public class ActivityStatServiceImpl extends AbstractService{
	
	@Autowired
	private ActivityStatDaoImpl activityStatDaoImpl;
	
	public List<ActivityStat> list(int start,int pageSize,Map map)  throws Exception{
		
		return activityStatDaoImpl.list(start,pageSize,map);
		
	}
	
	
	public int count(Map map)  throws Exception{
		
		return activityStatDaoImpl.count(map);
							
	}
	
}
