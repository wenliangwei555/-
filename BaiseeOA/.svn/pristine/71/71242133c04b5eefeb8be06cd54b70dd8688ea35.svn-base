package cn.baisee.oa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.dao.baisee.BaiseeDiscountMapper;
import cn.baisee.oa.dao.baisee.BaiseeRefundMapper;
import cn.baisee.oa.dao.baisee.BaiseeReturnMapper;
import cn.baisee.oa.dao.baisee.BaiseeStagesMapper;
import cn.baisee.oa.dao.baisee.BaiseeTuitionRuleMapper;
import cn.baisee.oa.model.student.BaiseeStudent;
import cn.baisee.oa.model.student.BaiseeStudentRefund;
import cn.baisee.oa.model.tuition.TuitionDiscount;
import cn.baisee.oa.model.tuition.TuitionRule;
import cn.baisee.oa.model.tuition.TuitionStages;
import cn.baisee.oa.model.tuition.TuitionStagesRule;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.TuitionRuleService;
import cn.baisee.oa.utils.ParamUtils;
@Service
public class TuitionRuleServiceImpl implements TuitionRuleService {
	/**
	 * 学费规则Mapper对象
	 */
	@Autowired
	private BaiseeTuitionRuleMapper tuitionMapper;
	/**
	 * 退费规则mapper对象
	 */
	@Autowired
	private BaiseeRefundMapper refundMapper;
	/**
	 * 优惠规则mapper对象
	 */
	@Autowired
	private BaiseeDiscountMapper discountMapper;
	/**
	 * 分期mapper对象
	 */
	@Autowired
	private BaiseeStagesMapper stagesMapper;
	/**
	 * 返费mapper对象
	 */
	@Autowired
	private BaiseeReturnMapper returnMapper;
	@Override
	public ModelAndView getTuitionList(HttpServletRequest request,TuitionRule tuition) {
		ModelAndView model=new ModelAndView("tuition/tuit_list");
		int pageNum=ParamUtils.getPageNumParameters(request);
		int pageSize=ParamUtils.getPageSizeParameters(request);
		PageHelper.startPage(pageNum, pageSize);//开始分页
		List<TuitionRule> list=tuitionMapper.getTuitionList(tuition);
		PageInfo<TuitionRule> info=new PageInfo<TuitionRule>(list);
		model.addObject("pageResult",info);//将分页查询到结果保存到modelanview
		model.addObject("tuit", tuition);
		return model;
	}
	
