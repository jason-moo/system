package me.gacl.utils;

import me.gacl.common.MD5;

public class MD5Utils {
	
	public static String digest(String salt,String data){
		String digestStr = null;
		try {
			MD5 md5 = new MD5(salt);
			digestStr = md5.digestHex(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return digestStr;
	}
	
	public static String digest(String data){
		String digestStr = null;
		try {
			MD5 md5 = new MD5();
			digestStr = md5.digestHex(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return digestStr;
	}
}
