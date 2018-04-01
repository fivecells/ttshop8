package com.qf.ttshop.service.impl;

import com.qf.ttshop.dao.TbItemCustomMapper;
import com.qf.ttshop.dao.TbItemDescMapper;
import com.qf.ttshop.dao.TbItemMapper;
import com.qf.ttshop.dao.TbItemParamItemMapper;
import com.qf.ttshop.dto.Order;
import com.qf.ttshop.dto.Page;
import com.qf.ttshop.dto.Result;
import com.qf.ttshop.pojo.po.TbItem;
import com.qf.ttshop.pojo.po.TbItemDesc;
import com.qf.ttshop.pojo.po.TbItemExample;
import com.qf.ttshop.pojo.po.TbItemParamItem;
import com.qf.ttshop.pojo.vo.TbItemCustom;
import com.qf.ttshop.pojo.vo.TbItemQuery;
import com.qf.ttshop.service.ItemService;
import com.qf.ttshop.utils.IDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品的业务逻辑层实现类
 * 在业务逻辑层实现类中异常处理需要尽量细化
 * 而且每个异常都应该写入日志中
 * User: DHC
 * Date: 2017/12/29
 * Time: 14:21
 * Version:V1.0
 */
@Service
public class ItemServiceImpl implements ItemService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TbItemMapper itemDao;
    @Autowired
    private TbItemCustomMapper itemCustomDao;
    @Autowired
    private TbItemDescMapper itemDescDao;
    @Autowired
    private TbItemParamItemMapper itemParamItemDao;

    @Override
    public TbItem getItemById(Long itemId) {
        return itemDao.selectByPrimaryKey(itemId);
    }

    @Override
    public List<TbItem> listItems() {
        List<TbItem> list = null;
        try {
            list = itemDao.selectByExample(null);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Result<TbItemCustom> listItemsByPage(Page page, Order order, TbItemQuery query) {
        Result<TbItemCustom> result = new Result<TbItemCustom>();
        try {
            //构建一个Map用来传递参数给DAO
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("page", page);
            map.put("order", order);
            map.put("query", query);

            Long total = itemCustomDao.countItemsByCondition(map);
            List<TbItemCustom> list = itemCustomDao.listItemsByPage(map);
            //商品总数
            result.setTotal(total);
            //指定页码的商品集合
            result.setRows(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int batchUpdate(List<Long> ids) {
        int i = 0;
        try {
            //创建一个对象，设置商品状态为“删除”3
            TbItem item = new TbItem();
            item.setStatus((byte)3);
            //创建更新模板
            TbItemExample example = new TbItemExample();
            TbItemExample.Criteria criteria = example.createCriteria();
            criteria.andIdIn(ids);
            //执行更新操作
            i = itemDao.updateByExampleSelective(item, example);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return i;
    }

    //这里的saveItem事务方法，注意并不是事务方法越多越好，查询方法不需要写成事务方法
    //事务方法应该尽量注意其原子性，假如事务方法有调用第三方接口的操作，那么建议拆解后使用
    @Transactional
    @Override
    public Long saveItem(TbItem item, String itemDesc, String paramData) {
        Long itemId = null;
        try {
            //存放两张表
            //tb_item
            itemId = IDUtils.getItemId();
            item.setId(itemId);
            item.setStatus((byte)1);
            item.setCreated(new Date());
            item.setUpdated(new Date());
            int i = itemDao.insert(item);
            //tb_item_desc
            TbItemDesc tbItemDesc = new TbItemDesc();
            tbItemDesc.setItemId(itemId);
            tbItemDesc.setItemDesc(itemDesc);
            tbItemDesc.setCreated(new Date());
            tbItemDesc.setUpdated(new Date());
            i += itemDescDao.insert(tbItemDesc);
            //tb_item_param_item
            TbItemParamItem tbItemParamItem = new TbItemParamItem();
            tbItemParamItem.setItemId(itemId);
            tbItemParamItem.setParamData(paramData);
            tbItemParamItem.setCreated(new Date());
            tbItemParamItem.setUpdated(new Date());
            i += itemParamItemDao.insert(tbItemParamItem);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return itemId;
    }
}
