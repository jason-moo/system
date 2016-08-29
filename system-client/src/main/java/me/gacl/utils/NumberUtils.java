package me.gacl.utils;

import java.util.Random;

public class NumberUtils {
	
	private static final  String base = "abcdefghijklmnopqrstuvwxyz0123456789"; 
	
	public static String getRandomNum(int num){
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < num; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	}
}
