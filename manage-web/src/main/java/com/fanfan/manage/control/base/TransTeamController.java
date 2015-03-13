package com.fanfan.manage.control.base;

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
import com.fanfan.manage.modle.admin.AdminLoginInfo;
import com.fanfan.manage.modle.base.TransTeam;
import com.fanfan.manage.service.base.TransTeamServiceImpl;
import com.fanfan.manage.service.common.PageNav;
import com.fanfan.manage.service.vector.AdminOperateVector;

@Controller
@RequestMapping(value="base/transteam")
public class TransTeamController {

	@Autowired
	private TransTeamServiceImpl transTeamServiceImpl;
	
	@Autowired
	private AdminOperateVector	 adminOperateVector;;
	
	@RequestMapping(value="home",method=RequestMethod.GET)
	public String index(){
		
		return "account/base/transteam/index";
		
	}
	
	@RequestMapping(value="create",method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	public String create(){
		
		return "account/base/transteam/create";
		
	}
	
	@RequestMapping(value="create",method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	public String created(TransTeam transTeam,HttpServletRequest res)  throws Exception{	
		
		transTeamServiceImpl.put(transTeam);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "crearte");
		adminOperateVector.operate(transTeam, adminMap);					
		return "redirect:/base/transteam/list";
		
	}
	
	@RequestMapping(value={"list","list/p_{pageIndex}"},method=RequestMethod.GET)
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
		String url = "/manage/base/transteam/list%s";				
		List<TransTeam> list =null;
		int start = pageSize*(pageIndex-1);		
		list = transTeamServiceImpl.list(start, pageSize);			
		int total = transTeamServiceImpl.count();
		PageNav<TransTeam> context = new PageNav<TransTeam>(list,total,pageSize,pageIndex,url);	
		model.addAttribute("context", context);			
		return "account/base/transteam/list";	
		
	}
	
	@RequestMapping(value="/view")
	@AdminInfoToken(secondToken=true)
	public String view(HttpServletRequest res,Model model)  throws Exception{
		String id =  res.getParameter("id");
		model.addAttribute("team", transTeamServiceImpl.get(Integer.parseInt(id)));
		return "account/base/transteam/view";	
		
	}
	
	@RequestMapping(value="update",method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	public String update(HttpServletRequest res,Model model)  throws Exception{
		
		String id =  res.getParameter("id");
		TransTeam team = transTeamServiceImpl.get(Integer.parseInt(id));
		model.addAttribute("team", team);
		
		return "account/base/transteam/update";
	}
	@RequestMapping(value="update",method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	public String updated(HttpServletRequest res,TransTeam team)  throws Exception{
		String id =  res.getParameter("id");
	
		team.setId(Integer.parseInt(id));
		transTeamServiceImpl.update(team);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "update");
		adminOperateVector.operate(team, adminMap);
		return "redirect:/base/transteam/list";
		
	}
	
	@RequestMapping(value="/delete")
	@AdminInfoToken(secondToken=true)
	public String delete(HttpServletRequest res)  throws Exception{
		String id = res.getParameter("id");
		TransTeam team =  transTeamServiceImpl.get(Integer.parseInt(id));
		transTeamServiceImpl.delete(team);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "delete");
		adminOperateVector.operate(team, adminMap);
		return "redirect:/base/transteam/list";
	}
	
}
