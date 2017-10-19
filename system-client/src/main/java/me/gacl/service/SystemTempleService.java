package me.gacl.service;

import org.springframework.data.redis.connection.RedisConnectionFactory;


public interface SystemTempleService {
	
	 Object set(Object key,Object value);

	 Object set(Object key,Object value,long time);

	 Object get(Object key);

	 boolean delete(Object key);

	 RedisConnectionFactory getFactory();
}
