package com.yu.demo.service;

import java.util.concurrent.TimeUnit;

public class Utility {
	
	public static void sleep(long ms) {
		try {
	    	  TimeUnit.MILLISECONDS.sleep(ms);
	      } catch (InterruptedException e) {
	    	  // TODO Auto-generated catch block
	    	  e.printStackTrace();
	      }
	}
	
	public static void thinkTime(long ms) {
		sleep(ms);
	}

}
