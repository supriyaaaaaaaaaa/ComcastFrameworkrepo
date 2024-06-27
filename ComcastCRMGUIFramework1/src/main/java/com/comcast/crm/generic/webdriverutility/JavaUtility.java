package com.comcast.crm.generic.webdriverutility;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	
	public int getRandomNumber() {
		Random ranDom= new Random();
		int randomNumber = ranDom.nextInt(5000);
		return randomNumber;
		}
	
	public String getSystemDateYYYYDDMM() {
		
		Date dateobj = new Date();
		SimpleDateFormat sdf =  new SimpleDateFormat("YYYY-MM-dd");
		String date = sdf.format(dateobj);
		
		return date;
		
	}
	
	public String getRequiredDateYYDDMM(int days) {
		Date dateobj = new Date();
		SimpleDateFormat sim =  new SimpleDateFormat("YYYY-MM-dd");
		String date = sim.format(dateobj);
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH,days);
		String dateRequries = sim.format(cal.getTime());
		return dateRequries;
		
	}

}
