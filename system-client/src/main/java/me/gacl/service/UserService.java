package me.gacl.service;

import javax.servlet.http.HttpServletResponse;

public interface UserService {
	
	 void insertUser(String userName,String phone,String passwd);
	
	 void insertRegister(String userName,String phone,String passwd);
	 
	 boolean login(String phone,String passwd,HttpServletResponse response);
}
