package cn.baisee.oa.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.baisee.BaiseeClazzMapper;
import cn.baisee.oa.dao.baisee.BaiseeStagesMapper;
import cn.baisee.oa.dao.baisee.BaiseeSupplementMapper;
import cn.baisee.oa.dao.baisee.BaiseeTransactionMapper;
import cn.baisee.oa.dao.baisee.BaiseeTuitionMapper;
import cn.baisee.oa.dao.baisee.BaiseeUrgrFeesMapper;
import cn.baisee.oa.model.BaiseeDownload;
import cn.baisee.oa.model.DetailAccount;
import cn.baisee.oa.model.student.BaiseeStudent;
import cn.baisee.oa.model.student.BaiseeStudentSupplement;
import cn.baisee.oa.model.tuition.TuitionStagesRule;
import cn.baisee.oa.service.BaiseeUrgeFeesService;

@Service
public class BaiseeUrgeFeesServiceImpl implements BaiseeUrgeFeesService {
	@Autowired
	private BaiseeUrgrFeesMapper baiseeUrgrFeesMapper;
	@Autowired
	private BaiseeTuitionMapper baiseeTuitionMapper;
	@Autowired
	private BaiseeTransactionMapper baiseeTransactionMapper;
	@Autowired
	private BaiseeStagesMapper baiseeStagesMapper;
	@Autowired
	private BaiseeSupplementMapper supplementMapper;
	@Autowired
	private BaiseeClazzMapper classMapper;

	@Override
	public List<BaiseeDownload> getUfeesStudentNo(int pageNum, int pageSize, String itemlableSearch) {
		List<BaiseeDownload> list = new ArrayList<BaiseeDownload>();
		Map<String, String> map = new HashMap<String, String>();
		Calendar nowDate =  Calendar.getInstance();
		map.put("itemlableSearch", itemlableSearch);
		// 查询所有未缴清学费的人
		List<BaiseeStudent> lst = baiseeUrgrFeesMapper.selectUrgrStu( map);

		for (int i = 0; i < lst.size(); i++) {	
			// 获取到未缴清学费的学生    
			BaiseeStudent student = lst.get(i);
			
			// 根据学生ID查询学生的家庭住址
			BaiseeStudentSupplement supp = supplementMapper.selectDetailedById( student.getStuId());
			/*BaiseeClazz bsClass = classMapper.selectDetailedById( student.getClaId());*/
			BaiseeDownload ds = new BaiseeDownload();
			boolean flag = true;
			// 查询所有的缴费信息
			List<DetailAccount> accLst = baiseeTransactionMapper.readTotalMoney(student.getStuId(), "学费");
			List<DetailAccount> accLst1 = baiseeTransactionMapper.readTotalMoney(student.getStuId(), "试听费");
			for(DetailAccount  d:accLst1) {
				accLst.add(d);
			}
			Map<String, Object> paramMap = new HashMap<String, Object>();
			if(student.getTuId()==null) {
				continue;
			}
			paramMap.put("tuId", student.getTuId());
			// 获取分期ID
			String[] tuStId = baiseeStagesMapper.getTuStIdByTuId(paramMap);
			paramMap.put("tuStId", tuStId);
			// 查询分期具体详情 就是为了	判断这个人是第几期未缴清学费
			List<TuitionStagesRule> tsrLst = baiseeStagesMapper.getStagesRuleById(paramMap);
			int addMon =0;
			int money = 0; // 期数中学费
			
			
			
			for (int j = 0; j < tsrLst.size(); j++) {// 循环期数
				if(!flag) {
					break;
				}
				int amount = 0; // 总缴学费
				TuitionStagesRule r = tsrLst.get(j);
				
				addMon += Integer.parseInt(r.getTuStRuleTimeLimit());
				// 获取到第一期的学费
				money += Integer.parseInt(r.getTuStRuleHighestTuition());
				int fees = 0;
				// 获取日历
				Calendar ruXue = Calendar.getInstance();
				// 循环所有的缴费信息
				for (int a=0;a<accLst.size();a++) {
					Calendar monDate = Calendar.getInstance();
					Integer year = Integer.parseInt(accLst.get(a).getTime().substring(0, 4));
					Integer month = Integer.parseInt(accLst.get(a).getTime().substring(4, 6));
					Integer day = Integer.parseInt(accLst.get(a).getTime().substring(6, 8));
					monDate.set(year,month,day);
					if(a==0) {
						Integer year1 = Integer.parseInt(accLst.get(a).getTime().substring(0, 4));
						Integer month1 = Integer.parseInt(accLst.get(a).getTime().substring(4, 6));
						Integer day1 = Integer.parseInt(accLst.get(a).getTime().substring(6, 8));
						ruXue.set(year1,month1,day1);
						ruXue.add(Calendar.DAY_OF_MONTH, addMon);
					}
					if(monDate.before(ruXue)) {
						fees += Integer.parseInt(accLst.get(a).getAmount());
					}
					if(a==accLst.size()-1) {
						if(money>fees) {
							// 如果小于就是这期未缴清
							ds.setPeriods(j+1);
							ds.setName(student.getStuName());
							if(supp != null) {
								ds.setAddress(supp.getStuHomeAddress());
							}
							ds.setClassName(student.getClaName());
							// 最后一次缴费时间
							ds.setEndTime(accLst.get(a).getTime());
							// 应缴费多少
							ds.setPayable(Integer.valueOf(r.getTuStRuleHighestTuition()));
							ds.setStuId(student.getStuId());
							//未交多少
							ds.setUnpaid(money - fees);
							list.add(ds);
							flag = false;
						}
					}
						
					}
				if(nowDate.before(ruXue)) {
					break;
				}
			}
			
			
		}
		return list;
	}

}
