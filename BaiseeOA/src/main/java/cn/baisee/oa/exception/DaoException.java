package cn.baisee.oa.exception;

public class DaoException extends BaseException {
	private static final long serialVersionUID = -8366705779104000511L; 
	public DaoException(String code) {
		super(code);
	}
	
	public DaoException(String message, Throwable e) {
		super(message, e);
	}
}
