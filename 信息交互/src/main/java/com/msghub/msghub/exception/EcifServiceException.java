package com.msghub.msghub.exception;

/**
 * service层异常
 * @author Administrator
 *
 */
/**
 * @Author 温莨
 * @Date 2020/1/31 16:02
 * @Version 1.0
 */
public class EcifServiceException extends BaseException {

	private static final long serialVersionUID = -8366705779104000511L;

	public EcifServiceException(String code) {
		super(code);
	}
	
	public EcifServiceException(String code, Exception e) {
		super(code, e);
	}
}
