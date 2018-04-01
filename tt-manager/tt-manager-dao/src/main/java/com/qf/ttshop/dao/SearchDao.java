package com.qf.ttshop.dao;

import com.qf.ttshop.pojo.dto.TbSearchItemCustom;
import com.qf.ttshop.pojo.dto.TbSearchItemResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: DHC
 * Date: 2018/1/24
 * Time: 15:35
 * Version:V1.0
 */
@Repository
public class SearchDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SolrServer solrServer;

    public TbSearchItemResult search(SolrQuery query) {
        TbSearchItemResult result = new TbSearchItemResult();
        try {
            //通过查询条件执行DAO查询方法
            //recordCount,list
            //获取查询响应
            QueryResponse queryResponse = solrServer.query(query);
            //获取查询结果集
            SolrDocumentList solrDocumentList = queryResponse.getResults();
            //获取总记录数
            long numFound = solrDocumentList.getNumFound();
            result.setRecordCount(numFound);

            //获取高亮的列表
            Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
            List<TbSearchItemCustom> searchItemCustomList = new ArrayList<TbSearchItemCustom>();
            //遍历solrDocumentList形成List<TbSearchItemCustom>
            //solrDocumentList---List<TbSearchItemCustom>
            for (SolrDocument document : solrDocumentList) {
                TbSearchItemCustom item = new TbSearchItemCustom();
                item.setId((String) document.get("id"));
                item.setCatName((String) document.get("item_category_name"));
                item.setImage((String) document.get("item_image"));
                item.setPrice((long) document.get("item_price"));
                item.setSellPoint((String) document.get("item_sell_point"));

                //获取高亮列表的值
                List<String> list = highlighting.get(document.get("id")).get("item_title");
                String title = "";
                if (list != null && !list.isEmpty()) {
                    title = list.get(0);
                } else {
                    title = (String) document.get("item_tile");
                }
                item.setTitle(title);
                searchItemCustomList.add(item);
            }
            result.setItemList(searchItemCustomList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return result;//recordCount,list
    }
}
