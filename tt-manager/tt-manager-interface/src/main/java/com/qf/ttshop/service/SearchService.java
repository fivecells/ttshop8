package com.qf.ttshop.service;

import com.qf.ttshop.pojo.dto.TbSearchItemResult;

/**
 * User: DHC
 * Date: 2018/1/24
 * Time: 15:13
 * Version:V1.0
 */
public interface SearchService {
    TbSearchItemResult search(String keyword, Integer page, int i);
}
