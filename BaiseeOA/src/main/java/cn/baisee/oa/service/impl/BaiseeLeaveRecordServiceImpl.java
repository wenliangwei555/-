package cn.baisee.oa.service.impl;

import cn.baisee.oa.dao.baisee.BaiseeLeaveRecordMapper;
import cn.baisee.oa.model.BaiseeAToken;
import cn.baisee.oa.model.BaiseeLeaveRecord;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeLeaveRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BaiseeLeaveRecordServiceImpl implements BaiseeLeaveRecordService {

    @Autowired
    public BaiseeLeaveRecordMapper baiseeLeaveRecordMapper;


    //展示页面


    //查询审批框的数据
    @Override
    public BaiseeLeaveRecord getId(Integer id) {
        return baiseeLeaveRecordMapper.getId(id);
    }


    //审核通过/拒绝
    @Override
    public int toreceive(Date check_time, String check_user_name, String check_user, String status, String reject_reason, Integer id) {
        Map<String, Object> map = new HashMap<>();
        map.put("check_time", check_time);
        map.put("check_user_name", check_user_name);
        map.put("check_user", check_user);
        map.put("status", status);
        map.put("reject_reason", reject_reason);
        map.put("id", id);
        return baiseeLeaveRecordMapper.toreceive(map);
    }


    //统计请假记录
    @Override
    public PageInfo<BaiseeLeaveRecord> getSumLeave(int pageNum, int pageSize, String start_time, String student_name, String c_name) {
        PageHelper.startPage(pageNum, pageSize);
        Map<String, String> map = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        String start_time1 = simpleDateFormat.format(new Date());
        map.put("student_name", student_name == null ? "" : student_name);
        map.put("c_name", c_name == null ? "" : c_name);
        if ((student_name != null && student_name != "" )|| (c_name != "" && c_name != null )) {
            map.put("start_time", start_time == null ? start_time1 : start_time);
        } else {
            map.put("start_time", start_time == null ? start_time1 : start_time == "" ? start_time1 : start_time);
        }
        List<BaiseeLeaveRecord> list = baiseeLeaveRecordMapper.getSumLeave(map);
        PageInfo<BaiseeLeaveRecord> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public PageInfo<BaiseeLeaveRecord> getDateQueryTotalBill(int pageNum, int pageSize, String check_user, String student_name, String c_name, String type, String check_user_name, String start_time, String end_time, String status) {
        PageHelper.startPage(pageNum, pageSize);
        Map<String, String> map = new HashMap<>();
        map.put("student_name", student_name == null ? "" : student_name);
        map.put("c_name", c_name == null ? "" : c_name);
        map.put("type", type == null ? "" : type);
        map.put("check_user_name", check_user_name == null ? "" : check_user_name);
        map.put("start_time", start_time == null ? "" : start_time);
        map.put("end_time", end_time == null ? "" : end_time);
        map.put("status", status == null ? "" : status);
        map.put("check_user", check_user == null ? "" : check_user);
        List<BaiseeLeaveRecord> list = baiseeLeaveRecordMapper.getDateQueryTotalBill(map);
        PageInfo<BaiseeLeaveRecord> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public String getOpenId(String student_no) {
        return baiseeLeaveRecordMapper.getOpenId(student_no);
    }

    @Override
    public String getBaiseeAToken() {
        return baiseeLeaveRecordMapper.getBaiseeAToken();
    }

//    @Override
//    public int findReceive(Integer id, String check_user) throws ParseException {
//        DateFormat df = new SimpleDateFormat("HH:mm:ss");
//        Map<String, Object> map1 = new HashMap<>();
//        BaiseeLeaveRecord b = baiseeLeaveRecordMapper.getId(id);
//        String class_no = b.getClass_no();
//        map1.put("check_user", check_user);
//        map1.put("class_no", class_no);
//        String create_time1 = df.format(b.getCreate_time());
//        Date create_time = df.parse(create_time1);
//        String start_time1 = df.format(b.getStart_time());
//        Date start_time = df.parse(start_time1);
//        String end_time1 = df.format(b.getEnd_time());
//        Date end_time = df.parse(end_time1);
//        Date df1 = df.parse("17:20:00");
//        Date df2 = df.parse("08:20:00");
//        Date df3 = df.parse("23:29:59");
//        Date df4 = df.parse("00:00:00");
//        if (((create_time.getTime() >= df1.getTime() && create_time.getTime() <= df3.getTime()) || (create_time.getTime() >= df4.getTime() && create_time.getTime() <= df2.getTime())) && ((start_time.getTime() >= df1.getTime() && start_time.getTime() <= df3.getTime()) || (start_time.getTime() >= df4.getTime() && start_time.getTime() <= df2.getTime())) && ((end_time.getTime() >= df1.getTime() && end_time.getTime() <= df3.getTime()) || (end_time.getTime() >= df4.getTime() && end_time.getTime() <= df2.getTime()))) {
//            int a = baiseeLeaveRecordMapper.findReceive(map1);
//            return a;
//        } else {
//            int c = baiseeLeaveRecordMapper.findReceive(map1);
//            if (c > 0) {
//                return 0;
//            } else {
//                int a = baiseeLeaveRecordMapper.findReceive1(map1);
//                return a;
//            }
//        }
//    }


}

