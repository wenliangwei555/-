package cn.baisee.oa.service;

import cn.baisee.oa.model.BaiseeAToken;
import cn.baisee.oa.model.BaiseeLeaveRecord;
import cn.baisee.oa.page.pagehelper.PageInfo;

import java.text.ParseException;
import java.util.Date;


public interface BaiseeLeaveRecordService {

    //查询审批框的数据
    public BaiseeLeaveRecord getId(Integer id);

    //审核通过/拒绝
    public int toreceive(Date check_time, String check_user_name, String check_user, String status, String reject_reason, Integer id);

    //统计请假记录
    public PageInfo<BaiseeLeaveRecord> getSumLeave(int pageNum, int pageSize, String start_time, String student_name, String c_name);

    //展示页面
    public PageInfo<BaiseeLeaveRecord> getDateQueryTotalBill(int pageNum, int pageSize, String check_user, String student_name, String c_name, String type, String check_user_name, String start_time, String end_time, String status);

    public String getOpenId(String student_no);

    public String getBaiseeAToken();


//    //判断他能不能审批
//    public int findReceive(Integer id,String check_user)throws ParseException;
}
