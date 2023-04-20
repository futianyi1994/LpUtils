package com.leapmotor.play.db.onlineradio;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * good programmer.
 *
 * @date : 2022-05-23 16:17
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class TimeInfoData implements Parcelable {
    public static final Creator<TimeInfoData> CREATOR = new Creator<TimeInfoData>() {
        @Override
        public TimeInfoData createFromParcel(Parcel in) {
            return new TimeInfoData(in);
        }

        @Override
        public TimeInfoData[] newArray(int size) {
            return new TimeInfoData[size];
        }
    };
    private long startTime;
    private long finishTime;
    private String beginTime;
    private String endTime;
    private long curSystemTime;

    public TimeInfoData() {
    }

    protected TimeInfoData(Parcel in) {
        startTime = in.readLong();
        finishTime = in.readLong();
        beginTime = in.readString();
        endTime = in.readString();
        curSystemTime = in.readLong();
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getFinishTime() {
        return this.finishTime;
    }

    public void setFinishTime(long finishTime) {
        this.finishTime = finishTime;
    }

    public String getBeginTime() {
        return this.beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public long getCurSystemTime() {
        return this.curSystemTime;
    }

    public void setCurSystemTime(long curSystemTime) {
        this.curSystemTime = curSystemTime;
    }

    @Override
    public String toString() {
        return "TimeInfoData{" +
                "startTime=" + startTime +
                ", finishTime=" + finishTime +
                ", beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", curSystemTime=" + curSystemTime +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(startTime);
        dest.writeLong(finishTime);
        dest.writeString(beginTime);
        dest.writeString(endTime);
        dest.writeLong(curSystemTime);
    }
}