	@Override
	public ModelAndView toTuitionInfo(HttpServletRequest request) {
		ModelAndView model=new ModelAndView("tuition/tuit_info");
		String tuId=ParamUtils.getParameter(request, "tuId");
		//Map<String, Object> map=new HashMap<String ,Object>();
		Map<String, Object> paramMap=new HashMap<String,Object>();
		paramMap.put("tuId", tuId);
		model.addObject("refundsList", refundMapper.getRefundList());//页面退费下拉
		model.addObject("discountList", discountMapper.getDiscount());//页面优惠下拉
		model.addObject("stagesList", stagesMapper.getStagesList(paramMap));//页面分期下拉
		model.addObject("returnList", returnMapper.getAllReturn());//页面返费下拉
		if (!"".equals(tuId) && tuId!=null ) {//学费ID不为空，去查询该学费关联的数据
			model.addObject("tuition", tuitionMapper.getTuitionById(paramMap));
			return getAllData(model, paramMap);
		}else {//学费ID为空
			model.addObject("tuition", new TuitionRule());
			model.addObject("discount",new TuitionDiscount());
			model.addObject("refund", new BaiseeStudentRefund());
			model.addObject("stages",new TuitionStages());
			model.addObject("stagesRule", new ArrayList<TuitionStagesRule>());
			return model;
		}
	}
	//查询所有与学费关联的数据
	@Override
	public ModelAndView getAllData(ModelAndView model,Map<String, Object> paramMap) {
		String rId=refundMapper.getRidByTuid(paramMap);//根据学费ID查询学费退费关联表
		if (rId != null && !"".equals(rId)) {//查询结果不为空
			paramMap.put("rId", rId);
			model.addObject("refund", refundMapper.getRefundByRid(paramMap));
		}else {//查询结果为空
			model.addObject("refund", new BaiseeStudentRefund());
		}
		String[] tuDiId=discountMapper.getTuDiIdByTuId(paramMap);//查询要修改的优惠ID
		if (tuDiId.length>0) {
			paramMap.put("tuDiId", tuDiId[0]);
			model.addObject("discount", discountMapper.getDiscountsById(paramMap));
		}else {
			model.addObject("discount",new TuitionDiscount());
		}
		String [] tuStId=stagesMapper.getTuStIdByTuId(paramMap);//查询要修改的分期ID
		if (tuStId.length>0) {
			paramMap.put("tuStId",tuStId[0]);
			model.addObject("stages", stagesMapper.getStagesById(paramMap));
		}else {
			model.addObject("stages",new TuitionStages());
		}
			model.addObject("rtId",returnMapper.getRetuByTuId(paramMap));
		return model;
	}
	@Override
	public ModelAndView addOrUpdateTuition(TuitionRule tuition,TuitionDiscount discount,
			BaiseeStudentRefund refund,TuitionStages stages,HttpServletRequest request) {
		ModelAndView model=new ModelAndView("tuition/tuit_info");
		model.addObject("tuition", tuition);
		Map<String, Object> paramMap=new HashMap<String,Object>();
		paramMap.put("tuId", tuition.getTuId());
		if (tuition.getTuId() != null && !"".equals(tuition.getTuId())) {
			//修改前的学费对象
			TuitionRule tuition2=tuitionMapper.getTuitionById(paramMap);
			//修改学费规则
			int r=tuitionMapper.updateTuition(tuition);
			if (r>0) {
				//updateDiscount(tuition, tuition2, discount, paramMap);//修改优惠
				updateRefund(tuition, tuition2, refund, paramMap);//修改退费
				updateStagesAndRule(tuition, tuition2, stages,paramMap);//修改分期及规则
				String rrId=returnMapper.getRid(paramMap);
				if (StringUtils.isEmpty(rrId)) {
					insertReturn(request, paramMap);
				}else {
					updateTuitionReturn(paramMap, request);
				}
			}
		}else {
			//添加学费规则
			model.addObject("prompt", insertTuition(tuition, discount, refund, stages, request));
			paramMap.put("tuId", tuition.getTuId());
		}
		model.addObject("discount", discount);//回显优惠信息
		model.addObject("refund", refund);//回显退费信息
		model.addObject("stages", stages);//回显分期信息
		model.addObject("stagesRule",new ArrayList<TuitionStagesRule>());
		model.addObject("refundsList", refundMapper.getRefundList());//退费下拉框
		model.addObject("discountList", discountMapper.getDiscInfo(new TuitionDiscount()));//优惠下拉框
		model.addObject("stagesList", stagesMapper.getStagesList(paramMap));//分期下拉框
		model.addObject("rtId",returnMapper.getRetuByTuId(paramMap));//返费ID
		model.addObject("returnList", returnMapper.getAllReturn());//返费下拉框
		return model;
	}
	/**
	 * 修改优惠规则
	 */
	@Override
	public void updateDiscount(TuitionRule tuition,TuitionRule tuition2,TuitionDiscount discount,Map<String, Object> paramMap) {
		//优惠
		paramMap.put("tuDiId", discount.getTuDiId());
		if (tuition2.getTuDiscount().equals("1") && tuition.getTuDiscount().equals("0")) {//如果原来有优惠改为没有优惠 则进行删除操作
			discountMapper.delTuitionDisc(paramMap);//删除
		}else if(tuition2.getTuDiscount().equals("0") && tuition.getTuDiscount().equals("1")){//如果原来没有优惠改为有优惠 则进行新增操作
			discountMapper.insertDiscountTyition(paramMap);
		}else if(tuition2.getTuDiscount().equals("1") && tuition.getTuDiscount().equals("1")){
			discountMapper.updateTuitionDiscount(paramMap);
		}
	}
	/**
	 * 修改退费规则
	 */
	@Override
	public void updateRefund(TuitionRule tuition,TuitionRule tuition2,BaiseeStudentRefund refund,Map<String, Object> paramMap) {
		paramMap.put("rId", refund.getrId());
		if (tuition2.getTuRefund().equals("1") && tuition.getTuRefund().equals("0")) {//如果原来有优惠改为没有退费 则进行删除操作
			refundMapper.delTiitionRefund(paramMap);
		}else if(tuition2.getTuRefund().equals("0") && tuition.getTuRefund().equals("1")){//如果原来没有优惠改为有优惠 则进行新增操作
			refundMapper.insertTuitionRefund(paramMap);
		}else if(tuition2.getTuRefund().equals("1") && tuition.getTuRefund().equals("1")){
			refundMapper.updateTuitionRefund(paramMap);
		}
	}
	/**
	 * 修改分期和分期规则
	 */
	@Override
	public void updateStagesAndRule(TuitionRule tuition,TuitionRule tuition2,TuitionStages stages,Map<String, Object> paramMap) {
		paramMap.put("tuStId",stages.getTuStId());
		if (tuition2.getTuByStages().equals("1") && tuition.getTuByStages().equals("0")) {//如果原来分期改为一次性缴清 则进行删除操作
			stagesMapper.delTuitionStages(paramMap);//删除
		}else if(tuition2.getTuByStages().equals("0") && tuition.getTuByStages().equals("1")){//如果原来一次性缴清改为分期 则进行新增操作
			stagesMapper.insertTuitionStages(paramMap);//新增
		}else if(tuition2.getTuByStages().equals("1") && tuition.getTuByStages().equals("1")){
			stagesMapper.updateTuitionStages(paramMap);
		}
	}

