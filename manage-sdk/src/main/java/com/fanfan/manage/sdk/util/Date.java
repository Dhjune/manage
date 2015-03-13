package com.fanfan.manage.sdk.util;

public class Date extends java.util.Date{
	
	
	
	@Override
	public String toString() {
		int year = super.getYear() + 1900;
        int month = super.getMonth() + 1;
        int day = super.getDate();
        int hours = super.getHours();
        
        char buf[] = "2000-00-00 00".toCharArray();
        buf[0] = Character.forDigit(year/1000,10);
        buf[1] = Character.forDigit((year/100)%10,10);
        buf[2] = Character.forDigit((year/10)%10,10);
        buf[3] = Character.forDigit(year%10,10);
        buf[5] = Character.forDigit(month/10,10);
        buf[6] = Character.forDigit(month%10,10);
        buf[8] = Character.forDigit(day/10,10);
        buf[9] = Character.forDigit(day%10,10);
        buf[11] = Character.forDigit(hours/10, 10);
        buf[12] = Character.forDigit(hours%10, 10);

        return new String(buf);
	}
}
