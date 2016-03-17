package com.mmbao.redis.service.business;

import com.mmbao.redis.dao.mybatis.domain.XmallDxDlDz;
import com.mmbao.redis.util.page.Page;

import java.util.List;

/**
 * Created by Cesar.X on 2016/3/6.
 * 接口
 */
public interface IHomeService {

    /**
     * 列表.
     *
     * @param page page
     * @return list<XmallDxDlDz></XmallDxDlDz>
     */
    List<XmallDxDlDz> findList(Page page);
}
