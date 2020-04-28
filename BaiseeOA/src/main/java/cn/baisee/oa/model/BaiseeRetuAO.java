package cn.baisee.oa.model;

public class BaiseeRetuAO {
	private String sId;
	
	//学生姓名
	private String sName;
	//学生试听时间
	private String sAuditionStartTime;
	//学生入学时间
	private String sEnrolmentTime;
	//学生年龄
	private String sAge;
	//返费状态
	private String returnStatus;
	//应返金额
	private String returnSum;
	//返费时间
	private String returnTime;
	//缴费金额
	private String tuitionSum;
	//学费学籍
	private String tuTypeMeaning;
	//入学开始时间
	private String enterStartTime;
	//入学结束时间
	private String enterEndTime;
	//试听开始时间
	private String audStartTime;
	//试听结束时间
	private String audEndTime;
	//实际返费金额
	private String actualSum;
	//未返金额
	private int notRetu;
	//地区
	private String area;
	//实际地区
	private String realArea;
	//备注1
	private String remarks;
	//备注2
	private String remarks2;
	public String getRealArea() {
		return realArea;
	}

	public void setRealArea(String realArea) {
		this.realArea = realArea;
	}

	public BaiseeRetuAO() {
		
	}
	
	public BaiseeRetuAO(String sName,String enterStartTime,String enterEndTime,
			String audStartTime,String audEndTime,String area) {
		this.sName=sName;
		this.enterStartTime=enterStartTime;
		this.enterEndTime=enterEndTime;
		this.audStartTime=audStartTime;
		this.audEndTime=audEndTime;
		this.area=area;
	}
	public String getsId() {
		return sId;
	}
	public void setsId(String sId) {
		this.sId = sId;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getTuTypeMeaning() {
		return tuTypeMeaning;
	}
	public void setTuTypeMeaning(String tuTypeMeaning) {
		this.tuTypeMeaning = tuTypeMeaning;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getsAuditionStartTime() {
		return sAuditionStartTime;
	}
	public void setsAuditionStartTime(String sAuditionStartTime) {
		this.sAuditionStartTime = sAuditionStartTime;
	}
	public String getsEnrolmentTime() {
		return sEnrolmentTime;
	}
	public void setsEnrolmentTime(String sEnrolmentTime) {
		this.sEnrolmentTime = sEnrolmentTime;
	}
	public String getsAge() {
		return sAge;
	}
	public void setsAge(String sAge) {
		this.sAge = sAge;
	}
	public String getReturnStatus() {
		return returnStatus;
	}
	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}
	public String getTuitionSum() {
		return tuitionSum;
	}
	public void setTuitionSum(String tuitionSum) {
		this.tuitionSum = tuitionSum;
	}
	public String getEnterStartTime() {
		return enterStartTime;
	}
	public void setEnterStartTime(String enterStartTime) {
		this.enterStartTime = enterStartTime;
	}
	public String getEnterEndTime() {
		return enterEndTime;
	}
	public void setEnterEndTime(String enterEndTime) {
		this.enterEndTime = enterEndTime;
	}
	public String getAudStartTime() {
		return audStartTime;
	}
	public void setAudStartTime(String audStartTime) {
		this.audStartTime = audStartTime;
	}
	public String getAudEndTime() {
		return audEndTime;
	}
	public void setAudEndTime(String audEndTime) {
		this.audEndTime = audEndTime;
	}
	public String getReturnSum() {
		return returnSum;
	}
	public void setReturnSum(String returnSum) {
		this.returnSum = returnSum;
	}
	public String getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}
	public String getActualSum() {
		return actualSum;
	}
	public void setActualSum(String actualSum) {
		this.actualSum = actualSum;
	}
	public int getNotRetu() {
		return notRetu;
	}
	public void setNotRetu(int notRetu) {
		this.notRetu = notRetu;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarks2() {
		return remarks2;
	}

	public void setRemarks2(String remarks2) {
		this.remarks2 = remarks2;
	}
	
}
