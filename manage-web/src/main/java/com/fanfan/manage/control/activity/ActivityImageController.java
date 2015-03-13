package com.fanfan.manage.control.activity;

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

import com.fanfan.manage.inteceptor.AdminInfoToken;
import com.fanfan.manage.inteceptor.AnnatationToken;
import com.fanfan.manage.modle.activity.Activity;
import com.fanfan.manage.modle.activity.ActivityImage;
import com.fanfan.manage.modle.admin.AdminLoginInfo;
import com.fanfan.manage.modle.book.Book;
import com.fanfan.manage.modle.book.BookPart;
import com.fanfan.manage.service.activity.ActivityImageServiceImpl;
import com.fanfan.manage.service.activity.ActivityServiceImpl;
import com.fanfan.manage.service.common.PageNav;
import com.fanfan.manage.service.vector.AdminOperateVector;

@Controller
@RequestMapping(value="activity/image/")
public class ActivityImageController {

	@Autowired
	private ActivityImageServiceImpl activityImageServiceImpl;
	
	@Autowired
	private ActivityServiceImpl activityServiceImpl;
	
	@Autowired
	private AdminOperateVector adminOperateVector;
	
	@RequestMapping(value="create",method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	public String  create(HttpServletRequest res,Model model)  throws Exception{
		String acId =  res.getParameter("acId");
		Activity activity = activityServiceImpl.get(Integer.parseInt(acId));
		model.addAttribute("activity", activity);
		
		return "account/activity/img/create";
		
	}
	
	@RequestMapping(value="create",method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	public String  created(ActivityImage img,HttpServletRequest res)  throws Exception{
		String acId =  res.getParameter("acId");
		Activity activity = activityServiceImpl.get(Integer.parseInt(acId));
		img.setActivity(activity);
		activityImageServiceImpl.put(img);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "create");
		adminOperateVector.operate(img, adminMap);
		return "redirect:/activity/image/list?acId="+acId;
		
	}
	
	@RequestMapping(value={"list"})
	@AdminInfoToken(secondToken=true)
	public String list(HttpServletRequest res,Model model)  throws Exception{
		String s = "_\\d+";
		Pattern  pattern=Pattern.compile(s);				
		String complie =res.getRequestURI();		
		Matcher mt=pattern.matcher(complie); 
		int pageIndex ;
		if(mt.find()){		
			pageIndex = Integer.parseInt(mt.group().replace("_", ""));
		}else{
			pageIndex = 1;
		}				
		int pageSize = 48;
		int acId = Integer.parseInt(res.getParameter("acId"));
		String url = "/manage/book/part/list";		
		List<ActivityImage> list =null;	
		Activity activity = activityServiceImpl.get(acId);
		int start = pageSize*(pageIndex-1);				
		list = activityImageServiceImpl.list(acId,start, pageSize);			
		int total = activityImageServiceImpl.count(acId);		
		PageNav<ActivityImage> context = new PageNav<ActivityImage>(list,total,pageSize,pageIndex,url);			
		model.addAttribute("context", context);		
		model.addAttribute("activity", activity);			
		return "account/activity/img/list";												
	}
	
	@RequestMapping(value="update",method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	@AnnatationToken(SaveToken=true)
	public String update(Model model,HttpServletRequest res)  throws Exception{
		
		String id =  res.getParameter("id");
		model.addAttribute("pic", activityImageServiceImpl.get(Integer.parseInt(id)));
		
		return "account/activity/img/update";
		
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	@AnnatationToken(RemoveToken=true)
	public String update(HttpServletRequest res)  throws Exception{
		
		String id =  res.getParameter("id");
		String subject =  res.getParameter("subject");
		String status =  res.getParameter("status");
		String intro = res.getParameter("intro");
		String imageUrl =  res.getParameter("imageUrl");
		
		ActivityImage acImg = activityImageServiceImpl.get(Integer.parseInt(id));
		acImg.setStatus(Integer.parseInt(status));
		acImg.setSubject(subject);
		acImg.setImageUrl(imageUrl);
		acImg.setIntro(intro);
		activityImageServiceImpl.update(acImg);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "update");
		adminOperateVector.operate(acImg, adminMap);
		
		return "redirect:/activity/image/list?acId="+acImg.getActivity().getId();
		
	}
	
	@RequestMapping(value="upused")
	@AdminInfoToken(secondToken=true)
	public String upUsed(HttpServletRequest res)  throws Exception{
		
		String id =  res.getParameter("id");
		String status =  res.getParameter("status");
		ActivityImage acImg = activityImageServiceImpl.get(Integer.parseInt(id));
		acImg.setStatus(Integer.parseInt(status));
		activityImageServiceImpl.update(acImg);
		
		return "redirect:/activity/image/list?acId="+acImg.getActivity().getId();
		
	}
	
	@RequestMapping(value="delete")
	@AdminInfoToken(secondToken=true)
	public String delete(HttpServletRequest res) throws Exception{
		
		String id = res.getParameter("id");
		ActivityImage acImg = activityImageServiceImpl.get(Integer.parseInt(id));
		activityImageServiceImpl.delete(acImg);	
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "update");
		adminOperateVector.operate(acImg, adminMap);
		return "redirect:/activity/image/list?acId="+acImg.getActivity().getId();
		
	}
	
	
	
	
}
