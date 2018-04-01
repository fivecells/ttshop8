package com.qf.ttshop.web;

import com.qf.ttshop.dto.TreeNode;
import com.qf.ttshop.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * User: DHC
 * Date: 2018/1/5
 * Time: 17:54
 * Version:V1.0
 */
@Controller
public class ItemCatAction {

    @Autowired
    private ItemCatService itemCatService;

    @ResponseBody
    @RequestMapping(value = "/itemCat", method = RequestMethod.POST)
    public List<TreeNode> listCatsByParentId(@RequestParam("parentId") Long parentId) {
        List<TreeNode> list = null;
        try {
            list = itemCatService.listCatsByParentId(parentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
