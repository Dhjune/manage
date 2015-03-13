package com.fanfan.manage.control.datacube.base;

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
import com.fanfan.manage.modle.book.Book;
import com.fanfan.manage.modle.user.UserStat;
import com.fanfan.manage.service.base.PlatFormServiceImpl;
import com.fanfan.manage.service.common.PageNav;
import com.fanfan.manage.service.common.PageNavJson;
import com.fanfan.manage.service.datacube.user.UserStatServiceImpl;

@Controller
@RequestMapping(value="datacube/base/user/stat")
public class UserStatController {

	@Autowired
	private UserStatServiceImpl userStatServiceImpl;
	
	@Autowired
	private PlatFormServiceImpl platFormServiceImpl;
	
	@RequestMapping(value="/pageNavJson",produces = "application/json")
	@ResponseBody
	public PageNavJson pageNavJson(@RequestBody Map map)  throws Exception{
		List<UserStat> list = null;
		int pageSize = Constants.ACCOUNT_DEFAULT_PAGE_SIZE;	
		int pageIndex ;
		if(map.get("pageIndex")!=null){
			pageIndex = Integer.parseInt(map.get("pageIndex").toString());
		}else{
			pageIndex = 1;
		}
		Object stat_day = map.get("stat_day");
		Calendar c = Calendar.getInstance(); 
		Date date = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if(stat_day!= null && !stat_day.toString().equals("")){
			try {
				
				date = format.parse(stat_day.toString());
				c.setTime(date);
				c.add(c.DAY_OF_MONTH, -7);
				map.put("start_day", format.format(c.getTime()));
				map.put("end_day", format.format(date));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else{
			
			date = new Date();
			c.setTime(date);
			c.add(c.DAY_OF_MONTH, -7);
			map.put("start_day", format.format(c.getTime()));
			map.put("end_day", format.format(date));
			
		}
				
			
		int start = pageSize*(pageIndex-1);	
		list =  userStatServiceImpl.list(start,pageSize,map);	
		int total = userStatServiceImpl.count(map);
		PageNavJson<UserStat> context = new PageNavJson<UserStat>(list, total, pageSize, pageIndex);
		return context;
	}
	
	@RequestMapping(value={"day/list","day/list/p_{pageIndex}"})
	public String list(Model model,HttpServletRequest res)  throws Exception{
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
		List<UserStat> list = null;
		Date date =new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(date);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Map map = new HashMap();
	//	map.put("platform_id", "0");
		map.put("end_day", format.format(date));
		c.add(c.DAY_OF_MONTH, -7);
		map.put("start_day", format.format(c.getTime()));
		String url = "/manage/datacube/base/user/stat/day/list%s";	
		int start = pageSize*(pageIndex-1);	
		list =  userStatServiceImpl.list(start,pageSize,map);
		int total = userStatServiceImpl.count(map);
		PageNav<UserStat> context = new PageNav<UserStat>(list,total,pageSize,pageIndex,url);			
		model.addAttribute("context", context);			
		model.addAttribute("plats", platFormServiceImpl.list());
		return "account/datacube/base/user/daylist";
		
	}
	
	
	
}
