package cn.baisee.oa.dao.baisee;
import java.util.List;
import java.util.Map;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.student.BaiseeStudent;
/**
 * 班级优惠Mapper接口
 * @author WANGBAOXING
 *
 */
@Datasource(DatasourceTypes.OA)
public interface BaiseeUrgrFeesMapper {
	List<BaiseeStudent> selectUrgrStu(Map<String, String> map);
}
