package cn.baisee.oa.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.ExcelUtils.ExcelBeanUtil;
import cn.baisee.oa.ExcelUtils.ExcelUtil;
import cn.baisee.oa.ExcelUtils.WebUtil;
import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.model.BaiseeClazz;
import cn.baisee.oa.model.IcipBusDict;
import cn.baisee.oa.model.Insurance.BaiseeInsurance;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.ClazzService;
import cn.baisee.oa.service.DictionariesService;
import cn.baisee.oa.service.InsuranceHandleService;
import cn.baisee.oa.utils.DateUtil;
import cn.baisee.oa.utils.ParamUtils;

@Controller
@RequestMapping(value = "insurance")
public class InsuranceHandleController {

	private static final String prefix="poi";
	
	private static final Logger log = LoggerFactory.getLogger(InsuranceHandleController.class);
	
	@Autowired
	private InsuranceHandleService stuInsuranceHandleSerice;
	
	private static final String sheetProductName = "保险信息表";
	
	private static final String excelProductName = "保险信息列表.xls";
	
	//字典service
	@Autowired
	private DictionariesService dictionariesService;
	
	//班级service （LiFQ）
	@Autowired
	private ClazzService clazzService;

	/**
	 * 分页查询学生保险信息
	 */
	@RequestMapping(value = "toHandleList")
	@Role("BXGL_BL")
	public ModelAndView pageSelectStuInsuranceInfo(HttpServletRequest request,ModelAndView model,
				PageInfo<BaiseeInsurance> pageInfo,BaiseeInsurance baiseeInsurance) {
				Integer pageNum = ParamUtils.getPageNumParameters(request);	 /*当前页*/
				Integer pageSize = ParamUtils.getPageSizeParameters(request);/*每页数据*/
				PageHelper.startPage(pageNum, pageSize);    /*开始分页*/


				List<BaiseeInsurance> list = stuInsuranceHandleSerice.selectStudentInsurance(baiseeInsurance);
				pageInfo = new PageInfo<BaiseeInsurance>(list);
				if(baiseeInsurance.getiComName() == null) {
					baiseeInsurance = new BaiseeInsurance();
					baiseeInsurance.setiComName("1");
					baiseeInsurance.setStatus("a");
					baiseeInsurance.setClassName("b");
					baiseeInsurance.setStuStatus("2"); 
				}
				 /* 所有保险公司信息*/
				List<IcipBusDict> list2 = dictionariesService.selectIcipBusDictByDictName("保险公司");
				model.addObject("dicts", list2);  
				/*所有班级信息*/
				List<BaiseeClazz> list3 = clazzService.selectClass(new HashMap<String, Object>());
				model.addObject("clazzs", list3);
				String week = DateUtil.getDateWeek();  /* 获取当前时间的后一周*/
				model.addObject("week", week);
				model.addObject("condition", baiseeInsurance);
				model.addObject("pageResult", pageInfo);
				model.setViewName("insurance/Insurance_handle_list");
				return model;
	}
	
	
	/**
	 * 跳转到办理保险的页面
	 * @return
	 */
	@RequestMapping(value = "toInHandlePage")
	@Role("BXGL_BDBL")
	public ModelAndView toInHandlePage(HttpServletRequest request,ModelAndView model) {
		String id = ParamUtils.getParameter(request, "iId");
		/*查询个人录入的信息*/
		BaiseeInsurance stuInsurance = stuInsuranceHandleSerice.getStuInsuInfo(id);   
		 /* 所有保险公司信息*/
		List<IcipBusDict> list2 = dictionariesService.selectIcipBusDictByDictName("保险公司");
		model.addObject("dicts", list2);
		model.addObject("stuInsuInfo", stuInsurance);
		model.setViewName("insurance/handle_Insurance");
		return model;
	}
	
	/**
	 * 办理
	 * */
	@RequestMapping(value = "toAddStuInsurance")
	@Role("BXGL_BDBL")
	public ModelAndView toAddStuInsurance(HttpServletRequest request,ModelAndView model,BaiseeInsurance baiseeInsurance) {
		int i  = stuInsuranceHandleSerice.toAddStuInsurance(baiseeInsurance);
		if(i > 0 ) {
			request.setAttribute("operationSuccess", "操作成功!");
			model.setViewName("redirect:insurance/toHandleList.ht");
		}else {
			/* 所有保险公司信息*/
			List<IcipBusDict> list2 = dictionariesService.selectIcipBusDictByDictName("保险公司");
			model.addObject("dicts", list2);
			model.addObject("stuInsuInfo", baiseeInsurance);
			request.setAttribute("operationSuccess", "操作失败");
			model.setViewName("insurance/handle_Insurance");
		}
		return model;
	}
	
