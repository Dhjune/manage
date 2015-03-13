package com.fanfan.manage.control.common;

import java.util.Random;

public class Helps {
	public static String getRandomString(int length) { 
	    String base = "abcdefghijkl0123456789mnopqrstuvwxyz0123456789ABCDEFGHIGK0123456789LMNOPQRSTUVWXYZ";   
	    Random random = new Random();   
	    StringBuffer sf = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sf.append(base.charAt(number));   
	    }   
	    return sf.toString();   
	 }	
}
