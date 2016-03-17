package com.mmbao.redis.service;

import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Created by Cesar.X on 2016/3/6.
 * redis单机操作
 * StringRedisTemplate基类
 */
public class BaseCache {

	protected StringRedisTemplate redisTemplate;
	
    /** 
     * @return the template 
     */  
    public StringRedisTemplate getRedisTemplate() {
        return redisTemplate;  
    }  
  
    /** 
     * @param redisTemplate the template to set
     */  
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;  
    }  
  

}
