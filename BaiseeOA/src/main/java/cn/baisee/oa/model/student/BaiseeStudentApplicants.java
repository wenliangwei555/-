package cn.baisee.oa.model.student;

import java.io.Serializable;

/**
 * 预报名学生
 * @author chenxuegang
 *
 */
public class BaiseeStudentApplicants implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -521615795280050840L;
	/**
	 * 学号
	 */
	private String id;
	/**
	 * 姓名
	 */
	private String stuName;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 电话
	 */
	private String stuMobile;
	/**
	 * 身份证
	 */
	private String stuIdNumber;
	/**
	 * 预交金额
	 */
	private String prepaidAmount;
	/**
	 * 省份
	 */
	private String province;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 所属区县
	 */
	private String area;
	/**
	 * 家庭住址
	 */
	private String address;
	/**
	 * 备注
	 */
	private String remarks;
	/**
	 * 学生状态
	 */
	private String status;
	/**
	 * 创建人
	 */
	private String createdUser;
	/**
	 * 创建时间
	 */
	private String stuAuditionStartTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
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
	public String getPrepaidAmount() {
		return prepaidAmount;
	}
	public void setPrepaidAmount(String prepaidAmount) {
		this.prepaidAmount = prepaidAmount;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public String getStuAuditionStartTime() {
		return stuAuditionStartTime;
	}
	public void setStuAuditionStartTime(String stuAuditionStartTime) {
		this.stuAuditionStartTime = stuAuditionStartTime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result
				+ ((createdUser == null) ? 0 : createdUser.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((prepaidAmount == null) ? 0 : prepaidAmount.hashCode());
		result = prime * result
				+ ((province == null) ? 0 : province.hashCode());
		result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime
				* result
				+ ((stuAuditionStartTime == null) ? 0 : stuAuditionStartTime
						.hashCode());
		result = prime * result
				+ ((stuIdNumber == null) ? 0 : stuIdNumber.hashCode());
		result = prime * result
				+ ((stuMobile == null) ? 0 : stuMobile.hashCode());
		result = prime * result + ((stuName == null) ? 0 : stuName.hashCode());
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
		BaiseeStudentApplicants other = (BaiseeStudentApplicants) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (createdUser == null) {
			if (other.createdUser != null)
				return false;
		} else if (!createdUser.equals(other.createdUser))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (prepaidAmount == null) {
			if (other.prepaidAmount != null)
				return false;
		} else if (!prepaidAmount.equals(other.prepaidAmount))
			return false;
		if (province == null) {
			if (other.province != null)
				return false;
		} else if (!province.equals(other.province))
			return false;
		if (remarks == null) {
			if (other.remarks != null)
				return false;
		} else if (!remarks.equals(other.remarks))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (stuAuditionStartTime == null) {
			if (other.stuAuditionStartTime != null)
				return false;
		} else if (!stuAuditionStartTime.equals(other.stuAuditionStartTime))
			return false;
		if (stuIdNumber == null) {
			if (other.stuIdNumber != null)
				return false;
		} else if (!stuIdNumber.equals(other.stuIdNumber))
			return false;
		if (stuMobile == null) {
			if (other.stuMobile != null)
				return false;
		} else if (!stuMobile.equals(other.stuMobile))
			return false;
		if (stuName == null) {
			if (other.stuName != null)
				return false;
		} else if (!stuName.equals(other.stuName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "BaiseeStudentApplicants [id=" + id + ", stuName=" + stuName
				+ ", sex=" + sex + ", stuMobile=" + stuMobile
				+ ", stuIdNumber=" + stuIdNumber + ", prepaidAmount="
				+ prepaidAmount + ", province=" + province + ", city=" + city
				+ ", area=" + area + ", address=" + address + ", remarks="
				+ remarks + ", status=" + status + ", createdUser="
				+ createdUser + ", stuAuditionStartTime="
				+ stuAuditionStartTime + "]";
	}

	

	

}
