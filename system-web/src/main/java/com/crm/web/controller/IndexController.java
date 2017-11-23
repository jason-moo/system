package com.crm.web.controller;

import com.crm.dao.mapper.CodeDao;
import me.gacl.exception.ServiceException;
import me.gacl.plugin.Page;
import me.gacl.service.SystemTempleService;
import me.gacl.service.UserService;
import me.gacl.url.Url;
import me.gacl.utils.FreeMarkers;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class IndexController {
	private static final Logger logger = LogManager.getLogger("IndexController");
	@Autowired
	UserService userService;
	@Autowired
	SystemTempleService systemTempleService;
	@Autowired
	CodeDao codeDao;
	
	@RequestMapping(value="/")
	public String Index(){
		logger.info("dasfdsfasddfasdsasagdasdfasds");

		return "index";
	}
	
	@RequestMapping(value = Url.USER_LOGIN_PAGE)
	public String login(HttpServletRequest request){
		logger.info("dasfdsfasddfasdsasagdasdfasds");
		codeDao.selectAllCode(1,new Page<>());
//		userService.insertUser("fdadsad","fdfdsfdsfds","gfdfdsfdsfsd");
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
