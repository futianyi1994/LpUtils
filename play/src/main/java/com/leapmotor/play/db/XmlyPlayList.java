package com.leapmotor.play.db;

import android.os.Parcel;
import android.os.Parcelable;

import com.leapmotor.play.annotation.MediaType;
import com.leapmotor.play.annotation.PrivilegeType;


/**
 * good programmer.
 *
 * @date : 2020-04-26 14:01
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class XmlyPlayList implements Parcelable {
    public static final Creator<XmlyPlayList> CREATOR = new Creator<XmlyPlayList>() {
        @Override
        public XmlyPlayList createFromParcel(Parcel source) {
            return new XmlyPlayList(source);
        }

        @Override
        public XmlyPlayList[] newArray(int size) {
            return new XmlyPlayList[size];
        }
    };
    /**
     * 鉴权结果：0:免费;1:vip;2:付费;3:无版权
     * {@link PrivilegeType}
     */
    @PrivilegeType
    public int checkType = PrivilegeType.FREE;
    /**
     * 是否是试听歌曲0：听完整版；1：试听（只在鉴权获取播放链接时获取，并传递给客户端）
     */
    public int isFreeTry;
    private long playListId;
    /**
     * XmlyPlayList的唯一值喜马拉雅声音或广播的id值
     */
    private String uniqueId;
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
     * radio
     */
    private long radio_id;
    private long program_schedule_id;
    private long program_id;
    private String fm;
    /**
     * track
     */
    private long track_id;
    private long album_id;
    private boolean is_records_desc;
    private boolean is_subscribe;
    private boolean is_favourite;
    private boolean is_paid;
    private boolean is_authorized;
    private boolean is_free;
    private boolean is_vip_free;
    private boolean is_vip_onley;
    private int vip_first_status;
    private String rec_src;
    private String rec_track;
    /**
     * xmly common use
     */
    private String kind;
    private String playUrl;
    private int duration;
    private String title;
    private String small_cover_url;
    private String middle_cover_url;
    private String large_cover_url;
    private long updated_at;
    /**
     * 自定义属性播放鉴权结果：0:免费;1:vip;2:付费;3:无版权
     * {@link PrivilegeType}
     */
    @PrivilegeType
    private int checkPlayType = PrivilegeType.FREE;

    /************************************************************************************************/
    public XmlyPlayList() {
    }

    protected XmlyPlayList(Parcel in) {
        this.playListId = in.readLong();
        this.uniqueId = in.readString();
        this.mediaType = in.readInt();
        this.isPlaying = in.readByte() != 0;

        this.radio_id = in.readLong();
        this.program_schedule_id = in.readLong();
        this.program_id = in.readLong();
        this.fm = in.readString();

        this.track_id = in.readLong();
        this.album_id = in.readLong();
        this.is_records_desc = in.readByte() != 0;
        this.is_subscribe = in.readByte() != 0;
        this.is_favourite = in.readByte() != 0;
        this.is_paid = in.readByte() != 0;
        this.is_authorized = in.readByte() != 0;
        this.is_free = in.readByte() != 0;
        this.is_vip_free = in.readByte() != 0;
        this.is_vip_onley = in.readByte() != 0;
        this.vip_first_status = in.readInt();
        this.rec_src = in.readString();
        this.rec_track = in.readString();

        this.kind = in.readString();
        this.playUrl = in.readString();
        this.duration = in.readInt();
        this.title = in.readString();
        this.small_cover_url = in.readString();
        this.middle_cover_url = in.readString();
        this.large_cover_url = in.readString();
        this.updated_at = in.readLong();
        this.checkType = in.readInt();
        this.checkPlayType = in.readInt();
        this.isFreeTry = in.readInt();
    }

    public static Creator<XmlyPlayList> getCREATOR() {
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

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
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

    public long getRadio_id() {
        return radio_id;
    }

    public void setRadio_id(long radio_id) {
        this.radio_id = radio_id;
    }

    public long getProgram_schedule_id() {
        return program_schedule_id;
    }

    public void setProgram_schedule_id(long program_schedule_id) {
        this.program_schedule_id = program_schedule_id;
    }

    public long getProgram_id() {
        return program_id;
    }

    public void setProgram_id(long program_id) {
        this.program_id = program_id;
    }

    public String getFm() {
        return fm;
    }

    public void setFm(String fm) {
        this.fm = fm;
    }

    public long getTrack_id() {
        return track_id;
    }

    public void setTrack_id(long track_id) {
        this.track_id = track_id;
    }

    public long getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(long album_id) {
        this.album_id = album_id;
    }

    public boolean isIs_records_desc() {
        return is_records_desc;
    }

    public void setIs_records_desc(boolean is_records_desc) {
        this.is_records_desc = is_records_desc;
    }

    public boolean isIs_subscribe() {
        return is_subscribe;
    }

    public void setIs_subscribe(boolean is_subscribe) {
        this.is_subscribe = is_subscribe;
    }

    public boolean isIs_favourite() {
        return is_favourite;
    }

    public void setIs_favourite(boolean is_favourite) {
        this.is_favourite = is_favourite;
    }

    public boolean isIs_paid() {
        return is_paid;
    }

    public void setIs_paid(boolean is_paid) {
        this.is_paid = is_paid;
    }

    public boolean isIs_authorized() {
        return is_authorized;
    }

    public void setIs_authorized(boolean is_authorized) {
        this.is_authorized = is_authorized;
    }

    public boolean isIs_free() {
        return is_free;
    }

    public void setIs_free(boolean is_free) {
        this.is_free = is_free;
    }

    public boolean isIs_vip_free() {
        return is_vip_free;
    }

    public void setIs_vip_free(boolean is_vip_free) {
        this.is_vip_free = is_vip_free;
    }

    public boolean isIs_vip_onley() {
        return is_vip_onley;
    }

    public void setIs_vip_onley(boolean is_vip_onley) {
        this.is_vip_onley = is_vip_onley;
    }

    public int getVip_first_status() {
        return vip_first_status;
    }

    public void setVip_first_status(int vip_first_status) {
        this.vip_first_status = vip_first_status;
    }

    public String getRec_src() {
        return rec_src;
    }

    public void setRec_src(String rec_src) {
        this.rec_src = rec_src;
    }

    public String getRec_track() {
        return rec_track;
    }

    public void setRec_track(String rec_track) {
        this.rec_track = rec_track;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSmall_cover_url() {
        return small_cover_url;
    }

    public void setSmall_cover_url(String small_cover_url) {
        this.small_cover_url = small_cover_url;
    }

    public String getMiddle_cover_url() {
        return middle_cover_url;
    }

    public void setMiddle_cover_url(String middle_cover_url) {
        this.middle_cover_url = middle_cover_url;
    }

    public String getLarge_cover_url() {
        return large_cover_url;
    }

    public void setLarge_cover_url(String large_cover_url) {
        this.large_cover_url = large_cover_url;
    }

    public long getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(long updated_at) {
        this.updated_at = updated_at;
    }

    @PrivilegeType
    public int getCheckType() {
        return checkType;
    }

    public void setCheckType(@PrivilegeType int checkType) {
        this.checkType = checkType;
    }

    @PrivilegeType
    public int getCheckPlayType() {
        return checkPlayType;
    }

    public void setCheckPlayType(@PrivilegeType int checkPlayType) {
        this.checkPlayType = checkPlayType;
    }

    public int getIsFreeTry() {
        return isFreeTry;
    }

    public void setIsFreeTry(int isFreeTry) {
        this.isFreeTry = isFreeTry;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.playListId);
        dest.writeString(this.uniqueId);
        dest.writeInt(this.mediaType);
        dest.writeByte(this.isPlaying ? (byte) 1 : (byte) 0);

        dest.writeLong(this.radio_id);
        dest.writeLong(this.program_schedule_id);
        dest.writeLong(this.program_id);
        dest.writeString(this.fm);

        dest.writeLong(this.track_id);
        dest.writeLong(this.album_id);
        dest.writeByte(this.is_records_desc ? (byte) 1 : (byte) 0);
        dest.writeByte(this.is_subscribe ? (byte) 1 : (byte) 0);
        dest.writeByte(this.is_favourite ? (byte) 1 : (byte) 0);
        dest.writeByte(this.is_paid ? (byte) 1 : (byte) 0);
        dest.writeByte(this.is_authorized ? (byte) 1 : (byte) 0);
        dest.writeByte(this.is_free ? (byte) 1 : (byte) 0);
        dest.writeByte(this.is_vip_free ? (byte) 1 : (byte) 0);
        dest.writeByte(this.is_vip_onley ? (byte) 1 : (byte) 0);
        dest.writeInt(this.vip_first_status);
        dest.writeString(this.rec_src);
        dest.writeString(this.rec_track);

        dest.writeString(this.kind);
        dest.writeString(this.playUrl);
        dest.writeInt(this.duration);
        dest.writeString(this.title);
        dest.writeString(this.small_cover_url);
        dest.writeString(this.middle_cover_url);
        dest.writeString(this.large_cover_url);
        dest.writeLong(this.updated_at);
        dest.writeInt(checkType);
        dest.writeInt(checkPlayType);
        dest.writeInt(isFreeTry);
    }
}
