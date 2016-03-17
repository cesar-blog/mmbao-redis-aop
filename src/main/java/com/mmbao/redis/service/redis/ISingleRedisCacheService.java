package com.mmbao.redis.service.redis;



/**
 * Created by Cesar.X on 2016/3/6.
 * redis缓存操作的接口
 */
public interface ISingleRedisCacheService extends IBaseRedisCacheService{

    /**
     * 删除key
     * @param name key
     * @throws Exception
     */
    public void remove(String name) throws Exception ;


    /**
     * 判断key是否存在
     * @param key key
     * @return 存在-true 不存在-false
     * @throws Exception
     */
    public boolean exists(String key) throws Exception;


    /**
     * 设置过期时间
     * @param key key
     * @param seconds 秒
     * @return 成功-true 失败-false
     * @throws Exception
     */
    public boolean expire(String key, int seconds) throws Exception;
}
