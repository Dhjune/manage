package com.fanfan.manage.control.book;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fanfan.manage.common.Constants;
import com.fanfan.manage.inteceptor.AdminInfoToken;
import com.fanfan.manage.inteceptor.AnnatationToken;
import com.fanfan.manage.modle.admin.AdminLoginInfo;
import com.fanfan.manage.modle.book.Author;
import com.fanfan.manage.modle.book.BookType;
import com.fanfan.manage.service.book.BookTypeServiceImpl;
import com.fanfan.manage.service.common.PageNav;
import com.fanfan.manage.service.vector.AdminOperateVector;

@Controller
@RequestMapping(value="book/type")
public class BookTypeController {
	
	@Autowired
	private BookTypeServiceImpl bookTypeServiceImpl;
	
	@Autowired
	private AdminOperateVector adminOperateVector;
	
	
	@RequestMapping(value="create",method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	public String create(){		
		
		return "account/book/type/create";
		
	}
	
	@RequestMapping(value="create",method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	public String created(BookType obj,HttpServletRequest res) throws Exception{
		
		bookTypeServiceImpl.put(obj);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "create");
		adminOperateVector.operate(obj, adminMap);
		return "redirect:/book/type/list";		
	}
	
	
	@RequestMapping(value="update",method=RequestMethod.GET)
	@AnnatationToken(SaveToken=true)
	@AdminInfoToken(secondToken=true)
	public String update(HttpServletRequest res,Model model) throws Exception{
		
		String  id =  res.getParameter("id");
		
		BookType bookType =  bookTypeServiceImpl.get(Integer.parseInt(id));
		model.addAttribute("type", bookType);
		
		return "account/book/type/update";
		
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	public String updated(BookType bookType,HttpServletRequest res) throws Exception{
		
		bookTypeServiceImpl.update(bookType);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "create");
		adminOperateVector.operate(bookType, adminMap);
		return "redirect:/book/type/list";
		
	}
	
	@RequestMapping(value="delete")
	@AdminInfoToken(secondToken=true)
	public String delete(HttpServletRequest res)  throws Exception{
		
		int id = Integer.parseInt(res.getParameter("id"));
		
		BookType bookType = bookTypeServiceImpl.get(id);
		
		bookTypeServiceImpl.delete(bookType);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "create");
		adminOperateVector.operate(bookType, adminMap);
		return "redirect:/book/type/list";
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
		String staStr = res.getParameter("status");
		int status = 1;
		if(staStr !=null&&!staStr.equals("")){
			status = Integer.parseInt(staStr);
		}
		int pageSize = Constants.ACCOUNT_DEFAULT_PAGE_SIZE;		
		String url = "/manage/book/type/list%s";		
		List<BookType> list =null;		
		int start = pageSize*(pageIndex-1);				
		list = bookTypeServiceImpl.list(start, pageSize,status);			
		int total = bookTypeServiceImpl.count(status);		
		PageNav<BookType> context = new PageNav<BookType>(list,total,pageSize,pageIndex,url);			
		model.addAttribute("context", context);	
		model.addAttribute("status", status);
		return "account/book/type/list";		
	}
	
	@RequestMapping(value="listJson",method=RequestMethod.POST)
	@ResponseBody
	@AdminInfoToken(secondToken=true)
	public List<BookType> List()  throws Exception{	
		//System.out.println("request is comming ");
		ArrayList<BookType> list = null;
		list = (ArrayList<BookType>) bookTypeServiceImpl.list();
		return list;
	}
	@RequestMapping(value="upused")
	@AdminInfoToken(secondToken=true)
	public String upused(@RequestParam("id") int id,@RequestParam("status") int status,HttpServletRequest res)  throws Exception{
		
		BookType bookType =  bookTypeServiceImpl.get(id);
		if(bookType!=null){
			bookType.setStatus(status);
			bookTypeServiceImpl.update(bookType);
		}
		
		return "redirect:/book/type/list";
		
	}
	
	

}
