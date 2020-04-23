package cn.baisee.oa.importExcel.filter.chain;

import cn.baisee.oa.core.context.ICIPContext;

import cn.baisee.oa.importExcel.dto.RowValidateResultDto;
import cn.baisee.oa.importExcel.filter.AbstractFilterChain;

import cn.baisee.oa.importExcel.filter.validator.EmpCheckMobileAndCertValidator;
import cn.baisee.oa.model.ImportEmpModel;
/**
 * 加载
 * @author Administrator
 *
 */
public class EmpBaseCheckFilterChain extends AbstractFilterChain<ImportEmpModel, RowValidateResultDto> {

	
	public EmpBaseCheckFilterChain(ICIPContext context) {
		init(context);
	}

	/**
	 * 初始化过滤器链
	 * 
	 * @param context
	 *            过滤过程中需要使用到的数据，统一放在了这个上下文中
	 */
	private void init(ICIPContext context) {
		/** 基础性数据校验器 */
		this.filters.add(new EmpCheckMobileAndCertValidator());
	}
}
