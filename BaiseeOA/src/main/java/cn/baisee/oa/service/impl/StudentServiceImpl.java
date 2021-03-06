package cn.baisee.oa.service.impl;

import java.io.File;
import java.io.InputStream;
import java.text.ParseException;
import java.util.*;

import javax.annotation.Resource;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.baisee.oa.constants.SourceConstant;
import cn.baisee.oa.model.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.constants.CacheKeyEnum;
import cn.baisee.oa.controller.ClazzController;
import cn.baisee.oa.core.context.ICIPContext;
import cn.baisee.oa.core.file.FileDir;
import cn.baisee.oa.core.file.FileUploadUtil;
import cn.baisee.oa.dao.baisee.BaiseeClazzMapper;
import cn.baisee.oa.dao.baisee.BaiseeDictMapper;
import cn.baisee.oa.dao.baisee.BaiseeInsuranceMapper;
import cn.baisee.oa.dao.baisee.BaiseeStuMapper;
import cn.baisee.oa.dao.baisee.BaiseeStudentApplicantsMapper;
import cn.baisee.oa.dao.baisee.BaiseeTuitionMapper;
import cn.baisee.oa.dao.stu.StuEvaluateMapper;
import cn.baisee.oa.exception.AppException;
import cn.baisee.oa.exception.OAServiceException;
import cn.baisee.oa.importExcel.dto.BaseDto;
import cn.baisee.oa.importExcel.dto.RowValidateResultDto;
import cn.baisee.oa.importExcel.exception.InvalidExcelTemplateException;
import cn.baisee.oa.importExcel.filter.AbstractFilterChain;
import cn.baisee.oa.importExcel.filter.chain.AuditionStudentCheckFilterChain;
import cn.baisee.oa.importExcel.filter.chain.BaseCheckFilterChain;
import cn.baisee.oa.importExcel.helper.ExcelToBeanConvertor;
import cn.baisee.oa.model.student.BaiseeAuditionStudent;
import cn.baisee.oa.model.student.BaiseeStuUser;
import cn.baisee.oa.model.student.BaiseeStudent;
import cn.baisee.oa.model.student.BaiseeStudentEducation;
import cn.baisee.oa.model.student.BaiseeStudentFamily;
import cn.baisee.oa.model.student.BaiseeStudentRecord;
import cn.baisee.oa.model.student.BaiseeStudentRefund;
import cn.baisee.oa.model.student.BaiseeStudentSupplement;
import cn.baisee.oa.model.tuition.RefundRule;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeTransactionService;
import cn.baisee.oa.service.StudentService;
import cn.baisee.oa.utils.DateUtil;
import cn.baisee.oa.utils.ParamUtils;
import cn.baisee.oa.utils.RedisUtils;
import cn.baisee.oa.utils.SessionUtil;
import cn.baisee.oa.utils.StringUtil;
import cn.baisee.oa.utils.VelocityParserUtil;

@Service
public class StudentServiceImpl implements StudentService {
    private static final Log logger = LogFactory.getLog(ClazzController.class);
    @Autowired
    private BaiseeStudentApplicantsMapper baiseeStudentApplicantsMapper;
    //流水service
    @Autowired
    private BaiseeTransactionService baiseeTransactionService;
    //学费规则mapper
    @Autowired
    private BaiseeTuitionMapper baiseeTuitionMapper;
    @Autowired
    private BaiseeDictMapper baiseeDictMapper;
    @Autowired
    private BaiseeStuMapper baiseeStuMapper;
    @Autowired
    private BaiseeTransactionService transactionService;
    @Autowired
    private BaiseeInsuranceMapper insuranceMapper;
    @Autowired
    private StuEvaluateMapper stuMapper;
    @Autowired
    private BaiseeClazzMapper baiseeClazzMapper;

    // 缓存的工具类
    @Resource(name = "redisUtils")
    private RedisUtils redisUtils;

    static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");

    //创建这个学生的本期总欠费用
    //public static Integer totalOweMoneyByPeriod;