	/**
	 * 下载excel
	 * @param response
	 * @return
	 */
	@RequestMapping(value="exportExcel")
	@Role("BXGL_BLDC")
	@ResponseBody
	public void exportExcel(HttpServletRequest request,HttpServletResponse response,BaiseeInsurance search){
		PageInfo<BaiseeInsurance> pageInfo = null;
		try {
			Integer pageNum = ParamUtils.getPageNumParameters(request);	 /*当前页*/
			Integer pageSize = ParamUtils.getPageSizeParameters(request);/*每页数据*/
			PageHelper.startPage(pageNum, pageSize);    /*开始分页*/
			List<BaiseeInsurance> products=stuInsuranceHandleSerice.selectStudentInsurance(search);
			pageInfo = new PageInfo<BaiseeInsurance>(products);
			String[] headers=new String[]{"学生姓名","学生性别","家长姓名","学生班级","学生身份证号","保费","是否包含学费","状态","保险公司","保单号","入保时间","到期时间","创建时间"};
			List<Map<Integer, Object>> dataList=ExcelBeanUtil.manageProductList(pageInfo.getList());
			log.info("下载excel ：",dataList);
			Workbook wb=new HSSFWorkbook();
			ExcelUtil.fillExcelSheetData(dataList, wb, headers, sheetProductName);
			WebUtil.downloadExcel(response, wb, excelProductName);
			/*导出excel(把状态更改为)*/
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = formatter.format(new Date()); 
			for (BaiseeInsurance i : products) {
				Map<String, String> map = new HashMap<>();
				if("0".equals(i.getStatus())) {
					i.setStatus("1");	
					map.put("id", i.getId());
					map.put("status", i.getStatus());
					map.put("updateTime", dateString);
					stuInsuranceHandleSerice.updateStatus(map); 
				}
			}
			 //更新完后，设定cookie，用于页面判断更新完成后的标志
			Cookie status = new Cookie("updateStatus","success");
			status.setMaxAge(600);
			response.addCookie(status);//添加cookie操作必须在写出文件前，如果写在后面，随着数据量增大时cookie无法写入。
		} catch (Exception e) {
			log.error("下载excel 发生异常：",e.fillInStackTrace());
		}
	}
	
	
	/*****************************保险单号***********************************************************/
	@RequestMapping(value="/checkInsuranceNumber" ,method = RequestMethod.POST)
	@ResponseBody
	@Role("BXGL_BL")
	public Object checkInsuranceNumber(HttpServletRequest request){
		String id = ParamUtils.getParameter(request, "id");
		String iNo = ParamUtils.getParameter(request, "iNo");
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("iNo", iNo);
		BaiseeInsurance insurance = stuInsuranceHandleSerice.selectcheckInsuranceNumber(map); 
		if(insurance!=null ){
			return false;
		}else{
			return true;
		}
	}
	
	/**异步查询备注*/
	@RequestMapping(value="/toRemarks",method = RequestMethod.POST)
	@ResponseBody
	@Role("BXGL_BL")
	public Object toRemarks(HttpServletRequest request) {
		String id = ParamUtils.getParameter(request, "id");
		/*查询个人录入的信息*/
		BaiseeInsurance stuInsurance = stuInsuranceHandleSerice.getStuInsuInfo(id); 
		if(stuInsurance == null) {
			return null;
		}
		return stuInsurance;
	}
	
	
	public static void main(String[] args) {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);//设置起时间
		//System.out.println("111111111::::"+cal.getTime());
		cal.add(Calendar.YEAR, 1);//增加一年
		//cd.add(Calendar.DATE, n);//增加一天  
		//cd.add(Calendar.DATE, -10);//减10天  
		//cd.add(Calendar.MONTH, n);//增加一个月   
		Date date2 = cal.getTime();
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		  String dateString = formatter.format(date2);
		System.out.println("输出::"+dateString);
	}
}
