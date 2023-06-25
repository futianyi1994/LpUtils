package com.leapmotor.play.body;

import android.os.Parcel;
import android.os.Parcelable;

import com.leapmotor.play.annotation.MediaType;

/**
 * good programmer.
 *
 * @date : 2023-06-21 15:34
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class AlbumBody implements Parcelable {
    public static final Creator<AlbumBody> CREATOR = new Creator<AlbumBody>() {
        @Override
        public AlbumBody createFromParcel(Parcel in) {
            return new AlbumBody(in);
        }

        @Override
        public AlbumBody[] newArray(int size) {
            return new AlbumBody[size];
        }
    };
    /**
     * 多媒体的音源
     */
    @MediaType
    private int type = MediaType.TYPE_CURRENT;
    /**
     * 与{@link MediaBody#uniqueId}关联
     * PlayList的唯一值:
     * MediaType.TYPE_ONLINE: hash值；
     * MediaType.TYPE_ULTIMATETV: songId值；
     * MediaType.TYPE_BLUETOOTH: 为蓝牙音乐得album（歌名）值；
     * MediaType.TYPE_XMLY: 为声音或广播的id值；
     * MediaType.TYPE_FM: 为Fm频道值；
     * MediaType.TYPE_USB: U盘音乐为本地地址
     * MediaType.TYPE_ULTIMATE_MV: mvId值；
     * MediaType.TYPE_ONLINE_RADIO: audioId值；
     * MediaType.TYPE_LP_RADIO: songId值；
     */
    private String uniqueId;
    /**
     * 专辑id
     */
    private String id;
    /**
     * 专辑名
     */
    private String name;
    /**
     * 专辑描述
     */
    private String des;
    /**
     * 专辑封面
     */
    private String imagUrl;

    public AlbumBody() {
    }

    public AlbumBody(@MediaType int type, String uniqueId, String id, String name, String des, String imagUrl) {
        this.type = type;
        this.uniqueId = uniqueId;
        this.id = id;
        this.name = name;
        this.des = des;
        this.imagUrl = imagUrl;
    }

    protected AlbumBody(Parcel in) {
        type = in.readInt();
        uniqueId = in.readString();
        id = in.readString();
        name = in.readString();
        des = in.readString();
        imagUrl = in.readString();
    }

    @MediaType
    public int getType() {
        return type;
    }

    public void setType(@MediaType int type) {
        this.type = type;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getImagUrl() {
        return imagUrl;
    }

    public void setImagUrl(String imagUrl) {
        this.imagUrl = imagUrl;
    }

    @Override
    public String toString() {
        return "AlbumBody{" +
                "type=" + type +
                ", uniqueId='" + uniqueId + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", des='" + des + '\'' +
                ", imagUrl='" + imagUrl + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(type);
        dest.writeString(uniqueId);
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(des);
        dest.writeString(imagUrl);
    }
}
