package cn.baisee.oa.core.context;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.jcraft.jsch.Session;

import cn.baisee.oa.core.file.SSHParamModel;
import cn.baisee.oa.dao.baisee.BaiseeSystemParamMapper;
import cn.baisee.oa.utils.ApplicationContextUtil;

/**
 * 加载ftp参数
 * @author Administrator
 *
 */
public class FileConnParamLoadHelper  {

	private static final Log logger = LogFactory
			.getLog(FileConnParamLoadHelper.class);

	//默认服务器配置
	private static final String DEFAUL_PARAM_MODEL = "sftpConfig01";
	
	private static SSHParamModel paramModel = null;
	
	private static Map<String,SSHParamModel> paramModels = null;
	
	
	private static String FILE_SERVER_IP=null;

	public FileConnParamLoadHelper() {
		System.out.println("FileConnParamLoadHelper init at " + System.currentTimeMillis());
	}
	public FileConnParamLoadHelper(Map<String,SSHParamModel> map) {
		this.paramModels = map;
	}
//	public FileConnParamLoadHelper(String sshParamBeanId) {
//		loadFTPParam(sshParamBeanId);
//	}

	public void loadFTPParam(String sshParamBeanId) {
		paramModel = (SSHParamModel) ApplicationContextUtil.getBean(sshParamBeanId);
		BaiseeSystemParamMapper mapper = (BaiseeSystemParamMapper) ApplicationContextUtil
				.getBean(BaiseeSystemParamMapper.class);
		 FILE_SERVER_IP=  mapper.findParamByCode("FILE_SERVER_IP").getParamValue();
		// 初始化文件服务器连接信息，如果数据库配置为空则默认获取配置文件中配置信息
		String ftpPost = FILE_SERVER_IP;
		String ftpPort = mapper.findParamByCode("FILE_SERVER_PORT").getParamValue();
		String ftpUsername = mapper.findParamByCode("FILE_SERVER_USER").getParamValue();
		String ftpPassword = mapper.findParamByCode("FILE_SERVER_PWD").getParamValue();
		logger.debug("ftp配置信息：----------->" + ftpPost);
		logger.debug("ftp配置信息：----------->" + ftpPort);
		logger.debug("ftp配置信息：----------->" + ftpUsername);
		logger.debug("ftp配置信息：----------->" + ftpPassword);
		if (StringUtils.isNotEmpty(ftpPost) && StringUtils.isNotEmpty(ftpPort)
				&& StringUtils.isNotEmpty(ftpUsername)
				&& StringUtils.isNotEmpty(ftpPassword)) {
			paramModel.setServerType("sftp");
			paramModel.setHost(ftpPost);
			paramModel.setPort(Integer.valueOf(ftpPort));
			try {
				paramModel.setUserName(ftpUsername);
				paramModel.setPassword(ftpPassword);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
	}

	/**
	 * @Description: 获取连接的ftp的session
	 * @param @return
	 * @return Session
	 * @throws
	 * @author
	 */
	public static Session getSshSession(String paramModelKey) {
		return FileContext.getSshSession(paramModels.get(paramModelKey));
	}
	
	public static Session getSshSession() {
		return FileContext.getSshSession(paramModels.get(DEFAUL_PARAM_MODEL));
	}
	public static String getFILE_SERVER_IP() {
		return paramModels.get(DEFAUL_PARAM_MODEL).getHost();
	}

	public static void setFILE_SERVER_IP(String fILE_SERVER_IP) {
		FILE_SERVER_IP = fILE_SERVER_IP;
	}

}
