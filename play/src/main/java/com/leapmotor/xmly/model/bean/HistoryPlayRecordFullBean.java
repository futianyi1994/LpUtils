package com.leapmotor.xmly.model.bean;

/**
 * good programmer.
 *
 * @date : 2021-03-25 14:49
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class HistoryPlayRecordFullBean {

    /**
     * uid : 123212
     * content_type : 1
     * album : null
     * track : null
     * radio : null
     * program : null
     * played_secs : 0
     * started_at : 1504695227000
     * ended_at : 1504695227000
     */

    private int uid;
    private int content_type;
    private AlbumPayBean album;
    private TrackFullBean track;
    private RadioBean radio;
    private ProgramBean program;
    private int played_secs;
    private long started_at;
    private long ended_at;

    /**
     * 自定义属性：是否选择
     */
    private boolean isSelect;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

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

    public RadioBean getRadio() {
        return radio;
    }

    public void setRadio(RadioBean radio) {
        this.radio = radio;
    }

    public ProgramBean getProgram() {
        return program;
    }

    public void setProgram(ProgramBean program) {
        this.program = program;
    }

    public int getPlayed_secs() {
        return played_secs;
    }

    public void setPlayed_secs(int played_secs) {
        this.played_secs = played_secs;
    }

    public long getStarted_at() {
        return started_at;
    }

    public void setStarted_at(long started_at) {
        this.started_at = started_at;
    }

    public long getEnded_at() {
        return ended_at;
    }

    public void setEnded_at(long ended_at) {
        this.ended_at = ended_at;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
