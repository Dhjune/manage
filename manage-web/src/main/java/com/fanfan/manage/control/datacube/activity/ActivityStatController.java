package com.fanfan.manage.control.datacube.activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.fanfan.manage.common.Constants;
import com.fanfan.manage.modle.activity.ActivityStat;
import com.fanfan.manage.modle.user.UserStat;
import com.fanfan.manage.service.base.PlatFormServiceImpl;
import com.fanfan.manage.service.common.PageNav;
import com.fanfan.manage.service.common.PageNavJson;
import com.fanfan.manage.service.datacube.activity.ActivityStatServiceImpl;

@Controller
@RequestMapping(value="datacube/activity/stat")
public class ActivityStatController {
	
	@Autowired
	private ActivityStatServiceImpl activityStatServiceImpl;
	
	@Autowired
	private PlatFormServiceImpl platFormServiceImpl;
	
	@RequestMapping(value="/pageNavJson",produces = "application/json")
	@ResponseBody
	public PageNavJson pageNavJson(@RequestBody Map map) throws Exception{
		List<ActivityStat> list = null;
		int pageSize = Constants.ACCOUNT_DEFAULT_PAGE_SIZE;	
		int pageIndex ;
		if(map.get("pageIndex")!=null){
			pageIndex = Integer.parseInt(map.get("pageIndex").toString());
		}else{
			pageIndex = 1;
		}
		
		Object stat_month = map.get("stat_month");
		Object stat_day  = map.get("stat_day");
		
		Calendar c = Calendar.getInstance(); 
		Date date = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		if(stat_day!=null){
			
			try {
				
				date = format.parse(stat_day.toString());
				
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
			map.put("stat_day", format.format(date));
			
		}else{
			
			if(stat_month!= null && !stat_month.toString().equals("")){
				try {
					
					date = format.parse(stat_month.toString());
					map.put("stat_month", format.format(date));
					
				} catch (ParseException e) {
					e.printStackTrace();				
				}
				
			}else {
				
				date = new Date();
				
				map.put("stat_month", format.format(date));
				
			}
			
		}
		
		
		
		
		int start = pageSize*(pageIndex-1);	
		list =  activityStatServiceImpl.list(start,pageSize,map);	
		int total = activityStatServiceImpl.count(map);
		PageNavJson<ActivityStat> context = new PageNavJson<ActivityStat>(list, total, pageSize, pageIndex);
		return context;
		
	}
	
	@RequestMapping(value={"month/list","month/list/p_{pageIndex}"})
	public String list(Model model,HttpServletRequest res) throws Exception{
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
		List<ActivityStat> list = null;
		Date date =new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(date);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Map map = new HashMap();
	//	map.put("platform_id", "0");
		map.put("stat_month", format.format(date));
		
		String url = "/manage/datacube/base/user/stat/day/list%s";	
		int start = pageSize*(pageIndex-1);	
		list =  activityStatServiceImpl.list(start,pageSize,map);
		int total = activityStatServiceImpl.count(map);
		PageNav<ActivityStat> context = new PageNav<ActivityStat>(list,total,pageSize,pageIndex,url);			
		model.addAttribute("context", context);			
		model.addAttribute("plats", platFormServiceImpl.list());
		return "account/datacube/activity/monthlist";		
	}
	
	@RequestMapping(value={"month/view","month/view/p_{pageIndex}"})
	public String dayView(HttpServletRequest res,Model model) throws Exception{
		
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
		String platId = res.getParameter("platId");
		String acId = res.getParameter("acId");
		String stat_date = res.getParameter("stat_day");	
		Date date =null;
		Calendar c = Calendar.getInstance(); 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		
		if(stat_date ==null || stat_date.equals("")){
			date = new Date();
		}else{
			try {
				date =  format.parse(stat_date);
				
			} catch (ParseException e) {				
				e.printStackTrace();
			}
		}
		
		Map map = new HashMap();
		map.put("stat_day", format.format(date));
		map.put("activity_id", acId);
		map.put("platform_id", platId);
		
		c.setTime(date);
		List<ActivityStat> list = null;
		String url = "/manage/datacube/activity/stat/month/view%s?acId="+acId+"&platId="+platId+"&stat_day="+stat_date;	
		int start = pageSize*(pageIndex-1);	
		list =  activityStatServiceImpl.list(start,pageSize,map);
		int total = activityStatServiceImpl.count(map);
		PageNav<ActivityStat> context = new PageNav<ActivityStat>(list,total,pageSize,pageIndex,url);			
		model.addAttribute("context", context);			
		model.addAttribute("plats", platFormServiceImpl.list());
		model.addAttribute("stat_day", stat_date);
		model.addAttribute("activity_id",acId);	
		model.addAttribute("platform_id", platId);
		return "account/datacube/activity/monthview";
		
	}

}
