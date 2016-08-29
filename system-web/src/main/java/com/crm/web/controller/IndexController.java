package com.crm.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.gacl.domain.CUser;
import me.gacl.service.SystemTempleService;
import me.gacl.service.UserService;
import me.gacl.url.Url;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
	@Autowired
	UserService userService;
	
	@Autowired
	SystemTempleService systemTempleService;
	@RequestMapping(value="/")
	public String Index(){
		CUser cUser = new CUser();
		cUser.setId(1);
		cUser.setPhone("dsdsadsad");
		systemTempleService.set("test",cUser);
		CUser cUser2 = (CUser) systemTempleService.get("test");
		System.out.println(cUser2.getId());
		RedisAtomicLong redisAtomicLong = new RedisAtomicLong("idgenerator",systemTempleService.getFactory());
		System.out.println(redisAtomicLong.addAndGet(1));
		System.out.println(redisAtomicLong.addAndGet(1));
		return "index";
	}
	
	@RequestMapping(value = Url.USER_LOGIN_PAGE)
	public String login(HttpServletRequest request){
		return "login_page";
	}
	
	@RequestMapping(value = Url.USER_DOLOGIN)
	@ResponseBody
	public void doLogin(String phone,String passwd,HttpServletResponse response){
		boolean isSuccuess =  userService.login(phone, passwd,response);
		CUser cUser = new CUser();
		cUser.setId(1);
		cUser.setScert("asdasd");
		throw new me.gacl.exception.ServiceException("资源已删除!");
	}
	
	@RequestMapping(value = Url.USER_REGISTER)
	@ResponseBody
	public Object register(String phone,String passwd,String userName,HttpServletResponse response){
		userService.insertUser(userName,phone,passwd);
		boolean isSuccuess =  userService.login(phone, passwd,response);
		return 1;
	}
	
	@RequestMapping(value = Url.MustLogin.SHOP_NEWS)
	public String news(HttpServletRequest request){
		return "new";
	}
	
}
