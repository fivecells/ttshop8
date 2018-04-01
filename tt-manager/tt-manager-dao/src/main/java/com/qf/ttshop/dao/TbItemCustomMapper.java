package com.qf.ttshop.dao;

import com.qf.ttshop.pojo.dto.TbSearchItemCustom;
import com.qf.ttshop.pojo.vo.TbItemCustom;

import java.util.List;
import java.util.Map;

/**
 * 扩展自商品的数据访问层接口
 * User: DHC
 * Date: 2018/1/2
 * Time: 15:12
 * Version:V1.0
 */
public interface TbItemCustomMapper {

    /**
     * 分页查询商品数据集合
     * @param map
     * @return
     */
    List<TbItemCustom> listItemsByPage(Map<String,Object> map);

    /**
     * 通过条件查询商品数量
     * @return
     */
    Long countItemsByCondition(Map<String,Object> map);

    /**
     * 用于索引库的采集数据（采集的是集合）
     */
    List<TbSearchItemCustom> listSearchItems();

    /**
     *通过商品ID查询出该商品（单个商品）
     * @param itemId
     * @return
     */
    TbSearchItemCustom getItemCustomById(long itemId);
}
