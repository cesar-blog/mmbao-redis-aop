package com.mmbao.redis.aop;

import com.mmbao.redis.annotation.ReadThroughAssignRedisClusterCache;
import com.mmbao.redis.service.redis.IClusterRedisCacheService;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;

/**
 * Created by Cesar.X on 2016/3/5.
 * redis aop具体实现的方法.
 */
abstract class SingleReadRedisClusterCacheAdivce {

    /**
     * log4j.
     */
    private static final Logger log = Logger.getLogger(SingleReadRedisClusterCacheAdivce.class);

    /**
     * 操作redis集群封装的方法.
     */
    @Autowired
    private IClusterRedisCacheService iClusterRedisCacheService;

    /**
     * 方法调用前，先查询缓存。如果存在缓存，则返回缓存数据，阻止方法调用.
     * 如果没有缓存，则调用业务方法，然后将结果放到缓存中.
     *
     * @param jp 切入点
     * @return aop切入方法的返回值
     * @throws Throwable
     */
    @SuppressWarnings("unchecked")
    protected Object cache(ProceedingJoinPoint jp) throws Throwable {
        log.info("ydmmbao-redis-aop =============>>>>>>> 进入AOP");
        // 得到类名
        String clazzName = jp.getTarget().getClass().getName();
        // 得法方法名
        String methodName = jp.getSignature().getName();
        // 得到方法的参数
        Object[] args = jp.getArgs();
        // 得到被代理的方法
        java.lang.reflect.Method method = getMethod(jp);
        // 得到被代理的方法上的注解
        ReadThroughAssignRedisClusterCache cacheable = method.getAnnotation(ReadThroughAssignRedisClusterCache.class);
        // 得到过期时间
        int expireTime = cacheable.expireTime();
        // 得到数据类型
        Class modelType = cacheable.valueclass();
        // 得到是否启用缓存
        boolean cacheEnable = cacheable.cacheEnable();
        // 得到自定义key
        String assignedKey = cacheable.assignedKey();
        // 根据类名，方法名和参数生成key
        String key = genKey(clazzName, methodName, args, assignedKey);
        log.info("ydmmbao-redis-aop AOP的key =============>>>>>>>" + key);
        Object result = null;
        if (cacheEnable) {
            log.info("ydmmbao-redis-aop =============>>>>>>> 启用缓存");
            // 检查redis中是否有缓
            boolean isExist = iClusterRedisCacheService.exists(key);
            // result是方法的最终返回结果
            if (!isExist) {
                // 缓存未命中
                log.info("ydmmbao-redis-aop =============>>>>>>> 缓存未命中");
                // 调用数据库查询方法
                result = jp.proceed(args);
                //写入缓存
                iClusterRedisCacheService.set(key, result, expireTime);
            } else {
                // 缓存命中
                result = iClusterRedisCacheService.get(key, modelType);
                log.info("ydmmbao-redis-aop =============>>>>>>> 缓存命中");
            }
        } else {
            log.info("ydmmbao-redis-aop =============>>>>>>> 禁用缓存");
            // 调用数据库查询方法
            result = jp.proceed(args);
        }
        return result;
    }


    /**
     * 根据类名、方法名和参数生成key
     *
     * @param clazzName   类名
     * @param methodName  方法名
     * @param args        方法参数
     * @param assignedKey 自定义key
     * @return key
     */
    protected String genKey(String clazzName, String methodName, Object[] args, String assignedKey) {
        StringBuilder sb = new StringBuilder(clazzName);
        sb.append("_");
        sb.append(methodName);
        sb.append("_");
        sb.append(assignedKey);
//        for (Object obj : args) {
//            sb.append(obj.toString());
//            sb.append("_");
//        }
        return sb.toString();
    }


    /**
     * 获取注解方法.
     *
     * @param pjp ProceedingJoinPoint
     * @return Method
     */
    protected Method getMethod(ProceedingJoinPoint pjp) {
        // 获取参数的类型
        Object[] args = pjp.getArgs();
        @SuppressWarnings("rawtypes")
        Class[] argTypes = new Class[pjp.getArgs().length];
        for (int i = 0; i < args.length; i++) {
            argTypes[i] = args[i].getClass();
        }
        Method method = null;
        try {
            method = pjp.getTarget().getClass().getMethod(pjp.getSignature().getName(), argTypes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return method;
    }
}