package com.fanfan.manage.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.demo.AbstractService;
import com.fanfan.manage.dao.admin.AdminRoleDaoImpl;
import com.fanfan.manage.modle.admin.AdminRole;


@Service
@Transactional
public class AdminRoleServiceImpl extends AbstractService{
	
	@Autowired
	private AdminRoleDaoImpl adminRoleImpl;

	public AdminRole get(int id) throws Exception{
		return adminRoleImpl.get(id);
	}
	
	
	public List<AdminRole> list(int start, int pageSize) throws Exception{
		return adminRoleImpl.list(start,pageSize);
	}

	public int count() {
		
		return  adminRoleImpl.count();
	
		
	}
	
	public void put(AdminRole role) throws Exception{
		if(!confirm(role)){
			adminRoleImpl.put(role);
		}
		
	}
	
	public boolean confirm(AdminRole role) throws Exception{
		
		return adminRoleImpl.confirm(role);
		
	}


	public void update(AdminRole role) throws Exception{
		if(!confirm(role)){
			adminRoleImpl.update(role);
		}
		
	}
	
	
	
	
	
}
