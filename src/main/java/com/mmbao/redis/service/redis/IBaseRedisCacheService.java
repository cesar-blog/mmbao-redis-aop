package com.mmbao.redis.service.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by Cesar.X on 2016/3/6.
 * redis缓存操作的接口
 */
public interface IBaseRedisCacheService {


    //----------------String-------------------
    //    常用命令：
    //    除了get、set、incr、decr mget等操作外，Redis还提供了下面一些操作：
    //    获取字符串长度
    //    往字符串append内容
    //    设置和获取字符串的某一段内容
    //    设置及获取字符串的某一位（bit）
    //    批量设置一系列字符串的内容
    //
    //    应用场景：
    //    String是最常用的一种数据类型，普通的key/value存储都可以归为此类，value其实不仅是String，
    //    也可以是数字：比如想知道什么时候封锁一个IP地址(访问超过几次)。INCRBY命令让这些变得很容易，通过原子递增保持计数。

    /**
     * 给一个 KEY 设置一个 值 ，并且设置有效时间
     *
     * @param key    存储的KEY
     * @param value  存储KEY的值
     * @param expire 有效时间
     * @throws Exception 异常
     */
    public void set(String key, Object value, int expire) throws Exception;

    /**
     * 获取 KEY对应的值
     *
     * @param key 查询的KEY
     * @param cls 返回的对象类型
     * @return 泛型
     * @throws Exception 异常
     */
    public <T> T get(String key, Class<T> cls) throws Exception;

    //----------------list-------------------

    //--------------Set-----------------------
    //    常用命令：
    //    sadd,srem,spop,sdiff ,smembers,sunion 等。
    //
    //    应用场景：
    //    Redis set对外提供的功能与list类似是一个列表的功能，特殊之处在于set是可以自动排重的，当你需要存储一个列表数据，
    //    又不希望出现重复数据时，set是一个很好的选择，并且set提供了判断某个成员是否在一个set集合内的重要接口，这个也是list所不能提供的。
    //    比如在微博应用中，每个人的好友存在一个集合（set）中，这样求两个人的共同好友的操作，可能就只需要用求交集命令即可。
    //    Redis还为集合提供了求交集、并集、差集等操作，可以非常方便的实

    /**
     * 返回Set中成员的数量，如果该Key并不存在，返回0。
     *
     * @param key key
     * @return set中成员的数量
     * @throws Exception
     */
    public Long scard(String key) throws Exception;

    /**
     * 如果在插入的过程用，参数中有的成员在Set中已经存在，该成员将被忽略，而其它成员仍将会被正常插入。
     * 如果执行该命令之前，该Key并不存在，该命令将会创建一个新的Set，此后再将参数中的成员陆续插入。
     * 如果该Key的Value不是Set类型，该命令将返回相关的错误信息。
     *
     * @param key key
     * @param members members
     * @return 本次操作实际插入的成员数量。
     * @throws Exception
     */
    public Long sadd(String key, Object... members) throws Exception;

    /**
     * 从与Key关联的Set中删除参数中指定的成员，不存在的参数成员将被忽略，如果该Key并不存在，将视为空Set处理。
     *
     * @param key key
     * @param members members
     * @return 从Set中实际移除的成员数量，如果没有则返回0。
     * @throws Exception
     */
    public Long srem(String key, Object... members) throws Exception;

    /**
     * 查询set 里面的成员
     *
     * @param key
     * @throws Exception
     */
    public <T> Set<T> smembers(String key, Class<T> t) throws Exception;

    /**
     * 删除 set key的数据
     *
     * @throws Exception
     */
    public Long sremove(String key) throws Exception;


//    //--------------Map-----------------------
//
//    /**
//     * Map集合存储值
//     *
//     * @param key    map的key
//     * @param mapKey map里面value对应的key
//     * @param value  要存储的值
//     * @throws Exception 异常
//     */
//    public void setMap(String key, String mapKey, Object value) throws Exception;
//
//    /**
//     * 获取缓存中，map集合中mapkey存放的对象
//     *
//     * @param key    以对象形式存储的名字
//     * @param mapKey map中key值
//     * @param t      返回实体对象类型
//     * @throws Exception
//     */
//    public <T> T getMapValue(String key, String mapKey, Class<T> t) throws Exception;
//
//    /**
//     * 获取缓存中，map集合中的值
//     *
//     * @param key 以对象形式存储的名字
//     * @param t   返回实体对象类型
//     * @throws Exception
//     */
//    public <T> List<T> getMapValues(String key, Class<T> t) throws Exception;
//
//    /**
//     * 删除 map里面的某一个值
//     *
//     * @param key      map的外层key
//     * @param valueKey 值对应的key
//     * @return
//     * @throws Exception
//     */
//    public Long removeMap(String key, String valueKey) throws Exception;
//
//    /**
//     * 获取 map里面 所有 key对应的 value
//     *
//     * @param key 以对象形式存储的key
//     * @throws Exception
//     */
//    public Map<String, String> getMaps(String key) throws Exception;

//
//


//	//--------------List----------------------
//


}
