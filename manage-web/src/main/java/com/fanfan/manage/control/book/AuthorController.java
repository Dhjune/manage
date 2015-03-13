package com.fanfan.manage.control.book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fanfan.manage.common.Constants;
import com.fanfan.manage.inteceptor.AdminInfoToken;
import com.fanfan.manage.modle.admin.AdminLoginInfo;
import com.fanfan.manage.modle.book.Author;
import com.fanfan.manage.modle.book.Book;
import com.fanfan.manage.service.book.AuthorServiceImpl;
import com.fanfan.manage.service.book.BookServiceImpl;
import com.fanfan.manage.service.common.PageNav;
import com.fanfan.manage.service.common.PageNavJson;
import com.fanfan.manage.service.vector.AdminOperateVector;



@Controller
@RequestMapping(value="book/author")
public class AuthorController {
	
	@Autowired
	private AuthorServiceImpl authorServiceImpl;
	
	
	@Autowired
	private BookServiceImpl bookServiceImpl;
	
	@Autowired
	private AdminOperateVector adminOperateVector;
	
	@RequestMapping(value="home",method=RequestMethod.GET)
	public String index(){
		
		return "account/book/author/home";
		
	}
	
	@RequestMapping(value="create",method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	public String create(){
		
		return "account/book/author/create";
		
	}
	
	@RequestMapping(value="create",method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	public String created(Author author,HttpServletRequest res) throws Exception{	
		
		authorServiceImpl.put(author);
		if(author.getId()>0){
			AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
			Map adminMap =new HashMap<String,String>();
			adminMap.put("ipAddr", res.getRemoteAddr());
			adminMap.put("adminId", info.getAdmin_id());
			adminMap.put("logInfo", "create");
			adminOperateVector.operate(author, adminMap);
			return "redirect:/book/author/view?id="+author.getId();
			
		}else{
		
			return "redirect:/book/author/list";
			
		}
						
		
		
	}
	
	@RequestMapping(value={"list","list/p_{pageIndex}"},method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	public String list(HttpServletRequest res,Model model) throws Exception{
		
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
		String staStr  = res.getParameter("status");
		int status = 1;
		if(staStr !=null && !staStr.equals("")){
			status  = Integer.parseInt(staStr);
		}
		int pageSize = Constants.ACCOUNT_DEFAULT_PAGE_SIZE;
		String url = "/manage/book/author/list%s?status="+status;				
		List<Author> list =null;
		int start = pageSize*(pageIndex-1);		
		list = authorServiceImpl.list(start, pageSize,status);			
		int total = authorServiceImpl.count();
		//int total = list.size();
		PageNav<Author> context = new PageNav<Author>(list,total,pageSize,pageIndex,url);	
		model.addAttribute("context", context);			
		return "account/book/author/list";	
		
	}
	
	@RequestMapping(value={"list/search","list/search/p_{pageIndex}"},method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	public String search(HttpServletRequest res,Model model)  throws Exception{
		
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
		String value =  res.getParameter("value");
		int pageSize = Constants.ACCOUNT_DEFAULT_PAGE_SIZE;		
		String url = "/manage/book/author/list/search%s";		
		List<Author> list =null;	
		Map map = new HashMap();
		map.put("value", value);
		int start = pageSize*(pageIndex-1);				
		list = authorServiceImpl.search(start, pageSize,map);			
		int total = authorServiceImpl.countSearch(map);		
		PageNav<Author> context = new PageNav<Author>(list,total,pageSize,pageIndex,url);			
		model.addAttribute("context", context);	
		model.addAttribute("map", map);
		return "account/book/author/list";
	}
	
	
	@RequestMapping(value="/view")
	@AdminInfoToken(secondToken=true)
	public String view(HttpServletRequest res,Model model)  throws Exception{
		
		String id =  res.getParameter("id");		
		Author author =  authorServiceImpl.get(Integer.parseInt(id));		
		model.addAttribute("author", author);
				
		return "account/book/author/view";			
	}
	
	@RequestMapping(value="used")
	@AdminInfoToken(secondToken=true)
	public String used(HttpServletRequest res)  throws Exception{
		
		String id =  res.getParameter("id");
		String status =  res.getParameter("status");
		Author author =  authorServiceImpl.get(Integer.parseInt(id));
		author.setStatus(Integer.parseInt(status));
		authorServiceImpl.update(author);		
		return "redirect:/book/author/view?id="+id;
		
	}
	
	@RequestMapping(value="delete")
	@AdminInfoToken(secondToken=true)
	public String delete(HttpServletRequest res)  throws Exception{
		String id =  res.getParameter("id");
		Author author =  authorServiceImpl.get(Integer.parseInt(id));
		author.setStatus(0);
		authorServiceImpl.update(author);
		
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "delete");
		adminOperateVector.operate(author, adminMap);
		
		return "redirect:/book/author/list";
		
	}
	
	@RequestMapping(value="update",method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	public String update(HttpServletRequest res ,Model model)  throws Exception{
		
		String id =  res.getParameter("id");
		model.addAttribute("author", authorServiceImpl.get(Integer.parseInt(id)));
		
		return "account/book/author/update";
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	public String update(Author author,HttpServletRequest res)  throws Exception{
		
		authorServiceImpl.update(author);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "update");
		adminOperateVector.operate(author, adminMap);		
		return "redirect:/book/author/list";
		
	}
	
	@RequestMapping(value="pageNavJson",produces = "application/json")
	@AdminInfoToken(secondToken=true)
	@ResponseBody
	public PageNavJson<Author> pageNavJson(@RequestBody Map map)  throws Exception{
		List<Author> list = null;				
		int pageSize = Constants.ACCOUNT_DEFAULT_PAGE_SIZE;	
		int pageIndex ;
		
		if(map.get("pageIndex")!=null){
			pageIndex = Integer.parseInt(map.get("pageIndex").toString());
		}else{
			pageIndex = 1;
		}		
		int start = pageSize*(pageIndex-1);	
		list =  authorServiceImpl.list(start,pageSize,map);	
		int total = authorServiceImpl.count(map);	
		PageNavJson<Author> context = new PageNavJson<Author>(list,total,pageSize,pageIndex);
		return context;		
	}

}
