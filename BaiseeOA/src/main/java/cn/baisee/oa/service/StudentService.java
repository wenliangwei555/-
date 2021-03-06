package cn.baisee.oa.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.baisee.oa.model.BaiseeClassMerger;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.model.BaiseeClazz;
import cn.baisee.oa.model.BaiseeUser;
import cn.baisee.oa.model.PhoneAndID;
import cn.baisee.oa.model.empinfo.Employee;
import cn.baisee.oa.model.student.BaiseeStudent;
import cn.baisee.oa.model.student.BaiseeStudentEducation;
import cn.baisee.oa.model.student.BaiseeStudentFamily;
import cn.baisee.oa.model.student.BaiseeStudentRecord;
import cn.baisee.oa.model.student.BaiseeStudentSupplement;
import cn.baisee.oa.page.pagehelper.PageInfo;

public interface StudentService {
    /**
     * 查询所有试听学生
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<BaiseeStudent> getAudList(int pageNum, int pageSize, String itemlableSearch, String status, String startTime, String endTime, HttpServletRequest request, String claStatus, String areas) throws Exception;

    /**
     * 查询所有正式学生
     *
     * @param pageNum
     * @param pageSize
     * @param itemlableSearch
     * @param pageInfo
     * @return
     * @throws Exception
     */
    public PageInfo<BaiseeStudent> getForList(int pageNum, int pageSize, String itemlableSearch, String status,
                                              String startTime, String endTime, String returnFeeStatus, HttpServletRequest request, String claStatus, String areas, String choiceStuState) throws Exception;

    /**
     * 修改学生状态
     *
     * @param stuId
     * @throws Exception
     */
    public int updateStatus(String stuId, String addParam, String operator, String operationStart, String operationEnd) throws Exception;

    /**
     * 修改试听班级为正式班级
     *
     * @param stuId
     * @param claId
     * @return
     */
    public int updateAudToFor(String stuId, String claId, String stuEnrolmentTime) throws Exception;

    /**
     * 校验学生身份证信息
     *
     * @param stuIdNumber
     * @return
     * @throws Exception
     */
    public String selectStudentIDNumberVerification(String stuIdNumber, String stuId) throws Exception;

    public String selectStuIDNumberVerification(String stuIdNumber);


    /**
     * 校验员工身份证信息
     *
     * @param empIdNumber
     * @return
     */
    public String selectEmployeeIDNumberVerification(String empIdNumber) throws Exception;

    /**
     * 校验学生手机信息
     *
     * @param stuIdNumber
     * @return
     * @throws Exception
     */
    public String selectStudentMobileVerification(String stuMobile, String stuId) throws Exception;

    /**
     * 校验员工手机号
     *
     * @param empMobile
     * @return
     */
    public String selectEmployeeMobileVerification(@Param("empMobile") String empMobile) throws Exception;

    /**
     * 查询所有班级下拉显示,根据clatype查询
     *
     * @return
     * @throws Exception
     */
     List<BaiseeClazz> selectClass(String claType) throws Exception;

    /************************************执行添加*************************************/
    /**
     * 1.添加学生基本信息
     *
     * @param student
     */
    public void addStudent(BaiseeStudent student) throws Exception;

    /**
     * 2.添加学生附加信息
     *
     * @param supplement
     */
    public void addStudentSupplement(BaiseeStudentSupplement supplement) throws Exception;

    /**
     * 3.添加学生教育信息
     *
     * @param education
     */
    public void addStudentEducation(BaiseeStudentEducation education) throws Exception;

    /**
     * 4.添加学生家庭信息
     *
     * @param family
     */
    public void addStudentFamily(BaiseeStudentFamily family) throws Exception;


    /**
     * 学生添加修改页面跳转
     *
     * @param request
     * @return
     */
     ModelAndView getStuPageJump(HttpServletRequest request) throws Exception;

    /**
     * 判断执行添加或修改，并执行
     *
     * @param stuHeadPhotoDir
     * @param student
     * @param supplement
     * @param education
     * @param family
     * @param request
     * @return
     * @throws Exception
     */
     ModelAndView toSaveOrUpdateStu(MultipartFile stuHeadPhotoDir, BaiseeStudent student, BaiseeStudentSupplement supplement, BaiseeStudentEducation education, BaiseeStudentFamily family, HttpServletRequest request) throws Exception;

