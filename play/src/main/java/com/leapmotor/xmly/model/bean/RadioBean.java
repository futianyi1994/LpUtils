package com.leapmotor.xmly.model.bean;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * good programmer.
 *
 * @date : 2020-12-01 11:03
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class RadioBean implements Parcelable {

    public static final Creator<RadioBean> CREATOR = new Creator<RadioBean>() {
        @Override
        public RadioBean createFromParcel(Parcel source) {
            return new RadioBean(source);
        }

        @Override
        public RadioBean[] newArray(int size) {
            return new RadioBean[size];
        }
    };
    /**
     * id : 95
     * kind : radio
     * name : 北京⾳乐⼴播
     * intro : null
     * area : null
     * playing_program_id : 166698
     * playing_program_name : 永恒的魅⼒
     * support_bitrates : null
     * play_count : 4734683
     * small_cover_url : http://fdfs.xmcdn.com/group29/M0B/2D/15/wKgJWVkxP7wIPZUAABRqWHVL_g511_mobile_small.jpg
     * large_cover_url : http://fdfs.xmcdn.com/group29/M0B/2D/15/wKgJWVkxP7wIPZUAABRqWHVL_g511_mobile_large.jpg
     * updated_at : 1551111608000
     * fm : null
     * play_url : {"aac24":"http://live.xmcdn.com/live/95/24.m3u8","aac64":"http://live.xmcdn.com/live/95/64.m3u8","ts24":"http://live.xmcdn.com/live/95/24.m3u8?transcode=ts","ts64":"http://live.xmcdn.com/live/95/64.m3u8?transcode=ts"}
     */
    private long id;
    private String kind;
    private String name;
    private String intro;
    private String area;
    private long playing_program_id;
    private String playing_program_name;
    private List<Integer> support_bitrates;
    private long play_count;
    private String small_cover_url;
    private String large_cover_url;
    private long updated_at;
    private String fm;
    private PlayUrlBean play_url;

    public RadioBean() {
    }

    protected RadioBean(Parcel in) {
        this.id = in.readLong();
        this.kind = in.readString();
        this.name = in.readString();
        this.intro = in.readString();
        this.area = in.readString();
        this.playing_program_id = in.readLong();
        this.playing_program_name = in.readString();
        this.support_bitrates = new ArrayList<Integer>();
        in.readList(this.support_bitrates, int.class.getClassLoader());
        this.play_count = in.readLong();
        this.small_cover_url = in.readString();
        this.large_cover_url = in.readString();
        this.updated_at = in.readLong();
        this.fm = in.readString();
        this.play_url = in.readParcelable(PlayUrlBean.class.getClassLoader());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public long getPlaying_program_id() {
        return playing_program_id;
    }

    public void setPlaying_program_id(long playing_program_id) {
        this.playing_program_id = playing_program_id;
    }

    public String getPlaying_program_name() {
        return playing_program_name;
    }

    public void setPlaying_program_name(String playing_program_name) {
        this.playing_program_name = playing_program_name;
    }

    public List<Integer> getSupport_bitrates() {
        return support_bitrates;
    }

    public void setSupport_bitrates(List<Integer> support_bitrates) {
        this.support_bitrates = support_bitrates;
    }

    public long getPlay_count() {
        return play_count;
    }

    public void setPlay_count(long play_count) {
        this.play_count = play_count;
    }

    public String getSmall_cover_url() {
        return small_cover_url;
    }

    public void setSmall_cover_url(String small_cover_url) {
        this.small_cover_url = small_cover_url;
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

    public String getFm() {
        return fm;
    }

    public void setFm(String fm) {
        this.fm = fm;
    }

    public PlayUrlBean getPlay_url() {
        return play_url;
    }

    public void setPlay_url(PlayUrlBean play_url) {
        this.play_url = play_url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.kind);
        dest.writeString(this.name);
        dest.writeString(this.intro);
        dest.writeString(this.area);
        dest.writeLong(this.playing_program_id);
        dest.writeString(this.playing_program_name);
        dest.writeList(this.support_bitrates);
        dest.writeLong(this.play_count);
        dest.writeString(this.small_cover_url);
        dest.writeString(this.large_cover_url);
        dest.writeLong(this.updated_at);
        dest.writeString(this.fm);
        dest.writeParcelable(this.play_url, flags);
    }
}