	@Override
	public void updateTuitionReturn(Map<String, Object> paramMap,HttpServletRequest request) {
		String rtId=ParamUtils.getParameter(request, "rtId");
		paramMap.put("rtId", rtId);
		returnMapper.updateTuitionReturn(paramMap);
	}
	/**
	 * 添加学费并判断其他添加是否成功
	 */
	@Override
	public String insertTuition(TuitionRule tuition, TuitionDiscount discount, BaiseeStudentRefund refund,
			TuitionStages stages,HttpServletRequest request) {
		int r=tuitionMapper.addTuition(tuition);
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("tuId", tuition.getTuId());
		if (r>0) {
			if (tuition.getTuRefund() != null && !tuition.getTuRefund().equals("0")) {//判断新增学费规则是否退费
				insertRefund(refund, map);//添加退费规则表
			}
			if (tuition.getTuDiscount() != null && !tuition.getTuDiscount().equals("0")) {//判断新增学费规则是否优惠
				//insertDiscount(discount, map);//添加优惠规则
			}
			if (tuition.getTuByStages() != null && !tuition.getTuByStages().equals("0")) {//判断新增学费规则是否分期
				insertStages(stages,request ,map);//添加分期
			}
				insertReturn(request, map);//添加学费返费关联
			return "新增成功";
		}else{
			return "新增失败";
		}
	}
	/**
	 * 添加学费退费关联
	 * @param refund
	 * @param tuId
	 * @return
	 */
	@Override
	public int insertRefund(BaiseeStudentRefund refund,Map<String, Object> map) {
		map.put("rId", refund.getrId());
		int rf=refundMapper.insertTuitionRefund(map);
		return rf;
	}
	/**
	 * 添加学费优惠关联
	 * @param refund
	 * @param tuId
	 * @return
	 */
	@Override
	public int insertDiscount(TuitionDiscount discount, Map<String, Object> map) {
		map.put("tuDiId", discount.getTuDiId());
		int dr=discountMapper.insertDiscountTyition(map);
		return dr;
	}
	/**
	 * 新增分学费分期关联
	 */
	@Override
	public int insertStages(TuitionStages stages,HttpServletRequest request, Map<String, Object> map) {
		map.put("tuStId", stages.getTuStId());
		int sr=stagesMapper.insertTuitionStages(map);
		return sr;
	}
	@Override
	public int insertReturn(HttpServletRequest request, Map<String, Object> map) {
		String rtId=ParamUtils.getParameter(request, "rtId");
		map.put("rtId", rtId);
		return returnMapper.insertTuitionReturn(map);
	}
	/**
	 * 删除方法 根据学费ID删除其他表的主键
	 */
	@Override
	public Object delAllRilesByTid(HttpServletRequest request) {
		String tuId=ParamUtils.getParameter(request, "tuId");
		Map<String, Object> map=new HashMap<String,Object>();
		Map<String, Object> paramMap=new HashMap<String,Object>();
		paramMap.put("tuId", tuId);
		//查询要删除的学费是否有学生在使用该学费规则
		List<BaiseeStudent> stu=tuitionMapper.getStudentByTuId(paramMap);
		if (stu != null && stu.size()>0) {//有的话则不能进行删除
			map.put("flag", "notDel");
			return map;
		}else {//没有则进行删除
			TuitionRule tuition=tuitionMapper.getTuitionById(paramMap);
			return delTuition(paramMap,tuition);
		}
		
	}
	/**
	 * 删除学费并调用其他删除方法
	 */
	@Override
	public Object delTuition(Map<String, Object> paramMap,TuitionRule tuition) {
		Map<String, Object> map=new HashMap<String,Object>();
		int r=tuitionMapper.delTuition(paramMap);
		int sum=0;
		if (r>0) {
			if (!"".equals(tuition.getTuRefund()) && "1".equals(tuition.getTuRefund())) {
				sum=sum+delRefund(paramMap);//删除退费
			}else {
				sum=sum+1;
			}
			if (!"".equals(tuition.getTuDiscount()) && "1".equals(tuition.getTuDiscount())) {
				sum=sum+delDiscount(paramMap);//删除优惠
			}else {
				sum=sum+1;
			}
			if ( !"".equals(tuition.getTuByStages()) && "1".equals(tuition.getTuByStages())) {
				sum=sum+delStages(paramMap);//删除分期
			}else {
				sum=sum+1;
			}
				sum=sum+delRturn(paramMap);//删除返费
				
			if (sum>=4) {
				map.put("flag", "success");
			}else {
				map.put("flag", "tuitError");
			}
		}else {
			map.put("flag", "tuitError");
		}
		return map;
	}
	/**
	 * 删除退费规则
	 */
	@Override
	public int delRefund(Map<String, Object> paramMap) {
		//对退费规则表进行删除操作
		return refundMapper.delTiitionRefund(paramMap);
	}
	/**
	 * 删除优惠
	 */
	@Override
	public int delDiscount(Map<String, Object> paramMap) {
		return discountMapper.delTuitionDisc(paramMap);
	}
	/**
	 * 删除分期
	 */
	@Override
	public int delStages(Map<String, Object> paramMap) {
		return stagesMapper.delTuitionStages(paramMap);
	}
	/**
	 * 删除学费返费关联
	 */
	@Override
	public int delRturn(Map<String, Object> paramMap) {
		return returnMapper.delTuitionReturnByTuId(paramMap);
	}
	
