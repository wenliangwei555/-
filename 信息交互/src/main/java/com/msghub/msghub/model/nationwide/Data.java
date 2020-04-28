package com.msghub.msghub.model.nationwide;

/**
 * @Author 温莨
 * @Date 2020/2/20 21:06
 * @Version 1.0
 */
public class Data {
    private String id;
    //确诊
    private String diagnosed;
    //疑似
    private String suspect;
    //死亡
    private String death;
    //治疗
    private String cured;
    //时间
    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiagnosed() {
        return diagnosed;
    }

    public void setDiagnosed(String diagnosed) {
        this.diagnosed = diagnosed;
    }

    public String getSuspect() {
        return suspect;
    }

    public void setSuspect(String suspect) {
        this.suspect = suspect;
    }

    public String getDeath() {
        return death;
    }

    public void setDeath(String death) {
        this.death = death;
    }

    public String getCured() {
        return cured;
    }

    public void setCured(String cured) {
        this.cured = cured;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
