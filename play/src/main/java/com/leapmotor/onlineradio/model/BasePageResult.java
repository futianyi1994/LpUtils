package com.leapmotor.onlineradio.model;

import com.google.gson.annotations.SerializedName;

/**
 * good programmer.
 *
 * @date : 2023-07-13 19:57
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class BasePageResult<T> {

    /**
     * haveNext : 0 是否有下一页 1表示有下一页，0表示没有
     * nextPage : 1 下一页页数
     * havePre : 0 是否有上一页 1表示有，0表示没有
     * prePage : 1 上一页页码
     * currentPage : 1 当前页码
     * count : 8 总数
     * sumPage : 1 总页数
     * pageSize : 30 每页个数
     */

    @SerializedName("haveNext")
    private int haveNext;

    @SerializedName("nextPage")
    private int nextPage;

    @SerializedName("havePre")
    private int havePre;

    @SerializedName("prePage")
    private int prePage;

    @SerializedName("currentPage")
    private int currentPage;

    @SerializedName("count")
    private int count;

    @SerializedName("sumPage")
    private int sumPage;

    @SerializedName("pageSize")
    private int pageSize;

    @SerializedName("dataList")
    private T dataList;

    public int getHaveNext() {
        return haveNext;
    }

    public void setHaveNext(int haveNext) {
        this.haveNext = haveNext;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getHavePre() {
        return havePre;
    }

    public void setHavePre(int havePre) {
        this.havePre = havePre;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSumPage() {
        return sumPage;
    }

    public void setSumPage(int sumPage) {
        this.sumPage = sumPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public T getDataList() {
        return dataList;
    }

    public void setDataList(T dataList) {
        this.dataList = dataList;
    }
}