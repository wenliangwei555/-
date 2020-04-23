package cn.baisee.oa.model;

import java.util.Date;

public class BaiseeNoticeStaff {
    private String t_id;//自定义模板id
    private String stu_temlate_id;//模板id
    private String t_title;//模板标题
    private String t_content;//模板内容
    private Date create_time;//创建时间
    private Date update_time;//更改时间
    private String create_name;//创建人
    private Date sending_time;//发送时间
    private String create_id;//创建人id
    private String USER_ID;//员工id
    private String name;//操作人
    private char type1;//0未读1已读2已处理3已关闭
    private String user_name;//员工名称
    private String id;//纪录id

    public String getUser_name() {
        return user_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Date getSending_time() {
        return sending_time;
    }

    public void setSending_time(Date sending_time) {
        this.sending_time = sending_time;
    }

    public char getType1() {
        return type1;
    }

    public void setType1(char type1) {
        this.type1 = type1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getT_id() {
        return t_id;
    }

    public void setT_id(String t_id) {
        this.t_id = t_id;
    }

    public String getStu_temlate_id() {
        return stu_temlate_id;
    }

    public void setStu_temlate_id(String stu_temlate_id) {
        this.stu_temlate_id = stu_temlate_id;
    }

    public String getT_title() {
        return t_title;
    }

    public void setT_title(String t_title) {
        this.t_title = t_title;
    }

    public String getT_content() {
        return t_content;
    }

    public void setT_content(String t_content) {
        this.t_content = t_content;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getCreate_name() {
        return create_name;
    }

    public void setCreate_name(String create_name) {
        this.create_name = create_name;
    }

    public String getCreate_id() {
        return create_id;
    }

    public void setCreate_id(String create_id) {
        this.create_id = create_id;
    }

    @Override
    public String toString() {
        return "BaiseeNoticeStaff{" +
                "t_id='" + t_id + '\'' +
                ", stu_temlate_id='" + stu_temlate_id + '\'' +
                ", t_title='" + t_title + '\'' +
                ", t_content='" + t_content + '\'' +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                ", create_name='" + create_name + '\'' +
                ", sending_time=" + sending_time +
                ", create_id='" + create_id + '\'' +
                ", USER_ID='" + USER_ID + '\'' +
                ", name='" + name + '\'' +
                ", type1=" + type1 +
                ", user_name='" + user_name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
