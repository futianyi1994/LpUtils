package com.leapmotor.xmly.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * good programmer.
 *
 * @date : 2021-03-16 14:12
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class AnnouncerBean implements Parcelable {
    public static final Creator<AnnouncerBean> CREATOR = new Creator<AnnouncerBean>() {
        @Override
        public AnnouncerBean createFromParcel(Parcel source) {
            return new AnnouncerBean(source);
        }

        @Override
        public AnnouncerBean[] newArray(int size) {
            return new AnnouncerBean[size];
        }
    };
    /**
     * id : 102403331
     * nickname : 封⾯新闻
     * avatar_url : https://imagev2.xmcdn.com/group31/M05/AE/BC/wKgJSVpVWEehNz2YAABUGZUcTNw947.jpg!op_type=3&columns=110&rows=110
     * is_verified : true
     * url : null
     * popularity : 0
     * tags : null
     */
    private int id;
    private String nickname;
    private String avatar_url;
    private boolean is_verified;
    private String url;
    private int popularity;
    private String tags;

    public AnnouncerBean() {
    }

    protected AnnouncerBean(Parcel in) {
        this.id = in.readInt();
        this.nickname = in.readString();
        this.avatar_url = in.readString();
        this.is_verified = in.readByte() != 0;
        this.url = in.readString();
        this.popularity = in.readInt();
        this.tags = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public boolean getIs_verified() {
        return is_verified;
    }

    public void setIs_verified(boolean is_verified) {
        this.is_verified = is_verified;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nickname);
        dest.writeString(this.avatar_url);
        dest.writeByte(this.is_verified ? (byte) 1 : (byte) 0);
        dest.writeString(this.url);
        dest.writeInt(this.popularity);
        dest.writeString(this.tags);
    }
}
