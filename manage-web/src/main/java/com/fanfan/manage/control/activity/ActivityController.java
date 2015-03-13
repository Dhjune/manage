package com.fanfan.manage.control.activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fanfan.manage.common.Constants;
import com.fanfan.manage.inteceptor.AdminInfoToken;
import com.fanfan.manage.modle.activity.Activity;
import com.fanfan.manage.modle.admin.AdminLoginInfo;
import com.fanfan.manage.modle.base.PlatForm;
import com.fanfan.manage.service.activity.ActivityServiceImpl;
import com.fanfan.manage.service.base.PlatFormServiceImpl;
import com.fanfan.manage.service.common.PageNav;
import com.fanfan.manage.service.vector.AdminOperateVector;

@Controller
@RequestMapping(value="activity/")
public class ActivityController {
	
	@Autowired
	private ActivityServiceImpl activityServiceImpl;
	
	@Autowired
	private PlatFormServiceImpl  platFormServiceImpl;
	
	@Autowired
	private AdminOperateVector adminOperateVector;
	
	@RequestMapping(value="home")
	public String home(){
		return "account/activity/home";
	}
	
	@RequestMapping(value="create",method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	public String create(Model model)  throws Exception{
		model.addAttribute("plats", platFormServiceImpl.list());
		return "account/activity/create";
		
	}	
	
	@RequestMapping(value="create",method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	public String created(Activity activity,HttpServletRequest res)  throws Exception{
		
		String platId =  res.getParameter("platId");	
		String startStr =  res.getParameter("startStr");
		String endStr = res.getParameter("endStr");		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startTime = null;
		Date endTime = null;
		try {
			startTime = sdf.parse(startStr);
			endTime = sdf.parse(endStr);
		} catch (ParseException e) {
					
			e.printStackTrace();
		}	
		activity.setStartTime(startTime);
		activity.setEndTime(endTime);
		activity.setSchedule((endTime.getTime()-startTime.getTime())/(24*60*60*1000));		
		PlatForm platForm = platFormServiceImpl.get(Integer.parseInt(platId));
		
		activity.setPlatForm(platForm);
		activityServiceImpl.put(activity);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "create");
		adminOperateVector.operate(activity, adminMap);
		
		return "redirect:/activity/list";
						
	}	
		
	
	@RequestMapping(value={"list","list/p_{pageIndex}"},method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	public String list(HttpServletRequest res,Model model) throws Exception{
		
		String s = "_\\d+";
		Pattern  pattern= Pattern.compile(s); 
		String complie = res.getRequestURI();
		Matcher mt= pattern.matcher(complie); 
		
		int pageIndex ;		
		if(mt.find()){		
			pageIndex = Integer.parseInt(mt.group().replace("_", ""));
		}else{
			pageIndex = 1;
		}			
		int status = -1;
		String statusStr =  res.getParameter("status");
		if(statusStr!=null&&!statusStr.equals("")){
			status = Integer.parseInt(statusStr);
			res.getSession().setAttribute("activity_status", statusStr);
		}else{
			statusStr = (String) res.getSession().getAttribute("activity_status");
			if(statusStr!=null&&!statusStr.equals("")){
				status = Integer.parseInt(statusStr);
			}
		}
		int pageSize = Constants.ACCOUNT_DEFAULT_PAGE_SIZE;
		String url = "/manage/activity/list%s?status="+status;				
		List<Activity> list =null;
		int start = pageSize*(pageIndex-1);	
		int total = 0;
		if(status>=0){
			list = activityServiceImpl.list(start, pageSize,status);			
			total = activityServiceImpl.count(status);
		}else{
			list = activityServiceImpl.list(start, pageSize);			
			total = activityServiceImpl.count();
		}		
		PageNav<Activity> context = new PageNav<Activity>(list,total,pageSize,pageIndex,url);	
		model.addAttribute("context", context);	
		model.addAttribute("status", statusStr);
		return "account/activity/list";		
	}
	
	@RequestMapping(value="update",method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	public String update(HttpServletRequest res ,Model model) throws Exception{
		
		String id  = res.getParameter("id");
		Activity acty =  activityServiceImpl.get(Integer.parseInt(id));
		model.addAttribute("acty", acty);
		model.addAttribute("plats", platFormServiceImpl.list());		
		return "account/activity/update";
		
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	public String updated(Activity acty,HttpServletRequest res) throws Exception{
		
		String platId =  res.getParameter("platId");		
		String startStr =  res.getParameter("startStr");
		String endStr = res.getParameter("endStr");		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startTime = null;
		Date endTime = null;
		try {
			startTime = sdf.parse(startStr);
			endTime = sdf.parse(endStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		
		acty.setStartTime(startTime);
		acty.setEndTime(endTime);
		acty.setSchedule((endTime.getTime()-startTime.getTime())/(24*60*60*1000));
		if(platId!=null&&!platId.equals("")){
			activityServiceImpl.update(acty,Integer.parseInt(platId));
			AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
			Map adminMap =new HashMap<String,String>();
			adminMap.put("ipAddr", res.getRemoteAddr());
			adminMap.put("adminId", info.getAdmin_id());
			adminMap.put("logInfo", "update");
			adminOperateVector.operate(acty, adminMap);
		}
		
		
		return "redirect:/activity/view?id="+acty.getId();
				
	}
	
	
	@RequestMapping(value="delete")
	@AdminInfoToken(secondToken=true)
	public String delte(HttpServletRequest res) throws Exception{
		String id =  res.getParameter("id");
		Activity activity = activityServiceImpl.get(Integer.parseInt(id));	
		activity.setStatus(2);
		activityServiceImpl.update(activity,activity.getPlatForm().getId());
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "update");
		adminOperateVector.operate(activity, adminMap);
		return "redirect:/activity/list";
		
	}
	
	@RequestMapping(value="view")
	@AdminInfoToken(secondToken=true)
	public String view(HttpServletRequest res,Model model)  throws Exception{
		
		String id  =  res.getParameter("id");
		Activity activity = activityServiceImpl.get(Integer.parseInt(id));	
		//System.out.println("the startTime is "+activity.getStartTime());
		model.addAttribute("activity", activity);
		
		return "account/activity/view";
		
	}
		
}
