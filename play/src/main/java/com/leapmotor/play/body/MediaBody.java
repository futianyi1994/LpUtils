package com.leapmotor.play.body;


import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.leapmotor.play.annotation.MediaType;
import com.leapmotor.play.annotation.PlayState;

public class MediaBody implements Cloneable, Parcelable {
    public static final Creator<MediaBody> CREATOR = new Creator<MediaBody>() {
        @Override
        public MediaBody createFromParcel(Parcel in) {
            return new MediaBody(in);
        }

        @Override
        public MediaBody[] newArray(int size) {
            return new MediaBody[size];
        }
    };
    private static final String TAG = "MediaBody";
    /**
     * 多媒体的音源
     */
    @MediaType
    private int type = MediaType.TYPE_CURRENT;
    /**
     * {@link AlbumBody#uniqueId}
     * PlayList的唯一值:
     * MediaType.TYPE_ONLINE: hash值；
     * MediaType.TYPE_ULTIMATETV: songId值；
     * MediaType.TYPE_BLUETOOTH: 为蓝牙音乐得album（歌名）值；
     * MediaType.TYPE_XMLY: 为声音或广播的id值；
     * MediaType.TYPE_FM: 为Fm频道值；
     * MediaType.TYPE_USB: U盘音乐为本地地址
     * MediaType.TYPE_ULTIMATE_MV: mvId值；
     * MediaType.TYPE_ONLINE_RADIO: audioId值；
     * MediaType.TYPE_LP_RADIO: songId值；
     */
    private String uniqueId;
    /**
     * 专辑id（只有喜马拉雅和酷狗和在线广播和零跑电台有此字段）
     */
    private String albumId;
    /**
     * 歌曲名称（收音机时为频道）
     */
    private String name;
    /**
     * 标题名称（酷狗音乐和U盘音乐时为fileName字段:歌手 - 歌名；蓝牙音乐时为album字段:歌名；其他时同name）
     */
    private String title;
    /**
     * 艺术家名称（收音机和喜马拉雅无此字段；零跑电台该字段为isLpRadioTimeProgram的值标识是否是零跑电台的节目单歌曲）
     */
    private String artist;
    /**
     * 封面图片路径及url，无图时为默认图资源id
     */
    private String imagUrl;
    /**
     * 时长秒（在线广播、收音机时为0）
     */
    private int duration;
    /**
     * 音乐播放器的状态
     * 0:暂停;1:播放2:停止
     */
    @PlayState
    private int state = PlayState.NONE;

    public MediaBody() {
    }

    public MediaBody(@MediaType int type, String name, String title, String artist, String imagUrl, int duration, @PlayState int state, String uniqueId, String albumId) {
        this.type = type;
        this.name = name == null ? "" : name;
        this.title = title == null ? "" : title;
        this.artist = artist == null ? "" : artist;
        this.imagUrl = imagUrl == null ? "" : imagUrl;
        this.duration = duration;
        this.state = state;
        this.uniqueId = uniqueId == null ? "" : uniqueId;
        this.albumId = albumId == null ? "" : albumId;
    }

    protected MediaBody(Parcel in) {
        type = in.readInt();
        uniqueId = in.readString();
        albumId = in.readString();
        name = in.readString();
        title = in.readString();
        artist = in.readString();
        imagUrl = in.readString();
        duration = in.readInt();
        state = in.readInt();
    }

    @MediaType
    public int getType() {
        return type;
    }

    public void setType(@MediaType int type) {
        this.type = type;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getImagUrl() {
        return imagUrl;
    }

    public void setImagUrl(String imagUrl) {
        this.imagUrl = imagUrl;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @PlayState
    public int getState() {
        return state;
    }

    public void setState(@PlayState int state) {
        this.state = state;
    }

    @Override
    public MediaBody clone() {
        MediaBody mediaBody = null;
        try {
            mediaBody = (MediaBody) super.clone();
        } catch (CloneNotSupportedException e) {
            Log.e(TAG, "MediaBody CloneNotSupportedException : " + e);
        }
        return mediaBody;
    }

    @Override
    public String toString() {
        return "MediaBody{" +
                "type=" + type +
                ", uniqueId='" + uniqueId + '\'' +
                ", albumId='" + albumId + '\'' +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", imagUrl='" + imagUrl + '\'' +
                ", duration=" + duration +
                ", state=" + state +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(type);
        dest.writeString(uniqueId);
        dest.writeString(albumId);
        dest.writeString(name);
        dest.writeString(title);
        dest.writeString(artist);
        dest.writeString(imagUrl);
        dest.writeInt(duration);
        dest.writeInt(state);
    }
}
