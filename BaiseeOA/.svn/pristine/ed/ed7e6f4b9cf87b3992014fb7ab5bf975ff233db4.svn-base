package cn.baisee.oa.model.empinfo;
/**
 * 员工工作经历表
 * @author LiFQ
 *
 */
public class EmployeeWork {
	/**
	 * 本表主键
	 */
	private Integer PK_BEWI;//员工工作经历表主键
	/**
	 * 员工信息表主键
	 */
	private String empId;//员工共信息表主键EID
	/**
	 * 工作经历的开始时间
	 */
	private String workStartTime;//开始时间 START_TIME
	/**
	 * 工作经历的结束时间
	 */
	private String workEndTime;//结束时间END_TIME
	/**
	 * 在哪家公司任职
	 */
	private String empHisCompany;//哪家公司E_HIS_COMPANY
	/**
	 * 在公司的职务
	 */
	private String empPosition;//职务 E_POSITION
	/**
	 * 最后的更新时间
	 */
	private String workUpdateTime;// 更新时间 UPDATE_TIME
	public Integer getPK_BEWI() {
		return PK_BEWI;
	}
	public void setPK_BEWI(Integer pK_BEWI) {
		PK_BEWI = pK_BEWI;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId == null ? null : empId.trim();
	}
	public String getWorkStartTime() {
		if(workStartTime != null && workStartTime != "" && !workStartTime.equals("")) {
			StringBuffer sb = new StringBuffer(workStartTime.replace("-", ""));
			StringBuffer insertSB = sb.insert(4, "-").insert(7, "-");
			return insertSB.toString();
		}else {
			return workStartTime;
		}
		
	}
	public void setWorkStartTime(String workStartTime) {
		String str = workStartTime == null ? null : workStartTime.trim();
		if(str!=null){
			this.workStartTime = str.replace("-", "");
		}else {
			this.workStartTime=str;
		}
	}
	public String getWorkEndTime() {
		if(workEndTime != null && workEndTime != "" && !workEndTime.equals("")) {
			StringBuffer sb = new StringBuffer(workEndTime.replace("-", ""));
			StringBuffer insertSB = sb.insert(4, "-").insert(7, "-");
			return insertSB.toString();
		}else {
			return workEndTime;
		}
		
	}
	public void setWorkEndTime(String workEndTime) {
		String str = workEndTime == null ? null : workEndTime.trim();
		if(str!=null){
			this.workEndTime = str.replace("-", "");
		}else {
			this.workEndTime = str;
		}
	}
	public String getEmpHisCompany() {
		return empHisCompany;
	}
	public void setEmpHisCompany(String empHisCompany) {
		this.empHisCompany = empHisCompany == null ? null : empHisCompany.trim();
	}
	public String getEmpPosition() {
		return empPosition;
	}
	public void setEmpPosition(String empPosition) {
		this.empPosition = empPosition == null ? null : empPosition.trim();
	}
	public String getWorkUpdateTime() {
		return workUpdateTime;
	}
	public void setWorkUpdateTime(String workUpdateTime) {
		this.workUpdateTime = workUpdateTime == null ? null : workUpdateTime.trim();
	}
	@Override
	public String toString() {
		return "EmployeeWork [PK_BEWI=" + PK_BEWI + ", empId=" + empId
				+ ", workStartTime=" + workStartTime + ", workEndTime="
				+ workEndTime + ", empHisCompany=" + empHisCompany
				+ ", empPosition=" + empPosition + ", workUpdateTime="
				+ workUpdateTime + "]";
	}
	public EmployeeWork(String empId) {
		super();
		this.empId = empId;
	}
	public EmployeeWork() {
		super();
	}
	
	
	

}
