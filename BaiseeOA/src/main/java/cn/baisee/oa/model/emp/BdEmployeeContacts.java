package cn.baisee.oa.model.emp;

public class BdEmployeeContacts {
    private String eid;

    private String ePhoneNum;

    private String eTelNum;

    private String eWechatNum;

    private String eQqNum;

    private String eEmail;

    private String updateTime;

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid == null ? null : eid.trim();
    }

    public String getePhoneNum() {
        return ePhoneNum;
    }

    public void setePhoneNum(String ePhoneNum) {
        this.ePhoneNum = ePhoneNum == null ? null : ePhoneNum.trim();
    }

    public String geteTelNum() {
        return eTelNum;
    }

    public void seteTelNum(String eTelNum) {
        this.eTelNum = eTelNum == null ? null : eTelNum.trim();
    }

    public String geteWechatNum() {
        return eWechatNum;
    }

    public void seteWechatNum(String eWechatNum) {
        this.eWechatNum = eWechatNum == null ? null : eWechatNum.trim();
    }

    public String geteQqNum() {
        return eQqNum;
    }

    public void seteQqNum(String eQqNum) {
        this.eQqNum = eQqNum == null ? null : eQqNum.trim();
    }

    public String geteEmail() {
        return eEmail;
    }

    public void seteEmail(String eEmail) {
        this.eEmail = eEmail == null ? null : eEmail.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }
}