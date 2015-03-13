package com.fanfan.manage.control.datacube;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value="datacube/")
public class DataCubeTest {

	
	@RequestMapping(value="home")
	public String home(){
		return "account/datacube/index";
	}
	
}
