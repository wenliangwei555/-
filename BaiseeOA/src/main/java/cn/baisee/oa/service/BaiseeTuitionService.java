package cn.baisee.oa.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.baisee.oa.model.tuition.TuitionDiscount;
import cn.baisee.oa.model.tuition.TuitionRule;
import cn.baisee.oa.model.tuition.TuitionStages;
import cn.baisee.oa.model.tuition.TuitionStagesRule;

/**
 * 关于学费方面的service
 * @author LiFQ
 *
 */
public interface BaiseeTuitionService {
	/**
	 * 查询所有的学费规则
	 * @return
	 */
	public List<TuitionRule> selectTuitionRuleList();
	
	/**
	 * 根据学费主键id 获取某条学费信息
	 * @param map
	 * @return
	 */
	public TuitionRule selectTuitionRuleById(Map<String, String> map);
	/**
	 * 根据学费主键id 获取某条学费优惠信息
	 * @param map
	 * @return
	 */
	public List<TuitionDiscount> selectTuitionDiscountById(Map<String, String> map) ;
	/**
	 * 根据学费id，获取学费的周期的所有的信息，没有明细
	 * @param map
	 * @return
	 */
	public List<TuitionStages> selectTuitionStagesById(Map<String, String> map);
	/**
	 * 根据学费周期id，获取学费的详细分期信息
	 * @param map
	 * @return
	 */
	public List<TuitionStagesRule> selectTuitionStagesDetailedById(Map<String, String> map);
	/**
	 *	 根据优惠主键获取这条优惠的信息   详细信息
	 * @param map
	 * @return
	 */
	 TuitionDiscount selectTuitionDiscountDetailedById(Map<String, String> map);
	/**
	 * 根据班级的所有学籍获取这个对应的学费
	 * @param claId
	 * @return
	 */
	 List<TuitionRule> selectTuitionByClaStatus(String claStatus);
	/**
	 * 查询分期中都有哪些期数（去掉重复得查询）
	 * @return
	 */
	 List<TuitionStages> selectTuitionStagesNumber();
	/**
	 *  根据分期id，期数，查询期数之前的总学费
	 * @param tuStId 分期id
	 * @param stagesNumber 查询的期数
	 * @return
	 */
	 String selectTotalTutitonLessThanPeriodByTuStId(String tuStId,String stagesNumber);
	/**
	 * 根据分期id，期数，查询本期缴费金额
	 * @param tuStId 分期id
	 * @param stagesNumber 查询的期数
	 * @return
	 */
	 String selectTotalTutitonCurrentPeriodByTuStId(String tuStId,String stagesNumber);
	/**
	 * 删除学费对应的优惠
	 * @param tuId
	 * @param tuDiId
	 * @return
	 */
	int removeDiscounts(String tuId,String tuDiId );
}
