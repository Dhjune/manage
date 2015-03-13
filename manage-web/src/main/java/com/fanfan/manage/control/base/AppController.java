package com.fanfan.manage.control.base;

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
import com.fanfan.manage.modle.admin.AdminLoginInfo;
import com.fanfan.manage.modle.base.Application;
import com.fanfan.manage.modle.base.PlatForm;
import com.fanfan.manage.modle.base.TransTeam;
import com.fanfan.manage.service.base.AppServiceImpl;
import com.fanfan.manage.service.base.PlatFormServiceImpl;
import com.fanfan.manage.service.common.PageNav;
import com.fanfan.manage.service.vector.AdminOperateVector;

@Controller
@RequestMapping(value="base/app/")
public class AppController {
	
	@Autowired
	private AppServiceImpl appServiceImpl;
	
	@Autowired
	private PlatFormServiceImpl platFormServiceImpl;
	
	@Autowired
	private AdminOperateVector adminOperateVector;
	
	@RequestMapping(value="create",method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	private String create(Model model)  throws Exception{
		
		model.addAttribute("platforms", platFormServiceImpl.list());
		
		return "account/base/app/create";
	}
	
	@RequestMapping(value="create",method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	public String created(Application app,HttpServletRequest res)  throws Exception{	
	
		String pubTimeStr = res.getParameter("pubTimeStr");		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date pubTime = null;
		
		try {
			pubTime = sdf.parse(pubTimeStr);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		app.setPubTime(pubTime);
		appServiceImpl.put(app);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "update");
		adminOperateVector.operate(app, adminMap);
						
		return "redirect:/base/app/list";
		
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
		String url = "/manage/base/app/list%s";				
		List<Application> list =null;
		int start = pageSize*(pageIndex-1);		
		list = appServiceImpl.list(start, pageSize);			
		int total = appServiceImpl.count();
		PageNav<Application> context = new PageNav<Application>(list,total,pageSize,pageIndex,url);	
		model.addAttribute("context", context);
		
		return "account/base/app/list";	
		
	}
	
	@RequestMapping(value="/view")
	@AdminInfoToken(secondToken=true)
	public String view()  throws Exception{
		
		return "account/base/app/view";	
		
	}
	
	@RequestMapping(value="update",method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	public String update(HttpServletRequest res,Model model)  throws Exception{
		
		String id =  res.getParameter("id");
		Application app = appServiceImpl.get(Integer.parseInt(id));
		//PlatForm plat =  platFormServiceImpl.get(app.getPlatId());
		model.addAttribute("app", app);
		//model.addAttribute("plat", plat);
		model.addAttribute("platforms", platFormServiceImpl.list());
		
		return "account/base/app/update";
	}
	@RequestMapping(value="update",method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	public String updated(HttpServletRequest res,Application app)  throws Exception{
		String id =  res.getParameter("id");
		
		//String name = 
		String pubTimeStr = res.getParameter("pubTimeStr");		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date pubTime = null;
		
		try {
			pubTime = sdf.parse(pubTimeStr);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		app.setPubTime(pubTime);
		app.setId(Integer.parseInt(id));
		appServiceImpl.update(app);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "update");
		adminOperateVector.operate(app, adminMap);
		return "redirect:/base/app/list";
		
	}
	
	@RequestMapping(value="/delete")
	@AdminInfoToken(secondToken=true)
	public String delete(HttpServletRequest res)  throws Exception{
		String id = res.getParameter("id");
		Application team =  appServiceImpl.get(Integer.parseInt(id));
		appServiceImpl.delete(team);
		return "redirect:/base/app/list";
	}

}
