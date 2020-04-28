package cn.baisee.oa.dao.baiseew;
import java.util.List;
import java.util.Map;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.baiseeTerm;
/**
 * 
 * @author jxx
 *
 */
@Datasource(DatasourceTypes.BAISEEW)
public interface BaiseewTermMapper {
	
	/**
	 *   根据班级Id查询详细信息
	 * @param classId
	 * @return
	 */
	
	List<baiseeTerm> queryDetails(String classId);

	/**
	 *  查询班级的考试期数
	 * @param classId
	 * @return
	 */
	String getTermByCid(String classId);
	/**
	 *  新增 成绩总表数据
	 * @param termTow
	 */
	void addTerm(baiseeTerm termTow);
	/**
	 *  根据iD删除信息
	 * @param map
	 * @return
	 */
	int deleteTermById(Map<String, Object> map);

	/**
	 *  查询所有已经记录过成绩的班级
	 * @return
	 */
	List<baiseeTerm> selectTermAll();
	/**
	 *  根据ClassId跟期数删除
	 * @param cid
	 * @param term
	 */
	void deleteTermByIdorClaId(Map<String, Object> map);
	

}
