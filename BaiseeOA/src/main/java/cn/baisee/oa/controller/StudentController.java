package cn.baisee.oa.controller;

/**
 * 学生controller层
 */

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.constants.AppConstants;
import cn.baisee.oa.constants.CacheKeyEnum;
import cn.baisee.oa.core.file.FileDir;
import cn.baisee.oa.core.file.FileUploadUtil;
import cn.baisee.oa.model.BaiseeClazz;
import cn.baisee.oa.model.BaiseeSystemParam;
import cn.baisee.oa.model.BaiseeUser;
import cn.baisee.oa.model.DetailAccount;
import cn.baisee.oa.model.ResponseResult;
import cn.baisee.oa.model.student.BaiseeStudent;
import cn.baisee.oa.model.student.BaiseeStudentEducation;
import cn.baisee.oa.model.student.BaiseeStudentFamily;
import cn.baisee.oa.model.student.BaiseeStudentRecord;
import cn.baisee.oa.model.student.BaiseeStudentSupplement;
import cn.baisee.oa.model.tuition.TuitionDiscount;
import cn.baisee.oa.model.tuition.TuitionRule;
import cn.baisee.oa.model.tuition.TuitionStages;
import cn.baisee.oa.model.tuition.TuitionStagesRule;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeStudentApplicantsService;
import cn.baisee.oa.service.BaiseeStudentReturnService;
import cn.baisee.oa.service.BaiseeTransactionService;
import cn.baisee.oa.service.BaiseeTuitionService;
import cn.baisee.oa.service.ClazzService;
import cn.baisee.oa.service.StudentService;
import cn.baisee.oa.utils.ParamUtils;
import cn.baisee.oa.utils.PropertiesUtil;
import cn.baisee.oa.utils.RedisUtils;
import cn.baisee.oa.utils.SessionUtil;
import cn.baisee.oa.utils.StringUtil;
import cn.baisee.oa.utils.poi.WordUtils;

@Controller
@RequestMapping("/stu")
public class StudentController {

    private static final Log logger = LogFactory.getLog(ClazzController.class);
    @Autowired
    private StudentService studentService;
    // 缴纳学费service （LiFQ）
    @Autowired
    private BaiseeTuitionService baiseeTuitionService;
    // 班级service （LiFQ）
    @Autowired
    private ClazzService clazzService;
    @Resource(name = "redisUtils")
    private RedisUtils redisUtils;
    /**
     * 百思 交易 服务层
     */
    @Autowired
    private BaiseeTransactionService baiseeTransactionService;

    @Autowired
    private BaiseeStudentReturnService studentReturnService;
    @Autowired
    private BaiseeStudentApplicantsService baiseeStudentApplicantsService;

    /**
     * 试听学生 查询所有学生后调转到首页显示
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/toAudStuList")
    @Role("STXYGL_XYCK")
    public ModelAndView toAudStuList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer pageNum = ParamUtils.getPageNumParameters(request);
        Integer pageSize = ParamUtils.getPageSizeParameters(request);
        String itemlableSearch = "";
        String status = "";
        String startTime = "";
        String endTime = "";
        String str = ParamUtils.getParameter(request, "itemlableSearch");
        String sta = ParamUtils.getParameter(request, "status");
        String stuStartTime = ParamUtils.getParameter(request, "stuStartTime");
        String stuEndTime = ParamUtils.getParameter(request, "stuEndTime");
        String claStatus = ParamUtils.getParameter(request, "claStatus");
        String areas = ParamUtils.getParameter(request, "areas");
        if (str != null) {
            itemlableSearch = str;
        }
        if (sta != null) {
            status = sta;
        }
        if (stuStartTime != null) {
            startTime = stuStartTime.replace("-", "");
        }
        if (stuEndTime != null) {
            endTime = stuEndTime.replace("-", "");
        }
        PageInfo<BaiseeStudent> pageInfo = studentService.getAudList(pageNum, pageSize, itemlableSearch, status,
                startTime, endTime, request, claStatus, areas);
        ModelAndView mv = new ModelAndView("student/stu_AudStuList");
        if (pageInfo.getTotal() == 0) {
            mv.addObject("operationSuccess", "暂无数据");
        }
        mv.addObject("areas", areas);
        mv.addObject("pageResult", pageInfo);
        mv.addObject("itemlableSearch", itemlableSearch);
        mv.addObject("status", status);
        mv.addObject("stuStartTime", stuStartTime);
        mv.addObject("stuEndTime", stuEndTime);
        mv.addObject("claStatus", claStatus);
        mv.addObject("userType", SessionUtil.getUserInfo(request).getUserType());
        return mv;
    }

    /**
     * 正式学生 查询所有学生后调转到首页显示
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/toForStuList")
    @Role("ZSXYGL_XYCK")
    public ModelAndView toForStuList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer pageNum = ParamUtils.getPageNumParameters(request);
        Integer pageSize = ParamUtils.getPageSizeParameters(request);
        /* 学生状态 */
        String choiceStuState = ParamUtils.getParameter(request, "choiceStuState");
        //开始时间
        String stuStartTime = ParamUtils.getParameter(request, "stuStartTime");
        //结束时间
        String stuEndTime = ParamUtils.getParameter(request, "stuEndTime");
        //交易状态
        String feeStatus = ParamUtils.getParameter(request, "returnFeeStatus");
        //学籍
        String claStatus = ParamUtils.getParameter(request, "claStatus");
        //家庭区域
        String areas = ParamUtils.getParameter(request, "areas");
        //姓名手机号班级 搜索
        String str = ParamUtils.getParameter(request, "itemlableSearch");
        //学生状态
        String sta = ParamUtils.getParameter(request, "status");
        String itemlableSearch = "";
        String returnFeeStatus = "";
        String startTime = "";
        String endTime = "";
        String status = "";

        if (str != null) {
            itemlableSearch = str;
        }
        if (sta != null) {
            status = sta;
        }
        if (stuStartTime != null) {
            startTime = stuStartTime.replace("-", "");
        }
        if (stuEndTime != null) {
            endTime = stuEndTime.replace("-", "");
        }
        if (feeStatus != null) {
            returnFeeStatus = feeStatus;
        }

        PageInfo<BaiseeStudent> pageInfo = studentService.getForList(
                pageNum, pageSize, itemlableSearch, status,
                startTime, endTime, returnFeeStatus, request,
                claStatus, areas, choiceStuState);

