package cn.baisee.oa.model.student;
/**
 * 退费规则的实体类
 * @author WANGBAOXIANG
 *
 */
public class BaiseeStudentRefund{
	
	// 退费ID
	private String rId;
	//退费名称
	private String rName;
	//退费公式
	private String refundFormula;
	//退费公式描述
	private String formulaDescribe;
	//更新时间
	private String updateTime;
	//失效时间
	private String invalidTime;
	// 学费id
	private String trId;
	
	public String getTrId() {
		return trId;
	}
	public void setTrId(String trId) {
		this.trId = trId;
	}
	public String getrId() {
		return rId;
	}
	public void setrId(String rId) {
		this.rId = rId;
	}
	public String getrName() {
		return rName;
	}
	public void setrName(String rName) {
		this.rName = rName;
	}
	public String getRefundFormula() {
		return refundFormula;
	}
	public void setRefundFormula(String refundFormula) {
		this.refundFormula = refundFormula;
	}
	public String getFormulaDescribe() {
		return formulaDescribe;
	}
	public void setFormulaDescribe(String formulaDescribe) {
		this.formulaDescribe = formulaDescribe;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getInvalidTime() {
		return invalidTime;
	}
	public void setInvalidTime(String invalidTime) {
		this.invalidTime = invalidTime;
	}

}
