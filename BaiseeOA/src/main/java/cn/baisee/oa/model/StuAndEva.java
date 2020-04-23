package cn.baisee.oa.model;

public class StuAndEva {
   /**
    * 评论表的字段
    */
	private Integer evid;
	private String sid;
	private String emp_id;
	private Integer evType;
	private String evInfo;
	private String evDate;
	public Integer getEvid() {
		return evid;
	}
	public void setEvid(Integer evid) {
		this.evid = evid;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public Integer getEvType() {
		return evType;
	}
	public void setEvType(Integer evType) {
		this.evType = evType;
	}
	public String getEvInfo() {
		return evInfo;
	}
	public void setEvInfo(String evInfo) {
		this.evInfo = evInfo;
	}
	public String getEvDate() {
		return evDate;
	}
	public void setEvDate(String evDate) {
		this.evDate = evDate;
	}
	
	/**
	 * 学生表的字段
	 */
	
	private String sId;
	private String sName;
	public String getsId() {
		return sId;
	}
	public void setsId(String sId) {
		this.sId = sId;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	
}