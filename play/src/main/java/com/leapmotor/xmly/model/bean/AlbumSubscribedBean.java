package com.leapmotor.xmly.model.bean;

/**
 * good programmer.
 *
 * @date : 2021-04-27 17:33
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class AlbumSubscribedBean extends AlbumPayBean {

    private int timeline;
    private int updated_tracks_count;

    public int getTimeline() {
        return timeline;
    }

    public void setTimeline(int timeline) {
        this.timeline = timeline;
    }

    public int getUpdated_tracks_count() {
        return updated_tracks_count;
    }

    public void setUpdated_tracks_count(int updated_tracks_count) {
        this.updated_tracks_count = updated_tracks_count;
    }
}
