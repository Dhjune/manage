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
import com.fanfan.manage.modle.book.Book;
import com.fanfan.manage.modle.client.Recommend;
import com.fanfan.manage.modle.client.SliderShow;
import com.fanfan.manage.service.base.PlatFormServiceImpl;
import com.fanfan.manage.service.book.BookTypeServiceImpl;
import com.fanfan.manage.service.client.RecommendServiceImpl;
import com.fanfan.manage.service.common.PageNav;
import com.fanfan.manage.service.vector.AdminOperateVector;

@Controller
@RequestMapping(value="client/recommend/")
public class RecommendController {

	@Autowired
	private RecommendServiceImpl recommendServiecImpl;	
	
	@Autowired
	private BookTypeServiceImpl bookTypeServiceImpl;
	
	@Autowired
	private PlatFormServiceImpl platFormServiceImpl;
	
	@Autowired
	private AdminOperateVector adminOperateVector;
	
	
	@RequestMapping(value="create",method=RequestMethod.GET)
	@AnnatationToken(SaveToken=true)
	@AdminInfoToken(secondToken=true)
	private String create(Model model) throws Exception{
		model.addAttribute("platforms", platFormServiceImpl.list());
		model.addAttribute("booktypes", bookTypeServiceImpl.list());
		return "account/client/recommend/create";
		
	}
	
	@RequestMapping(value="create",method=RequestMethod.POST)
	@AnnatationToken(RemoveToken=true)
	@AdminInfoToken(secondToken=true)
	private String created(Recommend rec,HttpServletRequest res) throws Exception{
		
		recommendServiecImpl.put(rec);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "create");
		adminOperateVector.operate(rec, adminMap);
		return "redirect:/client/recommend/list";
		
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
		String pageStr = res.getParameter("pageIndex");
		if(pageStr!=null && !pageStr.equals("")){
			pageIndex = Integer.parseInt(pageStr);
		}
		int pageSize = Constants.ACCOUNT_DEFAULT_PAGE_SIZE;		
		String url = "/manage/client/recommend/list%s";		
		List<Recommend> list =null;	
		int total = 0;
		int start = pageSize*(pageIndex-1);	
		if(pageIndex>1){
			start-=1;
		}
		
		list = recommendServiecImpl.list(start,pageSize);
		total = recommendServiecImpl.count();
//		if(list !=null){
//			total = list.size();
//		}
		PageNav<Recommend> context = new PageNav<Recommend>(list,total,pageSize,pageIndex,url);			
		model.addAttribute("context", context);			
		return "account/client/recommend/list";
	}
	
	@RequestMapping(value="update",method=RequestMethod.GET)
	@AnnatationToken(SaveToken=true)
	@AdminInfoToken(secondToken=true)
	public String update(HttpServletRequest res,Model model)  throws Exception{
		String id =  res.getParameter("id");
		Recommend recommed =  recommendServiecImpl.get(Integer.parseInt(id));
		model.addAttribute("recommend", recommed);
		model.addAttribute("platforms", platFormServiceImpl.list());
		model.addAttribute("booktypes", bookTypeServiceImpl.list());
		return "account/client/recommend/update";
		
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	@AnnatationToken(RemoveToken=true)
	@AdminInfoToken(secondToken=true)
	public String updated(Recommend recommend,HttpServletRequest res)  throws Exception{
		recommendServiecImpl.update(recommend);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "update");
		adminOperateVector.operate(recommend, adminMap);
		return "redirect:/client/recommend/list";
	}
	
	@RequestMapping(value="view")
	@AdminInfoToken(secondToken=true)
	public String view(HttpServletRequest res,Model model)  throws Exception{
		
		String id =  res.getParameter("id");
		
		Recommend recommed =  recommendServiecImpl.get(Integer.parseInt(id));
		
		model.addAttribute("recommend", recommed);
		model.addAttribute("platforms", platFormServiceImpl.list());
		model.addAttribute("booktypes", bookTypeServiceImpl.list());
		return "account/client/recommend/view";
	}
	
	@RequestMapping(value="disp")
	@AdminInfoToken(secondToken=true)
	public String disp(HttpServletRequest res)  throws Exception{
		
		String down =  res.getParameter("down");
		String up  =  res.getParameter("up");
		String pageIndex = res.getParameter("pageIndex");
		recommendServiecImpl.disp(Integer.parseInt(up) ,Integer.parseInt(down));
		return "redirect:/client/recommend/list/p_"+pageIndex;
		
	}
	
	@RequestMapping(value="delete")
	@AdminInfoToken(secondToken=true)
	public String delete(HttpServletRequest res) throws Exception{
		String id =  res.getParameter("id");
		Recommend rec = recommendServiecImpl.get(Integer.parseInt(id));
		rec.setStatus(0);
		recommendServiecImpl.update(rec);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "delete");
		adminOperateVector.operate(rec, adminMap);
		return "redirect:/client/recommend/list";
		
	}
}
