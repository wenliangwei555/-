package cn.baisee.oa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.dao.baisee.BaiseeDicMapper;
import cn.baisee.oa.dao.baisee.BaiseeRoleMapper;
import cn.baisee.oa.dao.baisee.BaiseeUserMapper;
import cn.baisee.oa.exception.OAServiceException;
import cn.baisee.oa.model.BaiseeRole;
import cn.baisee.oa.model.BaiseeUser;
import cn.baisee.oa.model.IcipBusDict;
import cn.baisee.oa.model.empinfo.Employee;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.UserService;
import cn.baisee.oa.utils.EncryptUtil;
import cn.baisee.oa.utils.ParamUtils;
import cn.baisee.oa.utils.SessionUtil;
import cn.baisee.oa.utils.StringUtil;
 
@Service
public class UserServiceImpl implements  UserService{
	 
	private static final Log logger = LogFactory.getLog(UserServiceImpl.class);
	@Autowired
	private BaiseeUserMapper baiseeUserMapper;
	@Autowired
	private BaiseeRoleMapper baiseeRoleMapper;
	//字典 mapper LiFQ
	@Autowired
	private BaiseeDicMapper baiseeDicMapper;
	
	
	public void updateUser(BaiseeUser user) throws Exception {
		
		baiseeUserMapper.updateByPrimaryKey(user);
	}
	
	
	public BaiseeUser getUserInfo(String userName) throws Exception {
		return baiseeUserMapper.selectByLoginName(userName);
	}
	
	
	public BaiseeUser getUserById(String userId) throws Exception {
		BaiseeUser user = null;
		try {
			user = baiseeUserMapper.selectByPrimaryKey(userId);
		} catch (Exception e) {
			 
		}
		return user;
	}
	
	public boolean isUserPwdCorrect(BaiseeUser user, String password)
			throws Exception {
		String secretKey = EncryptUtil.encryptMd5(password);
		if (user.getLoginPwd().equals(secretKey)) {
			return true;
		}
		return false;
	}


	@Override
	public PageInfo<BaiseeUser> selectBaiseeUserList(int pageNum, int pageSize,
			String userName) {
		PageHelper.startPage(pageNum, pageSize);// 开始分页
		Map<String, String> map = new HashMap<String, String>();
		map.put("userName", userName);
		List<BaiseeUser> list = baiseeUserMapper.selectUserAll(map);
		
		PageInfo<BaiseeUser> pageInfo = new PageInfo<BaiseeUser>(list);
		
		return pageInfo;
	}


	@Override
	public int addUser(BaiseeUser user) {
		
		return baiseeUserMapper.addUser(user);
	}


	@Override
	public int updateUserByID(BaiseeUser user) {
		//先把原来的权限删除，在加入角色
		return baiseeUserMapper.updateUserByID(user);
	}


	@Override
	public List<BaiseeUser> selectUserToDelete(String[] ids) {
		return baiseeUserMapper.selectUserToDelete(ids);
	}


	@Override
	public int deleteCheckUser(String[] ids) {
		List<BaiseeUser> list = selectUserToDelete(ids);
		List<String> idsList = new ArrayList<String>();
		for (BaiseeUser baiseeUser : list) {
			idsList.add(baiseeUser.getUserId());
		}
		if(idsList != null && idsList.size()>0){
			//删除role_user中的数据
			baiseeRoleMapper.deleteByUserIds(idsList);
			return baiseeUserMapper.deleteUserToCheck(idsList);
		}else{
			return 0;
		}
	}


	@Override
	public List<Employee> selectUserNames() {
		return baiseeUserMapper.selectUserNames();
	}


	@Override
	public BaiseeUser verificationEmpID(String userId) {
		return baiseeUserMapper.VerificationEmpID(userId);
	}




	@Override
	public BaiseeUser verificationLoginName(BaiseeUser user) {
		return baiseeUserMapper.VerificationLoginName(user);
	}




	@Override
	public int deleteUserOfRole(Map<String, String> map) {
		return baiseeUserMapper.deleteUserOfRole(map);
	}


	@Override
	public List<BaiseeRole> selectRoleNameByUserId(Map<String, String> map) {
		return baiseeUserMapper.selectRoleNameByUserId(map);
	}


	@Override
	public Employee selectEmployeeByID(String empId) {
		return baiseeUserMapper.selectEmployeeByID(empId);
	}
	
	
	/********************************controller类跳转页面方法********************************/
	
