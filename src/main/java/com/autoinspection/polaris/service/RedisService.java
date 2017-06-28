package com.autoinspection.polaris.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface RedisService {
	public boolean set(String key, String value);  
    
    public String get(String key);  
      
    public boolean expire(String key,long expire);  
      
    public <T> boolean setList(String key ,List<T> list) throws JsonProcessingException;  
      
    public <T> List<T> getList(String key,Class<T> clz) throws Exception ;  
      
    public long lpush(String key,Object obj) throws JsonProcessingException ;  
      
    public long rpush(String key,Object obj) throws JsonProcessingException ;  
      
    public String lpop(String key); 
}

