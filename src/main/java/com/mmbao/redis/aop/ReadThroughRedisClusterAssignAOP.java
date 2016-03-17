package com.mmbao.redis.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by Cesar.X on 2016/3/5.
 * redis集群aop环切实现.
 */
@Aspect
@Component
public class ReadThroughRedisClusterAssignAOP extends SingleReadRedisClusterCacheAdivce {

    /**
     * log4j.
     */
    private static final Logger log = Logger.getLogger(ReadThroughRedisClusterAssignAOP.class);

    /**
     * 指定切入点.
     */
    @Pointcut("@annotation(com.mmbao.redis.annotation.ReadThroughAssignRedisClusterCache)")
    public void getSingleAssign() {
    }

    /**
     * 指定切入点.
     * @param jp 切入点
     * @return 方法返回的对象
     * @throws Throwable
     */
    @Around("getSingleAssign()")
    public Object cacheSingleAssign(ProceedingJoinPoint jp) throws Throwable {
        return cache(jp);
    }

    /**
     * 扫描以下列单词开头的进行切入.
     * @param jp 切入点
     * @return 方法返回的对象
     * @throws Throwable
     */
//    @Around("execution(* com.mmbao.redis.persistence.XmallDxDlDzMapper.select*(..))" +
//            "|| execution(* com.mmbao.redis.persistence.XmallDxDlDzMapper.get*(..))" +
//            "|| execution(* com.mmbao.redis.persistence.XmallDxDlDzMapper.find*(..))" +
//            "|| execution(* com.mmbao.redis.persistence.XmallDxDlDzMapper.count*(..))" +
//            "|| execution(* com.mmbao.redis.persistence.XmallDxDlDzMapper.search*(..))")
//    public Object cacheSingleAssign(ProceedingJoinPoint jp) throws Throwable {
//        return cache(jp);
//    }

}
