package com.fanfan.manage.control.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fanfan.manage.common.AdminOperateAspect;
import com.fanfan.manage.common.Constants;
import com.fanfan.manage.control.base.PlatFormController;
import com.fanfan.manage.dao.admin.AdminUserDaoImpl;
import com.fanfan.manage.inteceptor.AdminInfoToken;
import com.fanfan.manage.modle.admin.AdminLoginInfo;
import com.fanfan.manage.modle.admin.AdminRole;
import com.fanfan.manage.modle.admin.AdminUser;
import com.fanfan.manage.modle.admin.AdminUserRole;
import com.fanfan.manage.modle.admin.AdminUserRoleUK;
import com.fanfan.manage.service.admin.AdminLogServiceImpl;
import com.fanfan.manage.service.admin.AdminRoleServiceImpl;
import com.fanfan.manage.service.admin.AdminUserRoleServiceImpl;
import com.fanfan.manage.service.admin.AdminUserServiceImpl;
import com.fanfan.manage.service.common.PageNav;
import com.fanfan.manage.service.vector.AdminOperateVector;


@Controller()
@RequestMapping(value="admin/")
public class AdminController {
	
	@Autowired
	private AdminOperateVector adminOprateVctor;
	
	@Autowired
	private AdminUserServiceImpl adminUserServiceImpl;
	
	@Autowired
	private AdminRoleServiceImpl adminRoleServiceImpl;
	
	@Autowired
	private AdminLogServiceImpl adminLogServiceImpl;
	
	@Autowired
	private AdminUserRoleServiceImpl adminUserRoleServiceImpl;
	
//	@Autowired
//	private AdminOperateAspect adminOperateAspect;
	
	@Autowired
	private PlatFormController platFormController;
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	@ResponseBody
	public AdminLoginInfo login(@RequestBody Map map,HttpSession session)  throws Exception{
		AdminLoginInfo info = null;
		info  =  adminUserServiceImpl.login(map);
		System.out.println(platFormController.getClass());
		if(info.getSuccess()){
			
			session.setAttribute("admin", info);
			
		}		
		return info;
		
	}
	
	@RequestMapping(value="logout",method=RequestMethod.POST)
	@ResponseBody
	public Map logout(@RequestBody Map map,HttpSession session)  throws Exception{
		
		Map rMap = null;
		rMap = adminUserServiceImpl.logout(map,session);
		return rMap;
	}
	
	@RequestMapping(value="user/create",method=RequestMethod.GET)
	@AdminInfoToken(superToken=true)
	public String createUser(Model model)  throws Exception{
		
		return "account/admin/user/create";
		
	}
	
	
	@RequestMapping(value="user/create",method=RequestMethod.POST)
	@AdminInfoToken(superToken=true)
	public String createUser(Model model,HttpServletRequest res,AdminUser aUser)  throws Exception{
		
		adminUserServiceImpl.put(aUser);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "新建管理员用户");
		adminOprateVctor.operate(aUser, adminMap);
		
