package cn.baisee.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.baisee.BaiseeGoodsTypeMapper;
import cn.baisee.oa.dao.baisee.BiseeRepertoryMapper;
import cn.baisee.oa.model.goods.BaiseeGoodsType;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeGoodsService;
import cn.baisee.oa.utils.UUIDUtils;
/***
 * 
 * @author zzy
 *
 */
@Service
public class BaiseeGoodsServiceImpl implements BaiseeGoodsService {
	@Autowired
	public  BaiseeGoodsTypeMapper baiseeGoodsTypeMapper;
	@Autowired
	public BiseeRepertoryMapper biseeRepertoryMapper;
	
	/**
	 * 查询所有分类&按照名称模糊查询
	 * @param  typeName 物品分类名称
	 * @return PageInfo<BaiseeGoodsType>
	 * @author zz
	 */
	@Override
	public PageInfo<BaiseeGoodsType> findAllGoodsType(int pageNum, int pageSize,String typeName) {
		//开始分页
		PageHelper.startPage(pageNum, pageSize);
		Map<String, String> map = new HashMap<String, String>();
		map.put("typeName", typeName);
		List<BaiseeGoodsType> list = baiseeGoodsTypeMapper.findAllGoodsTypeBy(map);
		PageInfo<BaiseeGoodsType> page = new PageInfo<BaiseeGoodsType>(list);
		return page;
	}
	
	
	/**
	 * 修改分类名称
	 * @param typeName 分类名称
	 * @author zzy
	 */
	@Override
	public String updateGoodsType(BaiseeGoodsType baiseeGoodsType) {
		String isStart="";
		//查询是否有相同名称
		List<BaiseeGoodsType> list = baiseeGoodsTypeMapper.selectOneGoodsTypeByName(baiseeGoodsType.getTypeName());
		if(list!=null&&list.size()>0){ 
			isStart="不可以重复名称";
		}else {
			baiseeGoodsTypeMapper.updateOneGoodsTypeName(baiseeGoodsType.getTypeName(), baiseeGoodsType.getTypeId());
			isStart= "修改成功";
		}
		return isStart;
	}
	
	

	/**
	 * 新增分类信息
	 * @param typeName 类型名称
	 * @return
	 * @author zzy
	 */
	@Override
	public Integer addGoodsType(String typeName) {
		Integer isStart=null;
		//查询是否有相同名称
		List<BaiseeGoodsType> list = baiseeGoodsTypeMapper.selectOneGoodsTypeByName(typeName);
		//判断集合是否有数据，有数据就是已经有这个名称的分类
		if(list!=null&&list.size()>0){
			isStart=0;
		}else {
			//如果没有则执行添加
			BaiseeGoodsType baiseeGoodsType = new BaiseeGoodsType();
			baiseeGoodsType.setTypeId("GT"+UUIDUtils.getUUID());
			baiseeGoodsType.setTypeName(typeName);
			baiseeGoodsType.setpTypeId("0");
			baiseeGoodsType.setOrderNum((int)(Math.random()*100));
			baiseeGoodsType.setIsMune("0");
			baiseeGoodsTypeMapper.insertOneGoodsType(baiseeGoodsType);
			
		}
		return isStart;
	}

	/**
	 * 查询所有库存分类信息
	 * @return
	 * @author zzy
	 */
	@Override
	public List<BaiseeGoodsType> getAllGoodsTypeList() {
		
		List<BaiseeGoodsType> list = baiseeGoodsTypeMapper.selectAllGoodsTypeList();
		
		return list;
	}


	/**
	 * 查询单条物品类型数据
	 * @param typeId 类型id
	 * @return BaiseeGoodsType
	 */
	@Override
	public BaiseeGoodsType selectOneGoodsTypeById(String typeId) {
		
		BaiseeGoodsType baiseeGoodsType = baiseeGoodsTypeMapper.selectOneGoodsTypeByAll(typeId);
		
		return baiseeGoodsType;
	}
	/**
	   * 批量删除分类
	 * @param typeId 类型id
	 * @return
	 */
	@Override
	public Integer deleteGoodsType(String[] typeId) {

		//删除要删除分类下的物品
		biseeRepertoryMapper.removeGoodsRepertoryByPid(typeId);
		
		//删除分类
		Integer i = baiseeGoodsTypeMapper.delGoodsType(typeId);
		
		return i;
	}
	
	
}
