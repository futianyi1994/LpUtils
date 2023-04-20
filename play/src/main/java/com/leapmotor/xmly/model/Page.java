package com.leapmotor.xmly.model;


import java.util.List;

/**
 * good programmer.
 *
 * @date : 2019-01-23 上午 11:51
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class Page<T> extends ErrorResponse {
    /**
     * total : 7
     * offset : 0
     * limit : 5
     * sort : null
     * items : []
     */
    /**
     * 可返回的数据的总项数
     */
    private int total;
    /**
     * 从第⼏项开始返回数据
     */
    private int offset;
    /**
     * ⼀次响应返回的最⼤项数
     */
    private int limit;
    /**
     * 排序类型名
     */
    private String sort;
    private List<T> items;

    public Page() {
    }

    public Page(Throwable e) {
        super(e);
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
