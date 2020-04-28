package cn.baisee.oa.dao.baisee;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.model.student.BaiseeStudent;
import cn.baisee.oa.model.tuition.TuitionRule;


public interface BaiseeTuitionRuleMapper {
	/**
	 * 查询学费规则
	 * @param tuition
	 * @return
	 */
	List<TuitionRule> getTuitionList(TuitionRule tuition);
	/**
	 * 新增学费规则
	 * @param tuition
	 * @return
	 */
	int addTuition(TuitionRule tuition);
	/**
	 * 根据tid删除学费规则
	 * @param map
	 * @return
	 */
	int delTuition(Map<String, Object> map);
	/**
	 * 根据tId查询要修改的数据
	 * @param map
	 * @return
	 */
	TuitionRule getTuitionById(Map<String, Object> map);
	
	int updateTuition(TuitionRule tuition);
	/**
	 * 查询学生是否使用要删除的学费规则
	 * @param map
	 * @return
	 */
	List<BaiseeStudent> getStudentByTuId(Map<String,Object> map);
}
