package com.fanfan.manage.service.admin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.demo.AbstractService;
import com.fanfan.manage.dao.admin.AdminUserRoleDaoImpl;
import com.fanfan.manage.modle.admin.AdminUserRole;

@Service
@Transactional
public class AdminUserRoleServiceImpl extends AbstractService{

	@Autowired
	private AdminUserRoleDaoImpl adminUserRoleDaoImpl;
	
	public void put(AdminUserRole userRole) throws Exception{
		if(confirm(userRole)){
			
		}
		adminUserRoleDaoImpl.put(userRole);
		
	}
	
	public AdminUserRole saveOrUpdate(AdminUserRole userRole) throws Exception{
		AdminUserRole uRole = null;
		if(confirm(userRole)){
			
			uRole = adminUserRoleDaoImpl.update(userRole);
			
		}else{
			userRole.setUpdateTime(new Date());
			adminUserRoleDaoImpl.put(userRole);
			uRole = userRole;
			
		}
		return uRole;
	}
	
	public AdminUserRole get() throws Exception{
		return null;	
	}
	
	public boolean confirm(AdminUserRole userRole) throws Exception{
		
		return adminUserRoleDaoImpl.confirm(userRole);
		
	}
	
	public AdminUserRole update(AdminUserRole userRole)throws Exception{
		
		return adminUserRoleDaoImpl.update(userRole);
		
	}
	
}
