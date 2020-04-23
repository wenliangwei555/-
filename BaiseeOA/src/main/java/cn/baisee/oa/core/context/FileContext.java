package cn.baisee.oa.core.context;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.baisee.oa.constants.FileConstant;
import cn.baisee.oa.constants.SourceConstant;
import cn.baisee.oa.core.file.SSHClientUtil;
import cn.baisee.oa.core.file.SSHParamModel;
import cn.baisee.oa.exception.FileRuntimeException;
import cn.baisee.oa.utils.FileUtil;
import cn.baisee.oa.utils.StringUtil;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class FileContext {

	private static final Log logger = LogFactory.getLog(FileContext.class);
	/** 本地文件操作根目录 */
	private static String basePath;

	/** 服务器文件操作根目录存储,key对应服务器配置beandid，value对应根目录赋值 */
	private static HashMap<String, String> servBasePath = null;
	
	/** sshSession池,启动时完成初始化工作 */
	private static ConcurrentHashMap<SSHParamModel, Session> sshSessionPool = null;
	
	public FileContext(String basePathStr, Set<SSHParamModel> sshParamModelSet,
			HashMap<String, String> servBasePathTmp) {
		synchronized (FileContext.class) {// synchronized
			if (basePath == null) {
				basePath = basePathStr;
			} else {// 代码控制只能初始化一次
				throw new FileRuntimeException(
						SourceConstant.OBJECT_MULTI_CREATE_ERROR);
			}
			if (sshSessionPool == null) {
				sshSessionPool = new ConcurrentHashMap<SSHParamModel, Session>();
				for (SSHParamModel sshParamModel : sshParamModelSet) {
					String keyFile = sshParamModel.getKeyFile();
					if (!StringUtil.isStrEmpty(keyFile)) {
						keyFile = System
								.getProperty(SourceConstant.WebApp_SysKey)
								+ FileUtil.fileSplit + keyFile;
						sshParamModel.setKeyFile(keyFile);
					}
					sshSessionPool.put(sshParamModel,
							getSessionAndConn0(sshParamModel));
				}
			} else {
				throw new FileRuntimeException("E90009",
						SourceConstant.OBJECT_MULTI_CREATE_ERROR);
			}
			if (servBasePath == null) {
				servBasePath = servBasePathTmp;
			} else {
				throw new FileRuntimeException("E90009",
						SourceConstant.OBJECT_MULTI_CREATE_ERROR);
			}
		}
	}
	

	/**
	 * 从上下文中获取sshSession实例,如果存在且isConnected则直接返回，
	 * 否则根据请求的连接参数信息重新创建isConnected的服务器session
	 * 
	 * @param paramModel
	 *            文件服务器连接信息实例
	 * @return 返回已连接的文件服务器Session信息
	 */
	public static Session getSshSession(SSHParamModel paramModel) {
		Session currSession = sshSessionPool.get(paramModel);
		synchronized (FileContext.class) {
			if (currSession == null || !currSession.isConnected()) {
				logger.debug("getSshSession"
						+ "----framework:FileServer Session [" + paramModel
						+ "] is disConnected,try to recreate.");
				if (sshSessionPool.containsKey(paramModel)) {
					currSession = getSessionAndConn0(paramModel);
					sshSessionPool.put(paramModel, currSession);
				} else {// 如果传入的参数信息未定义
					throw new FileRuntimeException("E90009",
							FileConstant.UnDefined_FileServer_Info);
				}
			}
		}
		return currSession;
	}

	/**
	 * 根据参数信息创建文件服务器的session连接,设置session不超时0 若连接失败，则抛出连接错误异常信息
	 * 
	 * @param sshParamModel
	 *            服务器连接参数信息
	 * @return 返回连接成功的Session信息
	 */
	public static Session getSessionAndConn0(SSHParamModel sshParamModel) {
		Session tmpSession = SSHClientUtil.getSshSession(sshParamModel);
		try {
			tmpSession.connect(0);
			logger.debug("getSessionAndConn0" + "----framework:FileServer ["
					+ sshParamModel + "] create success.");
		} catch (JSchException e) {
			logger.debug(e.getMessage(), e);
			throw new FileRuntimeException("E90009",
					FileConstant.FILE_SESSION_CREATE_ERROR, e);
		}
		return tmpSession;
	}

	/**
	 * 通过beanId获取服务器根目录配置
	 * 
	 * @param beanId
	 *            服务器连接信息配置的beanId
	 * @return 设定的根目录
	 */
	public static String getServBasePath(String beanId) {
		return servBasePath.get(beanId);
	}
	
	/**
	 * 从上下文中获取本机文件服务器配置的根目录
	 * 
	 * @return 返回根目录字符串
	 */
	public static String getLocalBasePath() {
		return basePath;
	}
	
}
