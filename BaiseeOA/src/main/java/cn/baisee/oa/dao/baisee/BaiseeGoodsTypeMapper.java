package cn.baisee.oa.dao.baisee;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.model.goods.BaiseeGoodsType;

public interface BaiseeGoodsTypeMapper {
	/**
	 * 查询所有分类&按照名称模糊查询
	 * @param map
	 * @return
	 */
	public List<BaiseeGoodsType> findAllGoodsTypeBy(Map<String, String> map);
	
	/**
	 * 修改分类名称
	 * @param typeName 分类名称
	 * @author zzy
	 */
	public void updateOneGoodsTypeName(String typeName,String id);
	
	/**
	 * 根据id查询
	 * @param id 分类id
	 * @return
	 */
	public BaiseeGoodsType selectOneGoodsTypeByAll(String id);
	
	
	/**
	 * 批量删除
	 * @param typeId 分类id
	 * @return
	 */
	 public Integer delGoodsType(String [] typeId);
	
	
	
	/**
	 * 新增分类信息
	 * @param typeName 名称
	 * @return
	 * @author zzy
	 */
	public Integer insertOneGoodsType(BaiseeGoodsType baiseeGoodsType);
	
	
	/**
	 * 查询是否有相同名称
	 * @param id
	 * @param typeName
	 * @return
	 * @author zzy
	 */ 
	public List<BaiseeGoodsType> selectOneGoodsTypeByName(String typeName);
	
	
	
	
	/**
	 * 查询所有库存分类信息
	 * @return
	 * @author zzy
	 */
	public List<BaiseeGoodsType> selectAllGoodsTypeList();
	
}
