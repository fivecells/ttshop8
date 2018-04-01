package com.qf.ttshop.service;

import com.qf.ttshop.dto.Page;
import com.qf.ttshop.dto.Result;
import com.qf.ttshop.pojo.po.TbItemParam;
import com.qf.ttshop.pojo.vo.TbItemParamCustom;

/**
 * User: DHC
 * Date: 2018/1/8
 * Time: 11:39
 * Version:V1.0
 */
public interface ItemParamService {
    Result<TbItemParamCustom> listItemParamsByPage(Page page);

    TbItemParam getByItemCatId(Long cid);
}
