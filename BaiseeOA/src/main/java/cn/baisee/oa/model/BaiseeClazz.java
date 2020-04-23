package cn.baisee.oa.model;

import java.io.Serializable;

import cn.baisee.oa.model.empinfo.Employee;

/**
 * 班级的类
 *
 * @author wangbaoxiang
 */
public class BaiseeClazz implements Serializable {


    @Override
    public String toString() {
        return "BaiseeClazz{" +
                "cId='" + cId + '\'' +
                ", cName='" + cName + '\'' +
                ", cType='" + cType + '\'' +
                ", cStatus='" + cStatus + '\'' +
                ", cTeacherNumber='" + cTeacherNumber + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", isGraduate='" + isGraduate + '\'' +
                ", emp=" + emp +
                '}';
    }

    /**
     *
     */
    private static final long serialVersionUID = -1551404715567883462L;
    /**
     * 班级主键
     */
    private String cId;
    /**
     * 班级名称
     */
    private String cName;
    /**
     * 班级类型
     * 试听  或者 正式
     */
    private String cType;
    /**
     * 高中班级 或  初中班级
     */
    private String cStatus;
    /**
     * 所属班主任编号
     */
    private String cTeacherNumber;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 是否大学毕业
     */
    private String isGraduate;

    public String getIsGraduate() {
        return isGraduate;
    }

    public void setIsGraduate(String isGraduate) {
        this.isGraduate = isGraduate;
    }

    private Employee emp;

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcType() {
        return cType;
    }

    public void setcType(String cType) {
        this.cType = cType;
    }

    public String getcStatus() {
        return cStatus;
    }

    public void setcStatus(String cStatus) {
        this.cStatus = cStatus;
    }

    public String getcTeacherNumber() {
        return cTeacherNumber;
    }

    public void setcTeacherNumber(String cTeacherNumber) {
        this.cTeacherNumber = cTeacherNumber;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }


}
