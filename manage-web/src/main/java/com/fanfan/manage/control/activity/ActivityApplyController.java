package com.fanfan.manage.control.activity;

import java.util.List;
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
import com.fanfan.manage.modle.activity.ActivityApply;
import com.fanfan.manage.modle.activity.ActivityImage;
import com.fanfan.manage.service.activity.ActivityApplyServiceImpl;
import com.fanfan.manage.service.activity.ActivityServiceImpl;
import com.fanfan.manage.service.base.PlatFormServiceImpl;
import com.fanfan.manage.service.common.PageNav;

@Controller
@RequestMapping(value="activity/apply")
public class ActivityApplyController {
	@Autowired
	private ActivityApplyServiceImpl activityApplyServiceImpl;
	
	@Autowired
	private ActivityServiceImpl activityServiceImpl;
	
	@Autowired
	private PlatFormServiceImpl platFormServiceImpl;
	
	@RequestMapping(value="home")
	public String home(){
		
		return "";
		
	}
	
	@RequestMapping(value="view")
	@AdminInfoToken(secondToken=true)
	public String view(HttpServletRequest res,Model model)  throws Exception{
		String id =  res.getParameter("id");
		ActivityApply apply =  null ;
		if (id!=null&&!id.equals("")){
			apply = activityApplyServiceImpl.get(Integer.parseInt(id),true);
		}
		
		model.addAttribute("apply", apply);
		return "account/activity/apply/view";
		
	}
	@RequestMapping(value={"list","list/p_{pageIndex}"})
	@AdminInfoToken(secondToken=true)
	public String list(HttpServletRequest res,Model model)  throws Exception{
		
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
		int pageSize = Constants.ACCOUNT_DEFAULT_PAGE_SIZE;		
		String acId = res.getParameter("acId");
		String status =  res.getParameter("status");
		if(status==null||status.equals("")){
			status = "0";
		}
		String url = "/manage/activity/apply/list%s?status="+status;		
		List<ActivityApply> list =null;	
		if(acId!=null&&!acId.equals("") ) {			
			Activity activity = activityServiceImpl.get(Integer.parseInt(acId));
			model.addAttribute("activity", activity);
		}		
		int start = pageSize*(pageIndex-1);				
		list = activityApplyServiceImpl.list(acId,status,start, pageSize);			
		int total = activityApplyServiceImpl.count(acId,status);		
		PageNav<ActivityApply> context = new PageNav<ActivityApply>(list,total,pageSize,pageIndex,url);		
		model.addAttribute("context", context);		
		model.addAttribute("plats", platFormServiceImpl.list());
		model.addAttribute("activities", activityServiceImpl.last());	
		model.addAttribute("status", status);
		return "account/activity/apply/list";
		
	}
	@RequestMapping(value="examine",method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	public String examine(HttpServletRequest res)  throws Exception{
		
		String  id =  res.getParameter("id");
		String acId =  res.getParameter("acId");
		String status  =  res.getParameter("status");
		String result =  res.getParameter("result");
		
		ActivityApply apply = activityApplyServiceImpl.get(Integer.parseInt(id), false);
		
		apply.setStatus(Integer.parseInt(status));
		apply.setResult(result);
		activityApplyServiceImpl.update(apply);
		return "redirect:/activity/apply/list?acId="+acId;
		
	}
	
}
