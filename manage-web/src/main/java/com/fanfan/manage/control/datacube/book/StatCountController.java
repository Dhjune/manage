package com.fanfan.manage.control.datacube.book;

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
import com.fanfan.manage.modle.book.Book;
import com.fanfan.manage.modle.book.BookStatCount;
import com.fanfan.manage.modle.book.StatCountbook;
import com.fanfan.manage.service.base.PlatFormServiceImpl;
import com.fanfan.manage.service.common.PageNav;
import com.fanfan.manage.service.common.PageNavJson;
import com.fanfan.manage.service.datacube.book.StatCountServiceImpl;

@Controller
@RequestMapping(value="datacube/book/stat/count")
public class StatCountController {
	
	@Autowired
	private StatCountServiceImpl statCountServiceImpl;
	
	@Autowired
	private PlatFormServiceImpl platFormServiceImpl;
	
	@RequestMapping(value={"list","list/p_{pageIndex}"},method=RequestMethod.GET)
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
		int pageSize = Constants.ACCOUNT_DEFAULT_PAGE_SIZE;		
			
		List<StatCountbook> list =null;	
		String platStr  = res.getParameter("platId");
		int platId = 0;
		if(platStr !=null && !platStr.equals("")){
			platId  = Integer.parseInt(platStr);
		}
		int start = pageSize*(pageIndex-1);	
		String url = "/manage/datacube/book/stat/count/list%s?platId="+platId;	
		list = statCountServiceImpl.list(platId,start, pageSize);			
		int total = statCountServiceImpl.count(platId);		
		PageNav<StatCountbook> context = new PageNav<StatCountbook>(list,total,pageSize,pageIndex,url);
		
		model.addAttribute("context", context);	
		model.addAttribute("plats", platFormServiceImpl.list());
		model.addAttribute("platId", platId);
		
		return "account/datacube/book/stat/countlist";
	}
	
	
	@RequestMapping(value="pageNavJson",produces = "application/json")
	@ResponseBody
	public PageNavJson pageNavJson(@RequestBody Map map)  throws Exception{
		int pageSize = Constants.ACCOUNT_DEFAULT_PAGE_SIZE;	
		int pageIndex ;
		
		if(map.get("pageIndex")!=null){
			pageIndex = Integer.parseInt(map.get("pageIndex").toString());
			System.out.println(pageIndex);
		}else{
			pageIndex = 1;
		}
		List<StatCountbook> list =null;	
		int start = pageSize*(pageIndex-1);	
		list =  statCountServiceImpl.list(start,pageSize,map);	
		int total = statCountServiceImpl.count(map);	
		PageNavJson<StatCountbook> context = new PageNavJson<StatCountbook>(list,total,pageSize,pageIndex);
		return context;				
		
	}
			
}
