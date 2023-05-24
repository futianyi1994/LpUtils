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
public class AlbumInfo extends SongList implements IPage {
    @SerializedName("album_id")
    public String albumId;
    @SerializedName("album_name")
    public String albumName;
    @SerializedName("album_translate_name")
    public String albumTranslateName;
    @SerializedName("album_img")
    public String albumImg;
    @SerializedName("album_img_large")
    public String albumImgLarge;
    public String intro;
    public String company;
    @SerializedName("singer_id")
    public String singerId;
    @SerializedName("singer_name")
    public String singerName;
    @SerializedName("publish_time")
    public String publishTime;
    @SerializedName("is_buy")
    public int isBuy;

    public AlbumInfo() {
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

    public String getAlbumId() {
        return this.albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return this.albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumTranslateName() {
        return this.albumTranslateName;
    }

    public void setAlbumTranslateName(String albumTranslateName) {
        this.albumTranslateName = albumTranslateName;
    }

    public String getAlbumImg() {
        return this.albumImg;
    }

    public void setAlbumImg(String albumImg) {
        this.albumImg = albumImg;
    }

    public String getAlbumImgLarge() {
        return this.albumImgLarge;
    }

    public void setAlbumImgLarge(String albumImgLarge) {
        this.albumImgLarge = albumImgLarge;
    }

    public String getIntro() {
        return this.intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSingerId() {
        return this.singerId;
    }

    public void setSingerId(String singerId) {
        this.singerId = singerId;
    }

    public String getSingerName() {
        return this.singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    @Override
    public int getTotal() {
        return super.total;
    }

    @Override
    public void setTotal(int total) {
        super.total = total;
    }

    public String getPublishTime() {
        return this.publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public int getIsBuy() {
        return this.isBuy;
    }

    public void setIsBuy(int isBuy) {
        this.isBuy = isBuy;
    }

    @Override
    public int getListSize() {
        List var1;
        return (var1 = super.list) != null ? var1.size() : 0;
    }

    public String toString() {
        return "AlbumInfo{page=" + super.page + ", pagesize=" + super.pagesize + ", albumId='" + this.albumId + '\'' + ", albumName='" + this.albumName + '\'' + ", albumTranslateName='" + this.albumTranslateName + '\'' + ", albumImg='" + this.albumImg + '\'' + ", albumImgLarge='" + this.albumImgLarge + '\'' + ", intro='" + this.intro + '\'' + ", company='" + this.company + '\'' + ", singerId='" + this.singerId + '\'' + ", singerName='" + this.singerName + '\'' + ", total=" + super.total + ", isBuy=" + this.isBuy + ", publishTime='" + this.publishTime + '\'' + ", list=" + super.list + '}';
    }
}