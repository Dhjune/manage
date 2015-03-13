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
import org.springframework.web.bind.annotation.ResponseBody;

import com.fanfan.manage.inteceptor.AdminInfoToken;
import com.fanfan.manage.modle.admin.AdminLoginInfo;
import com.fanfan.manage.modle.book.Book;
import com.fanfan.manage.modle.book.BookPart;
import com.fanfan.manage.modle.book.BookType;
import com.fanfan.manage.modle.book.BookTypeRef;
import com.fanfan.manage.modle.book.BookTypeRefPK;
import com.fanfan.manage.service.book.BookServiceImpl;
import com.fanfan.manage.service.book.BookTypeRefServiceImpl;
import com.fanfan.manage.service.book.BookTypeServiceImpl;
import com.fanfan.manage.service.common.PageNav;
import com.fanfan.manage.service.vector.AdminOperateVector;

@Controller
@RequestMapping(value="book/type/ref/")
public class BookTypeRefController {

	@Autowired
	private BookTypeRefServiceImpl bookTypeRefServiceImpl;
	
	@Autowired
	private BookServiceImpl bookServiceImpl;
	
	@Autowired
	private AdminOperateVector adminOperateVector;
	
	@Autowired
	private BookTypeServiceImpl bookTypeServiceImpl;
	
	@RequestMapping(value="create",method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	public String  create(BookTypeRef typeRef,HttpServletRequest res)  throws Exception{
		
		String bookId =  res.getParameter("bookId");		
		String typeId =  res.getParameter("typeId");
		Book book =  bookServiceImpl.get(Integer.parseInt(bookId));
		BookType type =  bookTypeServiceImpl.get(Integer.parseInt(typeId));	
		BookTypeRefPK pk =new BookTypeRefPK();
		pk.setBook(book);
		pk.setBookType(type);
		typeRef.setPk(pk);				
		bookTypeRefServiceImpl.put(typeRef);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "create");
		adminOperateVector.operate(typeRef, adminMap);
		return "redirect:/book/view?id="+bookId;
		
	}
	@RequestMapping(value="view")
	@AdminInfoToken(secondToken=true)
	public String view(HttpServletRequest res){
		String bookId =  res.getParameter("bookId");
		String typeId =  res.getParameter("typeId"); 
		return "";
	}
	
	@RequestMapping(value="delete")
	@AdminInfoToken(secondToken=true)
	public String delete(HttpServletRequest res)  throws Exception{
		String bookId =  res.getParameter("bookId");
		String typeId =  res.getParameter("typeId");		
		BookTypeRef typeRef =  bookTypeRefServiceImpl.get(Integer.parseInt(bookId),Integer.parseInt(typeId));		
	//	System.out.println("the method type no error:"+typeRef.getStatus());
		return "redirect:/book/types?id="+bookId; 
	}
	
	@RequestMapping(value="list",method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	public List<BookTypeRef> listJson(HttpServletRequest res)  throws Exception {
		String bookId =  res.getParameter("bookId");
		List<BookTypeRef> list = bookTypeRefServiceImpl.list();
		return list;
		
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
		int pageSize = 48;
		int bookId = Integer.parseInt(res.getParameter("bookId"));
		String url = "/manage/book/type/ref/list%s?bookId="+bookId;		
		List<BookTypeRef> list =null;	
		Book book = bookServiceImpl.get(bookId);
		int start = pageSize*(pageIndex-1);				
		list = bookTypeRefServiceImpl.list(bookId,start, pageSize);			
		int total = bookTypeRefServiceImpl.count(bookId);		
		PageNav<BookTypeRef> context = new PageNav<BookTypeRef>(list,total,pageSize,pageIndex,url);			
		model.addAttribute("context", context);
		model.addAttribute("bookId", bookId);
		model.addAttribute("book", book);
		return "account/book/part/list";
			
		
	}
	
	@RequestMapping(value="upused")
	@AdminInfoToken(secondToken=true)
	public String upused(HttpServletRequest res) throws Exception{
		
		String bookId = res.getParameter("bookId");
		String typeId =  res.getParameter("typeId");
		String status =  res.getParameter("status");
		
		BookTypeRef btref = bookTypeRefServiceImpl.get(Integer.parseInt(bookId), Integer.parseInt(typeId));
		if(btref!=null){
			
			btref.setStatus(Integer.parseInt(status));
			
			bookTypeRefServiceImpl.update(btref);
			AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
			Map adminMap =new HashMap<String,String>();
			adminMap.put("ipAddr", res.getRemoteAddr());
			adminMap.put("adminId", info.getAdmin_id());
			adminMap.put("logInfo", "update");
			adminOperateVector.operate(btref, adminMap);
			
		}
		
		return "redirect:/book/types?id="+bookId;
	}
	
	
	
}
