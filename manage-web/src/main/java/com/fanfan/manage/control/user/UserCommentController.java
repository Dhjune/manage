package com.fanfan.manage.control.user;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fanfan.manage.common.Constants;
import com.fanfan.manage.inteceptor.AdminInfoToken;
import com.fanfan.manage.modle.user.User;
import com.fanfan.manage.modle.user.UserComment;
import com.fanfan.manage.modle.user.UserEntity;
import com.fanfan.manage.service.common.PageNav;
import com.fanfan.manage.service.common.PageNavJson;
import com.fanfan.manage.service.user.UserBaseServiceImpl;
import com.fanfan.manage.service.user.UserCommentServiceImpl;

@RequestMapping(value="user/comment/")
@Controller
public class UserCommentController {
	
	@Autowired
	private UserCommentServiceImpl userCommentServiceImpl;
	
	@Autowired
	private UserBaseServiceImpl userBaseServiceImpl ;
	
	@RequestMapping(value="view")
	@AdminInfoToken(secondToken=true)
	public String view(HttpServletRequest res , Model model) throws Exception{
		
		String id =  res.getParameter("id");
		model.addAttribute("comment", userCommentServiceImpl.get(Integer.parseInt(id)));
		
		return "account/user/comment/view";
	}
	
	@RequestMapping(value={"list","list/p_{pageIndex}"})
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
		int pageSize = Constants.ACCOUNT_DEFAULT_PAGE_SIZE;	
		
		String userStr = res.getParameter("userId");
		int userId = 0;
		String url = "/manage/user/comment/list%s";	
		if(userStr !=null && !userStr.equals("")){			
			userId =  Integer.parseInt(userStr);
			url +="?userId="+userId;
		}
		
			
		List<UserComment> list =null;		
		int start = pageSize*(pageIndex-1);				
		list = userCommentServiceImpl.list(userId,start, pageSize);			
		int total = userCommentServiceImpl.count(userId);		
		PageNav<UserComment> context = new PageNav<UserComment>(list,total,pageSize,pageIndex,url);			
		model.addAttribute("context", context);	
		model.addAttribute("user", userBaseServiceImpl.get(userId));
		return "account/user/comment/list";
		
	}
	
	
	@RequestMapping(value="pageNavJson",produces = "application/json")
	@AdminInfoToken(secondToken=true)
	@ResponseBody
	public PageNavJson pageNavJson(@RequestBody Map map) throws Exception{
		
		List<UserComment> list = null;				
		int pageSize = Constants.ACCOUNT_DEFAULT_PAGE_SIZE;	
		int pageIndex ;		
		if(map.get("pageIndex")!=null){
			
			pageIndex = Integer.parseInt(map.get("pageIndex").toString());
			
		}else{
			
			pageIndex = 1;
			
		}		
		int start = pageSize*(pageIndex-1);	
		list =  userCommentServiceImpl.list(start,pageSize,map);	
		int total = userCommentServiceImpl.count(map);	
		PageNavJson<UserComment> context = new PageNavJson<UserComment>(list,total,pageSize,pageIndex);
		return context;		
	}

}
