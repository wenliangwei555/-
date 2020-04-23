package cn.baisee.oa.thymeleaf.dataProcessor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.Assert;
import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;
import org.thymeleaf.processor.attr.AbstractTextChildModifierAttrProcessor;

import cn.baisee.oa.dao.baisee.BaiseeEmployeeMapper;
import cn.baisee.oa.dao.baisee.BaiseeStuMapper;
import cn.baisee.oa.model.empinfo.Employee;
import cn.baisee.oa.model.student.BaiseeStudent;
import cn.baisee.oa.thymeleaf.utils.OaDialectUtil;
import cn.baisee.oa.utils.ApplicationContextUtil;
import cn.baisee.oa.utils.StringUtil;

public class PersonNameProcessor extends AbstractTextChildModifierAttrProcessor {
	
	private static final String STUDENT_TYPE = "student";
	private static final String PERSON_TYPE = "person";
	public PersonNameProcessor() {
		this("name");
	}

	protected PersonNameProcessor(String attributeName) {
		super(attributeName);
	}

	@Override
	protected String getText(Arguments arguments, Element element, String attr) {
		String name = "";
		String personId = "";
		String personType = "";
		String value = element.getAttributeValue(attr);
		if(value.indexOf(",") > -1) {
			String[] array = value.split(",");
			personId = OaDialectUtil.getValue(arguments, array[0].trim());
			Assert.hasLength(personId, "人员编号不能为空");
			personType = array[1];
			Assert.hasLength(personType, "人员类型不能为空");
		} else {
			personId = OaDialectUtil.getValue(arguments, value.trim());
			Assert.hasLength(personId, "人员编号不能为空");
			personType = STUDENT_TYPE;
		}
		
	    switch (personType) {
		case STUDENT_TYPE:
			BaiseeStudent student = null;
			BaiseeStuMapper baiseeStuMapper = (BaiseeStuMapper)ApplicationContextUtil.getBean(BaiseeStuMapper.class);
			try {
				student = baiseeStuMapper.selectByStudentId(personId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(student!=null && !StringUtil.isEmpty(student.getStuName())) {
				name = student.getStuName();
			}else {
				name = "——— ———";
			}
			break;
		case PERSON_TYPE:
			BaiseeEmployeeMapper baiseeEmployeeMapper = (BaiseeEmployeeMapper)ApplicationContextUtil.getBean(BaiseeEmployeeMapper.class);
			Map<String, String> map = new HashMap<String, String>();
			map.put("empId", personId);
			Employee emp = baiseeEmployeeMapper.selectByEmployeeId(map);
			if(emp!=null && !StringUtil.isEmpty(emp.getEmpName())) {
				name = emp.getEmpName();
			}else {
				name = "——— ———";
			}
			break;
		}
		return name;
	}

	@Override
	public int getPrecedence() {
		return 0;
	}

}
