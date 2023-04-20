package com.leapmotor.xmly.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * good programmer.
 *
 * @date : 2021-03-16 14:13
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class AlbumBean implements Parcelable {
    public static final Creator<AlbumBean> CREATOR = new Creator<AlbumBean>() {
        @Override
        public AlbumBean createFromParcel(Parcel source) {
            return new AlbumBean(source);
        }

        @Override
        public AlbumBean[] newArray(int size) {
            return new AlbumBean[size];
        }
    };
    /**
     * id : 12580785
     * kind : album
     * title : 鲜快报
     * intro : 新鲜出炉的新闻来⼀打，这⾥是封⾯新闻推出的鲜快报。
     * tracks_natural_ordered : false
     * category_id : 1
     * tags : 新闻 快讯
     * short_intro : 新鲜出炉的新闻来⼀打，这⾥是封⾯新闻推出的鲜快报。
     * updated_at : 1566979412000
     * created_at : 1516005828000
     * play_count : 451244655
     * favorite_count : 0
     * share_count : 4365
     * include_track_count : 4601
     * is_finished : 1
     * can_download : false
     * subscribe_count : 536657
     * is_records_desc : true
     * is_paid : false
     * cover : {"small":{"height":86,"width":86,"url":"https://imagev2.xmcdn.com/group33/M02/85/08/wKgJTFpcaUCS86jcAADx8I4Z1Fs369.jpg!op_type=3&columns=86&rows=86&magick=png"},"middle":{"height":140,"width":140,"url":"https://imagev2.xmcdn.com/group33/M02/85/08/wKgJTFpcaUCS86jcAADx8I4Z1Fs369.jpg!op_type=3&columns=140&rows=140&magick=png"},"large":{"height":290,"width":290,"url":"https://imagev2.xmcdn.com/group33/M02/85/08/wKgJTFpcaUCS86jcAADx8I4Z1Fs369.jpg!op_type=3&columns=290&rows=290&magick=png"}}
     * description : null
     * uid : 102403331
     * last_updated_track_id : 208124499
     * list_items_relationship : 0
     * is_subscribe : false
     */
    private int id;
    private String kind;
    private String title;
    private String intro;
    private boolean tracks_natural_ordered;
    private int category_id;
    private String tags;
    private String short_intro;
    private long updated_at;
    private long created_at;
    private long play_count;
    private int favorite_count;
    private int share_count;
    private int include_track_count;
    private int is_finished;
    private boolean can_download;
    private int subscribe_count;
    private boolean is_records_desc;
    private boolean is_paid;
    private CoverBean cover;
    private String description;
    private int uid;
    private int last_updated_track_id;
    private int list_items_relationship;
    private boolean is_subscribe;

    public AlbumBean() {
    }

    protected AlbumBean(Parcel in) {
        this.id = in.readInt();
        this.kind = in.readString();
        this.title = in.readString();
        this.intro = in.readString();
        this.tracks_natural_ordered = in.readByte() != 0;
        this.category_id = in.readInt();
        this.tags = in.readString();
        this.short_intro = in.readString();
        this.updated_at = in.readLong();
        this.created_at = in.readLong();
        this.play_count = in.readLong();
        this.favorite_count = in.readInt();
        this.share_count = in.readInt();
        this.include_track_count = in.readInt();
        this.is_finished = in.readInt();
        this.can_download = in.readByte() != 0;
        this.subscribe_count = in.readInt();
        this.is_records_desc = in.readByte() != 0;
        this.is_paid = in.readByte() != 0;
        this.cover = in.readParcelable(CoverBean.class.getClassLoader());
        this.description = in.readString();
        this.uid = in.readInt();
        this.last_updated_track_id = in.readInt();
        this.list_items_relationship = in.readInt();
        this.is_subscribe = in.readByte() != 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public boolean getTracks_natural_ordered() {
        return tracks_natural_ordered;
    }

    public void setTracks_natural_ordered(boolean tracks_natural_ordered) {
        this.tracks_natural_ordered = tracks_natural_ordered;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getShort_intro() {
        return short_intro;
    }

    public void setShort_intro(String short_intro) {
        this.short_intro = short_intro;
    }

    public long getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(long updated_at) {
        this.updated_at = updated_at;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public long getPlay_count() {
        return play_count;
    }

    public void setPlay_count(long play_count) {
        this.play_count = play_count;
    }

    public int getFavorite_count() {
        return favorite_count;
    }

    public void setFavorite_count(int favorite_count) {
        this.favorite_count = favorite_count;
    }

    public int getShare_count() {
        return share_count;
    }

    public void setShare_count(int share_count) {
        this.share_count = share_count;
    }

    public int getInclude_track_count() {
        return include_track_count;
    }

    public void setInclude_track_count(int include_track_count) {
        this.include_track_count = include_track_count;
    }

    public int getIs_finished() {
        return is_finished;
    }

    public void setIs_finished(int is_finished) {
        this.is_finished = is_finished;
    }

    public boolean getCan_download() {
        return can_download;
    }

    public void setCan_download(boolean can_download) {
        this.can_download = can_download;
    }

    public int getSubscribe_count() {
        return subscribe_count;
    }

    public void setSubscribe_count(int subscribe_count) {
        this.subscribe_count = subscribe_count;
    }

    public boolean getIs_records_desc() {
        return is_records_desc;
    }

    public void setIs_records_desc(boolean is_records_desc) {
        this.is_records_desc = is_records_desc;
    }

    public boolean getIs_paid() {
        return is_paid;
    }

    public void setIs_paid(boolean is_paid) {
        this.is_paid = is_paid;
    }

    public CoverBean getCover() {
        return cover;
    }

    public void setCover(CoverBean cover) {
        this.cover = cover;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getLast_updated_track_id() {
        return last_updated_track_id;
    }

    public void setLast_updated_track_id(int last_updated_track_id) {
        this.last_updated_track_id = last_updated_track_id;
    }

    public int getList_items_relationship() {
        return list_items_relationship;
    }

    public void setList_items_relationship(int list_items_relationship) {
        this.list_items_relationship = list_items_relationship;
    }

    public boolean getIs_subscribe() {
        return is_subscribe;
    }

    public void setIs_subscribe(boolean is_subscribe) {
        this.is_subscribe = is_subscribe;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.kind);
        dest.writeString(this.title);
        dest.writeString(this.intro);
        dest.writeByte(this.tracks_natural_ordered ? (byte) 1 : (byte) 0);
        dest.writeInt(this.category_id);
        dest.writeString(this.tags);
        dest.writeString(this.short_intro);
        dest.writeLong(this.updated_at);
        dest.writeLong(this.created_at);
        dest.writeLong(this.play_count);
        dest.writeInt(this.favorite_count);
        dest.writeInt(this.share_count);
        dest.writeInt(this.include_track_count);
        dest.writeInt(this.is_finished);
        dest.writeByte(this.can_download ? (byte) 1 : (byte) 0);
        dest.writeInt(this.subscribe_count);
        dest.writeByte(this.is_records_desc ? (byte) 1 : (byte) 0);
        dest.writeByte(this.is_paid ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.cover, flags);
        dest.writeString(this.description);
        dest.writeInt(this.uid);
        dest.writeInt(this.last_updated_track_id);
        dest.writeInt(this.list_items_relationship);
        dest.writeByte(this.is_subscribe ? (byte) 1 : (byte) 0);
    }
}
