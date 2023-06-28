package com.leapmotor.ultimatetv.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * good programmer.
 *
 * @date : 2023-06-28 17:28
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class PlaylistList implements IPage {
    public int page;
    public int pagesize;
    public int total;
    public String session;
    @SerializedName("playlists")
    public List<Playlist> list;

    public PlaylistList() {
    }

    public List<Playlist> getList() {
        return this.list;
    }

    public void setList(List<Playlist> list) {
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

    public String getSession() {
        return this.session;
    }

    @Override
    public int getListSize() {
        List var1;
        return (var1 = this.list) != null ? var1.size() : 0;
    }

    public String toString() {
        return "PlaylistList{page=" + this.page + ", pagesize=" + this.pagesize + ", total=" + this.total + ", list=" + this.list + ", session=" + this.session + '}';
    }
}
