package com.leapmotor.play.body;


import android.os.Parcel;
import android.os.Parcelable;

import com.leapmotor.play.annotation.MediaType;

public class SeekBody implements Parcelable {

    public static final Creator<SeekBody> CREATOR = new Creator<SeekBody>() {
        @Override
        public SeekBody createFromParcel(Parcel in) {
            return new SeekBody(in);
        }

        @Override
        public SeekBody[] newArray(int size) {
            return new SeekBody[size];
        }
    };
    /**
     * 多媒体的音源
     */
    @MediaType
    private int type = MediaType.TYPE_CURRENT;
    /**
     * 歌曲已播放的时长单位ms
     */
    private long position;
    /**
     * 歌曲的总时长单位s
     */
    private int duration;

    public SeekBody() {
    }

    public SeekBody(@MediaType int type, long position, int duration) {
        this.type = type;
        this.position = position;
        this.duration = duration;
    }

    protected SeekBody(Parcel in) {
        type = in.readInt();
        position = in.readLong();
        duration = in.readInt();
    }

    @MediaType
    public int getType() {
        return type;
    }

    public void setType(@MediaType int type) {
        this.type = type;
    }

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }


    @Override
    public String toString() {
        return "SeekBody{" +
                "type=" + type +
                ", position=" + position +
                ", duration=" + duration +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(type);
        dest.writeLong(position);
        dest.writeInt(duration);
    }
}
