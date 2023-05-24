package com.leapmotor.ultimatetv.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * good programmer.
 *
 * @date : 2023-05-22 19:20
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class Song implements Serializable {
    public static final transient long serialVersionUID = -311666732916477696L;
    @SerializedName("song_id")
    public String songId;
    @SerializedName("song_name")
    public String songName;
    @SerializedName("singer_id")
    public String singerId;
    @SerializedName("singer_name")
    public String singerName;
    @SerializedName("singer_img")
    public String singerImg;
    @SerializedName("album_id")
    public String albumId;
    @SerializedName("album_name")
    public String albumName;
    @SerializedName("album_sizable_img")
    public String albumSizableImg;
    @SerializedName("album_img")
    public String albumImg;
    @SerializedName("album_img_mini")
    public String albumImgMini;
    @SerializedName("album_img_small")
    public String albumImgSmall;
    @SerializedName("album_img_medium")
    public String albumImgMedium;
    @SerializedName("album_img_large")
    public String albumImgLarge;
    @SerializedName("song_extra_id")
    public String songExtraId;
    @SerializedName("mv_id")
    public String mvId = "-1";
    @SerializedName("has_accompany")
    public int hasAccompany = -1;
    @SerializedName("playable_code")
    public int playableCode;
    @SerializedName("free_token")
    public String freeToken;
    @SerializedName("is_vip_song")
    public int isVipSong;
    @SerializedName("try_playable")
    public int tryPlayable = -1;
    public String language;
    public int duration;
    @SerializedName("topic_url")
    public String topicUrl;
    @SerializedName("highest_quality")
    public String highestQuality;
    @SerializedName("support_quality")
    public String supportQuality;
    @SerializedName("formSource")
    public String formSource;
    @SerializedName("from_source_id")
    public String fromSourceId;
    @SerializedName("song_size")
    public long songSize;
    @SerializedName("song_size_hq")
    public long songSizeHq;
    @SerializedName("song_size_sq")
    public long songSizeSq;
    @SerializedName("try_begin")
    public long tryBegin;
    @SerializedName("try_end")
    public long tryEnd;

    public Song() {
    }

    public String getSingerImg() {
        return this.singerImg;
    }

    public void setSingerImg(String singerImg) {
        this.singerImg = singerImg;
    }

    public String getAlbumSizableImg() {
        return this.albumSizableImg;
    }

    public void setAlbumSizableImg(String albumSizableImg) {
        this.albumSizableImg = albumSizableImg;
    }

    public String getAlbumImg() {
        return this.albumImg;
    }

    public void setAlbumImg(String albumImg) {
        this.albumImg = albumImg;
    }

    public String getAlbumImgMini() {
        return this.albumImgMini;
    }

    public void setAlbumImgMini(String albumImgMini) {
        this.albumImgMini = albumImgMini;
    }

    public String getAlbumImgSmall() {
        return this.albumImgSmall;
    }

    public void setAlbumImgSmall(String albumImgSmall) {
        this.albumImgSmall = albumImgSmall;
    }

    public String getAlbumImgMedium() {
        return this.albumImgMedium;
    }

    public void setAlbumImgMedium(String albumImgMedium) {
        this.albumImgMedium = albumImgMedium;
    }

    public String getAlbumImgLarge() {
        return this.albumImgLarge;
    }

    public void setAlbumImgLarge(String albumImgLarge) {
        this.albumImgLarge = albumImgLarge;
    }

    public int getPlayableCode() {
        return this.playableCode;
    }

    public void setPlayableCode(int playableCode) {
        this.playableCode = playableCode;
    }

    public String getFreeToken() {
        return this.freeToken;
    }

    public void setFreeToken(String freeToken) {
        this.freeToken = freeToken;
    }

    public String getSongId() {
        return this.songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return this.songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
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

    public String getSongExtraId() {
        return this.songExtraId;
    }

    public void setSongExtraId(String songExtraId) {
        this.songExtraId = songExtraId;
    }

    public int getIsVipSong() {
        return this.isVipSong;
    }

    public void setIsVipSong(int isVipSong) {
        this.isVipSong = isVipSong;
    }

    public String getMvId() {
        return this.mvId;
    }

    public void setMvId(String mvId) {
        this.mvId = mvId;
    }

    public int getHasAccompany() {
        return this.hasAccompany;
    }

    public void setHasAccompany(int hasAccompany) {
        this.hasAccompany = hasAccompany;
    }

    public int getTryPlayable() {
        return this.tryPlayable;
    }

    public void setTryPlayable(int tryPlayable) {
        this.tryPlayable = tryPlayable;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTopicUrl() {
        return this.topicUrl;
    }

    public void setTopicUrl(String topicUrl) {
        this.topicUrl = topicUrl;
    }

    public String getHighestQuality() {
        return this.highestQuality;
    }

    public void setHighestQuality(String highestQuality) {
        this.highestQuality = highestQuality;
    }

    public String getSupportQuality() {
        return this.supportQuality;
    }

    public void setSupportQuality(String supportQuality) {
        this.supportQuality = supportQuality;
    }

    public String getFormSource() {
        return this.formSource;
    }

    public void setFormSource(String formSource) {
        this.formSource = formSource;
    }

    public String getFromSourceId() {
        return this.fromSourceId;
    }

    public void setFromSourceId(String fromSourceId) {
        this.fromSourceId = fromSourceId;
    }

    public long getSongSize() {
        return this.songSize;
    }

    public void setSongSize(long songSize) {
        this.songSize = songSize;
    }

    public long getSongSizeHq() {
        return this.songSizeHq;
    }

    public void setSongSizeHq(long songSizeHq) {
        this.songSizeHq = songSizeHq;
    }

    public long getSongSizeSq() {
        return this.songSizeSq;
    }

    public void setSongSizeSq(long songSizeSq) {
        this.songSizeSq = songSizeSq;
    }

    public long getTryBegin() {
        return this.tryBegin;
    }

    public void setTryBegin(long tryBegin) {
        this.tryBegin = tryBegin;
    }

    public long getTryEnd() {
        return this.tryEnd;
    }

    public void setTryEnd(long tryEnd) {
        this.tryEnd = tryEnd;
    }

    public String toString() {
        return "Song{playableCode=" + this.playableCode + ", songId='" + this.songId + '\'' + ", songName='" + this.songName + '\'' + ", singerId='" + this.singerId + '\'' + ", singerName='" + this.singerName + '\'' + ", albumId='" + this.albumId + '\'' + ", albumName='" + this.albumName + '\'' + ", songExtraId='" + this.songExtraId + '\'' + ", singerImg='" + this.singerImg + '\'' + ", albumSizableImg='" + this.albumSizableImg + '\'' + ", albumImg='" + this.albumImg + '\'' + ", albumImgMini='" + this.albumImgMini + '\'' + ", albumImgSmall='" + this.albumImgSmall + '\'' + ", albumImgMedium='" + this.albumImgMedium + '\'' + ", albumImgLarge='" + this.albumImgLarge + '\'' + ", mvId='" + this.mvId + '\'' + ", hasAccompany=" + this.hasAccompany + ", isVipSong=" + this.isVipSong + ", tryPlayable=" + this.tryPlayable + ", freeToken='" + this.freeToken + '\'' + ", language='" + this.language + '\'' + ", duration=" + this.duration + ", topicUrl='" + this.topicUrl + '\'' + ", highestQuality='" + this.highestQuality + '\'' + ", supportQuality='" + this.supportQuality + '\'' + ", formSource='" + this.formSource + '\'' + ", fromSourceId='" + this.fromSourceId + '\'' + ", songSize='" + this.songSize + '\'' + ", songSizeHq='" + this.songSizeHq + '\'' + ", songSizeSq='" + this.songSizeSq + '\'' + ", tryBegin='" + this.tryBegin + '\'' + ", tryEnd='" + this.tryEnd + '\'' + ", fromSourceId='" + this.fromSourceId + '\'' + '}';
    }
}
