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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fanfan.manage.common.Constans;
import com.fanfan.manage.inteceptor.AdminInfoToken;
import com.fanfan.manage.inteceptor.AnnatationToken;
import com.fanfan.manage.modle.admin.AdminLoginInfo;
import com.fanfan.manage.modle.book.Book;
import com.fanfan.manage.modle.book.EditorComment;
import com.fanfan.manage.service.book.BookServiceImpl;
import com.fanfan.manage.service.book.EdCommentServiceImpl;
import com.fanfan.manage.service.common.PageNav;
import com.fanfan.manage.service.vector.AdminOperateVector;


@Controller
@RequestMapping(value="book/edcomment")
public class EdCommentController {

	@Autowired
	private EdCommentServiceImpl edCommentServiceImpl;
	
	@Autowired
	private BookServiceImpl bookServiceImpl;
	
	@Autowired
	private AdminOperateVector adminOperateVector;
	
	@RequestMapping(value="home")
	public String home(){
		return "account/book/edcomment/home";
	}
	
	@RequestMapping(value="create",method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	@AnnatationToken(SaveToken=true)
	public String create(HttpServletRequest res,Model model) throws Exception{
		
		String  bookId = res.getParameter("bookId");
		Book book = bookServiceImpl.get(Integer.parseInt(bookId));
		model.addAttribute("book", book);
		
		return "account/book/edcomment/create";
		
	}
	
	@RequestMapping(value="create",method=RequestMethod.POST)
	@AnnatationToken(RemoveToken=true)
	@AdminInfoToken(secondToken=true)
	public String created(HttpServletRequest res,EditorComment edComment)  throws Exception{
		
		String  bookId =  res.getParameter("bookId");
		Book book = bookServiceImpl.get(Integer.parseInt(bookId));
		edComment.setBook(book);
		edCommentServiceImpl.put(edComment);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "create");
		adminOperateVector.operate(edComment, adminMap);		
		return "redirect:/book/edcomment/list?bookId="+bookId;
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
		int pageSize = Constans.ACCOUNT_DEFAULT_PAGE_SIZE;
		int bookId = Integer.parseInt(res.getParameter("bookId"));
		String url = "/manage/book/edcomment/list%s?bookId="+bookId;			
		List<EditorComment> list =null;	
		Book book = bookServiceImpl.get(bookId);
		int start = pageSize*(pageIndex-1);				
		list = edCommentServiceImpl.list(bookId,start, pageSize);			
		int total = edCommentServiceImpl.count(bookId);		
		PageNav<EditorComment> context = new PageNav<EditorComment>(list,total,pageSize,pageIndex,url);			
		model.addAttribute("context", context);
		model.addAttribute("bookId", bookId);
		model.addAttribute("book", book);
		return "account/book/edcomment/list";
		
		
	}
	
	@RequestMapping(value="update",method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	public String update(){
		
		return "";
		
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	public String updated(HttpServletRequest  res,EditorComment edComment) throws Exception{
		
		String bookId =  res.getParameter("bookId");
		Book book = bookServiceImpl.get(Integer.parseInt(bookId));
		edComment.setBook(book);
		edCommentServiceImpl.update(edComment);	
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "update");
		adminOperateVector.operate(edComment, adminMap);
		return "redirect:/book/edcomment/list?bookId="+bookId;
		
	}
	
	@RequestMapping(value="view",method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	public String view(HttpServletRequest  res,Model model) throws Exception{
		
		int id = Integer.parseInt(res.getParameter("id"));
		int bookId =  Integer.parseInt(res.getParameter("bookId"));
		model.addAttribute("edcomment", edCommentServiceImpl.get(id));
		model.addAttribute("book", bookServiceImpl.get(bookId));		
		return "account/book/edcomment/view";
		
	}
	
	@RequestMapping(value="delete")
	@AdminInfoToken(secondToken=true)
	public String delete(HttpServletRequest res)  throws Exception{
		
		String id =  res.getParameter("id");
		String  bookId =  res.getParameter("bookId");
		EditorComment edComment =edCommentServiceImpl.get(Integer.parseInt(id));
		edCommentServiceImpl.delete(edComment);		
		return "redirect:/book/edcomment/list?bookId="+bookId;
		
	}
	
}
