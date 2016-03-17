package com.mmbao.redis.dao.redis;

import redis.clients.jedis.ShardedJedis;

/**
 * Created by Cesar.X on 2016/3/14.
 * 这个接口是操作sharedJedis,redis分片处理，并不是集群
 */
public interface IShardedJedisDataSource {

    /**
     * 从连接池中获取.
     * @return ShardedJedis
     */
    public abstract ShardedJedis getRedisClient();

    /**
     * 返回给连接池.
     * @param shardedJedis shardedJedis
     */
    public void returnResource(ShardedJedis shardedJedis);

//    public void returnResource(ShardedJedis shardedJedis,boolean broken);
}
