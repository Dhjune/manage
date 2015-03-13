package com.fanfan.manage.control.datacube.base;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping(value="datacube/base/stat/")
public  class BaseStatController {

	@RequestMapping(value="home")
	public String home(){
		
		return "account/datacube/base/index";
		
	}
	
	@RequestMapping(value="jsonmap",produces = "application/json")
	public String test(@RequestBody Map map){
		ObjectMapper objectMapper = new ObjectMapper();
		//JsonObject json = new JsonParser().parse(buffer.toString()).getAsJsonObject();
		//Map map=null;
//		try {
//			map = objectMapper.readValue(mapStr, Map.class);
//		} catch (JsonParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println("the request is comming"+map);
		Iterator<Object> it =  map.keySet().iterator();
		while(it.hasNext()) {  
	        String key = (String) it.next();  
	        System.out.println("the key is :"+key);
		}
		System.out.println(map.size());
		return "redirect: datacube/base/stat/home";
	}
	
	
}
