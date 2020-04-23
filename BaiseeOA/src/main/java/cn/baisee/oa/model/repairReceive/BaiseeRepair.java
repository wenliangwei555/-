package cn.baisee.oa.model.repairReceive;

import java.util.Date;

/**
 * 报修物品表  baisee_repair
 * @author liangfeng
 *
 */
public class BaiseeRepair {
	
	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 报修人id
	 */
	private String cId;
	/**
	 * 报修人名称
	 */
	private String cName;
	/**
	 * 报修人部门名称
	 */
	private String deptId;
	/**
	 * 报修人部门名称
	 */
	private String deptName;
	/**
	 * 报修物品ID
	 */
	private String goodsId;
	/**
	 * 报修物品名称
	 */
	private String goodsName;
	/**
	 * 报修物品数量
	 */
	private String goodsCount;
	/**
	 * 报修物品的照片
	 */
	private String imgUrl;
	/**
	 * 备注
	 */
	private String remarks;
	/**
	 * 报修状态0未维修1正在处理2维修完成
	 */
	private String state;
	/**
	 * 维修人id
	 */
	private String workerId;
	/**
	 * 维修人名称
	 */
	private String workerName;
	/**
	 * 报修日期
	 */
	private Date goodsTime;
	/**
	 * 最终处理时间
	 */
	private Date lastTime;
	/**
	 * 最终处理人
	 */
	private String lastPersonId;
	/**
	 * 最终处理人
	 */
	private String lastPerson;
	/**
	 * 维修建议
	 */
	private String views;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsCount() {
		return goodsCount;
	}
	public void setGoodsCount(String goodsCount) {
		this.goodsCount = goodsCount;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getWorkerId() {
		return workerId;
	}
	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}
	public String getWorkerName() {
		return workerName;
	}
	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}
	public Date getGoodsTime() {
		return goodsTime;
	}
	public void setGoodsTime(Date goodsTime) {
		this.goodsTime = goodsTime;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	public String getLastPersonId() {
		return lastPersonId;
	}
	public void setLastPersonId(String lastPersonId) {
		this.lastPersonId = lastPersonId;
	}
	public String getLastPerson() {
		return lastPerson;
	}
	public void setLastPerson(String lastPerson) {
		this.lastPerson = lastPerson;
	}
	public String getViews() {
		return views;
	}
	public void setViews(String views) {
		this.views = views;
	}
}
