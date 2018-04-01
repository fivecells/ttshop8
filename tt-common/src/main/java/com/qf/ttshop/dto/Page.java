package com.qf.ttshop.dto;

/**
 * easyui的表格分页的请求参数封装
 * User: DHC
 * Date: 2018/1/2
 * Time: 14:57
 * Version:V1.0
 */
public class Page {
    private int page;
    private int rows;

    public int getOffset() {
        return (page - 1) * rows;
    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
