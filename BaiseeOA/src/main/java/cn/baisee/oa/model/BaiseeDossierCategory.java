package cn.baisee.oa.model;

import java.util.Date;

public class BaiseeDossierCategory {
	//类别id
    private String id;
    //类别名字
    private String cName;
    //修改时间
    private Date uTime;
    //修改人
    private String uName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public Date getuTime() {
        return uTime;
    }

    public void setuTime(Date uTime) {
        this.uTime = uTime;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }
}
