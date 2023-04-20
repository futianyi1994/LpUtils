package com.leapmotor.play.db;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * good programmer.
 *
 * @date : 2020-04-26 14:01
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class UltimatetvPlayList implements Parcelable {
    public static final Creator<UltimatetvPlayList> CREATOR = new Creator<UltimatetvPlayList>() {
        @Override
        public UltimatetvPlayList createFromParcel(Parcel source) {
            return new UltimatetvPlayList(source);
        }

        @Override
        public UltimatetvPlayList[] newArray(int size) {
            return new UltimatetvPlayList[size];
        }
    };
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
    @SerializedName("is_vip_song")
    public int isVipSong;
    @SerializedName("try_playable")
    public int tryPlayable;
    public String language;
    public long duration;
    @SerializedName("topic_url")
    public String topicUrl;
    @SerializedName("highest_quality")
    public String highestQuality;
    @SerializedName("formSource")
    public String formSource;
    @SerializedName("from_source_id")
    public String fromSourceId;
    /**
     * 以下字段为SongInfo传入
     */
    public int songSize;
    public int songSizeHq;
    public int songSizeSq;
    public int songSizePq;
    public boolean isTryListen;
    public int trySize;
    public int tryBeginPos;
    public int tryEndPos;
    public List<Integer> supportQualities;
    public List<Integer> songSupportQuality;

    private int id;
    /**
     * 自定义字段：排序用
     */
    private long sort;
    /**
     * 自定义字段：是否喜欢
     */
    @SerializedName("like")
    private boolean isLike = true;
    /**
     * 自定义字段：是否播放
     */
    private boolean isPlaying;
    /**
     * 自定义字段：我喜欢的自建歌单id
     */
    private String playListId;

    public UltimatetvPlayList() {
    }

    public UltimatetvPlayList(String playlistId) {
        setPlayListId(playlistId);
    }


    protected UltimatetvPlayList(Parcel in) {
        this.id = in.readInt();
        this.sort = in.readLong();
        this.isLike = in.readByte() != 0;
        this.isPlaying = in.readByte() != 0;
        this.playListId = in.readString();
        this.songId = in.readString();
        this.songName = in.readString();
        this.singerId = in.readString();
        this.singerName = in.readString();
        this.singerImg = in.readString();
        this.albumId = in.readString();
        this.albumName = in.readString();
        this.albumImg = in.readString();
        this.albumImgMini = in.readString();
        this.albumImgSmall = in.readString();
        this.albumImgMedium = in.readString();
        this.albumImgLarge = in.readString();
        this.songExtraId = in.readString();
        this.mvId = in.readString();
        this.hasAccompany = in.readInt();
        this.playableCode = in.readInt();
        this.isVipSong = in.readInt();
        this.tryPlayable = in.readInt();
        this.language = in.readString();
        this.duration = in.readLong();
        this.topicUrl = in.readString();
        this.highestQuality = in.readString();
        this.formSource = in.readString();
        this.fromSourceId = in.readString();

        this.songSize = in.readInt();
        this.songSizeHq = in.readInt();
        this.songSizeSq = in.readInt();
        this.songSizePq = in.readInt();
        this.isTryListen = in.readByte() != 0;
        this.trySize = in.readInt();
        this.tryBeginPos = in.readInt();
        this.tryEndPos = in.readInt();
        this.supportQualities = new ArrayList<>();
        in.readList(this.supportQualities, Integer.class.getClassLoader());
        this.songSupportQuality = new ArrayList<>();
        in.readList(this.songSupportQuality, Integer.class.getClassLoader());
    }

    public void resetId() {
        setId(0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getSort() {
        return sort;
    }

    public void setSort(long sort) {
        this.sort = sort;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public String getPlayListId() {
        return playListId;
    }

    public void setPlayListId(String playListId) {
        this.playListId = playListId;
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

    public String getSingerId() {
        return singerId;
    }

    public void setSingerId(String singerId) {
        this.singerId = singerId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getSingerImg() {
        return singerImg;
    }

    public void setSingerImg(String singerImg) {
        this.singerImg = singerImg;
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

    public String getAlbumImg() {
        return albumImg;
    }

    public void setAlbumImg(String albumImg) {
        this.albumImg = albumImg;
    }

    public String getAlbumImgMini() {
        return albumImgMini;
    }

    public void setAlbumImgMini(String albumImgMini) {
        this.albumImgMini = albumImgMini;
    }

    public String getAlbumImgSmall() {
        return albumImgSmall;
    }

    public void setAlbumImgSmall(String albumImgSmall) {
        this.albumImgSmall = albumImgSmall;
    }

    public String getAlbumImgMedium() {
        return albumImgMedium;
    }

    public void setAlbumImgMedium(String albumImgMedium) {
        this.albumImgMedium = albumImgMedium;
    }

    public String getAlbumImgLarge() {
        return albumImgLarge;
    }

    public void setAlbumImgLarge(String albumImgLarge) {
        this.albumImgLarge = albumImgLarge;
    }

    public String getSongExtraId() {
        return songExtraId;
    }

    public void setSongExtraId(String songExtraId) {
        this.songExtraId = songExtraId;
    }

    public String getMvId() {
        return mvId;
    }

    public void setMvId(String mvId) {
        this.mvId = mvId;
    }

    public int getHasAccompany() {
        return hasAccompany;
    }

    public void setHasAccompany(int hasAccompany) {
        this.hasAccompany = hasAccompany;
    }

    public int getPlayableCode() {
        return playableCode;
    }

    public void setPlayableCode(int playableCode) {
        this.playableCode = playableCode;
    }

    public int getIsVipSong() {
        return isVipSong;
    }

    public void setIsVipSong(int isVipSong) {
        this.isVipSong = isVipSong;
    }

    public int getTryPlayable() {
        return tryPlayable;
    }

    public void setTryPlayable(int tryPlayable) {
        this.tryPlayable = tryPlayable;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getTopicUrl() {
        return topicUrl;
    }

    public void setTopicUrl(String topicUrl) {
        this.topicUrl = topicUrl;
    }

    public String getHighestQuality() {
        return highestQuality;
    }

    public void setHighestQuality(String highestQuality) {
        this.highestQuality = highestQuality;
    }

    public String getFormSource() {
        return formSource;
    }

    public void setFormSource(String formSource) {
        this.formSource = formSource;
    }

    public String getFromSourceId() {
        return fromSourceId;
    }

    public void setFromSourceId(String fromSourceId) {
        this.fromSourceId = fromSourceId;
    }

    public int getSongSize() {
        return songSize;
    }

    public void setSongSize(int songSize) {
        this.songSize = songSize;
    }

    public int getSongSizeHq() {
        return songSizeHq;
    }

    public void setSongSizeHq(int songSizeHq) {
        this.songSizeHq = songSizeHq;
    }

    public int getSongSizeSq() {
        return songSizeSq;
    }

    public void setSongSizeSq(int songSizeSq) {
        this.songSizeSq = songSizeSq;
    }

    public int getSongSizePq() {
        return songSizePq;
    }

    public void setSongSizePq(int songSizePq) {
        this.songSizePq = songSizePq;
    }

    public boolean isTryListen() {
        return isTryListen;
    }

    public void setTryListen(boolean tryListen) {
        isTryListen = tryListen;
    }

    public int getTrySize() {
        return trySize;
    }

    public void setTrySize(int trySize) {
        this.trySize = trySize;
    }

    public int getTryBeginPos() {
        return tryBeginPos;
    }

    public void setTryBeginPos(int tryBeginPos) {
        this.tryBeginPos = tryBeginPos;
    }

    public int getTryEndPos() {
        return tryEndPos;
    }

    public void setTryEndPos(int tryEndPos) {
        this.tryEndPos = tryEndPos;
    }

    public List<Integer> getSupportQualities() {
        return supportQualities;
    }

    public void setSupportQualities(List<Integer> supportQualities) {
        this.supportQualities = supportQualities;
    }

    public List<Integer> getSongSupportQuality() {
        return songSupportQuality;
    }

    public void setSongSupportQuality(List<Integer> songSupportQuality) {
        this.songSupportQuality = songSupportQuality;
    }

    @NonNull
    @Override
    public String toString() {
        return "UltimatetvLikeSongList{" +
                "id=" + id + '\'' +
                "sort=" + sort + '\'' +
                ", isLike=" + isLike + '\'' +
                ", isPlaying=" + isPlaying + '\'' +
                ", playListId='" + playListId + '\'' +
                ", songId='" + songId + '\'' +
                ", songName='" + songName + '\'' +
                ", singerId='" + singerId + '\'' +
                ", singerName='" + singerName + '\'' +
                ", singerImg='" + singerImg + '\'' +
                ", albumId='" + albumId + '\'' +
                ", albumName='" + albumName + '\'' +
                ", albumImg='" + albumImg + '\'' +
                ", albumImgMini='" + albumImgMini + '\'' +
                ", albumImgSmall='" + albumImgSmall + '\'' +
                ", albumImgMedium='" + albumImgMedium + '\'' +
                ", albumImgLarge='" + albumImgLarge + '\'' +
                ", songExtraId='" + songExtraId + '\'' +
                ", mvId='" + mvId + '\'' +
                ", hasAccompany=" + hasAccompany + '\'' +
                ", playableCode=" + playableCode + '\'' +
                ", isVipSong=" + isVipSong + '\'' +
                ", tryPlayable=" + tryPlayable + '\'' +
                ", language='" + language + '\'' +
                ", duration=" + duration + '\'' +
                ", topicUrl='" + topicUrl + '\'' +
                ", highestQuality='" + highestQuality + '\'' +
                ", formSource='" + formSource + '\'' +
                ", fromSourceId='" + fromSourceId + '\'' +
                ", songSize=" + songSize + '\'' +
                ", songSizeHq=" + songSizeHq + '\'' +
                ", songSizeSq=" + songSizeSq + '\'' +
                ", songSizePq=" + songSizePq + '\'' +
                ", isTryListen=" + isTryListen + '\'' +
                ", trySize=" + trySize + '\'' +
                ", tryBeginPos=" + tryBeginPos + '\'' +
                ", tryEndPos=" + tryEndPos + '\'' +
                ", supportQualities=" + supportQualities + '\'' +
                ", songSupportQuality=" + songSupportQuality + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeLong(this.sort);
        dest.writeByte(this.isLike ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isPlaying ? (byte) 1 : (byte) 0);
        dest.writeString(this.playListId);
        dest.writeString(this.songId);
        dest.writeString(this.songName);
        dest.writeString(this.singerId);
        dest.writeString(this.singerName);
        dest.writeString(this.singerImg);
        dest.writeString(this.albumId);
        dest.writeString(this.albumName);
        dest.writeString(this.albumImg);
        dest.writeString(this.albumImgMini);
        dest.writeString(this.albumImgSmall);
        dest.writeString(this.albumImgMedium);
        dest.writeString(this.albumImgLarge);
        dest.writeString(this.songExtraId);
        dest.writeString(this.mvId);
        dest.writeInt(this.hasAccompany);
        dest.writeInt(this.playableCode);
        dest.writeInt(this.isVipSong);
        dest.writeInt(this.tryPlayable);
        dest.writeString(this.language);
        dest.writeLong(this.duration);
        dest.writeString(this.topicUrl);
        dest.writeString(this.highestQuality);
        dest.writeString(this.formSource);
        dest.writeString(this.fromSourceId);
        dest.writeInt(this.songSize);
        dest.writeInt(this.songSizeHq);
        dest.writeInt(this.songSizeSq);
        dest.writeInt(this.songSizePq);
        dest.writeByte(this.isTryListen ? (byte) 1 : (byte) 0);
        dest.writeInt(this.trySize);
        dest.writeInt(this.tryBeginPos);
        dest.writeInt(this.tryEndPos);
        dest.writeList(this.supportQualities);
        dest.writeList(this.songSupportQuality);
    }
}
