package me.gacl.service;

import org.springframework.data.redis.connection.RedisConnectionFactory;


public interface SystemTempleService {
	
	public Object set(Object key,Object value);
	
	public Object set(Object key,Object value,long time);
	
	public Object get(Object key);
	
	public boolean delete(Object key);
	
	public RedisConnectionFactory getFactory();
}
