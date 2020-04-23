package cn.baisee.oa.dao.baisee;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.message.BaiseeMessage;


/**
 * 消息
 * @author apple
 *
 */
@Datasource(DatasourceTypes.OA)
public interface BaiseeMessageMapper {

	public List<BaiseeMessage> selectlist(@Param("baiseeMessage") BaiseeMessage baiseeMessage);
	
	public Long insert(@Param("baiseeMessage") BaiseeMessage baiseeMessage);
	
	public int updateById(@Param("baiseeMessage") BaiseeMessage baiseeMessage);

	public int deleteById(@Param("baiseeMessage") BaiseeMessage baiseeMessage);
	
	public BaiseeMessage selectById(Long id);

	
	
}