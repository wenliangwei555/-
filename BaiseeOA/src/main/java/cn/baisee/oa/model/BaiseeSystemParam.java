package cn.baisee.oa.model;

import java.io.Serializable;

/**
 * 系统参数
 * 
 * @author Administrator
 *
 */
public class BaiseeSystemParam implements Serializable  {
	private static final long serialVersionUID = 1L;
	private String paramCode;

	private String paramName;

	private String paramValue;

	private String paramDesc;

	private String updateTime;

	public String getParamCode() {
		return paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getParamDesc() {
		return paramDesc;
	}

	public void setParamDesc(String paramDesc) {
		this.paramDesc = paramDesc;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}
