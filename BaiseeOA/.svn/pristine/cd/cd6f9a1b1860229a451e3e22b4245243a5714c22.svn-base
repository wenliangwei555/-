package cn.baisee.oa.dao.baisee;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.student.BaiseeStudentReturn;
import cn.baisee.oa.model.student.BaiseeStudentReturnRule;

@Datasource(DatasourceTypes.OA)
public interface BaiseeReturnMapper {
	
	/**
	 * 查询所有的返费规则
	 * @return
	 */
	List<BaiseeStudentReturn> getReturnList();
	/**
	 * 根据学费ID查询返费ID
	 * @param map
	 * @return
	 */
	String getRid(Map<String, Object> map);
	/**
	 * 删除返费规则
	 * @param rids
	 * @return
	 */
	int delReturn(Map<String, Object> map);
	/**
	 * 根据RT_ID删除子表规则
	 * @param map
	 * @return
	 */
	int delRetuRule(Map<String, Object> map);
	/**
	 * 根据返费ID删除学费返费关联
	 * @param rids
	 * @return
	 */
	int delTuitionReturn(String[] rids);
	/**
	 * 根据学费ID删除学费返费关联
	 * @param rids
	 * @return
	 */
	int delTuitionReturnByTuId(Map<String, Object> map);
	/**
	 * 添加返费主表
	 * @param retu
	 * @return
	 */
	int saveReturn(BaiseeStudentReturn retu);
	/**
	 * 添加返费规则
	 * @param retu
	 * @return
	 */
	int saveReturnRule(BaiseeStudentReturnRule retuRule);
	/**
	 * 根据返费ID查询要修改的返费子表
	 * @param map
	 * @return
	 */
	BaiseeStudentReturn getReturnById(Map<String, Object> map);
	
	/**
	 * 根据返费ID查询要修改的返费子表
	 * @param map
	 * @return
	 */
	List<BaiseeStudentReturnRule> getReturnRuleById(Map<String, Object> map);
	/**
	 * 修改返费主表
	 * @return
	 */
	int updateReturn(BaiseeStudentReturn retu);
	/**
	 * 修改返费规则
	 * @param retu
	 * @return
	 */
	int updateReturnRule(BaiseeStudentReturnRule retuRule);
	/**
	 * 查询所有缴费金额条件
	 * @return
	 */
	List<BaiseeStudentReturnRule> getReturnByTypePay();
	/**
	 * 查询所有时间条件
	 * @return
	 */
	List<BaiseeStudentReturnRule> getReturnByTypeTime();
	/**
	 * 查询所有时间条件
	 * @return
	 */
	List<BaiseeStudentReturnRule> getReturnByTypeReturn();
	/**
	 * 新增学费返费关联
	 * @param map
	 * @return
	 */
	int insertTuitionReturn(Map<String, Object> map);
	/**
	 * 根据学费查询要修改的类型为缴费金额的列 
	 * @param map
	 * @return
	 */
	String getRetuByTuId(Map<String, Object> map);
	
	/**
	 * 根据返费rtid和规则类型查询是否已存在
	 * @param map
	 * @return
	 */
	BaiseeStudentReturnRule getRetuRuleByRtId(Map<String, Object> map);
	/**
	 * 修改学费返费关联表
	 * @param map
	 * @return
	 */
	int updateTuitionReturn(Map<String, Object> map);
	
	/**
	 * 根据返费ID查询详细规则
	 * @param map
	 * @return
	 */
	BaiseeStudentReturnRule getRetuRule(Map<String, Object> map);
	
	List<BaiseeStudentReturn> getAllReturn();
}