	//查询角色
	@Override
	public List<BaiseeRole> selectRolesByUserId(String userId) throws OAServiceException {
		List<BaiseeRole> roles = new ArrayList<BaiseeRole>();
		List<String> lists = new ArrayList<String>();
		lists.add(userId);
		roles = baiseeRoleMapper.selectAllByCreateUser();
		return roles;
	}
	//保存
	@Override
	public void saveRoleInfo(String userId, String roleId)
			throws OAServiceException {
		 Map<String, String> map = new HashMap<String, String>();
		 map.put("userId", userId);
		 map.put("roleId", roleId);
		 baiseeRoleMapper.insertRoleUser(map);
	}
	
	/**
	 *   分页显示用户数据
	 * @return
	 */
	public ModelAndView UserListInfo(HttpServletRequest request) {
		if(request.getSession().getAttribute("userId")!=null){
			request.getSession().removeAttribute("userId");
		}
		String itemlableSearch = "";
		String str = ParamUtils.getParameter(request, "itemlableSearch");//前台传过来的查询值
		if(str!=null){
			itemlableSearch = str;
		}
		Integer pageNum = ParamUtils.getPageNumParameters(request);
		Integer pageSize = ParamUtils.getPageSizeParameters(request);
		PageInfo<BaiseeUser> pageInfo = selectBaiseeUserList(pageNum, pageSize, itemlableSearch);
		ModelAndView model = new ModelAndView();
		model.addObject("itemlableSearch", itemlableSearch);
		model.addObject("pageResult", pageInfo);
		model.setViewName("user/user_list");
		return model;
	}
	//前往用户添加页面
	public ModelAndView UserAddPage(HttpServletRequest request) throws OAServiceException {
		ModelAndView model = new ModelAndView();
		List<Employee> listName = selectUserNames();
		//TODO 获取当前登录人的权限信息
		List<BaiseeRole> roles = new ArrayList<BaiseeRole>();
		//查询字典中值
		roles = selectRolesByUserId(SessionUtil.getUserInfo(request).getUserId());
		model.addObject("roles", roles);
		model.addObject("listName", listName);
		model.addObject("baiseeUser", new BaiseeUser());
		model.setViewName("user/userEdit");
		return model;
	}

	//前往用户修改页面
	@Override
	public ModelAndView UserUpdatePage(HttpServletRequest request) throws OAServiceException {
		String userId = ParamUtils.getParameter(request, "userId");
		BaiseeUser baiseeUser;
		try {
			baiseeUser = getUserById(userId);
		} catch (Exception e) {
			throw new OAServiceException("修改用户方法，查询用户失败", e);
		}
		//request.getSession().setAttribute("userId", baiseeUser.getUserId());
		//TODO 获取当前登录人的权限信息
		List<BaiseeRole> roles = new ArrayList<BaiseeRole>();
		try {
			roles = selectRolesByUserId(SessionUtil.getUserInfo(request).getUserId());
		} catch (OAServiceException e) {
			throw new OAServiceException("修改用户方法，查询所有角色方法失败", e);
		}
		//前往修改页面的时候查询这个用的角色名
		Map<String, String> map = new HashMap<String, String>();
		map.put("empId", baiseeUser.getUserId());
		List<BaiseeRole> list = selectRoleNameByUserId(map);//将修改用户的角色名称放在作用域里面
		
		ModelAndView model = new ModelAndView();
		if(list.size()>0) {
			logger.debug("即将修改用户的角色名称："+list.get(0).getRoleId());
			model.addObject("rName", list.get(0));
			model.addObject("rId", list.get(0).getRoleId());
		}else {
			model.addObject("rId", "");
			model.addObject("rName", "");
		}
		model.addObject("roles", roles);
		model.addObject("baiseeUser", baiseeUser);
		model.setViewName("user/userEditUpdate");
		return model;
	}

