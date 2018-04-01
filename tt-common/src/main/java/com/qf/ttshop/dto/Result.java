package com.qf.ttshop.dto;

import java.util.List;

/**
 * easyui的表格分页的响应参数封装
 * User: DHC
 * Date: 2018/1/2
 * Time: 14:39
 * Version:V1.0
 */
public class Result<T> {
    private Long total;
    private List<T> rows;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
