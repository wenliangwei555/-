package cn.baisee.oa.importExcel.filter.validator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import cn.baisee.oa.importExcel.dto.BaseDto;
import cn.baisee.oa.importExcel.dto.CellValidateResultDto;
import cn.baisee.oa.importExcel.dto.RowValidateResultDto;
import cn.baisee.oa.importExcel.filter.AbstractValidator;
import cn.baisee.oa.model.PhoneAndID;
import cn.baisee.oa.service.EmployeeService;
import cn.baisee.oa.service.StudentService;
import cn.baisee.oa.utils.ApplicationContextUtil;
import cn.baisee.oa.utils.StringUtil;

/**
 * 基础性校验 校验身份证和手机号是否为空 和 是否符合规则
 * 
 * @author Administrator
 *
 */
public class CheckMobileAndCertValidator extends
		AbstractValidator<BaseDto, RowValidateResultDto> {

	private static final int[] weightNumber = new int[] { 7, 9, 10, 5, 8, 4, 2,
			1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };

	private static final int[] checknumber = new int[] { 1, 0, 'X', 9, 8, 7, 6,
			5, 4, 3, 2 };

	@Override
	protected RowValidateResultDto validate(BaseDto dto) {
		RowValidateResultDto rvrDto = new RowValidateResultDto();
		rvrDto.setRowMarker(String.valueOf(dto.getRowIndex()));
		rvrDto.addCellValidateResultList(checkIsNull(dto));
		rvrDto.addCellValidateResultList(checkMobile(dto));
		rvrDto.addCellValidateResultList(checkCert(dto));
		return rvrDto;
	}

	@Override
	protected boolean isValid(RowValidateResultDto r) {

		return super.isValid(r);
	}
	/**
	 * 校验手机号的规则情况
	 * @param dto
	 * @return
	 */
	private CellValidateResultDto checkMobile(BaseDto dto) {
		String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
	    String phone = dto.getMobile();
	    StudentService bean = (StudentService) ApplicationContextUtil.getBean(StudentService.class);
	    EmployeeService emp= (EmployeeService) ApplicationContextUtil.getBean(EmployeeService.class);
	    List<PhoneAndID> list2 = emp.getIDAndPhone();
	    List<PhoneAndID> list = bean.readIDAndPhone();
	    if(list2.size()>0){
	    	for (PhoneAndID empphone : list2) {
				if(empphone.getPhone().equals(phone)){
					return new CellValidateResultDto("手机号已经存在!");
				}
			}
	    }
	    if(list.size()>0){
	    	for (PhoneAndID stuphone : list) {
	    		if(stuphone.getPhone().equals(phone)){
	    			return new CellValidateResultDto("手机号已经存在!");
	    		}
				
			}
	    	
	    }
		if (phone.length() != 11) {
	        return new CellValidateResultDto("手机号应该为11位");
	    } else {
	        Pattern p = Pattern.compile(regex);
	        Matcher m = p.matcher(phone);
	        boolean isMatch = m.matches();
	        if (!isMatch) {
	        	return new CellValidateResultDto("数据中存在手机号输入错误的情况");
	        }
	    }
		return null;

	}
	
	/**
	 * 校验是否为空
	 * @param dto
	 * @return
	 */
	private CellValidateResultDto checkIsNull(BaseDto dto) {
		String certNo = dto.getCertNo();
		String mobile = dto.getMobile();
		String name = dto.getName();
		//如果有一个不为空
		if (StringUtils.isNotEmpty(certNo)  || StringUtils.isNotEmpty(mobile) || StringUtils.isNotEmpty(name)) {
			if ( StringUtils.isEmpty(mobile)
					|| StringUtils.isEmpty(name) || StringUtils.isEmpty(certNo)) {
				return new CellValidateResultDto("输入信息时,证件号码、手机号、姓名必填");
			}
		}
		return null;
	}
	
	

	/**
	 * 检查身份证
	 * 
	 * @param dto
	 * @return
	 */
	private CellValidateResultDto checkCert(BaseDto dto) {
		String certNo = dto.getCertNo();
		StudentService sbean = (StudentService) ApplicationContextUtil.getBean(StudentService.class);
		 EmployeeService ebean= (EmployeeService) ApplicationContextUtil.getBean(EmployeeService.class);
		 List<PhoneAndID> list3 = ebean.getIDAndPhone();
		List<PhoneAndID> list1 = sbean.readIDAndPhone();
		if(list3.size()>0){
			for (PhoneAndID empID : list3) {
				if(empID.getID().equals(certNo)){
					return new CellValidateResultDto("身份证已经存在!");
				}
			}
		}
		if(list1.size()>0){
			for (PhoneAndID ID : list1) {
				if(ID.getID().equals(certNo)){
					return new CellValidateResultDto("身份证已经存在!");
				}
				
			}
		}
		if (!checkCard(dto)) {
			return new CellValidateResultDto("数据中存在身份证输入错误的情况");
		}else if(StringUtil.isEmpty(certNo)) {
			return new CellValidateResultDto("身份证不能为空！");
		}
		return null;

	}

	/**
	 * 验证身份证
	 * 
	 * @param dto
	 * @return
	 */
	private boolean checkCard(BaseDto dto) {
		String pCardNo = dto.getCertNo();// 身份证号码
		if (StringUtils.isEmpty(pCardNo)) {
			return true;
		}
		return verify(pCardNo);
	}

	/**
	 * 获取身份证号码中的校验码
	 * 
	 * @param cardNumber
	 * @return
	 */
	private static String getCheckDigit(String cardNumber) {
		int verify = 0;
		cardNumber = cardNumber.substring(0, 17); // 获取身份证号码中的前17位
		int sum = 0;
		int[] wi = new int[17]; // 创建int型数组
		for (int i = 0; i < 17; i++) { // 循环向数组赋值
			String strid = cardNumber.substring(i, i + 1);
			wi[i] = Integer.parseInt(strid);
		}
		for (int i = 0; i < 17; i++) { // 循环遍历数组
			sum = sum + weightNumber[i] * wi[i]; // 对17位本利码加权求和
		}
		verify = sum % 11; // 取模
		if (verify == 2) { // 如果模为2，则返回"X"
			return "X";
		} else {
			return String.valueOf(checknumber[verify]); // 否则返回对应的校验码
		}
	}

	/**
	 * 校验身份证
	 * 
	 * @param cardNumber
	 * @return
	 */
	private static boolean verify(String cardNumber) {
		String regx = "\\d{15}|\\d{17}[\\d,X]";
		if (!cardNumber.matches(regx)) {
			return false;
		}
		if (cardNumber.length() == 15) { // 如果要进行验证的身份证号码为15位
			cardNumber = fiveteenToeighteen(cardNumber); // 将其转换为18位
		}
		if (cardNumber.length() != 18) {
			return false;
		}

		String checkDight = cardNumber.substring(17, 18); // 获取要进行验证身份证号码的校验号
		if (checkDight.equals(getCheckDigit(cardNumber))) { // 判断校验码是否正确
			return true;
		}
		return false;
	}

	/**
	 * 将15位身份证号码转为18位身份证号码
	 * 
	 * @param fifteenNumber
	 * @return
	 */
	private static String fiveteenToeighteen(String fifteenNumber) {
		String eighteenNumberBefore = fifteenNumber.substring(0, 6); // 获取参数身份证号码中的地区码
		String eightNumberAfter = fifteenNumber.substring(6, 15); // 获取参数身份证号码中的出生日期码
		String eighteenNumber;
		eighteenNumber = eighteenNumberBefore + "19"; // 将地区码后面加"19"
		eighteenNumber = eighteenNumber + eightNumberAfter; // 获取地区码加出生日期码
		eighteenNumber = eighteenNumber + getCheckDigit(eighteenNumber);// 获取身份证的校验码
		return eighteenNumber; // 将转换后的身份证号码返回
	}
}
