package cn.baisee.oa.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.baisee.BaiseeInsuranceMapper;
import cn.baisee.oa.model.Insurance.BaiseeInsurance;
import cn.baisee.oa.service.InsuranceHandleService;
import cn.baisee.oa.utils.DateUtil;


@Service
public class InsuranceHandleServiceImpl implements InsuranceHandleService {

	@Autowired
	private BaiseeInsuranceMapper insuranceMapper;
	
	/**
	 * 分页显示学生保险
	 */
	@Override
	public List<BaiseeInsurance> selectStudentInsurance(BaiseeInsurance baiseeInsurance) {
		return insuranceMapper.selectStudentInsurance(baiseeInsurance);
	}
	

	/**
	 * 查询个人录入的信息
	 */
	@Override
	public BaiseeInsurance getStuInsuInfo(String id) {
		return insuranceMapper.getStuInsuInfo(id);
	}

	/**
	 * 办理学生保险
	 * @param id
	 * @return
	 */
	@Override
	public int toAddStuInsurance(BaiseeInsurance baiseeInsurance) {
		 DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		 String timeAddYear;
		try {
			timeAddYear = DateUtil.timeAddYear(format1.parse(baiseeInsurance.getiInTime()));
			baiseeInsurance.setiOutTime(timeAddYear);   /*到期时间*/
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String dateString = formatter.format(new Date()); 
		  baiseeInsurance.setUpdateTime(dateString);   /*修改时间*/
		int i = insuranceMapper.toAddStuInsurance(baiseeInsurance);
		return i; 
	}

	/**
	 * 校验保险单号
	 */
	@Override
	public BaiseeInsurance selectcheckInsuranceNumber(Map<String, String> map) {
		return insuranceMapper.selectcheckInsuranceNumber(map);
	}

	
	/**
	 * 导出 excel 是修改导出学生的保险状态
	 */
	@Override
	public void updateStatus(Map<String, String> map) {
		insuranceMapper.updateStatus(map);
	}
	
	
}
