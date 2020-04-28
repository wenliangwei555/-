package cn.baisee.oa.core.file;

import java.util.HashMap;
import java.util.Map;

import cn.baisee.oa.constants.FileConstant;
import cn.baisee.oa.exception.FileRuntimeException;


/**
 * 描述:文件访问的服务器配置信息基类
 */
public abstract class FileXmlConfigBase {

	/** 存储服务器类型字符串表示与枚举类型的对应关系 */
	private static final Map<String, FileServerTypes> serverTypeMap;

	/** 定义支持的文件服务器类型 */
	public static enum FileServerTypes {
		/** LocalApp本地应用服务器 */
		LocalApp,

		/** sftp服务器 */
		SFTP,
	}

	/** 本地服务器类型 */
	public static final String CONST_Local = "local";

	/** sftp服务器类型 */
	public static final String CONST_SFTP = "sftp";
	static {
		serverTypeMap = new HashMap<String, FileServerTypes>();
		serverTypeMap.put(CONST_Local, FileServerTypes.LocalApp);
		serverTypeMap.put(CONST_SFTP, FileServerTypes.SFTP);
	}

	/**
	 * 获取服务器类型
	 * 
	 * @return
	 */
	public abstract String getServerType();

	/**
	 * 获取服务器的枚举类型
	 * 
	 * @return
	 */
	public FileServerTypes getServerTypeEnum() {
		String serverType = getServerType();
		if (serverTypeMap.containsKey(serverType)) {
			return serverTypeMap.get(serverType);
		} else {
			throw new FileRuntimeException("E90018",
					FileConstant.FILE_SERVER_TYPE_ERROR + serverType
							+ FileConstant.CURR_SUPPORTED_TYPE
							+ serverTypeMap.keySet());
		}
	}

}
