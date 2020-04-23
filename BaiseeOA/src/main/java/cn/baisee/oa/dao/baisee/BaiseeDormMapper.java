package cn.baisee.oa.dao.baisee;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.dorm.BaiseeDorm;
import cn.baisee.oa.model.dorm.BaiseeStudorm;
import cn.baisee.oa.model.student.BaiseeStudent;
/**
 * 班级的mapper接口
 * @author wangbaoxiang
 *
 */
@Datasource(DatasourceTypes.OA)
public interface BaiseeDormMapper {	
	List<BaiseeDorm> selectDorm(Map<String, Object> map);
	List<BaiseeStudorm> selectDormStu(Map<String, Object> map);
	List<BaiseeStudent> selectCheckStu(Map<String, Object> map);
	Integer insertDormStu(@Param("stuId")String stuId,@Param("dormid")String dormid);
	Integer delDormStu(Map<String, Object> map);
	Integer insertDorm(BaiseeDorm baiseeDorm);
	Integer updateDorm(BaiseeDorm baiseeDorm);
	BaiseeDorm queryDorm(@Param("dormid")String dormid);
	Integer delDorm(Map<String, Object> map);
	BaiseeDorm queryDormNum(@Param("dorm")String dorm);
	
}