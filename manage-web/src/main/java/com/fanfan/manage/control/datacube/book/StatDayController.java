package com.fanfan.manage.control.datacube.book;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import com.fanfan.manage.modle.book.StatCountbook;
import com.fanfan.manage.modle.book.StatDaybook;
import com.fanfan.manage.modle.book.StatMonthbook;
import com.fanfan.manage.service.base.PlatFormServiceImpl;
import com.fanfan.manage.service.common.PageNav;
import com.fanfan.manage.service.common.PageNavJson;
import com.fanfan.manage.service.datacube.book.StatDayServiceImpl;


@Controller
@RequestMapping(value="datacube/book/stat/day")
public class StatDayController {
	
	@Autowired
	private StatDayServiceImpl statDayServiceImpl;
	
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
		String url = "/manage/datacube/book/stat/day/list%s";		
		List<StatDaybook> list =null;	
		String platStr  = res.getParameter("platId");
		int platId = 0;
		
		if(platStr !=null && !platStr.equals("")){
			platId  = Integer.parseInt(platStr);
		}
		String day  =  res.getParameter("stat_day");
		String reDay = "";
		if(day==null||day.equals("")){
			Date date = new Date();
			SimpleDateFormat format =  new SimpleDateFormat("yyyyMMdd");		      
			day = format.format(date);
			reDay = new SimpleDateFormat("yyyy-MM-dd").format(date);
		}else{
			reDay = day;
			day = day.replace("-", "");
		}
		
		int start = pageSize*(pageIndex-1);	
	
		list = statDayServiceImpl.list(platId,start, pageSize,day);			
		int total = statDayServiceImpl.count(platId,day);		
		PageNav<StatDaybook> context = new PageNav<StatDaybook>(list,total,pageSize,pageIndex,url);
		model.addAttribute("context", context);	
		model.addAttribute("plats", platFormServiceImpl.list());
		model.addAttribute("stat_day", reDay);
		model.addAttribute("platId", platId);
		return "account/datacube/book/stat/daylist";
	}
	
	@RequestMapping(value="view")
	public String view(HttpServletRequest res,Model model)  throws Exception{
		
		String bookId = res.getParameter("bookId");
		String platId = res.getParameter("platId");
		String stat_day =  res.getParameter("stat_day");
		
		String operation  = res.getParameter("operation");

		if(operation == null||operation.equals("")){
			
			operation = "prev";
			
		}
		
		Date date = null;
		String start_day = "";
		String end_day = "";
		Calendar c = Calendar.getInstance(); 
		SimpleDateFormat reFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");																	
		if(stat_day == null || stat_day.equals("") ){
			date = new Date();
		}else{
			try {
				date =  reFormat.parse(stat_day);
				
			} catch (ParseException e) {				
				e.printStackTrace();
			}
		}
		c.setTime(date);
		if(operation ==null || operation.equals("")){
			c.add(c.DAY_OF_MONTH, -7);
			end_day = format.format(date);
			start_day = format.format(c.getTime());
			model.addAttribute("prev_day", reFormat.format(c.getTime()));
			c.add(c.DAY_OF_MONTH, 7);
			model.addAttribute("next_day", reFormat.format(c.getTime()));
		}else{
		
			if(operation.equals("next")){				
				c.add(c.DAY_OF_MONTH, 7);
				//System.out.println("the next date is :"+reFormat.format(c.getTime()));
				end_day = format.format(c.getTime());				
				start_day = format.format(date);
				model.addAttribute("next_day", reFormat.format(c.getTime()));			
				c.add(c.DAY_OF_MONTH, -7);
				model.addAttribute("prev_day", reFormat.format(c.getTime()));
				
			}else{			
				c.add(c.DAY_OF_MONTH, -7);
				end_day = format.format(date);
				//System.out.println("the prev date is :"+reFormat.format(c.getTime()));
				
				start_day = format.format(c.getTime());
				model.addAttribute("prev_day", reFormat.format(c.getTime()));
				//System.out.println("the prev date is :"+reFormat.format(c.getTime()));
				c.add(c.DAY_OF_MONTH, 7);
				model.addAttribute("next_day", reFormat.format(c.getTime()));
				
			}	
		}
		List<StatDaybook> list = null;
		list = statDayServiceImpl.oneList(bookId,platId,start_day,end_day);
		model.addAttribute("list", list);
		model.addAttribute("bookId", bookId);
		model.addAttribute("platId", platId);
		
		return "/account/datacube/book/stat/dayview";
	}
	
	@RequestMapping(value="pageNavJson",produces = "application/json")
	@ResponseBody
	public PageNavJson pageNavJson(@RequestBody Map map) throws Exception{
		int pageSize = Constants.ACCOUNT_DEFAULT_PAGE_SIZE;	
		int pageIndex ;
		
		if(map.get("pageIndex")!=null){
			pageIndex = Integer.parseInt(map.get("pageIndex").toString());
			System.out.println(pageIndex);
		}else{
			pageIndex = 1;
		}
		
		String stat_day  =  "";
		if(map.get("stat_day") !=null){
			stat_day = map.get("stat_day").toString();
		}
				 
		if(stat_day==null||stat_day.equals("")){
			Date date  = new Date();
			SimpleDateFormat format =  new SimpleDateFormat("yyyyMMdd");		      
			stat_day = format.format(date);
			
		}else{
						
			stat_day = stat_day.replace("-", "");
			
		}
		map.put("stat_day", stat_day);
		List<StatDaybook> list =null;	
		int start = pageSize*(pageIndex-1);	
		list =  statDayServiceImpl.list(start,pageSize,map);	
		int total = statDayServiceImpl.count(map);	
		PageNavJson<StatDaybook> context = new PageNavJson<StatDaybook>(list,total,pageSize,pageIndex);
		return context;				
		
	}

}
