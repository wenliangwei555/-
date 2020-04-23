package cn.baisee.oa.dao.baiseew;
import java.util.List;
import java.util.Map;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.StudentScore;

/**
 * 获取学生成绩单列表数据访问层
 * @author Wang Chunhuan
 *
 */
@Datasource(DatasourceTypes.BAISEEW)
public interface StudentScoreListMapper {
	
	/**
	 * 查询学生成绩
	 * @param maps
	 * @return
	 */
	public StudentScore selectScoer(Map<String, Object> maps);
	/***
	 * 查询某个班 某厂考试的 某个学生的suo'y成绩   用户推送消息
	 * @param maps
	 * @return
	 */
	public List<StudentScore> getScoerBysid(Map<String, Object> maps);
	/***
	 * 查询某个班 某厂考试的 参加的学生Sid
	 * @param maps
	 * @return
	 */
	public List<String> getSidByClass(Map<String, Object> maps);
	/**
	 * 查询
	 * @param sid
	 * @return
	 */
	public List<String> getClassIdBySid(String sid);
	
	public List<String> getTermBySid(String sid);
	
	public List<String> getSubjectBySid(String sid);
	/**
	  * 根据学生ID查询学生成绩
	 * @param sid
	 * @return
	 */
	public List<StudentScore> selectBySid(Map<String, Object> maps);
	
	/**
	  * 根据学生ID查询学生各科排名
	 * @param sid
	 * @return
	 */
	
	public List<StudentScore> selectDescBySid(Map<String, Object> maps);
	
   /**
      *  根据学生分数ID查询科目和成绩
      * @param id
      * @return
      */
     public StudentScore querySubjectAndScore(String sid);
}
