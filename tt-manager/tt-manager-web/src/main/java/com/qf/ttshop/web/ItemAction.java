package com.qf.ttshop.web;

import com.qf.ttshop.dto.MessageResult;
import com.qf.ttshop.dto.Order;
import com.qf.ttshop.dto.Page;
import com.qf.ttshop.dto.Result;
import com.qf.ttshop.pojo.po.TbItem;
import com.qf.ttshop.pojo.vo.TbItemCustom;
import com.qf.ttshop.pojo.vo.TbItemQuery;
import com.qf.ttshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.jms.*;
import java.util.List;

/**
 * User: DHC
 * Date: 2017/12/29
 * Time: 14:13
 * Version:V1.0
 */
@Controller
public class ItemAction {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ItemService itemService;
    @Autowired
    private JmsTemplate jmsTemplate;
    //通过名称注入，而不是类型注入
    @Resource
    private Destination topicDestination;


    @ResponseBody
    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.GET)
    public TbItem getItemById(@PathVariable("itemId") Long itemId) {
        return itemService.getItemById(itemId);
    }


    @ResponseBody
    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public Result<TbItemCustom> listItems(Page page, Order order, TbItemQuery query) {
        Result<TbItemCustom> result = null;
        try {
            result = itemService.listItemsByPage(page, order, query);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/item/batch", method = RequestMethod.POST)
    public int batchUpdate(@RequestParam("ids[]") List<Long> ids) {
        int i = 0;
        try {
            i = itemService.batchUpdate(ids);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return i;
    }

    @ResponseBody
    @RequestMapping(value = "/item", method = RequestMethod.POST)
    public MessageResult saveItem(TbItem item, String itemDesc, String paramData) {
        MessageResult result = new MessageResult();
        try {
            //1 返回保存商品的ID
            //a action saveItem返回值 MessageResult
            //long itemId = itemService.saveItem(xxx);
            Long itemId = itemService.saveItem(item, itemDesc, paramData);
            //2 发送消息（商品ID）
            //a jmsTemplate.send(目标对象，MessageCreator);
            if (itemId != null) {
                jmsTemplate.send(topicDestination, new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        TextMessage message = session.createTextMessage(itemId + "");
                        return message;
                    }
                });
            }
            result.setSuccess(true);
            result.setMessage("新增商品成功！");
        } catch (Exception e) {
            result.setSuccess(false);
            logger.error(e.getMessage(), e);
        }
        return result;
    }

}
