package cn.baisee.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.dao.baisee.BaiseeClazzMapper;
import cn.baisee.oa.dao.baisee.BaiseeStuMapper;
import cn.baisee.oa.exception.OAServiceException;
import cn.baisee.oa.model.BaiseeClazz;
import cn.baisee.oa.model.empinfo.Employee;
import cn.baisee.oa.model.evaluate.BaiseeStuEva;
import cn.baisee.oa.model.student.BaiseeStudent;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.ClazzService;
import cn.baisee.oa.service.StuEvaluateService;
import cn.baisee.oa.utils.ParamUtils;
import cn.baisee.oa.utils.SessionUtil;
/**
 * 班级的service实现类
 *
 * @author wangbaoxiang
 */
@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private BaiseeClazzMapper claMapper;

    @Autowired
    private BaiseeStuMapper stuMapper;

    @Autowired
    private StuEvaluateService stuEvaService;

    private static final Log logger = LogFactory
            .getLog(ClazzServiceImpl.class);

    @Override
    public ModelAndView getCla(BaiseeClazz cla, HttpServletRequest request)
            throws Exception {
        try {
            ModelAndView model = new ModelAndView("clazz/cla_list");
            int pageNum = ParamUtils.getPageNumParameters(request);
            int pageSize = ParamUtils.getPageSizeParameters(request);
            Map<String, Object> map = new HashMap<String, Object>();
            if (StringUtils.isEmpty(cla.getcStatus())) cla.setcStatus(null);
            map.put("cName", cla.getcName());
            map.put("cType", cla.getcType());
            map.put("cStatus", cla.getcStatus());
            String[] cIds = (String[]) request.getSession().getAttribute("bsClass");
            String userType = SessionUtil.getUserInfo(request).getUserType();
            logger.debug("=========================登录角色类型：" + userType);
            if (StringUtils.isNotEmpty(userType)) {
                map.put("cStatus", userType);
                cla.setcStatus(userType);
            }
            map.put("cIds", cIds);
            PageHelper.startPage(pageNum, pageSize);
            List<BaiseeClazz> list = claMapper.getCla(map);
            PageInfo<BaiseeClazz> page = new PageInfo<BaiseeClazz>(list);
            model.addObject("pageResult", page);
            model.addObject("clazz", cla);
            model.addObject("userType", userType);
            return model;
        } catch (Exception e) {
            throw new OAServiceException("查询班级失败", e);
        }

    }

    @Override
    public int addCla(BaiseeClazz cla) throws Exception {
        try {
            return claMapper.addCla(cla);
        } catch (Exception e) {
            throw new OAServiceException("添加班级失败", e);
        }

    }

    @Override
    public BaiseeClazz getClaById(String cId) throws Exception {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("cId", cId);
            return claMapper.getClaById(map);
        } catch (Exception e) {
            throw new OAServiceException("通过cid查询失败", e);
        }

    }

    @Override
    public Integer updateCla(BaiseeClazz cla) throws Exception {
        try {
            if ("高中".equals(cla.getcStatus())) {
                cla.setcStatus("1");
            }
            if ("正式".equals(cla.getcType())) {
                cla.setcType("1");
            }
            return claMapper.updateCla(cla);
        } catch (Exception e) {
            throw new OAServiceException("修改失败", e);
        }
    }

    @Override
    public Map<String, Object> getStudentByCID(String cId) throws OAServiceException {
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("cId", cId);
            Map<String, Object> map = new HashMap<String, Object>();
            List<BaiseeStudent> list = claMapper.getStudentByCID(paramMap);
            if (list != null && list.size() > 0) {
                map.put("flag", "undeletable");
            } else {
                int r = delCla(cId);
                if (r > 0) {
                    map.put("flag", "success");
                } else {
                    map.put("flag", "error");
                }
            }
            return map;
        } catch (Exception e) {
            throw new OAServiceException("通过cId查询学生信息发生错误", e);
        }
    }

    @Override
    public Integer delCla(String cId) throws OAServiceException {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("cId", cId);
            return claMapper.delCla(map);
        } catch (Exception e) {
            throw new OAServiceException("删除班级失败", e);
        }

    }

    @Override
    public boolean getClaNameList(HttpServletRequest request) throws OAServiceException {
        try {
            String cName = ParamUtils.getParameter(request, "cName");
            String cId = ParamUtils.getParameter(request, "cId");
            String cStatus = ParamUtils.getParameter(request, "cStatus");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("cName", cName);
            map.put("cStatus", cStatus);
            BaiseeClazz clazz = claMapper.getClaNameList(map);
            if (clazz == null) {
                return true;
            } else {
                if (clazz.getcId().equals(cId)) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            throw new OAServiceException("验重查询班级失败", e);
        }
    }

    @Override
    public List<Employee> getEmployeeName() throws OAServiceException {
        try {
            return claMapper.getEmployeeName();
        } catch (Exception e) {
            throw new OAServiceException("查询班主任编号和姓名失败", e);
        }

    }

    @Override
    public ModelAndView saveOrUpdate(BaiseeClazz cla) throws Exception {
        ModelAndView model = new ModelAndView("clazz/cla_info");
        model.addObject("cla", cla);
        model.addObject("list", getEmployeeName());
        if (cla.getcId() != null && !cla.getcId().equals("")) {
            int r = updateCla(cla);
            if (r > 0) {
                model.addObject("prompt", "修改成功！");
            } else {
                model.addObject("prompt", "修改失败！");
            }
        } else {
            int r = addCla(cla);
            if (r > 0) {
                model.addObject("prompt", "添加成功！");
            } else {
                model.addObject("prompt", "修改失败！");
            }
        }
        return model;
    }

    @Override
    public String selectClaStatusByClaId(String claId) {
        return claMapper.selectClaStatusByClaId(claId);
    }

    /**
     * 班级毕业修改
     * @param cId
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, Object> updClaIsGra(String cId) throws Exception {
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("cId", cId);
            paramMap.put("gra", "1");
            int r = claMapper.updClaIsGra(paramMap);
            Map<String, Object> map = new HashMap<String, Object>();
            if (r > 0) {
                int stuCount = claMapper.getStudentCount(paramMap);
                List<String> list = getStuIdByCid(paramMap);
                int sr = 0;
                if (list != null && list.size()>0) {
                    sr = updateStudentStatus(list);
                }
                if (sr == stuCount) {
                    logger.debug("========================修改班级内学生状态成功====================");
                    map.put("prompt", "success");
                } else {
                    logger.debug("========================修改班级内学生状态失败====================");
                    map.put("prompt", "stuInput");
                }
            } else {
                map.put("prompt", "input");
            }
            return map;
        } catch (Exception e) {
            throw new OAServiceException("修改毕业状态失败", e);
        }
    }

    @Override
    public List<String> getStuIdByCid(Map<String, Object> paramMap) {
        return stuMapper.getStudentId(paramMap);
    }

    @Override
    public int updateStudentStatus(List<String> list) {

        return stuMapper.updateStudentStatus(list);
    }

    /**
     * 查询所有班级
     *
     * @return
     */
    public List<BaiseeClazz> selectClass(Map<String, Object> map) {
        return claMapper.selectClass(map);
    }

    ;

    @Override
    public ModelAndView getCla1(BaiseeClazz cla, HttpServletRequest request)
            throws Exception {
        try {
            ModelAndView model = new ModelAndView("evaluate/stu_evaluate_list");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("cName", cla.getcName());
            map.put("cType", cla.getcType());
            /*map.put("cStatus", cla.getcStatus());*/
            String userType = SessionUtil.getUserInfo(request).getUserType();
            logger.debug("=========================登录角色类型：" + userType);
            if (StringUtils.isNotEmpty(userType)) {
                map.put("cStatus", userType);
                cla.setcStatus(userType);
            }

            String isGraduate = ParamUtils.getParameter(request, "isGraduate");
            map.put("isGraduate", isGraduate);
            List<BaiseeClazz> list = claMapper.getCla1(map);
            model.addObject("list", list);

            String param = ParamUtils.getParameter(request, "claName");
            String state = ParamUtils.getParameter(request, "state");
            //	PageInfo<BaiseeStuEva> stuEvaPage = stuEvaService.selectStuEvaPage(request,map);

            /*	PageInfo<BaiseeStuEva> page1=new PageInfo<BaiseeStuEva>(stuEvaPage);*/
            model.addObject("className", param);
            model.addObject("isGraduate", isGraduate);
            model.addObject("state", state);
            //model.addObject("pageResult", stuEvaPage);
            model.addObject("clazz", cla);
            model.addObject("userType", userType);
            return model;
        } catch (Exception e) {
            throw new OAServiceException("查询班级失败", e);
        }

    }

    @Override
    public List<BaiseeClazz> selectClazzByClaId(String[] bsClass, String sta) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("bsClass", bsClass);
        map.put("sta", sta);
        return claMapper.selectClazzByClaId(map);
    }

    @Override
    public ModelAndView getCla2(BaiseeClazz cla, HttpServletRequest request)
            throws Exception {
        try {
            ModelAndView model = new ModelAndView("evaluate/stu_list");
            int pageNum = ParamUtils.getPageNumParameters(request);
            int pageSize = ParamUtils.getPageSizeParameters(request);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("cName", cla.getcName());
            map.put("cType", cla.getcType());
            String userType = SessionUtil.getUserInfo(request).getUserType();
            logger.debug("=========================登录角色类型：" + userType);
            if (StringUtils.isNotEmpty(userType)) {
                map.put("cStatus", userType);
                cla.setcStatus(userType);
            }

            String isGraduate = ParamUtils.getParameter(request, "isGraduate");
            if (isGraduate == null || "".equals(isGraduate)) {
                isGraduate = "0";
            }
            map.put("isGraduate", isGraduate);

            List<BaiseeClazz> list = claMapper.getCla1(map);
            model.addObject("list", list);

            PageHelper.startPage(pageNum, pageSize);
            List<BaiseeStuEva> stuEvaPage = stuEvaService.selectStuEvaListPage(list);
            PageInfo<BaiseeStuEva> page1 = new PageInfo<BaiseeStuEva>(stuEvaPage);
            model.addObject("isGraduate", isGraduate);
            model.addObject("pageResult", page1);
            return model;
        } catch (Exception e) {
            throw new OAServiceException("查询班级失败", e);
        }

    }

    @Override
    public List<BaiseeClazz> findAllClazzInfo() {
        // TODO Auto-generated method stub
        return claMapper.findAllClazzInfo();
    }

}
