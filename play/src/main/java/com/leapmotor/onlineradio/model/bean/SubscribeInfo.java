package com.leapmotor.onlineradio.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.leapmotor.onlineradio.ResType;

/**
 * good programmer.
 *
 * @date : 2023-07-13 19:58
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class SubscribeInfo implements Parcelable {

    public static final Parcelable.Creator<SubscribeInfo> CREATOR = new Parcelable.Creator<SubscribeInfo>() {
        @Override
        public SubscribeInfo createFromParcel(Parcel source) {
            return new SubscribeInfo(source);
        }

        @Override
        public SubscribeInfo[] newArray(int size) {
            return new SubscribeInfo[size];
        }
    };
    /**
     * 订阅的专辑、电台、在线广播等id
     */
    @SerializedName("id")
    private long id;
    /**
     * 订阅的专辑、电台、在线广播等名称
     */
    @SerializedName("name")
    private String name;
    /**
     * 订阅的资源类型。0：专辑，3：AI电台，5：单曲，11：在线广播，12：听电视
     */
    @SerializedName("type")
    private int type;
    /**
     * 封面图片url
     */
    @SerializedName("img")
    private String img;
    /**
     * 最新更新时间，时间戳，毫秒
     */
    @SerializedName("updateTime")
    private long updateTime;
    /**
     * 更新期数
     */
    @SerializedName("newNum")
    private int newNum;
    /**
     * 最新节目的标题
     */
    @SerializedName("newTitle")
    private String newTitle;
    @Deprecated
    /** 一直为0，不需要关心*/
    @SerializedName("updateNum")
    private int updateNum;
    /**
     * 是否在线，1表示在线
     */
    @SerializedName("isOnline")
    private int isOnline;
    @Deprecated
    /** 是否有版权，1表示有*/
    @SerializedName("hasCopyright")
    private int hasCopyright;
    @Deprecated
    /** 专辑更新时间*/
    @SerializedName("time")
    private String time;
    /**
     * 描述
     */
    @SerializedName("desc")
    private String desc;
    /**
     * 专辑总期数
     */
    @SerializedName("countNum")
    private int countNum;
    /**
     * 主持人名称
     */
    @SerializedName("comperes")
    private String comperes;
    @SerializedName("freq")
    private String freq;
    @SerializedName("playCount")
    private int playCount;
    /**
     * 是否vip 1:是,0:否
     */
    @SerializedName("vip")
    private int vip;
    /**
     * 是否精品 1:是,0:否
     */
    @SerializedName("fine")
    private int fine;
    @SerializedName("createdTime")
    private Long createdTime;
    @Deprecated
    @SerializedName("albumName")
    private String albumName;
    /**
     * 订阅量
     */
    @SerializedName("subscribeCount")
    private long subscribeCount;
    /**
     * 广播/听电视当前正在播放节目名
     */
    @SerializedName("currentProgram")
    private long currentProgram;


    public SubscribeInfo() {
    }

    protected SubscribeInfo(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.type = in.readInt();
        this.img = in.readString();
        this.updateTime = in.readLong();
        this.newNum = in.readInt();
        this.newTitle = in.readString();
        this.updateNum = in.readInt();
        this.isOnline = in.readInt();
        this.hasCopyright = in.readInt();
        this.time = in.readString();
        this.desc = in.readString();
        this.countNum = in.readInt();
        this.comperes = in.readString();
        fine = in.readInt();
        vip = in.readInt();
        playCount = in.readInt();
        albumName = in.readString();
        freq = in.readString();
        subscribeCount = in.readLong();
        currentProgram = in.readLong();
    }

    public SubscribeInfo(long id, String name, int type, String img, long updateTime, int newNum, String newTitle,
                         int updateNum, int isOnline, int hasCopyright, String time, String desc, int countNum,
                         String comperes, String freq, int playCount, int vip, int fine, Long createdTime, String albumName,
                         long subscribeCount, long currentProgram) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.img = img;
        this.updateTime = updateTime;
        this.newNum = newNum;
        this.newTitle = newTitle;
        this.updateNum = updateNum;
        this.isOnline = isOnline;
        this.hasCopyright = hasCopyright;
        this.time = time;
        this.desc = desc;
        this.countNum = countNum;
        this.comperes = comperes;
        this.freq = freq;
        this.playCount = playCount;
        this.vip = vip;
        this.fine = fine;
        this.createdTime = createdTime;
        this.albumName = albumName;
        this.subscribeCount = subscribeCount;
        this.currentProgram = currentProgram;
    }

    /**
     * 获取对应的{@link ResType}类型，与其进行类型统一。
     *
     * @return
     */
    public int getResType() {//0：专辑，3：AI电台，5：单曲，11：在线广播 12：听电视 13：专题
        switch (type) {
            case 5:
                return ResType.TYPE_AUDIO;
            case 0:
                return ResType.TYPE_ALBUM;
            case 3:
                return ResType.TYPE_RADIO;
            case 11:
                return ResType.TYPE_BROADCAST;
            case 12:
                return ResType.TYPE_TV;
            case 13:
                return ResType.TYPE_FEATURE;
            default:
        }
        return type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getNewNum() {
        return newNum;
    }

    public void setNewNum(int newNum) {
        this.newNum = newNum;
    }

    public String getNewTitle() {
        return newTitle;
    }

    public void setNewTitle(String newTitle) {
        this.newTitle = newTitle;
    }

    public int getUpdateNum() {
        return updateNum;
    }

    public void setUpdateNum(int updateNum) {
        this.updateNum = updateNum;
    }

    public int getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(int isOnline) {
        this.isOnline = isOnline;
    }

    public int getHasCopyright() {
        return hasCopyright;
    }

    public void setHasCopyright(int hasCopyright) {
        this.hasCopyright = hasCopyright;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCountNum() {
        return countNum;
    }

    public void setCountNum(int countNum) {
        this.countNum = countNum;
    }

    public String getComperes() {
        return comperes;
    }

    public void setComperes(String comperes) {
        this.comperes = comperes;
    }

    public String getFreq() {
        return freq;
    }

    public void setFreq(String freq) {
        this.freq = freq;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Long getSubscribeCount() {
        return subscribeCount;
    }

    public void setSubscribeCount(Long subscribeCount) {
        this.subscribeCount = subscribeCount;
    }

    public void setSubscribeCount(long subscribeCount) {
        this.subscribeCount = subscribeCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.type);
        dest.writeString(this.img);
        dest.writeLong(this.updateTime);
        dest.writeInt(this.newNum);
        dest.writeString(this.newTitle);
        dest.writeInt(this.updateNum);
        dest.writeInt(this.isOnline);
        dest.writeInt(this.hasCopyright);
        dest.writeString(this.time);
        dest.writeString(this.desc);
        dest.writeInt(this.countNum);
        dest.writeString(this.comperes);
        dest.writeInt(fine);
        dest.writeInt(vip);
        dest.writeInt(playCount);
        dest.writeString(albumName);
        dest.writeString(freq);
        dest.writeLong(subscribeCount);
        dest.writeLong(currentProgram);
    }

    @Override
    public String toString() {
        return "SubscribeInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", img='" + img + '\'' +
                ", updateTime=" + updateTime +
                ", newNum=" + newNum +
                ", newTitle='" + newTitle + '\'' +
                ", updateNum=" + updateNum +
                ", isOnline=" + isOnline +
                ", hasCopyright=" + hasCopyright +
                ", time='" + time + '\'' +
                ", desc='" + desc + '\'' +
                ", countNum=" + countNum +
                ", comperes='" + comperes + '\'' +
                ", freq='" + freq + '\'' +
                ", playCount=" + playCount +
                ", vip=" + vip +
                ", fine=" + fine +
                ", createdTime=" + createdTime +
                ", albumName='" + albumName + '\'' +
                ", subscribeCount=" + subscribeCount +
                ", currentProgram=" + currentProgram +
                '}';
    }

    public long getCurrentProgram() {
        return this.currentProgram;
    }


    public void setCurrentProgram(long currentProgram) {
        this.currentProgram = currentProgram;
    }
}