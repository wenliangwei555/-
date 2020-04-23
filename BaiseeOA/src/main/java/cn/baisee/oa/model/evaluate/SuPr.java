package cn.baisee.oa.model.evaluate;

import java.io.Serializable;

/**
 * 评价问题ID  和       
 * @author yzk
 *
 */
public class SuPr implements Serializable{
			
	private String suID;
	private String stID;
	public String getSuID() {
		return suID;
	}
	public void setSuID(String suID) {
		this.suID = suID;
	}
	public String getStID() {
		return stID;
	}
	public void setStID(String stID) {
		this.stID = stID;
	}
	
	
}
