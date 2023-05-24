package com.leapmotor.ultimatetv.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * good programmer.
 *
 * @date : 2023-05-22 19:18
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class SongList implements IPage {
    @SerializedName("songs")
    public List<Song> list;
    public int page;
    public int pagesize;
    public int total;

    public SongList() {
    }

    public List<Song> getList() {
        return this.list;
    }

    public void setList(List<Song> list) {
        this.list = list;
    }

    public int getPage() {
        return this.page;
    }

    @Override
    public void setPage(int page) {
        this.page = page;
    }

    public int getPagesize() {
        return this.pagesize;
    }

    @Override
    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public int getListSize() {
        List var1;
        return (var1 = this.list) != null ? var1.size() : 0;
    }

    public String toString() {
        return "SongList{page=" + this.page + ", pageSize=" + this.pagesize + ", total=" + this.total + ", list=" + this.list + '}';
    }
}
