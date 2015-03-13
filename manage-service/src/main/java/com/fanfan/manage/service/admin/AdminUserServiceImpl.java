package com.fanfan.manage.service.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.demo.AbstractService;
import com.fanfan.manage.dao.admin.AdminUserDaoImpl;
import com.fanfan.manage.modle.admin.AdminLoginInfo;
import com.fanfan.manage.modle.admin.AdminUser;

@Service
@Transactional
public class AdminUserServiceImpl extends AbstractService{

	@Autowired
	private AdminUserDaoImpl adminUserDaoImpl;
	
	private Vector vectors = new Vector();
	
	
	public AdminUser get(int id) throws Exception{
		
		return adminUserDaoImpl.get(id);
		
	}
	
	public void put(AdminUser adminUser) throws Exception{
		
		if(!confirm(adminUser)){
			adminUserDaoImpl.put(adminUser);
			adminUser.setUpdateTime(new Date());
		}	
		
	}
	
	public boolean confirm(AdminUser adminUser) throws Exception{
		return adminUserDaoImpl.confirm(adminUser);
	}
	
	
	public AdminLoginInfo login(Map map) throws Exception{
		return adminUserDaoImpl.login(map);
		
	}
	
	public Map logout(Map map,HttpSession session) throws Exception{
		Map rMap = new HashMap();
		AdminLoginInfo info =  (AdminLoginInfo) session.getAttribute("admin");
		boolean success =  adminUserDaoImpl.logout(map);
		if(success&&map.get("admin_id").toString().equals(info.getAdmin_id().toString())){
			session.removeAttribute("admin");
			rMap.put("message", "登出成功，如要进行操作，请重新登录");
		}else{
			rMap.put("message", "登出失败，请稍后重试");
		}
		rMap.put("success", success);
		return rMap;
	}

	public int count(Map map) throws Exception{
		
		return adminUserDaoImpl.count(map);
		
	}

	public List<AdminUser> list(int start, int pageSize,Map map) throws Exception{
		
		return adminUserDaoImpl.list(start,pageSize,map);
		
	}
	
	public void update(AdminUser aUser) throws Exception{
		aUser.setUpdateTime(new Date());
		long a=System.currentTimeMillis();
		adminUserDaoImpl.update(aUser);
		System.out.println("\r<br>执行耗时 : "+(System.currentTimeMillis()-a)/1000f+" 秒 ");
		long b=System.currentTimeMillis();
		
		System.out.println("\r<br>执行耗时 : "+(System.currentTimeMillis()-a)/1000f+" 秒 ");
	}

	
	
}
