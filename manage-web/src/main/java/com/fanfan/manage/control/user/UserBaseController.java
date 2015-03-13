package com.fanfan.manage.control.user;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.service.jdbc.connections.internal.UserSuppliedConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fanfan.manage.common.Constants;
import com.fanfan.manage.inteceptor.AdminInfoToken;
import com.fanfan.manage.modle.book.Book;
import com.fanfan.manage.modle.user.User;
import com.fanfan.manage.modle.user.UserEntity;
import com.fanfan.manage.modle.user.UserInfo;
import com.fanfan.manage.service.base.PlatFormServiceImpl;
import com.fanfan.manage.service.common.PageNav;
import com.fanfan.manage.service.common.PageNavJson;
import com.fanfan.manage.service.user.UserBaseServiceImpl;
import com.fanfan.manage.service.user.UserCommentServiceImpl;
import com.fanfan.manage.service.user.UserEntityServiceImpl;
import com.fanfan.manage.service.user.UserInfoServiceImpl;
import com.fanfan.manage.service.user.UserReadServiceImpl;

@Controller
@RequestMapping(value="user/")
public class UserBaseController {
	
	@Autowired
	private UserBaseServiceImpl userBaseServiceImpl;
	
	@Autowired
	private UserCommentServiceImpl userCommentServiceImpl ;
	
	@Autowired
	private UserInfoServiceImpl userInfoServiceImpl;
	
	@Autowired
	private UserEntityServiceImpl userEntityServiceImpl;
	
	@Autowired
	private PlatFormServiceImpl  platFormServiceImpl;
	
	@RequestMapping(value="home")
	public String home(){
		return "account/user/index";
	}
	
	@RequestMapping(value="create",method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	public String create(){
		return "account/user/create";
	}
	
	@RequestMapping(value="create",method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	public String created(User user){
		
		userBaseServiceImpl.put(user);
		
		return "redirect:/user/list";
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
		
		String platStr = res.getParameter("platId");
		int platId = 0;
		if(platStr !=null && !platStr.equals("")){
			platId =  Integer.parseInt(platStr);			
		}
		String url = "/manage/user/list%s";		
		List<User> list =null;		
		int start = pageSize*(pageIndex-1);				
		list = userBaseServiceImpl.list(platId,start, pageSize);			
		int total = userBaseServiceImpl.count(platId);		
		PageNav<User> context = new PageNav<User>(list,total,pageSize,pageIndex,url);
		model.addAttribute("plats",platFormServiceImpl.list());
		model.addAttribute("context", context);	
		return "account/user/list";
		
	}
	
	@RequestMapping(value="view")
	@AdminInfoToken(secondToken=true)
	public String view(HttpServletRequest res,Model model)  throws Exception{
		
		String id =  res.getParameter("id");		
		User user =  userBaseServiceImpl.get(Integer.parseInt(id));
		int count =  userCommentServiceImpl.count(Integer.parseInt(id));
		UserInfo userInfo =  userInfoServiceImpl.get(Integer.parseInt(id));		
		model.addAttribute("user", user);			
		model.addAttribute("count", count);
		model.addAttribute("userInfo", userInfo);
		return  "account/user/view";		
	}
	
	@RequestMapping(value="pageNavJson",produces = "application/json")
	@AdminInfoToken(secondToken=true)
	@ResponseBody
	public PageNavJson pageNavJson(@RequestBody Map map)  throws Exception{
		
		List<UserEntity> list = null;				
		int pageSize = Constants.ACCOUNT_DEFAULT_PAGE_SIZE;	
		int pageIndex ;		
		if(map.get("pageIndex")!=null){
			pageIndex = Integer.parseInt(map.get("pageIndex").toString());
			
		}else{
			pageIndex = 1;
		}		
		int start = pageSize*(pageIndex-1);	
		list =  userEntityServiceImpl.list(start,pageSize,map);	
		int total = userEntityServiceImpl.count(map);	
		PageNavJson<UserEntity> context = new PageNavJson<UserEntity>(list,total,pageSize,pageIndex);
		return context;		
	}
	
}
