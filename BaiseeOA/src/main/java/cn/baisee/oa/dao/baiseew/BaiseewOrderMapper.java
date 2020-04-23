package cn.baisee.oa.dao.baiseew;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.order.BaiseewOrder;

/**
 * 微信
 * @author 连银虎1
 *
 */
@Datasource(DatasourceTypes.BAISEEW)
public interface BaiseewOrderMapper {
	
	/**
	 * 查询order列表
	 */
	public List<BaiseewOrder> getOrder(Map<String, Object> map);
	
	/**
	 * 更新联系状态为已通知
	 */
	public int orderOldInformed(Map<String, Object> map);
	

}
