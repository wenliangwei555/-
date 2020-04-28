package cn.baisee.oa.dao.baisee;

import java.util.List;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.BaiseeSysParam;

@Datasource(DatasourceTypes.OA)
public interface BaiseeSysParamMapper {

	/**
	 * 读取Redis连接信息
	 * @return
	 */
	List<BaiseeSysParam> queryAll();
	/**
	 * 读取初始化文件服务器连接信息参数
	 * @return
	 */
	BaiseeSysParam readTheParameter(Integer id);
}
