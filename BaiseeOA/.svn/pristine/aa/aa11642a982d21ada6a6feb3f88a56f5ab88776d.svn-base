package cn.baisee.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.baisee.BaiseeTuitionMapper;
import cn.baisee.oa.model.tuition.TuitionDiscount;
import cn.baisee.oa.model.tuition.TuitionRule;
import cn.baisee.oa.model.tuition.TuitionStages;
import cn.baisee.oa.model.tuition.TuitionStagesRule;
import cn.baisee.oa.service.BaiseeTuitionService;
/**
 * 缴纳学费
 * @author LiFQ
 *
 */
@Service
public class BaiseeTuitionServiceImpl implements BaiseeTuitionService {
	
	@Autowired
	private BaiseeTuitionMapper baiseeTuitionMapper;
	
	@Override
	public List<TuitionRule> selectTuitionRuleList() {
		return baiseeTuitionMapper.selectTuitionRuleList();
	}

	@Override
	public TuitionRule selectTuitionRuleById(Map<String, String> map) {
		return baiseeTuitionMapper.selectTuitionRuleById(map);
	}

	@Override
	public List<TuitionDiscount> selectTuitionDiscountById(Map<String, String> map) {
		return baiseeTuitionMapper.selectTuitionDiscountById(map);
	}

	@Override
	public List<TuitionStages> selectTuitionStagesById(Map<String, String> map) {
		return baiseeTuitionMapper.selectTuitionStagesById(map);
	}

	@Override
	public List<TuitionStagesRule> selectTuitionStagesDetailedById(Map<String, String> map) {
		return baiseeTuitionMapper.selectTuitionStagesDetailedById(map);
	}

	@Override
	public TuitionDiscount selectTuitionDiscountDetailedById(Map<String, String> map) {
		return baiseeTuitionMapper.selectTuitionDiscountDetailedById(map);
	}

	@Override
	public List<TuitionRule> selectTuitionByClaStatus(String claStatus) {
		return baiseeTuitionMapper.selectTuitionByClaStatus(claStatus);
	}

	@Override
	public List<TuitionStages> selectTuitionStagesNumber() {
		return baiseeTuitionMapper.selectTuitionStagesNumber();
	}

	@Override
	public String selectTotalTutitonLessThanPeriodByTuStId(String tuStId, String stagesNumber) {
		return baiseeTuitionMapper.selectTotalTutitonLessThanPeriodByTuStId(tuStId, stagesNumber);
	}

	@Override
	public String selectTotalTutitonCurrentPeriodByTuStId(String tuStId, String stagesNumber) {
		return baiseeTuitionMapper.selectTotalTutitonCurrentPeriodByTuStId(tuStId, stagesNumber);
	}

	@Override
	public int removeDiscounts(String tuId, String tuDiId) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("tuId", tuId);
		map.put("tuDiId", tuDiId);
		int discounts = baiseeTuitionMapper.deleteDiscounts(map);
		return discounts;
	}

}
