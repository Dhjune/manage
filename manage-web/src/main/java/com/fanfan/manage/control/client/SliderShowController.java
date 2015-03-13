package com.fanfan.manage.control.client;

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
import com.fanfan.manage.inteceptor.AnnatationToken;
import com.fanfan.manage.modle.admin.AdminLoginInfo;
import com.fanfan.manage.modle.client.SliderShow;
import com.fanfan.manage.service.base.PlatFormServiceImpl;
import com.fanfan.manage.service.client.SliderShowServiceImpl;
import com.fanfan.manage.service.common.PageNav;
import com.fanfan.manage.service.vector.AdminOperateVector;


@Controller
@RequestMapping(value="client/slidershow/")
public class SliderShowController {

	@Autowired
	private SliderShowServiceImpl sliderShowServiceImpl;
	
	@Autowired
	private PlatFormServiceImpl platFormServiceImpl ;
	
	@Autowired
	private AdminOperateVector adminOperateVector;
	
	@RequestMapping(value="create",method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	@AnnatationToken(SaveToken=true)
	private String create(Model model) throws Exception{
		
		model.addAttribute("platforms", platFormServiceImpl.list());
		
		return "account/client/slidershow/create";		
	}
	
	@RequestMapping(value="create",method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	@AnnatationToken(RemoveToken=true)
	private String created(SliderShow slider,HttpServletRequest res)  throws Exception{		
		sliderShowServiceImpl.put(slider);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "update");
		adminOperateVector.operate(slider, adminMap);
		return "redirect:/client/slidershow/list";
		
	}
	
	@RequestMapping(value={"list","list/p_{pageIndex}"},method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	public String list(HttpServletRequest res,Model model)  throws Exception{
		
		String s = "_\\d+";
		Pattern  pattern=Pattern.compile(s); 
		String complie =res.getRequestURI();
		Matcher mt=pattern.matcher(complie); 
		int pageIndex;
		if(mt.find()){				
			pageIndex = Integer.parseInt(mt.group().replace("_", ""));			
		}else{			
			pageIndex = 1;			
		}
		
		int pageSize = Constants.ACCOUNT_DEFAULT_PAGE_SIZE;		
		String url = "/manage/client/slidershow/list%s";		
		List<SliderShow> list =null;	
		int start = pageSize*(pageIndex-1);	
		if(pageIndex>1){
			start -=1;
		}
		list =  sliderShowServiceImpl.list(start, pageSize);
		int count = 0;
		count = sliderShowServiceImpl.count();
		
//		if(list !=null){		
//			count = list.size();			
//		}
		
		PageNav<SliderShow> context = new PageNav<SliderShow>(list,count,pageSize,pageIndex,url);			
		model.addAttribute("context", context);	
		
		return "account/client/slidershow/list";
	}
	@RequestMapping(value="update",method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	public String update(HttpServletRequest res,Model model)  throws Exception{
		
		String id =  res.getParameter("id");
		SliderShow slider = sliderShowServiceImpl.get(Integer.parseInt(id));
		model.addAttribute("slider", slider);
		model.addAttribute("platforms", platFormServiceImpl.list());
		return "account/client/slidershow/update";
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	public String updated(SliderShow slider,HttpServletRequest res)  throws Exception{
		
		sliderShowServiceImpl.update(slider);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "update");
		adminOperateVector.operate(slider, adminMap);
		return "redirect:/client/slidershow/list";
		
	}
	
	@RequestMapping(value="view")
	@AdminInfoToken(secondToken=true)
	public String view(HttpServletRequest res,Model model) throws Exception{
		String id =  res.getParameter("id");
		return "account/client/slidershow/view";		
	}
	
	@RequestMapping(value="delete")
	@AdminInfoToken(secondToken=true)
	public String delete(HttpServletRequest res)  throws Exception{
		String id =  res.getParameter("id");
		SliderShow slider = sliderShowServiceImpl.get(Integer.parseInt(id));
		
		slider.setStatus(0);
		sliderShowServiceImpl.update(slider);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "update");
		adminOperateVector.operate(slider, adminMap);
		return "redirect:/client/slidershow/list";
		
	}
	
	@RequestMapping(value="disp")
	@AdminInfoToken(secondToken=true)
	public String disp(HttpServletRequest res) throws Exception{
		
		String down =  res.getParameter("down");
		String up  =  res.getParameter("up");	
		String pageIndex = res.getParameter("pageIndex");
		sliderShowServiceImpl.disp(Integer.parseInt(up) ,Integer.parseInt(down));
		return "redirect:/client/slidershow/list/p_"+pageIndex;
		
	}
	
}
