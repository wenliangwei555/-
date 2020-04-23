package cn.baisee.oa.model;

import java.io.Serializable;
/**
 * 手机和身份证实体类
 * @author chenxuegang
 *
 */
public class PhoneAndID implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 133769548937255928L;
	private String phone;//手机号
	private String ID;//身份证
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
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
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
		PhoneAndID other = (PhoneAndID) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PhoneAndID [phone=" + phone + ", ID=" + ID + "]";
	}

	
}
