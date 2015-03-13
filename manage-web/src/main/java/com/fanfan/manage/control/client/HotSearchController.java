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
import com.fanfan.manage.modle.client.HotSearch;
import com.fanfan.manage.modle.client.Recommend;
import com.fanfan.manage.service.base.PlatFormServiceImpl;
import com.fanfan.manage.service.client.HotSearchServiceImpl;
import com.fanfan.manage.service.common.PageNav;
import com.fanfan.manage.service.vector.AdminOperateVector;

@Controller
@RequestMapping(value="client/hotsearch/")
public class HotSearchController {
	
	@Autowired
	private HotSearchServiceImpl hotSearchServiceImpl;
	@Autowired
	private PlatFormServiceImpl platFormServiceImpl;
	@Autowired
	private AdminOperateVector adminOperateVector;
	
	
	@RequestMapping(value="create",method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	private String create(Model model)  throws Exception{
		
		model.addAttribute("platforms", platFormServiceImpl.list());
		return "account/client/hotsearch/create";
		
	}
	
	@RequestMapping(value="create",method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	private String created(HotSearch search,HttpServletRequest res)  throws Exception{
		
		hotSearchServiceImpl.put(search);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "create");
		adminOperateVector.operate(search, adminMap);
		return "redirect:/client/hotsearch/list";
		
	}
	
	@RequestMapping(value={"list","list/p_{pageIndex}"},method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	public String list(HttpServletRequest res,Model model) throws Exception{
		
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
		String url = "/manage/client/hotsearch/list%s";		
		List<HotSearch> list =null;	
		int start = pageSize*(pageIndex-1);	
		if(pageIndex>1){
			start -=1;
		}
		list =  hotSearchServiceImpl.list(start,pageSize);
		int count=0;		
		count = hotSearchServiceImpl.count();
		
//		if(list!=null){
//			count = list.size();
//		}	
		PageNav<HotSearch> context = new PageNav<HotSearch>(list,count,pageSize,pageIndex,url);			
		model.addAttribute("context", context);	
		return "account/client/hotsearch/list";
		
	}
	
	@RequestMapping(value="update",method=RequestMethod.GET)
	@AnnatationToken(SaveToken=true)
	@AdminInfoToken(secondToken=true)
	public String update(HttpServletRequest res,Model model) throws Exception{
		
		String id =  res.getParameter("id");
		HotSearch search =  hotSearchServiceImpl.get(Integer.parseInt(id));
		model.addAttribute("search", search);
		model.addAttribute("platforms", platFormServiceImpl.list());		
		return "account/client/hotsearch/update";
		
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	@AnnatationToken(RemoveToken=true)
	public String updated(HotSearch search,HttpServletRequest res) throws Exception{
		hotSearchServiceImpl.update(search);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "update");
		adminOperateVector.operate(search, adminMap);
		
		return "redirect:/client/hotsearch/list";
	}
	
	@RequestMapping(value="view")
	@AdminInfoToken(secondToken=true)
	public String view(HttpServletRequest res,Model model)  throws Exception{
		
		String id =  res.getParameter("id");		
		HotSearch search =  hotSearchServiceImpl.get(Integer.parseInt(id));	
		model.addAttribute("search", search);
		model.addAttribute("platforms", platFormServiceImpl.list());		
		return "account/client/recommend/view";
	}
	
	@RequestMapping(value="disp")
	@AdminInfoToken(secondToken=true)
	public String disp(HttpServletRequest res)  throws Exception{
		
		String down =  res.getParameter("down");
		String up  =  res.getParameter("up");	
		String pageIndex = res.getParameter("pageIndex");
		hotSearchServiceImpl.disp(Integer.parseInt(up) ,Integer.parseInt(down) );		
		return "redirect:/client/hotsearch/list/p_"+pageIndex;		
	}
	
	@RequestMapping(value="delete")
	@AdminInfoToken(secondToken=true)
	public String delete(HttpServletRequest res)  throws Exception{
		String id =  res.getParameter("id");
		HotSearch search = hotSearchServiceImpl.get(Integer.parseInt(id));
		search.setStatus(0);
		hotSearchServiceImpl.update(search);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "update");
		adminOperateVector.operate(search, adminMap);
		return "redirect:/client/hotsearch/list";
		
	}

}
