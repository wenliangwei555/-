package cn.baisee.oa.model;

import java.io.Serializable;

/**
 * 批量导入员工实体类
 * @author chenxuegang

 */
public class ImportEmpModel implements Serializable{
	/**
	 * 
	 */

	private String rowIndex;//行遍号
	private String empName;//姓名
	private String empSex;//性别
	private String empNation;//民族
	private String startDate ;//出生日期
	private String timeOfEntry;//入职时间
	private String phone;//手机号码
	private String ID;//身份证
	final private String nan="男";
	private static final long serialVersionUID = -4479547142051452542L;
	public String getNan() {
		return nan;
	}
	public String getRowIndex() {
		return rowIndex;
	}
	public void setRowIndex(String rowIndex) {
		this.rowIndex = rowIndex;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpSex() {
		return empSex;
	}
	public void setEmpSex(String empSex) {
		this.empSex = empSex;
	}
	public String getEmpNation() {
		return empNation;
	}
	public void setEmpNation(String empNation) {
		this.empNation = empNation;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getTimeOfEntry() {
		return timeOfEntry;
	}
	public void setTimeOfEntry(String timeOfEntry) {
		this.timeOfEntry = timeOfEntry;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		result = prime * result + ((empName == null) ? 0 : empName.hashCode());
		result = prime * result
				+ ((empNation == null) ? 0 : empNation.hashCode());
		result = prime * result + ((empSex == null) ? 0 : empSex.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result
				+ ((rowIndex == null) ? 0 : rowIndex.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result
				+ ((timeOfEntry == null) ? 0 : timeOfEntry.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImportEmpModel other = (ImportEmpModel) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		if (empName == null) {
			if (other.empName != null)
				return false;
		} else if (!empName.equals(other.empName))
			return false;
		if (empNation == null) {
			if (other.empNation != null)
				return false;
		} else if (!empNation.equals(other.empNation))
			return false;
		if (empSex == null) {
			if (other.empSex != null)
				return false;
		} else if (!empSex.equals(other.empSex))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (rowIndex == null) {
			if (other.rowIndex != null)
				return false;
		} else if (!rowIndex.equals(other.rowIndex))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (timeOfEntry == null) {
			if (other.timeOfEntry != null)
				return false;
		} else if (!timeOfEntry.equals(other.timeOfEntry))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ImportEmpModel [rowIndex=" + rowIndex + ", empName=" + empName
				+ ", empSex=" + empSex + ", empNation=" + empNation
				+ ", startDate=" + startDate + ", timeOfEntry=" + timeOfEntry
				+ ", phone=" + phone + ", ID=" + ID + "]";
	}


}
