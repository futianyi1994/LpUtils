package com.leapmotor.xmly.model.bean;

/**
 * good programmer.
 *
 * @date : 2021-03-20 13:42
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class TrackSimplifiedBean {

    /**
     * id : 26172733
     * kind : Track
     * title : 《医统江⼭》终章（完）
     * intro : null
     * tags :
     * created_at : 1480657876000
     * updated_at : 1540886125000
     * order_num : -1
     * duration : 2742
     * play_count : 66258
     * favorite_count : 533
     * comment_count : 364
     * source : 1
     * image : {"height":140,"width":140,"url":"http:!op_type=3&columns=180&rows=180"}
     * can_download : true
     * uid : 9207739
     * album_id : 2903266
     * is_favourite : false
     */
    private int id;
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
    private int album_id;
    private boolean is_favourite;

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

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public boolean getIs_favourite() {
        return is_favourite;
    }

    public void setIs_favourite(boolean is_favourite) {
        this.is_favourite = is_favourite;
    }
}
