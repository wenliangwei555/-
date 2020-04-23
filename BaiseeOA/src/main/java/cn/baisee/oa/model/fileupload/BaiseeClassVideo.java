package cn.baisee.oa.model.fileupload;

/**
 * 视频上传实体
 * @author G
 *
 */
public class BaiseeClassVideo {
	private String id;    //主键
	private String infoId; //上传者ID
	private String url;  //上传路径
	private String orderNum; //排序编号
	private String updateTime; //更新时间
	private String bigTypeId; //科目最大节点
	private String videoName; //视频名称
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInfoId() {
		return infoId;
	}
	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getBigTypeId() {
		return bigTypeId;
	}
	public void setBigTypeId(String bigTypeId) {
		this.bigTypeId = bigTypeId;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	@Override
	public String toString() {
		return "BaiseeClassVideo [id=" + id + ", infoId=" + infoId + ", url=" + url + ", orderNum=" + orderNum
				+ ", updateTime=" + updateTime + ", bigTypeId=" + bigTypeId + ", videoName=" + videoName + "]";
	}
	
}
