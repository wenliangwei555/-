package cn.baisee.oa.model.empinfo;
/**
 * 员工家庭信息表
 * @author LiFQ
 *
 */
public class EmployeeFamily {
	/**
	 * 本表主键
	 */
	private Integer PK_BEFI;//主键ID
	/**
	 * 员工信息
	 */
	private String empId;//员工信息表主键
	/**
	 * 员工家长姓名
	 */
	private String empFamName;//员工家长姓名 E_FAM_NAME
	/**
	 * 员工家长性别
	 */
	private String empFamSex;//员工共家长性别E_FAM_SEX
	/**
	 * 家长电话
	 */
	private String empFamTel;//员工家长电话E_FAM_TEL
	/**
	 * 员工与家长的电话
	 */
	private String empFamRelation;//员工与家长的关系 E_FAM_RELATION
	/**
	 * 最后更新时间
	 */
	private String famUpdateTime; // 最后更新时间UPDATE_TIME
	public Integer getPK_BEFI() {
		return PK_BEFI;
	}
	public void setPK_BEFI(Integer pK_BEFI) {
		PK_BEFI = pK_BEFI;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId == null ? null : empId.trim();
	}
	public String getEmpFamName() {
		return empFamName;
	}
	public void setEmpFamName(String empFamName) {
		this.empFamName = empFamName == null ? null : empFamName.trim();
	}
	public String getEmpFamSex() {
		return empFamSex;
	}
	public void setEmpFamSex(String empFamSex) {
		this.empFamSex = empFamSex == null ? null : empFamSex.trim();
	}
	public String getEmpFamTel() {
		return empFamTel;
	}
	public void setEmpFamTel(String empFamTel) {
		this.empFamTel = empFamTel == null ? null : empFamTel.trim();
	}
	
	public String getEmpFamRelation() {
		return empFamRelation;
	}
	public void setEmpFamRelation(String empFamRelation) {
		this.empFamRelation = empFamRelation == null ? null : empFamRelation.trim();
	}
	public String getFamUpdateTime() {
		return famUpdateTime;
	}
	public void setFamUpdateTime(String famUpdateTime) {
		this.famUpdateTime = famUpdateTime == null ? null : famUpdateTime.trim();
	}
	@Override
	public String toString() {
		return "EmployeeFamily [PK_BEFI=" + PK_BEFI + ", empId=" + empId
				+ ", empFamName=" + empFamName + ", empFamSex=" + empFamSex
				+ ", empFamTel=" + empFamTel + ", empFamRelaton="
				+ empFamRelation + ", famUpdateTime=" + famUpdateTime + "]";
	}
	public EmployeeFamily() {
		super();
	}
	public EmployeeFamily(String empId) {
		super();
		this.empId = empId;
	}

}
