package com.leapmotor.ultimatetv.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * good programmer.
 *
 * @date : 2023-06-28 17:28
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class Playlist implements Serializable {
    public static final transient long serialVersionUID = 3150154003736294428L;
    @SerializedName("playlist_id")
    public String playlistId;
    @SerializedName("playlist_name")
    public String playlistName;
    @SerializedName("pic")
    public String picImg;
    @SerializedName("playlist_extra_id")
    public String playlistExtraId;
    @SerializedName("update_time")
    public String updateTime;
    @SerializedName("create_time")
    public String createTime;
    public int total;
    public String intro;
    public String tags;
    @SerializedName("play_count")
    public int playCount;
    @SerializedName("author_name")
    public String authorName;
    @SerializedName("song_count")
    public String songCount;

    public Playlist() {
    }

    public String getAuthorName() {
        return this.authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPlaylistId() {
        return this.playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistName() {
        return this.playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getPicImg() {
        return this.picImg;
    }

    public void setPicImg(String picImg) {
        this.picImg = picImg;
    }

    public String getPlaylistExtraId() {
        return this.playlistExtraId;
    }

    public void setPlaylistExtraId(String playlistExtraId) {
        this.playlistExtraId = playlistExtraId;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getIntro() {
        return this.intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTags() {
        return this.tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getPlayCount() {
        return this.playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public String getSongCount() {
        return this.songCount;
    }

    public void setSongCount(String songCount) {
        this.songCount = songCount;
    }

    public String toString() {
        return "Playlist{authorName='" + this.authorName + '\'' + ", playlistId='" + this.playlistId + '\'' + ", playlistName='" + this.playlistName + '\'' + ", picImg='" + this.picImg + '\'' + ", playlistExtraId='" + this.playlistExtraId + '\'' + ", updateTime='" + this.updateTime + '\'' + ", createTime='" + this.createTime + '\'' + ", total=" + this.total + ", playCount=" + this.playCount + ", intro=‘" + this.intro + '\'' + ", tags='" + this.tags + '\'' + ", songCount='" + this.songCount + '\'' + '}';
    }
}