    /************************************* 根据stuid查询 **************************************/
    /**
     * 通过stuid查询
     * 1.通过stuid查询学生基本信息
     *
     * @param stuId
     * @return
     */
    public BaiseeStudent selectByStudentId(String stuId) throws Exception;

    /**
     * 2.通过stuid查询学生家庭信息
     *
     * @param stuId
     * @return
     */
    public List<BaiseeStudentFamily> selectByStudentIdQueryFamily(String stuId) throws Exception;

    /**
     * 3.通过stuid查询学生教育信息
     *
     * @param stuId
     * @return
     */
    public BaiseeStudentEducation selectByStudentIdQueryEducation(String stuId) throws Exception;

    /**
     * 4.通过stuid查询学生附加信息
     *
     * @param stuId
     * @return
     */
    public BaiseeStudentSupplement selectByStudentIdQuerySupplement(String stuId) throws Exception;


    /*******************************************执行修改***************************************************/
    /**
     * 通过id修改
     * 1.通过id修改学生基本信息
     *
     * @param student
     */
    public void updateStudentById(BaiseeStudent student) throws Exception;

    /**
     * 2.通过id修改学生附加信息
     *
     * @param supplement
     */
    public void updateStudentSupById(BaiseeStudentSupplement supplement) throws Exception;

    /**
     * 3.通过id修改学生教育信息
     *
     * @param education
     */
    public void updateStudentEduById(BaiseeStudentEducation education) throws Exception;

    /**
     * 4.通过id修改学生家庭信息
     *
     * @param family
     */
    public void updateStudentFamById(BaiseeStudentFamily family) throws Exception;


    /**
     * 批量删除
     *
     * @param ids
     */
    public void DeleteStudentAll(String[] ids);

    /**
     * 读取学生的手机号和身份证
     *
     * @return
     */

    List<PhoneAndID> readIDAndPhone();

    /**
     * 批量导入学生信息显示的结果集
     */

    Map<String, Object> doService(InputStream inputStream);

    /**
     * 批量导入试听学生信息显示的结果集
     */

    Map<String, Object> importStudentResult(InputStream inputStream, HttpServletRequest request);

    /**
     * 点击退费按钮
     *
     * @return
     */
    Object doRefund(String stuId, String tuId, String stuEnrolmentTime);

    /**
     * 退费记录流水
     *
     * @param map
     * @return
     */
    ModelAndView doRefundFlow(Map<String, Object> map);

    /**
     * 修改这个学生的缴学费状态  1：缴纳清楚，2：未缴纳清楚
     *
     * @param map
     */
    void updateStudentTuitionStatusById(Map<String, String> map);

    /**
     * 查询缴费list页面 ,返回学生信息
     *
     * @param map
     * @return
     */
    public PageInfo<BaiseeStudent> selectForTuitionList(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map);

    /**
     * 根据学生姓名筛选出学生和班级
     *
     * @param pageNum  当前页
     * @param pageSize 每页的数量
     * @param sName    学生姓名
     * @return
     */
    public PageInfo<BaiseeStudent> selectStuAndClass(int pageNum, int pageSize,
                                                     String itemlableSearch);


    public BaiseeStudentRecord selectStuRecord(String stuId);

    /**
     * 查询学生评定
     *
     * @param stuId
     * @return
     */
    public BaiseeStudentRecord selectStuAssess(String stuId);

    /**
     * 预报名转试听学生
     */
    void applicantsToAudition(BaiseeStudent baiseeStudent, String audition, String operator, String prepay);

    /**
     * 根据班级查询学生
     *
     * @param cid
     * @return
     */
    List<BaiseeStudent> selectStuAndClass(String cid);

    /**
     * 学生开户
     */
    int udateStuState(String sid, BaiseeUser user);


    /**
     * 注销学生的账号
     */
    int deleteStuUser(String sId);

    String getUpdateTime(String stuId);

    /**
     * 查询所有正式学生
     *
     * @param pageNum
     * @param pageSize
     * @param itemlableSearch
     * @param pageInfo
     * @return
     * @throws Exception
     */
    public PageInfo<BaiseeStudent> getForList(int pageNum, int pageSize, String itemlableSearch, HttpServletRequest request) throws Exception;

    /*
     *
     *
     * 合班
     * */
    public int updatMerger(String[] strs, String cid, String U_NAME);

    /*
     * 查询合班纪录
     * */
    public PageInfo<BaiseeClassMerger> selectMerger(String stu_name, String old_cname, String new_cname, String stuStartTime, String stuEndTime, String u_name, int pageNum, int pageSize);
}

