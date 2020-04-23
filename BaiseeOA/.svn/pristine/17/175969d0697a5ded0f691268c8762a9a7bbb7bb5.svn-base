package cn.baisee.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.dao.baisee.BaiseeDiscountMapper;
import cn.baisee.oa.exception.OAServiceException;
import cn.baisee.oa.model.tuition.TuitionDiscount;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.DiscountService;
import cn.baisee.oa.utils.ParamUtils;
/**
 * 优惠信息的Service实现类
 * @author WANGBAOXIANG
 *
 */
@Service
public class DiscountsServiceImpl implements DiscountService{
	@Autowired
	private BaiseeDiscountMapper discMapper;
	@Override
	public ModelAndView getDiscInfo(HttpServletRequest request,TuitionDiscount discount) throws OAServiceException {
		try {
			ModelAndView model=new ModelAndView("discount/disc_list");
			int pageNum=ParamUtils.getPageNumParameters(request);
			int pageSize=ParamUtils.getPageSizeParameters(request);
			PageHelper.startPage(pageNum, pageSize);//开始分页
			List<TuitionDiscount> list=discMapper.getDiscInfo(discount);
			PageInfo<TuitionDiscount> info = new PageInfo<TuitionDiscount>(list);
			model.addObject("pageResult", info);//将查询的结果放到ModelAndView
			model.addObject("dis",discount);
			return model;
		} catch (Exception e) {
			throw new OAServiceException("查询优惠信息列表失败", e);
		}
		
	}
	
	@Override
	public ModelAndView toDiscInfo(HttpServletRequest request) {//跳转到编辑页面
		String tuDiId=ParamUtils.getParameter(request, "tuDiId");//获取页面上传过来的优惠ID
		ModelAndView model=new ModelAndView("discount/disc_info");
		if (tuDiId!=null && !"".equals(tuDiId)) {//不为null修改
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("tuDiId", tuDiId);
			model.addObject("discInfo", discMapper.getDiscountsById(map));
		}else {//为null添加
			model.addObject("discInfo", new TuitionDiscount());
		}
		return model;
	}
	
	@Override
	public int adddiscount(TuitionDiscount discount) throws OAServiceException {//添加方法
		try {
			return discMapper.addDiscount(discount);
		} catch (Exception e) {
			throw new OAServiceException("新增优惠信息列失败", e);
		}
	}
	@Override
	public int updatediscount(TuitionDiscount discount) throws OAServiceException {//修改方法
		return discMapper.updateDiscount(discount);
	}
	@Override
	public ModelAndView saveOrUpdateDisc(TuitionDiscount discount) throws OAServiceException {
		ModelAndView model=new ModelAndView("discount/disc_info");
		model.addObject("discInfo", discount);
		if (discount.getTuDiId()!=null && !"".equals(discount.getTuDiId())) {//优惠ID不为null调用修改方法
			int r=updatediscount(discount);
			if (r>0) {
				model.addObject("prompt", "修改成功！");
			}else{
				model.addObject("prompt", "修改失败！");
			}
		} else{//优惠ID为null调用添加方法
			int r=adddiscount(discount);
			if (r>0) {
				model.addObject("prompt", "添加成功！");
			}else{
				model.addObject("prompt", "添加失败！");
			}
		}
		return model;
	}

	@Override
	public Map<String, Object> delDisc(HttpServletRequest request) {//删除优惠规则
		String tuDiId=ParamUtils.getParameter(request, "tuDiId");
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("tuDiId", tuDiId);
		String[] diId=discMapper.getTuDiIdByTuId(paramMap);//查询优惠规则是否与学费关联
		Map<String, Object> map=new HashMap<String,Object>();
		if (diId.length>0) {//与学费关联不可以删除
			map.put("flag", "notDel");
		}else {//进行删除操作
			int r=discMapper.delDisc(paramMap);
			if (r>0) {
				discMapper.delTuitionDiscByTuDiId(paramMap);
				map.put("flag", "success");
			}else {
				map.put("flag", "error");
			}
		}
		return map;
	}



	
	
	
}
