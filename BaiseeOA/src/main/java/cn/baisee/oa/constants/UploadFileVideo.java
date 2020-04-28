package cn.baisee.oa.constants;

public class UploadFileVideo {
	
	public static final String CHANNELTYPE = "sftp";// 类型

	public static final String CHANNELTYPEEXEC = "exec";// 类型
	
	public static final long upload_maxsize = 5000 * 1024 * 1024;// 最大值为500MB
	
	public static final String[] allowFiles = {".mp4"};// 文件允许格式
	
	public static final String UPLOADADDRESS = "/data/sftp/mysftp/upload/";//路径
	
	public static final String HTTP = "http://";
	
}
