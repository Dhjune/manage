package com.fanfan.manage.control.user;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.fanfan.manage.modle.user.User;
import com.fanfan.manage.modle.user.UserRead;
import com.fanfan.manage.service.common.PageNav;
import com.fanfan.manage.service.user.UserBaseServiceImpl;
import com.fanfan.manage.service.user.UserReadServiceImpl;

@Controller
@RequestMapping(value="user/read")
public class UserReadController {

	@Autowired
	private UserReadServiceImpl  userReadServiceImpl ;
	
	@Autowired
	private UserBaseServiceImpl userBaseServiceImpl;
	
	@RequestMapping(value="")
	public String view(){
		
		return "account/user/read/view";
		
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
		String userId = "";
		userId = res.getParameter("userId");
		String tableMonth = res.getParameter("tableMonth");
		if(tableMonth ==null || tableMonth.equals("")){
			Date date = new Date();
			SimpleDateFormat format =  new SimpleDateFormat("yyyyMM");
			tableMonth = format.format(date);			
		}
		String  table = "APP_USER_READ_LOG" + tableMonth;		
		String url = "/manage/user/read/list%s?userId="+userId+"&tableMonth="+tableMonth;		
		List<UserRead> list =null;		
		int start = pageSize*(pageIndex-1);				
		list = userReadServiceImpl.list(table,userId,start, pageSize);			
		int total = userReadServiceImpl.count(table,userId);		
		PageNav<UserRead> context = new PageNav<UserRead>(list,total,pageSize,pageIndex,url);			
		model.addAttribute("context", context);
		model.addAttribute("user", userBaseServiceImpl.get(Integer.parseInt(userId)));
		return "account/user/read/list";
	}
	
}
