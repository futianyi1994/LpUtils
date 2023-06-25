package com.leapmotor.lpradio.model.bean;

/**
 * good programmer.
 *
 * @date : 2022-09-08 17:11
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class SongBean {
    private String songId;
    private String songName;
    private String songLogo;
    private String songUrl;
    /**
     * 状态：1上架；0下架
     */
    private String status;
    private String duration;
    /**
     * 以下字段可能需要自己设置（只有getProgramListByTime()接口有返回）
     */
    private String albumId;
    private String albumName;
    private String albumLogo;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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
    public String toString() {
        return "SongBean{" +
                "songId='" + songId + '\'' +
                ", songName='" + songName + '\'' +
                ", songLogo='" + songLogo + '\'' +
                ", songUrl='" + songUrl + '\'' +
                ", status='" + status + '\'' +
                ", duration='" + duration + '\'' +
                ", albumId='" + albumId + '\'' +
                ", albumName='" + albumName + '\'' +
                ", albumLogo='" + albumLogo + '\'' +
                '}';
    }
}
