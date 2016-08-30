package com.crm.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.crm.dao.mapper.CUserMapper;

import me.gacl.domain.CUser;
import me.gacl.domain.CUserExample;
import me.gacl.service.UserService;
import me.gacl.utils.CookieUtils;
import me.gacl.utils.MD5Utils;
import me.gacl.utils.NumberUtils;


@Service
public class UserServiceImpl implements UserService{
	@Autowired
	CUserMapper cUserMapper;
	
	@Override
	public void insertUser(String userName,String phone,String passwd) {
		String salt = NumberUtils.getRandomNum(6);
		CUser cUser = new CUser();
		cUser.setPhone(phone);
		cUser.setDeleted(0);
		cUser.setPassword(MD5Utils.digest(salt,MD5Utils.digest(salt, passwd)));
		cUser.setUserName(userName);
		cUser.setScert(salt);
		cUserMapper.insert(cUser);
	}

	@Override
	public boolean login(String phone, String passwd,HttpServletResponse response) {
		CUserExample cUserExample = new CUserExample();
		cUserExample.createCriteria().andPhoneEqualTo(phone);
		List<CUser> cUsers = cUserMapper.selectByExample(cUserExample);
		if (!CollectionUtils.isEmpty(cUsers) && cUsers.size() == 1) {
			CUser cUser = cUsers.get(0);
			String digest = MD5Utils.digest(cUser.getScert(),MD5Utils.digest(cUser.getScert(),passwd));
			if (digest.equals(cUser.getPassword())) {
				CookieUtils.setCookie(response, "test_cookie_exist", cUser.getId().toString());
				return true;
			}
		}
		return false;
	}
 
	@Override
	public boolean getCountByPhone(String phone) {
		boolean isCountMoreThanZero = false;
		CUserExample userExample =  new CUserExample();
		userExample.createCriteria().andPhoneEqualTo(phone.trim());
		isCountMoreThanZero = cUserMapper.countByExample(userExample) > 0;
		return isCountMoreThanZero;
	}
}
