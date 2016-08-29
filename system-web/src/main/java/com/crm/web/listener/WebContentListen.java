package com.crm.web.listener;

import javax.servlet.ServletContextEvent;

import me.gacl.utils.BeanFactory;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class WebContentListen extends ContextLoaderListener{
	
	 @Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		BeanFactory.setWebApplicationContext(WebApplicationContextUtils.getWebApplicationContext(event.getServletContext()));
	}
}
