package com.fanfan.manage.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.demo.AbstractService;
import com.fanfan.manage.dao.admin.AdminLogDaoImpl;
import com.fanfan.manage.modle.admin.AdminLog;


@Service
@Transactional
public class AdminLogServiceImpl extends AbstractService{
	
	@Autowired
	private AdminLogDaoImpl adminLogDaoImpl;
	
	public void put(AdminLog adminLog) throws Exception{
		
		adminLogDaoImpl.put(adminLog);
		
	}
	
}
