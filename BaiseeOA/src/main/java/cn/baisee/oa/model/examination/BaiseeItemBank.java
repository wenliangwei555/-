package cn.baisee.oa.model.examination;

import java.io.Serializable;

/**
 * 题库表
 * @author jxx
 *
 */
public class BaiseeItemBank implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String iId;			
	private String subject;		 //题目
	private String selectOne;	 //选择一
	private String selectTwo;	 //选择二
	private String selectThree;  //选择三
	private String selectFour;   //选择四
	private String answer;	     //答案
	private String projectAnalysis;//课题分析
	private String did;			 //(分类)
	private String createName;	 //创建人
	private String createTs;	 //createTs
	private String dictValue; // 字典值 / 试题分类
	private String updateTs; // 更新时间
	
	public String getUpdateTs() {
		return updateTs;
	}
	public void setUpdateTs(String updateTs) {
		this.updateTs = updateTs;
	}
	public String getDictValue() {
		return dictValue;
	}
	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}
	public String getiId() {
		return iId;
	}
	public void setiId(String iId) {
		this.iId = iId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSelectOne() {
		return selectOne;
	}
	public void setSelectOne(String selectOne) {
		this.selectOne = selectOne;
	}
	public String getSelectTwo() {
		return selectTwo;
	}
	public void setSelectTwo(String selectTwo) {
		this.selectTwo = selectTwo;
	}
	public String getSelectThree() {
		return selectThree;
	}
	public void setSelectThree(String selectThree) {
		this.selectThree = selectThree;
	}
	public String getSelectFour() {
		return selectFour;
	}
	public void setSelectFour(String selectFour) {
		this.selectFour = selectFour;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getProjectAnalysis() {
		return projectAnalysis;
	}
	public void setProjectAnalysis(String projectAnalysis) {
		this.projectAnalysis = projectAnalysis;
	}
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public String getCreateTs() {
		return createTs;
	}
	public void setCreateTs(String createTs) {
		this.createTs = createTs;
	}
	@Override
	public String toString() {
		return "BaiseeItemBank [iId=" + iId + ", subject=" + subject + ", selectOne=" + selectOne + ", selectTwo="
				+ selectTwo + ", selectThree=" + selectThree + ", selectFour=" + selectFour + ", answer=" + answer
				+ ", projectAnalysis=" + projectAnalysis + ", did=" + did + ", createName=" + createName + ", createTs="
				+ createTs + "]";
	}
	
}
