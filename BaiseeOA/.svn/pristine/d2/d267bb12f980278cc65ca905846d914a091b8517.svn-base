package cn.baisee.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.constants.AppConstants;
import cn.baisee.oa.constants.CacheKeyEnum;
import cn.baisee.oa.core.OaSessionListener;
import cn.baisee.oa.dao.baisee.BaiseeClazzMapper;
import cn.baisee.oa.dao.baisee.BaiseeMenuMapper;
import cn.baisee.oa.dao.baisee.BaiseeUserMapper;
import cn.baisee.oa.exception.AppException;
import cn.baisee.oa.importExcel.dto.BaseDto;
import cn.baisee.oa.model.BaiseeMenu;
import cn.baisee.oa.model.BaiseeUser;
import cn.baisee.oa.model.ReturnInfo;
import cn.baisee.oa.model.SessionUserInfo;
import cn.baisee.oa.model.UserCheckResult;
import cn.baisee.oa.model.empinfo.Employee;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.LoginService;
import cn.baisee.oa.service.UserService;
import cn.baisee.oa.utils.DateUtil;
import cn.baisee.oa.utils.EncryptUtil;
import cn.baisee.oa.utils.ParamUtils;
import cn.baisee.oa.utils.RedisUtils;
import cn.baisee.oa.utils.SessionUtil;
@Service
public class LoginServiceImpl implements LoginService {

	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Autowired
	private BaiseeUserMapper baiseeUserMapper;
	@Autowired
	private UserService userServiceImpl;
	@Autowired
	private BaiseeMenuMapper menuMapper;
	@Autowired
	private BaiseeClazzMapper classMapper; 
	@Override
	public PageInfo<BaiseeUser> getList(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<BaiseeUser> list = baiseeUserMapper.selectAll(new HashMap<String, Object>());
		PageInfo<BaiseeUser> page = new PageInfo<BaiseeUser>(list);
		return page;
	}

	@Override
	public ReturnInfo login(HttpServletRequest request) throws Exception {
		ReturnInfo rtInfo = null;
		try {
			BaiseeUser user = null;
			// 从request中获取用户名
			String userName = ParamUtils.getParameter(request, "username");
			if (StringUtils.isNotEmpty(userName)) {
				// 根据用户名查询用户
				user = userServiceImpl.getUserInfo(userName);
			} else {
				// 根据用户ID查询用户，这里是从INDEX系统点击左侧菜单进入的
				String userId = EncryptUtil.decrypt(ParamUtils.getParameter(
						request, "userId"));
				user = userServiceImpl.getUserById(userId);
			}

			// 校验用户
			UserCheckResult userState = checkUser(request, user);

			switch (userState) {
			case VALID:
				rtInfo = doValid(request, user);
				break;
			case INCORRECT:
				rtInfo = doIncorrect();
				break;
			case NOMENU:
				rtInfo = doNoMenu();
				break;
			case ABNORMAL:
				rtInfo = doAbnormal();
				break;
			case LEAVE:
				rtInfo = doLeave();
				break;
			default:
				break;
			}
		} catch (Exception e) {
//			logger.error("登录时发生异常: ", e);
			throw e; // 直接抛出，让异常拦截器捕获异常并处理，不能在rtInfo中设置异常信息，即使设置了，调用者也得不到rtInfo中的异常信息
		}
		// TODO记录登录日志
		return rtInfo;
	}
	
	/**
	 * 校验用户
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param user
	 *            用户
	 * @return
	 */
	private UserCheckResult checkUser(HttpServletRequest request, BaiseeUser user) throws Exception {
		// 校验用户是否为空
		if (user == null || StringUtils.isEmpty(user.getUserId())) {
			return UserCheckResult.INCORRECT;
		}
		// 校验用户状态是否正常
		if (!AppConstants.NORMAL.equals(user.getStatus())) {
			return UserCheckResult.ABNORMAL;
		}

		// 检查密码是否正确
		String password = ParamUtils.getParameter(request, "password");
		if (!userServiceImpl.isUserPwdCorrect(user, password)) {
			return UserCheckResult.INCORRECT;
		}

		// 校验用户是否有权限
		if (CollectionUtils.isEmpty(getUserMenus(request, user))) {
			return UserCheckResult.NOMENU;
		}

		return UserCheckResult.VALID;
	}

	/**
	 * 校验通过后设置及缓存用户相关信息
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param user
	 *            用户
	 * @return
	 */
	private ReturnInfo doValid(HttpServletRequest request, BaiseeUser user) {
		ReturnInfo rtInfo = new ReturnInfo();

		// 1、更新用户最近登录时间
		updateBaiseeUser(user);
		// 2、强制下线用户在其他地方的登录
		forceOff(request, user);
		// 3、设置缓存
		setUserCache(request, user);
		// 4、设置返回对象信息
		rtInfo.setResult(SessionUtil.getParameter(request,
				AppConstants.HOME_PAGE_URL));
		rtInfo.setCode(AppConstants.SUCCESS_CODE);
		

		BaiseeUser users = (BaiseeUser)SessionUtil.getUserInfo(request);
		 
		String[] bsClass = classMapper.selectById(users.getUserId());
		
		request.getSession().setAttribute("bsClass", bsClass);
		return rtInfo;
	}

