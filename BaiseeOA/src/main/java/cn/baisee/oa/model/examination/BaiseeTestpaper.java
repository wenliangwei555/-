package cn.baisee.oa.model.examination;
/**
 * 试卷表
 * @author jxx
 *
 */
public class BaiseeTestpaper {
	private String tid;		//主键
	private String createUser;// 创建人
	private String paperName;//试卷名称
	private String createTs; //	创建时间
	private String updateTs; // 更新时间
	private String sort; // 排序
	private String examinationType;//考试类型
	private String stateOfOwnership;//考试类型
	public String getStateOfOwnership() {
		return stateOfOwnership;
	}
	public void setStateOfOwnership(String stateOfOwnership) {
		this.stateOfOwnership = stateOfOwnership;
	}
	public String getExaminationType() {
		return examinationType;
	}
	public void setExaminationType(String examinationType) {
		this.examinationType = examinationType;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getUpdateTs() {
		return updateTs;
	}
	public void setUpdateTs(String updateTs) {
		this.updateTs = updateTs;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getPaperName() {
		return paperName;
	}
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	public String getCreateTs() {
		return createTs;
	}
	public void setCreateTs(String createTs) {
		this.createTs = createTs;
	}
}
