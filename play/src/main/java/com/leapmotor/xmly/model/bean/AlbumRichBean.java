package com.leapmotor.xmly.model.bean;

/**
 * good programmer.
 *
 * @date : 2021-05-13 16:59
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class AlbumRichBean extends AlbumFullBean {
    private String rich_intro;
    private String buy_notes;
    private String other_title;
    private String other_content;
    private String personal_description;

    public String getRich_intro() {
        return rich_intro;
    }

    public void setRich_intro(String rich_intro) {
        this.rich_intro = rich_intro;
    }

    public String getBuy_notes() {
        return buy_notes;
    }

    public void setBuy_notes(String buy_notes) {
        this.buy_notes = buy_notes;
    }

    public String getOther_title() {
        return other_title;
    }

    public void setOther_title(String other_title) {
        this.other_title = other_title;
    }

    public String getOther_content() {
        return other_content;
    }

    public void setOther_content(String other_content) {
        this.other_content = other_content;
    }

    public String getPersonal_description() {
        return personal_description;
    }

    public void setPersonal_description(String personal_description) {
        this.personal_description = personal_description;
    }
}
