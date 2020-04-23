package cn.baisee.oa.dao.pas;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
@Datasource(DatasourceTypes.PAS)
public interface PasHisTaskMapper {

	
	public List<Map<String, String>>  selectHisPageByMap(Map<String, Object> map);
 
	
}
