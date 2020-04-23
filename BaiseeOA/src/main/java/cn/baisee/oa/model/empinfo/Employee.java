package cn.baisee.oa.model.empinfo;

import java.util.List;

/**
 * 员工信息表
 *
 * @author LiFQ
 */
public class Employee {
    /**
     * 员工主键
     */
    private String empId;//员工表主键 EID
    /**
     * 员工姓名
     */
    private String empName;//员工共姓名 E_NAME
    /**
     * 员工性别
     */
    private String empSex;//员工性别 E_SEX
    /**
     * 员工民族
     */
    private String empNation;//员工名族 NATION
    /**
     * 员工出生日期
     */
    private String empHireDate;//员工出生日期  E_HIRE_DATE
    /**
     * 员工的 在职或者离职状态
     */
    private String empStatus;//员工的补充表的状态
    /**
     * 员工入职日期
     */
    private String empRegTime;//员工入职日期 E_REG_TIME
    /**
     * 员工离职日期
     */
    private String empLeaveTime;//员工离职日期 E_LEAVE_TIME
    /**
     * 员工照片
     */
    private String empHeadPhotoDir;//员工照片 HEAD_PHOTO_DIR
    /**
     * 员工手机号码
     */
    private String empMobile;//员工手机号码MOBILE
    /**
     * 员工的身份证号
     */
    private String empIdNumber;//员工身份证号码
    /**
     * 更新时间
     */
    private String updateTime;//更新时间  UPDATE_TIME
    private String empJob;//职位
    private String empDept;//所属部门
    private String empHomeAddress;//员工的家庭住址

    public String getEmpHomeAddress() {
        return empHomeAddress;
    }

    public void setEmpHomeAddress(String empHomeAddress) {
        this.empHomeAddress = empHomeAddress;
    }

    public String getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(String empStatus) {
        this.empStatus = empStatus == null ? null : empStatus.trim();
    }


    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public String getEmpSex() {
        return empSex;
    }

    public void setEmpSex(String empSex) {
        this.empSex = empSex == null ? null : empSex.trim();
    }

    public String getEmpNation() {
        return empNation;
    }

    public void setEmpNation(String empNation) {
        this.empNation = empNation == null ? null : empNation.trim();
    }

    public String getEmpHireDate() {
        if (empHireDate != null && empHireDate != "" && !empHireDate.equals("")) {
            StringBuffer sb = new StringBuffer(empHireDate.replace("-", ""));
            StringBuffer insertSB = sb.insert(4, "-").insert(7, "-");
            return insertSB.toString();
        } else {
            return empHireDate;
        }

    }

    public void setEmpHireDate(String empHireDate) {
        String str = empHireDate == null ? null : empHireDate.trim();
        if (str != null) {
            this.empHireDate = str.replace("-", "");
        } else {
            this.empHireDate = str;
        }

    }

    public String getEmpRegTime() {
        if (empRegTime != null && empRegTime != "" && !empRegTime.equals("")) {
            StringBuffer sb = new StringBuffer(empRegTime.replace("-", ""));
            StringBuffer insertSB = sb.insert(4, "-").insert(7, "-");
            return insertSB.toString();
        } else {
            return empRegTime;
        }

    }

    public void setEmpRegTime(String empRegTime) {
        String str = empRegTime == null ? null : empRegTime.trim();
        if (str != null) {
            this.empRegTime = str.replace("-", "");
        } else {
            this.empRegTime = str;
        }
    }

    public String getEmpLeaveTime() {
        return empLeaveTime;
		/*if(!empLeaveTime.equals("") && empLeaveTime != null && empLeaveTime != "") {
			StringBuffer sb = new StringBuffer(empLeaveTime.replace("-", ""));
			StringBuffer insertSB = sb.insert(4, "-").insert(7, "-");
			return insertSB.toString();
		}else {
			return empLeaveTime;
		}*/
    }

    public void setEmpLeaveTime(String empLeaveTime) {
     /*   String str = empLeaveTime == null ? null : empLeaveTime.trim();
        if (str != null) {
            this.empLeaveTime = str.replace("-", "");
        } else {*/
        this.empLeaveTime = empLeaveTime;
        //  }
    }

    public String getEmpHeadPhotoDir() {
        return empHeadPhotoDir;
    }

    public void setEmpHeadPhotoDir(String empHeadPhotoDir) {
        this.empHeadPhotoDir = empHeadPhotoDir == null ? null : empHeadPhotoDir.trim();
    }

    public String getEmpMobile() {
        return empMobile;
    }

    public void setEmpMobile(String empMobile) {
        this.empMobile = empMobile == null ? null : empMobile.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public String getEmpIdNumber() {
        return empIdNumber;
    }

    public void setEmpIdNumber(String empIdNumber) {
        this.empIdNumber = empIdNumber == null ? null : empIdNumber.trim();
    }

    /**
     * 多个家庭信息表
     */
    private List<EmployeeFamily> families;//一对多，员工可能有多个家庭信息表
    /**
     * 工作经历表
     */
    private EmployeeWork work;//工作经表
    /**
     * 教育表
     */
    private EmployeeEducation education;//教育表
    /**
     * 补充表
     */
    private EmployeeSupplement supplement;//补充表

    public List<EmployeeFamily> getFamilies() {
        return families;
    }

    public void setFamilies(List<EmployeeFamily> families) {
        this.families = families;
    }

    public EmployeeWork getWork() {
        return work;
    }

    public void setWork(EmployeeWork work) {
        this.work = work;
    }

    public EmployeeEducation getEducation() {
        return education;
    }

    public void setEducation(EmployeeEducation education) {
        this.education = education;
    }

    public EmployeeSupplement getSupplement() {
        return supplement;
    }

    public void setSupplement(EmployeeSupplement supplement) {
        this.supplement = supplement;
    }

    /**
     * 构造方法
     */
    public Employee() {
    }

    public Employee(String empId) {
        super();
        this.empId = empId;
    }

    public String getEmpJob() {
        return empJob;
    }

    public void setEmpJob(String empJob) {
        this.empJob = empJob;
    }

    public String getEmpDept() {
        return empDept;
    }

    public void setEmpDept(String empDept) {
        this.empDept = empDept;
    }

    @Override
    public String toString() {
        return "Employee [empId=" + empId + ", empName=" + empName
                + ", empSex=" + empSex + ", empNation=" + empNation
                + ", empHireDate=" + empHireDate + ", empStatus=" + empStatus
                + ", empRegTime=" + empRegTime + ", empLeaveTime="
                + empLeaveTime + ", empHeadPhotoDir=" + empHeadPhotoDir
                + ", empMobile=" + empMobile + ", empIdNumber=" + empIdNumber
                + ", updateTime=" + updateTime + ", empJob=" + empJob
                + ", empDept=" + empDept + ", empHomeAddress=" + empHomeAddress
                + ", families=" + families + ", work=" + work + ", education="
                + education + ", supplement=" + supplement + "]";
    }


}
