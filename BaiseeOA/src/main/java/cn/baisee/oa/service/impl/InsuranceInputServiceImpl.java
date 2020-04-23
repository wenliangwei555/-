package cn.baisee.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.baisee.BaiseeClazzMapper;
import cn.baisee.oa.dao.baisee.BaiseeInsuranceMapper;
import cn.baisee.oa.dao.baisee.BaiseeStuMapper;
import cn.baisee.oa.exception.OAServiceException;
import cn.baisee.oa.model.BaiseeClazz;
import cn.baisee.oa.model.Insurance.BaiseeInsurance;
import cn.baisee.oa.model.student.BaiseeStudent;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.InsuranceInputService;
import cn.baisee.oa.utils.StringUtil;

@Service
public class InsuranceInputServiceImpl implements InsuranceInputService {

	@Autowired
	private BaiseeInsuranceMapper insuranceMapper;

	//学生mapper
	@Autowired
	private BaiseeStuMapper baiseeStuMapper;
	
	@Autowired
	private BaiseeClazzMapper claMapper;
	@Override
	public PageInfo<BaiseeInsurance> selectPageInsurance(BaiseeInsurance insurance, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<BaiseeInsurance> list = insuranceMapper.selectStudentInsurance(insurance);
		PageInfo<BaiseeInsurance> pageInfo = new PageInfo<BaiseeInsurance>(list);
		return pageInfo;
	}

	@Override
	public String addInsurance(BaiseeInsurance insurance) {
		//将stuId传入到所有学生的集合 Id 姓名 班级 
		Map<String, String> map = new HashMap<String, String>();
		map.put("itemlableSearch", insurance.getStuId());//学生id
		List<BaiseeStudent> students = baiseeStuMapper.selectStuAndClass(map);
		if(students != null && students.size()>0){
			//根据id 查询保险表
			List<BaiseeInsurance> list = insuranceMapper.selectStudentInsurance(insurance);
			for (BaiseeInsurance baiseeInsurance : list) {
				if("0".equals(baiseeInsurance.getStatus())  || "1".equals(baiseeInsurance.getStatus())){
					// 状态为0 时，代表未投保 ，1 代表投保中 这两个状态时不能进行录入
					return "该用户不能再次录入,请重新填写";
				}
			}
			BaiseeStudent student = students.get(0);//得到学生信息也是唯一一个
			String classId = student.getClaId();// 学生班级id
			BaiseeClazz clazz = claMapper.selectDetailedById(classId);//学生所在班级
			insurance.setClassName(clazz.getcName());// 赋值班级名称
			insurance.setClassId(classId);// 赋值班级id
			insurance.setStuName(student.getStuName()); // 赋值学生姓名
			insurance.setStuIdNumber(student.getStuIdNumber()); // 赋值学生身份证号
			insuranceMapper.addInsurance(insurance);
			return "操作成功";
		}else{
			return "用户不符合录入条件";
		}
		
	}

	@Override
	public Map<String, Object> delInsurance(String id) throws OAServiceException{
		try {
			Map<String, Object> map = new HashMap<String,Object>();
			if(!StringUtil.isEmpty(id)){//判断id是否为空
				//不为空
				BaiseeInsurance ins = insuranceMapper.getStuInsuInfo(id);//查询该保险的状态
				if(ins == null){
					map.put("flag", "fail");//没有对应的数据，返回失败信息
				}else if("0".equals(ins.getStatus())){//0 为未入保  可以删除
					int del = insuranceMapper.delInsurance(id);
					if(del > 0){
						map.put("flag", "success");//删除成功
					}else{
						map.put("flag", "fail");//删除失败
					}
				}else{
					map.put("flag", "statusFail");//其他状态不能删除
				}
			}else{
				map.put("flag", "fail");//为空，返回失败信息
			}
			return map;
		} catch (Exception e) {
			throw new OAServiceException("删除保险失败", e);
		}
	}

	@Override
	public Boolean updInsurance(BaiseeInsurance ins) {
		BaiseeInsurance insurance = insuranceMapper.getStuInsuInfo(ins.getId());
		if(insurance == null){
			return false;//没有对应的保险信息
		}
		if("2".equals(insurance.getStatus()) || "3".equals(insurance.getStatus())){
			return false;//状态是已办理 或者 是 已到期时 不能进行修改
		}
		int r = insuranceMapper.updIns(ins);
		if(r > 0){
			return true;
		}
			return false;
	}
}
