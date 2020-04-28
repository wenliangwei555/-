package cn.baisee.oa.model;

import java.util.Date;

public class BaiseeClassMerger {
    private Integer ID;//id
    private String SID;//学生编号
    private String S_ID_NUMBER;//学生身份证号
    private String OLD_CID;//上一个班级
    private String NEW_CID;//新班级
    private Date NEW_TIME;//合并时间
    private String U_NAME;//操作人
    private String STU_NAME;//学生姓名
    private String NEW_CNAME;//新的班级名称
    private String OLD_CNAME;//原来班级名称



    public String getNEW_CNAME() {
        return NEW_CNAME;
    }

    public void setNEW_CNAME(String NEW_CNAME) {
        this.NEW_CNAME = NEW_CNAME;
    }

    public String getOLD_CNAME() {
        return OLD_CNAME;
    }

    public void setOLD_CNAME(String OLD_CNAME) {
        this.OLD_CNAME = OLD_CNAME;
    }

    public String getSTU_NAME() {
        return STU_NAME;
    }

    public void setSTU_NAME(String STU_NAME) {
        this.STU_NAME = STU_NAME;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getSID() {
        return SID;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }

    public String getS_ID_NUMBER() {
        return S_ID_NUMBER;
    }

    public void setS_ID_NUMBER(String s_ID_NUMBER) {
        S_ID_NUMBER = s_ID_NUMBER;
    }

    public String getOLD_CID() {
        return OLD_CID;
    }

    public void setOLD_CID(String OLD_CID) {
        this.OLD_CID = OLD_CID;
    }

    public String getNEW_CID() {
        return NEW_CID;
    }

    public void setNEW_CID(String NEW_CID) {
        this.NEW_CID = NEW_CID;
    }

    public Date getNEW_TIME() {
        return NEW_TIME;
    }

    public void setNEW_TIME(Date NEW_TIME) {
        this.NEW_TIME = NEW_TIME;
    }

    public String getU_NAME() {
        return U_NAME;
    }

    public void setU_NAME(String u_NAME) {
        U_NAME = u_NAME;
    }

}
