package cn.baisee.oa.controller;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.model.BaiseeClazz;
import cn.baisee.oa.model.BaisswScore;
import cn.baisee.oa.model.ReturnInfo;
import cn.baisee.oa.model.baiseeTerm;
import cn.baisee.oa.model.student.BaiseeStudent;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaisswScoreService;
import cn.baisee.oa.service.StudentService;
import cn.baisee.oa.utils.ParamUtils;
import cn.baisee.oa.utils.poi.WordUtils;
/**
 * 
 * @author jxx
 *
 */
@Controller()
@RequestMapping("/stuScore")
public class BaisswScoreController {
	@Autowired 
	private BaisswScoreService scoreService;
	@Autowired 
	private StudentService studentService;
	
	
	@Role(value="XSCJ")
	@RequestMapping("/toAchievement")
	public ModelAndView toAchievementAll(HttpServletRequest request, String operationSuccess){
		ModelAndView mv = new ModelAndView();
		Integer pageNum = ParamUtils.getPageNumParameters(request);
		Integer pageSize = ParamUtils.getPageSizeParameters(request);
		// 查询当前登录人所教课的班级/超级管理员查看所有
		String [] bsClass = (String[])request.getSession().getAttribute("bsClass");
		// 查询所有班级
		PageInfo<BaiseeClazz> pageInfo = scoreService.selectWScoreAll(pageNum, pageSize, bsClass);
		if(pageInfo.getSize() < 1) {
			pageInfo = new PageInfo<BaiseeClazz>();
		}
		if(StringUtils.isNotEmpty(operationSuccess)) {
			mv.addObject("operationSuccess", operationSuccess);
		}else {
			mv.addObject("operationSuccess", "");
		}
		mv.addObject("pageResult", pageInfo);
		mv.setViewName("weChat/scoreList");
		return mv;
	}
	
	@Role(value="XSCJ_BJXQ") // 根据班级 查看 班级期数详情
	@RequestMapping("/queryDetails")
	public ModelAndView queryDetails(HttpServletRequest request, String operationSuccess){
		Integer pageNum = ParamUtils.getPageNumParameters(request);
		Integer pageSize = ParamUtils.getPageSizeParameters(request);
		ModelAndView mv = new ModelAndView();
		String classId = request.getParameter("classId");
		PageInfo<baiseeTerm> pageInfo = scoreService.queryDetails(pageNum, pageSize,classId);
		mv.addObject("pageResult", pageInfo);
		mv.addObject("classId", classId);
		if(StringUtils.isNotEmpty(operationSuccess)) {
			mv.addObject("operationSuccess", operationSuccess);
		}else {
			mv.addObject("operationSuccess", "");
		}
		mv.setViewName("weChat/scoreDetailed");
		return mv;
	}
	
	@Role(value="XSCJ_QSXQ")
	@RequestMapping("/showScore")
	public ModelAndView toAchievementAll(HttpServletRequest request){
		String cid = request.getParameter("cid");
		String term = request.getParameter("term");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("weChat/setSocer");
		mv.addObject("cid", cid);
		mv.addObject("term", term);
		return mv;
	}
	
	@Role(value="XSCJ_QSXQ") //根据班级跟期数查看每期详情
	@RequestMapping("/queryResults")
	@ResponseBody
	public List<Map<String, Object>> queryResults(HttpServletRequest request){
		String classId = request.getParameter("classId");
		String term = request.getParameter("term");
		List<Map<String, Object>> scoreList = null;
		try {
			scoreList = scoreService.getClassScore(classId, term);
		} catch (Exception e) {
			scoreList = new ArrayList<Map<String, Object>>();
			e.printStackTrace();
		}
		
		return scoreList;
	}
	
	@Role(value="XSCJ_PLDR") // 跳转模板下载/导入页面
	@RequestMapping("/pathJump")
	@ResponseBody
	public ModelAndView pathJump(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		mv.addObject("classId", "".equals(request.getParameter("classId")) ? "" : request.getParameter("classId"));
		mv.setViewName("weChat/newScores");
		return mv;
	}
	
	@RequestMapping("/stuDownLoad")
	@Role(value="XSCJ_MBXZ")
	public void stuDownLoad(HttpServletRequest req,HttpServletResponse res ){
		//excel标题
		String[] title = {"学生姓名","科目1分数","科目2分数","科目3分数","科目4分数"};
		//excel文件名
		String fileName = "学生成绩表.xls";
		//sheet名
		String sheetName = "学生成绩表";
		InputStream input = null;
		OutputStream out =null;
		String classId = req.getParameter("classId");
		List<BaiseeStudent> list = studentService.selectStuAndClass(classId);
		String[][] content  = new String[list.size()][title.length];
		for (int i = 0; i < list.size(); i++) {
            BaiseeStudent obj = list.get(i);
            content[i][0] = obj.getStuName();
		}
		HSSFWorkbook wb = WordUtils.getHSSFWorkbook(sheetName, title, content, null);
		//响应到客户端
		try {
			this.setResponseHeader(res, fileName);
			OutputStream os= res.getOutputStream();
			wb.write(os);
			os.flush();
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	} 
	
	@RequestMapping("/importStuScore")
	@Role(value="XSCJ_PLDR")
	@ResponseBody			// 批量导入成绩
	public Object importStuScore(HttpServletRequest request) throws Exception {
		ReturnInfo rInfo = new ReturnInfo();
		MultipartHttpServletRequest multipartRequest = null;
		multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("excelfile");
		String classId = request.getParameter("classId");
		Map<String, Object> map = scoreService.importStuScore( file, request, classId);
		rInfo.setResult(map);
		return rInfo;
	}
	@RequestMapping("/deleteScore")
	@Role(value="XSCJ_DLCZ")
	@ResponseBody			// 删除成绩
	public ModelAndView deleteScore(HttpServletRequest request) throws Exception {
		String[] parameterValues = request.getParameterValues("ids");
		String classId = request.getParameter("classId");
		scoreService.deleteScore( parameterValues, classId);
		return queryDetails(request, "操作成功");
	}
	 //发送响应流方法
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes("UTF-8"),"ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