	@Override
	public Map<String, Object> getDiscountById(HttpServletRequest request) {
		String tuDiId=ParamUtils.getParameter(request, "tuDiId");
		Map<String, Object> map=new HashMap<String,Object>();
		Map<String, Object> paramMap=new HashMap<String,Object>();
		paramMap.put("tuDiId", tuDiId);
		//TuitionDiscount discount=discountMapper.getDiscountsById(paramMap);
		//map.put("discId", discount.getTuDiId());
		//map.put("tuDiThing", discount.getTuDiThing());
		return map;
	}
	/**
	 * 根据分期ID查询分期详情
	 */
	@Override
	public Map<Object, Object> getStagesRule(HttpServletRequest request) {
		String tuStId=ParamUtils.getParameter(request, "tuStId");
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("tuStId", tuStId);
		Map<Object, Object> returnMap=new HashMap<Object,Object>();
		returnMap.put("stagesRule", stagesMapper.getStagesRuleById(map));
		return returnMap;
	}

	@Override
	public Map<Object, Object> getTuStCycle(HttpServletRequest request) {
		String tuType=ParamUtils.getParameter(request, "tuType");
		List<TuitionStages> list=new ArrayList<TuitionStages>();
		Map<String, Object> paramMap=new HashMap<String,Object>();
		if ("0".equals(tuType)) {
			paramMap.put("tuStCycle","3");
		}else if ("1".equals(tuType)){
			paramMap.put("tuStCycle","2");
		}else {
		}
		list=stagesMapper.getStagesList(paramMap);
		Map<Object, Object> map=new HashMap<Object,Object>();
		map.put("stagesList", list);
		return map;
	}

	@Override
	public void settingDiscounts(String tuId,String tuDiId) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("tuId", tuId);
		map.put("tuDiId", tuDiId);
		discountMapper.insertDiscountTyition(map);
	}

	@Override
	public List<TuitionDiscount> showDiscount() {
	
		return discountMapper.getDiscount();
	}
	
}
