//package com.mmbao.redis.dao.redis.impl;
//
//import com.mmbao.redis.dao.redis.IShardedJedisDataSource;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import redis.clients.jedis.ShardedJedis;
//import redis.clients.jedis.ShardedJedisPool;
//
///**
// * Created by Cesar.X on 2016/3/14.
// * 从连接池中操作sharedJedis实现类
// */
//@Repository()
//public class ShardedJedisDataSourceImpl implements IShardedJedisDataSource {
//
//    /**
//     * log4j.
//     */
//    private static final Logger log = LoggerFactory.getLogger(ShardedJedisDataSourceImpl.class);
//
//    @Autowired
//    private ShardedJedisPool shardedJedisPool;
//
//    /**
//     * 从连接池中获取shardJedis.
//     *
//     * @return ShardedJedis
//     */
//    @Override
//    public ShardedJedis getRedisClient() {
//        try {
//            ShardedJedis shardJedis = shardedJedisPool.getResource();
//            return shardJedis;
//        } catch (Exception e) {
//            log.error("getRedisClent error", e);
//        }
//        return null;
//    }
//
//    /**
//     * 将shardedJedis返回给连接池.
//     *
//     * @param shardedJedis shardedJedis
//     */
//    @Override
//    public void returnResource(ShardedJedis shardedJedis) {
//        shardedJedis.close();
//    }
//}
