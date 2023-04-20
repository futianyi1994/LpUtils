package com.leapmotor.play.db.onlineradio;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * good programmer.
 *
 * @date : 2022-05-23 16:16
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class InfoData implements Parcelable {
    public static final Creator<InfoData> CREATOR = new Creator<InfoData>() {
        @Override
        public InfoData createFromParcel(Parcel source) {
            return new InfoData(source);
        }

        @Override
        public InfoData[] newArray(int size) {
            return new InfoData[size];
        }
    };
    private String title;
    private long albumId;
    private String audioPic;
    private int dataSrc;
    private String icon;
    private String audioDes;
    private String albumPic;
    private String albumName;
    private int orderNum;
    private String hosts;
    private int isLiked;
    private String updateTime;
    private long createTime;
    private String sourceLogo;
    private String sourceName;

    public InfoData() {
    }

    public InfoData(Parcel in) {
        this.title = in.readString();
        this.albumId = in.readLong();
        this.audioPic = in.readString();
        this.dataSrc = in.readInt();
        this.icon = in.readString();
        this.audioDes = in.readString();
        this.albumPic = in.readString();
        this.albumName = in.readString();
        this.orderNum = in.readInt();
        this.hosts = in.readString();
        this.isLiked = in.readInt();
        this.updateTime = in.readString();
        this.createTime = in.readLong();
        this.sourceLogo = in.readString();
        this.sourceName = in.readString();
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getAlbumId() {
        return this.albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public String getAudioPic() {
        return this.audioPic;
    }

    public void setAudioPic(String audioPic) {
        this.audioPic = audioPic;
    }

    public int getDataSrc() {
        return this.dataSrc;
    }

    public void setDataSrc(int dataSrc) {
        this.dataSrc = dataSrc;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAudioDes() {
        return this.audioDes;
    }

    public void setAudioDes(String audioDes) {
        this.audioDes = audioDes;
    }

    public String getAlbumPic() {
        return this.albumPic;
    }

    public void setAlbumPic(String albumPic) {
        this.albumPic = albumPic;
    }

    public String getAlbumName() {
        return this.albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getOrderNum() {
        return this.orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getHosts() {
        return this.hosts;
    }

    public void setHosts(String hosts) {
        this.hosts = hosts;
    }

    public int getIsLiked() {
        return this.isLiked;
    }

    public void setIsLiked(int isLiked) {
        this.isLiked = isLiked;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getSourceLogo() {
        return this.sourceLogo;
    }

    public void setSourceLogo(String sourceLogo) {
        this.sourceLogo = sourceLogo;
    }

    public String getSourceName() {
        return this.sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    @Override
    public String toString() {
        return "InfoData{" +
                "title='" + title + '\'' +
                ", albumId=" + albumId +
                ", audioPic='" + audioPic + '\'' +
                ", dataSrc=" + dataSrc +
                ", icon='" + icon + '\'' +
                ", audioDes='" + audioDes + '\'' +
                ", albumPic='" + albumPic + '\'' +
                ", albumName='" + albumName + '\'' +
                ", orderNum=" + orderNum +
                ", hosts='" + hosts + '\'' +
                ", isLiked=" + isLiked +
                ", updateTime='" + updateTime + '\'' +
                ", createTime=" + createTime +
                ", sourceLogo='" + sourceLogo + '\'' +
                ", sourceName='" + sourceName + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeLong(this.albumId);
        dest.writeString(this.audioPic);
        dest.writeInt(this.dataSrc);
        dest.writeString(this.icon);
        dest.writeString(this.audioDes);
        dest.writeString(this.albumPic);
        dest.writeString(this.albumName);
        dest.writeInt(this.orderNum);
        dest.writeString(this.hosts);
        dest.writeInt(this.isLiked);
        dest.writeString(this.updateTime);
        dest.writeLong(this.createTime);
        dest.writeString(this.sourceLogo);
        dest.writeString(this.sourceName);
    }
}
