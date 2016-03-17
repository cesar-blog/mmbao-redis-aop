//package com.mmbao.redis.service.redis.impl;
//
//import com.mmbao.redis.dao.redis.IShardedJedisDataSource;
//import com.mmbao.redis.service.redis.IShardedRedisCacheService;
//import com.mmbao.redis.util.core.JsonSerializerUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import redis.clients.jedis.ShardedJedis;
//
///**
// * Created by Cesar.X on 2016/3/14.
// * 操作redis集群实现.
// */
//@Service
//public class ShardedRedisCacheServiceImpl implements IShardedRedisCacheService {
//
//    private static final Logger log = LoggerFactory.getLogger(ShardedRedisCacheServiceImpl.class);
//
//    @Autowired
//    private IShardedJedisDataSource iRedisDataSource;
//
//    //----------------String-------------------
//    @Override
//    public void set(String key, Object value, int expire) throws Exception {
//        ShardedJedis shardedJedis = iRedisDataSource.getRedisClient();
//        if (shardedJedis != null) {
//            try {
//                shardedJedis.set(key, JsonSerializerUtil.getJsonArrStrFromList(value));
//                //缓存失效时间
//                shardedJedis.expire(key,expire);
//            } catch (Exception e) {
//                e.printStackTrace();
//                log.error(e.getMessage(), e);
//            } finally {
//                iRedisDataSource.returnResource(shardedJedis);
//            }
//        }
//    }
//
//    @Override
//    public <T> T get(String key, Class<T> cls) throws Exception {
//        ShardedJedis shardedJedis = iRedisDataSource.getRedisClient();
//        if (shardedJedis == null) {
//            return null;
//        }
////        try {
////            result = shardedJedis;
////        } catch (Exception e) {
////            log.error(e.getMessage(), e);
////            broken = true;
////        } finally {
////            iRedisDataSource.returnResource(shardedJedis, broken);
////        }
//        return null;
//    }
//
//
//    //-------------------set--------------------------
//    @Override
//    public Long scard(String key) throws Exception {
//        Long count = 0l;
//        try {
//            count = redisTemplate.opsForSet().size(key);
//        } catch (Exception e) {
//            throw e;
//        }
//        return count;
//    }
//
//    @Override
//    public Long sadd(String key, Object... members) throws Exception {
//        Long rows = 0l;
//        try {
//            String[] item = new String[members.length];
//            int i = 0;
//            for (Object obj : members) {
//                item[i] = gson.toJson(obj);
//                i++;
//            }
//            rows = redisTemplate.opsForSet().add(JsonSerializerUtil.getJsonArrStrFromList(members));
//            rows = jedis.sadd(key, item);
//        } catch (Exception e) {
//            throw e;
//        }
//        return rows;
//    }
//
//    @Override
//    public Long srem(String key, Object... members) throws Exception {
//        Long rows = 0l;
//        try {
//            jedis = shardedJedisPool.getResource();
//            String[] item = new String[members.length];
//            int i = 0;
//            for (Object obj : members) {
//                item[i] = gson.toJson(obj);
//                i++;
//            }
//            rows = jedis.srem(key, item);
//        } catch (Exception e) {
//            throw e;
//        }
//        return rows;
//    }
//
//    @Override
//    public <T> Set<T> smembers(String key, Class<T> t) throws Exception {
//        try {
//            jedis = shardedJedisPool.getResource();
//            Set<String> sItem = jedis.smembers(key);
//
//            Set<T> result = new HashSet<T>();
//            Iterator<String> rt = sItem.iterator();
//            while (rt.hasNext()) {
//                result.add(gson.fromJson(rt.next(), t));
//            }
//            return result;
//        } catch (Exception e) {
//            throw e;
//        }
//    }
//
//    @Override
//    public Long sremove(String key) throws Exception {
//        Long result = 0L;
//        try {
//            jedis = shardedJedisPool.getResource();
//            result = jedis.del(key);
//        } catch (Exception e) {
//            throw e;
//        }
//        return result;
//    }
////
////    //-------------------map--------------------------
////    @Override
////    public void setMap(String key, String mapKey, Object value)
////            throws Exception {
////        ShardedJedis jedis = null;
////        try {
////            jedis = shardedJedisPool.getResource();
////            String jsonValue = gson.toJson(value);
////            jedis.hset(key, mapKey, jsonValue);
////        } catch (Exception e) {
////            throw e;
////        }
////    }
////
////    @Override
////    public <T> T getMapValue(String key, String mapKey, Class<T> t)
////            throws Exception {
////        Gson gson = new Gson();
////        List<String> list = null;
////        try {
////            jedis = shardedJedisPool.getResource();
////            list = jedis.hmget(key, mapKey);
////        } catch (Exception e) {
////            throw e;
////        }
////
////        if (list == null || list.size() == 0) {
////            return null;
////        }
////
////        String value = list.get(0);
////        if (value != null) {
////            return gson.fromJson(value, t);
////        }
////        return null;
////    }
////
////    @Override
////    public <T> List<T> getMapValues(String key, Class<T> t) throws Exception {
////        Gson gson = new Gson();
////        List<String> list = new ArrayList<String>();
////        List<T> rList = new ArrayList<T>();
////
////        try {
////            jedis = shardedJedisPool.getResource();
////            list = jedis.hvals(key);
////        } catch (Exception e) {
////            throw e;
////        }
////
////        if (list != null) {
////            for (int i = 0; i < list.size(); i++) {
////                rList.add(gson.fromJson(list.get(i), t));
////            }
////        }
////
////        return rList;
////    }
////
////    @Override
////    public Long removeMap(String key, String valueKey) throws Exception {
////        Long result = 0L;
////        try {
////            jedis = shardedJedisPool.getResource();
////            result = jedis.hdel(key, valueKey);
////        } catch (Exception e) {
////            throw e;
////        }
////        return result;
////    }
////
////    @Override
////    public Map<String, String> getMaps(String key) throws Exception {
////        Map<String, String> rList = new HashMap<String, String>();
////        try {
////            jedis = shardedJedisPool.getResource();
////            rList = jedis.hgetAll(key);
////        } catch (Exception e) {
////            throw new Exception(e);
////        }
////        return rList;
////    }
////
