package cn.baisee.oa.model.student;

public class BaiseeStudentReturnRule {
	private String rrid;
	private String tuId;
	private String returnType;
	private String returnValue;
	private String upDate;
	private String paymentAmount;
	private String returnTime;
	private String returnSum;
	private String rtId;
	
	
	public String getRtId() {
		return rtId;
	}

	public void setRtId(String rtId) {
		this.rtId = rtId;
	}

	public String getRrid() {
		return rrid;
	}

	public void setRrid(String rrid) {
		this.rrid = rrid;
	}

	public String getTuId() {
		return tuId;
	}

	public void setTuId(String tuId) {
		this.tuId = tuId;
	}

	public String getReturnType() {
		return returnType;
	}
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	public String getReturnValue() {
		return returnValue;
	}
	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}
	public String getUpDate() {
		return upDate;
	}
	public void setUpDate(String upDate) {
		this.upDate = upDate;
	}
	public String getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public String getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}
	public String getReturnSum() {
		return returnSum;
	}
	public void setReturnSum(String returnSum) {
		this.returnSum = returnSum;
	}
	@Override
	public String toString() {
		return "BaiseeStudentReturn [rrid=" + rrid + ", returnType=" + returnType + ", returnValue="
				+ returnValue + ", upDate=" + upDate + "]";
	}
	

}
