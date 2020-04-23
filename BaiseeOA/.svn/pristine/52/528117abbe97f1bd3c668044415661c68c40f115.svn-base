package cn.baisee.oa.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.exception.OAServiceException;
import cn.baisee.oa.model.BaiseeRole;
import cn.baisee.oa.model.BaiseeUser;
import cn.baisee.oa.model.empinfo.Employee;
import cn.baisee.oa.page.pagehelper.PageInfo;

public interface UserService {
	public void updateUser(BaiseeUser user)  throws Exception;
	
	public BaiseeUser getUserInfo(String userName) throws Exception;
	
	public BaiseeUser getUserById(String userId) throws Exception;
	
	public boolean isUserPwdCorrect(BaiseeUser user, String password) throws Exception ;
	/**
	 * 
	 * @param pageNum 当前页
	 * @param pageSize 每页显示数据数量
	 * @param empName 页面上面查询
	 * @return
	 */
	public PageInfo<BaiseeUser> selectBaiseeUserList(int pageNum, int pageSize ,String empName);
	
	/**
	 * 添加用户
	 * @param user 页面传过来的实体类信息
	 * @return 数据库操作受影响行数
	 */
	public int addUser(BaiseeUser user);
	
	/**
	 * 修改一条用户
	 * @param user
	 * @return
	 */
	public int updateUserByID(BaiseeUser user);
	/**
	 * 查询可以删除的用户
	 * @param ids
	 * @return
	 */
	public List<BaiseeUser> selectUserToDelete(String[] ids);
	/**
	 * 全选删除方法
	 * @param ids
	 * @return
	 */
	public int deleteCheckUser(String[] ids);
	
	/**
	 * 获取所有的员工信息
	 * @return
	 */
	public List<Employee> selectUserNames();
	
	/**
	 *校验数据库中有没有此员工 
	 * @param userId
	 * @return
	 */
	public BaiseeUser verificationEmpID(String userId);
	/**
	 * 查询某条员工的信息,在新增用户时使用
	 * @param empId
	 * @return
	 */
	public Employee selectEmployeeByID(String empId);
	
	
	/**
	 * 校验数据库中有没有此登录名称
	 * @param loginName
	 * @return
	 */
	public BaiseeUser verificationLoginName(BaiseeUser user);
	
	/**
	 * 删除某条用户的角色权限
	 * @return
	 */
	public int deleteUserOfRole(Map<String, String> map);
	
	/**
	 * 查询用户的角色名称
	 * @param map
	 * @return
	 */
	public List<BaiseeRole> selectRoleNameByUserId(Map<String, String> map);
	/*********************************controller类里调用页面跳转方法*******************************************/
	/**
	 * 查询所有的角色信息
	 * @param userId
	 * @return
	 * @throws OAServiceException
	 */
	public List<BaiseeRole> selectRolesByUserId(String userId) throws OAServiceException;
	/**
	 * 保存用户角色
	 * @param userId
	 * @param roleName
	 * @throws OAServiceException
	 */
	public void saveRoleInfo(String userId, String roleName)  throws OAServiceException;
	/***
	 * 分页显示user
	 * @param request
	 * @return
	 */
	public ModelAndView UserListInfo(HttpServletRequest request);
	/**
	 * 前往用户添加页面
	 * @param request
	 * @return
	 */
	public ModelAndView UserAddPage(HttpServletRequest request) throws OAServiceException;
	
	/**
	 * 前往员工修改页面
	 * @param request
	 * @return
	 */
	public ModelAndView UserUpdatePage(HttpServletRequest request) throws OAServiceException;
	
	/**
	 * 员工或者修改方法
	 * @param request
	 * @param baiseeUser
	 * @return
	 */
	public ModelAndView UserAddOrUpdateMethod(HttpServletRequest request,BaiseeUser baiseeUser) throws OAServiceException;
	/**
	 * 全选删除
	 * @param request
	 * @return
	 */
	public ModelAndView UserCheckDelete(HttpServletRequest request);


	/**
	 *  修改用户登录密码
	 * @param oldPassword
	 * @param newPassword
	 * @param userId 
	 * @return
	 */
	public String updateChanagePwd(HttpServletRequest request);
	/**
	 * 根据员工id删除用户角色关联表里面信息
	 * @param empId
	 * @return
	 */
	public Integer deleteRoleUserById(@Param("empId")String empId);
	
	/**
	 * 根据类型查询所有的教师 
	 * @param userType
	 * @return
	 */
	public List<BaiseeUser> selectUserAll(String userType);
	/**
	 * 获取所有用户
	 * @return
	 */
	public List<BaiseeUser> selectUserAll();
	/**
	 * 根据角色的id，查询该角色下的所有用户 
	 * @param roleId	角色id
	 * @return
	 * @author liangfeng
	 */
	public List<BaiseeUser> findUsersByRoleId(String roleId);
	
}
