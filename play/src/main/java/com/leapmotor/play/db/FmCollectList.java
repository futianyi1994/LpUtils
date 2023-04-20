package com.leapmotor.play.db;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.IntRange;

/**
 * good programmer.
 *
 * @date : 2020-06-15 10:13
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class FmCollectList implements Parcelable {
    public static final Creator<FmCollectList> CREATOR = new Creator<FmCollectList>() {
        @Override
        public FmCollectList createFromParcel(Parcel in) {
            return new FmCollectList(in);
        }

        @Override
        public FmCollectList[] newArray(int size) {
            return new FmCollectList[size];
        }
    };
    private long fmCollectId;

    @IntRange(from = 8750, to = 10800)
    private int freq;
    private long time;

    public FmCollectList() {
    }

    public FmCollectList(@IntRange(from = 8750, to = 10800) int freq, long time) {
        this.freq = freq;
        this.time = time;
    }

    protected FmCollectList(Parcel in) {
        fmCollectId = in.readLong();
        freq = in.readInt();
        time = in.readLong();
    }

    public long getFmCollectId() {
        return fmCollectId;
    }

    public void setFmCollectId(long fmCollectId) {
        this.fmCollectId = fmCollectId;
    }

    @IntRange(from = 8750, to = 10800)
    public int getFreq() {
        return freq;
    }

    public void setFreq(@IntRange(from = 8750, to = 10800) int freq) {
        this.freq = freq;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(fmCollectId);
        dest.writeInt(freq);
        dest.writeLong(time);
    }
}
