package com.leapmotor.xmly.model.bean;


import android.os.Parcel;
import android.os.Parcelable;

import com.leapmotor.play.annotation.PrivilegeType;

/**
 * good programmer.
 *
 * @date : 2020-12-01 20:41
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class TrackFullBean implements Parcelable {
    public static final Creator<TrackFullBean> CREATOR = new Creator<TrackFullBean>() {
        @Override
        public TrackFullBean createFromParcel(Parcel source) {
            return new TrackFullBean(source);
        }

        @Override
        public TrackFullBean[] newArray(int size) {
            return new TrackFullBean[size];
        }
    };
    /**
     * 是否是试听歌曲0：听完整版；1：试听（只在鉴权获取播放链接时获取，并传递给客户端）
     */
    public int isFreeTry;
    /**
     * id : 208124499
     * kind : Track
     * title : 朴槿惠8⽉ 29⽇将迎终审宣判 旁听名额还剩7个没⼈要
     * intro : 8⽉ 27⽇，韩国⺠众报名旁听“⼲政⻔”案终审，现场冷清。（纽⻄斯通讯社）海外⽹8⽉ 28⽇电 明天也就是29⽇， 67岁的朴槿惠将迎来“亲信⼲政”案的终审宣判。不过，韩国⺠众似乎已经遗忘了这位被弹劾的前总统。据韩国MBN新闻等媒体报道， 8⽉ 27⽇下午，韩国最⾼法院就朴槿惠案庭审举⾏旁听席位抽签活动，结果现场很冷清， 88个对普通市⺠开放的席位，最后还剩下7个名额。 ⽆奈之下，主办⽅⼲脆取消了预定的抽签环节，给每个来现场报名的⼈，都发放了旁听⼊场券。 韩国⺠众对朴槿惠案旁听热情不⾼，去年已有先例。 2018年4⽉，朴槿惠“⼲政⻔”案⼀审宣判前，法院曾开放30个普通观众的旁听名额，结果仅吸引90多位⺠众前来抽签。  不过，这个数字，也⽐今年旁听席位不满的情况好⼀些。与这两次形成鲜明对⽐的是， 2017年5⽉份，朴槿惠⾸次接受审判时的旁听抽签现场。当时开放了68个席位，不少⼈⼀⼤早就赶来排队，最终共有525⼈参与抽签，竞争率⾼达7.7:1。有拿到旁听⼊场券的观众说， “就像中了彩票⼤奖⼀样⾼兴。 ” 朴槿惠被韩国⼈忘⼲净了吗？韩国MBN新...
     * tags :
     * created_at : 1566979412000
     * updated_at : 1566979435000
     * order_num : 0
     * duration : 76
     * play_count : 401
     * favorite_count : 0
     * comment_count : 0
     * source : 1
     * image : {"height":140,"width":140,"url":"http://imagev2.xmcdn.com/group33/M02/85/08/wKgJTFpcaUCS86jcAADx8I4Z1Fs369.jpg!op_type=3&columns=180&rows=180"}
     * can_download : true
     * uid : 102403331
     * album_id : 12580785
     * is_favourite : false
     * vip_first_status : 0
     * announcer : {"id":102403331,"nickname":"封⾯新闻","avatar_url":"https://imagev2.xmcdn.com/group31/M05/AE/BC/wKgJSVpVWEehNz2YAABUGZUcTNw947.jpg!op_type=3&columns=110&rows=110","is_verified":true,"url":null,"popularity":0,"tags":null}
     * album : {"id":12580785,"kind":"album","title":"鲜快报","intro":"新鲜出炉的新闻来⼀打，这⾥是封⾯新闻推出的鲜快报。 ","tracks_natural_ordered":false,"category_id":1,"tags":"新闻 快讯","short_intro":"新鲜出炉的新闻来⼀打，这⾥是封⾯新闻推出的鲜快报。 ","updated_at":1566979412000,"created_at":1516005828000,"play_count":451244655,"favorite_count":0,"share_count":4365,"include_track_count":4601,"is_finished":1,"can_download":false,"subscribe_count":536657,"is_records_desc":true,"is_paid":false,"cover":{"small":{"height":86,"width":86,"url":"https://imagev2.xmcdn.com/group33/M02/85/08/wKgJTFpcaUCS86jcAADx8I4Z1Fs369.jpg!op_type=3&columns=86&rows=86&magick=png"},"middle":{"height":140,"width":140,"url":"https://imagev2.xmcdn.com/group33/M02/85/08/wKgJTFpcaUCS86jcAADx8I4Z1Fs369.jpg!op_type=3&columns=140&rows=140&magick=png"},"large":{"height":290,"width":290,"url":"https://imagev2.xmcdn.com/group33/M02/85/08/wKgJTFpcaUCS86jcAADx8I4Z1Fs369.jpg!op_type=3&columns=290&rows=290&magick=png"}},"description":null,"uid":102403331,"last_updated_track_id":208124499,"list_items_relationship":0,"is_subscribe":false}
     * is_paid : false
     * is_authorized : false
     * is_free : false
     * play_info : {"play_32":{"url":"http://fdfs.xmcdn.com/group66/M08/71/25/wKgMdV1mNL6zHTqEAATPrstpAvs630.mp3","size":315310},"play_64":{"url":"http://fdfs.xmcdn.com/group66/M08/71/25/wKgMdV1mNL2ACqArAAl3hAI3oHk004.mp3","size":620420},"play_24_aac":null,"play_64_aac":null,"play_128_aac":null,"play_24_m4a":{"url":"http://audio.xmcdn.com/group66/M08/71/26/wKgMa11mNL6QTs5xAAOfnGwGu94953.m4a","size":237468},"play_64_m4a":{"url":"http://audio.xmcdn.com/group66/M08/71/25/wKgMdV1mNL2Ruq_QAAlywS5lgPY531.m4a","size":619201},"play_amr":{"url":"http://fdfs.xmcdn.com/group65/M04/70/B7/wKgMal1mNLyw1Qj7AAEMIL5HPMg131.amr","size":68640},"download_url":{"url":"http://download.xmcdn.com/group66/M08/71/26/wKgMa11mNL6QTs5xAAOfnGwGu94953.m4a","size":237468}}
     * played_secs : 0
     */
    private long id;
    private String kind;
    private String title;
    private String intro;
    private String tags;
    private long created_at;
    private long updated_at;
    private int order_num;
    private int duration;
    private long play_count;
    private int favorite_count;
    private int comment_count;
    private int source;
    private ImageBean image;
    private boolean can_download;
    private int uid;
    private long album_id;
    private boolean is_favourite;
    private int vip_first_status;
    private AnnouncerBean announcer;
    private AlbumBean album;
    private boolean is_paid;
    private boolean is_authorized;
    private boolean is_free;
    private boolean is_vip_free;
    private boolean is_vip_onley;
    private PlayInfoBean play_info;
    private int played_secs;
    private String rec_src;
    private String rec_track;
    private String play_source;
    /**
     * 自定义属性：是否正在播放
     */
    private boolean isPlaying;
    /**
     * 自定义属性声音标识鉴权结果：0:免费;1:vip;2:付费;3:无版权
     * {@link PrivilegeType}
     */
    @PrivilegeType
    private int checkType = PrivilegeType.FREE;
    /**
     * 自定义属性播放鉴权结果：0:免费;1:vip;2:付费;3:无版权
     * {@link PrivilegeType}
     */
    @PrivilegeType
    private int checkPlayType = PrivilegeType.FREE;

    public TrackFullBean() {
    }

    protected TrackFullBean(Parcel in) {
        this.id = in.readLong();
        this.kind = in.readString();
        this.title = in.readString();
        this.intro = in.readString();
        this.tags = in.readString();
        this.created_at = in.readLong();
        this.updated_at = in.readLong();
        this.order_num = in.readInt();
        this.duration = in.readInt();
        this.play_count = in.readLong();
        this.favorite_count = in.readInt();
        this.comment_count = in.readInt();
        this.source = in.readInt();
        this.image = in.readParcelable(ImageBean.class.getClassLoader());
        this.can_download = in.readByte() != 0;
        this.uid = in.readInt();
        this.album_id = in.readLong();
        this.is_favourite = in.readByte() != 0;
        this.vip_first_status = in.readInt();
        this.announcer = in.readParcelable(AnnouncerBean.class.getClassLoader());
        this.album = in.readParcelable(AlbumBean.class.getClassLoader());
        this.is_paid = in.readByte() != 0;
        this.is_authorized = in.readByte() != 0;
        this.is_free = in.readByte() != 0;
        this.is_vip_free = in.readByte() != 0;
        this.is_vip_onley = in.readByte() != 0;
        this.play_info = in.readParcelable(PlayInfoBean.class.getClassLoader());
        this.played_secs = in.readInt();
        this.rec_src = in.readString();
        this.rec_track = in.readString();
        this.play_source = in.readString();
        this.isPlaying = in.readByte() != 0;
        this.checkType = in.readInt();
        this.checkPlayType = in.readInt();
        this.isFreeTry = in.readInt();
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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public long getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(long updated_at) {
        this.updated_at = updated_at;
    }

    public int getOrder_num() {
        return order_num;
    }

    public void setOrder_num(int order_num) {
        this.order_num = order_num;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public ImageBean getImage() {
        return image;
    }

    public void setImage(ImageBean image) {
        this.image = image;
    }

    public boolean getCan_download() {
        return can_download;
    }

    public void setCan_download(boolean can_download) {
        this.can_download = can_download;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public long getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(long album_id) {
        this.album_id = album_id;
    }

    public boolean getIs_favourite() {
        return is_favourite;
    }

    public void setIs_favourite(boolean is_favourite) {
        this.is_favourite = is_favourite;
    }

    public int getVip_first_status() {
        return vip_first_status;
    }

    public void setVip_first_status(int vip_first_status) {
        this.vip_first_status = vip_first_status;
    }

    public AnnouncerBean getAnnouncer() {
        return announcer;
    }

    public void setAnnouncer(AnnouncerBean announcer) {
        this.announcer = announcer;
    }

    public AlbumBean getAlbum() {
        return album;
    }

    public void setAlbum(AlbumBean album) {
        this.album = album;
    }

    public boolean getIs_paid() {
        return is_paid;
    }

    public void setIs_paid(boolean is_paid) {
        this.is_paid = is_paid;
    }

    public boolean getIs_authorized() {
        return is_authorized;
    }

    public void setIs_authorized(boolean is_authorized) {
        this.is_authorized = is_authorized;
    }

    public boolean getIs_free() {
        return is_free;
    }

    public void setIs_free(boolean is_free) {
        this.is_free = is_free;
    }

    public boolean getIs_vip_free() {
        return is_vip_free;
    }

    public void setIs_vip_free(boolean is_vip_free) {
        this.is_vip_free = is_vip_free;
    }

    public boolean getIs_vip_onley() {
        return is_vip_onley;
    }

    public void setIs_vip_onley(boolean is_vip_onley) {
        this.is_vip_onley = is_vip_onley;
    }

    public PlayInfoBean getPlay_info() {
        return play_info;
    }

    public void setPlay_info(PlayInfoBean play_info) {
        this.play_info = play_info;
    }

    public int getPlayed_secs() {
        return played_secs;
    }

    public void setPlayed_secs(int played_secs) {
        this.played_secs = played_secs;
    }

    public String getRec_src() {
        return rec_src;
    }

    public void setRec_src(String rec_src) {
        this.rec_src = rec_src;
    }

    public String getRec_track() {
        return rec_track;
    }

    public void setRec_track(String rec_track) {
        this.rec_track = rec_track;
    }

    public String getPlay_source() {
        return play_source;
    }

    public void setPlay_source(String play_source) {
        this.play_source = play_source;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    @PrivilegeType
    public int getCheckType() {
        return checkType;
    }

    public void setCheckType(@PrivilegeType int checkType) {
        this.checkType = checkType;
    }

    @PrivilegeType
    public int getCheckPlayType() {
        return checkPlayType;
    }

    public void setCheckPlayType(@PrivilegeType int checkPlayType) {
        this.checkPlayType = checkPlayType;
    }

    public int getIsFreeTry() {
        return isFreeTry;
    }

    public void setIsFreeTry(int isFreeTry) {
        this.isFreeTry = isFreeTry;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.kind);
        dest.writeString(this.title);
        dest.writeString(this.intro);
        dest.writeString(this.tags);
        dest.writeLong(this.created_at);
        dest.writeLong(this.updated_at);
        dest.writeInt(this.order_num);
        dest.writeInt(this.duration);
        dest.writeLong(this.play_count);
        dest.writeInt(this.favorite_count);
        dest.writeInt(this.comment_count);
        dest.writeInt(this.source);
        dest.writeParcelable(this.image, flags);
        dest.writeByte(this.can_download ? (byte) 1 : (byte) 0);
        dest.writeInt(this.uid);
        dest.writeLong(this.album_id);
        dest.writeByte(this.is_favourite ? (byte) 1 : (byte) 0);
        dest.writeInt(this.vip_first_status);
        dest.writeParcelable(this.announcer, flags);
        dest.writeParcelable(this.album, flags);
        dest.writeByte(this.is_paid ? (byte) 1 : (byte) 0);
        dest.writeByte(this.is_authorized ? (byte) 1 : (byte) 0);
        dest.writeByte(this.is_free ? (byte) 1 : (byte) 0);
        dest.writeByte(this.is_vip_free ? (byte) 1 : (byte) 0);
        dest.writeByte(this.is_vip_onley ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.play_info, flags);
        dest.writeInt(this.played_secs);
        dest.writeString(this.rec_src);
        dest.writeString(this.rec_track);
        dest.writeString(this.play_source);
        dest.writeByte(this.isPlaying ? (byte) 1 : (byte) 0);
        dest.writeInt(this.checkType);
        dest.writeInt(this.checkPlayType);
        dest.writeInt(this.isFreeTry);
    }
}