	/**
	 * 设置INCORRECT返回状态
	 * 
	 * @return
	 */
	private ReturnInfo doIncorrect() {
		ReturnInfo rtInfo = new ReturnInfo();
		rtInfo.setMessage(UserCheckResult.INCORRECT.getMsg());
		rtInfo.setException(new AppException("IICIPBMS00001"));
		return rtInfo;
	}

	/**
	 * 设置NOMENU返回状态
	 * 
	 * @return
	 */
	private ReturnInfo doNoMenu() {
		ReturnInfo rtInfo = new ReturnInfo();
		rtInfo.setMessage(UserCheckResult.NOMENU.getMsg());
		rtInfo.setException(new AppException("IICIPBMS00002"));
		return rtInfo;
	}

	/**
	 * 设置ABNORMAL返回状态
	 * @return
	 */
	private ReturnInfo doAbnormal() {
		ReturnInfo rtInfo = new ReturnInfo();
		rtInfo.setMessage(UserCheckResult.ABNORMAL.getMsg());
		rtInfo.setException(new AppException("IICIPBMS00003"));
		return rtInfo;
	}

	/**
	 * 设置LEAVE返回状态
	 * 
	 * @return
	 */
	private ReturnInfo doLeave() {
		ReturnInfo rtInfo = new ReturnInfo();
		rtInfo.setMessage(UserCheckResult.LEAVE.getMsg());
		rtInfo.setException(new AppException("IICIPBMS00004"));
		return rtInfo;
	}

	/**
	 * 设置登录用户缓存信息
	 * 
	 * @param request
	 * @param user
	 *            登录用户
	 */
	public void setUserCache(HttpServletRequest request, BaiseeUser user) {
		request.getSession().setAttribute(AppConstants.SESSION_USER_ID, user.getUserId());
		request.getSession().setAttribute(AppConstants.SESSION_USER_INFO, getSessionUserInfo(user));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", user.getUserId());
		RedisUtils.utils.set(CacheKeyEnum.MENU_USER_INFO.getCacheKey()+user.getUserId(), menuMapper.selectMenuByCondition(map), null);
	}

	/**
	 * 更新用户最近登录时间
	 * 
	 * @param user
	 *            登录用户
	 */
	private void updateBaiseeUser(BaiseeUser user) {
		user.setLastUpdateTime(DateUtil.getNow(DateUtil.DATE_FORMAT_FULL_II));
		try {
			userServiceImpl.updateUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 更新最近登录时间
	}

	/**
	 * 获取用户权限，先从缓存中获取，缓存中没有去查询数据库
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param user
	 *            登录用户
	 * @return
	 */
	private List<BaiseeMenu> getUserMenus(HttpServletRequest request, BaiseeUser user) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", user.getUserId());
		List<BaiseeMenu> menus = menuMapper.selectMenuByCondition(map);
		request.getSession().setAttribute(AppConstants.SESSION_MENU_INFO, menus);
		return menus;
	}


	/**
	 * 获取用户信息
	 * 
	 * @param user
	 *            登录用户
	 * @return
	 */
	private SessionUserInfo getSessionUserInfo(BaiseeUser user) {
		SessionUserInfo info = new SessionUserInfo();
		BeanUtils.copyProperties(user, info);
		//查询员工信息
		Employee employee = userServiceImpl.selectEmployeeByID(user.getUserId());
		if (employee != null) {
			//将员工的部门赋值到sessionInfo
			info.setEmpDept(employee.getEmpDept());
		}
		return info;
	}

	/**
	 * 删除用户缓存
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param user
	 *            登录用户
	 */
	private synchronized void forceOff(HttpServletRequest request, BaiseeUser user) {
		HttpSession session = request.getSession();
		String userId = user.getUserId();
		HttpSession userSession = (HttpSession) OaSessionListener.sessionContext.getSessionMap().get(userId);
		if (userSession != null) {
			logger.debug("挤出重复登录用户");
			OaSessionListener.sessionContext.delSession(session);
			OaSessionListener.sessionContext.getSessionMap().put(userId, session);
		} else {
			userSession = (HttpSession) OaSessionListener.sessionContext.getSessionMap().get(session.getId());
			if (userSession != null) {
				OaSessionListener.sessionContext.getSessionMap().put(userId, userSession);
			} else {
				OaSessionListener.sessionContext.getSessionMap().put(userId, session);
			}
		}
		OaSessionListener.sessionContext.getSessionMap().remove(session.getId());
	}

	/**
	 * 删除缓存对象
	 * 
	 * @param key
	 */
	private synchronized void cleanCacheByKey(HttpServletRequest request, String key) {
 
	}

	/**
	 * 用户登出
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 */
	public void logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		OaSessionListener.sessionContext.delSession(session);
		String userId = SessionUtil.getString(request,
				AppConstants.SESSION_USER_ID);
		// 清除用户缓存
		cleanCacheByKey(request, session.getId());
		if (StringUtils.isNotEmpty(userId)) {
			session.invalidate();
		}
	}

	@Override
	public void insert(List<BaseDto> list) {
		 baiseeUserMapper.bashInsert(list);
	}
}
