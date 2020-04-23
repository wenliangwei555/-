package cn.baisee.oa.controller;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import cn.baisee.oa.model.BaiseeRetuAO;
import cn.baisee.oa.service.BaiseeStudentReturnService;
import cn.baisee.oa.utils.ParamUtils;

@Controller
@RequestMapping(value="stuRetu")
public class BaiseeStudentReturnController {
	@Autowired
	private BaiseeStudentReturnService stuRetuService;
	
	/**
	 * 分页查询所有返费信息
	 * @param request
	 * @param retuAO
	 * @return
	 */
	@RequestMapping(value="toRetuList")
	@Role(value="FFXXCK")
	public ModelAndView toRetuList(HttpServletRequest request,BaiseeRetuAO retuAO) {
		return stuRetuService.getRetuStatusList(request,retuAO);	
	}
	/**
	 * 下载返费信息
	 * @param request
	 * @param response
	 * @param wb
	 * @throws Exception
	 */
	@RequestMapping("/retuDownload")
	@Login(ignore = true)
	@Role(ignore=true)
	@ResponseBody
	public void toDownload(HttpServletRequest request, HttpServletResponse response, HSSFWorkbook wb)
			throws Exception {
		String sName=ParamUtils.getParameter(request, "sName");
		String enterStartTime=ParamUtils.getParameter(request, "enterStartTime");
		String enterEndTime=ParamUtils.getParameter(request, "enterEndTime");
		String audStartTime=ParamUtils.getParameter(request, "audStartTime");
		String audEndTime=ParamUtils.getParameter(request, "audEndTime");
		String area=ParamUtils.getParameter(request, "area");
		// 查询表里的所有数据
		List<BaiseeRetuAO> list = stuRetuService.getAllRetu(sName,enterStartTime,enterEndTime,audStartTime,audEndTime,area);
		for (BaiseeRetuAO baiseeRetuAO : list) {
			//给学生的地区赋值
			baiseeRetuAO.setArea(stuRetuService.selectByAreaCode(baiseeRetuAO.getArea()));
			//返费状态
			if (StringUtils.isNotEmpty(baiseeRetuAO.getReturnStatus()) && "1".equals(baiseeRetuAO.getReturnStatus())) {
				baiseeRetuAO.setReturnStatus("已返清");
			}else if (StringUtils.isNotEmpty(baiseeRetuAO.getReturnStatus()) && "0".equals(baiseeRetuAO.getReturnStatus())){
				baiseeRetuAO.setReturnStatus("未返清");
			}else {
				baiseeRetuAO.setReturnStatus("暂无数据");
			}
			//未返费金额
			if (StringUtils.isNotEmpty(baiseeRetuAO.getReturnSum()) && StringUtils.isNotEmpty(baiseeRetuAO.getActualSum())) {
				int returnSum=Integer.parseInt(baiseeRetuAO.getReturnSum());
				int actualSum=Integer.parseInt(baiseeRetuAO.getActualSum());
				baiseeRetuAO.setNotRetu(returnSum-actualSum);
			}else {
				baiseeRetuAO.setNotRetu(0);
			}
		}
		HSSFSheet sheet = wb.createSheet("返费信息表");
		HSSFRow header = sheet.createRow(0);
		header.createCell(0).setCellValue("学生姓名"); 
		header.createCell(1).setCellValue("年龄");
		header.createCell(2).setCellValue("学籍（初中/高中）");
		header.createCell(3).setCellValue("地区");
		header.createCell(4).setCellValue("实际地区");
		header.createCell(5).setCellValue("试听时间");
		header.createCell(6).setCellValue("入学时间");
		header.createCell(7).setCellValue("已交学费");
		header.createCell(8).setCellValue("是否返费");
		header.createCell(9).setCellValue("应返金额");
		header.createCell(10).setCellValue("返费时间");
		header.createCell(11).setCellValue("已返金额");
		header.createCell(12).setCellValue("未返金额");
		// 填充数据
		int rowNum = 1;
		for (BaiseeRetuAO retuAo : list) {
			HSSFRow row = sheet.createRow(rowNum);
			row.createCell(0).setCellValue(retuAo.getsName());
			row.createCell(1).setCellValue(retuAo.getsAge());
			row.createCell(2).setCellValue(retuAo.getTuTypeMeaning());
			row.createCell(3).setCellValue(retuAo.getArea());
			row.createCell(4).setCellValue(retuAo.getArea());
			row.createCell(5).setCellValue(retuAo.getsAuditionStartTime());
			row.createCell(6).setCellValue(retuAo.getsEnrolmentTime());
			row.createCell(7).setCellValue(retuAo.getTuitionSum());
			row.createCell(8).setCellValue(retuAo.getReturnStatus());
			row.createCell(9).setCellValue(retuAo.getReturnSum());
			row.createCell(10).setCellValue(retuAo.getReturnTime());
			row.createCell(11).setCellValue(retuAo.getActualSum());
			row.createCell(12).setCellValue(retuAo.getNotRetu());
			rowNum++;
		}
		// File也可以用作输出
		OutputStream output = response.getOutputStream();
		response.reset();
		response.setHeader("Content-disposition", "attachment; filename=baisee.xls");
		response.setContentType("application/msexcel");
		wb.write(output);
		output.close();
	}
}
