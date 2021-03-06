package cn.baisee.oa.model.student;

import java.util.List;

/**
 * 学生信息
 *
 * @author sdx
 */
public class BaiseeStudent {
    /**
     * 学生主键
     */
    private String stuId;
    /**
     * 该学生所属试听班级
     */
    private String audCid;
    /**
     * 所属正式班级id
     */
    private String claId;
    /**
     * 班级名称
     */
    private String claName;
    /**
     * 所属省份
     */
    private String stuProvince;


    /**
     * 所属城市
     */
    private String stuCity;
    /**
     * 所属区县
     */
    private String stuArea;
    /**
     * 家庭住址
     */
    private String stuHomeAddress;
    /**
     * 家庭表中家人联系方式
     */
    private List<BaiseeStudentFamily> stuFamTel;
    /**
     * 学生姓名
     */
    private String stuName;
    /**
     * 性别
     */
    private String stuSex;
    /**
     * 年龄
     */
    private String stuAge;
    /**
     * 手机号
     */
    private String stuMobile;
    /**
     * 身份证
     */
    private String stuIdNumber;
    /**
     * 学生状态
     */
    private String stuStatus;
    /**
     * 学生类型试听，正式
     */
    private String stuType;
    /**
     * 正式学员学号
     */
    private String stuFormalNo;
    /**
     * 在校专业
     */
    private String stuSpecialty;
    /**
     * 出生日期
     */
    private String stuBirthDate;
    /**
     * 试听开始时间
     */
    private String stuAuditionStartTime;
    /**
     * 试听结束时间
     */
    private String stuAuditionEndTime;
    /**
     * 正式入学时间
     */
    private String stuEnrolmentTime;
    /**
     * 离开学校时间
     */
    private String stuStatusDate;
    /**
     * 休学开始时间
     */
    private String stuLeaveStartTime;
    /**
     * 休学结束时间
     */
    private String stuLeaveEndTime;
    /**
     * 修改状态时根据此属性进行不同的修改
     */
    private String addParam;

    /**
     * 最后更新时间
     */
    private String updateTime;
    /**
     * 学费id
     */
    private String tuId;
    /**
     * 是否交清学费状态 (  0已经交清  /   1未交清)
     */
    private String tuitioinStatus;
    /**
     * 现在正在缴纳的是第几期
     * 此学生正在缴纳的期数(0:缴纳完毕、1:正在缴纳第一期、2:正在缴纳第二期、3:正在缴纳第三期、等等)
     */
    private String tuitioinPeriodsNow;//TUITIOIN_PERIODS_NOW
    /**
     * 学生的分期主键
     */
    private String tuStId;//TU_ST_ID
    /**
     * 是否返还推荐费用( 0返还  / 1未返）
     */
    private String returnStatus;
    /**
     * 学员备注
     */
    private String stuRemark;
    /**
     * 学员的地区名字
     */
    private String stuAreaName;
    /**
     * 学生未缴费用
     */
    private String stuNotPayment;
    /**
     * 现在这个学生欠这个期数多少钱？List催费页面上使用
     */
    private String stuNotCurrentPeriod;
    /**
     * 这个值时存储此期中学生全部欠多少钱，只在催费页面使用，不在数据库和其他地方使用
     */
    private String stuTotalOwePeriodMoney;

    /**
     * 到现在这个学生缴纳了多少钱呢？
     */
    private String totalMoney;
    /**
     * 他的总学费多少钱
     */
    private String xfTotal;

    /**
     * 学生账户状态(0未开通、1已开通）
     */
    private int openHouseholdState;

    /*
     * 学生原班级
     * */
    private String oldCid;
    /*
     *学生原班级名称
     *  */
    private String oldCname;

    public String getOldCname() {
        return oldCname;
    }

    public void setOldCname(String oldCname) {
        this.oldCname = oldCname;
    }

    public String getOldCid() {
        return oldCid;
    }

    public void setOldCid(String oldCid) {
        this.oldCid = oldCid;
    }

    public int getOpenHouseholdState() {
        return openHouseholdState;
    }

    public void setOpenHouseholdState(int openHouseholdState) {
        this.openHouseholdState = openHouseholdState;
    }

    public String getXfTotal() {
        return xfTotal;
    }

    public void setXfTotal(String xfTotal) {
        this.xfTotal = xfTotal;
    }

    public String getStuTotalOwePeriodMoney() {
        return stuTotalOwePeriodMoney;
    }

