package cn.baisee.oa.dao.baisee;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.BaiseeAToken;

@Datasource(DatasourceTypes.OA)
public interface BaiseeATokenMapper {

	public BaiseeAToken getToken();
	
}