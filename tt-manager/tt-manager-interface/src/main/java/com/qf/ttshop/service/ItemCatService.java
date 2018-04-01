package com.qf.ttshop.service;

import com.qf.ttshop.dto.TreeNode;

import java.util.List;

/**
 * User: DHC
 * Date: 2018/1/6
 * Time: 11:13
 * Version:V1.0
 */
public interface ItemCatService {
    List<TreeNode> listCatsByParentId(Long parentId);
}
