package com.leapmotor.xmly.model.bean;

/**
 * good programmer.
 *
 * @date : 2021-03-22 13:07
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class RecommendInfoBean {

    /**
     * content_type : 1
     * album : {"id":11830493,"kind":"album","title":"刘兰芳评书 |【新】岳飞传","intro":"","tracks_natural_ordered":false,"category_id":12,"tags":"","short_intro":"","updated_at":1599635580000,"created_at":1512030594000,"play_count":709259934,"favorite_count":0,"share_count":31122,"include_track_count":200,"is_finished":2,"can_download":false,"subscribe_count":782025,"is_records_desc":false,"is_paid":false,"cover":{"small":{"height":86,"width":86,"url":"https://imagev2.xmcdn.com/group83/M08/04/CC/wKg5I18-GfHwk_sxAAMzdUUR_BY264.jpg!op_type=3&columns=86&rows=86&magick=png"},"middle":{"height":140,"width":140,"url":"https://imagev2.xmcdn.com/group83/M08/04/CC/wKg5I18-GfHwk_sxAAMzdUUR_BY264.jpg!op_type=3&columns=140&rows=140&magick=png"},"large":{"height":290,"width":290,"url":"https://imagev2.xmcdn.com/group83/M08/04/CC/wKg5I18-GfHwk_sxAAMzdUUR_BY264.jpg!op_type=3&columns=290&rows=290&magick=png"}},"description":null,"uid":1000267,"last_updated_track_id":73563066,"list_items_relationship":2,"vip_first_status":0,"age_level":0,"is_exclusive":true,"authorized_type_id":0,"left_time":0,"announcer":{"id":1000267,"nickname":"刘兰芳百姓书场","avatar_url":"https://imagev2.xmcdn.com/group34/M01/3A/42/wKgJYVnfMm-gpWilAAHrQgbEX8I607.jpg!op_type=3&columns=110&rows=110","is_verified":true,"url":null,"popularity":0,"tags":null,"verify_type":2,"ptitle":"相声评书月度优质主播","follower_count":1312713},"last_update_track":{"id":73563066,"kind":"Track","title":"刘兰芳播讲：[新]岳飞传 第199回 金兵埋伏盘龙山 十万大军围岳帅","intro":"加刘老师助理V信：LLFFans，进群互动！","tags":"","created_at":1519615800000,"updated_at":1594181310000,"order_num":-1,"duration":1101,"play_count":3176170,"favorite_count":5178,"comment_count":537,"source":1,"image":{"height":140,"width":140,"url":"http://imagev2.xmcdn.com/group33/M05/10/4E/wKgJUVohBhPyKirTAARxptPTOJk828.jpg!op_type=3&columns=180&rows=180"},"can_download":true,"uid":1000267,"album_id":11830493,"is_favourite":false,"is_video":false,"vip_first_status":0,"ximi_first_status":0},"is_sample":false,"is_vip_free":false,"is_vip_only":false,"price_infos":null,"price_type_id":0,"free_track_count":0,"quality_score":0,"is_authorized":false,"is_subscribe":false}
     * track : null
     * rec_src : time
     * rec_track : 2.car_fgyl.1905
     * rec_reason : null
     */
    private int content_type;
    private AlbumPayBean album;
    private TrackFullBean track;
    private String rec_src;
    private String rec_track;
    private String rec_reason;

    public int getContent_type() {
        return content_type;
    }

    public void setContent_type(int content_type) {
        this.content_type = content_type;
    }

    public AlbumPayBean getAlbum() {
        return album;
    }

    public void setAlbum(AlbumPayBean album) {
        this.album = album;
    }

    public TrackFullBean getTrack() {
        return track;
    }

    public void setTrack(TrackFullBean track) {
        this.track = track;
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

    public String getRec_reason() {
        return rec_reason;
    }

    public void setRec_reason(String rec_reason) {
        this.rec_reason = rec_reason;
    }
}
