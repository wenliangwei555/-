package cn.baisee.oa.controller;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import cn.baisee.oa.annotation.Login;
import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.model.DetailAccount;
import cn.baisee.oa.model.ResponseResult;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeTransactionService;
import cn.baisee.oa.utils.ParamUtils;
@Controller
@RequestMapping("flow")
public class BaiseeTransactionController {
	@Autowired
	private BaiseeTransactionService baiseeTransactionService;
	/**
	 * 查询总金额
	 * @param userID
	 * @param type
	 * @return
	 */
	@RequestMapping("showPayment")
	@Role(value="ZDCX")
	public ModelAndView toClaList(HttpServletRequest request,String endTime,String startTime,String userid,String type){
		int pageNum=ParamUtils.getPageNumParameters(request);
		int pageSize=ParamUtils.getPageSizeParameters(request);
		PageInfo<DetailAccount> claInfo = baiseeTransactionService.getDateQueryTotalBill(pageNum, pageSize, startTime, endTime, userid, type);
		Map<String, Object> map = baiseeTransactionService.sumTotalBill(startTime, endTime, userid, type);
		ModelAndView model=new ModelAndView("flow/totalBill");
		if (claInfo.getTotal() == 0) {
			model.addObject("operationSuccess", "暂无数据");
		}
		model.addObject("pageResult",claInfo);
		model.addObject("startTime", startTime);
		model.addObject("endTime", endTime);
		model.addObject("userid", userid);
		model.addObject("type",type);
		model.addObject("totalIncome",map.get("totalIncome"));//总收入
		model.addObject("totalExpenditure",map.get("totalExpenditure"));//总支出
		return model;
	}
	/**
	 * 查询学生缴费业务表
	 * @param userID
	 * @param type
	 * @return
	 */
	@RequestMapping("showServiceAccount")
	@Login(ignore=true)
	@Role(ignore=true)
	@ResponseBody
	public ResponseResult<List<DetailAccount>> showServiceAccount(String transactionID){
		ResponseResult<List<DetailAccount>> rr=new ResponseResult<List<DetailAccount>>(1, "成功");
		rr.setData(baiseeTransactionService.getDetail(transactionID));
		return rr;
	}
	/**
	 * 查询优惠表
	 * @param userID
	 * @param type
	 * @return
	 */
	@RequestMapping("/getPaymentDiscounts")
	@Login(ignore=true)
	@Role(ignore=true)
	@ResponseBody
	public ResponseResult<List<DetailAccount>> getPaymentDiscounts(String transactionID){
		ResponseResult<List<DetailAccount>> rr=new ResponseResult<List<DetailAccount>>(1,"成功");
		List<DetailAccount> list = baiseeTransactionService.readPaymentDiscounts(transactionID);
		rr.setData(list);
		return rr;
	}
	/**
	 * 查询抵充表
	 * @param userID
	 * @param type
	 * @return
	 */
	@RequestMapping("/getFillFlowMeter")
	@Login(ignore=true)
	@Role(ignore=true)
	@ResponseBody
	public ResponseResult<List<DetailAccount>> getFillFlowMeter(String transactionID){
		ResponseResult<List<DetailAccount>> rr=new ResponseResult<List<DetailAccount>>(1,"成功");
		List<DetailAccount> list = baiseeTransactionService.readFillFlowMeter(transactionID);
		rr.setData(list);
		return rr;
	}

	
}
