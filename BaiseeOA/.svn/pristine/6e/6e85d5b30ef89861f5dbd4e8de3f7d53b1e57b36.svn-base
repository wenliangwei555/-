package cn.baisee.oa.dao.baisee;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.tuition.TuitionStages;
import cn.baisee.oa.model.tuition.TuitionStagesRule;

@Datasource(DatasourceTypes.OA)
public interface BaiseeStagesMapper {
	/**
	 * 查询所有分期
	 * @return
	 */
	List<TuitionStages> getStagesList(Map<String, Object> map);
	/**
	 * 新增分期
	 * @param stages
	 * @return
	 */
	int insertStages(TuitionStages stages);
	/**
	 * 新增学费分期关联
	 * @param map
	 * @return
	 */
	int insertTuitionStages(Map<String, Object> map);
	/**
	 * 修改学费分期关联
	 * @param map
	 * @return
	 */
	int updateTuitionStages(Map<String, Object> map);
	/**
	 * 添加学费分期规则表
	 * @param map
	 * @return
	 */
    int insertStagesRule(Map<String, Object> map);
    /**
    *  据根据学费规则id获取分期ID
     * @param map
     * @return
     */
    String[] getTuStIdByTuId(Map<String, Object> map);
    /**
    * 根据ID删除分期     
     * @param map
     * @return
     */
    int delStages(Map<String, Object> map);
    /**
     * 根据分期ID删除学费规则表 
     * @param map
     * @return
     */
    int delStagesRule(Map<String, Object> map);
    /**
     * 删除学费 分期关联
     * @param map
     * @return
     */
    int delTuitionStages(Map<String, Object> map);
    /**
    * 根据分期ID查询
     * @param map
     * @return
     */
    TuitionStages getStagesById(Map<String, Object> map);
    /**
     * 根据分期ID查询分期详情
     * @param map
     * @return
     */
    List<TuitionStagesRule> getStagesRuleById(Map<String, Object> map);
    /**
     * 修改分期
     * @param stages
     * @return
     */
    int updateStages(TuitionStages stages);
    /**
     * 修改分期规则
     * @param map
     * @return
     */
    int updateStagesRule(Map<String, Object> map);
    /**
             * 分组查询分几期
     * @return
     */
    List<TuitionStages> getTuStCycleList();
    /**
           * 根据分期ID删除学费分期
     * @param map
     * @return
     */
    int delStagesByStId(Map<String, Object> map);
}
