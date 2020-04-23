package cn.baisee.oa.dao.baisee;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.student.BaiseeStudentFile;
/**
 * 班级的mapper接口
 * @author wangbaoxiang
 *
 */
@Datasource(DatasourceTypes.OA)
public interface BaiseeFileMapper {	
	List<BaiseeStudentFile> selectFile(Map<String, Object> map);
	Integer insertFile(Map<String, Object> map);
	Integer delFile(Map<String, Object> map);
}