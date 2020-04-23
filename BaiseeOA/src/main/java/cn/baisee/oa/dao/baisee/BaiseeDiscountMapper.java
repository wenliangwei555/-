package cn.baisee.oa.dao.baisee;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.tuition.TuitionDiscount;

/**
 * 班级优惠Mapper接口
 * @author WANGBAOXING
 *
 */
@Datasource(DatasourceTypes.OA)
public interface BaiseeDiscountMapper {
	/**
	 * 分页查询优惠信息
	 * @return
	 */
	List<TuitionDiscount> getDiscInfo(TuitionDiscount discount);
	/**
	 * 新增优惠信息
	 * @param discounts
	 * @return
	 */
	int addDiscount(TuitionDiscount discount);
	/**
	 * 新增学费优惠关联 
	 * @param map
	 * @return
	 */
	int insertDiscountTyition(Map<String, Object> map);
	/**
	 * 修改学费优惠关联
	 * @param map
	 * @return
	 */
	int updateTuitionDiscount(Map<String,Object> map);
	/**
	 * 根据ID查询要修改的信息
	 * @param map
	 * @return
	 */
	TuitionDiscount getDiscountsById(Map<String, Object> map);
	
	/**
	 * 修改优惠信息
	 * @param discounts
	 * @return
	 */
	int updateDiscount(TuitionDiscount discount);
	/**
	 * 根据根据学费规则id获取优惠规则ID
	 * @param map
	 * @return
	 */
	String[] getTuDiIdByTuId(Map<String, Object> map);
	/**
	 * 通过ID删除优惠信息
	 * @param map
	 * @return
	 */
	int delDisc(Map<String, Object> map);
	/**
	 * 根据优惠ID删除关联
	 * @param map
	 * @return
	 */
	int delTuitionDiscByTuDiId(Map<String, Object> map);
	/**
	 * 根据学费ID删除学费 优惠关联表
	 * @param map
	 * @return
	 */
	int delTuitionDisc(Map<String, Object> map);
	
	List<TuitionDiscount> getDiscount();
}
