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
public class FmList implements Parcelable {
    public static final Creator<FmList> CREATOR = new Creator<FmList>() {
        @Override
        public FmList createFromParcel(Parcel in) {
            return new FmList(in);
        }

        @Override
        public FmList[] newArray(int size) {
            return new FmList[size];
        }
    };
    private long fmId;

    @IntRange(from = 8750, to = 10800)
    private int freq;

    private boolean isCollect;

    public FmList() {
    }

    public FmList(@IntRange(from = 8750, to = 10800) int freq, boolean isCollect) {
        this.freq = freq;
        this.isCollect = isCollect;
    }

    protected FmList(Parcel in) {
        fmId = in.readLong();
        freq = in.readInt();
        isCollect = in.readByte() != 0;
    }

    public long getFmId() {
        return fmId;
    }

    public void setFmId(long fmId) {
        this.fmId = fmId;
    }

    @IntRange(from = 8750, to = 10800)
    public int getFreq() {
        return freq;
    }

    public void setFreq(@IntRange(from = 8750, to = 10800) int freq) {
        this.freq = freq;
    }

    public boolean isCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(fmId);
        dest.writeInt(freq);
        dest.writeByte((byte) (isCollect ? 1 : 0));
    }
}
