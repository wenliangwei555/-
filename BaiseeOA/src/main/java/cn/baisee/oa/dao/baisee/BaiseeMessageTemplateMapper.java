package cn.baisee.oa.dao.baisee;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.message.BaiseeMessageTemplate;


@Datasource(DatasourceTypes.OA)
public interface BaiseeMessageTemplateMapper {

	public BaiseeMessageTemplate selectById(Long id);
	
	public void deleteById(Long id);
	
	public List<BaiseeMessageTemplate> selectlist(@Param("baiseeMessageTemplate") BaiseeMessageTemplate baiseeMessageTemplate);
	
	public int insert(@Param("baiseeMessageTemplate") BaiseeMessageTemplate baiseeMessageTemplate);
	
	public int updateById(@Param("baiseeMessageTemplate") BaiseeMessageTemplate baiseeMessageTemplate);
	
}