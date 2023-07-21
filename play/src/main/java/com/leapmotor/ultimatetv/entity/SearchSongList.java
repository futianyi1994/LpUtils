package com.leapmotor.ultimatetv.entity;

import java.util.List;

/**
 * good programmer.
 *
 * @date : 2023-07-21 15:22
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class SearchSongList extends SongList implements IPage {
    public String keyword;

    public SearchSongList() {
    }

    @Override
    public List<Song> getList() {
        return super.list;
    }

    @Override
    public void setList(List<Song> list) {
        super.list = list;
    }

    @Override
    public int getPage() {
        return super.page;
    }

    @Override
    public void setPage(int page) {
        super.page = page;
    }

    @Override
    public int getPagesize() {
        return super.pagesize;
    }

    @Override
    public void setPagesize(int pagesize) {
        super.pagesize = pagesize;
    }

    @Override
    public int getTotal() {
        return super.total;
    }

    @Override
    public void setTotal(int total) {
        super.total = total;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public int getListSize() {
        List var1;
        return (var1 = super.list) != null ? var1.size() : 0;
    }

    public String toString() {
        return "SearchSongList{page=" + super.page + ", pagesize=" + super.pagesize + ", total=" + super.total + ", keyword='" + this.keyword + '\'' + ", list=" + super.list + '}';
    }
}