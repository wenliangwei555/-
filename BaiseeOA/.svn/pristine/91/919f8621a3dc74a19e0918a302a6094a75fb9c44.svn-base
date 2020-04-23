package cn.baisee.oa.dao.baisee;

import java.util.List;

import java.util.Map;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.goods.BaiseeGoodsRepertory;
/***
 * 
 * @author zzy
 *
 */
@Datasource(DatasourceTypes.OA)
public interface BiseeRepertoryMapper {
	/**
	 * 查询所有物品库存信息
	 * @return List<BaiseeGoodsRepertory>
	 * @author zzy
	 */
	public List<BaiseeGoodsRepertory> selectGoodsRepertoryList(Map<String, String> map);
	/**
	 *根据物品id查询单条数据并返回
	 * @param id
	 * @return BaiseeGoodsRepertory
	 * @author zzy
	 */
	public BaiseeGoodsRepertory selectOneGodsRepertory(String id);
	
	/**
	 * 根据物品goodsTypeId，查询该物品的库存信息
	 * @param id
	 * @return BaiseeGoodsRepertory
	 * @author liangfeng
	 */
	public List<BaiseeGoodsRepertory> findGoodsRepertoryByGoodsTypeId(String goodsTypeId);
	/**
	 * 查询所有库存
	 * @return BaiseeGoodsRepertory
	 * @author liangfeng
	 */
	public List<BaiseeGoodsRepertory> findALLRpertory();
	
	/**
	 * 根据物品的goodsId修改库存物品的数量
	 * @param baiseeGoodsRepertory
	 * @author zzy
	 */
	public Integer updateOneGodsRepertory(BaiseeGoodsRepertory baiseeGoodsRepertory);
	
	/**
	 * 根据物品的goodsTypeId修改（数量）
	 * @param baiseeGoodsRepertory
	 * @author liangfeng
	 */
	public Integer updateGodsRepertoryGoodsNum(BaiseeGoodsRepertory goodsRepertory);
	
	/**
	    * 新增库存物品
	 * @param baiseeGoodsRepertory
	 * @author zzy
	 */
	public Integer addOneGodsRepertory(BaiseeGoodsRepertory baiseeGoodsRepertory);
	
	/**
	 * 根据物品id批量删除库存物品
	 * @param goodsId
	 * @author zzy
	 */
	public Integer removeGodsRepertory(String [] goodsId);
	/**
	 * 根据所属分类Id批量删除库存物品
	 * @param pTypeId  所属分类Id
	 * @return
	 */
	public Integer removeGoodsRepertoryByPid(String [] goodsTypeId);
	
}
