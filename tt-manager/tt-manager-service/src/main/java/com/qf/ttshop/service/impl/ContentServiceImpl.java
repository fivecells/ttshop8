package com.qf.ttshop.service.impl;

import com.qf.ttshop.dao.TbContentMapper;
import com.qf.ttshop.jedis.JedisClient;
import com.qf.ttshop.pojo.po.TbContent;
import com.qf.ttshop.pojo.po.TbContentExample;
import com.qf.ttshop.service.ContentService;
import com.qf.ttshop.utils.JsonUtils;
import com.qf.ttshop.utils.StrKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: DHC
 * Date: 2018/1/22
 * Time: 9:32
 * Version:V1.0
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private JedisClient jedisClient;
    @Autowired
    private TbContentMapper contentDao;


    @Override
    public List<TbContent> getContentListByCid(Long cid) {
        //1 在缓存服务器中进行查询
        try {
            String listJson = jedisClient.hget("CONTENT_LIST", Long.toString(cid));
            if (StrKit.notBlank(listJson)){
                List<TbContent> contentList = JsonUtils.jsonToList(listJson, TbContent.class);
                return contentList;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        //2 主业务：去mysql中查询
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        List<TbContent> contentList = contentDao.selectByExample(example);
        //3 将查询到的数据存放到缓存服务器中
        try {
            jedisClient.hset("CONTENT_LIST",Long.toString(cid),JsonUtils.objectToJson(contentList));
        }catch (Exception e) {
            e.printStackTrace();
        }
        return contentList;
    }
}
