package com.qf.ttshop.service;

import com.qf.ttshop.pojo.po.TbContent;

import java.util.List;

/**
 * User: DHC
 * Date: 2018/1/22
 * Time: 9:27
 * Version:V1.0
 */
public interface ContentService {
    List<TbContent> getContentListByCid(Long cid);
}
