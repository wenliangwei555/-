package cn.baisee.oa.service;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.model.Insurance.BaiseeInsurance;

/**
 * 学生保险办理 Service 
 * @author Lenovo
 *
 */
public interface InsuranceHandleService {
	
	/**
	 * 分页查询学生保险
	 * @param map
	 * @return
	 */
	List<BaiseeInsurance> selectStudentInsurance(BaiseeInsurance baiseeInsurance);

	/**
	 * 查询个人录入的信息
	 * @param id 
	 * */
	BaiseeInsurance getStuInsuInfo(String id);

	/**添加保险*/
	int toAddStuInsurance(BaiseeInsurance baiseeInsurance);
	
	/**
	 * 校验保险单号
	 * @param map
	 * @return
	 */
	BaiseeInsurance selectcheckInsuranceNumber(Map<String, String> map);
	
	/**
	 * 导出 excel 是修改导出学生的保险状态
	 * @param map
	 */
	void updateStatus(Map<String, String> map);

}
