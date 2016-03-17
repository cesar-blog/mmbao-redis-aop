package com.mmbao.redis.service.redis.impl;

import com.mmbao.redis.service.redis.IClusterRedisCacheService;
import com.mmbao.redis.util.core.JsonSerializerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Cesar.X on 2016/3/15.
 * 操作jedisCluster的具体实现
 */
@Service
public class ClusterRedisCacheServiceImpl implements IClusterRedisCacheService {


    @Autowired
    private JedisCluster jedisCluster;


    @Override
    public boolean exists(String key) throws Exception {
        return jedisCluster.exists(key);
    }

    @Override
    public void remove(String name) throws Exception {
        try {
            jedisCluster.del(name);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void expire(String key, int seconds) throws Exception {
        try {
            jedisCluster.expire(key, seconds);
        } catch (Exception e) {
            throw e;
        }
    }

    //----------------String 实现方法-------------------
    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> cls) throws Exception {
        try {
            String jsonValue = jedisCluster.get(key);
            return (T) JsonSerializerUtil.getListFromJsonArrStr(jsonValue, cls);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void set(String key, Object value, int expire) throws Exception {
        try {
            jedisCluster.set(key, JsonSerializerUtil.getJsonArrStrFromList(value));
            if (expire > 0)
                expire(key, expire);
        } catch (Exception e) {
            throw e;
        }
    }


    //-------------------set--------------------------
    @Override
    public Long scard(String key) throws Exception {
        Long count = 0L;
        try {
            jedisCluster.scard(key);
        } catch (Exception e) {
            throw e;
        }
        return count;
    }

    @Override
    public Long sadd(String key, Object... members) throws Exception {
        Long rows = 0L;
        try {
            jedisCluster.sadd(key, JsonSerializerUtil.getJsonArrStrFromList(members));
        } catch (Exception e) {
            throw e;
        }
        return rows;
    }

    @Override
    public Long srem(String key, Object... members) throws Exception {
        Long rows = 0L;
        try {
            rows = jedisCluster.srem(key, JsonSerializerUtil.getJsonArrStrFromList(members));
        } catch (Exception e) {
            throw e;
        }
        return rows;
    }


    @Override
    @SuppressWarnings("unchecked")
    public <T> Set<T> smembers(String key, Class<T> t) throws Exception {
        try {
            Set<String> sItem = jedisCluster.smembers(key);
            Set<T> result = new HashSet<T>();
            Iterator<String> rt = sItem.iterator();
            while (rt.hasNext()) {
                result.add((T) JsonSerializerUtil.getObjFromJsonStr(rt.next(), t));
            }
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Long sremove(String key) throws Exception {
        Long result = 0L;
        remove(key);
        return result;
    }
}
