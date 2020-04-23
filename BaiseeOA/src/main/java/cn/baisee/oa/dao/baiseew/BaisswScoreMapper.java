package cn.baisee.oa.dao.baiseew;
import java.util.List;
import java.util.Map;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.BaisswScore;
/**
 * 
 * @author jxx
 *
 */
@Datasource(DatasourceTypes.BAISEEW)
public interface BaisswScoreMapper {
	
	/**
	 * 	根据班级成绩期数查询详细信息
	 * @param term
	 * @param cid
	 * @return
	 */
	List<BaisswScore> getTermByCId(Map<String, Object> map);
	/**
	 *  新增学生成绩数据
	 * @param baisswScore
	 */
	void addScore(BaisswScore baisswScore);
	/**
	 *  根据Id删除成绩表数据
	 * @param parameterValues
	 * @return
	 */
	int deleteScoreById( Map<String, Object> map );
	
	
	List<Map<String,Object>> getTermByCIDandTerm( Map<String, Object> map );
	
}