	//添加或者修改方法
	@Override
	public ModelAndView UserAddOrUpdateMethod(HttpServletRequest request, BaiseeUser baiseeUser) throws OAServiceException {
		ModelAndView model = new ModelAndView();
		String roleName = ParamUtils.getParameter(request, "roleName");
		if(baiseeUser.getLastUpdateTime() != "" && baiseeUser.getLastUpdateTime() != null && baiseeUser.getLastUpdateTime().length() > 0){//表示是修改
			//修改用户
			Map<String, String> map = new HashMap<String, String>();
			map.put("empId", baiseeUser.getUserId());
			deleteUserOfRole(map);//删除用户对应角色
			updateUserByID(baiseeUser);//修改用户的信息
			BaiseeUser user = null;
			try {
				saveRoleInfo(baiseeUser.getUserId(), roleName);//添加用户的信息
				user = getUserById(baiseeUser.getUserId());
			} catch (OAServiceException e) {
				throw new OAServiceException("保存用户的信息失败", e);
			} catch (Exception e) {
				throw new OAServiceException("查询用户的信息失败", e);
			}
			
			List<BaiseeRole> list = selectRoleNameByUserId(map);//将修改用户的角色名称放在作用域里面
			if(list.size()>0) {
				model.addObject("rId", list.get(0).getRoleId());
				model.addObject("rName", list.get(0).getRoleName());
			}else {
				model.addObject("rId", "");
				model.addObject("rName", "");
			}
			
			//TODO 获取当前登录人的权限信息
			List<BaiseeRole> roles = new ArrayList<BaiseeRole>();
			try {
				roles = selectRolesByUserId(SessionUtil.getUserInfo(request).getUserId());
			} catch (OAServiceException e) {
				e.printStackTrace();
			}
			model.addObject("roles", roles);//将所有的角色放到作用于里面 
			model.addObject("baiseeUser", user);
			model.addObject("operationSuccess", "操作成功");//添加操作成功的提示信息
			model.setViewName("user/userEditUpdate");
			return UserListInfo(request);
		}else{//表示是添加
			//baiseeUser.setUserType("1");//设置user的类型Type
			baiseeUser.setCanDelete("0");//设置该用户是否可以删除
			Employee emp = selectEmployeeByID(baiseeUser.getUserId());
			baiseeUser.setStatus(emp.getEmpStatus());
			baiseeUser.setUserName(emp.getEmpName());
			if("2".equals(emp.getEmpDept())){
				baiseeUser.setUserType(null);
			}else{
				baiseeUser.setUserType(emp.getEmpDept());
			}
			try {
				baiseeUser.setLoginPwd(EncryptUtil.encryptMd5("password"));
			} catch (Exception e) {
				throw new OAServiceException("保存用户时，加密密码失败", e);
			}
			addUser(baiseeUser);
			//TODO 增加该用户权限信息
			saveRoleInfo(baiseeUser.getUserId(), roleName);
			return UserListInfo(request);
		}
	}

	//全选删除
	@Override
	public ModelAndView UserCheckDelete(HttpServletRequest request) {
		String[] ids = ParamUtils.getParameters(request, "ids");
		deleteCheckUser(ids);
		return UserListInfo(request);
	}
	

	//   修改用户密码      先判断  旧的密码是否一致     再判断新的密码是否跟旧的密码一致   再判断两次密码是否一致
	@Override
	public String updateChanagePwd(HttpServletRequest request) {
			try {
				String oldPassword = ParamUtils.getParameter(request, "oldPassword");
				String newPassword = ParamUtils.getParameter(request, "newPassword");
				BaiseeUser user = (BaiseeUser)SessionUtil.getUserInfo(request);
				oldPassword = EncryptUtil.encryptMd5(oldPassword);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("oldPassword", oldPassword);
				map.put("userId", user.getUserId());
				BaiseeUser baissUser = baiseeUserMapper.selectUserByPwd(map);
				
				if(baissUser == null) {
					// 密码输入错误
					return "1";
					
				}else if(EncryptUtil.encryptMd5(newPassword).equalsIgnoreCase(baissUser.getLoginPwd())) {
					//判断新的密码是否跟旧的密码一致
					return "2";
				}else {
					
					// 然后就修改密码
					Map<String, String> map2 = new HashMap<String, String>();
					map2.put("newPassword", EncryptUtil.encryptMd5(newPassword));
					map2.put("userId", user.getUserId());
					
					Integer id = baiseeUserMapper.updateUserPwdById( map2);
					if(id == null || id <= 0) {
						// 修改失败
						return "3";
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			return "4";
	}


	@Override
	public Integer deleteRoleUserById(String empId) {
		return baiseeUserMapper.deleteRoleUserById(empId);
	}


	@Override
	public List<BaiseeUser> selectUserAll(String userType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userType", userType);
		return baiseeUserMapper.selectUserAllByType( map);
	}
	
	@Override
	public List<BaiseeUser> findUsersByRoleId(String roleId) {
		return baiseeUserMapper.findUsersByRoleId(roleId);
	}


	@Override
	public List<BaiseeUser> selectUserAll() {
		// TODO Auto-generated method stub
		return baiseeUserMapper.selectUserList();
	}
	
}
