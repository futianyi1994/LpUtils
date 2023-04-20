package com.leapmotor.xmly.model.bean;

import java.util.List;

/**
 * good programmer.
 *
 * @date : 2021-03-25 14:50
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class ProgramBean {

    /**
     * id : 129785
     * program_name : 音乐无人驾驶
     * back_pic_url : null
     * support_bitrates : [24,64]
     * live_announcers : [{"id":11485,"nickname":"qqtester325","avatar_url":null,"is_verified":false,"url":null,"popularity":0,"tags":null}]
     * updated_at : 0
     * play_url : {"aac24":"http://live.xmcdn.com/live/95/24.m3u8","aac64":"http://live.xmcdn.com/live/95/64.m3u8","ts24":"http://live.xmcdn.com/live/95/24.m3u8?transcode=ts","ts64":"http://live.xmcdn.com/live/95/64.m3u8?transcode=ts"}
     * kind : program
     */
    private long id;
    private String program_name;
    private String back_pic_url;
    private int updated_at;
    private PlayUrlBean play_url;
    private String kind;
    private List<Integer> support_bitrates;
    private List<AnnouncerBean> live_announcers;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProgram_name() {
        return program_name;
    }

    public void setProgram_name(String program_name) {
        this.program_name = program_name;
    }

    public String getBack_pic_url() {
        return back_pic_url;
    }

    public void setBack_pic_url(String back_pic_url) {
        this.back_pic_url = back_pic_url;
    }

    public int getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }

    public PlayUrlBean getPlay_url() {
        return play_url;
    }

    public void setPlay_url(PlayUrlBean play_url) {
        this.play_url = play_url;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public List<Integer> getSupport_bitrates() {
        return support_bitrates;
    }

    public void setSupport_bitrates(List<Integer> support_bitrates) {
        this.support_bitrates = support_bitrates;
    }

    public List<AnnouncerBean> getLive_announcers() {
        return live_announcers;
    }

    public void setLive_announcers(List<AnnouncerBean> live_announcers) {
        this.live_announcers = live_announcers;
    }
}
