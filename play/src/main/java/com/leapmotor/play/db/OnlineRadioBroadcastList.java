package com.leapmotor.play.db;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.Map;

/**
 * good programmer.
 *
 * @date : 2022/5/26 9:31
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class OnlineRadioBroadcastList implements Parcelable {

    public static final Creator<OnlineRadioBroadcastList> CREATOR = new Creator<OnlineRadioBroadcastList>() {
        @Override
        public OnlineRadioBroadcastList createFromParcel(Parcel in) {
            return new OnlineRadioBroadcastList(in);
        }

        @Override
        public OnlineRadioBroadcastList[] newArray(int size) {
            return new OnlineRadioBroadcastList[size];
        }
    };
    private int id;
    /**
     * 分类成员的code值，用于获取子分类成员。该值是可变的
     */
    private String code;
    /**
     * 标题名
     */
    private String title;
    /**
     * 副标题
     */
    private String subtitle;
    /**
     * 描述
     */
    private String description;
    /**
     * SDK内部使用，开发者不需要关心，会一直为空
     */
    private String type;
    /**
     * icon imageFile
     */
    private String iconImageFileJson;
    /**
     * cover imageFile
     */
    private String coverImageFilesJson;

    /**
     * 以下字段为BroadcastCategoryMember传入
     */
    /**
     * 额外信息，用于一些定制需求
     */
    private Map<String, String> extInfo;
    /**
     * 在线广播资源id
     */
    private long broadcastId;
    /**
     * 在线广播的收听数
     */
    private int playTimes;
    /**
     * 频道
     */
    private String freq;

    public OnlineRadioBroadcastList() {
    }

    protected OnlineRadioBroadcastList(Parcel in) {
        id = in.readInt();
        code = in.readString();
        title = in.readString();
        subtitle = in.readString();
        description = in.readString();
        type = in.readString();
        iconImageFileJson = in.readString();
        coverImageFilesJson = in.readString();
        in.readMap(extInfo, Map.class.getClassLoader());
        broadcastId = in.readLong();
        playTimes = in.readInt();
        freq = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIconImageFileJson() {
        return iconImageFileJson;
    }

    public void setIconImageFileJson(String iConImageFileJson) {
        this.iconImageFileJson = iConImageFileJson;
    }

    public String getCoverImageFilesJson() {
        return coverImageFilesJson;
    }

    public void setCoverImageFilesJson(String coverImageFilesJson) {
        this.coverImageFilesJson = coverImageFilesJson;
    }

    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }

    public long getBroadcastId() {
        return broadcastId;
    }

    public void setBroadcastId(long broadcastId) {
        this.broadcastId = broadcastId;
    }

    public int getPlayTimes() {
        return playTimes;
    }

    public void setPlayTimes(int playTimes) {
        this.playTimes = playTimes;
    }

    public String getFreq() {
        return freq;
    }

    public void setFreq(String freq) {
        this.freq = freq;
    }

    @Override
    public String toString() {
        return "OnlineRadioBroadcastList{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", iConImageFileJson='" + iconImageFileJson + '\'' +
                ", coverImageFilesJson='" + coverImageFilesJson + '\'' +
                ", extInfo=" + extInfo +
                ", broadcastId=" + broadcastId +
                ", playTimes=" + playTimes +
                ", freq='" + freq + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(code);
        dest.writeString(title);
        dest.writeString(subtitle);
        dest.writeString(description);
        dest.writeString(type);
        dest.writeString(iconImageFileJson);
        dest.writeString(coverImageFilesJson);
        dest.writeMap(extInfo);
        dest.writeLong(broadcastId);
        dest.writeInt(playTimes);
        dest.writeString(freq);
    }
}