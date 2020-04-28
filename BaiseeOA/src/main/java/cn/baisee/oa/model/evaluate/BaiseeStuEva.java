package cn.baisee.oa.model.evaluate;

import java.io.Serializable;

/**
 * 评论  POJO
 * @author yzk
 *
 */
public class BaiseeStuEva implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6916815479110098821L;
	/**
	 * 
	 */
	private String eId;  /*评价ID*/
	private String csId;  /*课程ID*/
	private String csName; /*课程名*/
	private String content; /*内容*/
	private String className;  /*班级名*/
	private String	stuId;  /*学生ID*/
	private String creationTime; /*评论时间*/
	private String updateTime;   /*更新时间*/
	private String sord;    /*排序字段*/
	private int state;  	/*评论状态*/
	private String titleName; /*标题名*/
	private String evaName;//评价人
	private String ecid;//序号
	public String geteId() {
		return eId;
	}
	public void seteId(String eId) {
		this.eId = eId;
	}
	public String getCsId() {
		return csId;
	}
	public void setCsId(String csId) {
		this.csId = csId;
	}
	public String getCsName() {
		return csName;
	}
	public void setCsName(String csName) {
		this.csName = csName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public String getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	public String getEvaName() {
		return evaName;
	}
	public void setEvaName(String evaName) {
		this.evaName = evaName;
	}
	public String getEcid() {
		return ecid;
	}
	public void setEcid(String ecid) {
		this.ecid = ecid;
	}
	@Override
	public String toString() {
		return "BaiseeStuEva [eId=" + eId + ", csId=" + csId + ", csName="
				+ csName + ", content=" + content + ", className=" + className
				+ ", stuId=" + stuId + ", creationTime=" + creationTime
				+ ", updateTime=" + updateTime + ", sord=" + sord + ", state="
				+ state + ", titleName=" + titleName + ", evaName=" + evaName
				+ ", ecid=" + ecid + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((className == null) ? 0 : className.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result
				+ ((creationTime == null) ? 0 : creationTime.hashCode());
		result = prime * result + ((csId == null) ? 0 : csId.hashCode());
		result = prime * result + ((csName == null) ? 0 : csName.hashCode());
		result = prime * result + ((eId == null) ? 0 : eId.hashCode());
		result = prime * result + ((ecid == null) ? 0 : ecid.hashCode());
		result = prime * result + ((evaName == null) ? 0 : evaName.hashCode());
		result = prime * result + ((sord == null) ? 0 : sord.hashCode());
		result = prime * result + state;
		result = prime * result + ((stuId == null) ? 0 : stuId.hashCode());
		result = prime * result
				+ ((titleName == null) ? 0 : titleName.hashCode());
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
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
		BaiseeStuEva other = (BaiseeStuEva) obj;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (creationTime == null) {
			if (other.creationTime != null)
				return false;
		} else if (!creationTime.equals(other.creationTime))
			return false;
		if (csId == null) {
			if (other.csId != null)
				return false;
		} else if (!csId.equals(other.csId))
			return false;
		if (csName == null) {
			if (other.csName != null)
				return false;
		} else if (!csName.equals(other.csName))
			return false;
		if (eId == null) {
			if (other.eId != null)
				return false;
		} else if (!eId.equals(other.eId))
			return false;
		if (ecid == null) {
			if (other.ecid != null)
				return false;
		} else if (!ecid.equals(other.ecid))
			return false;
		if (evaName == null) {
			if (other.evaName != null)
				return false;
		} else if (!evaName.equals(other.evaName))
			return false;
		if (sord == null) {
			if (other.sord != null)
				return false;
		} else if (!sord.equals(other.sord))
			return false;
		if (state != other.state)
			return false;
		if (stuId == null) {
			if (other.stuId != null)
				return false;
		} else if (!stuId.equals(other.stuId))
			return false;
		if (titleName == null) {
			if (other.titleName != null)
				return false;
		} else if (!titleName.equals(other.titleName))
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		return true;
	}
	
	
	
}

