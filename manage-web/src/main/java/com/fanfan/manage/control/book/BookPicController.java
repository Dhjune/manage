package com.fanfan.manage.control.book;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fanfan.manage.inteceptor.AdminInfoToken;
import com.fanfan.manage.modle.admin.AdminLoginInfo;
import com.fanfan.manage.modle.book.Book;
import com.fanfan.manage.modle.book.BookPic;
import com.fanfan.manage.service.book.BookPicServiceImpl;
import com.fanfan.manage.service.book.BookServiceImpl;
import com.fanfan.manage.service.vector.AdminOperateVector;

@Controller
@RequestMapping(value="book/pic")
public class BookPicController {
	
	@Autowired
	private BookServiceImpl bookServiceImpl;
	
	@Autowired
	private BookPicServiceImpl bookPicServiceImpl;
	
	@Autowired
	private AdminOperateVector adminOperateVector;
	
	@RequestMapping(value="create",method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	public String create(BookPic bookPic,HttpServletRequest res)  throws Exception{		
		int bookId = Integer.parseInt(res.getParameter("bookId"));			
		Book book = bookServiceImpl.get(bookId);
		bookPic.setBook(book);
		bookPicServiceImpl.put(bookPic, bookId);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "create");
		adminOperateVector.operate(bookPic, adminMap);	
		return "redirect:/book/pic/list?bookId="+bookId;
		
	}
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	public String list(HttpServletRequest res,Model model) throws Exception{
		
		int bookId = Integer.parseInt(res.getParameter("bookId"));	
		model.addAttribute("pics", bookPicServiceImpl.list(bookId));
		model.addAttribute("book", bookServiceImpl.get(bookId));
		return "account/book/pic/list";
		
	}
	
	@RequestMapping(value="update",method=RequestMethod.GET)
	@AdminInfoToken(secondToken=true)
	public String update(HttpServletRequest res,Model model) throws Exception{
		
		String id = res.getParameter("id");
		String bookId = res.getParameter("bookId");
		BookPic bookPic= bookPicServiceImpl.get(Integer.parseInt(id));
		model.addAttribute("pic", bookPic);
		return "account/book/pic/update";
		
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	@AdminInfoToken(secondToken=true)
	public String updated(BookPic bookPic,HttpServletRequest res) throws Exception{
		String bookId =  res.getParameter("bookId");
		Book book = bookServiceImpl.get(Integer.parseInt(bookId));
		bookPic.setBook(book);
		bookPicServiceImpl.update(bookPic);
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "update");
		adminOperateVector.operate(bookPic, adminMap);
		return "redirect:/book/pic/list?bookId="+bookId;		
	}
	
	@RequestMapping(value="upstatus")
	@AdminInfoToken(secondToken=true)
	public String upstatus(HttpServletRequest res) throws Exception{
		String id =res.getParameter("id");
		String status =  res.getParameter("status");
		String bookId =  res.getParameter("bookId");
		BookPic pic = bookPicServiceImpl.get(Integer.parseInt(id));
		pic.setStatus(Integer.parseInt(status));
		bookPicServiceImpl.merge(pic);
		//bookPicServiceImpl.update(pic);
		return "redirect:/book/pic/list?bookId="+bookId;
	}
	
	@RequestMapping(value="delete")
	@AdminInfoToken(secondToken=true)
	public String delete(HttpServletRequest res) throws Exception{
		
		String id =  res.getParameter("id");
		String bookId =  res.getParameter("bookId");		
		BookPic bookPic =  bookPicServiceImpl.get(Integer.parseInt(id));
		bookPicServiceImpl.delete(bookPic);	
		AdminLoginInfo info = (AdminLoginInfo) res.getSession().getAttribute("admin");
		Map adminMap =new HashMap<String,String>();
		adminMap.put("ipAddr", res.getRemoteAddr());
		adminMap.put("adminId", info.getAdmin_id());
		adminMap.put("logInfo", "delete");
		adminOperateVector.operate(bookPic, adminMap);
		return "redirect:/book/pic/list?bookId="+bookId;
		
	}
	
}
