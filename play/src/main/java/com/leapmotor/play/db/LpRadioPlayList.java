package com.leapmotor.play.db;

import android.os.Parcel;
import android.os.Parcelable;

import com.leapmotor.play.annotation.MediaType;


/**
 * good programmer.
 *
 * @date : 2022/8/30 11:15
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class LpRadioPlayList implements Parcelable {
    public static final Creator<LpRadioPlayList> CREATOR = new Creator<LpRadioPlayList>() {
        @Override
        public LpRadioPlayList createFromParcel(Parcel source) {
            return new LpRadioPlayList(source);
        }

        @Override
        public LpRadioPlayList[] newArray(int size) {
            return new LpRadioPlayList[size];
        }
    };
    private long playListId;
    /**
     * 多媒体类型
     */
    @MediaType
    private int mediaType = MediaType.TYPE_CURRENT;
    /**
     * 是否正在播放
     */
    private boolean isPlaying;
    /**
     * 是否是零跑电台时段节目
     */
    private boolean isLpRadioTimeProgram;
    /**
     * 节目所在时间段
     * {@link com.leapmotor.lpradio.model.bean.ProgramListTimeBean.DataBean#time}
     */
    private String time;
    /**
     * lpRadio单曲列表
     */
    /**
     * 单曲id
     */
    private String songId;
    /**
     * 单曲名称
     */
    private String songName;
    /**
     * 单曲封面
     */
    private String songLogo;
    /**
     * 单曲播放地址
     */
    private String songUrl;
    /**
     * 声音时长(秒)
     */
    private int duration;
    /**
     * 状态：1上架 0下架
     */
    private String status;
    /**
     * 专辑id
     */
    private String albumId;
    /**
     * 专辑名称
     */
    private String albumName;
    /**
     * 专辑图片
     */
    private String albumLogo;

    /************************************************************************************************/
    public LpRadioPlayList() {
    }

    protected LpRadioPlayList(Parcel in) {
        this.playListId = in.readLong();
        this.mediaType = in.readInt();
        this.isPlaying = in.readByte() != 0;
        this.isLpRadioTimeProgram = in.readByte() != 0;
        this.time = in.readString();

        this.songId = in.readString();
        this.songName = in.readString();
        this.songLogo = in.readString();
        this.songUrl = in.readString();
        this.duration = in.readInt();
        this.status = in.readString();
        this.albumId = in.readString();
        this.albumName = in.readString();
        this.albumLogo = in.readString();
    }

    public static Creator<LpRadioPlayList> getCREATOR() {
        return CREATOR;
    }

    public void resetPlayListId() {
        setPlayListId(0);
    }

    public long getPlayListId() {
        return playListId;
    }

    public void setPlayListId(long playListId) {
        this.playListId = playListId;
    }

    public int getMediaType() {
        return mediaType;
    }

    public void setMediaType(int mediaType) {
        this.mediaType = mediaType;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public boolean isLpRadioTimeProgram() {
        return isLpRadioTimeProgram;
    }

    public void setLpRadioTimeProgram(boolean lpRadioTimeProgram) {
        isLpRadioTimeProgram = lpRadioTimeProgram;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongLogo() {
        return songLogo;
    }

    public void setSongLogo(String songLogo) {
        this.songLogo = songLogo;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumLogo() {
        return albumLogo;
    }

    public void setAlbumLogo(String albumLogo) {
        this.albumLogo = albumLogo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.playListId);
        dest.writeInt(this.mediaType);
        dest.writeByte(this.isPlaying ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isLpRadioTimeProgram ? (byte) 1 : (byte) 0);
        dest.writeString(this.time);

        dest.writeString(this.songId);
        dest.writeString(this.songName);
        dest.writeString(this.songLogo);
        dest.writeString(this.songUrl);
        dest.writeInt(this.duration);
        dest.writeString(this.status);
        dest.writeString(this.albumId);
        dest.writeString(this.albumName);
        dest.writeString(this.albumLogo);
    }
}
