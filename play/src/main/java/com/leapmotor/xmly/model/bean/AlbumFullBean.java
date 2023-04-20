package com.leapmotor.xmly.model.bean;

/**
 * good programmer.
 *
 * @date : 2020-12-01 11:00
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class AlbumFullBean {

    /**
     * id : 72281
     * kind : album
     * title : secret garden
     * intro : null
     * short_intro : xxx
     * tracks_natural_ordered : false
     * category_id : 2
     * tags :
     * updated_at : 1421769600000
     * created_at : 1366992000000
     * play_count : 0
     * favorite_count : 0
     * share_count : 0
     * include_track_count : 16
     * is_finished : 0
     * can_download : false
     * subscribe_count : 213
     * is_paid : false
     * is_subscribe : false
     * is_records_desc : false
     * cover : {"small":{"height":86,"width":86,"url":"http:_mobile_small.jpg"},"middle":{"height":140,"width":140,"url":"http:_mobile_meduim.jpg"},"large":{"height":290,"width":290,"url":"http:_mobile_large.jpg"}}
     * description : 每周两更
     * uid : 23131
     * last_updated_track_id : 213133
     */
    private long id;
    private String kind;
    private String title;
    private String intro;
    private String short_intro;
    private boolean tracks_natural_ordered;
    private int category_id;
    private String tags;
    private long updated_at;
    private long created_at;
    private long play_count;
    private int favorite_count;
    private int share_count;
    private int include_track_count;
    private int is_finished;
    private boolean can_download;
    private int subscribe_count;
    private boolean is_paid;
    private boolean is_subscribe;
    private boolean is_records_desc;
    private CoverBean cover;
    private String description;
    private int uid;
    private int last_updated_track_id;
    //private boolean list_items_relationship;
    private int vip_first_status;
    private AnnouncerBean announcer;
    private TrackSimplifiedBean last_update_track;

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

    public String getShort_intro() {
        return short_intro;
    }

    public void setShort_intro(String short_intro) {
        this.short_intro = short_intro;
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

    public boolean getIs_paid() {
        return is_paid;
    }

    public void setIs_paid(boolean is_paid) {
        this.is_paid = is_paid;
    }

    public boolean getIs_subscribe() {
        return is_subscribe;
    }

    public void setIs_subscribe(boolean is_subscribe) {
        this.is_subscribe = is_subscribe;
    }

    public boolean getIs_records_desc() {
        return is_records_desc;
    }

    public void setIs_records_desc(boolean is_records_desc) {
        this.is_records_desc = is_records_desc;
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

    /*public boolean isList_items_relationship() {
        return list_items_relationship;
    }

    public void setList_items_relationship(boolean list_items_relationship) {
        this.list_items_relationship = list_items_relationship;
    }*/

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

    public TrackSimplifiedBean getLast_update_track() {
        return last_update_track;
    }

    public void setLast_update_track(TrackSimplifiedBean last_update_track) {
        this.last_update_track = last_update_track;
    }
}
