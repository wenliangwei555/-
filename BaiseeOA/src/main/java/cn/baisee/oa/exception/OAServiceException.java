package cn.baisee.oa.exception;

/**
 * service层异常
 * @author Administrator
 *
 */
public class OAServiceException extends BaseException {
	private static final long serialVersionUID = -8366705779104000511L; 
	public OAServiceException(String code) {
		super(code);
	}
	
	public OAServiceException(String code, Exception e) {
		super(code, e);
	}
}
