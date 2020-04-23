package cn.baisee.oa.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.baisee.BiseeRepertoryMapper;
import cn.baisee.oa.model.goods.BaiseeGoodsRepertory;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BiseeRepertoryService;
import cn.baisee.oa.utils.UUIDUtils;
/***
 * 
 * @author zzy
 *
 */
@Service
public class BiseeRepertoryServiceImpl implements BiseeRepertoryService {
	@Autowired
	private BiseeRepertoryMapper biseeRepertoryMapper;
	
	 /**
	 * 查询所有物品库存信息
	 * @author zzy
	 *  
	 */
	@Override
	public PageInfo<BaiseeGoodsRepertory> getGoodsRepertoryList(int pageNum, int pageSize,String goodsName,String goodsTypeId) {
		//开始分页
		PageHelper.startPage(pageNum, pageSize);
		Map<String, String> map = new HashMap<String, String>();
		map.put("goodsName", goodsName);
		map.put("goodsTypeId", goodsTypeId);
		List<BaiseeGoodsRepertory> list = biseeRepertoryMapper.selectGoodsRepertoryList(map);
		PageInfo<BaiseeGoodsRepertory> page = new PageInfo<BaiseeGoodsRepertory>(list);
		return page;
	}
	
	/**
	 * 根据物品id查询单条数据并返回
	 * @param id 物品id
	 * @return
	 * @author zzy
	 */
	@Override
	public BaiseeGoodsRepertory getOneGodsRepertory(String id) {
		
		BaiseeGoodsRepertory selectOneGodsRepertory = biseeRepertoryMapper.selectOneGodsRepertory(id);
		return selectOneGodsRepertory;
	}
	
	/**
	 * 根据物品goodsTypeId，查询该物品的库存信息
	 * @param goodsTypeId  
	 * @return
	 * @author liangfeng
	 */
	@Override
	public List<BaiseeGoodsRepertory> findGoodsRepertoryByGoodsTypeId(String goodsTypeId) {
		
		List<BaiseeGoodsRepertory> selectOneGodsRepertory = biseeRepertoryMapper.findGoodsRepertoryByGoodsTypeId(goodsTypeId);
		return selectOneGodsRepertory;
	}
	/**
	 * 查询所有库存物品信息
	 * @return
	 * @author liangfeng
	 */
	@Override
	public List<BaiseeGoodsRepertory> findALLRpertory() {
		
		List<BaiseeGoodsRepertory> selectOneGodsRepertory = biseeRepertoryMapper.findALLRpertory();
		return selectOneGodsRepertory;
	}
	/**
	 * 根据物品的goodsId修改物品数量
	 * @param baiseeGoodsRepertory 
	 * @author zzy
	 */
	@Override
	public Integer updateOneGodsRepertory(BaiseeGoodsRepertory baiseeGoodsRepertory) {
		Integer integer = biseeRepertoryMapper.updateOneGodsRepertory(baiseeGoodsRepertory);
		return integer;
	}
	/**
	 * 根据物品的goodsTypeId修改
	 * @param baiseeGoodsRepertory
	 * @author liangfeng
	 */
	@Override
	public Integer updateGodsRepertoryGoodsNum(BaiseeGoodsRepertory goodsRepertory) {
		Integer integer = biseeRepertoryMapper.updateGodsRepertoryGoodsNum(goodsRepertory);
		return integer;
	}
	/**
	    * 新增库存物品
	 * @param baiseeGoodsRepertory
	 * @author zzy
	 */
	@Override
	public Integer addOneGodsRepertory(BaiseeGoodsRepertory baiseeGoodsRepertory) {
		
		//利用 UUID给物品ID赋值
		baiseeGoodsRepertory.setGoodsId("G-"+UUIDUtils.getUUID());
		//物品添加当前时间赋值
		baiseeGoodsRepertory.setUpdateTime(new Date());
		return biseeRepertoryMapper.addOneGodsRepertory(baiseeGoodsRepertory);
	}

	/**
	 * 批量删除库存物品
	 * @param goodsId
	 * @author zzy
	 */
	@Override
	public Integer delGodsRepertory(String[] goodsId) {
		return biseeRepertoryMapper.removeGodsRepertory(goodsId);
	}

}
