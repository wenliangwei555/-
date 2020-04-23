package cn.baisee.oa.exception;

import java.util.HashMap;

import cn.baisee.oa.utils.StringUtil;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;

/**
 * 描述:文件资源运行时操作异常类
 */
public class FileRuntimeException extends SysRuntimeException {

	private static final HashMap<Integer, String> errorMsgMap;

	static {
		errorMsgMap = new HashMap<Integer, String>();
		errorMsgMap.put(ChannelSftp.SSH_FX_OK, "SSH_FX_OK");
		errorMsgMap.put(ChannelSftp.SSH_FX_EOF, "SSH_FX_EOF");
		errorMsgMap.put(ChannelSftp.SSH_FX_NO_SUCH_FILE, "SSH_FX_NO_SUCH_FILE");
		errorMsgMap.put(ChannelSftp.SSH_FX_PERMISSION_DENIED,
				"SSH_FX_PERMISSION_DENIED");
		errorMsgMap.put(ChannelSftp.SSH_FX_FAILURE, "SSH_FX_FAILURE");
		errorMsgMap.put(ChannelSftp.SSH_FX_BAD_MESSAGE, "SSH_FX_BAD_MESSAGE");
		errorMsgMap.put(ChannelSftp.SSH_FX_NO_CONNECTION,
				"SSH_FX_NO_CONNECTION");
		errorMsgMap.put(ChannelSftp.SSH_FX_CONNECTION_LOST,
				"SSH_FX_CONNECTION_LOST");
		errorMsgMap.put(ChannelSftp.SSH_FX_OP_UNSUPPORTED,
				"SSH_FX_OP_UNSUPPORTED");
	}

	private static final long serialVersionUID = 9001724897408075889L;

	/**
	 * @param errorCode
	 *            异常代码
	 */
	public FileRuntimeException(String errorCode) {
		super(errorCode);
	}

	/**
	 * @param errorCode
	 *            异常代码
	 * @param errorMessage
	 *            异常信息
	 */
	public FileRuntimeException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	/**
	 * 
	 * @param cause
	 *            异常对象
	 */
	public FileRuntimeException(Throwable cause) {
		super(cause);
		if (cause instanceof SftpException) {
			int errId = ((SftpException) cause).id;
			String errorCode = errorMsgMap.get(errId);
			if (!StringUtil.isStrEmpty(errorCode)) {
				throw new FileRuntimeException(errorCode, cause);
			} else {
				throw new FileRuntimeException(cause);
			}
		} else {
			throw new SysRuntimeException(cause);
		}
	}

	/**
	 * @param errorCode
	 *            异常代码
	 * @param cause
	 *            异常对象
	 */
	public FileRuntimeException(String errorCode, Throwable cause) {
		super(errorCode, cause);
	}

	/**
	 * @param errorCode
	 *            异常代码
	 * @param errorMessage
	 *            异常信息
	 * @param cause
	 *            异常对象
	 */
	public FileRuntimeException(String errorCode, String errorMessage,
			Throwable cause) {
		super(errorCode, errorMessage, cause);
	}

	// /**
	// * @param errorCode 异常代码
	// */
	// public FileRuntimeException(String errorCode, String defaultErrMessage) {
	// super(errorCode,defaultErrMessage);
	// }

}
