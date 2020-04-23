package cn.baisee.oa.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

/**
 * 教师对学生的评论
 *
 * @author JSH
 */
public class BaiseeEvaluateInfo implements Serializable, Comparator<BaiseeEvaluateInfo> {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 所有班级
     */
    private List<BaiseeClazz> clazzs;
    /**
     * 班级名称
     */
    private String clazz;
    /**
     * 评价类型 1是教师对学生的评价 0 是学生对教师的评价
     */
    private Integer evType;
    /**
     * 评价内容
     */
    private String evInfo;
    /**
     * 时间
     */
    private String evDate;
    /**
     * 学生姓名
     */
    private String name;
    /**
     * 主键ID
     */
    private Integer evid;
    /**
     * 学生ID
     */
    private String sid;
    /**
     * 教师ID
     */
    private String emp_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public Integer getEvid() {
        return evid;
    }

    public void setEvid(Integer evid) {
        this.evid = evid;
    }

    public String getsId() {
        return sid;
    }

    public void setsId(String sId) {
        this.sid = sId;
    }

    public Integer getEvType() {
        return evType;
    }

    public void setEvType(Integer evType) {
        this.evType = evType;
    }

    public String getEvInfo() {
        return evInfo;
    }

    public void setEvInfo(String evInfo) {
        this.evInfo = evInfo;
    }

    public String getEvDate() {
        return evDate;
    }

    public void setEvDate(String evDate) {
        this.evDate = evDate;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public List<BaiseeClazz> getClazzs() {
        return clazzs;
    }

    public void setClazzs(List<BaiseeClazz> clazzs) {
        this.clazzs = clazzs;
    }

    /**
     * 它是新增的字段
     */
    private String cName;

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    @Override
    public int compare(BaiseeEvaluateInfo o1, BaiseeEvaluateInfo o2) {
        int valueOf2 = Integer.valueOf(o1.getClazz());
        int valueOf = Integer.valueOf(o2.getClazz());
        return valueOf2 - valueOf;
    }


}
