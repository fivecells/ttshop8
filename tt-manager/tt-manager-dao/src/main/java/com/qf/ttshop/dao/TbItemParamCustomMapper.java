package com.qf.ttshop.dao;

import com.qf.ttshop.dto.Page;
import com.qf.ttshop.pojo.po.TbItemParam;
import com.qf.ttshop.pojo.vo.TbItemParamCustom;

import java.util.List;

/**
 * User: DHC
 * Date: 2018/1/8
 * Time: 11:42
 * Version:V1.0
 */
public interface TbItemParamCustomMapper {

    Long countItemParams();

    List<TbItemParamCustom> listItemParamsByPage(Page page);

    TbItemParam getByItemCatId(Long cid);
}
