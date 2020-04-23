package cn.baisee.oa.service;

import cn.baisee.oa.model.BaiseeNoticeStaff;
import cn.baisee.oa.model.IcipBusDict;
import cn.baisee.oa.page.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

public interface BaiseeNoticeStaffService {
    public PageInfo<BaiseeNoticeStaff> staffList(int pageNum, int pageSize, String t_title, String create_id);

    public PageInfo<BaiseeNoticeStaff> addTemplate(int pageNum, int pageSize, String t_title, String create_id);


    public int addId(String t_title, String t_content, String dict_id, String[] dict_id1, String userName, String create_id, String stu_temlate_id);

    public PageInfo<IcipBusDict> select2UserList1(Integer pageNum, Integer pageSize, String itemlableSearch, String ids);

    public List<IcipBusDict> getList();

    public void deleteTemplate(String[] ids);

    public BaiseeNoticeStaff selectTemplate(String id);

    public void deleteTemplate1(String t_id);

    public int addId1(String t_id, String t_title, String t_content, String dict_id, String[] dict_id1);

    public List<BaiseeNoticeStaff> getTid(String t_id);

    public String getopenId(String user_id);

    public int addrecord(String t_title, String userName, Date date, String uId, String t_id, String user_id);

    public BaiseeNoticeStaff viewTemplate(String id);

    public int handle1(String id);

    public int closeNotice1(String noticeId);

    public String getAccess_token();
}
