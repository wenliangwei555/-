package cn.baisee.oa.service;

import java.util.List;

import cn.baisee.oa.model.goods.BaiseeGoodsRepertory;
import cn.baisee.oa.page.pagehelper.PageInfo;
/**
 * 
 * @author zzy
 *
 */
public interface BiseeRepertoryService {
 /**
    * 查询所有物品库存信息
    *  @param goodsName 物品名称
    *   @param goodsTypeId  物品类型ID
 * @author zzy
 *  
 */
	public PageInfo<BaiseeGoodsRepertory> getGoodsRepertoryList(int pageNum, int pageSize,String goodsName,String goodsTypeId);
	/**
	 * 根据物品id查询单条数据并返回
	 * @param id 物品ID
	 * @return
	 * @author zzy
	 */
	public BaiseeGoodsRepertory getOneGodsRepertory(String id);
	
	/**
	 * 根据物品goodsTypeId，查询该物品的库存信息
	 * @param goodsTypeId  物品类型ID
	 * @return
	 * @author liangfeng
	 */
	public List<BaiseeGoodsRepertory> findGoodsRepertoryByGoodsTypeId(String goodsTypeId);
	
	/**
	 * 查询所有库存物品
	 * @return
	 * @author liangfeng 
	 */
	public List<BaiseeGoodsRepertory> findALLRpertory();
	
	/**
	 * 修改库存物品的详细信息（数量）
	 * @param baiseeGoodsRepertory  要修改的数据  用对象封装
	 * @author zzy
	 */
	public Integer updateOneGodsRepertory(BaiseeGoodsRepertory baiseeGoodsRepertory);
	
	/**
	 * 根据物品的goodsTypeId修改
	 * @param baiseeGoodsRepertory
	 * @author liangfeng
	 */
	public Integer updateGodsRepertoryGoodsNum(BaiseeGoodsRepertory goodsRepertory);
	
	/**
	    * 新增库存物品
	 * @param baiseeGoodsRepertory  新增的物品信息 用对象封装
	 * @author zzy
	 */
	public Integer addOneGodsRepertory(BaiseeGoodsRepertory baiseeGoodsRepertory);
	
	/**
	 * 批量删除库存物品
	 * @param goodsId  要删除的物品id（一个或多个）
	 * @author zzy
	 */
	public Integer delGodsRepertory(String [] goodsId);
	
}
