package cn.baisee.oa.dao.baisee;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.student.BaiseeStudentRefund;
/**
 * 退费规则管理的mapper接口
 * @author WANGBAOXIANG
 *
 */
@Datasource(DatasourceTypes.OA)
public interface BaiseeRefundMapper {
	/**
	 * 分页查询所有的退费规则
	 * @return
	 */
	List<BaiseeStudentRefund> getRefundList();

	/**
	 * 添加退费规则
	 * @param refund
	 * @return
	 */
	int insertRefund(BaiseeStudentRefund refund);
	/**
	 * 添加学费-退费关联表
	 * @param map
	 * @return
	 */
	int insertTuitionRefund(Map<String, Object> map);
	/**
	 * 修改学费退费关联
	 * @param map
	 * @return
	 */
	int updateTuitionRefund(Map<String, Object> map);
	/**
	 * 通过ID查询要修改的退费规则
	 * @param map
	 * @return
	 */
	List<BaiseeStudentRefund> getRefundById(Map<String, Object> map);
	/**
	 * 修改退费规则
	 * @param refund
	 * @return
	 */
	int updateRefund(BaiseeStudentRefund refund);
	/**
	 * 根据学费ID查询退费ID
	 * @return
	 */
	String getRidByTuid(Map<String, Object> map);
	/**
	 * 通过ID删除退费规则
	 * @param map
	 * @return
	 */
	int delRefund(Map<String, Object> map);
	/**
	 * 根据学费ID删除学费 退费关联表
	 * @param map
	 * @return
	 */
	int delTiitionRefund(Map<String, Object> map);
	/**
	 * 根据退费ID删除学费 退费关联表
	 * @param map
	 * @return
	 */
	int delTiitionRefundByRid(Map<String, Object> map);
	
	
	BaiseeStudentRefund getRefundByRid(Map<String, Object> map);
}
