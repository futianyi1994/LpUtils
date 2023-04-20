package com.leapmotor.xmly.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * good programmer.
 *
 * @date : 2021-03-16 14:11
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class ImageBean implements Parcelable {
    public static final Creator<ImageBean> CREATOR = new Creator<ImageBean>() {
        @Override
        public ImageBean createFromParcel(Parcel source) {
            return new ImageBean(source);
        }

        @Override
        public ImageBean[] newArray(int size) {
            return new ImageBean[size];
        }
    };
    /**
     * height : 140
     * width : 140
     * url : http://imagev2.xmcdn.com/group33/M02/85/08/wKgJTFpcaUCS86jcAADx8I4Z1Fs369.jpg!op_type=3&columns=180&rows=180
     */
    private int height;
    private int width;
    private String url;

    public ImageBean() {
    }

    protected ImageBean(Parcel in) {
        this.height = in.readInt();
        this.width = in.readInt();
        this.url = in.readString();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.height);
        dest.writeInt(this.width);
        dest.writeString(this.url);
    }
}
