package cn.baisee.oa.model;

import java.util.List;

/**
 * json返回信息结构
 * @author think1
 *
 */
public class ReturnInfo {

	private int code = 1;
	private String message;
	private Object result;
	private Exception exception;
	
	private List<?> list;
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}

	public ReturnInfo() {}
	
	public ReturnInfo(int code) {
		this.code = code;
	}
	public int getCode() {
		return code;
	}
	public Object getResult() {
		return result;
	}
	public Exception getException() {
		return exception;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public void setException(Exception exception) {
		this.exception = exception;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
}
