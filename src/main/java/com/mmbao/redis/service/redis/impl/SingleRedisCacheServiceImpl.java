package com.mmbao.redis.service.redis.impl;


import com.mmbao.redis.service.BaseCache;
import com.mmbao.redis.service.redis.ISingleRedisCacheService;
import com.mmbao.redis.util.core.JsonSerializerUtil;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by Cesar.X on 2016/3/6.
 * redis缓存操作的接口实现类
 */
@Service
public class SingleRedisCacheServiceImpl extends BaseCache implements ISingleRedisCacheService {


    //-------------------common--------------------------
    @Override
    public void remove(String name) throws Exception {
        try {
            redisTemplate.delete(name);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean exists(String key) throws Exception {
        return false;
    }

    @Override
    public boolean expire(String key, int seconds) throws Exception {
        try {
            return redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw e;
        }
    }

    //-------------------String--------------------------
    @Override
    public void set(String key, Object value, int expire) throws Exception {
        try {
            if (expire > 0) {
                redisTemplate.opsForValue().set(key, JsonSerializerUtil.getJsonArrStrFromList(value), expire, TimeUnit.SECONDS);
            } else {
                redisTemplate.opsForValue().set(key, JsonSerializerUtil.getJsonArrStrFromList(value));
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> cls) throws Exception {
        try {
            String jsonValue = redisTemplate.opsForValue().get(key);
            return (T) JsonSerializerUtil.getListFromJsonArrStr(jsonValue, cls);
        } catch (Exception e) {
            throw e;
        }
    }

    //-------------------set--------------------------
    @Override
    public Long scard(String key) throws Exception {
        Long count = 0L;
        try {
            count = redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            throw e;
        }
        return count;
    }

    @Override
    public Long sadd(String key, Object... members) throws Exception {
        Long rows = 0L;
        try {
            rows = redisTemplate.opsForSet().add(JsonSerializerUtil.getJsonArrStrFromList(members));
        } catch (Exception e) {
            throw e;
        }
        return rows;
    }

    @Override
    public Long srem(String key, Object... members) throws Exception {
        Long rows = 0L;
        try {
            rows = redisTemplate.opsForSet().remove(key, members);
        } catch (Exception e) {
            throw e;
        }
        return rows;
    }


    @Override
    @SuppressWarnings("unchecked")
    public <T> Set<T> smembers(String key, Class<T> t) throws Exception {
        try {
            Set<String> sItem = redisTemplate.opsForSet().members(key);
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

//
//    //-------------------map--------------------------
//    @Override
//    public void setMap(String key, String mapKey, Object value)
//            throws Exception {
//        ShardedJedis jedis = null;
//        try {
//            jedis = shardedJedisPool.getResource();
//            String jsonValue = gson.toJson(value);
//            jedis.hset(key, mapKey, jsonValue);
//        } catch (Exception e) {
//            throw e;
//        }
//    }
//
//    @Override
//    public <T> T getMapValue(String key, String mapKey, Class<T> t)
//            throws Exception {
//        Gson gson = new Gson();
//        List<String> list = null;
//        try {
//            jedis = shardedJedisPool.getResource();
//            list = jedis.hmget(key, mapKey);
//        } catch (Exception e) {
//            throw e;
//        }
//
//        if (list == null || list.size() == 0) {
//            return null;
//        }
//
//        String value = list.get(0);
//        if (value != null) {
//            return gson.fromJson(value, t);
//        }
//        return null;
//    }
//
//    @Override
//    public <T> List<T> getMapValues(String key, Class<T> t) throws Exception {
//        Gson gson = new Gson();
//        List<String> list = new ArrayList<String>();
//        List<T> rList = new ArrayList<T>();
//
//        try {
//            jedis = shardedJedisPool.getResource();
//            list = jedis.hvals(key);
//        } catch (Exception e) {
//            throw e;
//        }
//
//        if (list != null) {
//            for (int i = 0; i < list.size(); i++) {
//                rList.add(gson.fromJson(list.get(i), t));
//            }
//        }
//
//        return rList;
//    }
//
//    @Override
//    public Long removeMap(String key, String valueKey) throws Exception {
//        Long result = 0L;
//        try {
//            jedis = shardedJedisPool.getResource();
//            result = jedis.hdel(key, valueKey);
//        } catch (Exception e) {
//            throw e;
//        }
//        return result;
//    }
//
//    @Override
//    public Map<String, String> getMaps(String key) throws Exception {
//        Map<String, String> rList = new HashMap<String, String>();
//        try {
//            jedis = shardedJedisPool.getResource();
//            rList = jedis.hgetAll(key);
//        } catch (Exception e) {
//            throw new Exception(e);
//        }
//        return rList;
//    }
//

}