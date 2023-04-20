package com.leapmotor.xmly.model.bean;

/**
 * good programmer.
 *
 * @date : 2021-03-20 14:03
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class PriceInfosBean {
    /**
     * price_type_id : 2
     * price : 99.00
     * discounted_price : 99.00
     * price_unit : 喜点
     * free_track_ids : 97046088,97158632,98459679
     */

    private int price_type_id;
    private String price;
    private String discounted_price;
    private String price_unit;
    private String free_track_ids;
    private double platform_vip_price;
    private boolean is_has_platform_vip_discount;

    public int getPrice_type_id() {
        return price_type_id;
    }

    public void setPrice_type_id(int price_type_id) {
        this.price_type_id = price_type_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscounted_price() {
        return discounted_price;
    }

    public void setDiscounted_price(String discounted_price) {
        this.discounted_price = discounted_price;
    }

    public String getPrice_unit() {
        return price_unit;
    }

    public void setPrice_unit(String price_unit) {
        this.price_unit = price_unit;
    }

    public String getFree_track_ids() {
        return free_track_ids;
    }

    public void setFree_track_ids(String free_track_ids) {
        this.free_track_ids = free_track_ids;
    }

    public double getPlatform_vip_price() {
        return platform_vip_price;
    }

    public void setPlatform_vip_price(double platform_vip_price) {
        this.platform_vip_price = platform_vip_price;
    }

    public boolean isIs_has_platform_vip_discount() {
        return is_has_platform_vip_discount;
    }

    public void setIs_has_platform_vip_discount(boolean is_has_platform_vip_discount) {
        this.is_has_platform_vip_discount = is_has_platform_vip_discount;
    }
}
