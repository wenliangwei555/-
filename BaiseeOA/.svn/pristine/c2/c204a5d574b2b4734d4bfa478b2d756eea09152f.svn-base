package cn.baisee.oa.dao.baisee;

import cn.baisee.oa.model.BaiseeNoticeStaff;
import cn.baisee.oa.model.BaiseeUser;
import cn.baisee.oa.model.IcipBusDict;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BaiseeNoticeStaffMapper {
    public List<BaiseeNoticeStaff> staffList(Map<String, String> map);

    public List<BaiseeNoticeStaff> addTemplate(Map<String, String> map);


    public List<IcipBusDict> select2UserList1(Map<String, Object> map);

    public int addNoticeStaff(BaiseeNoticeStaff baiseeNoticeStaff);

    public String getId(String s);

    public List<String> getuId(String id);

    public int addId(@Param("t_id") String t_id, @Param("s2") String s2);

    public List<String> getUserId(String s1);

    public List<IcipBusDict> getList();

    public void deleteTemplate(String[] ids);

    public void deleteTemplate1(String[] ids);

    public BaiseeNoticeStaff selectTemplate(String id);

    public void updateNoticeStaff(BaiseeNoticeStaff baiseeNoticeStaff);

    public List<BaiseeNoticeStaff> getTid(String t_id);

    public String getopenId(String user_id);


    public int addrecord(Map<String, Object> map);

    public String getUser(String user_id);

    public BaiseeNoticeStaff viewTemplate(String id);

    public void updateTemplate(@Param("id") String id, @Param("update_time") Date update_time);

    public int handle1(String id);

    public int closeNotice1(String id);

    public String getAccess_token();

}
