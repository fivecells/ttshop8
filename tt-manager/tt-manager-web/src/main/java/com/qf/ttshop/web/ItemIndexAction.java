package com.qf.ttshop.web;

import com.qf.ttshop.dto.MessageResult;
import com.qf.ttshop.service.ItemIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User: DHC
 * Date: 2018/1/24
 * Time: 9:41
 * Version:V1.0
 */
@Controller
public class ItemIndexAction {

    @Autowired
    private ItemIndexService itemIndexService;

    @ResponseBody
    @RequestMapping(value = "/item/index",method = RequestMethod.POST)
    public MessageResult itemIndex(){
        //1 调用业务层方法导入到索引库
        boolean bl = itemIndexService.importAll();
        MessageResult messageResult = new MessageResult();
        messageResult.setSuccess(false);
        //2 封装DTO对象
        if(bl){
            messageResult.setSuccess(true);
            messageResult.setMessage("恭喜！导入成功！");
        }
        return messageResult;
    }

}
