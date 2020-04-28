package cn.baisee.oa.dao.baisee;

import org.springframework.stereotype.Repository;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;

@Repository("commMapper")
public interface CommMapper {
	/**
	 * 主键生成
	 * 
	 * @param keyName
	 * @return
	 */
	@Datasource(DatasourceTypes.OA)
	String getSeq(String keyName);
}
