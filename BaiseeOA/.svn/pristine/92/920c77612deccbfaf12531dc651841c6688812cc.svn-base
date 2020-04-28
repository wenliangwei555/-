package cn.baisee.oa.service.impl;

import cn.baisee.oa.dao.baisee.BaiseeEmployeeMapper;
import cn.baisee.oa.dao.baisee.BaiseeNoticeStaffMapper;
import cn.baisee.oa.model.BaiseeNoticeStaff;
import cn.baisee.oa.model.BaiseeUser;
import cn.baisee.oa.model.IcipBusDict;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeNoticeStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BaiseeNoticeStaffServiceImpl implements BaiseeNoticeStaffService {
    @Autowired
    private BaiseeNoticeStaffMapper baiseeNoticeStaffMapper;

    @Override
    public PageInfo<BaiseeNoticeStaff> staffList(int pageNum, int pageSize, String t_title, String create_id) {
        PageHelper.startPage(pageNum, pageSize);
        Map<String, String> map = new HashMap<>();
        map.put("t_title", t_title);
        map.put("create_id", create_id);
        List<BaiseeNoticeStaff> list = baiseeNoticeStaffMapper.staffList(map);
        PageInfo<BaiseeNoticeStaff> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<BaiseeNoticeStaff> addTemplate(int pageNum, int pageSize, String t_title, String create_id) {
        PageHelper.startPage(pageNum, pageSize);
        Map<String, String> map = new HashMap<>();
        map.put("t_title", t_title);
        map.put("create_id", create_id);
        List<BaiseeNoticeStaff> list = baiseeNoticeStaffMapper.addTemplate(map);
        PageInfo<BaiseeNoticeStaff> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }


    @Override
    public PageInfo<IcipBusDict> select2UserList1(Integer pageNum, Integer pageSize, String itemlableSearch, String ids) {
        PageHelper.startPage(pageNum, pageSize);// 开始分页
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", itemlableSearch);
        map.put("id", ids);
        List<IcipBusDict> list = baiseeNoticeStaffMapper.select2UserList1(map);
        PageInfo<IcipBusDict> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<IcipBusDict> getList() {
        return baiseeNoticeStaffMapper.getList();
    }

    @Override
    public void deleteTemplate(String[] ids) {
        baiseeNoticeStaffMapper.deleteTemplate(ids);
        baiseeNoticeStaffMapper.deleteTemplate1(ids);
    }

    @Override
    public BaiseeNoticeStaff selectTemplate(String id) {
        return baiseeNoticeStaffMapper.selectTemplate(id);
    }

    @Override
    public void deleteTemplate1(String t_id) {
        String[] id = {t_id};
        baiseeNoticeStaffMapper.deleteTemplate1(id);
    }

    @Override
    public int addId1(String t_id, String t_title, String t_content, String dict_id, String[] dict_id1) {
        BaiseeNoticeStaff baiseeNoticeStaff = new BaiseeNoticeStaff();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssS");
        baiseeNoticeStaff.setT_id(t_id);
        baiseeNoticeStaff.setT_content(t_content);
        baiseeNoticeStaff.setT_title(t_title);
        baiseeNoticeStaff.setUpdate_time(new Date());
        int flag = 0;
        if (dict_id1 == null) {
            List<String> u_id = baiseeNoticeStaffMapper.getuId(dict_id);
            for (String s1 : u_id) {
                List<String> user_id = baiseeNoticeStaffMapper.getUserId(s1);
                for (String s2 : user_id) {
                    flag += baiseeNoticeStaffMapper.addId(t_id, s2);
                }
            }
        } else {
            for (String s : dict_id1) {
                List<String> user_id = baiseeNoticeStaffMapper.getUserId(s);
                for (String s2 : user_id) {
                    flag += baiseeNoticeStaffMapper.addId(t_id, s2);
                }
            }
        }
        if (flag > 0) {
            baiseeNoticeStaffMapper.updateNoticeStaff(baiseeNoticeStaff);
        }
        System.err.println("总共人数：" + flag);

        return flag;
    }

    @Override
    public List<BaiseeNoticeStaff> getTid(String t_id) {
        return baiseeNoticeStaffMapper.getTid(t_id);
    }

    @Override
    public String getopenId(String user_id) {
        return baiseeNoticeStaffMapper.getopenId(user_id);
    }

    @Override
    public int addrecord(String t_title, String userName, Date date, String uId, String t_id, String user_id) {
        Map<String, Object> map = new HashMap<>();
        String baiseeEmployee = baiseeNoticeStaffMapper.getUser(user_id);
        map.put("t_title", t_title);
        map.put("userName", userName);
        map.put("date", date);
        map.put("uId", uId);
        map.put("t_id", t_id);
        map.put("user_id", user_id);
        map.put("user_name", baiseeEmployee);
        map.put("update_time", new Date());
        return baiseeNoticeStaffMapper.addrecord(map);
    }

    @Override
    public BaiseeNoticeStaff viewTemplate(String id) {
        BaiseeNoticeStaff b = baiseeNoticeStaffMapper.viewTemplate(id);
        if (b.getType1() == '0') {
            baiseeNoticeStaffMapper.updateTemplate(id, new Date());
            return baiseeNoticeStaffMapper.viewTemplate(id);
        }
        return b;
    }

    @Override
    public int handle1(String id) {
        return baiseeNoticeStaffMapper.handle1(id);
    }

    @Override
    public int closeNotice1(String noticeId) {
        return baiseeNoticeStaffMapper.closeNotice1(noticeId);
    }

    @Override
    public String getAccess_token() {
        return baiseeNoticeStaffMapper.getAccess_token();
    }

    @Override
    public int addId(String t_title, String t_content, String dict_id, String[] dict_id1, String userName, String create_id, String stu_temlate_id) {
        BaiseeNoticeStaff baiseeNoticeStaff = new BaiseeNoticeStaff();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssS");
        baiseeNoticeStaff.setT_id("MMBT" + df.format(new Date()));
        baiseeNoticeStaff.setCreate_id(create_id);
        baiseeNoticeStaff.setCreate_name(userName);
        baiseeNoticeStaff.setCreate_time(new Date());
        baiseeNoticeStaff.setStu_temlate_id(stu_temlate_id);
        baiseeNoticeStaff.setT_content(t_content);
        baiseeNoticeStaff.setT_title(t_title);
        baiseeNoticeStaff.setUpdate_time(new Date());
        int flag = 0;
        if (dict_id1 == null) {
            String t_id = baiseeNoticeStaff.getT_id();
            List<String> u_id = baiseeNoticeStaffMapper.getuId(dict_id);
            for (String s1 : u_id) {
                List<String> user_id = baiseeNoticeStaffMapper.getUserId(s1);
                for (String s2 : user_id) {
                    flag += baiseeNoticeStaffMapper.addId(t_id, s2);
                }
            }
        } else {
            String t_id = baiseeNoticeStaff.getT_id();
            for (String s : dict_id1) {
                List<String> user_id = baiseeNoticeStaffMapper.getUserId(s);
                for (String s2 : user_id) {
                    flag += baiseeNoticeStaffMapper.addId(t_id, s2);
                }
            }
        }
        if (flag > 0) {
            baiseeNoticeStaffMapper.addNoticeStaff(baiseeNoticeStaff);
        }
        System.err.println("总共人数：" + flag);

        return flag;
    }
}
