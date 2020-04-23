package cn.baisee.oa.model.empinfo;
/**
 * 员工教育信息表
 * @author LiFQ
 *
 */
public class EmployeeEducation {
	/**
	 * 本张表的主键
	 */
	private Integer PK_BEGI;//本张表的主键
	/**
	 * 员工信息表的主键
	 */
	private String empId;//员工信息表主键EID
	/**
	 * 教育开始时间
	 */
	private String educStartTime;//开始时间 START_TIME
	/**
	 * 教育结束时间
	 */
	private String educSendTime;//结束时间END_TIME
	/**
	 * 毕业院校
	 */
	private String empGraduatedSchool;//毕业院校 E_GRADUATED_SCHOOL
	/**
	 * 学历
	 */
	private String empDegree;//学历 E_DEGREE
	/**
	 * 专业
	 */
	private String empMajor;//专业  E_MAJOR
	/**
	 * 最后更新时间
	 */
	private String educUpdateTime; //更新时间UPDATE_TIME
	public Integer getPK_BEGI() {
		return PK_BEGI;
	}
	public void setPK_BEGI(Integer pK_BEGI) {
		PK_BEGI = pK_BEGI;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId == null ? null : empId.trim();
	}
	public String getEmpGraduatedSchool() {
		return empGraduatedSchool;
	}
	public void setEmpGraduatedSchool(String empGraduatedSchool) {
		this.empGraduatedSchool = empGraduatedSchool == null ? null : empGraduatedSchool.trim();
	}
	public String getEmpDegree() {
		return empDegree;
	}
	public void setEmpDegree(String empDegree) {
		this.empDegree = empDegree == null ? null : empDegree.trim();
	}
	public String getEmpMajor() {
		return empMajor;
	}
	public void setEmpMajor(String empMajor) {
		this.empMajor = empMajor == null ? null : empMajor.trim();
	}
	
	public String getEducStartTime() {
		if(educStartTime != null && educStartTime != "" && !educStartTime.equals("")) {
			StringBuffer sb = new StringBuffer(educStartTime.replace("-", ""));
			StringBuffer insertSB = sb.insert(4, "-").insert(7, "-");
			return insertSB.toString();
		}else {
			return educStartTime;
		}
		
	}
	public void setEducStartTime(String educStartTime) {
		String str =  educStartTime == null ? null : educStartTime.trim();
		if(str!=null){
			this.educStartTime = str.replace("-", "");
		}else {
			this.educStartTime = str;
		}
	}
	public String getEducSendTime() {
		if(educSendTime != null && educSendTime != "" && !educSendTime.equals("")) {
			StringBuffer sb = new StringBuffer(educSendTime.replace("-", ""));
			StringBuffer insertSB = sb.insert(4, "-").insert(7, "-");
			return insertSB.toString();
		}else {
			return educSendTime;
		}
		
	}
	public void setEducSendTime(String educSendTime) {
		String str =  educSendTime == null ? null : educSendTime.trim();
		if(str!=null){
			this.educSendTime = str.replace("-", "");
		}else {
			this.educSendTime = str;
		}
	}
	public String getEducUpdateTime() {
		return educUpdateTime;
	}
	public void setEducUpdateTime(String educUpdateTime) {
		this.educUpdateTime = educUpdateTime == null ? null : educUpdateTime.trim();
	}
	@Override
	public String toString() {
		return "EmployeeEducation [PK_BEGI=" + PK_BEGI + ", empId=" + empId
				+ ", educStartTime=" + educStartTime + ", educSendTime="
				+ educSendTime + ", empGraduatedSchool=" + empGraduatedSchool
				+ ", empDegree=" + empDegree + ", empMajor=" + empMajor
				+ ", educUpdateTime=" + educUpdateTime + "]";
	}
	public EmployeeEducation() {
		super();
	}
	public EmployeeEducation(String empId) {
		super();
		this.empId = empId;
	}
	
}
