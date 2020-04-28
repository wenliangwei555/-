package cn.baisee.oa.dao.baisee;

import java.util.List;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.BaiseeSystemParam;

@Datasource(DatasourceTypes.OA)
public interface BaiseeSystemParamMapper {

	 public List<BaiseeSystemParam> listAll();
	 
	 public BaiseeSystemParam findParamByCode(String paramCode);
}
