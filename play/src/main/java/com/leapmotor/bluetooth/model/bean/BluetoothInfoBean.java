package com.leapmotor.bluetooth.model.bean;

import com.leapmotor.play.annotation.PlayState;

/**
 * good programmer.
 *
 * @date : 2020-06-09 16:52
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class BluetoothInfoBean {
    @PlayState
    private int state = PlayState.NONE;
    private String artist;
    private String album;
    private String name;
    private int duration;
    private String albumArtUri;

    public BluetoothInfoBean(@PlayState int state, String artist, String album, String name, int duration, String albumArtUri) {
        this.state = state;
        this.artist = artist;
        this.album = album;
        this.name = name;
        this.duration = duration;
        this.albumArtUri = albumArtUri;
    }

    @PlayState
    public int getState() {
        return state;
    }

    public void setState(@PlayState int state) {
        this.state = state;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getAlbumArtUri() {
        return albumArtUri;
    }

    public void setAlbumArtUri(String albumArtUri) {
        this.albumArtUri = albumArtUri;
    }

    @Override
    public String toString() {
        return "BluetoothInfoBean{" +
                "state=" + state +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", albumArtUri='" + albumArtUri + '\'' +
                '}';
    }
}
