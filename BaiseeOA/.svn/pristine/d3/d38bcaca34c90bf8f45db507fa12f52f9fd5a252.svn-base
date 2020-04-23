package cn.baisee.oa.dao.baisee;


import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.BaiseeLeaveRecord;

import java.util.List;
import java.util.Map;
@Datasource(DatasourceTypes.OA)
public interface BaiseeLeaveRecordMapper {

    //展示页面
    public List<BaiseeLeaveRecord> getDateQueryTotalBill(Map<String, String> map);

    //查询审批框的数据
    public BaiseeLeaveRecord getId(Integer id);

    //审核通过/拒绝
    public int toreceive(Map<String, Object> map);

    //统计请假记录
    public List<BaiseeLeaveRecord> getSumLeave(Map<String, String> map);

    public String getOpenId(String student_no);

    public String getBaiseeAToken();

//    public int findReceive(Map<String, Object> map);
//
//    public int findReceive1(Map<String, Object> map);
}
