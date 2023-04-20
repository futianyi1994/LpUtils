package com.leapmotor.xmly.model.bean;

import java.util.List;

/**
 * good programmer.
 *
 * @date : 2020-12-01 11:00
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class AlbumPayBean extends AlbumFullBean {
    private boolean is_vip_free;
    private boolean is_vip_only;
    private int price_type_id;
    private int free_track_count;
    private double qualify_score;
    private boolean is_authorized;
    private List<PriceInfosBean> price_infos;

    public boolean getIs_vip_free() {
        return is_vip_free;
    }

    public void setIs_vip_free(boolean is_vip_free) {
        this.is_vip_free = is_vip_free;
    }

    public boolean getIs_vip_only() {
        return is_vip_only;
    }

    public void setIs_vip_only(boolean is_vip_only) {
        this.is_vip_only = is_vip_only;
    }

    public int getPrice_type_id() {
        return price_type_id;
    }

    public void setPrice_type_id(int price_type_id) {
        this.price_type_id = price_type_id;
    }

    public int getFree_track_count() {
        return free_track_count;
    }

    public void setFree_track_count(int free_track_count) {
        this.free_track_count = free_track_count;
    }

    public double getQualify_score() {
        return qualify_score;
    }

    public void setQualify_score(double qualify_score) {
        this.qualify_score = qualify_score;
    }

    public boolean isIs_authorized() {
        return is_authorized;
    }

    public void setIs_authorized(boolean is_authorized) {
        this.is_authorized = is_authorized;
    }

    public List<PriceInfosBean> getPrice_infos() {
        return price_infos;
    }

    public void setPrice_infos(List<PriceInfosBean> price_infos) {
        this.price_infos = price_infos;
    }
}
