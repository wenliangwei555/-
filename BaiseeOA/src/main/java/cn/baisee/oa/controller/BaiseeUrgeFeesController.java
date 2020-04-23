package cn.baisee.oa.controller;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.annotation.Login;
import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.model.BaiseeClazz;
import cn.baisee.oa.model.BaiseeDownload;
import cn.baisee.oa.model.student.BaiseeStudent;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeUrgeFeesService;
import cn.baisee.oa.service.StudentService;
import cn.baisee.oa.utils.ParamUtils;

@Controller
@RequestMapping("ufees")
public class BaiseeUrgeFeesController {
	@Autowired
	private BaiseeUrgeFeesService baiseeUrgeFeesService;
	@Autowired
	private StudentService studentService;
	@RequestMapping("/showUrgeStudents")
	@Login(ignore = true)
	@Role("CFXXGL")
	// @Role("CFXXGL")
	public ModelAndView toForStuList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer pageNum = ParamUtils.getPageNumParameters(request);
		Integer pageSize = ParamUtils.getPageSizeParameters(request);
		String itemlableSearch = "";
		String str = ParamUtils.getParameter(request, "itemlableSearch");
		if (str != null) {
			itemlableSearch = str;
		}
		List<BaiseeDownload> list = baiseeUrgeFeesService.getUfeesStudentNo(pageNum, pageSize, itemlableSearch);
		request.getSession().setAttribute("downloadList", list);
		PageHelper.startPage(pageNum, pageSize);
		PageInfo<BaiseeDownload> pageInfo = new PageInfo<BaiseeDownload>();
		pageInfo.setList(list);
		ModelAndView mv = new ModelAndView("urgefees/urgefees");
		if (pageInfo.getTotal() == 0) {
			mv.addObject("operationSuccess", "暂无数据");
		}
		mv.addObject("pageResult", pageInfo);
		mv.addObject("itemlableSearch", itemlableSearch);
		return mv;
	}

	@RequestMapping("/download")
	@Login(ignore = true)
	@Role("XSJS_CFXZ")
	@ResponseBody
	public void toDownload(HttpServletRequest request, HttpServletResponse response, HSSFWorkbook wb)
			throws Exception {
		// 查询表里的所有数据
		List<BaiseeDownload> list = (List<BaiseeDownload>) request.getSession().getAttribute("downloadList");
		HSSFSheet sheet = wb.createSheet("催费表");
		HSSFRow header = sheet.createRow(0);
		header.createCell(0).setCellValue("期数");
		header.createCell(1).setCellValue("姓名");
		header.createCell(2).setCellValue("班级");
		header.createCell(3).setCellValue("家庭住址");
		header.createCell(4).setCellValue("最后一次交费时间");
		header.createCell(5).setCellValue("应交");
		header.createCell(6).setCellValue("未交");
		// 填充数据
		int rowNum = 1;
		for (BaiseeDownload user : list) {
			HSSFRow row = sheet.createRow(rowNum);
			row.createCell(0).setCellValue(user.getPeriods());
			row.createCell(1).setCellValue(user.getName());
			row.createCell(2).setCellValue(user.getClassName());
			row.createCell(3).setCellValue(user.getAddress());
			row.createCell(4).setCellValue(user.getEndTime());
			row.createCell(5).setCellValue(user.getPayable());
			row.createCell(6).setCellValue(user.getUnpaid());
			rowNum++;
		}
		// File也可以用作输出
		OutputStream output = response.getOutputStream();
		response.reset();
		response.setHeader("Content-Type","application/force-download");
		response.setContentType("application/x-excel;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=" +  	new String("催费.xls".getBytes("GBK"),"ISO-8859-1"));
		/*response.setHeader("Content-disposition", "attachment; filename=baisee.xls");
		response.setContentType("application/msexcel");*/
		wb.write(output);
		output.close();
	}
	
	/**
	 *催费列表点击查看学员基本信息
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value ="/toStudentEdit")
	@Role(ignore = true)
	public ModelAndView toStudentEdit(HttpServletRequest request) throws Exception {
		ModelAndView mv=new ModelAndView();
		String stuId = ParamUtils.getParameter(request, "stuId");
		BaiseeStudent student = studentService.selectByStudentId(stuId);
		List<BaiseeClazz> list = studentService.selectClass("");
		mv.addObject("list", list);
		mv.addObject("stu", student);
		mv.setViewName("urgefees/student_edit");
		return mv;
	}
	

}
