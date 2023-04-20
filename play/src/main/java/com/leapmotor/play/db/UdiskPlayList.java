package com.leapmotor.play.db;

import android.os.Parcel;
import android.os.Parcelable;

import com.leapmotor.play.annotation.MediaType;

/**
 * good programmer.
 *
 * @date : 2020-04-26 14:01
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class UdiskPlayList implements Parcelable {
    public static final Creator CREATOR = new Creator() {

        @Override
        public UdiskPlayList createFromParcel(Parcel source) {
            // 必须按成员变量声明的顺序读取数据，不然会出现获取数据出错
            UdiskPlayList playList = new UdiskPlayList();
            playList.setPlayListId(source.readLong());
            playList.setPlayUrl(source.readString());
            playList.setFilename(source.readString());
            playList.setExtname(source.readString());
            playList.setBitrate(source.readInt());
            playList.setFilesize(source.readInt());
            playList.setTimelength(source.readLong());
            playList.setTitle(source.readString());
            playList.setImagUrl(source.createByteArray());
            playList.setSingername(source.readString());
            playList.setLike(source.readByte() != 0);
            playList.setMediaType(source.readInt());
            return playList;
        }

        @Override
        public UdiskPlayList[] newArray(int size) {
            return new UdiskPlayList[size];
        }
    };
    private long playListId;
    /**
     * 播放地址
     */
    private String playUrl;
    /**
     * music name ("singername - title")
     */
    private String filename;
    /**
     * 歌曲后缀名
     */
    private String extname;
    /**
     * 歌曲比特率
     */
    private int bitrate;
    /**
     * 文件大小
     */
    private int filesize;
    /**
     * 歌曲时长ms
     */
    private long timelength;
    /**
     * 歌曲名
     */
    private String title;
    /**
     * 歌曲图片
     */
    private byte[] imagUrl;
    /**
     * 歌手名
     */
    private String singername;
    /**
     * 是否喜欢
     */
    private boolean isLike;
    /**
     * 多媒体类型
     */
    @MediaType
    private int mediaType = MediaType.TYPE_USB;

    /**
     * 自定义属性：是否正在播放
     */
    private boolean isPlaying;

    public UdiskPlayList() {
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

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getExtname() {
        return extname;
    }

    public void setExtname(String extname) {
        this.extname = extname;
    }

    public int getBitrate() {
        return bitrate;
    }

    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }

    public int getFilesize() {
        return filesize;
    }

    public void setFilesize(int filesize) {
        this.filesize = filesize;
    }

    public long getTimelength() {
        return timelength;
    }

    public void setTimelength(long timelength) {
        this.timelength = timelength;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getImagUrl() {
        return imagUrl;
    }

    public void setImagUrl(byte[] imagUrl) {
        this.imagUrl = imagUrl;
    }

    public String getSingername() {
        return singername;
    }

    public void setSingername(String singername) {
        this.singername = singername;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    @MediaType
    public int getMediaType() {
        return mediaType;
    }

    public void setMediaType(@MediaType int mediaType) {
        this.mediaType = mediaType;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    @Override
    public String toString() {
        return "UdiskPlayList{" +
                "playListId=" + playListId +
                ", playUrl='" + playUrl + '\'' +
                ", filename='" + filename + '\'' +
                ", extname='" + extname + '\'' +
                ", bitrate=" + bitrate +
                ", filesize=" + filesize +
                ", timelength=" + timelength +
                ", title='" + title + '\'' +
                ", imagUrl is null=" + (imagUrl == null) + '\'' +
                ", singername='" + singername + '\'' +
                ", isLike=" + isLike + '\'' +
                ", mediaType=" + mediaType + '\'' +
                ", isPlaying=" + isPlaying +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(playListId);
        dest.writeString(playUrl);
        dest.writeString(filename);
        dest.writeString(extname);
        dest.writeInt(bitrate);
        dest.writeInt(filesize);
        dest.writeLong(timelength);
        dest.writeString(title);
        dest.writeByteArray(imagUrl);
        dest.writeString(singername);
        dest.writeByte(this.isLike ? (byte) 1 : (byte) 0);
        dest.writeInt(mediaType);
    }

}
