package me.gacl.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import me.gacl.url.Url;

public class LoginManagerUtils {
	
	private static Set<String> cache = Collections.synchronizedSet(new HashSet<String>());
	static{
		Class clazz =	Url.MustLogin.class;
		Field [] fields = clazz.getDeclaredFields();
		 for(Field field:fields){
			 boolean isStatic = Modifier.isStatic(field.getModifiers());
			 if(isStatic){
			    try {
					String servletPath=field.get(null).toString();
					cache.add(".*"+servletPath.trim()+".*");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
			 }
		 }
	}
	
	public static boolean isMustLogin(String url){
		for(String item:cache){
			Pattern pattern=Pattern.compile(item);
			if(pattern.matcher(url.trim()).matches()){
				return true;
			}
		}
		return false;
	}
}
