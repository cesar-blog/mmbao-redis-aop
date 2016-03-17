package com.mmbao.redis.service.business.impl;

import com.mmbao.redis.annotation.ReadCacheType;
import com.mmbao.redis.annotation.ReadThroughAssignRedisClusterCache;
import com.mmbao.redis.dao.mybatis.domain.XmallDxDlDz;
import com.mmbao.redis.dao.mybatis.domain.XmallDxDlDzExample;
import com.mmbao.redis.dao.mybatis.persistence.XmallDxDlDzMapper;
import com.mmbao.redis.service.business.IHomeService;
import com.mmbao.redis.util.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Cesar.X on 2016/3/6.
 * 接口实现
 */
@Service
public class HomeServiceImpl implements IHomeService {


    /**
     * xmallDxdlDz表分装的操作方法.
     */
    @Autowired
    private XmallDxDlDzMapper xmallDxDlDzMapper;


    /**
     * 列表实现.
     * 利用切面，首先从Redis缓存集群中读取，如何读取不到，则执行原先的方法(从数据库读).
     * @param page page
     * @return XmallDxDlDz
     */
    @ReadThroughAssignRedisClusterCache(namespace="YDMMBAO_REDIS_AOP",cacheType = ReadCacheType.String,
            valueclass = XmallDxDlDz.class,expireTime=0,cacheEnable = false)
    @Override
    public List<XmallDxDlDz> findList(Page page) {
        XmallDxDlDzExample example = new XmallDxDlDzExample();
        //page total
        page.setTotalCount(xmallDxDlDzMapper.countByExample(example));
        example.setOrderByClause("MODIFY_DATE desc");
        example.setPage(page);

        return xmallDxDlDzMapper.selectByExample(example);
    }


}
