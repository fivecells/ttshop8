package com.qf.ttshop.web;

import com.qf.ttshop.dto.Page;
import com.qf.ttshop.dto.Result;
import com.qf.ttshop.pojo.po.TbItemParam;
import com.qf.ttshop.pojo.vo.TbItemParamCustom;
import com.qf.ttshop.service.ItemParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * User: DHC
 * Date: 2018/1/8
 * Time: 11:31
 * Version:V1.0
 */
@Controller
public class ItemParamAction {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ItemParamService itemParamService;

    @ResponseBody
    @RequestMapping(value = "/itemParams", method = RequestMethod.GET)
    public Result<TbItemParamCustom> listItemParamsByPage(Page page) {
        Result<TbItemParamCustom> result = null;
        try {
            result = itemParamService.listItemParamsByPage(page);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/itemParam/{cid}",method = RequestMethod.POST)
    public int saveItemParam(@PathVariable("cid") Long cid, @RequestParam("paramData") String paramData){
        System.out.println("xxxxx");
        return 0;
    }

    @ResponseBody
    @RequestMapping(value = "/itemParam/{cid}",method = RequestMethod.GET)
    public TbItemParam getByItemCatId(@PathVariable("cid") Long cid){
        TbItemParam  tbItemParam  = null;
        try {
            tbItemParam  = itemParamService.getByItemCatId(cid);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return tbItemParam;
    }

}