    public void setStuTotalOwePeriodMoney(String stuTotalOwePeriodMoney) {
        this.stuTotalOwePeriodMoney = stuTotalOwePeriodMoney;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getAudCid() {
        return audCid;
    }

    public void setAudCid(String audCid) {
        this.audCid = audCid;
    }

    public String getClaId() {
        return claId;
    }

    public void setClaId(String claId) {
        this.claId = claId;
    }

    public String getClaName() {
        return claName;
    }

    public void setClaName(String claName) {
        this.claName = claName;
    }

    public String getStuHomeAddress() {
        return stuHomeAddress;
    }

    public void setStuHomeAddress(String stuHomeAddress) {
        this.stuHomeAddress = stuHomeAddress;
    }

    public List<BaiseeStudentFamily> getStuFamTel() {
        return stuFamTel;
    }

    public void setStuFamTel(List<BaiseeStudentFamily> stuFamTel) {
        this.stuFamTel = stuFamTel;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public String getStuAge() {
        return stuAge;
    }

    public void setStuAge(String stuAge) {
        this.stuAge = stuAge;
    }

    public String getStuMobile() {
        return stuMobile;
    }

    public void setStuMobile(String stuMobile) {
        this.stuMobile = stuMobile;
    }

    public String getStuIdNumber() {
        return stuIdNumber;
    }

    public void setStuIdNumber(String stuIdNumber) {
        this.stuIdNumber = stuIdNumber;
    }

    public String getStuStatus() {
        return stuStatus;
    }

    public void setStuStatus(String stuStatus) {
        this.stuStatus = stuStatus;
    }

    public String getStuType() {
        return stuType;
    }

    public void setStuType(String stuType) {
        this.stuType = stuType;
    }

    public String getStuFormalNo() {
        return stuFormalNo;
    }

    public void setStuFormalNo(String stuFormalNo) {
        this.stuFormalNo = stuFormalNo;
    }


    public String getStuNotCurrentPeriod() {
        return stuNotCurrentPeriod;
    }

    public void setStuNotCurrentPeriod(String stuNotCurrentPeriod) {
        this.stuNotCurrentPeriod = stuNotCurrentPeriod;
    }

    public String getStuSpecialty() {
        return stuSpecialty;
    }

    public void setStuSpecialty(String stuSpecialty) {
        this.stuSpecialty = stuSpecialty;
    }

    public String getStuBirthDate() {
        return stuBirthDate;
    }

    public void setStuBirthDate(String stuBirthDate) {
        this.stuBirthDate = stuBirthDate;
    }

    public String getStuAuditionStartTime() {
        return stuAuditionStartTime;
    }

    public void setStuAuditionStartTime(String stuAuditionStartTime) {
        this.stuAuditionStartTime = stuAuditionStartTime;
    }

    public String getStuAuditionEndTime() {
        return stuAuditionEndTime;
    }

    public void setStuAuditionEndTime(String stuAuditionEndTime) {
        this.stuAuditionEndTime = stuAuditionEndTime;
    }

    public String getStuEnrolmentTime() {
        return stuEnrolmentTime;
    }

    public void setStuEnrolmentTime(String stuEnrolmentTime) {
        this.stuEnrolmentTime = stuEnrolmentTime;
    }

    public String getStuStatusDate() {
        return stuStatusDate;
    }

    public void setStuStatusDate(String stuStatusDate) {
        this.stuStatusDate = stuStatusDate;
    }

    public String getStuLeaveStartTime() {
        return stuLeaveStartTime;
    }

    public void setStuLeaveStartTime(String stuLeaveStartTime) {
        this.stuLeaveStartTime = stuLeaveStartTime;
    }

    public String getStuLeaveEndTime() {
        return stuLeaveEndTime;
    }

    public void setStuLeaveEndTime(String stuLeaveEndTime) {
        this.stuLeaveEndTime = stuLeaveEndTime;
    }

    public String getAddParam() {
        return addParam;
    }

    public void setAddParam(String addParam) {
        this.addParam = addParam;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getTuId() {
        return tuId;
    }

    public void setTuId(String tuId) {
        this.tuId = tuId;
    }

    public String getTuitioinStatus() {
        return tuitioinStatus;
    }

    public void setTuitioinStatus(String tuitioinStatus) {
        this.tuitioinStatus = tuitioinStatus;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public String getStuRemark() {
        return stuRemark;
    }

    public void setStuRemark(String stuRemark) {
        this.stuRemark = stuRemark;
    }

    public String getStuProvince() {
        return stuProvince;
    }

    public void setStuProvince(String stuProvince) {
        this.stuProvince = stuProvince;
    }

    public String getStuCity() {
        return stuCity;
    }

    public void setStuCity(String stuCity) {
        this.stuCity = stuCity;
    }

    public String getStuArea() {
        return stuArea;
    }

    public void setStuArea(String stuArea) {
        this.stuArea = stuArea;
    }

    public String getStuNotPayment() {
        return stuNotPayment;
    }

    public void setStuNotPayment(String stuNotPayment) {
        this.stuNotPayment = stuNotPayment;
    }

    public String getStuAreaName() {
        return stuAreaName;
    }

    public void setStuAreaName(String stuAreaName) {
        this.stuAreaName = stuAreaName;
    }

    public String getTuitioinPeriodsNow() {
        return tuitioinPeriodsNow;
    }

    public void setTuitioinPeriodsNow(String tuitioinPeriodsNow) {
        this.tuitioinPeriodsNow = tuitioinPeriodsNow;
    }

    public String getTuStId() {
        return tuStId;
    }

    public void setTuStId(String tuStId) {
        this.tuStId = tuStId;
    }

    public BaiseeStudent() {

    }

    //声明一个学生带参构造方法，正式批量导入用
    public BaiseeStudent(String stuName, String stuSex, String claId, String idNumber, String stuMobile, String stuEnrolmentTime, String stuSpecialty, String stuRemark) {
        this.stuName = stuName;
        this.stuSex = stuSex;
        this.claId = claId;
        this.stuIdNumber = idNumber;
        this.stuMobile = stuMobile;
        this.stuEnrolmentTime = stuEnrolmentTime;
        this.stuSpecialty = stuSpecialty;
        this.stuRemark = stuRemark;
    }

    //声明一个学生带参构造方法，试听批量导入用
    public BaiseeStudent(String stuAuditionStartTime, String stuName, String stuSex, String stuBirthDate, String idNumber, String stuMobile, String audCid, String stuRemark, String a) {
        this.stuAuditionStartTime = stuAuditionStartTime;
        this.stuName = stuName;
        this.stuSex = stuSex;
        this.stuBirthDate = stuBirthDate;
        this.stuIdNumber = idNumber;
        this.stuMobile = stuMobile;
        this.audCid = audCid;
        this.stuRemark = stuRemark;
    }

    @Override
    public String toString() {
        return "BaiseeStudent{" +
                "stuId='" + stuId + '\'' +
                ", audCid='" + audCid + '\'' +
                ", claId='" + claId + '\'' +
                ", claName='" + claName + '\'' +
                ", stuProvince='" + stuProvince + '\'' +
                ", stuCity='" + stuCity + '\'' +
                ", stuArea='" + stuArea + '\'' +
                ", stuHomeAddress='" + stuHomeAddress + '\'' +
                ", stuFamTel=" + stuFamTel +
                ", stuName='" + stuName + '\'' +
                ", stuSex='" + stuSex + '\'' +
                ", stuAge='" + stuAge + '\'' +
                ", stuMobile='" + stuMobile + '\'' +
                ", stuIdNumber='" + stuIdNumber + '\'' +
                ", stuStatus='" + stuStatus + '\'' +
                ", stuType='" + stuType + '\'' +
                ", stuFormalNo='" + stuFormalNo + '\'' +
                ", stuSpecialty='" + stuSpecialty + '\'' +
                ", stuBirthDate='" + stuBirthDate + '\'' +
                ", stuAuditionStartTime='" + stuAuditionStartTime + '\'' +
                ", stuAuditionEndTime='" + stuAuditionEndTime + '\'' +
                ", stuEnrolmentTime='" + stuEnrolmentTime + '\'' +
                ", stuStatusDate='" + stuStatusDate + '\'' +
                ", stuLeaveStartTime='" + stuLeaveStartTime + '\'' +
                ", stuLeaveEndTime='" + stuLeaveEndTime + '\'' +
                ", addParam='" + addParam + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", tuId='" + tuId + '\'' +
                ", tuitioinStatus='" + tuitioinStatus + '\'' +
                ", tuitioinPeriodsNow='" + tuitioinPeriodsNow + '\'' +
                ", tuStId='" + tuStId + '\'' +
                ", returnStatus='" + returnStatus + '\'' +
                ", stuRemark='" + stuRemark + '\'' +
                ", stuAreaName='" + stuAreaName + '\'' +
                ", stuNotPayment='" + stuNotPayment + '\'' +
                ", stuNotCurrentPeriod='" + stuNotCurrentPeriod + '\'' +
                ", stuTotalOwePeriodMoney='" + stuTotalOwePeriodMoney + '\'' +
                ", totalMoney='" + totalMoney + '\'' +
                ", xfTotal='" + xfTotal + '\'' +
                ", openHouseholdState=" + openHouseholdState +
                ", oldCid='" + oldCid + '\'' +
                ", oldCname='" + oldCname + '\'' +
                '}';
    }
}
