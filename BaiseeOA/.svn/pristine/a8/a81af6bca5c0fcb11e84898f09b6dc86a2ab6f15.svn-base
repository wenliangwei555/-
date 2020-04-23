package cn.baisee.oa.core.file;

/**
 * 描述:Sftp服务器操作的入参模型
 */
public class SSHParamModel extends FileXmlConfigBase {

	/** 服务器类型 */
	private String serverType;
	/** 主机 */
	private String host;
	/** 端口号 */
	private int port;
	/** 用户名 */
	private String userName;
	/** 密码 */
	private String password;
	/** 如果密码加密时，密码信息的解密密码 */
	private String passphrase;
	/** 本地密钥文件路径 */
	private String keyFile;

	/**
	 * 无参构造
	 */
	public SSHParamModel() {
	}

	/**
	 * 构造方法
	 * 
	 * @param host
	 *            主机
	 * @param port
	 *            端口号*
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @param fileServerType
	 *            文件服务器类型
	 */
	public SSHParamModel(String serverType, String host, int port,
			String userName, String password) {
		this.serverType = serverType;
		this.host = host;
		this.port = port;
		this.userName = userName;
		this.password = password;
	}

	/**
	 * 构造方法
	 * 
	 * @param host
	 *            主机
	 * @param port
	 *            端口号*
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @param fileServerType
	 *            文件服务器类型
	 * @param passphrase
	 *            如果密码加密时，密码信息的解密密码
	 * @param keyFile
	 *            本地密钥文件路径
	 */
	public SSHParamModel(String serverType, String host, int port,
			String userName, String password, String passphrase, String keyFile) {
		this.serverType = serverType;
		this.host = host;
		this.port = port;
		this.userName = userName;
		this.password = password;
		this.passphrase = passphrase;
		this.keyFile = keyFile;
	}

	/**
	 * @return the serverType
	 */
	public String getServerType() {
		return serverType;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the passphrase
	 */
	public String getPassphrase() {
		return passphrase;
	}

	/**
	 * @return the keyFile
	 */
	public String getKeyFile() {
		return keyFile;
	}

	/**
	 * @param host
	 *            the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param passphrase
	 *            the passphrase to set
	 */
	public void setPassphrase(String passphrase) {
		this.passphrase = passphrase;
	}

	/**
	 * @param keyFile
	 *            the keyFile to set
	 */
	public void setKeyFile(String keyFile) {
		this.keyFile = keyFile;
	}

	/**
	 * @param serverType
	 *            the serverType to set
	 */
	public void setServerType(String serverType) {
		this.serverType = serverType;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("serverType=").append(serverType).append(";");
		sbf.append("host=").append(host).append(";");
		sbf.append("port=").append(port).append(";");
		// sbf.append("userName=").append(userName).append(";");
		// sbf.append("password=").append(password).append(";");
		// sbf.append("passphrase=").append(passphrase).append(";");
		sbf.append("keyFile=").append(keyFile).append(";");
		return sbf.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((host == null) ? 0 : host.hashCode());
		result = prime * result + ((keyFile == null) ? 0 : keyFile.hashCode());
		result = prime * result
				+ ((passphrase == null) ? 0 : passphrase.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + port;
		result = prime * result
				+ ((serverType == null) ? 0 : serverType.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SSHParamModel other = (SSHParamModel) obj;
		if (host == null) {
			if (other.host != null)
				return false;
		} else if (!host.equals(other.host))
			return false;
		if (keyFile == null) {
			if (other.keyFile != null)
				return false;
		} else if (!keyFile.equals(other.keyFile))
			return false;
		if (passphrase == null) {
			if (other.passphrase != null)
				return false;
		} else if (!passphrase.equals(other.passphrase))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (port != other.port)
			return false;
		if (serverType == null) {
			if (other.serverType != null)
				return false;
		} else if (!serverType.equals(other.serverType))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	
		public static void main(String [] args) {
			
		}
}
