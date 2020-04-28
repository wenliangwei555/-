package cn.baisee.oa.dao.baisee;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.importExcel.dto.BaseDto;
import cn.baisee.oa.model.BaiseeRole;
import cn.baisee.oa.model.BaiseeUser;
import cn.baisee.oa.model.empinfo.Employee;

@Datasource(DatasourceTypes.OA)
public interface BaiseeUserMapper {

	/**
	 * 根据用户ID查询用户信息
	 * 
	 * @param userId
	 *            用户ID
	 * @return 对应的用户信息实体
	 */
	BaiseeUser selectByPrimaryKey(String userId);

	/**
	 * 根据用户登录名查询用户信息
	 * 
	 * @param loginName
	 *            用户登录名
	 * @return 对应的用户信息实体
	 */
	BaiseeUser selectByLoginName(String loginName);
	
	/**
	 * 获取用户集合数据
	 * @param map
	 * @return
	 */
	List<BaiseeUser> selectAll(Map<String, Object> map);
	
	
	int updateByPrimaryKey(BaiseeUser user);


	void bashInsert(List<BaseDto> list);

	/**
	 * 
	 * @param userName 页面传过来的查询值
	 * @return 用户集合数据
	 */
	List<BaiseeUser> selectUserAll(Map<String, String> map);
	/**
	 * 添加用户
	 * @param user 页面穿过来的实体类
	 * @return 数据库操作受影响行数
	 */
	public int addUser(BaiseeUser user);
	/**
	 * 添加该用户的登录权限
	 * @param map
	 * @return
	 */
	public int addUserLoginJurisdiction(Map<String, String> map);
	
	
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	public int updateUserByID(BaiseeUser user);
	/**
	 * 查询可以删除的用户
	 * @return 
	 */
	public List<BaiseeUser> selectUserToDelete(String[] ids);
	/**
	 * 批量删除，删除可以删除的用户
	 * @param idsList
	 * @return
	 */
	public int deleteUserToCheck(List<String> idsList);
	/**
	 * 全选删除角色用户表的权限
	 * @param list
	 * @return
	 */
	public int deleteUserToCheckOfJurisdiction(List<String> list);
	
	/**
	 * 获取所有的员工姓名方便在选择用户时候进行回显
	 * @return 所有员工的基本数据
	 */
	public List<Employee> selectUserNames();
	
	/**
	 * 校验数据库中有没有此用户
	 * @param userId
	 * @return
	 */
	public BaiseeUser VerificationEmpID(String userId);
	/**
	 * 查询选择的此用户的信息
	 * @param empId
	 * @return
	 */
	public Employee selectEmployeeByID(String empId);
	
	/**
	 * 校验数据库中有没有此登录名称
	 * @param loginName
	 * @return
	 */
	public BaiseeUser VerificationLoginName(BaiseeUser user);
	/**
	 * 删除某个用户的角色权限
	 * @return
	 */
	public int deleteUserOfRole(Map<String, String> map);
	
	/**
	 * 查询用户的角色名称和ID
	 * @param map
	 * @return
	 */
	public List<BaiseeRole> selectRoleNameByUserId(Map<String, String> map);
	
	/**
	 *  查询要修改的原始密码是否正确
	 * @param oldPassword
	 * @param userId
	 * @return
	 */
	BaiseeUser selectUserByPwd(Map<String, Object> map );
	
	/**
	 *	根据Id修改用户密码 
	 * @param encryptMd5
	 * @param userId
	 * @return
	 */
	Integer updateUserPwdById(Map<String, String> map);
	
	/**
	 * 根据员工id删除用户角色关联表里面信息
	 * @param empId
	 * @return
	 */
	public Integer deleteRoleUserById(@Param("empId")String empId);
	/**
	 *  创建自定义Id
	 * @param prefix
	 * @return
	 */
	String createId(String prefix);
	
	List<BaiseeUser> statusUserType(Map<String, Object> map);
	/**
	 *  根据类型查询所有教师
	 * @param userType
	 * @return
	 */
	List<BaiseeUser> selectUserAllByType(Map<String, Object> map);
	
	/**
	 * 根据角色的id，查询该角色下的所有用户 
	 * @param roleId	角色id
	 * @return
	 * @author liangfeng
	 */
	List<BaiseeUser> findUsersByRoleId(String roleId);
	
	/**
	 * 根据权限角色ID查询所拥有这个角色的人
	 * @param roleID
	 * @return
	 */
	String[] selectUserByRoleId(String roleID);

	/**
	 * 获取所有用户
	 * @return
	 */
	List<BaiseeUser> selectUserList();
}
