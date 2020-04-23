package cn.baisee.oa.exception;

/**
 * 异常基类
 * @author Administrator
 *
 */
public class BaseException extends Exception {
	private static final long serialVersionUID = -8366705779104000511L;

	protected String code;

	public BaseException() {
		super();
	}

	public BaseException(String code) {
		super();
		this.code = code;
	}

	public BaseException(String code, Throwable e) {
		super(code, e);
		this.code = code;
	}
	
	public BaseException(Throwable e) {
		super(e);
	}

	@Override
	public String getMessage() {
		return code;
	}

	public String getCode() {
		return code;
	}

	@Override
	public String toString() {
		return code;
	}

}
