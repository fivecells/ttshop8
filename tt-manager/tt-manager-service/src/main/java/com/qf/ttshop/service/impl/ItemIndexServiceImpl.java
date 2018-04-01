package com.qf.ttshop.service.impl;

import com.qf.ttshop.dao.TbItemCustomMapper;
import com.qf.ttshop.pojo.dto.TbSearchItemCustom;
import com.qf.ttshop.service.ItemIndexService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * User: DHC
 * Date: 2018/1/24
 * Time: 9:52
 * Version:V1.0
 */
@Service
public class ItemIndexServiceImpl implements ItemIndexService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbItemCustomMapper itemCustomDao;
    @Autowired
    private SolrServer solrServer;
    @Override
    public boolean importAll() {
        //1 采集数据
        List<TbSearchItemCustom> searchList = itemCustomDao.listSearchItems();
        //2 创建索引库
        // 遍历集合
        for (TbSearchItemCustom itemCustom : searchList){
            // 创建document
            SolrInputDocument document = new SolrInputDocument();
            // TbSearchItemCustom--SolrInputDocument
            document.addField("id",itemCustom.getId());
            document.addField("item_title",itemCustom.getTitle());
            document.addField("item_sell_point",itemCustom.getSellPoint());
            document.addField("item_price",itemCustom.getPrice());
            document.addField("item_image",itemCustom.getImage());
            document.addField("item_category_name",itemCustom.getCatName());
            //拿到装配好的document存放到索引库中
            try {
                solrServer.add(document);
            } catch (SolrServerException e) {
                logger.error(e.getMessage(),e);
                return false;
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
                return false;
            }
        }
        // solrServer提交
        try {
            solrServer.commit();
        } catch (SolrServerException e) {
            logger.error(e.getMessage(),e);
            return false;
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            return false;
        }
        return true;
    }
}
