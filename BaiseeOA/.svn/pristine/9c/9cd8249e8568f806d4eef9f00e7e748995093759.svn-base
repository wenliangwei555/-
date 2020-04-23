package cn.baisee.oa.dao.baisee;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.student.BaiseeStudentLeave;

/**
 * 班级的mapper接口
 *
 * @author wangbaoxiang
 */
@Datasource(DatasourceTypes.OA)
public interface BaiseeLeaveMapper {
    List<BaiseeStudentLeave> selectLeaveStu(Map<String, Object> map);

    Integer insertLeaveStu(Map<String, Object> map);

    Integer updateleaveStu(Map<String, Object> map);

    /**
     * 删除请假记录
     *
     * @param map
     * @return
     */
    Integer delleaveStu(String id);


}