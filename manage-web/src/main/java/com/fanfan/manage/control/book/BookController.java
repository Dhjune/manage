package com.fanfan.manage.control.book;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
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
import com.fanfan.manage.inteceptor.AnnatationToken;
import com.fanfan.manage.modle.admin.AdminLoginInfo;
import com.fanfan.manage.modle.base.TransTeam;
import com.fanfan.manage.modle.book.Author;
import com.fanfan.manage.modle.book.Book;
import com.fanfan.manage.modle.book.BookInfo;
import com.fanfan.manage.modle.book.BookType;
import com.fanfan.manage.modle.book.BookTypeRef;
import com.fanfan.manage.service.base.PlatFormServiceImpl;
import com.fanfan.manage.service.base.TransTeamServiceImpl;
import com.fanfan.manage.service.book.AuthorServiceImpl;
import com.fanfan.manage.service.book.BookInfoServiceImpl;
import com.fanfan.manage.service.book.BookPartServiceImpl;
import com.fanfan.manage.service.book.BookPicServiceImpl;
import com.fanfan.manage.service.book.BookServiceImpl;
import com.fanfan.manage.service.book.BookTypeRefServiceImpl;
import com.fanfan.manage.service.book.BookTypeServiceImpl;
import com.fanfan.manage.service.common.PageNav;
import com.fanfan.manage.service.common.PageNavJson;
import com.fanfan.manage.service.vector.AdminOperateVector;



@Controller
@RequestMapping(value="book")
public class BookController {
	
	@Autowired
	private BookServiceImpl bookServiceImpl;
	
	@Autowired 
	private PlatFormServiceImpl platFormServiceImpl;
	
	@Autowired
	private TransTeamServiceImpl transTeamServiceImpl;
	
	@Autowired
	private AuthorServiceImpl authorServiceImpl;
	
	@Autowired
	private BookPicServiceImpl bookPicServiceImpl;
	
	@Autowired
	private BookPartServiceImpl bookPartServiceImpl;
	
	@Autowired
	private BookInfoServiceImpl bookInfoServiceImpl;
	
	@Autowired
	private BookTypeServiceImpl bookTypeServiceImpl;
	
	@Autowired
	private BookTypeRefServiceImpl bookTypeRefServiceImpl;
	
	@Autowired
	private AdminOperateVector adminOperateVector;
	
	
	@RequestMapping(value="home")
	public String index(){
		return"account/book/index";
	}
	
	@RequestMapping(value="create",method=RequestMethod.GET)
	@AnnatationToken(SaveToken=true)
	@AdminInfoToken(secondToken=true)
	public String create(Model model,HttpServletRequest res) throws Exception{
		
		String authorId = res.getParameter("authorId");
		if(authorId !=null && !authorId.equals("")){
			model.addAttribute("author", authorServiceImpl.get(Integer.parseInt(authorId)));
		}else{
			model.addAttribute("authors", authorServiceImpl.list());
		}
		model.addAttribute("teams", transTeamServiceImpl.list());		
		model.addAttribute("platfomrs", platFormServiceImpl.list());
		return "account/book/create";
		
	}
	
	@RequestMapping(value="create",method=RequestMethod.POST)
	@AnnatationToken(RemoveToken=true)
	@AdminInfoToken(secondToken=true)
	public String created(HttpServletRequest res,Book book) throws Exception{
						
		String authorId =  res.getParameter("authorId");		
		String teamId =  res.getParameter("teamId");
		String onlineStr = res.getParameter("onlineStr");
		
		if(authorId!=null&&!authorId.equals("")){
			Author author = authorServiceImpl.get(Integer.parseInt(authorId));
			book.setAuthor(author);
			book.setBookAuthor(author.getName());
		}
		if(onlineStr!=null && !onlineStr.equals("")){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				book.setAddTime(sdf.parse(onlineStr));
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
		}
		
		if(teamId!=null&&!teamId.equals("")){
			
			TransTeam team = transTeamServiceImpl.get(Integer.parseInt(teamId));			
			book.setTransName(team.getName());
			book.setTransTeam(team);
			
		}
		
		BookInfo bookInfo = bookServiceImpl.put(book,true);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "create");
		adminOperateVector.operate(book, adminMap);
		
		adminMap.put("logInfo", "create");
		adminOperateVector.operate(bookInfo, adminMap);
		
