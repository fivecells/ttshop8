package com.qf.ttshop.pojo.vo;

import com.qf.ttshop.pojo.po.TbItem;

/**
 * User: DHC
 * Date: 2018/1/3
 * Time: 9:34
 * Version:V1.0
 */
public class TbItemCustom extends TbItem {

    private String catName;
    private String priceView;

    public String getPriceView() {
        return priceView;
    }

    public void setPriceView(String priceView) {
        this.priceView = priceView;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
