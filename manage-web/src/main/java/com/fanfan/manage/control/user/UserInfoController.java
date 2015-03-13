package com.fanfan.manage.control.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fanfan.manage.inteceptor.AdminInfoToken;
import com.fanfan.manage.modle.user.UserInfo;
import com.fanfan.manage.service.user.UserInfoServiceImpl;

@RequestMapping(value="user/info/")
@Controller
public class UserInfoController {
	
	@Autowired
	private UserInfoServiceImpl userInfoServiceImpl;
	
	@RequestMapping(value="view")
	@AdminInfoToken(secondToken=true)
	public String view(HttpServletRequest res,Model model)  throws Exception{		
		String userId = res.getParameter("userId");		
		String platId =  res.getParameter("platId");		
		UserInfo userInfo =  userInfoServiceImpl.get(Integer.parseInt(userId), Integer.parseInt(platId));	
		return "account/user/info/view";
		
	}
	
	
	
	
}
