package cn.baisee.oa.service;

import java.util.List;

import cn.baisee.oa.model.goods.BaiseeGoodsType;
import cn.baisee.oa.page.pagehelper.PageInfo;
/***
 * 
 * @author zzy
 *
 */
public interface BaiseeGoodsService {
	/**
	 * 查询所有分类&按照名称模糊查询
	 * @param  typeName 物品分类名称
	 * @return PageInfo<BaiseeGoodsType>
	 * @author zz
	 */
	public PageInfo<BaiseeGoodsType> findAllGoodsType(int pageNum, int pageSize,String typeName);
	
	/**
	 * 查询单条物品类型数据
	 * @param typeId 物品分类id
	 * @return BaiseeGoodsType
	 */
	public BaiseeGoodsType selectOneGoodsTypeById(String typeId);
	
	/**
	 * 修改分类名称
	 * @param typeName 分类名称
	 * @author zzy
	 */
	public String updateGoodsType(BaiseeGoodsType baiseeGoodsType);
	
	
	/**
	 * 查询所有库存分类信息
	 * @return 
	 * @author zzy
	 */
	public List<BaiseeGoodsType> getAllGoodsTypeList();
	/**
	 * 批量删除
	 * @param typeId 分类id
	 * @return
	 */
	public Integer deleteGoodsType(String [] typeId) ;
	
	/**
	 * 新增分类
	 * @param typeName 要添加的分类名称
	 * @return
	 * @author zzy
	 */
	public Integer addGoodsType(String typeName);
}
