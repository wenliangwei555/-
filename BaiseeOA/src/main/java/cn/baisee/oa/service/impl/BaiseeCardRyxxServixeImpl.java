package cn.baisee.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.baisee.BaiseeCardBindingMapper;
import cn.baisee.oa.dao.baisee.BaiseeEmployeeMapper;
import cn.baisee.oa.dao.baisee.BaiseeStuMapper;
import cn.baisee.oa.dao.baisee.BaiseeStudentApplicantsMapper;
import cn.baisee.oa.dao.card.BaiseeCardRyxxMapper;
import cn.baisee.oa.model.BaiseeCardBinding;
import cn.baisee.oa.model.BaiseeCardRyxx;
import cn.baisee.oa.model.empinfo.Employee;
import cn.baisee.oa.model.student.BaiseeStudent;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeCardRyxxServixe;
@Service
public class BaiseeCardRyxxServixeImpl implements BaiseeCardRyxxServixe{
	
	@Autowired
	private BaiseeCardRyxxMapper cardMapper;
	@Autowired
	private BaiseeCardBindingMapper cardBindingMapper;
	@Autowired
	private BaiseeStuMapper baiseeStuMapper;
	@Autowired
	private BaiseeEmployeeMapper employeeMapper;
	
	@Override
	public PageInfo<BaiseeCardRyxx> getCardAll(int pageNum,int pageSize,String name) {
		PageHelper.startPage(pageNum, pageSize);
		List<BaiseeCardRyxx> cards = cardMapper.getCardAll(name);
		for (BaiseeCardRyxx baiseeCardRyxx : cards) {
			BaiseeCardBinding cardBinding =  cardBindingMapper.getCardBinding(baiseeCardRyxx.getRfkh());
			if(cardBinding!=null) {
				baiseeCardRyxx.setState("已绑定");
				if(cardBinding.getType().equals("1")) {
					baiseeCardRyxx.setType("学生");
					BaiseeStudent student = baiseeStuMapper.selectById(cardBinding.getSid());
					baiseeCardRyxx.setId(student.getStuId());
					baiseeCardRyxx.setName(student.getStuName());
					baiseeCardRyxx.setPhone(student.getStuMobile());
				}else {//员工
					baiseeCardRyxx.setType("员工");
					Map<String, String > map = new HashMap<String, String>();
					map.put("empId",cardBinding.getSid());
					Employee employee = employeeMapper.selectByEmployeeId(map);
					baiseeCardRyxx.setId(employee.getEmpId());
					baiseeCardRyxx.setName(employee.getEmpName());
					baiseeCardRyxx.setPhone(employee.getEmpMobile());
				}
			}else {
				baiseeCardRyxx.setState("未绑定");
				List<BaiseeStudent> students = baiseeStuMapper.selectByName(baiseeCardRyxx.getRyxm());
				if(students.size()==1) {
					baiseeCardRyxx.setId(students.get(0).getStuId());
					baiseeCardRyxx.setName(students.get(0).getStuName());
					baiseeCardRyxx.setPhone(students.get(0).getStuMobile());
					baiseeCardRyxx.setType("学生");
				}if(students.size()==0||students==null) {//员工
					Map<String, String > map = new HashMap<String, String>();
					map.put("name",baiseeCardRyxx.getRyxm());
					List<Employee> list = employeeMapper.selectByEmployeeName(map);
					if(list.size() == 1) {
						baiseeCardRyxx.setId(list.get(0).getEmpId());
						baiseeCardRyxx.setName(list.get(0).getEmpName());
						baiseeCardRyxx.setPhone(list.get(0).getEmpMobile());
						baiseeCardRyxx.setType("员工");
					}
				}else {//查询不到
					
				}
			}
		}
	
		PageInfo<BaiseeCardRyxx> page = new PageInfo<>(cards);
		return page;
	}

}
