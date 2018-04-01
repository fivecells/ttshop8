package com.qf.ttshop.service.impl;

import com.qf.ttshop.dao.TbItemCatMapper;
import com.qf.ttshop.dto.TreeNode;
import com.qf.ttshop.pojo.po.TbItemCat;
import com.qf.ttshop.pojo.po.TbItemCatExample;
import com.qf.ttshop.service.ItemCatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * User: DHC
 * Date: 2018/1/6
 * Time: 11:14
 * Version:V1.0
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbItemCatMapper itemCatDao;

    @Override
    public List<TreeNode> listCatsByParentId(Long parentId) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        try {
            //创建查询模板
            TbItemCatExample example = new TbItemCatExample();
            TbItemCatExample.Criteria criteria = example.createCriteria();
            criteria.andParentIdEqualTo(parentId);
            //执行查询
            List<TbItemCat> itemCatList = itemCatDao.selectByExample(example);
            //List<TbItemCat> ----> List<TreeNode>
            for (TbItemCat cat : itemCatList) {
                TreeNode tn = new TreeNode();
                tn.setId(cat.getId());
                tn.setText(cat.getName());
                tn.setState(cat.getIsParent() ? "closed" : "open");
                list.add(tn);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return list;
    }
}
