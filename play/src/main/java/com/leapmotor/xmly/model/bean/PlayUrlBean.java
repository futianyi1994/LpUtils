package com.leapmotor.xmly.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * good programmer.
 *
 * @date : 2021-03-16 14:05
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class PlayUrlBean implements Parcelable {
    public static final Creator<PlayUrlBean> CREATOR = new Creator<PlayUrlBean>() {
        @Override
        public PlayUrlBean createFromParcel(Parcel source) {
            return new PlayUrlBean(source);
        }

        @Override
        public PlayUrlBean[] newArray(int size) {
            return new PlayUrlBean[size];
        }
    };
    /**
     * aac24 : http://live.xmcdn.com/live/95/24.m3u8
     * aac64 : http://live.xmcdn.com/live/95/64.m3u8
     * ts24 : http://live.xmcdn.com/live/95/24.m3u8?transcode=ts
     * ts64 : http://live.xmcdn.com/live/95/64.m3u8?transcode=ts
     */
    private String aac24;
    private String aac64;
    private String ts24;
    private String ts64;

    public PlayUrlBean() {
    }

    protected PlayUrlBean(Parcel in) {
        this.aac24 = in.readString();
        this.aac64 = in.readString();
        this.ts24 = in.readString();
        this.ts64 = in.readString();
    }

    public String getAac24() {
        return aac24;
    }

    public void setAac24(String aac24) {
        this.aac24 = aac24;
    }

    public String getAac64() {
        return aac64;
    }

    public void setAac64(String aac64) {
        this.aac64 = aac64;
    }

    public String getTs24() {
        return ts24;
    }

    public void setTs24(String ts24) {
        this.ts24 = ts24;
    }

    public String getTs64() {
        return ts64;
    }

    public void setTs64(String ts64) {
        this.ts64 = ts64;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.aac24);
        dest.writeString(this.aac64);
        dest.writeString(this.ts24);
        dest.writeString(this.ts64);
    }
}
