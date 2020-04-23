package cn.baisee.oa.dao.baisee;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.ImportEmpModel;
import cn.baisee.oa.model.PhoneAndID;
import cn.baisee.oa.model.empinfo.Employee;
import cn.baisee.oa.model.empinfo.EmployeeEducation;
import cn.baisee.oa.model.empinfo.EmployeeFamily;
import cn.baisee.oa.model.empinfo.EmployeeSupplement;
import cn.baisee.oa.model.empinfo.EmployeeWork;

/**
 * 员工mapper接口类
 * 
 * @author LiFQ
 * 
 */
@Datasource(DatasourceTypes.OA)
public interface BaiseeEmployeeMapper {
	/**
	 * 获取某个员工的信息
	 * 
	 * @param eid 员工用户ID
	 * @return  员工表基本信息
	 */
	public Employee selectByEmployeeId(Map<String, String> map);
	/**
	 * 获取某个员工的信息
	 * @param name
	 * @return
	 */
	public List<Employee> selectByEmployeeName(Map<String, String> map);

	/**
	 * 获取某个员工的所有家庭信息
	 * 
	 * @param eid 员工用户ID
	 * @return 员工家庭信息
	 */
	public EmployeeFamily selectByEmployeeIdQueryFamily(Map<String, String> map);

	/**
	 * 获取某个员工的工作经历信息
	 * 
	 * @param eid 员工用户ID
	 * @return 员工工作经历表信息
	 */
	public EmployeeWork selectByEmployeeIdQueryWork(Map<String, String> map);

	/**
	 * 获取某个员工共的教育信息
	 * 
	 * @param eid 员工用户ID
	 * @return  员工教育表信息
	 */
	public EmployeeEducation selectByEmployeeIdQueryEducation(Map<String, String> map);

	/**
	 * 获取某个员工的补充信息
	 * 
	 * @param eid 员工用户ID
	 * @return  员工补充表信息
	 */ 
	public EmployeeSupplement selectByEmployeeIdQuerySupplement(Map<String, String> map);

	/**
	 * 获取集合数据
	 * @param empName 根据姓名查询
	 * @return  获取员工集合数据
	 */
	 List<Employee> selectEmployeePage(Map<String, String> map);

	/**
	 * 添加员工信息
	 * 
	 * @param employee 员工一条基本信息数据
	 * @return  数据库添加之后受影响行数
	 */
	public int addEmployee(Employee employee);

	/**
	 * 修改员工信息
	 * 
	 * @param employee 员工一条基本信息数据
	 * @return 数据库操作之后返回受影响行数
	 */
	public int updateEmployeeByID(Employee employee);

	/**
	 * 修改员工补充表信息
	 * 
	 * @param employeeSupplement 员工补充表信息
	 * @return 数据库操作之后返回受影响行数
	 */
	public int updateEmployeeSupByID(EmployeeSupplement employeeSupplement);

	/**
	 * 添加员工补充表信息
	 * 
	 * @param supplement 员工一条补充表信息
	 * @return 数据库操作之后返回受影响行数
	 */
	public int addEmployeeSupplement(EmployeeSupplement supplement);

	/**
	 * 修改员工教育信息
	 * 
	 * @param education 员工一条教育信息
	 * @return 数据库操作之后返回受影响行数
	 */
	public int updateEmployeeEduByID(EmployeeEducation education);

	/**
	 * 添加员工教育表信息
	 * 
	 * @param education 员工一条教育信息
	 * @return 数据库操作之后返回受影响行数
	 */
	public int addEmployeeEducation(EmployeeEducation education);

	/**
	 * 添加员工工作经历表
	 * 
	 * @param work 员工一条工作履历信息
	 * @return 数据库操作之后返回受影响行数
	 */
	public int addEmployeeWork(EmployeeWork work);

	/**
	 * 修改员工工作经历表
	 * 
	 * @param work 员工一条工作履历信息
	 * @return 数据库操作之后返回受影响行数
	 */
	public int updateEmployeeWorkByID(EmployeeWork work);

	/**
	 * 修改员工家庭信息表
	 * 
	 * @param family 员工一条家庭信息
	 * @return 数据库操作之后返回受影响行数
	 */
	public int updateEmployeeFamByID(EmployeeFamily family);

	/**
	 * 添加员工家庭信息表
	 * 
	 * @param family 员工一条家庭信息
	 * @return 数据库操作之后返回受影响行数
	 */
	public int addEmployeeFamily(EmployeeFamily family);
	/*******************************校验手机号、身份证**************************************************************/
	/**
	 * 校验身份证号
	 * 
	 * @param empIdNumber 页面传过来的身份证号码
	 * @return 数据库操作之后返回受影响行数
	 */
	public String selectEmployeeIDNumberVerification(Employee emp);
	
	/**
	 * 校验手机号
	 * @param Mobile 页面输入的手机号
	 * @return 
	 */
	public String selectEmployeeMobileVerification(Employee emp);
	
	/******************************批量删除**************************************/
	/**
	 * 批量删除
	 * 员工表
	 * @param ids 数组（员工的empid）
	 * @return 数据库操作之后返回受影响行数
	 */
	public int deleteCheckEmployee(List<String> list);
	/**
	 * 批量删除员工补充表
	 * @param ids 数组（员工的empid）
	 * @return 数据库操作之后返回受影响行数
	 */
	public int deleteCheckEmployeeSupplement(List<String> list);
	/**
	 * 批量删除员工教育表
	 * @param ids 数组（员工的empid）
	 * @return 数据库操作之后返回受影响行数
	 */
	public int deleteCheckEmployeeEducation(List<String> list);
	/**
	 * 批量删除员工家庭表信息
	 * @param ids 数组（员工的empid）
	 * @return 数据库操作之后返回受影响行数
	 */
	public int deleteCheckEmployeeFamily(List<String> list);
	/**
	 * 批量删除员工工作履历信息
	 * @param ids 数组（员工的empid）
	 * @return 数据库操作之后返回受影响行数
	 */
	public int deleteCheckEmployeeWork(List<String> list);
	/**
	 * 修改员工的在职或者离职状态
	 * @param empId
	 * @return
	 */
	 int updateEmployeeStatus(Employee employee);
	
	/**
	 *  删除员工的角色
	 * @param map
	 * @return
	 */
	public int deleteEmployeeRole(Employee employee);
	/**
	 * 获得员工的手机号和身份证
	 * @return
	 */
	List<PhoneAndID> selectPhoneAndID();
	/**
	 * 批量导入员工信息
	 */
	
	void insetImportExcel(List<ImportEmpModel> list);
	/**
	 * 根据员工ID查询对应的员工名字
	 * @param empID
	 * @return
	 */
	String getEmpName(String empID);
	/**
	 * 根据用户类型查询对应的员工列表 
	 * @param type
	 * @return
	 */
	List<Employee> selectEmpByType(Map<String, Object> map);
	
	/**
	 * 根据职位名称查询员工集合
	 * @param roleName	职位名称
	 * @return	员工集合
	 */
	List<Employee> findDeptAndRoleEmp(Map<String, Object> map);

	/**
	 * 查询所有在职的员工
	 * @return 
	 * liangfeng
	 */
	List<Employee> selectEmps();
}
