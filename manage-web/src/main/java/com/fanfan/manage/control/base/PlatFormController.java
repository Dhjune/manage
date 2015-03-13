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
import com.fanfan.manage.modle.base.PlatForm;
import com.fanfan.manage.service.base.PlatFormServiceImpl;
import com.fanfan.manage.service.common.PageNav;
import com.fanfan.manage.service.vector.AdminOperateVector;

@Controller
@RequestMapping(value="base/platform")
public class PlatFormController {

	@Autowired
	private PlatFormServiceImpl platFormServiceImpl;
	
	@Autowired
	private AdminOperateVector adminOperateVector;
	
	@RequestMapping(value="home",method=RequestMethod.GET)
	public String index(){
		
		return "account/base/platform/inex";
		
	}
	
	@RequestMapping(value="create",method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	public String create(){
		
		return "account/base/platform/create";
		
	}
	
	@RequestMapping(value="create",method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	public String created(PlatForm platForm,HttpServletRequest res)  throws Exception{	
		
		platFormServiceImpl.put(platForm);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "crearte");
		adminOperateVector.operate(platForm, adminMap);				
		return "redirect:/base/platform/list";
		
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
		String url = "/manage/base/platform/list%s";				
		List<PlatForm> list =null;
		int start = pageSize*(pageIndex-1);		
		list = platFormServiceImpl.list(start, pageSize);			
		int total = platFormServiceImpl.count();
		PageNav<PlatForm> context = new PageNav<PlatForm>(list,total,pageSize,pageIndex,url);	
		model.addAttribute("context", context);			
		return "account/base/platform/list";	
		
	}
	
	
	@RequestMapping(value="update",method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	public String update(HttpServletRequest res,Model model)  throws Exception{
		String id =  res.getParameter("id");
		
		PlatForm plat = platFormServiceImpl.get(Integer.parseInt(id));
		model.addAttribute("plat", plat);
		
		return "account/base/platform/update";
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	public String updated(HttpServletRequest res) throws Exception{
		
		String id =  res.getParameter("id");
		String name =  res.getParameter("name");
		String width =  res.getParameter("width");
		String height =  res.getParameter("height");
		String status =  res.getParameter("status");
		PlatForm plat = platFormServiceImpl.get(Integer.parseInt(id));		
		plat.setName(name);
		plat.setWidth(Integer.parseInt(width));
		plat.setHeight(Integer.parseInt(height));	
		plat.setStatus(Integer.parseInt(status));
		platFormServiceImpl.update(plat);	
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "update");
		adminOperateVector.operate(plat, adminMap);	
		return "redirect:/base/platform/list";		
	}
	
	@RequestMapping(value="addItem",method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	public String addItem(HttpServletRequest res,Model model)  throws Exception{
		
		String id =  res.getParameter("id");
		model.addAttribute("plat", platFormServiceImpl.get(Integer.parseInt(id)));		
		return "account/base/platform/addItem";
		
	}
	
	@RequestMapping(value="addItem",method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	public String addItemed(HttpServletRequest res,PlatForm platForm)  throws Exception{
		
		String id =  res.getParameter("platId");
		PlatForm plat =  platFormServiceImpl.get(Integer.parseInt(id));
		platForm.setParent(plat);
		platFormServiceImpl.put(platForm);	
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "crearte");
		adminOperateVector.operate(platForm, adminMap);	
		return "redirect:/base/platform/list";		
	}
	
	@RequestMapping(value="view")
	@AdminInfoToken(secondToken=true)
	public String view(HttpServletRequest res,Model model)  throws Exception{
		
		String id =  res.getParameter("id");
		PlatForm plat = platFormServiceImpl.get(Integer.parseInt(id));
		model.addAttribute("plat", plat);
		
		return "account/base/platform/view";
	}
	
	@RequestMapping(value="delete")
	@AdminInfoToken(secondToken=true)
	public String delete(HttpServletRequest res) throws Exception{
		
		String id =  res.getParameter("id");
		PlatForm platForm = platFormServiceImpl.get(Integer.parseInt(id));
		platFormServiceImpl.delete(Integer.parseInt(id));
		
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "crearte");
		adminOperateVector.operate(platForm, adminMap);	
		return "redirect:/base/platform/list";
				
	}
	
	
	
	
	
	
	
}
