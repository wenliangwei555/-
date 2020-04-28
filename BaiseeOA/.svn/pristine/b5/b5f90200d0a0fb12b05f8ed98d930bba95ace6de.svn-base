package cn.baisee.oa.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.model.PhoneAndID;
import cn.baisee.oa.model.empinfo.Employee;
import cn.baisee.oa.model.empinfo.EmployeeEducation;
import cn.baisee.oa.model.empinfo.EmployeeFamily;
import cn.baisee.oa.model.empinfo.EmployeeSupplement;
import cn.baisee.oa.model.empinfo.EmployeeWork;
import cn.baisee.oa.page.pagehelper.PageInfo;

/**
 * 员工servce接口类
 * 
 * @author LiFQ
 * 
 */
public interface EmployeeService {

	/**
	 * 分页显示员工表里面的信息
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<Employee> selectEmployeeList(int pageNum, int pageSize,
			String str,String status,String empJob,String empDept,String gender);

	/************************************* 查询 **************************************/
	/**
	 * 查询员工补充表的信息
	 * 
	 * @param eid
	 * @return
	 */
	public EmployeeSupplement selectByEmployeeIdQuerySupplement(String eid);

	/**
	 * 查询员工教育信息
	 * 
	 * @param eid
	 * @return
	 */
	public EmployeeEducation selectByEmployeeIdQueryEducation(String eid);

	/**
	 * 查询员工作履历信息
	 * 
	 * @param eid
	 * @return
	 */
	public EmployeeWork selectByEmployeeIdQueryWork(String eid);

	/**
	 * 查询员工家庭信息
	 * 
	 * @param eid
	 * @return
	 */
	public EmployeeFamily selectByEmployeeIdQueryFamily(String eid);

	/**
	 * 根据id获取一条员工基本表的信息
	 * 
	 * @param eid
	 * @return
	 */
	public Employee selectEmployeeById(String eid);

	/********************************************** 添加 ****************************************************/
	/**
	 * 添加员工基本表的信息
	 * 
	 * @param employee
	 * @return
	 */
	public int addEmployee(Employee employee);

	/**
	 * 添加员工补充表信息
	 * 
	 * @param employeeSupplement
	 * @return
	 */
	public int addEmployeeSupplement(EmployeeSupplement employeeSupplement);

	/**
	 * 添加员工教育表信息
	 * 
	 * @param education
	 * @return
	 */
	public int addEmployeeEducation(EmployeeEducation education);

	/**
	 * 添加员工工作经历表
	 * 
	 * @param work
	 * @return
	 */
	public int addEmployeeWork(EmployeeWork work);

	/**
	 * 添加员工家庭信息表
	 * 
	 * @param family
	 * @return
	 */
	public int addEmployeeFamily(EmployeeFamily family);

	/********************************************** 修改 ********************************************************/
	/**
	 * 修改员工信息
	 * 
	 * @param employee
	 * @return
	 */
	public int updateEmployeeByID(Employee employee);

	/**
	 * 修改员工补充表信息
	 * 
	 * @param employeeSupplement
	 * @return
	 */
	public int updateEmployeeSupByID(EmployeeSupplement employeeSupplement);

	/**
	 * 修改员工教育信息
	 * 
	 * @param education
	 * @return
	 */
	public int updateEmployeeEduByID(EmployeeEducation education);

	/**
	 * 修改员工作经历表
	 * 
	 * @param work
	 * @return
	 */
	public int updateEmployeeWorkByID(EmployeeWork work);

	/**
	 * 修改员工家庭信息表
	 * 
	 * @param family
	 * @return
	 */
	public int updateEmployeeFamByID(EmployeeFamily family);
	/*****************************校验手机号************************************************/
	/**
	 * 校验身份证号
	 * 
	 * @param empIdNumber
	 * @return
	 */
	public String selectEmployeeIDNumberVerification(Employee emp);
	/**
	 * 校验手机号
	 * @param mobile
	 * @return
	 */
	 String selectEmployeeMobileVerification(Employee emp);

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	 int deleteCheckEmployeeAll(List<String> list);
	
	/**
	 * 去员工分页显示的list页面
	 * @param request
	 * @param response
	 * @return
	 */
	 ModelAndView employeeListPage(HttpServletRequest request, HttpServletResponse response );

	/**
	 * 员工添加修改方法
	 * 
	 * @param empHeadPhotoDir
	 * @param employee
	 * @param supplement
	 * @param education
	 * @param work
	 * @param family
	 * @param request
	 * @return
	 */
	 ModelAndView employeeAddAndUpdatePage(
			MultipartFile empHeadPhotoDir, Employee employee,
			EmployeeSupplement supplement, EmployeeEducation education,
			EmployeeWork work, EmployeeFamily family, HttpServletRequest request);

	/**
	 * 员工添加修页面跳转
	 * 
	 * @param request
	 * @return
	 */
	 ModelAndView employeePageJump(HttpServletRequest request);
	
	/**
	 * 修改员工的状态，在分页显示上面的功能按钮
	 * @param empId
	 * @return
	 */
	 int updateEmployeeStatus(Employee employee);
	/**
	 * 读取员工的手机号和身份证
	 * @return
	 */
	
	List<PhoneAndID> getIDAndPhone();
	/**
	 * 批量导入员工信息显示的结果集
	 * @param inputStream
	 * @return
	 */
	Map<String, Object> doService1(InputStream inputStream);
	
	/**
	 * 查询所有的在职员工
	 * @return
	 */
	 List<Employee> selectAllEmployees();
	
	/**
	 *  根据用户类型查询对应的员工列表 
	 * @return
	 */
	 List<Employee> selectEmpByType(String type);
	
	/**
	 * 根据员工职位名称查询员工集合
	 * @param roleName	职位名称
	 * @return	员工集合
	 */
	 List<Employee> findDeptAndRoleEmp(String roleName);
}
