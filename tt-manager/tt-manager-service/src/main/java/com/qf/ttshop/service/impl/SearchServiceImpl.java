package com.qf.ttshop.service.impl;

import com.qf.ttshop.dao.SearchDao;
import com.qf.ttshop.pojo.dto.TbSearchItemResult;
import com.qf.ttshop.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: DHC
 * Date: 2018/1/24
 * Time: 15:13
 * Version:V1.0
 * private long recordCount;
 * private int totalPages;
 * private List<TbSearchItemCustom> itemList;
 */
@Service
public class SearchServiceImpl implements SearchService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SearchDao searchDao;

    @Override
    public TbSearchItemResult search(String keyword, Integer pageNumber, int pageSize) {
        TbSearchItemResult result = null;
        try {
            //创建solr查询对象
            SolrQuery query = new SolrQuery();
            //1 设置查询的内容
            query.setQuery(keyword);
            //2 设置分页条件
            if (pageNumber <= 0) pageNumber = 1;
            query.setStart((pageNumber - 1) * pageSize);
            query.setRows(pageSize);
            //3 设置搜索域
            query.set("df","item_keywords");
            //4 设置高亮显示
            query.setHighlight(true);
            query.addHighlightField("item_title");
            query.setHighlightSimplePre("<em style='color:red;'>");
            query.setHighlightSimplePost("</em>");
            //通过查询条件执行DAO查询方法
            //recordCount,list
            result = searchDao.search(query);
            //计算总页数
            long recordCount = result.getRecordCount();
            int totalPages = ((int)recordCount+pageSize-1)/pageSize;
            result.setTotalPages(totalPages);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }
}
