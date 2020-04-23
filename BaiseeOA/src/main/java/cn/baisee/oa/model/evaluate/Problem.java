package cn.baisee.oa.model.evaluate;

import java.io.Serializable;


public class Problem implements Serializable{
	
	private int stID;   /*问题ID*/
	private String stTitle;   /*问题名*/
	private String stOptionA ;  /*选项A*/
	private String stOptionB; 	/*选项B*/
	private String stOptionC;	/*选项C*/
	private String stOptionD;	/*选项D*/
	private String Founder ;    /*创建人*/
	private String Modifier ;   /*修改人*/
	private String Creationtime ; /*创建时间*/
	private String Modificationtime;  /*修改时间*/

	
	public int getStID() {
		return stID;
	}
	public void setStID(int stID) {
		this.stID = stID;
	}
	public String getStTitle() {
		return stTitle;
	}
	public void setStTitle(String stTitle) {
		this.stTitle = stTitle;
	}
	public String getStOptionA() {
		return stOptionA;
	}
	public void setStOptionA(String stOptionA) {
		this.stOptionA = stOptionA;
	}
	public String getStOptionB() {
		return stOptionB;
	}
	public void setStOptionB(String stOptionB) {
		this.stOptionB = stOptionB;
	}
	public String getStOptionC() {
		return stOptionC;
	}
	public void setStOptionC(String stOptionC) {
		this.stOptionC = stOptionC;
	}
	public String getStOptionD() {
		return stOptionD;
	}
	public void setStOptionD(String stOptionD) {
		this.stOptionD = stOptionD;
	}
	public String getFounder() {
		return Founder;
	}
	public void setFounder(String founder) {
		Founder = founder;
	}
	public String getModifier() {
		return Modifier;
	}
	public void setModifier(String modifier) {
		Modifier = modifier;
	}
	public String getCreationtime() {
		return Creationtime;
	}
	public void setCreationtime(String creationtime) {
		Creationtime = creationtime;
	}
	public String getModificationtime() {
		return Modificationtime;
	}
	public void setModificationtime(String modificationtime) {
		Modificationtime = modificationtime;
	}
	@Override
	public String toString() {
		return "Problem [stID=" + stID + ", stTitle=" + stTitle + ", stOptionA=" + stOptionA + ", stOptionB="
				+ stOptionB + ", stOptionC=" + stOptionC + ", stOptionD=" + stOptionD + ", Founder=" + Founder
				+ ", Modifier=" + Modifier + ", Creationtime=" + Creationtime + ", Modificationtime=" + Modificationtime
				+ "]";
	}
	
	
}
