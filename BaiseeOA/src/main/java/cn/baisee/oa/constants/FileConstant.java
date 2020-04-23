package cn.baisee.oa.constants;

/**
 * 描述:文件资源处理静态类
 */
public class FileConstant {

	public static final String CONST_fileUrl = "fileUrl";
	public static final String CONST_fileContent = "fileContent";
	public static final String CONST_connInfo = "connInfo";
	public static final String CONST_fileServerType = "fileServerType";
	public static final String CONST_exchangeRes = "exchangeRes";
	public static final String CONST_fileExchangeEnumType = "fileExchangeEnumType";

	public static final String PARAM_NULL_ERROR = "文件资源传入参数模型不能为空";
	public static final String PARAM_TYPE_ERROR = "文件资源操作传入参数错误";
	public static final String CONN_TYPE_ERROR = "文件资源操作的连接类型错误";
	public static final String CONN_FileServer_TYPE_UnSupport = "不支持的服务器类型";
	public static final String CONN_UnSupport_Config = "不支持的文件资源连接配置";
	public static final String Exchange_NULL_ERROR = "文件资源操作类型不能为空";
	public static final String Exchange_Unknown_Type = "不支持的文件资源操作类型";
	public static final String Exchange_File_Exist_Error = "要新增的文件已经存在，新增失败。";
	public static final String Exchange_File_NotExist_Error = "要替换的文件不存在，替换失败。";
	public static final String Result_Unknown_Type = "未知的文件操作返回结果类型";
	public static final String Result_UnSupport_Type = "不支持的结果数据转换类型";

	public static final String FILE_SESSION_CREATE_ERROR = "初始化文件服务器连接发生异常";
	public static final String UnDefined_FileServer_Info = "文件服务器连接信息未定义";
	public static final String FILE_SERVER_TYPE_ERROR = "服务器类型错误:";
	public static final String CURR_SUPPORTED_TYPE = "当前支持的服务器类型:";

}
