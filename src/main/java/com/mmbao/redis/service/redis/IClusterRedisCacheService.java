package com.mmbao.redis.service.redis;



/**
 * Created by Cesar.X on 2016/3/6.
 * redis缓存操作的接口
 */
public interface IClusterRedisCacheService extends IBaseRedisCacheService{

    /**
     * 判断key是否存在
     * @param key key
     * @return 存在-true 不存在-false
     * @throws Exception
     */
    public boolean exists(String key) throws Exception;


    /**
     * 删除key
     * @param name key
     * @throws Exception
     */
    public void remove(String name) throws Exception;


    /**
     * 设置失效时间
     * @param key key
     * @param seconds 秒
     * @throws Exception
     */
    public void expire(String key, int seconds) throws Exception;
}
