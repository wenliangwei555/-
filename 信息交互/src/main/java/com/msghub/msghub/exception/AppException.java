package com.msghub.msghub.exception;

import java.text.MessageFormat;

/**
 * <pre>
 * 定义 Utility 基础异常以及错误代码
 * 
 *  返回码编码规则：【信息类别(1位)】 + 【系统ID(7位)】 +  【返回码编号(5位]】
 *	信息类别：目前以E/W/I三个字母分别代表如下含义
 *  E：Error，表示系统应用错误
 *	W：Warning，表示系统应用警告
 *  I：一般提醒信息
 * 
 * 示例1:
 * 
 * 		AppException appException = new AppException("IICIPBS01001");
 * 
 * 
 * 示例2(异常转译（异常链）)
 * 		SQLException sqlException = new SQLException();
 * 		AppException appException = new AppException("IICIPBS01001", sqlException);
 *		
 * </pre>
 */
/**
 * @Author 温莨
 * @Date 2020/1/31 16:02
 * @Version 1.0
 */
public class AppException extends RuntimeException{

	private static final long serialVersionUID = -3448940577256191837L;
	/**
	 * 	异常码
	 */
	private String exceptionCode;
	/**
	 * 	可以抛
	 */
	private Throwable e;
	/**
	 * 构造器
	 * @param exceptionID 异常码
	 */
	public AppException(String exceptionID) {
		this.exceptionCode = exceptionID;
	}

	/**
	 * 构造器
	 * @param exceptionID  异常代码
	 * @param message 异常错误信息
	 */
	public AppException(String exceptionID, String message) {
		super(message);
		this.exceptionCode = exceptionID;
	}

	/**
	 * @param exceptionID  异常代码
	 * @param cause  抛
	 */
	public AppException(String exceptionID, Throwable cause) {
		super(cause);
		this.exceptionCode = exceptionID;
		this.e = cause;
	}

	/**
	 * 
	 * @param exceptionID 异常代码
	 * @param message  错误信息
	 * @param cause 抛
	 */
	public AppException(String exceptionID, String message, Throwable cause) {
		super(message, cause);
		this.exceptionCode = exceptionID;
		this.e = cause;
	}

	
	/**
	 * <pre>
	 * 提供异常信息拼接的能力
	 * 如：throw new DataServiceNotFoundException("[service={0}] is not existed!", new String[]{serviceName});
	 * </pre>
	 * @param exceptionID  异常编号
	 * @param message 异常原因
	 * @param param 在异常信息中提示的变量数据
	 */
	public AppException(String exceptionID, String message, Object[] param) {
		super(format(message, param));
		this.exceptionCode = exceptionID;
	}

	/**
	 *
	 * @param exceptionID
	 * @param message
	 * @param param
	 * @param cause
	 */
	public AppException(String exceptionID, String message, Object[] param, Throwable cause) {
		super(format(message, param), cause);
		this.exceptionCode = exceptionID;
		this.e = cause;
	}


	/**
	 * 设置异常标识号
	 * @aram code 异常标识号
	 */
	public void setExceptionCode(String code) {
		this.exceptionCode = code;
	}

	/**
	 * 取异常标识号
	 * @return 异常标识号
	 */
	public String getExceptionCode() {
		return exceptionCode;
	}

	public Throwable getThrowable(){
		return e;
	}

	/**
	 *
	 * @param message
	 * @param params
	 * @return
	 */
	static String format(String message, Object[] params) {
		if (message != null && message.trim().length() > 0) {
			if (params != null && params.length > 0) {
				return new MessageFormat(message).format(params);
			}
		}
		return message;
	}

}
