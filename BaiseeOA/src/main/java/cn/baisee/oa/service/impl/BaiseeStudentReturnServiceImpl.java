package cn.baisee.oa.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import cn.baisee.oa.dao.baisee.BaiseeDictMapper;
import cn.baisee.oa.dao.baisee.BaiseeStuMapper;
import cn.baisee.oa.dao.baisee.BaiseeStudentReturnMapper;
import cn.baisee.oa.dao.baisee.BaiseeTransactionMapper;
import cn.baisee.oa.model.BaiseeRetuAO;
import cn.baisee.oa.model.DetailAccount;
import cn.baisee.oa.model.student.BaiseeStudentReturnRule;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeStudentReturnService;
import cn.baisee.oa.utils.ParamUtils;
import cn.baisee.oa.utils.SessionUtil;
import cn.baisee.oa.utils.StringUtil;

@Service
public class BaiseeStudentReturnServiceImpl implements BaiseeStudentReturnService {
	@Autowired
	private BaiseeTransactionMapper baiseeTransactionMapper;

	@Autowired
	private BaiseeStudentReturnMapper baiseeStudentReturnMapper;
	
	@Autowired
	private BaiseeStuMapper baiseeStuMapper;
	@Autowired
	private BaiseeDictMapper baiseeDictMapper;
	
	@Override
	public Map<String, Object> selectRule(String stuId, String tuId, String formalAdmissionTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 查询该学生所交的所有学费
		String selectTotalMoney1 = baiseeTransactionMapper.selectPaymentAmount(stuId, "学费");
		if (!"".equals(selectTotalMoney1) && selectTotalMoney1 != null) {
			Integer valueOf = Integer.valueOf(selectTotalMoney1);
			try {
				// 查询返费规则表 总共返费多少钱upDate=5000
				List<BaiseeStudentReturnRule> list = baiseeStudentReturnMapper.selectReturFee(stuId);
				for (BaiseeStudentReturnRule studentReturn : list) {
					if(studentReturn==null) {
						break ;
					}
					// 返费金额
					String returnmoney = studentReturn.getUpDate();
					Integer valueOf5 = Integer.valueOf(returnmoney);
					//if (valueOf >= valueOf2 && inTheQuery >= valueOf3) {
						List<DetailAccount> selectDetailAccount = baiseeTransactionMapper.selectDetailAccount(stuId,
								"返费");
						Integer valueOf4 = 0;
						map.put("stuId", stuId);
						if (CollectionUtils.isNotEmpty(selectDetailAccount)) {
							//map.put("outcome", studentReturn.getUpDate());// 总的金额
							for (DetailAccount detailAccount : selectDetailAccount) {
								valueOf4 = Integer.valueOf(detailAccount.getAmount());
								if(valueOf4 > 0) {
									map.put("referee", "six");
									map.put("outcome", valueOf5 - valueOf4);
								}
							}
					//	}
						return map;
					}else {
						map.put("outcome", studentReturn.getUpDate());
						return map;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	
	@Override
	public void selectReturnfee(String studentID, String type) {
		// 查询这个学生总共返了多少费用
		String selectTotalMoney1 = baiseeTransactionMapper.selectPaymentAmount(studentID, "返费");
		// 查询规则表 获取应返费用
		List<BaiseeStudentReturnRule> list = baiseeStudentReturnMapper.selectReturFee(studentID);
		// 判断 如果相等 则修改学生返费状态
		for (BaiseeStudentReturnRule baiseeStudentReturn : list) {
			if(baiseeStudentReturn == null || selectTotalMoney1.equals("")  || selectTotalMoney1==null || 
					baiseeStudentReturn.getUpDate().equals("") || baiseeStudentReturn.getUpDate() == null) {
				continue;
			}
			int money = Integer.valueOf(baiseeStudentReturn.getUpDate());
			int money2 = Integer.valueOf(selectTotalMoney1);
			if (money == money2) {
				// 修改状态
				Map<String, Object> map = new HashMap<>();
				map.put("stuId", studentID);
				map.put("status", 1);
				baiseeStuMapper.updateThtStudentStatus(map);
			}else {
				// 修改状态
				Map<String, Object> map = new HashMap<>();
				map.put("stuId", studentID);
				map.put("status", 2);
				baiseeStuMapper.updateThtStudentStatus(map);
			}
		}

	}
	
	@Override
	public ModelAndView getRetuStatusList(HttpServletRequest request,BaiseeRetuAO retuAO) {
		ModelAndView model=new ModelAndView("retuDetail/detail_list");
		int pageNum=ParamUtils.getPageNumParameters(request);
		int pageSize=ParamUtils.getPageSizeParameters(request);
		PageHelper.startPage(pageNum, pageSize);//开始分页
		List<BaiseeRetuAO> list=baiseeStudentReturnMapper.getRetuStatusList(retuAO);
		for (BaiseeRetuAO baiseeRetuAO : list) {
			//给学生的真实地区赋值
			if(StringUtil.isNotEmpty(baiseeRetuAO.getRealArea())) {//真实地区为空的情况下将地区赋值给真实地区
				baiseeRetuAO.setRealArea(baiseeDictMapper.selectByAreaCode(baiseeRetuAO.getRealArea()));
			}else {
				baiseeRetuAO.setRealArea(baiseeDictMapper.selectByAreaCode(baiseeRetuAO.getArea()));
			}
			baiseeRetuAO.setArea(baiseeDictMapper.selectByAreaCode(baiseeRetuAO.getArea()));//给学生地区赋值
			//学生的应返金额与已返金额不为空，计算剩余返费金额，为空则
			if (StringUtils.isNotEmpty(baiseeRetuAO.getReturnSum()) && StringUtils.isNotEmpty(baiseeRetuAO.getActualSum())) {
				int returnSum=Integer.parseInt(baiseeRetuAO.getReturnSum());//应该返费金额
				int actualSum=Integer.parseInt(baiseeRetuAO.getActualSum());//已返费金额
				baiseeRetuAO.setNotRetu(returnSum-actualSum);//剩余金额
			}else {
				baiseeRetuAO.setNotRetu(0);
			}
		}
		PageInfo<BaiseeRetuAO> info=new PageInfo<BaiseeRetuAO>(list);
		model.addObject("pageResult", info);//将查询结果放到modelandview中
		model.addObject("retuAO", retuAO);//将搜索的数据放到modelandview中在页面进行回显
		return model;
	}
	@Override
	public List<BaiseeRetuAO> getAllRetu(String sName,String enterStartTime,String enterEndTime,
			String audStartTime,String audEndTime,String area){
		//创建BaiseeRetuAO实体对象，调用有参构造方法
		BaiseeRetuAO retuAO=new BaiseeRetuAO(sName, enterStartTime, enterEndTime, audStartTime, audEndTime, area);
		List<BaiseeRetuAO> list=baiseeStudentReturnMapper.getRetuStatusList(retuAO);//查询返费情况
		return list;
	}
	
	@Override
	public String selectByAreaCode(String area) {
		return baiseeDictMapper.selectByAreaCode(area);//查询地区码
	}

}