    /**
     * 查询所有试听学生
     */
    @Override
    public PageInfo<BaiseeStudent> getAudList(int pageNum, int pageSize, String itemlableSearch, String status,
                                              String startTime, String endTime, HttpServletRequest request, String claStatus, String areas) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        String[] cIds = (String[]) request.getSession().getAttribute("bsClass");
        String userType = SessionUtil.getUserInfo(request).getUserType();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("itemlableSearch", itemlableSearch);
        map.put("status", status);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("cIds", cIds);
        map.put("areas", areas);
        if (StringUtils.isNotEmpty(userType)) {
            map.put("cStatus", userType);
        } else {
            map.put("cStatus", claStatus);
        }
        List<BaiseeStudent> list = baiseeStuMapper.selectAllAudStu(map);
        for (BaiseeStudent baiseeStudent : list) {
            if (baiseeStudent.getStuArea() != null) {
                baiseeStudent.setStuHomeAddress(StringUtil.ifNullToBlank(baiseeDictMapper.selectByProvinceCode(baiseeStudent.getStuProvince())) + StringUtil.ifNullToBlank(baiseeDictMapper.selectByCityCode(baiseeStudent.getStuCity())) + StringUtil.ifNullToBlank(baiseeDictMapper.selectByAreaCode(baiseeStudent.getStuArea())));
            }
        }
        PageInfo<BaiseeStudent> page = new PageInfo<BaiseeStudent>(list);
        return page;
    }

    /**
     * 查询所有正式学生
     */
    @Override
    public PageInfo<BaiseeStudent> getForList(int pageNum, int pageSize, String itemlableSearch, String status,
                                              String startTime, String endTime, String returnFeeStatus, HttpServletRequest request, String claStatus, String areas, String choiceStuState) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        String[] cIds = (String[]) request.getSession().getAttribute("bsClass");
        for (int i = 0; i < cIds.length; i++) {
            System.err.println(cIds[i]);
        }
        String userType = SessionUtil.getUserInfo(request).getUserType();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("itemlableSearch", itemlableSearch);
        map.put("status", status);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("returnFeeStatus", returnFeeStatus);
        map.put("cIds", cIds);
        map.put("areas", areas);
        map.put("choiceStuState", choiceStuState);
        if (StringUtils.isNotEmpty(userType)) {
            map.put("cStatus", userType);
        } else {
            map.put("cStatus", claStatus);
        }
        List<BaiseeStudent> list = baiseeStuMapper.selectAllForStu(map);
        for (BaiseeStudent baiseeStudent : list) {
            if (baiseeDictMapper.selectByAreaCode(baiseeStudent.getStuArea()) != null) {
                baiseeStudent.setStuHomeAddress(StringUtil.ifNullToBlank(baiseeDictMapper.selectByProvinceCode(baiseeStudent.getStuProvince())) + StringUtil.ifNullToBlank(baiseeDictMapper.selectByCityCode(baiseeStudent.getStuCity())) + StringUtil.ifNullToBlank(baiseeDictMapper.selectByAreaCode(baiseeStudent.getStuArea())));
            }
            Integer count = insuranceMapper.selectStuInsuInfoByStuId(baiseeStudent.getStuId());
            if (count > 0) {
                baiseeStudent.setAudCid("1");
            } else {
                baiseeStudent.setAudCid("0");
            }


        }
        PageInfo<BaiseeStudent> pageInfo = new PageInfo<BaiseeStudent>(list);
        return pageInfo;
    }

    /**
     * 修改学生状态
     */
    @Override
    public int updateStatus(String stuId, String addParam, String operator, String operationStart, String operationEnd) throws Exception {
        if (!("2".equals(addParam) || "4".equals(addParam))) {
            deleteStuUser(stuId);
        }

        String SID = baiseeStuMapper.selectSidFromOperation(stuId);//从操作表获取sid
        if (SID != null) {//在这里进行判断，如果操作表中有SID，就去执行修改，没有就会去执行增加
            baiseeStuMapper.updateOperation(stuId, operator, operationStart, operationEnd);
        } else {
            baiseeStuMapper.insertOperation(stuId, operator, operationStart, operationEnd);
        }
        return baiseeStuMapper.updateStatus(stuId, addParam);//先修改状态
    }

    /**
     * 修改学员试听班级为正式班级
     */
    @Override
    public int updateAudToFor(String stuId, String claId, String stuEnrolmentTime) throws Exception {

        return baiseeStuMapper.updateAudToFor(stuId, claId, stuEnrolmentTime);
    }

    /**
     * 查询所有班级根据claType查询
     */
    @Override
    public List<BaiseeClazz> selectClass(String claType) throws Exception {
        return baiseeStuMapper.selectClass(claType);
    }

    /**
     * 校验学生身份信息
     */
    @Override
    public String selectStudentIDNumberVerification(String stuIdNumber, String stuId) throws Exception {

        return baiseeStuMapper.selectStudentIDNumberVerification(stuIdNumber, stuId);
    }

    /**
     * 校验学生身份信息
     */
    @Override
    public String selectStuIDNumberVerification(String stuIdNumber) {

        return baiseeStuMapper.selectStuIDNumberVerification(stuIdNumber);
    }

    /**
     * 校验员工身份信息
     */
    @Override
    public String selectEmployeeIDNumberVerification(String empIdNumber) throws Exception {

        return baiseeStuMapper.selectEmployeeIDNumberVerification(empIdNumber);
    }

    /**
     * 校验学生手机号
     */
    @Override
    public String selectStudentMobileVerification(String stuMobile, String stuId) throws Exception {
        return baiseeStuMapper.selectStudentMobileVerification(stuMobile, stuId);
    }

    /**
     * 校验员工手机号
     */
    @Override
    public String selectEmployeeMobileVerification(String empMobile) throws Exception {
        return baiseeStuMapper.selectEmployeeMobileVerification(empMobile);
    }

    /********************************
     * 执行添加，四张表四个添加
     **********************************************/
    @Override
    public void addStudent(BaiseeStudent student) throws Exception {
        baiseeStuMapper.addStudent(student);

    }

    @Override
    public void addStudentSupplement(BaiseeStudentSupplement supplement) throws Exception {
        baiseeStuMapper.addStudentSupplement(supplement);

    }

    @Override
    public void addStudentEducation(BaiseeStudentEducation education) throws Exception {
        baiseeStuMapper.addStudentEducation(education);

    }

    @Override
    public void addStudentFamily(BaiseeStudentFamily family) throws Exception {
        baiseeStuMapper.addStudentFamily(family);

    }

    /**
     * 执行学生增加或修改
     *
     * @param stuHeadPhotoDir
     * @param student
     * @param supplement
     * @param education
     * @param family
     * @param request
     * @return
     */
    public ModelAndView toSaveOrUpdateStu(MultipartFile stuHeadPhotoDir, BaiseeStudent student,
                                          BaiseeStudentSupplement supplement, BaiseeStudentEducation education, BaiseeStudentFamily family,
                                          HttpServletRequest request) throws Exception {
        ModelAndView model = new ModelAndView();
        String stuTable = ParamUtils.getParameter(request, "stuTable");
        String checked = ParamUtils.getParameter(request, "audition");
        if (student.getStuId() != null && student.getStuId() != "" && student.getStuId().length() > 10) {// stuId有值,放到session中
            request.getSession().setAttribute("stuId", student.getStuId());
        }
        if (stuTable != null && stuTable != "") {
            if (stuTable.equalsIgnoreCase("audStudent")) {// 试听生学生基础信息进行修改或增加
                if (student.getStuId() != null && student.getStuId() != "" && student.getStuId().length() > 10) {// 表示是修改
                    // 调用修改方法
                    List<BaiseeClazz> list = selectClass("0");
                    String updateTime = getUpdateTime(student.getStuId());
                    model.addObject("updateTime", updateTime);
                    model.addObject("list", list);
                    updateStudentById(student);
                    model.addObject("operationSuccess", "操作成功");
                    model.addObject("stu", student);
                    model.setViewName("student/stu_AudStuEdit");
                    return model;
                } else {// 表示是添加
                    List<BaiseeClazz> list = selectClass("0");
                    model.addObject("list", list);
                    addStudent(student);
                    request.getSession().setAttribute("stuId", student.getStuId());
                    student.setStuId(student.getStuId());
                    if ("1".equals(checked)) {
                        //添加完成后加入一笔试听费的流水
                        BaiseeUser user = (BaiseeUser) SessionUtil.getUserInfo(request);
                        String operator = user.getUserId();
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("amount", "300");
                        map.put("operator", operator);
                        map.put("userID", student.getStuId());
                        map.put("remarks", "无");
                        map.put("type", "试听费");
                        map.put("status", "1");
                        transactionService.addReturnFee(map);
                    }
                    model.addObject("operationSuccess", "操作成功");
                    model.addObject("stu", student);
                    model.addObject("true", true);
                    model.setViewName("student/stu_AudStuEdit");

                    return model;

                }

            } else if (stuTable.equalsIgnoreCase("audStudentSupplement")) {// 试听生传过来的值为附加信息
                if (supplement.getPkBesi() != null && supplement.getPkBesi() > 0) {// 主键有值，表示为修改
                    // 根据id查询学生附加信息，判断图片
                    /**
                     * 判断图片，进行上传图片
                     */
                    File file = FileUploadUtil.getSingleFile(request);
                    if (file != null && file.getName().length() > 0) {
                        FileUploadUtil.uploadFile(file, null, FileDir.S_PICTURE_DIR, student.getStuId());
                        StringBuffer buffer = new StringBuffer();
                        buffer.append("/student").append("/").append(student.getStuId())
                                .append(file.getName().substring(file.getName().indexOf(".")).toLowerCase());
                        supplement.setStuHeadPhotoDir(buffer.toString());
                    }
                    // 修改信息
                    updateStudentSupById(supplement);
                    // 再进行跳转页面
                    BaiseeStudentSupplement sup = selectByStudentIdQuerySupplement(supplement.getStuId());
                    model.addObject("operationSuccess", "操作成功");
                    model.addObject("sup", sup);
                    model.setViewName("student/stu_AudSupEdit");
                    return model;
                } else {// 附加信息执行增加

                    /**
                     * 判断是否上传了图片，进行上传图片
                     */
                    File file = FileUploadUtil.getSingleFile(request);
                    if (file != null && file.getName().length() > 0) {
                        FileUploadUtil.uploadFile(file, null, FileDir.S_PICTURE_DIR, student.getStuId());
                        StringBuffer buffer = new StringBuffer();
                        buffer.append("/student").append("/").append(student.getStuId())
                                .append(file.getName().substring(file.getName().indexOf(".")).toLowerCase());
                        supplement.setStuHeadPhotoDir(buffer.toString());
                    }
                    // 添加信息
                    addStudentSupplement(supplement);
                    // 添加信息，跳转页面
                    supplement.setPkBesi(supplement.getPkBesi());
                    BaiseeStudentSupplement sup = selectByStudentIdQuerySupplement(supplement.getStuId());
                    model.addObject("operationSuccess", "操作成功");
                    model.addObject("sup", sup);
                    model.setViewName("student/stu_AudSupEdit");
                    return model;
                }
            } else if (stuTable.equalsIgnoreCase("audStudentEducation")) {// 试听学生教育信息编辑
                if (education.getPkBegi() != null && education.getPkBegi() > 0) {// 教育信息修改
                    // 调用修改方法
                    //education.setUpdateTime(DateUtil.getNow(DateUtil.DATE_FORMAT_FULL));
                    updateStudentEduById(education);
                    // 跳转页面
                    model.addObject("operationSuccess", "操作成功");
                    model.addObject("edu", education);
                    model.setViewName("student/stu_AudEduEdit");
                    return model;
                } else {
                    // 添加添加页面
                    addStudentEducation(education);
                    // 跳转页面
                    education.setPkBegi(education.getPkBegi());
                    model.addObject("operationSuccess", "操作成功");
                    model.addObject("edu", education);
                    model.setViewName("student/stu_AudEduEdit");
                    return model;
                }
            } else if (stuTable.equalsIgnoreCase("audStudentFamily")) {// 试听学生家庭信息编辑
                System.out.println("为判断" + family);
                if (family.getPkBefi() != null && family.getPkBefi() > 0) {// 修改方法
                    // 调用修改方法
                    updateStudentFamById(family);
                    System.out.println(family);
                    // 跳转页面
                    List<BaiseeStudentFamily> list = selectByStudentIdQueryFamily(family.getStuId());
                    model.addObject("operationSuccess", "操作成功");
                    model.addObject("list", list);
                    model.setViewName("student/stu_AudFamEdit");
                    return model;
                } else {
                    // 添加方法
                    System.out.println("增加" + family);
                    education.setUpdateTime(DateUtil.getNow(DateUtil.DATE_FORMAT_FULL));
                    addStudentFamily(family);
                    // 跳转页面
                    //family.setPkBefi(family.getPkBefi());
                    List<BaiseeStudentFamily> list = selectByStudentIdQueryFamily(family.getStuId());
                    model.addObject("operationSuccess", "操作成功");
                    model.addObject("list", list);
                    model.setViewName("student/stu_AudFamEdit");
                    return model;
                }
            } else if (stuTable.equalsIgnoreCase("forStudent")) {// 正式生

                if (student.getStuId() != null && student.getStuId() != "" && student.getStuId().length() > 10) {// 表示是修改
                    if (StringUtil.isNotEmpty(student.getClaId())) {//班级不为空则修改保险的班级
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("cId", student.getClaId());
                        BaiseeClazz baiseeClazz = baiseeClazzMapper.getClaById(map);
                        insuranceMapper.updateClassNameBystuID(student.getStuId(), baiseeClazz.getcName(), student.getClaId());

                    }

                    // 调用修改方法
                    List<BaiseeClazz> list = selectClass("1");
                    String updateTime = getUpdateTime(student.getStuId());
                    model.addObject("updateTime", updateTime);
                    model.addObject("list", list);
                    updateStudentById(student);
                    model.addObject("operationSuccess", "操作成功");
                    model.addObject("stu", student);
                    model.setViewName("student/stu_ForStuEdit");
                    return model;
                }
            } else if (stuTable.equalsIgnoreCase("forStudentSupplement")) {// 正式生附加信息
                if (supplement.getPkBesi() != null && supplement.getPkBesi() > 0) {// 主键有值，表示为修改
                    // 根据id查询学生附加信息，判断图片
                    /**
                     * 判断图片，进行上传图片
                     */
                    File file = FileUploadUtil.getSingleFile(request);
                    if (file != null && file.getName().length() > 0) {
                        FileUploadUtil.uploadFile(file, null, FileDir.S_PICTURE_DIR, student.getStuId());
                        StringBuffer buffer = new StringBuffer();
                        buffer.append("/student").append("/").append(student.getStuId())
                                .append(file.getName().substring(file.getName().indexOf(".")).toLowerCase());
                        supplement.setStuHeadPhotoDir(buffer.toString());
                    }
                    // 修改信息
                    updateStudentSupById(supplement);
                    BaiseeStudentSupplement sup = selectByStudentIdQuerySupplement(supplement.getStuId());
                    // 再进行跳转页面
                    model.addObject("operationSuccess", "操作成功");
                    model.addObject("sup", sup);
                    model.setViewName("student/stu_ForSupEdit");
                    return model;
                } else {// 附加信息执行增加

                    /**
                     * 判断是否上传了图片，进行上传图片
                     */
                    File file = FileUploadUtil.getSingleFile(request);
                    if (file != null && file.getName().length() > 0) {
                        FileUploadUtil.uploadFile(file, null, FileDir.S_PICTURE_DIR, student.getStuId());
                        StringBuffer buffer = new StringBuffer();
                        buffer.append("/student").append("/").append(student.getStuId())
                                .append(file.getName().substring(file.getName().indexOf(".")).toLowerCase());
                        supplement.setStuHeadPhotoDir(buffer.toString());
                    }
                    // 添加信息
                    addStudentSupplement(supplement);
                    // 添加信息，跳转页面
                    supplement.setPkBesi(supplement.getPkBesi());
                    BaiseeStudentSupplement sup = selectByStudentIdQuerySupplement(supplement.getStuId());
                    model.addObject("operationSuccess", "操作成功");
                    model.addObject("sup", sup);
                    model.setViewName("student/stu_ForSupEdit");
                    return model;
                }
            } else if (stuTable.equalsIgnoreCase("forStudentEducation")) {// 正式生学生教育信息编辑
                if (education.getPkBegi() != null && education.getPkBegi() > 0) {// 正式生教育信息修改
                    // 调用修改方法
                    updateStudentEduById(education);
                    // 跳转页面
                    model.addObject("operationSuccess", "操作成功");
                    model.addObject("edu", education);
                    model.setViewName("student/stu_ForEduEdit");
                    return model;
                } else {
                    // 添加添加页面
                    addStudentEducation(education);
                    // 跳转页面
                    education.setPkBegi(education.getPkBegi());
                    model.addObject("operationSuccess", "操作成功");
                    model.addObject("edu", education);
                    model.setViewName("student/stu_ForEduEdit");
                    return model;
                }
            } else if (stuTable.equalsIgnoreCase("forStudentFamily")) {// 正式生学生家庭信息编辑
                if (family.getPkBefi() != null && family.getPkBefi() > 0) {// 修改方法
                    // 调用修改方法
                    updateStudentFamById(family);
                    // 跳转页面
                    List<BaiseeStudentFamily> list = selectByStudentIdQueryFamily(family.getStuId());
                    model.addObject("operationSuccess", "操作成功");
                    // 跳转页面
                    model.addObject("list", list);
                    model.setViewName("student/stu_ForFamEdit");
                    return model;
                } else {
                    // 添加方法
                    addStudentFamily(family);
                    // 跳转页面
                    family.setPkBefi(family.getPkBefi());
                    List<BaiseeStudentFamily> list = selectByStudentIdQueryFamily(family.getStuId());
                    model.addObject("operationSuccess", "操作成功");
                    model.addObject("list", list);
                    model.setViewName("student/stu_ForFamEdit");
                    return model;
                }
            }
        }
        return model;
    }

    /**
     * 添加修改时进行页面跳转
     */
    @Override
    public ModelAndView getStuPageJump(HttpServletRequest request) throws Exception {

        ModelAndView model = new ModelAndView();
        String stuTable = ParamUtils.getParameter(request, "stuTable");

        if (stuTable.equalsIgnoreCase("audStudentEducationJump")) {// 试听学生教育跳转页面
            String stuId = (String) request.getSession().getAttribute("stuId");
            BaiseeStudentEducation education = selectByStudentIdQueryEducation(stuId);
            if (education == null || education.getStuId() == null || education.getStuId() == "") {
                model.addObject("edu", new BaiseeStudentEducation(stuId));
            } else {
                model.addObject("edu", education);
            }
            model.setViewName("student/stu_AudEduEdit");
            return model;
        } else if (stuTable.equalsIgnoreCase("audStudentSupplementJump")) {// 试听学生补充表跳转 试听学生附加信息
            String stuId = (String) request.getSession().getAttribute("stuId");
            BaiseeStudentSupplement sup = selectByStudentIdQuerySupplement(stuId);
            if (sup == null || sup.getStuId() == null || sup.getStuId() == "") {// 就是没有值
                model.addObject("sup", new BaiseeStudentSupplement(stuId));
            } else {
                model.addObject("sup", sup);
            }
            model.setViewName("student/stu_AudSupEdit");
            return model;
        } else if (stuTable.equalsIgnoreCase("audStudentFamilyJump")) {// 试听学生家庭信息表
            String stuId = (String) request.getSession().getAttribute("stuId");
            List<BaiseeStudentFamily> list = selectByStudentIdQueryFamily(stuId);
            if (list.size() == 0) {
                model.addObject("operationSuccess", "暂无数据");
            }

            model.addObject("list", list);
            model.addObject("stuId", stuId);
            model.setViewName("student/stu_AudFamEdit");
            return model;
        } else if (stuTable.equalsIgnoreCase("audStudentJump")) {// 跳转到试听学生基本信息页面
            String stuId = (String) request.getSession().getAttribute("stuId");
            BaiseeStudent student = selectByStudentId(stuId);
            List<BaiseeClazz> list = selectClass("0");
            String updateTime = getUpdateTime(stuId);
            model.addObject("updateTime", updateTime);
            model.addObject("list", list);
            model.addObject("stu", student);
            model.setViewName("student/stu_AudStuEdit");
            return model;
        } else if (stuTable.equalsIgnoreCase("forStudentJump")) {// 跳转到正式学生基本信息页面
            String stuId = (String) request.getSession().getAttribute("stuId");
            BaiseeStudent student = selectByStudentId(stuId);
            String updateTime = getUpdateTime(stuId);
            model.addObject("updateTime", updateTime);
            List<BaiseeClazz> list = selectClass("1");
            model.addObject("list", list);
            model.addObject("stu", student);
            model.setViewName("student/stu_ForStuEdit");
            return model;
        } else if (stuTable.equalsIgnoreCase("forStudentFamilyJump")) {// 正式学生家庭信息表
            String stuId = (String) request.getSession().getAttribute("stuId");
            List<BaiseeStudentFamily> list = selectByStudentIdQueryFamily(stuId);
            if (list.size() == 0) {
                model.addObject("operationSuccess", "暂无数据");
            }
            model.addObject("list", list);
            model.addObject("stuId", stuId);
            model.setViewName("student/stu_ForFamEdit");
            return model;
        } else if (stuTable.equalsIgnoreCase("forStudentSupplementJump")) {// 正式学生补充表跳转
            String stuId = (String) request.getSession().getAttribute("stuId");
            BaiseeStudentSupplement sup = selectByStudentIdQuerySupplement(stuId);
            if (sup == null || sup.getStuId() == null || sup.getStuId() == "") {// 就是没有值
                model.addObject("sup", new BaiseeStudentSupplement(stuId));
            } else {
                model.addObject("sup", sup);
            }
            model.setViewName("student/stu_ForSupEdit");
            return model;
        } else if (stuTable.equalsIgnoreCase("forStudentEducationJump")) {// 正式学生教育跳转页面
            String stuId = (String) request.getSession().getAttribute("stuId");
            BaiseeStudentEducation education = selectByStudentIdQueryEducation(stuId);
            if (education == null || education.getStuId() == null || education.getStuId() == "") {
                model.addObject("edu", new BaiseeStudentEducation(stuId));
            } else {
                model.addObject("edu", education);
            }
            model.setViewName("student/stu_ForEduEdit");
            return model;
        }

        return model;
    }

    /********************************
     * 根据stuid进行查询，四张表
     **********************************************/
    @Override
    public BaiseeStudent selectByStudentId(String stuId) throws Exception {
        BaiseeStudent student = baiseeStuMapper.selectByStudentId(stuId);
        if (StringUtil.isNotEmpty(student.getStuEnrolmentTime()) && student.getStuEnrolmentTime().length() > 8) {
            String str = student.getStuEnrolmentTime().substring(0, 8);
            StringBuilder sbd = new StringBuilder(str);
            StringBuilder insert = sbd.insert(4, "-").insert(7, "-");
            String success = insert.toString();
            student.setStuEnrolmentTime(success);
        }
        return student;
    }

    @Override
    public List<BaiseeStudentFamily> selectByStudentIdQueryFamily(String stuId) throws Exception {
        return baiseeStuMapper.selectByStudentIdQueryFamily(stuId);
    }

    @Override
    public BaiseeStudentEducation selectByStudentIdQueryEducation(String stuId) throws Exception {
        return baiseeStuMapper.selectByStudentIdQueryEducation(stuId);
    }

    @Override
    public BaiseeStudentSupplement selectByStudentIdQuerySupplement(String stuId) throws Exception {
        BaiseeStudentSupplement Supplement = baiseeStuMapper.selectByStudentIdQuerySupplement(stuId);
        if (Supplement != null) {
            if (StringUtil.isNotEmpty(Supplement.getStuArea())) {
                Supplement.setStuAddress(StringUtil.ifNullToBlank(baiseeDictMapper.selectByProvinceCode(Supplement.getStuProvince())) + StringUtil.ifNullToBlank(baiseeDictMapper.selectByCityCode(Supplement.getStuCity())) + StringUtil.ifNullToBlank(baiseeDictMapper.selectByAreaCode(Supplement.getStuArea())));
            }
        }

        return Supplement;
    }

    /********************************
     * 执行修改
     **********************************************/
    @Override
    public void updateStudentById(BaiseeStudent student) throws Exception {
        baiseeStuMapper.updateStudentById(student);

    }

    @Override
    public void updateStudentSupById(BaiseeStudentSupplement supplement) throws Exception {
        baiseeStuMapper.updateStudentSupById(supplement);

    }

    @Override
    public void updateStudentEduById(BaiseeStudentEducation education) throws Exception {
        baiseeStuMapper.updateStudentEduById(education);

    }

    @Override
    public void updateStudentFamById(BaiseeStudentFamily family) throws Exception {

        baiseeStuMapper.updateStudentFamById(family);

    }

    /********************************
     * 批量删除
     **********************************************/
    @Override
    public void DeleteStudentAll(String[] ids) {
        baiseeStuMapper.deleteStudent(ids);
        baiseeStuMapper.deleteStudentEducation(ids);
        baiseeStuMapper.deleteStudentFamily(ids);
        baiseeStuMapper.deleteStudentSupplement(ids);
        stuMapper.deleteStuUsers(ids); /* 删除学生账户*/
    }

    @Override
    public List<PhoneAndID> readIDAndPhone() {

        return baiseeStuMapper.readPhoneAndID();
    }

    @Override
    public Map<String, Object> doService(InputStream inputStream) {
        BaiseeStudent student = null;
        BaiseeStudentSupplement stuSupplement = null;
        BaiseeStudentEducation stuEducation = null;
        BaiseeStudentFamily stuDad = null;
        BaiseeStudentFamily stuMam = null;
        BaiseeStudentFamily stuGuardian = null;
        String[] titles = {"序号", "姓名", "性别", "班级", "所学专业", "家庭住址", "身份证号", "学历", "本人电话", "父亲姓名", "父亲身份证号", "父亲电话", "母亲姓名", "母亲身份证号", "母亲电话", "其他监护人姓名", "其他监护人身份证号", "其他建监护人电话", "其他监护人与学生的关系", "正式入学时间", "备注"};// excel中的表头
        ExcelToBeanConvertor<BaseDto> convertor = new ExcelToBeanConvertor<>();
        // 1.读取excel数据至对象map集合中
        /*******************************************************************/
        Map<String, List<BaseDto>> map = null;
        try {
            map = convertor.readToBeanMap(inputStream, titles, BaseDto.class);
        } catch (InvalidExcelTemplateException e) { // 模板不正确则抛出异常
            throw new AppException("IICIPBMS00022");
        } catch (OAServiceException e) {
            e.printStackTrace();
        }

        if (MapUtils.isEmpty(map))
            return null;

        Map<String, Object> returnMap = new HashMap<String, Object>();
        // 总记录数
        int totalCount = 0;
        // 导入数据的校验结果集合
        List<RowValidateResultDto> rvrDtoList = new ArrayList<RowValidateResultDto>();
        // 循环每一个符合模板格式的sheet页
        for (Map.Entry<String, List<BaseDto>> entry : map.entrySet()) {
            List<BaseDto> list = entry.getValue();
            totalCount += list.size();
            /*******************************************************************/
            // 2.准备好过滤时需要使用到的数据，放入上下文中
            /*******************************************************************/
            ICIPContext context = new ICIPContext();
            /*******************************************************************/
            // 3.校验数据
            /*******************************************************************/
            AbstractFilterChain<BaseDto, RowValidateResultDto> filterChain = new BaseCheckFilterChain(context);
            List<RowValidateResultDto> errorMsgList = filterChain.doFilter(list);

            if (CollectionUtils.isNotEmpty(errorMsgList))
                rvrDtoList.addAll(errorMsgList); // 汇总经过过滤器链过滤后返回的校验结果

            /*******************************************************************/
            // 4.插入记录
            /*******************************************************************/
            if (CollectionUtils.isNotEmpty(list)) { // 如果拆分后的数据集合不为空      暂时忽略出生日期
                for (BaseDto baseDao : list) {
                    //添加主表
                    student = new BaiseeStudent(baseDao.getName(), baseDao.getSex(), baseDao.getClassNo(), baseDao.getCertNo(), baseDao.getMobile(), baseDao.getFormalAdmissionTime(), baseDao.getMajor(), baseDao.getRemarks());
                    baiseeStuMapper.addImportExcel(student);
                    //执行添加学号方法
                    baiseeStuMapper.updateAudToFor(student.getStuId(), baseDao.getClassNo(), baseDao.getFormalAdmissionTime());

                    //添加附加表
                    stuSupplement = new BaiseeStudentSupplement(student.getStuId(), baseDao.getAddress());
                    baiseeStuMapper.addStudentSupplement(stuSupplement);

                    //添加家庭表父亲信息
                    stuDad = new BaiseeStudentFamily(student.getStuId(), baseDao.getDadPhone(), "父亲", baseDao.getDadCertNo(), baseDao.getDadName(), "");
                    baiseeStuMapper.addStudentFamily(stuDad);

                    //添加家庭表母亲信息
                    stuMam = new BaiseeStudentFamily(student.getStuId(), baseDao.getMomPhone(), "母亲", baseDao.getMomCertNo(), baseDao.getMomName(), "");
                    baiseeStuMapper.addStudentFamily(stuMam);

                    //添加家庭表其他监护人信息
                    stuGuardian = new BaiseeStudentFamily(student.getStuId(), baseDao.getGuardianPhone(), "其他", baseDao.getGuardianCertNo(), baseDao.getGuardianName(), baseDao.getRelation());
                    baiseeStuMapper.addStudentFamily(stuGuardian);

                    //添加学生教育信息
                    stuEducation = new BaiseeStudentEducation(student.getStuId(), baseDao.getEducation());
                    baiseeStuMapper.addStudentEducation(stuEducation);


                }
                //baiseeStuMapper.addImportExcel(list);
            }
        }
        // 对校验结果集合进行排序，方便前端页面按照行号进行展示
        Collections.sort(rvrDtoList);
        returnMap.put("totalCount", totalCount);
        returnMap.put("rvrDtoList", rvrDtoList);
        returnMap.put("errorCount", rvrDtoList.size());
        try {
            rvrDtoList.get(0).getCvrDtoList().get(0).getErrorMsg();
        } catch (Exception e) {

        }
        returnMap.put("successCount", totalCount - rvrDtoList.size());
        return returnMap;
    }

    /**
     * 点击退费按钮
     */
    @Override
    public Object doRefund(String stuId, String tuId, String stuEnrolmentTime) {
        String date = stuEnrolmentTime.substring(0, 8);//格式化获取的日期，获取前八位
        RefundRule refundInfo = new RefundRule();
        int days = DateUtil.getDifferFromNowDay(date);//计算入学时间，得到天数
        Map<String, Object> map = new HashMap<String, Object>();
        //如果天数小于等于90（三个月）就进行退费，否则无法进行
        if (days > 90) {//无法退费
            map.put("flag", "cannot");
        } else {//进行退费
            refundInfo = baiseeStuMapper.selectFormulaInfoByTrid(tuId);//根据学费Id查询退费公式和失效时间
            if (StringUtils.isNotEmpty(refundInfo.getInvalidTime())) {
                if (0 >= DateUtil.getDifferFromNowDay(refundInfo.getInvalidTime().substring(0, 7))) {//截取失效时间前八位与当前时间判断，为负数说明当前时间小于失效时间，继续下面的方法，否则说明已失效直接return
                } else {//已失效
                    map.put("reid", refundInfo.getReId());
                    map.put("flag", "invalid");
                    return map;
                }
            }
            map.put("flag", "can");
            int month = 1;//查询入学月数
            try {
                month = DateUtil.getMonth(stuEnrolmentTime, DateUtil.getNow("yyyyMMdd"));
                month++;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String totalTuition = transactionService.getTotalMoney(stuId, "学费");//根据stuId查询该学员总交学费
            List<BaiseeSysParam> list = (List<BaiseeSysParam>) redisUtils.get(CacheKeyEnum.TUITION.getCacheKey());//缓存中抓取学费信息
            int fee = 0;//总学费
            int sundryFees = 0;//杂费
            int schoolSystem = 0;//学制
            BaiseeStudentRefund refund = baiseeTuitionMapper.getRefundById(tuId);
            for (BaiseeSysParam baiseeSysParam : list) {
                if (refund.getTrId().equals(baiseeSysParam.getSysParamName())) {//通过传过来的学费id在缓存学费参数中查找
                    fee = Integer.valueOf(baiseeSysParam.getSysParamValue());
                    sundryFees = Integer.valueOf(baiseeSysParam.getUpdateTime());
                    schoolSystem = Integer.valueOf(baiseeSysParam.getSysParamDesc());
                }
            }
            Map<String, Object> mymap = new HashMap<String, Object>();
            mymap.put("paidFee", Integer.valueOf(totalTuition));//已交学费
            mymap.put("fee", fee);//总学费
            mymap.put("sundryFees", sundryFees);//杂费
            mymap.put("schoolSystem", schoolSystem);//学制
            mymap.put("month", month);//上学月数
            Object a = "";
            try {
                a = jse.eval(VelocityParserUtil.getInstance().parseVelocityTemplate(refundInfo.getRefundFormula(), mymap));
            } catch (ScriptException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            map.put("paidFee", totalTuition);
            //四舍五入获取到的退费金额
            a = Math.round(Double.parseDouble(String.valueOf(a)));
            map.put("refund", a);
        }
        return map;
    }

    /**
     * 退费记录流水
     */
    @Override
    public ModelAndView doRefundFlow(Map<String, Object> map) {
        ModelAndView model = new ModelAndView();
        if (Integer.parseInt(String.valueOf(map.get("amount"))) > 0) {//判断获取到的退费金额是否大于0,大于0就修改学员状态
            baiseeStuMapper.updateStatus(String.valueOf(map.get("userID")), "7");//学生状态改为已退费
            Map<String, String> stuUpdateMap = new HashMap<String, String>();//修改学员交费状态为已终止
            stuUpdateMap.put("stuId", String.valueOf(map.get("userID")));
            stuUpdateMap.put("tuitionStatus", "2");
            baiseeStuMapper.updateStudentTuitionStatusById(stuUpdateMap);
        }
        transactionService.addReturnFee(map);//插入流水
        model.addObject("operationSuccess", "操作成功");
        return model;
    }

    @Override
    public void updateStudentTuitionStatusById(Map<String, String> map) {
        baiseeStuMapper.updateStudentTuitionStatusById(map);
    }

    @Override
    public Map<String, Object> importStudentResult(InputStream inputStream, HttpServletRequest request) {
        BaiseeStudent student = null;
        BaiseeStudentSupplement stuSupplement = null;
        BaiseeStudentEducation stuEducation = null;
        BaiseeStudentFamily stuDad = null;
        BaiseeStudentFamily stuMam = null;

        String[] titles = {"序号", "试听日期", "姓名", "性别", "学历", "出生日期", "身份证号", "学生电话", "父亲姓名", "父亲电话", "母亲姓名", "母亲电话", "区县", "家庭地址", "试听班级", "备注"};//excel中的表头
        ExcelToBeanConvertor<BaiseeAuditionStudent> convertor = new ExcelToBeanConvertor<>();
        // 1.读取excel数据至对象map集合中
        /*******************************************************************/
        Map<String, List<BaiseeAuditionStudent>> map = null;
        try {
            map = convertor.readToBeanMap(inputStream, titles,
                    BaiseeAuditionStudent.class);
        } catch (InvalidExcelTemplateException e) { // 模板不正确则抛出异常
            throw new AppException("IICIPBMS00022");
        } catch (OAServiceException e) {
            e.printStackTrace();
        }

        if (MapUtils.isEmpty(map))
            return null;

        Map<String, Object> returnMap = new HashMap<String, Object>();
        // 总记录数
        int totalCount = 0;
        // 导入数据的校验结果集合
        List<RowValidateResultDto> rvrDtoList = new ArrayList<RowValidateResultDto>();
        // 循环每一个符合模板格式的sheet页
        for (Map.Entry<String, List<BaiseeAuditionStudent>> entry : map.entrySet()) {
            List<BaiseeAuditionStudent> list = entry.getValue();
            totalCount += list.size();
            /*******************************************************************/
            // 2.准备好过滤时需要使用到的数据，放入上下文中
            /*******************************************************************/
            ICIPContext context = new ICIPContext();
            /*******************************************************************/
            // 3.校验数据
            /*******************************************************************/
            AbstractFilterChain<BaiseeAuditionStudent, RowValidateResultDto> filterChain = new AuditionStudentCheckFilterChain(
                    context);
            List<RowValidateResultDto> errorMsgList = filterChain
                    .doFilter(list);

            if (CollectionUtils.isNotEmpty(errorMsgList))
                rvrDtoList.addAll(errorMsgList); // 汇总经过过滤器链过滤后返回的校验结果

            /*******************************************************************/
            // 4.插入记录
            /*******************************************************************/
            if (CollectionUtils.isNotEmpty(list)) { // 如果拆分后的数据集合不为空
                for (BaiseeAuditionStudent baseDao : list) {
                    //添加主表
                    student = new BaiseeStudent(baseDao.getAuditionDates(), baseDao.getName(), baseDao.getGender(), baseDao.getBirthdate(), baseDao.getCertNo(), baseDao.getMobile(), baseDao.getClassNo(), baseDao.getRemarks(), null);
                    baiseeStuMapper.addAudImportExcel(student);


                    //添加完成后加入一笔试听费的流水
                    BaiseeUser user = (BaiseeUser) SessionUtil.getUserInfo(request);
                    String operator = user.getUserName();
                    Map<String, Object> mapp = new HashMap<String, Object>();
                    mapp.put("amount", "300");
                    mapp.put("operator", operator);
                    mapp.put("userID", student.getStuId());
                    mapp.put("remarks", "无");
                    mapp.put("type", "试听费");
                    mapp.put("status", "1");
                    transactionService.addReturnFee(mapp);
                    //添加附加表
                    stuSupplement = new BaiseeStudentSupplement(student.getStuId(), baseDao.getArea().substring(baseDao.getArea().length() - 6), baseDao.getAddress());
                    baiseeStuMapper.addStudentSupplement(stuSupplement);

                    //添加家庭表父亲信息
                    stuDad = new BaiseeStudentFamily(student.getStuId(), baseDao.getDadPhone(), "父亲", baseDao.getDadName());
                    baiseeStuMapper.addStudentFamily(stuDad);

                    //添加家庭表母亲信息
                    stuMam = new BaiseeStudentFamily(student.getStuId(), baseDao.getMomPhone(), "母亲", baseDao.getMomName());
                    baiseeStuMapper.addStudentFamily(stuMam);

                    //添加学生教育信息
                    stuEducation = new BaiseeStudentEducation(student.getStuId(), baseDao.getEducation());
                    baiseeStuMapper.addStudentEducation(stuEducation);
                }
            }
        }
        // 对校验结果集合进行排序，方便前端页面按照行号进行展示
        Collections.sort(rvrDtoList);
        returnMap.put("totalCount", totalCount);
        returnMap.put("rvrDtoList", rvrDtoList);
        returnMap.put("errorCount", rvrDtoList.size());
        try {
            rvrDtoList.get(0).getCvrDtoList().get(0).getErrorMsg();
        } catch (Exception e) {

        }

        returnMap.put("successCount", totalCount - rvrDtoList.size());
        return returnMap;

    }

    @Override
    public PageInfo<BaiseeStudent> selectForTuitionList(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
        Integer pageNum = ParamUtils.getPageNumParameters(request);
        Integer pageSize = ParamUtils.getPageSizeParameters(request);
        PageHelper.startPage(pageNum, pageSize);
        List<BaiseeStudent> list = baiseeStuMapper.selectForTuitionList(map);
        String stagesNumber = (String) map.get("stagesNumber");//获取客户要查询的期数
        /**========================开始设置未缴纳 ,本期缴纳费用=======================**/
        if (list != null) {
            for (BaiseeStudent student : list) {
                Integer tuSurplusTuition = 0;//总未缴纳学费的钱
                Integer totalMoney = 0;//到现在这个学生总共缴纳了多少钱
                if (!StringUtil.isEmpty(student.getTotalMoney()) && !StringUtil.isEmpty(student.getXfTotal())) {
                    totalMoney = Integer.parseInt(student.getTotalMoney());
                    tuSurplusTuition = (Integer.parseInt(student.getXfTotal()) - Integer.parseInt(student.getTotalMoney()));
                    student.setStuNotPayment(tuSurplusTuition.toString());//设置未缴纳费用
                }
                // 在这里计算一下本期剩余未缴纳费用 和 已缴纳费用
				/*思路：在这里已经搜索到了第几期，然后在计算这个同学正在缴纳的是第几期，如果是查询那个期数，在计算他现在欠本期多少钱？
				显示搜索的这期他欠多少钱？如果他现在正在缴纳期数正好是搜索的期数，那么   减去1期到搜索期数的金额的和  - 已缴纳费用，就是本期未缴纳费用，本期减去本期未缴纳费用就是本期已经缴纳费用
				如果他现在缴纳的不是搜索的期数，（在这里可理解为他现在没有缴纳到这期了）那么，他就是搜索的期数中的全部学费，那么本期中他已缴纳0元
				如果期数是空的，没有点击期数查询，那么就查询当前本期他已缴纳多少钱，未缴纳多少钱*/

                if (!StringUtil.isStrEmpty(stagesNumber)) {//如果这个查询期数不是空的，那么计算这个人的欠本期的钱金额
                    Integer stagesNumberInt = Integer.parseInt(stagesNumber);
                    Integer tuitioinPeriodsNowInt = Integer.parseInt(student.getTuitioinPeriodsNow());//这个学生正在缴纳的期数
                    if (stagesNumberInt > tuitioinPeriodsNowInt) {//搜索的期数大于这个学生的期数，那么就搜索查询的这个期数的全部金额值
                        //查询条件根据这个学生的分期主键，和查询的期数值
                        String TotalTutitonLessThanPeriodMoney = baiseeTuitionMapper.selectTotalTutitonCurrentPeriodByTuStId(student.getTuStId(), stagesNumber);
                        Integer stuNotCurrentPeriodInt = 0;
                        if (StringUtils.isNotEmpty(TotalTutitonLessThanPeriodMoney)) {
                            stuNotCurrentPeriodInt = Integer.parseInt(TotalTutitonLessThanPeriodMoney);
                        }
                        if (stuNotCurrentPeriodInt < 0) {
                            student.setStuNotCurrentPeriod("0");
                        } else {
                            student.setStuNotCurrentPeriod(TotalTutitonLessThanPeriodMoney);
                        }
                    } else {//否则期数则是一样的，就搜索他还欠本期多少钱，就不是本期的全部金额值
                        String TotalTutitonLessThanPeriodMoney = baiseeTuitionMapper.selectTotalTutitonLessThanPeriodByTuStId(student.getTuStId(), stagesNumber);
                        Integer stuNotCurrentPeriodInt = (Integer.parseInt(TotalTutitonLessThanPeriodMoney) - totalMoney);
                        if (stuNotCurrentPeriodInt < 0) {
                            stuNotCurrentPeriodInt = 0;
                        }
                        student.setStuNotCurrentPeriod(stuNotCurrentPeriodInt.toString());
                    }
                } else {//否则计算这个人现在缴纳到第几期了，还欠第几期的金额数
                    String TotalTutitonLessThanPeriodMoney = baiseeTuitionMapper.selectTotalTutitonLessThanPeriodByTuStId(student.getTuStId(), student.getTuitioinPeriodsNow());
                    Integer stuNotCurrentPeriodInt = (Integer.parseInt(TotalTutitonLessThanPeriodMoney) - totalMoney);
                    if (stuNotCurrentPeriodInt < 0) {
                        stuNotCurrentPeriodInt = 0;
                    }
                    student.setStuNotCurrentPeriod(stuNotCurrentPeriodInt.toString());
                }
            }
        }
        /**========================结束设置未缴纳费用=======================**/
        PageInfo<BaiseeStudent> pageInfo = new PageInfo<BaiseeStudent>(list);
        return pageInfo;
    }


    @Override
    public PageInfo<BaiseeStudent> selectStuAndClass(int pageNum, int pageSize, String itemlableSearch) {
        PageHelper.startPage(pageNum, pageSize);//开始分页
        Map<String, String> map = new HashMap<String, String>();
        map.put("itemlableSearch", itemlableSearch);//学生姓名或者学生id
        List<BaiseeStudent> list = baiseeStuMapper.selectStuAndClass(map);
        PageInfo<BaiseeStudent> pageInfo = new PageInfo<BaiseeStudent>(list);
        return pageInfo;
    }


    @Override
    public List<BaiseeStudent> selectStuAndClass(String cid) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("cid", cid);//学生姓名或者学生id
        List<BaiseeStudent> list = baiseeStuMapper.selectStuAndClass(map);
        return list;
    }

    @Override
    public BaiseeStudentRecord selectStuRecord(String stuId) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("stuId", stuId);//学生姓名或者学生id
        BaiseeStudentRecord record = baiseeStuMapper.selectStuRecord(map);
        return record;
    }

    @Override
    public BaiseeStudentRecord selectStuAssess(String stuId) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("stuId", stuId);
        BaiseeStudentRecord record = baiseeStuMapper.selectStuAssess(map);//根据学生id得到学生评定信息
        return record;
    }

    @Override
    public void applicantsToAudition(BaiseeStudent baiseeStudent, String audition, String operator, String prepay) {
        baiseeStuMapper.addStudent(baiseeStudent);//添加学生基本信息
        baiseeStudentApplicantsMapper.deleteStudentApplicantsByid(baiseeStudent.getStuId());//把预报名学生信息删掉（转到了试听学生信息）
        BaiseeStudentSupplement supplement = new BaiseeStudentSupplement();
        supplement.setStuId(baiseeStudent.getStuId());
        supplement.setStuHomeAddress(baiseeStudent.getStuHomeAddress());
        supplement.setStuProvince(baiseeStudent.getStuProvince());
        supplement.setStuCity(baiseeStudent.getStuCity());
        supplement.setStuArea(baiseeStudent.getStuArea());
        supplement.setStuRealProvince("");
        supplement.setStuRealCity("");
        supplement.setStuRealArea("");
        baiseeStuMapper.addStudentSupplement(supplement);//把预报名学生的地址转移到试听学生表
        if ("1".equals(prepay)) {
            //判断是否要用预缴费抵试听费
            String readPrepay = baiseeTransactionService.readPrepay(baiseeStudent.getStuId());//查看预缴费余额
            if (StringUtil.isEmpty(readPrepay)) {
                readPrepay = "0";
            }
            int i = Integer.parseInt(readPrepay);
            if (i < 300) {
                logger.debug("预交余额小于试听费不能抵充");
                return;
            }
            if ("1".equals(audition)) {//判断是否有试听费
                int j = i - 300;//预缴费减去试听费
                baiseeTransactionService.removePrepay(baiseeStudent.getStuId(), j);//修改预缴费余额
                //添加完成后加入一笔试听费的流水
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("amount", "0");
                map.put("operator", operator);
                map.put("userID", baiseeStudent.getStuId());
                map.put("remarks", "无");
                map.put("type", "试听费");
                map.put("status", "1");
                map.put("source", "预缴费");// 抵充来源
                map.put("balance", "" + j);// 抵充余额
                map.put("takeOut", "300");// 抵充金额，预缴金额
                transactionService.addReturnFee(map);
            }
        } else {
            if ("1".equals(audition)) {
                //添加完成后加入一笔试听费的流水
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("amount", "300");
                map.put("operator", operator);
                map.put("userID", baiseeStudent.getStuId());
                map.put("remarks", "无");
                map.put("type", "试听费");
                map.put("status", "1");
                transactionService.addReturnFee(map);
            }


        }
    }

    /**
     * 学生开户
     */
    @Override
    public int udateStuState(String sId, BaiseeUser user) {
        Map<String, Object> map = new HashMap<>();
        map.put("sId", sId);
        map.put("state", "1");
        int i = baiseeStuMapper.updateStuState(map);   /*在OA上更改学生的开户状态*/
        BaiseeStuUser stuUser = new BaiseeStuUser();  /*学生登录的model*/
        int s = 0;
        if (i > 0) {
            BaiseeStudent stu = baiseeStuMapper.selectByStudentId(sId);   /*根据学生ID查出要开户的学生信息*/
            if (stu != null) {
                stuUser.setUserId(stu.getStuId());         /*开户学生的ID*/
                stuUser.setClaId(stu.getClaId());             /*开户学生的班级ID*/
                stuUser.setUserName(stu.getStuName());     /*开户学生的姓名*/
                stuUser.setLoginName(stu.getStuIdNumber());  /*开户学生的身份证号 简称 登录账号*/
                stuUser.setLoginPwd(stu.getStuIdNumber());   /*开户学生的身份号 并且简称 登录密码*/
                stuUser.setFounder(user.getUserName());       /*创建人 就是说把这个学生开通的账户*/
            }
            s = stuMapper.insertStuUser(stuUser);    /*把开户的学生的账户信息添加到STU系统中*/
        }
        return s;
    }

    /**
     * 注销学生的账号
     */
    @Override
    public int deleteStuUser(String sId) {
        Map<String, Object> map = new HashMap<>();
        map.put("sId", sId);
        map.put("state", "0");
        int s = 0;
        int i = baiseeStuMapper.updateStuState(map);
        if (i > 0) {
            s = stuMapper.deleteStuUser(sId);
        }

        return s;
    }

    @Override
    public PageInfo<BaiseeStudent> getForList(int pageNum, int pageSize, String itemlableSearch, HttpServletRequest request) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("itemlableSearch", itemlableSearch);
        List<BaiseeStudent> list = baiseeStuMapper.selectStuForSel(map);
        PageInfo<BaiseeStudent> pageInfo = new PageInfo<BaiseeStudent>(list);
        return pageInfo;
    }

    @Override
    public String getUpdateTime(String stuId) {
        return baiseeStuMapper.selectUpdateTime(stuId);
    }

    @Override
    public int updatMerger(String[] strs, String claId, String U_NAME) {
        int flag = 0;
        for (String str : strs) {
            BaiseeStudent student = baiseeStuMapper.selectByStudentId(str);
            String oldCid = student.getClaId();
            BaiseeClassMerger baiseeClassMerger = new BaiseeClassMerger();
            baiseeClassMerger.setSID(str);
            baiseeClassMerger.setNEW_CID(claId);
            baiseeClassMerger.setU_NAME(U_NAME);
            baiseeClassMerger.setS_ID_NUMBER(student.getStuIdNumber());
            baiseeClassMerger.setNEW_TIME(new Date());
            baiseeClassMerger.setSTU_NAME(student.getStuName());
            if (student.getOldCid() == "" || student.getOldCid() == null) {
                baiseeClassMerger.setOLD_CID(student.getClaId());
                Map<String, String> map = new HashMap<>();
                map.put("str", str);
                map.put("claId", claId);
                map.put("oldCid", oldCid);
                baiseeStuMapper.updataMerger(map);
                flag += baiseeStuMapper.addMerger(baiseeClassMerger);
            } else {
                Map<String, String> map = new HashMap<>();
                map.put("str", str);
                map.put("claId", claId);
                baiseeClassMerger.setOLD_CID(student.getClaId());
                baiseeStuMapper.updateMerger1(map);
                flag += baiseeStuMapper.addMerger(baiseeClassMerger);
            }
        }
        System.err.println(flag);
        return flag;
    }
    /*
     *
     * 查询合班纪录
     * */

    @Override
    public PageInfo<BaiseeClassMerger> selectMerger(String stu_name, String old_cname, String new_cname, String stuStartTime, String stuEndTime, String u_name, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Map<String, String> map = new HashMap<>();
        map.put("stu_name", stu_name == null ? "" : stu_name);
        map.put("old_cname", old_cname == null ? "" : old_cname);
        map.put("new_cname", new_cname == null ? "" : new_cname);
        map.put("stuStartTime", stuStartTime == null ? "" : stuStartTime);
        map.put("stuEndTime", stuEndTime == null ? "" : stuEndTime);
        map.put("u_name", u_name == null ? "" : u_name);
        List<BaiseeClassMerger> list = baiseeStuMapper.selectMerger(map);
        PageInfo<BaiseeClassMerger> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

}