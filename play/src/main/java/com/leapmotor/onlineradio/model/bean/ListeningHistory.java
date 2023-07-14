package com.leapmotor.onlineradio.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * good programmer.
 *
 * @date : 2023-07-13 20:05
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class ListeningHistory implements Parcelable {

    /**
     * type : 3
     * radioId : 1200000000162
     * radioTitle : 搞笑电台
     * picUrl : http://img.kaolafm.net/mz/images/201806/ce809597-bcc7-4f05-868f-12aedbf29354/default.jpg
     * audioId : 1000024031379
     * audioTitle : 路上捡到个美女
     * playUrl : http://image.kaolafm.net/mz/aac_64/201902/2818789d-e6b7-429c-a4ab-d997ab28580d.aac
     * playedTime : 319350
     * duration : 359000
     * orderNum : 2270
     * shareUrl : http://m.kaolafm.com/share/jm.html?audioId=1000024031379
     * status : 1
     * createTime : 1552036635000
     * updateTime : 1552042666000
     * timeStamp : 1591944881581
     */

    public static final Creator<ListeningHistory> CREATOR = new Creator<ListeningHistory>() {
        @Override
        public ListeningHistory createFromParcel(Parcel in) {
            return new ListeningHistory(in);
        }

        @Override
        public ListeningHistory[] newArray(int size) {
            return new ListeningHistory[size];
        }
    };
    /**
     * 单曲Id
     */
    private String audioId;
    /**
     * 单曲标题
     */
    private String audioTitle;
    /**
     * 创建时间
     */
    private long createTime;
    /**
     * 单曲时长
     */
    private int duration;
    /**
     * 期数
     */
    private int orderNum;
    /**
     * 图片地址
     */
    private String picUrl;
    /**
     * 播放地址
     */
    private String playUrl;
    /**
     * 已播时长
     */
    private long playedTime;
    /**
     * 电台/专辑 id
     */
    private String radioId;
    /**
     * 电台标题
     */
    private String radioTitle;
    @Deprecated
    /** 分享链接 */
    private String shareUrl;
    @Deprecated
    /** 节目状态 */
    private int status;
    /**
     * 节目类型 0专辑，1单曲，3电台，11在线广播，12：听电视
     */
    private int type;
    @Deprecated
    /** 更新时间 */
    private long updateTime;
    /**
     * 时间戳
     */
    private long timeStamp;
    /**
     * 是否需要付费 1:是,0:否
     */
    private int fine;
    /**
     * 是否vip 1:是,0:否
     */
    private int vip;
    /**
     * 是否在线 1:在线,0:已经下架
     */
    private int online;
    /**
     * 广播频率
     */
    private String freq;
    /**
     * 广播内容类型（音乐，交通，新闻等）
     */
    private int broadcastSort;
    /**
     * 播放量
     */
    @SerializedName("listenCount")
    private long listenCount;
    /**
     * 内部使用，不对外提供
     * 用于判断是否需要断点续播
     */
    private String paramOne;
    /**
     * 内部使用，不对外提供
     * 预留字段，暂无用途
     */
    private String paramTwo;

    public ListeningHistory() {
    }

    protected ListeningHistory(Parcel in) {
        audioId = in.readString();
        audioTitle = in.readString();
        createTime = in.readLong();
        duration = in.readInt();
        orderNum = in.readInt();
        picUrl = in.readString();
        playUrl = in.readString();
        playedTime = in.readLong();
        radioId = in.readString();
        radioTitle = in.readString();
        shareUrl = in.readString();
        status = in.readInt();
        type = in.readInt();
        updateTime = in.readLong();
        timeStamp = in.readLong();
        fine = in.readInt();
        vip = in.readInt();
        online = in.readInt();
        freq = in.readString();
        broadcastSort = in.readInt();
        listenCount = in.readLong();
        paramOne = in.readString();
        paramTwo = in.readString();
    }

    public ListeningHistory(String audioId, String audioTitle, long createTime, int duration,
                            int orderNum, String picUrl, String playUrl, long playedTime, String radioId,
                            String radioTitle, String shareUrl, int status, int type, long updateTime, long timeStamp,
                            int fine, int vip, int online, String freq, int broadcastSort, long listenCount,
                            String paramOne, String paramTwo) {
        this.audioId = audioId;
        this.audioTitle = audioTitle;
        this.createTime = createTime;
        this.duration = duration;
        this.orderNum = orderNum;
        this.picUrl = picUrl;
        this.playUrl = playUrl;
        this.playedTime = playedTime;
        this.radioId = radioId;
        this.radioTitle = radioTitle;
        this.shareUrl = shareUrl;
        this.status = status;
        this.type = type;
        this.updateTime = updateTime;
        this.timeStamp = timeStamp;
        this.fine = fine;
        this.vip = vip;
        this.online = online;
        this.freq = freq;
        this.broadcastSort = broadcastSort;
        this.listenCount = listenCount;
        this.paramOne = paramOne;
        this.paramTwo = paramTwo;
    }

    public String getAudioId() {
        return audioId;
    }

    public void setAudioId(String audioId) {
        this.audioId = audioId;
    }

    public String getAudioTitle() {
        return audioTitle;
    }

    public void setAudioTitle(String audioTitle) {
        this.audioTitle = audioTitle;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public long getPlayedTime() {
        return playedTime;
    }

    public void setPlayedTime(long playedTime) {
        this.playedTime = playedTime;
    }

    public String getRadioId() {
        return radioId;
    }

    public void setRadioId(String radioId) {
        this.radioId = radioId;
    }

    public String getRadioTitle() {
        return radioTitle;
    }

    public void setRadioTitle(String radioTitle) {
        this.radioTitle = radioTitle;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public String getFreq() {
        return freq;
    }

    public void setFreq(String freq) {
        this.freq = freq;
    }

    public int getBroadcastSort() {
        return broadcastSort;
    }

    public void setBroadcastSort(int broadcastSort) {
        this.broadcastSort = broadcastSort;
    }

    public long getListenCount() {
        return listenCount;
    }

    public void setListenCount(long listenCount) {
        this.listenCount = listenCount;
    }

    public String getParamOne() {
        return paramOne;
    }

    public void setParamOne(String paramOne) {
        this.paramOne = paramOne;
    }

    public String getParamTwo() {
        return paramTwo;
    }

    public void setParamTwo(String paramTwo) {
        this.paramTwo = paramTwo;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(audioId);
        dest.writeString(audioTitle);
        dest.writeLong(createTime);
        dest.writeInt(duration);
        dest.writeInt(orderNum);
        dest.writeString(picUrl);
        dest.writeString(playUrl);
        dest.writeLong(playedTime);
        dest.writeString(radioId);
        dest.writeString(radioTitle);
        dest.writeString(shareUrl);
        dest.writeInt(status);
        dest.writeInt(type);
        dest.writeLong(updateTime);
        dest.writeLong(timeStamp);
        dest.writeInt(fine);
        dest.writeInt(vip);
        dest.writeInt(online);
        dest.writeString(freq);
        dest.writeInt(broadcastSort);
        dest.writeLong(listenCount);
        dest.writeString(paramOne);
        dest.writeString(paramTwo);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "ListeningHistory{" +
                "audioId='" + audioId + '\'' +
                ", audioTitle='" + audioTitle + '\'' +
                ", createTime=" + createTime +
                ", duration=" + duration +
                ", orderNum=" + orderNum +
                ", picUrl='" + picUrl + '\'' +
                ", playUrl='" + playUrl + '\'' +
                ", playedTime=" + playedTime +
                ", radioId='" + radioId + '\'' +
                ", radioTitle='" + radioTitle + '\'' +
                ", shareUrl='" + shareUrl + '\'' +
                ", status=" + status +
                ", type=" + type +
                ", updateTime=" + updateTime +
                ", timeStamp=" + timeStamp +
                ", fine=" + fine +
                ", vip=" + vip +
                ", online=" + online +
                ", freq='" + freq + '\'' +
                ", broadcastSort=" + broadcastSort +
                ", listenCount=" + listenCount +
                ", paramOne='" + paramOne + '\'' +
                ", paramTwo='" + paramTwo + '\'' +
                '}';
    }
}