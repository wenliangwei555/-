package cn.baisee.oa.model.repairReceive;

import java.util.Date;

/**
 * 报修字典-故障地点表	baisee_repair_type
 * @author liangfeng
 *
 */
public class BaiseeRepairType {
	
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 校区id
	 */
	private String pId;
	/**
	 * 校区名称
	 */
	private String pName;
	/**
	 * 报修类型名称
	 */
	private String tName;
	/**
	 * 创建人id
	 */
	private String cId;
	/**
	 * 创建人名称
	 */
	private String cName;
	/**
	 * 更新人人id
	 */
	private String uId;
	/**
	 * 更新人名称
	 */
	private String uName;
	/**
	 * 创建时间
	 */
	private Date cTime;
	/**
	 * 更新时间
	 */
	private Date uTime;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getcId() {
		return cId;
	}
	public void setcId(String cId) {
		this.cId = cId;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public Date getcTime() {
		return cTime;
	}
	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}
	public Date getuTime() {
		return uTime;
	}
	public void setuTime(Date uTime) {
		this.uTime = uTime;
	}
}
