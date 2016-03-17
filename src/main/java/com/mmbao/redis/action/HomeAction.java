package com.mmbao.redis.action;

import com.mmbao.redis.dao.mybatis.domain.XmallDxDlDz;
import com.mmbao.redis.service.business.IHomeService;
import com.mmbao.redis.util.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


/**
 * Created by Cesar.X on 2016/3/6.
 * homeaction
 */
@Controller
@RequestMapping("/home")
public class HomeAction {

    /**
     * service.
     */
    @Autowired
    private IHomeService iHomeService;

    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    @SuppressWarnings("unchecked")
    public String list(Model model, Page page) {
        List<XmallDxDlDz> dxdlDzList = iHomeService.findList( page);
        model.addAttribute("page", page);
        model.addAttribute("dxdlDzList", dxdlDzList);
        model.asMap();
        return "adminBase.definition";
    }

}