		return "redirect:/book/view?id="+book.getId();
		
	}
	
	@RequestMapping(value={"list","list/p_{pageIndex}"},method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	public String list(HttpServletRequest res,Model model) throws Exception{
		
		System.out.println("bookService :"+bookServiceImpl);
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
		List<Book> list =null;	
		String staStr  = res.getParameter("status");
		int status = 1;
		
		if(staStr !=null && !staStr.equals("")){
			status  = Integer.parseInt(staStr);
		}
		
		String url = "/manage/book/list%s?status="+status;	
		int start = pageSize*(pageIndex-1);				
		list = bookServiceImpl.list(start, pageSize,status);			
		int total = bookServiceImpl.count(status);		
		PageNav<Book> context = new PageNav<Book>(list,total,pageSize,pageIndex,url);			
		model.addAttribute("context", context);	
		model.addAttribute("status", status);
		
		return "account/book/list";
	}
	
	@RequestMapping(value={"list/search","list/search/p_{pageIndex}"},method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	public String search(HttpServletRequest res,Model model) throws Exception{
		
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
		String url = "/manage/book/list/search%s";		
		List<Book> list =null;	
		Map map = new HashMap();
		map.put("value", value);
		
		int start = pageSize*(pageIndex-1);				
		list = bookServiceImpl.search(start, pageSize,map);			
		int total = bookServiceImpl.countSearch(map);		
		PageNav<Book> context = new PageNav<Book>(list,total,pageSize,pageIndex,url);			
		model.addAttribute("context", context);	
		model.addAttribute("map", map);
		return "account/book/list";
	}
	
	
	@RequestMapping(value="view",method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	public String view(Model model,HttpServletRequest res) throws Exception{
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		String id =  res.getParameter("id");
		res.getSession().setAttribute("book_id", id);
		Book book =  bookServiceImpl.get(Integer.parseInt(id),1);
//		Map adminMap =new HashMap<String,String>();
//		adminMap.put("ipAddr", res.getRemoteAddr());
//		adminMap.put("adminId", info.getAdmin_id());
//		System.out.println("the admin_id is"+info.getAdmin_id());
//		adminMap.put("logInfo", "测试日志生成器");
//		adminMap.put("", value)
//		adminOperateVector.operate(book, adminMap);
		model.addAttribute("bookInfos", bookInfoServiceImpl.list(Integer.parseInt(id)));
		model.addAttribute("book", book);	
		model.addAttribute("pics", bookPicServiceImpl.list(Integer.parseInt(id)));
		return "account/book/view";
		
		
		
	}
	
	@RequestMapping(value="update",method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	public String update(HttpServletRequest res,Model model)  throws Exception{	
		
		String id = res.getParameter("id");
		Book book =  bookServiceImpl.get(Integer.parseInt(id));						
		model.addAttribute("teams", transTeamServiceImpl.list());
		model.addAttribute("book", book);
		model.addAttribute("authors", authorServiceImpl.list());
		return "account/book/update";
		
	}
	@RequestMapping(value="update",method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	public String updated(HttpServletRequest res)  throws Exception{
		
		//没有采用前面的常用update方式\
		String  id =  res.getParameter("id");
		String authorId  =  res.getParameter("authorId");
		String teamId =  res.getParameter("teamId");
		String bookTitle =  res.getParameter("bookTitle");
		String bookEditor =  res.getParameter("bookEditor");
		String briefIntro =  res.getParameter("briefIntro");		
		String isOffPrint =  res.getParameter("offPrint");
		String isSerialize =  res.getParameter("serialize");
		String transName =  res.getParameter("transName");
		String officialFlag =  res.getParameter("officialFlag");
		String exclusiveFlag =  res.getParameter("exclusiveFlag");
		String bookTags =  res.getParameter("bookTags");
		String onlineStr =  res.getParameter("onlineStr");
		String refBookId =  res.getParameter("refBookId");
		String searchFlag =  res.getParameter("searchFlag");	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Book book =  bookServiceImpl.get(Integer.parseInt(id));
		book.setBookTitle(bookTitle);
		book.setBookEditor(bookEditor);
		book.setBriefIntro(briefIntro);
		book.setOffPrint(Integer.parseInt(isOffPrint));
		book.setSerialize(Integer.parseInt(isSerialize));
		book.setTransName(transName);
		//book.setRefBookId(Integer.parseInt(refBookId));
		book.setOfficialFlag(Integer.parseInt(officialFlag));
		book.setExclusiveFlag(Integer.parseInt(exclusiveFlag));
		book.setBookTags(bookTags);
		book.setSearchFlag(Integer.parseInt(searchFlag));
		if(onlineStr!=null && !onlineStr.equals("")){
			try {
				book.setOnlineTime(sdf.parse(onlineStr));
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
		}
		
		
		if(authorId!=null &&!authorId.equals("")){
			Author author = authorServiceImpl.get(Integer.parseInt(authorId));
			book.setAuthor(author);
			book.setBookAuthor(author.getName());
			
		}
		if(teamId!=null &&!teamId.equals("")){
			
			TransTeam team = transTeamServiceImpl.get(Integer.parseInt(teamId));
			if(team!=null){
				book.setTransName(team.getName());
				book.setTransTeam(team);
			}
						
		}		
		bookServiceImpl.update(book);	
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "update");
		adminOperateVector.operate(book, adminMap);
		return "redirect:/book/list";				
	}
	
	@RequestMapping(value="pageNavJson",produces = "application/json")
	@ResponseBody
	@AdminInfoToken(secondToken=true)
	public PageNavJson pageNavJson(@RequestBody Map map) throws Exception{
		
		List<Book> list = null;		
		
		int pageSize = Constants.ACCOUNT_DEFAULT_PAGE_SIZE;	
		int pageIndex ;
//		System.out.println(map.get("list"));
//		List mlist = (List) map.get("list");
//		for(Object o : mlist){
//			System.out.println(o.toString());
//		}
		if(map.get("pageIndex")!=null){
			pageIndex = Integer.parseInt(map.get("pageIndex").toString());
		}else{
			pageIndex = 1;
		}
		
		int start = pageSize*(pageIndex-1);	
		list =  bookServiceImpl.list(start,pageSize,map);	
		int total = bookServiceImpl.count(map);	
		PageNavJson<Book> context = new PageNavJson<Book>(list,total,pageSize,pageIndex);
		return context;		
	}
	
	@RequestMapping(value="listJson",produces = "application/json")
	@AdminInfoToken(secondToken=true)
	@ResponseBody
	public List<Book> listJson(@RequestBody Map map)  throws Exception{
		
		List<Book> list = null;		
		list =  bookServiceImpl.list(map);		
		return list;		
	}
	
	
	@RequestMapping(value="used")
	@AdminInfoToken(secondToken=true)
	public String used(HttpServletRequest res)  throws Exception{
		
		String id =  res.getParameter("id");
		String status =  res.getParameter("status");
		Book book =  bookServiceImpl.get(Integer.parseInt(id));
		book.setStatus(Integer.parseInt(status));
		bookServiceImpl.update(book);		
		return "redirect:/book/view?id="+id;
		
	}
	
	@RequestMapping(value="delete")
	@AdminInfoToken(secondToken=true)
	public String delete(HttpServletRequest res)  throws Exception{
		String id = res.getParameter("id");
		Book book = bookServiceImpl.get(Integer.parseInt(id));
		bookServiceImpl.delete(book);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "delete");
		adminOperateVector.operate(book, adminMap);
		return "redirect:/book/list";
	}
	
	
	
	
	@RequestMapping(value="types")
	@AdminInfoToken(secondToken=true)
	public String types(HttpServletRequest res,Model model)  throws Exception{		
		String id = res.getParameter("id");
		Book book =  bookServiceImpl.get(Integer.parseInt(id),1);
		model.addAttribute("book", book);
		model.addAttribute("typerefs",bookTypeRefServiceImpl.list(Integer.parseInt(id)));	
		return "account/book/types";		
	}
	@RequestMapping(value="types/remove")
	@AdminInfoToken(secondToken=true)
	public String removeType(HttpServletRequest res)  throws Exception{
		
		String typeId = res.getParameter("typeId");
		String bookId =  res.getParameter("bookId");
		Book book =  bookServiceImpl.get(Integer.parseInt(bookId));
		BookType type =  bookTypeServiceImpl.get(Integer.parseInt(typeId));
		BookTypeRef ref = bookTypeRefServiceImpl.get(Integer.parseInt(bookId), Integer.parseInt(typeId));
		ref.setStatus(0);
		//book.getBooktypes().remove(type);
		//bookServiceImpl.update(book);
		bookTypeRefServiceImpl.update(ref);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "delete");
		adminOperateVector.operate(ref, adminMap);
		return "redirect:/book/types?id="+bookId;
		
	}
	
}
