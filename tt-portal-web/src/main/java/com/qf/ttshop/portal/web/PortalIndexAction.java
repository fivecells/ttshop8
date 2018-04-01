package com.qf.ttshop.portal.web;

import com.qf.ttshop.pojo.po.TbContent;
import com.qf.ttshop.service.ContentService;
import com.qf.ttshop.utils.PropKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * User: DHC
 * Date: 2018/1/19
 * Time: 15:24
 * Version:V1.0
 */
@Controller
public class PortalIndexAction {

    @Autowired
    private ContentService contentService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(Model model){
        Long cid = PropKit.use("picture.properties").getLong("categoryId");
        List<TbContent> list =  contentService.getContentListByCid(cid);
        model.addAttribute("ad1List",list);
        return "index";
    }
}
