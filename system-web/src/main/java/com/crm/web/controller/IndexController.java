package com.crm.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.gacl.exception.ServiceException;
import me.gacl.service.SystemTempleService;
import me.gacl.service.UserService;
import me.gacl.url.Url;

@Controller
public class IndexController {
	@Autowired
	UserService userService;
	@Autowired
	SystemTempleService systemTempleService;
	
	@RequestMapping(value="/")
	public String Index(){
		
		RedisAtomicLong redisAtomicLong = new RedisAtomicLong("idgenerator",systemTempleService.getFactory());
		System.out.println(redisAtomicLong.addAndGet(1));
		return "index";
	}
	
	@RequestMapping(value = Url.USER_LOGIN_PAGE)
	public String login(HttpServletRequest request){
		return "login_page";
	}
	
	@RequestMapping(value = Url.USER_DOLOGIN)
	@ResponseBody
	public Object doLogin(String phone,String passwd,HttpServletResponse response){
		return userService.login(phone, passwd,response);
	}
	
	@RequestMapping(value = Url.USER_REGISTER)
	public String register(){
		return "register";
	}
	
	@RequestMapping(value = Url.USER_DOREGISTER)
	@ResponseBody
	public Object doRegister(String phone,String passwd,String userName,HttpServletResponse response){
		try {
			userService.insertUser(userName,phone,passwd);
		} catch (Exception e) {
			throw new ServiceException("手机号已存在！");
		}
		return userService.login(phone, passwd,response);
	}
}
