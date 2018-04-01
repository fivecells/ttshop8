package com.qf.ttshop.service.impl;

import com.qf.ttshop.dao.TbItemParamCustomMapper;
import com.qf.ttshop.dto.Page;
import com.qf.ttshop.dto.Result;
import com.qf.ttshop.pojo.po.TbItemParam;
import com.qf.ttshop.pojo.vo.TbItemParamCustom;
import com.qf.ttshop.service.ItemParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: DHC
 * Date: 2018/1/8
 * Time: 11:40
 * Version:V1.0
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbItemParamCustomMapper itemParamCustomDao;

    @Override
    public Result<TbItemParamCustom> listItemParamsByPage(Page page) {
        Result<TbItemParamCustom> result = new Result<TbItemParamCustom>();
        try {
            Long total = itemParamCustomDao.countItemParams();
            List<TbItemParamCustom> list =  itemParamCustomDao.listItemParamsByPage(page);
            result.setTotal(total);
            result.setRows(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public TbItemParam getByItemCatId(Long cid) {
        TbItemParam  tbItemParam  = null;
        try {
            tbItemParam = itemParamCustomDao.getByItemCatId(cid);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return tbItemParam;
    }
}
