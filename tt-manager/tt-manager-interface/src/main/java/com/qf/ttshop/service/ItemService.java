package com.qf.ttshop.service;

import com.qf.ttshop.dto.Order;
import com.qf.ttshop.dto.Page;
import com.qf.ttshop.dto.Result;
import com.qf.ttshop.pojo.po.TbItem;
import com.qf.ttshop.pojo.vo.TbItemCustom;
import com.qf.ttshop.pojo.vo.TbItemQuery;

import java.util.List;

/**
 * User: DHC
 * Date: 2017/12/29
 * Time: 14:20
 * Version:V1.0
 */
public interface ItemService {
    /**
     * 通过主键查询商品
     * @param itemId 商品表的主键
     * @return 商品对象
     */
    TbItem getItemById(Long itemId);

    /**
     * 不带分页查询所有商品
     * @return 所有商品列表
     */
    List<TbItem> listItems();

    /**
     * 带分页查询所有商品
     * @param page 请求参数
     * @return 分页后的商品结果集
     */
    Result<TbItemCustom> listItemsByPage(Page page, Order order, TbItemQuery query);

    /**
     * 批量更新操作
     * @param ids
     * @return
     */
    int batchUpdate(List<Long> ids);

    /**
     * 新增商品
     * @param item
     * @param itemDesc
     * @return
     */
    Long saveItem(TbItem item, String itemDesc, String paramData);
}
