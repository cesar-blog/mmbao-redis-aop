package com.mmbao.redis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Cesar.X on 2016/3/5.
 * 读Redis集群缓存注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ReadThroughAssignRedisClusterCache {

    /**
     * 命名 空间  也是 KEY的前缀.
     *
     * @return
     */
    String namespace() default AnnotationConstants.DEFAULT_STRING;

    /**
     * 默认key的后缀.
     * 你也可以自定义.
     *
     * @return
     */
    String assignedKey() default AnnotationConstants.DEFAULT_KEY_NAME;

    /**
     * redis存储缓存的类型.
     *
     * @return
     */
    ReadCacheType cacheType() default ReadCacheType.String;

    /**
     * 有效时间 默认 永久有效.
     *
     * @return int,单位秒
     */
    int expireTime() default 0;

    /**
     * 是否启用  默认 开启.
     *
     * @return
     */
    boolean cacheEnable() default true;

    /**
     * 默认 value 存储类型.
     *
     * @return
     */
    Class<?> valueclass() default String.class;
}
