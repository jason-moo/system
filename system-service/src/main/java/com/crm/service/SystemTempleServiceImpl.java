package com.crm.service;

import me.gacl.service.SystemTempleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.UUID;

@Service
public class SystemTempleServiceImpl implements SystemTempleService {

	@Autowired
    RedisTemplate redisTemplate;

	@Override
	public Object set(Object key, Object value) {
		try {
			redisTemplate.opsForValue().set(key, value);
			DispatcherServlet
			return value;
		} catch (Exception e) {
			return null;
		}
	}


	@Override
	public Object set(Object key, Object value, long time) {
		try {
			redisTemplate.opsForValue().set(key, value,time);
			return value;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Object get(Object key) {
		try {
			return redisTemplate.opsForValue().get(key);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean delete(Object key) {
		try {
			redisTemplate.delete(key);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public RedisConnectionFactory getFactory() {
		try {
			return redisTemplate.getConnectionFactory();
		} catch (Exception e) {
			return null;
		}
	}
}
