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
import org.springframework.web.bind.annotation.ResponseBody;

import com.fanfan.manage.common.Constants;
import com.fanfan.manage.modle.book.StatMonthbook;
import com.fanfan.manage.service.base.PlatFormServiceImpl;
import com.fanfan.manage.service.common.PageNav;
import com.fanfan.manage.service.common.PageNavJson;
import com.fanfan.manage.service.datacube.book.StatMonthServiceImpl;

@Controller
@RequestMapping(value="datacube/book/stat/month")
public class StatMonthController {

	@Autowired
	private StatMonthServiceImpl statMonthServiceImpl;
	
	@Autowired
	private PlatFormServiceImpl platFormServiceImpl;
	
	@RequestMapping(value={"list","list/p_{pageIndex}"})
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
		int pageSize = Constants.ACCOUNT_DEFAULT_PAGE_SIZE;		
		String url = "/manage/datacube/book/stat/month/list%s";		
		List<StatMonthbook> list =null;	
		String platStr  = res.getParameter("platId");
		int platId = 0;		
		if(platStr !=null && !platStr.equals("")){
			platId  = Integer.parseInt(platStr);
		}
		String month  =  res.getParameter("stat_month");
		Date date  = new Date();
		String reMonth =""; 
		if(month==null||month.equals("")){
			SimpleDateFormat format =  new SimpleDateFormat("yyyyMM");		      
			month = format.format(date);
			reMonth = new SimpleDateFormat("yyyy-MM-dd").format(date);
		}else{
			reMonth = month;
			month = month.substring(0, 7);		
			month = month.replace("-", "");
		}
		
		int start = pageSize*(pageIndex-1);	
		list = statMonthServiceImpl.list(platId,start, pageSize,month);			
		int total = statMonthServiceImpl.count(platId,month);		
		PageNav<StatMonthbook> context = new PageNav<StatMonthbook>(list,total,pageSize,pageIndex,url);	
		
		model.addAttribute("context", context);	
		model.addAttribute("stat_month", reMonth);
		model.addAttribute("plats", platFormServiceImpl.list());
		model.addAttribute("platId", platId);
		
		return "account/datacube/book/stat/monthlist";
		
	}
	
	@RequestMapping(value="view")
	public String view(HttpServletRequest res,Model model)  throws Exception{
		
		String bookId = res.getParameter("bookId");
		String platId = res.getParameter("platId");
		String stat_month =  res.getParameter("stat_month");		
		String operation  = res.getParameter("operation");	
		Date date = null;
		String start_month = "";
		String end_month = "";
		Calendar c = Calendar.getInstance(); 
		SimpleDateFormat reFormat = new SimpleDateFormat("yyyy-MM-dd");
																			
		if(stat_month ==null || stat_month.equals("")){
			date = new Date();
		}else{
			try {
				date =  reFormat.parse(stat_month);
				
			} catch (ParseException e) {				
				e.printStackTrace();
			}
		}
		c.setTime(date);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		if(operation == null||operation.equals("")){
			
			c.add(c.MONTH, -6);
			end_month = format.format(date);
			start_month = format.format(c.getTime());
			model.addAttribute("prev_month", reFormat.format(c.getTime()));
			c.add(c.MONTH, 6);
			model.addAttribute("next_month", reFormat.format(c.getTime()));
			
			
		}else{
			if(operation.equals("next")){
				
				c.add(c.MONTH, 6);
				end_month = format.format(c.getTime());		
				model.addAttribute("next_month", reFormat.format(c.getTime()));
				start_month = format.format(date);
				c.add(c.MONTH, -6);
				model.addAttribute("prev_month", reFormat.format(c.getTime()));
				
			}else{		
				
				c.add(c.MONTH, -6);
				end_month = format.format(date);
				start_month = format.format(c.getTime());
				model.addAttribute("prev_month", reFormat.format(c.getTime()));	
				c.add(c.MONTH, 6);
				
				model.addAttribute("next_month", reFormat.format(c.getTime()));
				
			}		
		}
		List<StatMonthbook> list = null;
		list = statMonthServiceImpl.oneList(bookId,platId,start_month,end_month);
		model.addAttribute("list", list);
		model.addAttribute("bookId", bookId);
		model.addAttribute("platId", platId);
		
		return "/account/datacube/book/stat/monthview";		
	}
	
	@RequestMapping(value="pageNavJson",produces = "application/json")
	@ResponseBody
	public PageNavJson pageNavJson(@RequestBody Map map) throws Exception{
		int pageSize = Constants.ACCOUNT_DEFAULT_PAGE_SIZE;	
		int pageIndex ;
		
		if(map.get("pageIndex")!=null){
			
			pageIndex = Integer.parseInt(map.get("pageIndex").toString());
		
		}else{
			pageIndex = 1;
		}
		String month = "";
		if(map.get("stat_month")!=null){
			month = map.get("stat_month").toString();
		}
		
		Date date  = new Date();
		String reMonth =""; 
		if(month==null||month.equals("")){
			
			SimpleDateFormat format =  new SimpleDateFormat("yyyyMM");		      
			month = format.format(date);
			reMonth = new SimpleDateFormat("yyyy-MM-dd").format(date);
			
		}else{
			
			reMonth = month;
			month = month.substring(0, 7);		
			month = month.replace("-", "");
			
		}
		
		map.put("stat_month", month);				
		List<StatMonthbook> list =null;	
		int start = pageSize*(pageIndex-1);	
		list =  statMonthServiceImpl.list(start,pageSize,map);	
		int total = statMonthServiceImpl.count(map);	
		PageNavJson<StatMonthbook> context = new PageNavJson<StatMonthbook>(list,total,pageSize,pageIndex);
		return context;				
	
	}
}
