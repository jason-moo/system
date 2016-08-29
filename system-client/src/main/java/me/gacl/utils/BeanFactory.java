package me.gacl.utils;

import org.springframework.web.context.WebApplicationContext;

public class BeanFactory {
    private static	WebApplicationContext webApplicationContext ;

	public static void setWebApplicationContext(WebApplicationContext webApplicationContext) {
		BeanFactory.webApplicationContext = webApplicationContext;
	}
    
	public static WebApplicationContext getWebApplicationContext() {
		return BeanFactory.webApplicationContext;
	}

	public static  <T> T  getBean(Class<T> t){
		return getWebApplicationContext().getBean(t);
	}
}
