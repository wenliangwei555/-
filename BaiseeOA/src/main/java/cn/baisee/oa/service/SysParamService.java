package cn.baisee.oa.service;

import java.util.List;

import cn.baisee.oa.model.BaiseeSysParam;

public interface SysParamService {

	List<BaiseeSysParam> queryAll();
	/**
	 * 读取初始化文件服务器连接信息参数
	 * @return
	 */
	BaiseeSysParam getInitParameter(Integer id);

	
	
	
}
