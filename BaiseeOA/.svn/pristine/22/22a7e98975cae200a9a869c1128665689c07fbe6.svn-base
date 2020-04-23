package cn.baisee.oa.model;

import java.io.Serializable;
/**
 * 详细账单实体类
 * @author chenxuegang
 *
 */
public class DetailAccount implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2076342245254057079L;
	private String serviceID;    //业务流水号
	private String transactionID;//交易流水号
	private String userID;       //用户编号
	private String amount;       //单笔金额 
	private String transactionType;//交易类型
	private String remarks;//备注
	private String time;//时间
	private String STID;
	
	public String getSTID() {
		return STID;
	}
	public void setSTID(String sTID) {
		STID = sTID;
	}
	public String getServiceID() {
		return serviceID;
	}
	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
		result = prime * result
				+ ((serviceID == null) ? 0 : serviceID.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result
				+ ((transactionID == null) ? 0 : transactionID.hashCode());
		result = prime * result
				+ ((transactionType == null) ? 0 : transactionType.hashCode());
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
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
		DetailAccount other = (DetailAccount) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (remarks == null) {
			if (other.remarks != null)
				return false;
		} else if (!remarks.equals(other.remarks))
			return false;
		if (serviceID == null) {
			if (other.serviceID != null)
				return false;
		} else if (!serviceID.equals(other.serviceID))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (transactionID == null) {
			if (other.transactionID != null)
				return false;
		} else if (!transactionID.equals(other.transactionID))
			return false;
		if (transactionType == null) {
			if (other.transactionType != null)
				return false;
		} else if (!transactionType.equals(other.transactionType))
			return false;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "DetailAccount [serviceID=" + serviceID + ", transactionID="
				+ transactionID + ", userID=" + userID + ", amount=" + amount
				+ ", transactionType=" + transactionType + ", remarks="
				+ remarks + ", time=" + time + "]";
	}

	
	
}
