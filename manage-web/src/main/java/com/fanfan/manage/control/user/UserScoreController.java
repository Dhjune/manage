package com.fanfan.manage.control.user;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fanfan.manage.common.Constants;
import com.fanfan.manage.inteceptor.AdminInfoToken;
import com.fanfan.manage.modle.user.ScoreHistory;
import com.fanfan.manage.modle.user.UserComment;
import com.fanfan.manage.service.common.PageNav;
import com.fanfan.manage.service.user.UserBaseServiceImpl;
import com.fanfan.manage.service.user.UserScoreServiceImpl;

@Controller
@RequestMapping(value="user/score/")
public class UserScoreController {
	
	@Autowired
	private UserScoreServiceImpl userScoreServiceImpl;
	
	@Autowired
	private UserBaseServiceImpl userBaseServiceImpl;
	
	@RequestMapping(value="view")
	public String view(){
		
		return "account/user/score";
		
	}
	
	@RequestMapping(value={"list","list/p_{pageIndex}"})
	@AdminInfoToken(secondToken=true)
	public String list(HttpServletRequest res ,Model model)  throws Exception{
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
		if(userStr !=null && !userStr.equals("")){			
			userId =  Integer.parseInt(userStr);
		}	
		String url = "/manage/user/score/list%s?userId="+userId;		
		List<ScoreHistory> list =null;		
		int start = pageSize*(pageIndex-1);				
		list = userScoreServiceImpl.list(userId,start, pageSize);			
		int total = userScoreServiceImpl.count(userId);		
		PageNav<ScoreHistory> context = new PageNav<ScoreHistory>(list,total,pageSize,pageIndex,url);			
		model.addAttribute("context", context);	
		model.addAttribute("user", userBaseServiceImpl.get(userId));
		return "account/user/score/list";
		
	}

}