		return "redirect:/admin/user/list";
	}
	
	@RequestMapping(value="user/view")
	@AdminInfoToken(secondToken=true)
	public String userView(HttpServletRequest res,Model model)  throws Exception{
		String id =  res.getParameter("id");
		model.addAttribute("aUser", adminUserServiceImpl.get(Integer.parseInt(id)));
		return "account/admin/user/view";
		
	}

	@RequestMapping(value="user/role/create",method=RequestMethod.POST)
	@AdminInfoToken(superToken=true)
	public String userRole(HttpServletRequest res)  throws Exception{
		String adminId = res.getParameter("adminId");
		String roleId =  res.getParameter("roleId");
		String status =  res.getParameter("status");
		if(adminId!=null && !adminId.equals("")&& roleId!=null && !roleId.equals("")){
			AdminRole role = adminRoleServiceImpl.get(Integer.parseInt(roleId));
			AdminUser user = adminUserServiceImpl.get(Integer.parseInt(adminId));
			AdminUserRole userRole = new AdminUserRole();
			AdminUserRoleUK uk = new AdminUserRoleUK();
			uk.setRole(role);
			uk.setUser(user);
			userRole.setUk(uk);
			userRole.setStatus(Integer.parseInt(status));
			userRole = adminUserRoleServiceImpl.saveOrUpdate(userRole);			
			AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
			Map adminMap =new HashMap<String,String>();
			adminMap.put("ipAddr", res.getRemoteAddr());
			adminMap.put("adminId", info.getAdmin_id());
			adminMap.put("logInfo", "建立admin用户权限");
			adminOprateVctor.operate(userRole, adminMap);
		}
		return "redirect:/admin/user/list";
	}
	
	@RequestMapping(value="user/update",method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	public String updateUser(HttpServletRequest res,Model model)  throws Exception{
		
		String id = res.getParameter("id");
		String pwd = res.getParameter("pwd");
		String realName = res.getParameter("realName");
		String department = res.getParameter("department");
		String email = res.getParameter("email");
		String mobileNo = res.getParameter("mobileNo");
		String status = res.getParameter("status");
		
		AdminUser aUser = adminUserServiceImpl.get(Integer.parseInt(id));		
		aUser.setPwd(pwd);
		aUser.setRealName(realName);
		aUser.setDepartment(department);
		aUser.setEmail(email);
		aUser.setMobileNo(mobileNo);
		aUser.setStatus(Integer.parseInt(status));
		adminUserServiceImpl.update(aUser);
		model.addAttribute("aUser", aUser);
		return "account/admin/user/view";
		
	}
	
	@RequestMapping(value={"user/list","user/list/p_{pageIndex}"},method=RequestMethod.GET)
	@AdminInfoToken(superToken=true)
	public String userList(HttpServletRequest res,Model model)  throws Exception{
		String s = "_\\d+";
		HttpSession session =  res.getSession();
		AdminLoginInfo info = (AdminLoginInfo) session.getAttribute("admin");
		Pattern  pattern=Pattern.compile(s); 
		String complie =res.getRequestURI();
		Matcher mt=pattern.matcher(complie); 
		int pageIndex;
		
		if(mt.find()){				
			pageIndex = Integer.parseInt(mt.group().replace("_", ""));			
		}else{			
			pageIndex = 1;			
		}
		
		int pageSize = Constants.ACCOUNT_DEFAULT_PAGE_SIZE;				
		List<AdminUser> list =null;	
		String staStr  = res.getParameter("status");
		int status = 1;
		if(staStr !=null && !staStr.equals("")){
			status  = Integer.parseInt(staStr);
		}

		Map map = new HashMap();
		
	//	map.put("admin_id", info.getAdmin_id());
		map.put("admin_id", 1);
		map.put("status", status);
		String url = "/manage/admin/user/list%s";	
		int start = pageSize*(pageIndex-1);				
		list = adminUserServiceImpl.list(start, pageSize,map);			
		int total = adminUserServiceImpl.count(map);		
		PageNav<AdminUser> context = new PageNav<AdminUser>(list,total,pageSize,pageIndex,url);			
		model.addAttribute("context", context);	
		model.addAttribute("status", status);
		model.addAttribute("roles", adminRoleServiceImpl.list(0,10));
		return "account/admin/user/list";
		
	}
	
	
	@RequestMapping(value="role/create",method=RequestMethod.GET)
	@AdminInfoToken(superToken=true)
	public String createRole(Model model,HttpServletRequest res)  throws Exception{
		String pid = res.getParameter("pid");
		if(pid!=null && !pid.equals("")){
			model.addAttribute("role", adminRoleServiceImpl.get(Integer.parseInt(pid))); 
			model.addAttribute("pid", pid);
		}
				
		return "account/admin/role/create";
		
	}
	
	@RequestMapping(value="role/create",method=RequestMethod.POST)
	@AdminInfoToken(superToken=true)
	public String createRole(Model model,HttpServletRequest res,AdminRole role)  throws Exception{
		
		if(role.getPid()==0){
			HttpSession session =  res.getSession();
			AdminLoginInfo info = (AdminLoginInfo) session.getAttribute("admin");
			role.setPid(info.getAdmin_role_id().intValue());
		}
		
		adminRoleServiceImpl.put(role);
		
		return "redirect:/admin/role/list";
		
	}
	
	@RequestMapping(value="role/view")
	@AdminInfoToken(superToken=true)
	public String roleView(Model model,HttpServletRequest res)  throws Exception{
		
		String id =  res.getParameter("id");
		model.addAttribute("role", adminRoleServiceImpl.get(Integer.parseInt(id)));
		
		return "account/admin/role/view";
	}
	
	@RequestMapping(value="role/update",method=RequestMethod.POST)
	@AdminInfoToken(superToken=true)
	public String roleUpdate(AdminRole role)  throws Exception{
		adminRoleServiceImpl.update(role);
		return "redirect:/admin/role/list";
		
	}
	
	@RequestMapping(value={"role/list","role/list/p_{pageIndex}"},method=RequestMethod.GET)
	@AdminInfoToken(superToken=true)
	public String roleList(HttpServletRequest res,Model model)  throws Exception{
		String s = "_\\d+";
		Pattern  pattern=Pattern.compile(s); 
		String complie =res.getRequestURI();
		Matcher mt=pattern.matcher(complie); 
		int pageIndex;
		
		if(mt.find()){				
			pageIndex = Integer.parseInt(mt.group().replace("_", ""));			
		}else{			
			pageIndex = 1;			
		}
		
		int pageSize = Constants.ACCOUNT_DEFAULT_PAGE_SIZE;				
		List<AdminRole> list =null;	
		String staStr  = res.getParameter("status");
		int status = 1;
		if(staStr !=null && !staStr.equals("")){
			status  = Integer.parseInt(staStr);
		}
		String url = "/manage/admin/role/list%s";	
		int start = pageSize*(pageIndex-1);				
		list = adminRoleServiceImpl.list(start, pageSize);			
		int total = adminRoleServiceImpl.count();		
		PageNav<AdminRole> context = new PageNav<AdminRole>(list,total,pageSize,pageIndex,url);			
		model.addAttribute("context", context);	
		model.addAttribute("status", status);
		
		return "account/admin/role/list";
	}
	
	
	
}