        ModelAndView mv = new ModelAndView("student/stu_ForStuList");
        if (pageInfo.getTotal() == 0) {
            mv.addObject("operationSuccess", "暂无数据");
        }
        mv.addObject("areas", areas);
        mv.addObject("pageResult", pageInfo);
        mv.addObject("itemlableSearch", itemlableSearch);
        mv.addObject("status", status);
        mv.addObject("stuStartTime", stuStartTime);
        mv.addObject("stuEndTime", stuEndTime);
        mv.addObject("returnStatus", returnFeeStatus);
        mv.addObject("claStatus", claStatus);
        mv.addObject("userType", SessionUtil.getUserInfo(request).getUserType());
        return mv;
    }

    /**
     * 修改试听学生状态
     */
    @RequestMapping("/updateAudStatus")
    @ResponseBody
    @Role("STXYGL")
    public Object updateAudStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String stuId = request.getParameter("stuId");
        String addParam = request.getParameter("addParam");
        String operationStart = request.getParameter("OPERATION_START");
        String operationEnd = request.getParameter("OPERATION_END");
        BaiseeUser user = (BaiseeUser) SessionUtil.getUserInfo(request);
        String operator = user.getUserId();
        Map<String, Object> map = new HashMap<String, Object>();
        int a = studentService.updateStatus(stuId, addParam, operator, operationStart, operationEnd);
        if (a > 0) {
            map.put("flag", "success");
        } else {
            map.put("flag", "error");
        }
        return map;
    }

    /**
     * 修改正式学生状态
     */
    @RequestMapping("/updateForStatus")
    @ResponseBody
    @Role("ZSXYGL")
    public Object updateForStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String stuId = request.getParameter("stuId");
        String addParam = request.getParameter("addParam");
        String operationStart = request.getParameter("OPERATION_START");
        String operationEnd = request.getParameter("OPERATION_END");
        BaiseeUser user = (BaiseeUser) SessionUtil.getUserInfo(request);
        String operator = user.getUserId();
        Map<String, Object> map = new HashMap<String, Object>();
        int a = studentService.updateStatus(stuId, addParam, operator, operationStart, operationEnd);
        if (a > 0) {
            map.put("flag", "success");
        } else {
            map.put("flag", "error");
        }
        return map;
    }

    /**
     * 学生员工身份证信息验证
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/VerificationStuID", method = RequestMethod.POST)
    @ResponseBody
    @Role(ignore = true)
    public Object studentIDVerification(HttpServletRequest request) throws Exception {
        String IdNumber = ParamUtils.getParameter(request, "stuIdNumber");
        String stuId = ParamUtils.getParameter(request, "stuId");
        String stuID = studentService.selectStudentIDNumberVerification(IdNumber, stuId);
        String empID = studentService.selectEmployeeIDNumberVerification(IdNumber);
        if (stuID != null && stuID.length() > 0) {
            return false;
        } else if (empID != null && empID.length() > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 预报名学生身份证验证是否存在
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/stuIDVerification", method = RequestMethod.POST)
    @ResponseBody
    @Role(ignore = true)
    public Object stuIDVerification(HttpServletRequest request) throws Exception {
        String IdNumber = ParamUtils.getParameter(request, "stuIdNumber");
        String stuID = studentService.selectStuIDNumberVerification(IdNumber);
        String empID = studentService.selectEmployeeIDNumberVerification(IdNumber);
        String idNumberVerification = baiseeStudentApplicantsService.getIDNumberVerification(IdNumber);
        if (stuID != null && stuID.length() > 0) {
            return false;
        } else if (empID != null && empID.length() > 0) {
            return false;
        } else if (idNumberVerification != null && idNumberVerification.length() > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 学生手机信息验证
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/VerificationStuMobile", method = RequestMethod.POST)
    @ResponseBody
    @Role(ignore = true)
    public Object studentMobileVerification(HttpServletRequest request) throws Exception {
        String Mobile = ParamUtils.getParameter(request, "stuMobile");
        String stuId = ParamUtils.getParameter(request, "stuId");
        String stuMobile = studentService.selectStudentMobileVerification(Mobile, stuId);
        String empMobile = studentService.selectEmployeeMobileVerification(Mobile);
        if (stuMobile != null && stuMobile.length() > 0) {
            return false;
        } else if (empMobile != null && empMobile.length() > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 试听生添加页面
     *
     * @return
     */
    @RequestMapping("/toAddAudStu")
    @Role(value = "STXYGL_XYXZ")
    public ModelAndView toAddAudStu() throws Exception {
        List<BaiseeClazz> list = studentService.selectClass("0");
        ModelAndView mv = new ModelAndView("student/stu_AudStuEdit");
        mv.addObject("list", list);
        mv.addObject("stu", new BaiseeStudent());
        return mv;
    }

    /**
     * 去试听生修改页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/AudStudentUpdate")
    @Role("STXYGL_XYCK")
    public ModelAndView toAudStuUpdate(HttpServletRequest request) throws Exception {
        ModelAndView model = new ModelAndView();
        String stuId = ParamUtils.getParameter(request, "stuId");
        request.getSession().setAttribute("stuId", stuId);
        BaiseeStudent student = studentService.selectByStudentId(stuId);
        List<BaiseeClazz> list = studentService.selectClass("0");
        String updateTime = studentService.getUpdateTime(stuId);
        model.addObject("updateTime", updateTime);
        model.addObject("list", list);
        model.addObject("stu", student);
        model.setViewName("student/stu_AudStuEdit");
        return model;
    }

    /**
     * 去正式生修改页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/ForStudentUpdate")
    @Role("ZSXYGL_XYCK")
    public ModelAndView toForStuUpdate(HttpServletRequest request) throws Exception {
        ModelAndView model = new ModelAndView();
        String stuId = ParamUtils.getParameter(request, "stuId");
        request.getSession().setAttribute("stuId", stuId);
        BaiseeStudent student = studentService.selectByStudentId(stuId);
        List<BaiseeClazz> list = studentService.selectClass("1");
        String updateTime = studentService.getUpdateTime(stuId);
        model.addObject("updateTime", updateTime);
        model.addObject("list", list);
        model.addObject("stu", student);
        model.setViewName("student/stu_ForStuEdit");
        return model;
    }

    /**
     * 去学员交费页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/AudToFor")
    @Role("STXYGL_XYJF")
    public ModelAndView AudToFor(HttpServletRequest request) throws Exception {
    	ModelAndView model = new ModelAndView();
        String stuId = ParamUtils.getParameter(request, "stuId");
        BaiseeStudent student = studentService.selectByStudentId(stuId);
        List<BaiseeClazz> list = studentService.selectClass("1");
        model.addObject("list", list);
        model.addObject("stu", student);
        model.setViewName("student/stu_AudToFor");
        return model;
    }

    /**
     * 前往 缴纳学费页面 特殊情况
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/AudToForSpecial")
    @Role("STXYGL_XYJF")
    public ModelAndView AudToForSpecial(HttpServletRequest request) throws Exception {
        ModelAndView model = new ModelAndView();
        String stuId = ParamUtils.getParameter(request, "stuId");
        BaiseeStudent student = studentService.selectByStudentId(stuId);
        List<BaiseeClazz> list = studentService.selectClass("1");

        model.addObject("list", list);
        model.addObject("stu", student);
        model.setViewName("student/stu_AudToForSpecial");
        return model;
    }

    /**
     * 执行交费，修改班级，缴纳学费，记录流水
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateAudToFor")
    @Role("ZSXYGL")
    public ModelAndView updateAudToFor(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	System.err.println("111111111111111111111111");
        String stuId = ParamUtils.getParameter(request, "stuId").trim(); // 缴费学生的id
        String claId = ParamUtils.getParameter(request, "claId").trim(); // 缴费的班级
        String StagesTuitionNow = ParamUtils.getParameter(request, "StagesTuitionNow").trim();// 获取分期首次缴纳实际（输入框中）费用

        String MinimumTuitionInput = ParamUtils.getParameter(request, "MinimumTuitionInput").trim();// 分期后，第一次最低缴纳学费金额值

        String NowPayTuitionInput = ParamUtils.getParameter(request, "NowPayTuitionInput").trim();// 一次性，减去优惠
        // 和抵充后最低需缴纳金额

        String studentTuition = ParamUtils.getParameter(request, "studentTuition").trim();// 学费类型的主键

        String studentTuitionType = ParamUtils.getParameter(request, "studentTuitionType").trim();// 缴纳学费类型一次性还是分期（1：一次性，0分期）

        String TotalTuition = ParamUtils.getParameter(request, "TotalTuition").trim();// 学费的费用，没有优惠之前，总学费

        String PrepaymentTuition = ParamUtils.getParameter(request, "PrepaymentTuition").trim();// 这个学生的预缴学费

        String AuditionTuition = ParamUtils.getParameter(request, "AuditionTuition");// 获取试听费用
        if (StringUtil.isBlank(AuditionTuition)) {
            AuditionTuition = "0";
        }

        String PayTuitionspecial = ParamUtils.getParameter(request, "PayTuitionspecial");// 获取特殊情况

        String remarks = ParamUtils.getParameter(request, "remarks");// 获取备注
        // 获取当前登录的用户
        BaiseeUser user = (BaiseeUser) SessionUtil.getUserInfo(request);
        String operator = user.getUserId();
        if (studentTuitionType != null && studentTuitionType.equals("1")) {
            // 表示是一次性
            String TuitionFees = ParamUtils.getParameter(request, "TuitionFees");// 获取优惠的主键
            Map<String, String> mapData = new HashMap<String, String>();
            mapData.put("TuDiId", TuitionFees);
            TuitionDiscount tuitionDiscount = baiseeTuitionService.selectTuitionDiscountDetailedById(mapData);  //   这里开始报错
            Map<String, Object> map = new HashMap<String, Object>();
            // 总学费减去优惠金额 = 实际缴纳 = totalMoney
            Integer actualPayment = 0;
            Integer amountMoney = 0;
            try {
                actualPayment = Integer.parseInt(TotalTuition) - Integer.parseInt(AuditionTuition)
                        - Integer.parseInt(PrepaymentTuition) - Integer.parseInt(tuitionDiscount.getTuDiReduce());
                amountMoney = actualPayment + Integer.parseInt(tuitionDiscount.getTuDiReduce());
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("stuId", stuId);
                request.setAttribute("ErrorPrompt", "金额无效，请重新输入！");
                logger.debug("一次性缴纳：金额无效，请重新输入！");
                return AudToFor(request);
            }
            map.put("totalMoney", actualPayment);// 实际缴纳，总学费（总学费减去优惠减去试听减去预交）
            map.put("discountAmount", tuitionDiscount.getTuDiReduce());// 优惠金额
            map.put("operator", operator);// 操作人
            map.put("userID", stuId);// 用户id
            map.put("type", "学费");// 缴费类型
            if (remarks == null || remarks.equals("") || remarks == "") {
                remarks = "无";
            }
            map.put("remarks", remarks);// 备注
            map.put("discountsType", tuitionDiscount.getTuDiThing());// 优惠类型
            map.put("source", "预缴费");// 抵充来源
            map.put("status", "1");// 缴费状态
            if (Integer.parseInt(NowPayTuitionInput) < 0) {
                // 如果预缴金额大于并且超过了总学费优惠试听费用后的金额，需要修改学生的预缴信息，不能删除
                Integer NowPayTuitionInputInt = Math.abs(Integer.parseInt(NowPayTuitionInput));// 最终修改为学生预缴表的信息
                // 算一下最终使用预缴表里多少钱 总学费减去优惠，再减去 试听
                Integer totalTuitionInt = Integer.parseInt(TotalTuition);// 总学费
                Integer tuDiReduceInt = Integer.parseInt(tuitionDiscount.getTuDiReduce());// 优惠金额
                Integer AuditionTuitionInt = Integer.parseInt(AuditionTuition);// 试听费用
                Integer takeOut = (totalTuitionInt - tuDiReduceInt - AuditionTuitionInt);
                map.put("takeOut", takeOut.toString());// 抵充金额，预缴金额,使用了预缴表里面多少钱
                map.put("amount", "0");// 单笔金额
                map.put("balance", NowPayTuitionInputInt.toString());// 抵充余额
                baiseeTransactionService.removePrepay(stuId, NowPayTuitionInputInt);// 修改此学生的预缴的信息
            } else {
                map.put("takeOut", PrepaymentTuition);// 抵充金额，预缴金额
                map.put("amount", amountMoney.toString());// 单笔金额
                map.put("balance", "0");// 抵充余额
                baiseeTransactionService.removePrepay(stuId, 0);// 删除此学生的预缴的信息
            }
            baiseeTransactionService.addFullPayment(map);
            // 记录修改学生的缴费状态 ，缴费状态变成缴费成功，在学生表中添加学生的学费主键
            Map<String, String> stuUpdateMap = new HashMap<String, String>();
            stuUpdateMap.put("stuId", stuId);
            stuUpdateMap.put("tuitionStatus", "1");// 缴纳学费状态本次缴清
            stuUpdateMap.put("tuId", studentTuition);// 学费类型的主键
            stuUpdateMap.put("tuitioinPeriodsNow", "0");// 现在正在缴纳的是第几期学费
            studentService.updateStudentTuitionStatusById(stuUpdateMap);// 修改学生的缴费状态
            request.setAttribute("operationSuccess", "操作成功，本次缴纳学费为："
                    + (Integer.parseInt(NowPayTuitionInput) <= 0 ? 0 : NowPayTuitionInput) + "元     " + "学费已缴纳完毕");
        } else if (studentTuitionType != null && studentTuitionType.equals("0")) {
            // 表示是分期缴纳
            /*************************************
             * 查询这个学费首期最低需缴纳金额
             ***************************************************/
            String tuitionDiscountByStages = ParamUtils.getParameter(request, "tuitionDiscountByStages");// 获取分期优惠的主键
            Map<String, String> mapDataDiscount = new HashMap<String, String>();
            mapDataDiscount.put("TuDiId", tuitionDiscountByStages);
            TuitionDiscount tuitionDiscount = baiseeTuitionService.selectTuitionDiscountDetailedById(mapDataDiscount);

            String tuStId = ParamUtils.getParameter(request, "StagingType");// 获取分期规则表的主键 TUSTID
            Map<String, String> mapData = new HashMap<String, String>();
            mapData.put("tuStId", tuStId);
            List<TuitionStagesRule> stagesRules = baiseeTuitionService.selectTuitionStagesDetailedById(mapData);// 获取分期的详细费用
            String MinimumTuition = "";// 首次最低金额
            if (stagesRules != null && stagesRules.size() > 0) {
                MinimumTuition = stagesRules.get(0).getTuStRuleMinimumTuition().trim();
            }
            // 在最少缴纳金额小于等于0的时候处理流水 表示 总学费减去预缴费用 减去优惠 小于等于零 就是预交费大于第一次首次分期金额了
            if (Integer.parseInt(MinimumTuitionInput) <= 0) {
                return paymentGreaterStages(request, response, studentTuition, MinimumTuition, MinimumTuitionInput,
                        PrepaymentTuition, StagesTuitionNow, stuId, claId, operator, TotalTuition, tuitionDiscount);
            }
            // 后台校验金额是否输入有误
            ModelAndView model = validatePayNumber(request, StagesTuitionNow, TotalTuition, PrepaymentTuition, stuId,
                    MinimumTuitionInput, PayTuitionspecial);
            if (model != null) {
                return model;
            }
            Integer tuitionFirst = (Integer.parseInt(PrepaymentTuition) + Integer.parseInt(StagesTuitionNow));// 预缴 +
            // 首次缴纳+优惠金额
            // 费用
            Integer AuditionTuitionInt = Integer.parseInt(AuditionTuition);// 试听费用
            Map<String, String> stuUpdateMap = new HashMap<String, String>();// 修改学生的缴费状态以及分期期数
            stuUpdateMap.put("stuId", stuId);// 学生主键
            stuUpdateMap.put("tuStId", tuStId);// 学生的分期主键
            stuUpdateMap.put("tuId", studentTuition);// 学费类型的主键
            Map<String, Object> map = new HashMap<String, Object>();// 添加流水map
            map.put("totalMoney", StagesTuitionNow);// 实际缴纳，在框中输入的值
            Integer amountMoney = Integer.parseInt(StagesTuitionNow)
                    + Integer.parseInt(tuitionDiscount.getTuDiReduce());
            map.put("amount", amountMoney.toString());// 分期首次缴纳单笔金额
            map.put("takeOut", PrepaymentTuition);// 抵充金额，预缴金额
            map.put("discountAmount", tuitionDiscount.getTuDiReduce());// 优惠金额
            map.put("operator", operator);// 操作人
            map.put("userID", stuId);// 用户id
            map.put("type", "学费");// 缴费类型
            if (remarks == null || remarks.equals("") || remarks == "") {
                remarks = "无";
            }
            map.put("remarks", remarks);// 备注
            map.put("discountsType", tuitionDiscount.getTuDiThing());// 优惠类型
            map.put("source", "预缴费");// 抵充来源
            map.put("balance", "0");// 抵充余额
            map.put("status", "1");// 缴费状态
            if (tuitionFirst + Integer.parseInt(tuitionDiscount.getTuDiReduce()) >= Integer.parseInt(TotalTuition)) {// 如果这次加纳得
                stuUpdateMap.put("tuitioinPeriodsNow", "0");// 已经缴纳清楚学费
                stuUpdateMap.put("tuitionStatus", "1");// 表示分期缴纳时，缴纳的学费大于等于总学费了
            } else {
                stuUpdateMap.put("tuitionStatus", "0");// 缴纳学费状态没缴清
                int i = 1;// 根据学生的分期主键获取这个人的分期表里面的学费规则，进行每次判断，看着学生现在缴纳到第几期了？
                Integer tuStRuleHighestTuition = Integer.parseInt(stagesRules.get(0).getTuStRuleHighestTuition());// 周期每期和
                for (TuitionStagesRule tuitionStagesRule : stagesRules) {
                    // 判断每期学费和缴纳的学费，如果是大于0 并且 小于本次循环的期数价格则是当前循环的次数，则将学生的正在缴纳期数改为这个数
                    if ((tuitionFirst + AuditionTuitionInt) < (tuStRuleHighestTuition)) {
                        stuUpdateMap.put("tuitioinPeriodsNow", String.valueOf(i));// 已经缴纳清楚学费
                        break;
                    } else {
                        tuStRuleHighestTuition += Integer.parseInt(tuitionStagesRule.getTuStRuleHighestTuition());
                    }
                    i++;
                }
            }

            studentService.updateStudentTuitionStatusById(stuUpdateMap);// 修改学生的缴费状态
            baiseeTransactionService.addFullPayment(map);// 记录流水，添加流水
            request.setAttribute("operationSuccess",
                    "操作成功，本次缴纳学费为：" + StagesTuitionNow + "元     " + "预缴学费为：" + PrepaymentTuition + "元");
            baiseeTransactionService.removePrepay(stuId, 0);// 删除此学生的预缴的信息
        }
        studentService.updateAudToFor(stuId, claId, null);// 修改学生班级
        studentService.udateStuState(stuId, user);
        return toForStuList(request, response);

    }

    /**
     * 试听执行添加或修改
     *
     * @param stuHeadPhotoDir
     * @param student
     * @param supplement
     * @param education
     * @param family
     * @param request
     * @return
     * @throws Exception
     * @throws IOException
     */
    @RequestMapping("/toSaveOrUpdateAudStu")
    @Role("STXYGL")
    public synchronized ModelAndView toSaveOrUpdateStu(MultipartFile stuHeadPhotoDir, BaiseeStudent student,
                                                       BaiseeStudentSupplement supplement,
                                                       BaiseeStudentEducation education,
                                                       BaiseeStudentFamily family,
                                                       HttpServletRequest request) throws Exception {
        return studentService.toSaveOrUpdateStu(stuHeadPhotoDir, student, supplement, education, family, request);
    }

    /**
     * 正式执行修改
     *
     * @param
     * @param student
     * @param supplement
     * @param education
     * @param family
     * @param request
     * @return
     * @throws Exception
     * @throws IOException
     */
    @RequestMapping("/toUpdateForStu")
    @Role("ZSXYGL")
    public ModelAndView toSaveOrUpdateForStu(MultipartFile input_file, BaiseeStudent student,
                                             BaiseeStudentSupplement supplement, BaiseeStudentEducation education, BaiseeStudentFamily family,
                                             HttpServletRequest request) throws Exception {
        return studentService.toSaveOrUpdateStu(input_file, student, supplement, education, family, request);
    }

    /**
     * 添加修改页面间进行跳转
     *
     * @param request
     * @return
     */
    @RequestMapping("/stuPageJump")
    @Role(ignore = true)
    public ModelAndView stuPageJump(HttpServletRequest request) throws Exception {
        return studentService.getStuPageJump(request);
    }

    /**
     * 查看学生信息基础信息
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/notstuJump")
    @Role(ignore = true)
    public ModelAndView studentPageJump(HttpServletRequest request, String stuId) throws Exception {
        BaiseeStudent student = studentService.selectByStudentId(stuId);
        List<BaiseeClazz> list = studentService.selectClass("");
        ModelAndView mv = new ModelAndView("student/student_ForStuEdit");
        mv.addObject("list", list);
        mv.addObject("stu", student);
        return mv;

    }

    /**
     * 查看学生附加信息
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/notSupJump")
    @Role(ignore = true)
    public ModelAndView notSupJump(HttpServletRequest request, String stuId) throws Exception {
        BaiseeStudentSupplement studentSupplement = studentService.selectByStudentIdQuerySupplement(stuId);
        ModelAndView mv = new ModelAndView("student/ student_ForSupEdit");
        if (studentSupplement == null || studentSupplement.getStuId() == null || studentSupplement.getStuId() == "") {// 就是没有值
            mv.addObject("studentSupplement", new BaiseeStudentSupplement(stuId));
            mv.addObject("stuId", stuId);
        } else {
            mv.addObject("studentSupplement", studentSupplement);
            mv.addObject("stuId", stuId);
        }
        return mv;

    }

    /**
     * 查看学生家庭信息
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/notFamJump")
    @Role(ignore = true)
    public ModelAndView notFamJump(HttpServletRequest request, String stuId) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<BaiseeStudentFamily> list = studentService.selectByStudentIdQueryFamily(stuId);
        if (list.size() == 0) {
            mv.addObject("operationSuccess", "暂无数据");
        }
        mv.addObject("list", list);
        mv.addObject("stuId", stuId);

        mv.setViewName("student/ student_ForFamEdit");
        return mv;

    }

    /**
     * 查看学生教育信息
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/notEduJump")
    @Role(ignore = true)
    public ModelAndView notEduJump(HttpServletRequest request, String stuId) throws Exception {
        ModelAndView mv = new ModelAndView();
        BaiseeStudentEducation education = studentService.selectByStudentIdQueryEducation(stuId);
        if (education == null || education.getStuId() == null || education.getStuId() == "") {
            mv.addObject("edu", new BaiseeStudentEducation(stuId));
            mv.addObject("stuId", stuId);
        } else {
            mv.addObject("edu", education);
            mv.addObject("stuId", stuId);
        }
        mv.setViewName("student/student_ForEduEdit");
        return mv;

    }

    /**
     * 删除试听生方法
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/deleteCheckAudStu")
    @Role("STXYGL_XYSC")
    public ModelAndView deleteCheckAudStu(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ids[] = ParamUtils.getParameters(request, "ids");
        studentService.DeleteStudentAll(ids);
        return toAudStuList(request, response);
    }

    /**
     * 删除正式生方法
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/deleteCheckForStu")
    @Role("ZSXYGL_XYSC")
    public ModelAndView deleteCheckForStu(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ids[] = ParamUtils.getParameters(request, "ids");
        studentService.DeleteStudentAll(ids);
        return toForStuList(request, response);
    }

    /**
     * 试听学生缴费 查询某一个总学费
     *
     * @param request
     */
    @RequestMapping(value = "/getTotalTuition", method = RequestMethod.POST)
    @ResponseBody
    @Role(ignore = true)
    public Map<Object, Object> getTotalTuition(HttpServletRequest request) {
        String stuId = ParamUtils.getParameter(request, "SID");
        // 学生一次性的学费类型，获取高中还初中
        String studentTuition = ParamUtils.getParameter(request, "studentTuition");
        // 获取这个学生的预缴费
        String Prepayment = baiseeTransactionService.readPrepay(stuId);
        if (Prepayment == null || Prepayment == "") {
            Prepayment = "0";
        }
        String AuditionTuitionMoney = baiseeTransactionService.getPaymentAmount(stuId, "试听费");// 获取试听费用
        Map<String, String> mapData = new HashMap<String, String>();
        mapData.put("tuId", studentTuition);
        TuitionRule tuitionRule = baiseeTuitionService.selectTuitionRuleById(mapData);
        List<TuitionDiscount> listDiscounts = baiseeTuitionService.selectTuitionDiscountById(mapData);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("tuitionRule", tuitionRule);// 放进去学费规则
        map.put("listDiscounts", listDiscounts);// 放进去学费优惠
        if (StringUtil.isEmpty(AuditionTuitionMoney)) {// 在这里需要放进去一个试听费用金额
            map.put("AuditionTuitionMoney", "0");// 将试听费用传到前台页面
        } else {
            map.put("AuditionTuitionMoney", AuditionTuitionMoney);// 将试听费用传到前台页面
        }

        map.put("Prepayment", Prepayment);// 放进去预交费用
        return map;
    }

    /**
     * 查询某个优惠条件的详细信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getTuitionDiscountById", method = RequestMethod.POST)
    @ResponseBody
    @Role(ignore = true)
    public Map<Object, Object> getTuitionDiscountById(HttpServletRequest request) {
        String TuitionFees = ParamUtils.getParameter(request, "TuitionFees");// 获取某个学费优惠条件的，优惠表的主键
        Map<String, String> mapData = new HashMap<String, String>();
        mapData.put("TuDiId", TuitionFees);
        TuitionDiscount tuitionDiscount = baiseeTuitionService.selectTuitionDiscountDetailedById(mapData);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("tuitionDiscount", tuitionDiscount);
        return map;
    }

    /**
     * 查询某个优惠条件的详细信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getTuitionStagesDiscountById", method = RequestMethod.POST)
    @ResponseBody
    @Role(ignore = true)
    public Map<Object, Object> getTuitionStagesDiscountById(HttpServletRequest request) {
        String tuitionDiscountByStages = ParamUtils.getParameter(request, "tuitionDiscountByStages");// 获取某个学费优惠条件的，优惠表的主键
        Map<String, String> mapData = new HashMap<String, String>();
        mapData.put("TuDiId", tuitionDiscountByStages);
        TuitionDiscount tuitionDiscount = baiseeTuitionService.selectTuitionDiscountDetailedById(mapData);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("tuitionDiscount", tuitionDiscount);
        return map;
    }

    /**
     * 试听生 获取学费都有哪些分期类型，以及总学费和预缴费用
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getTotalTuitionStages", method = RequestMethod.POST)
    @ResponseBody
    @Role(ignore = true)
    public Map<Object, Object> getTotalTuitionStages(HttpServletRequest request) {
        String stuId = ParamUtils.getParameter(request, "SID");
        String studentTuition = ParamUtils.getParameter(request, "studentTuition");// 学生一次性的学费类型，获取高中还初中
        String Prepayment = baiseeTransactionService.readPrepay(stuId);// 查询预缴费用
        if (Prepayment == null || Prepayment == "") {
            Prepayment = "0";
        }
        String AuditionTuitionMoney = baiseeTransactionService.getPaymentAmount(stuId, "试听费");// 获取试听费用
        Map<String, String> mapData = new HashMap<String, String>();
        mapData.put("tuId", studentTuition);
        TuitionRule tuitionRule = baiseeTuitionService.selectTuitionRuleById(mapData);// 查询总费用
        List<TuitionDiscount> listDiscounts = baiseeTuitionService.selectTuitionDiscountById(mapData);// 将分期类型放进去
        List<TuitionStages> tuitionStages = baiseeTuitionService.selectTuitionStagesById(mapData);// 下面查询分期类型(根据学费id)//查询这个学费都有哪些分期选择类型
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("tuitionRule", tuitionRule);// 放进去学费规则
        if (AuditionTuitionMoney != "" || AuditionTuitionMoney != null) {// 在这里需要放进去一个试听费用金额
            map.put("AuditionTuitionMoney", AuditionTuitionMoney);// 将试听费用传到前台页面
        } else {
            map.put("AuditionTuitionMoney", "0");// 将试听费用传到前台页面
        }
        map.put("listDiscounts", listDiscounts);// 放进去分期优惠 其实也是一次性的优惠类型
        map.put("Prepayment", Prepayment);// 放进去预交费用
        map.put("tuitionStages", tuitionStages);// 把这项学费的分期信息放进去
        return map;
    }

    /**
     * 查询某个学费的周期详细分期信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getTotalTuitionStagesDetailed", method = RequestMethod.POST)
    @ResponseBody
    @Role(ignore = true)
    public Map<Object, Object> getTotalTuitionStagesDetailed(HttpServletRequest request) {
        String tuStId = ParamUtils.getParameter(request, "StagesType");// 获取学费的周期类型，周期的主键，然后查询它的分期详细
        Map<String, String> mapData = new HashMap<String, String>();
        mapData.put("tuStId", tuStId);
        List<TuitionStagesRule> stagesRules = baiseeTuitionService.selectTuitionStagesDetailedById(mapData);
        String MinimumTuition = "";
        if (stagesRules != null && stagesRules.size() > 0) {
            MinimumTuition = stagesRules.get(0).getTuStRuleMinimumTuition();
        }
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("stagesRules", stagesRules);
        map.put("MinimumTuition", MinimumTuition);
        return map;
    }

    /**
     * 前去分期学生缴费页面
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/toPayTuitionByStages")
    @Role("ZSXYGL_DJJNXF")
    public ModelAndView toPayTuitionByStages(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ModelAndView model = new ModelAndView();
        String stuId = ParamUtils.getParameter(request, "stuId");// 获取学生的主键ID
        BaiseeStudent student = studentService.selectByStudentId(stuId);// 查询这个学生的基本信息
        if (student.getTuId() != null && student.getTuId() != "") {
            Map<String, String> dataMap = new HashMap<String, String>();
            dataMap.put("tuId", student.getTuId());
            TuitionRule tuitionRule = baiseeTuitionService.selectTuitionRuleById(dataMap);// 根据学费主键获取学费的信息
            String totalM = baiseeTransactionService.getTotalMoneyAndDiscounts(stuId, "学费", "试听费");// 总缴费用加上优惠（总缴学费+优惠+试听+抵充）
            System.err.println("到现在为止他缴纳了多少？" + totalM);
            List<DetailAccount> AuditionTuitionDetailAccount = baiseeTransactionService.getDetailAccount(stuId, "试听费");
            List<DetailAccount> detailAccount = baiseeTransactionService.getDetailAccount(stuId, "学费");// 查询缴费记录
            for (DetailAccount detailAccount2 : detailAccount) {
                AuditionTuitionDetailAccount.add(detailAccount2);
            }
            if (detailAccount == null) {
                request.setAttribute("errorPayTuition", "暂时没有此学生的缴费信息！");// 没有缴为信息
                return toForStuList(request, response);
            }
            Integer tuSurplusTuition = 0;
            if (tuitionRule.getTuMonet() != null && totalM != null) {
                tuSurplusTuition = (Integer.parseInt(tuitionRule.getTuMonet()) - Integer.parseInt(totalM));
            } else {
                request.setAttribute("errorPayTuition", "暂时没有此学生的缴费信息！");// 没有缴为信息
                return toForStuList(request, response);
            }
            model.addObject("student", student);// 学生基本信息
            model.addObject("tuMonet", tuitionRule.getTuMonet());// 学生学费的总金额
            model.addObject("tuTypeMeaning", tuitionRule.getTuTypeMeaning());// 学费类型

            model.addObject("totalMoney", totalM);// 到现在已经缴纳多少钱
            model.addObject("tuSurplusTuition", tuSurplusTuition);// 还剩多少钱没有缴纳了
            model.addObject("detailAccount", AuditionTuitionDetailAccount);// 以往缴纳的明细
            model.setViewName("student/stu_ForPayTuition");// 设置跳转页面
            return model;
        } else {
            request.setAttribute("errorPayTuition", "暂时没有此学生的缴费信息！");// 没有缴为信息
            return toForStuList(request, response);
        }
    }

    /**
     * 分期缴纳学费，记录流水，如果缴纳完毕，修改学生状态
     *
     * @throws Exception
     */
    @RequestMapping(value = "/PayTuitionByStages")
    @Role(ignore = true)
    public ModelAndView PayTuitionByStages(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BaiseeUser user = (BaiseeUser) SessionUtil.getUserInfo(request); // 获取当前登录的用户
        String operator = user.getUserId();
        Integer tuMonet = Integer.parseInt(ParamUtils.getParameter(request, "tuMonet"));// 总学费
        Integer totalMoney = Integer.parseInt(ParamUtils.getParameter(request, "totalMoney"));// 已缴纳多少元
        Integer tuSurplusTuition = Integer.parseInt(ParamUtils.getParameter(request, "tuSurplusTuition"));// 剩余未缴纳费用
        String stuId = ParamUtils.getParameter(request, "stuId");// 学生id
        // String tuitioinPeriodsNow = ParamUtils.getParameter(request,
        // "tuitioinPeriodsNow");//这个学生现在在缴纳得是第几期
        String StagesTuitionMoney = ParamUtils.getParameter(request, "StagesTuitionNow");// 分期此次缴纳的费用
        String remarks = ParamUtils.getParameter(request, "remarks");// 获取备注
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("amount", StagesTuitionMoney);// 分期此次缴纳的费用
        map.put("operator", operator);// 操作人
        map.put("userID", stuId);// 用户id
        map.put("type", "学费");// 缴费类型
        map.put("status", "1");// 缴费状态
        if (remarks == null || remarks.equals("") || remarks == "") {
            remarks = "无";
        }
        map.put("remarks", remarks);// 流水备注
        BaiseeStudent student = studentService.selectByStudentId(stuId);// 查询这个学生的基本信息
        Map<String, String> mapData = new HashMap<String, String>();
        mapData.put("tuStId", student.getTuStId());
        List<TuitionStagesRule> stagesRules = baiseeTuitionService.selectTuitionStagesDetailedById(mapData);// 获取分期的详细费用
        Integer StagesTuitionNow = Integer.parseInt(ParamUtils.getParameter(request, "StagesTuitionNow"));// 本次缴纳费用
        Integer totalMoneyNow = totalMoney + StagesTuitionNow;// 到现在总共缴纳了多少元？本次缴纳加上曾经缴纳的钱
        Map<String, String> stuUpdateMap = new HashMap<String, String>();// 有没有缴清都要修改这个学生的
        stuUpdateMap.put("stuId", stuId);
        int i = 1;
        if (totalMoneyNow >= tuMonet && tuSurplusTuition <= StagesTuitionNow) {// 表示已经缴纳清楚,没有缴纳清，只记录流水
            stuUpdateMap.put("tuitionStatus", "1");// 缴纳学费状态，本次缴清
            stuUpdateMap.put("tuitioinPeriodsNow", "0");// 现在正在缴纳的是第几期学费 0 则是缴纳清楚
            request.setAttribute("operationSuccess", "操作成功，本次缴纳学费为：" + StagesTuitionNow + "元 ，学费已缴纳完毕");
        } else {// 没有缴纳清楚学费
            stuUpdateMap.put("tuitionStatus", "0");// 修改缴纳学费状态，没有缴清
            // 根据学生的分期主键获取这个人的分期表里面的学费规则，进行每次判断，看着学生现在缴纳到第几期了？
            Integer tuStRuleHighestTuition = Integer.parseInt(stagesRules.get(0).getTuStRuleHighestTuition());// 周期每期费用和
            for (TuitionStagesRule tuitionStagesRule : stagesRules) {
                // 判断每期学费和缴纳的学费，如果是大于0 并且 小于本次循环的期数价格则是当前循环的次数，则将学生的正在缴纳期数改为这个数
                if ((totalMoneyNow) < (tuStRuleHighestTuition)) {
                    stuUpdateMap.put("tuitioinPeriodsNow", String.valueOf(i));// 已经缴纳清楚学费
                    break;
                } else {
                    tuStRuleHighestTuition += Integer.parseInt(tuitionStagesRule.getTuStRuleHighestTuition());
                }
                i++;
            }
            request.setAttribute("operationSuccess", "操作成功，本次缴纳学费为：" + StagesTuitionNow + "元 ");
        }
        baiseeTransactionService.installment(map);// 记录流水
        studentService.updateStudentTuitionStatusById(stuUpdateMap);// 修改学生的缴费状态
        return toForStuList(request, response);

    }

    /**
     * 前往预缴页面
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/PayPrepaymentHTML")
    @Role("STXYGL_XYYJ")
    public ModelAndView PayPrepaymentHTML(HttpServletRequest request) throws Exception {
        ModelAndView model = new ModelAndView("student/stu_AudPayPrepayment");
        String stuId = ParamUtils.getParameter(request, "stuId");
        BaiseeStudent student = studentService.selectByStudentId(stuId);
        String Prepayment = baiseeTransactionService.readPrepay(stuId);// 查询预缴费用
        model.addObject("student", student);
        if (Prepayment == "" || Prepayment == null) {
            model.addObject("Prepayment", "0");
        } else {
            model.addObject("Prepayment", Prepayment);
        }
        return model;
    }

    /**
     * 前往退预缴费页面
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/backPayPrepaymentHTML")
    @Role("STXYGL_XYYJ")
    public ModelAndView backPayPrepaymentHTML(HttpServletRequest request) throws Exception {
        ModelAndView model = new ModelAndView("student/stu_AudBackPrepay");
        String stuId = ParamUtils.getParameter(request, "stuId");
        BaiseeStudent student = studentService.selectByStudentId(stuId);
        String Prepayment = baiseeTransactionService.readPrepay(stuId);// 查询预缴费用
        model.addObject("student", student);
        if (Prepayment == "" || Prepayment == null) {
            model.addObject("Prepayment", "0");
        } else {
            model.addObject("Prepayment", Prepayment);
        }
        return model;
    }

    /**
     * 预缴费退费
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/backPrepayment")
    @Role(ignore = true)
    public ModelAndView backPrepayment(HttpServletRequest request, HttpServletResponse response, String remarks)
            throws Exception {
        BaiseeUser user = (BaiseeUser) SessionUtil.getUserInfo(request); // 获取当前登录的用户
        String operator = user.getUserId();
        String stuId = ParamUtils.getParameter(request, "stuId");
        String PayPrepaymentMoney = ParamUtils.getParameter(request, "PayPrepaymentMoney");
        try {
            if (PayPrepaymentMoney != "" && PayPrepaymentMoney != null) {
                if (Integer.parseInt(PayPrepaymentMoney) < 1) {
                    Integer.parseInt(PayPrepaymentMoney);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("stuId", stuId);
            request.setAttribute("ErrorPromptPayPrepayment", "金额无效，请重新输入！");
            return PayPrepaymentHTML(request);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("userID", stuId);
        map.put("type", "退预缴费");
        map.put("amount", PayPrepaymentMoney);
        map.put("operator", operator);
        if (StringUtil.isEmpty(remarks)) {
            remarks = "无";
        }
        map.put("remarks", remarks);// 流水备注
        baiseeTransactionService.backPrepay(map);
        request.setAttribute("PayPrepaymentSuccess", "退预交费成功");
        return toAudStuList(request, response);
    }

    /**
     * 预缴费缴纳
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/PayPrepayment")
    @Role(ignore = true)
    public ModelAndView PayPrepayment(HttpServletRequest request, HttpServletResponse response, String remarks)
            throws Exception {
        BaiseeUser user = (BaiseeUser) SessionUtil.getUserInfo(request); // 获取当前登录的用户
        String operator = user.getUserId();
        String stuId = ParamUtils.getParameter(request, "stuId");
        String PayPrepaymentMoney = ParamUtils.getParameter(request, "PayPrepaymentMoney");
        try {
            if (PayPrepaymentMoney != "" && PayPrepaymentMoney != null) {
                if (Integer.parseInt(PayPrepaymentMoney) < 1) {
                    Integer.parseInt(PayPrepaymentMoney);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("stuId", stuId);
            request.setAttribute("ErrorPromptPayPrepayment", "金额无效，请重新输入！");
            return PayPrepaymentHTML(request);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("userID", stuId);
        map.put("type", "预缴费");
        map.put("amount", PayPrepaymentMoney);
        map.put("operator", operator);
        if (StringUtil.isEmpty(remarks)) {
            remarks = "无";
        }
        map.put("remarks", remarks);// 流水备注
        baiseeTransactionService.addPrepay(map);
        request.setAttribute("PayPrepaymentSuccess", "缴纳预缴成功");
        return toAudStuList(request, response);
    }

    /**
     * 点击退费后执行的操作
     *
     * @return
     */
    @RequestMapping(value = "/doRefund")
    @Role("ZSXYGL_XYTF")
    @ResponseBody
    public Object doRefund(HttpServletRequest request) {
        String stuId = ParamUtils.getParameter(request, "stuId");
        String tuId = ParamUtils.getParameter(request, "tuId");
        String stuEnrolmentTime = ParamUtils.getParameter(request, "stuEnrolmentTime");
        return studentService.doRefund(stuId, tuId, stuEnrolmentTime);
    }

    /**
     * 退费记录流水
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doRefundFlow")
    @Role(ignore = true)
    public ModelAndView doRefundFlow(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String stuId = ParamUtils.getParameter(request, "sid");// 用户编号
        String refundMoney = ParamUtils.getParameter(request, "refundMoney");// 单笔金额
        BaiseeUser user = (BaiseeUser) SessionUtil.getUserInfo(request);
        String remarks = ParamUtils.getParameter(request, "remarks");// 备注，退费原因
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("amount", refundMoney);
        map.put("operator", user.getUserId());
        map.put("userID", stuId);
        map.put("remarks", remarks);
        map.put("type", "退费");
        map.put("status", "1");
        studentService.doRefundFlow(map);
        return toForStuList(request, response);
    }

    /**
     * 学生返费明细查询
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/selectStudentDetail")
    @Role("ZSXYGL_FFXXCK")
    @ResponseBody
    public List<DetailAccount> selectStudentDetail(HttpServletRequest request, HttpServletResponse response) {

        String stuId = ParamUtils.getParameter(request, "stuId");
        String transactionType = ParamUtils.getParameter(request, "transactionType");

        List<DetailAccount> list = baiseeTransactionService.readReturnFee(stuId, transactionType);

        return list;
    }

    /**
     * 根据学生ID查询学生的详情返费信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/queryRules")
    @Role("ZSXYGL_XYFF")
    @ResponseBody
    public Map<String, Object> queryRules(HttpServletRequest request) {
        String stuId = ParamUtils.getParameter(request, "stuId");
        String tuId = ParamUtils.getParameter(request, "trud");
        String formalAdmissionTime = ParamUtils.getParameter(request, "formalAdmissionTime");
        Map<String, Object> map = studentReturnService.selectRule(stuId, tuId, formalAdmissionTime);
        return map;
    }


    /**
     * 添加学生返费流水
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/saveInstallment")
    @Role(ignore = true)
    public ModelAndView saveInstallment(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //封装参数
        Map<String, Object> map = packaging(request);
        //添加返费
        baiseeTransactionService.addReturnFee(map);
        //查询返费金额是否返全
        studentReturnService.selectReturnfee((String) map.get("userID"), (String) map.get("type"));
        //视图跳转
        return toForStuList(request, response);
    }

    /***
     *
     *   // 推荐人/转推荐人   推荐人姓名
     // String returnName = ParamUtils.getParameter(request, "returnName");
     /*
     * if("".equals(remarks.trim()) || remarks == null) { remark = "类型 ：" +
     * returnType+";  姓名 : " + returnName + ";  返费金额 : " + returnMoney ; }else {
     * remark = "类型 ：" + returnType+";  姓名 : " + returnName + ";  返费金额 : " +
     * returnMoney +" 其他: " +remarks ; }
     *
     *
     * 封装返费的参数
     * @param request
     */
    private Map<String, Object> packaging(HttpServletRequest request) {
        String type = "返费";
        //获取当前登录人
        BaiseeUser user = SessionUtil.getUserInfo(request);
        // 学生ID
        String studentID = ParamUtils.getParameter(request, "studeid");
        //返回时间
        String returnTime = ParamUtils.getParameter(request, "returnTime");
        // 返费金额
        String returnMoney = ParamUtils.getParameter(request, "returnMoney");
        // 备注
        String remarks = ParamUtils.getParameter(request, "remarks");
        //封装参数
        Map<String, Object> map = new HashMap<>(20);
        map.put("type", type);
        map.put("operator", user.getUserId());
        map.put("userID", studentID);
        map.put("returnTime", returnTime);
        map.put("amount", returnMoney);
        map.put("remarks", remarks);
        map.put("status", "1");
        return map;
    }

    /**
     * 选择某个班级，获取相对应的学费
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getTuitionType", method = RequestMethod.POST)
    @ResponseBody
    @Role(ignore = true)
    public Map<Object, Object> getTuitionType(HttpServletRequest request) {
        System.out.println("");
        Map<Object, Object> map = new HashMap<>();
        String claId = ParamUtils.getParameter(request, "claId"); // 获取这个班级的id
        String cStatus = clazzService.selectClaStatusByClaId(claId); // 获取这个班级的所属学籍
        if (cStatus != null) {
            List<TuitionRule> tuitionRules = baiseeTuitionService.selectTuitionByClaStatus(cStatus); // 获取这个班级对应的学费
            map.put("tuitionRules", tuitionRules);
        }
        return map;
    }

    /**
     * 分期下缴纳首次，预缴费用大于等于首次最低缴纳金额
     * 学费主键、首次首期最低金额、页面上首期首次减去预缴费用、预缴金额、页面上输入框中输入的值，学生id，班级id，当前操作人，总学费
     *
     * @throws Exception
     */
    public ModelAndView paymentGreaterStages(HttpServletRequest request, HttpServletResponse response,
                                             String studentTuition, String MinimumTuition, String MinimumTuitionInput, String PrepaymentTuition,
                                             String StagesTuitionNow, String stuId, String claId, String operator, String TotalTuition,
                                             TuitionDiscount tuitionDiscount) throws Exception {
        // 获取预缴表里面还有多少钱
        // Math.abs ： 如果参数是非负数，则返回该参数。如果参数是负数，则返回该参数的相反数
        Integer MinimumTuitionInput1 = Math.abs(Integer.parseInt(MinimumTuitionInput));// 页面上首期首次减去预缴费用，得出得费用，一般是附属负数
        // 获取这次总共缴纳了多少钱（首次首期缴纳的钱 + 文本框中输入的值）
        Integer payTuitionNow = Integer.parseInt(MinimumTuition) + Integer.parseInt(StagesTuitionNow);// 本次总共支付了多少钱学费
        Integer amoutMoney = Integer.parseInt(StagesTuitionNow) + Integer.parseInt(tuitionDiscount.getTuDiReduce());// 实际缴纳加上优惠
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("totalMoney", StagesTuitionNow);// 预缴加实际，此次实际缴纳了多少呢
        map.put("amount", amoutMoney.toString());// 分期首次缴纳单笔金额，
        map.put("takeOut", MinimumTuition);// 抵充金额，预缴金额,本次预缴了多少呢（在这里应该是首次首期最低缴纳费用）
        map.put("discountAmount", tuitionDiscount.getTuDiReduce());// 优惠金额
        map.put("discountsType", tuitionDiscount.getTuDiThing());// 优惠类型
        map.put("operator", operator);// 操作人
        map.put("userID", stuId);// 用户id
        map.put("type", "学费");// 缴费类型
        map.put("remarks", "首次分期");// 备注
        map.put("source", "预缴费");// 抵充来源
        map.put("status", "1");// 缴费状态
        if (MinimumTuitionInput1 > 0) {
            // 修改预缴值
            map.put("balance", MinimumTuitionInput1);// 抵充余额
            baiseeTransactionService.removePrepay(stuId, MinimumTuitionInput1);// 删除此学生的预缴的信息
        } else {
            // 删除预缴值
            map.put("balance", "0");// 抵充余额
            baiseeTransactionService.removePrepay(stuId, 0);// 删除此学生的预缴信息
        }
        baiseeTransactionService.addFullPayment(map);// 记录流水，添加流水
        /**
         * 记录修改学生缴费状态，修改为0，未缴清
         */
        Map<String, String> stuUpdateMap = new HashMap<String, String>();
        stuUpdateMap.put("stuId", stuId);
        // 表示分期缴纳时，缴纳的学费大于等于总学费了
        stuUpdateMap.put("tuitionStatus", "0");
        stuUpdateMap.put("tuId", studentTuition);// 学费类型的主键
        stuUpdateMap.put("tuitioinPeriodsNow", "2");// 修改为正在缴纳第二期
        studentService.updateStudentTuitionStatusById(stuUpdateMap);// 修改学生的缴费状态
        request.setAttribute("operationSuccess",
                "操作成功，本次缴纳学费为：" + payTuitionNow.toString() + "元     " + "预缴学费为：" + MinimumTuition + "元");
        try {
            studentService.updateAudToFor(stuId, claId, null);// 修改学生班级
            return toForStuList(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("stuId", stuId);
        request.setAttribute("ErrorPrompt", "本次金额无效，请重新输入！");
        return AudToFor(request);
    }

    /**
     * 校验输入的学费数据是否正确
     *
     * @param request
     * @param stagesTuitionNow
     * @param totalTuition
     * @param prepaymentTuition
     * @param stuId
     * @param minimumTuitionInput
     * @return
     * @throws Exception
     */
    public ModelAndView validatePayNumber(HttpServletRequest request, String stagesTuitionNow, String totalTuition,
                                          String prepaymentTuition, String stuId, String minimumTuitionInput, String PayTuitionspecial)
            throws Exception {
        try {
            if (stagesTuitionNow != null && stagesTuitionNow != "") {
                boolean stagesMoneyError = Double.parseDouble(stagesTuitionNow) < 1;
                if (stagesMoneyError) {
                    request.setAttribute("stuId", stuId);
                    request.setAttribute("ErrorPrompt", "金额无效，请重新输入！");
                    return AudToFor(request);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("stuId", stuId);
            request.setAttribute("ErrorPrompt", "金额无效，请重新输入！");
            return AudToFor(request);
        }
        /**************************
         * 判断输入的学费是否小于最小缴纳金额
         ***************************************/
        if (PayTuitionspecial != null && PayTuitionspecial.equals("0")) {
            if (stagesTuitionNow != null && stagesTuitionNow != "") {
                Integer now = Integer.parseInt(stagesTuitionNow);
                Integer minTuition = Integer.parseInt(minimumTuitionInput);
                if (now < minTuition) {
                    request.setAttribute("stuId", stuId);
                    request.setAttribute("ErrorPrompt", "金额无效，请重新输入！");
                    return AudToFor(request);
                }
            }
        }
        return null;
    }

    /**
     * 区县查询
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("showAddress")
    @ResponseBody
    @Role(ignore = true)
    public ResponseResult<BaiseeStudentSupplement> showAddress(HttpServletRequest request, String stuId)
            throws Exception {
        ResponseResult<BaiseeStudentSupplement> rr = new ResponseResult<BaiseeStudentSupplement>(1, "成功");
        BaiseeStudentSupplement supplement = studentService.selectByStudentIdQuerySupplement(stuId);
        rr.setData(supplement);
        return rr;

    }

    // 正式学生缴费页面List
    // 需求：List 页面展示 分页显示
    // 姓名 ，班级，地区，入学时间，本人电话，未交学费费用，备注
    @RequestMapping(value = "/ForTuitionList")
    @Role(value = "XSJS")
    public ModelAndView ForTuitionList(HttpServletRequest request, HttpServletResponse response) {
        BaiseeUser user = (BaiseeUser) SessionUtil.getUserInfo(request); // 获取当前登录的用户
        String[] bsClass = (String[]) request.getSession().getAttribute("bsClass");

        ModelAndView model = new ModelAndView();
        String areas = null;// 页面上面选择地区
        String areasMap = ParamUtils.getParameter(request, "areas");// 地区选择
        if (!StringUtil.isStrEmpty(areasMap)) {
            areas = areasMap;
        }
        String itemlableSearch = null;// 页面上姓名或者班级
        String itemlableSearchMap = ParamUtils.getParameter(request, "itemlableSearch");// 姓名、班级
        if (!StringUtil.isStrEmpty(itemlableSearchMap)) {
            itemlableSearch = itemlableSearchMap;
        }
        String stagesNumber = null;// 页面上选择期数
        String stagesNumberMap = ParamUtils.getParameter(request, "stagesNumber");// 期数
        if (!StringUtil.isStrEmpty(stagesNumberMap)) {
            stagesNumber = stagesNumberMap;
        }

        String claStatus = null;// 页面上显示的搜索的学籍初中还是高中
        String claStatusMap = ParamUtils.getParameter(request, "claStatus");
        if (!StringUtil.isStrEmpty(claStatusMap)) {
            claStatus = claStatusMap;

        }
        String stuStartTime = null;// 入学开始时间
        String stuStartTimeMap = ParamUtils.getParameter(request, "stuStartTime");// 开始时间
        if (!StringUtil.isStrEmpty(stuStartTimeMap)) {
            stuStartTime = stuStartTimeMap;
            stuStartTimeMap = stuStartTimeMap.replace("-", "");
        }
        String stuEndTime = null;// 入学结束时间
        String stuEndTimeMap = ParamUtils.getParameter(request, "stuEndTime");// 结束时间
        if (!StringUtil.isStrEmpty(stuEndTimeMap)) {
            stuEndTime = stuEndTimeMap;
            stuEndTimeMap = stuEndTimeMap.replace("-", "");
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userType", user.getUserType());
        map.put("stagesNumber", stagesNumberMap);
        map.put("itemlableSearch", itemlableSearchMap);
        map.put("areas", areasMap);
        map.put("bsClass", bsClass);
        if (stagesNumberMap != "3" && !stagesNumberMap.equals("3")) {
            map.put("claStatus", claStatusMap);
        } else {
            map.put("claStatus", "0");
        }

        map.put("startTime", stuStartTimeMap);
        map.put("endTime", stuEndTimeMap);
        PageInfo<BaiseeStudent> pageInfo = studentService.selectForTuitionList(request, response, map);
        model.addObject("userType", user.getUserType());// 当前登录人的用户type，看他是高中/初中老师
        model.addObject("claStatus", claStatus);// 学籍类型，高中/初中
        model.addObject("itemlableSearch", itemlableSearch);
        model.addObject("pageResult", pageInfo);// 将页面数据放进去
        model.addObject("areas", areas);// 选择的地区
        model.addObject("stagesNumberHidden", stagesNumber);// 选择的期数
        model.addObject("stuStartTime", stuStartTime);// 开始时间
        model.addObject("stuEndTime", stuEndTime);// 结束时间
        model.setViewName("student/stu_ForTuitionList");// 页面
        return model;
    }

    /**
     * select2 下拉选择用户专用
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/select2StuList")
    @ResponseBody
    @Role(ignore = true)
    public PageInfo<BaiseeStudent> select2UserList(HttpServletRequest request) {
        Integer pageNum = ParamUtils.getPageNumParameters(request);
        Integer pageSize = ParamUtils.getPageSizeParameters(request);
        String itemlableSearch = ParamUtils.getParameter(request, "itemlableSearch");
        PageInfo<BaiseeStudent> pageInfo = studentService.selectStuAndClass(pageNum, pageSize, itemlableSearch);
        return pageInfo;
    }

    /**
     * 下载学生在校表现评定表
     *
     * @param request
     */
    @ResponseBody
    @RequestMapping(value = "/studentAssess")
    @Role("XSDAGL_DL")
    public void studentAessess(HttpServletRequest request, HttpServletResponse response) {
        /*
         * String stuId = ParamUtils.getParameter(request, "stuId"); String genpath =
         * request.getSession().getServletContext().getRealPath("/"); Map<String,
         * String> map = new HashMap<String, String>(); try { BaiseeStudentRecord record
         * = studentService.selectStuAssess(stuId); Class clazz =
         * Class.forName("cn.baisee.oa.model.student.BaiseeStudentRecord"); Field[]
         * fields= clazz.getDeclaredFields(); for(Field f:fields){ Object value = null;
         * String fname = f.getName(); String method = "get"+fname.substring(0,
         * 1).toUpperCase()+fname.substring(1,fname.length()); //System.out.println(
         * f.getType()); System.err.println(method); value =
         * clazz.getMethod(method).invoke(record); if(value==null){ value=""; }
         * map.put("${"+fname+"}", String.valueOf(value)); } } catch (Exception e1) { //
         * TODO Auto-generated catch block e1.printStackTrace(); } String fileName =
         * MSWordPoi4.readwriteWord(genpath, map, "stuassess.doc"); String realPath =
         * request.getSession().getServletContext().getRealPath("/"); File destFile =
         * new File(realPath+ fileName); String filename = destFile.getName();//
         * 获取日志文件名称 InputStream fis; try { fis = new BufferedInputStream(new
         * FileInputStream(destFile)); byte[] buffer = new byte[fis.available()];
         * fis.read(buffer); fis.close(); response.reset(); //
         * 先去掉文件名称中的空格,然后转换编码格式为utf-8,保证不出现乱码,这个文件名称用于浏览器的下载框中自动显示的文件名
         * response.addHeader("Content-Disposition", "attachment;filename=" + new
         * String(filename.replaceAll(" ", "").getBytes("utf-8"),"iso8859-1"));
         * response.addHeader("Content-Length", "" + destFile.length()); OutputStream os
         * = new BufferedOutputStream(response.getOutputStream());
         * response.setContentType("application/octet-stream"); os.write(buffer);// 输出文件
         * os.flush(); os.close(); } catch (FileNotFoundException e) { // TODO
         * Auto-generated catch block e.printStackTrace(); } catch (IOException e) { //
         * TODO Auto-generated catch block e.printStackTrace(); }
         */
        String genpath = request.getSession().getServletContext().getRealPath("/");
        Map<String, Object> params = new HashMap<String, Object>();
        WordUtils wordUtil = new WordUtils();
        String stuId = ParamUtils.getParameter(request, "stuId");
        BaiseeStudentRecord record = studentService.selectStuAssess(stuId);
        try {
            Class clazz = Class.forName("cn.baisee.oa.model.student.BaiseeStudentRecord");
            Field[] fields = clazz.getDeclaredFields();
            for (Field f : fields) {
                Object value = null;
                String fname = f.getName();
                String method = "get" + fname.substring(0, 1).toUpperCase() + fname.substring(1, fname.length());
                // System.out.println( f.getType());
                // System.err.println(method);
                value = clazz.getMethod(method).invoke(record);
                if (value == null) {
                    value = "";
                }
                params.put("${" + fname + "}", String.valueOf(value));
            }

            String path = genpath + "/doc/stuassess.docx"; // 模板位置
            String fileName = new String("评分.docx".getBytes("UTF-8"), "iso-8859-1");
            List<String[]> testList = new ArrayList<String[]>();
            wordUtil.getWord(path, params, testList, fileName, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 下载学生退学申请
     *
     * @param request
     */
    @ResponseBody
    @RequestMapping(value = "/exportApply")
    @Role("XSDAGL_DL")
    public void studentApply(HttpServletRequest request, HttpServletResponse response) {
        BaiseeSystemParam param = (BaiseeSystemParam) RedisUtils.utils.mget(CacheKeyEnum.FTP_SERVER_ITEM.getCacheKey(),
                "FILE_SERVER_IP");
        String genpath = request.getSession().getServletContext().getRealPath("/");
        Map<String, Object> params = new HashMap<String, Object>();
        WordUtils wordUtil = new WordUtils();
        String stuId = ParamUtils.getParameter(request, "stuId");
        /* no： 档案编号 */
        String no = ParamUtils.getParameter(request, "no");
        BaiseeStudentRecord record = studentService.selectStuRecord(stuId);
        try {
            Class clazz = Class.forName("cn.baisee.oa.model.student.BaiseeStudentRecord");
            Field[] fields = clazz.getDeclaredFields();
            for (Field f : fields) {
                Object value = null;
                String fname = f.getName();
                String method = "get" + fname.substring(0, 1).toUpperCase() + fname.substring(1, fname.length());
                // System.out.println( f.getType());
                // System.err.println(method);
                value = clazz.getMethod(method).invoke(record);
                if ("photo".equals(fname)) {
                    continue;
                }
                if (value == null) {
                    value = "";
                }
                params.put("${" + fname + "}", String.valueOf(value));
            }
            if (record.getPhoto() != null) {
                Map<String, Object> header2 = new HashMap<String, Object>();
                String path = "http://" + param.getParamValue() + "/baiseefile" + record.getPhoto();
                header2.put("width", 100);
                header2.put("height", 150);
                header2.put("type", "png");
                header2.put("content", WordUtils.inputStream2ByteArray2(path));
                params.put("${photo}", header2);
            }
            String path = ""; // 档案路径
            if ("1".equals(no)) {
                path = genpath + "/doc/stuapply1.docx"; // 模板位置
            } else if ("2".equals(no)) {
                path = genpath + "/doc/stuapply2.docx"; // 模板位置
            } else if ("3".equals(no)) {
                path = genpath + "/doc/stuapply3.docx"; // 模板位置s
            }
            String fileName = new String("申请.docx".getBytes("UTF-8"), "iso-8859-1");
            List<String[]> testList = new ArrayList<String[]>();
            wordUtil.getWord(path, params, testList, fileName, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 导出学生的就业保障书
     */
    @ResponseBody
    @RequestMapping(value = "/studentAgreement")
    @Role("XSDAGL_DL")
    public void studentAgreement(HttpServletRequest request, HttpServletResponse response) {
        String genpath = request.getSession().getServletContext().getRealPath("/");
        WordUtils wordUtil = new WordUtils();
        Map<String, Object> params = new HashMap<String, Object>();
        String stuId = ParamUtils.getParameter(request, "stuId");
        BaiseeStudentRecord record = studentService.selectStuRecord(stuId);
        try {
            Class clazz = Class.forName("cn.baisee.oa.model.student.BaiseeStudentRecord");
            Field[] fields = clazz.getDeclaredFields();
            for (Field f : fields) {
                Object value = null;
                String fname = f.getName();
                String method = "get" + fname.substring(0, 1).toUpperCase() + fname.substring(1, fname.length());
                value = clazz.getMethod(method).invoke(record);
                if (value == null) {
                    value = "";
                }
                params.put("${" + fname + "}", String.valueOf(value));
            }
            String path = ""; // 档案路径
            path = genpath + "/doc/stuAgreement.docx"; // 模板位置
            String fileName = new String("就业保障协议书.docx".getBytes("UTF-8"), "iso-8859-1");
            List<String[]> testList = new ArrayList<String[]>();
            wordUtil.getWord(path, params, testList, fileName, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping("updateAddress")
    @Role("ZSXYGL_XYXG")
    public ModelAndView updateAddress(HttpServletRequest request, BaiseeStudentSupplement supplement) throws Exception {
        // if(StringUtil.isNotEmpty(supplement.getStuRealProvince())||StringUtil.isNotEmpty(supplement.getStuRealCity())
        // ||StringUtil.isNotEmpty(supplement.getStuRealArea())){
        studentService.updateStudentSupById(supplement);
        // }
        return notSupJump(request, supplement.getStuId());

    }

    /**
     * 学生开户
     *
     * @param request
     * @return
     */
    @RequestMapping("/updateStuState")
    @ResponseBody
    @Role("XSZH")
    public Object updateStuState(HttpServletRequest request) {
        BaiseeUser user = (BaiseeUser) request.getSession().getAttribute(AppConstants.SESSION_USER_INFO);
        String sId = ParamUtils.getParameter(request, "sId");
        Map<String, Object> map = new HashMap<>();
        int i = studentService.udateStuState(sId, user);
        if (i > 0) {
            map.put("flag", "success");
        }
        return map;
    }

    /**
     * 注销学生账户
     *
     * @param request
     * @return
     */
    @RequestMapping("/deleteStuUser")
    @ResponseBody
    @Role("XSZH")
    public Object deleteStuUser(HttpServletRequest request) {
        String sId = ParamUtils.getParameter(request, "sId");
        Map<String, Object> map = new HashMap<>();
        int i = studentService.deleteStuUser(sId);
        if (i > 0) {
            map.put("flag", "success");
        }
        return map;
    }

}
