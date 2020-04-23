package cn.baisee.oa.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.model.BaiseeUser;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.UserService;
import cn.baisee.oa.utils.ExtCacheUtils;
import cn.baisee.oa.utils.ParamUtils;
import cn.baisee.oa.utils.SessionUtil;

@Controller
@RequestMapping(value="user")
public class BaiseeUserController {
	
	@Autowired
	private UserService userService;
	
	
	/***********************************分页显示数据*******************************************/
	@RequestMapping(value="/toUserList")
	@Role(value="XTGL_YHGL")
	public ModelAndView toUserList(HttpServletRequest request){
		return userService.UserListInfo(request);
	}
	/**************************************前往添加页面***********************************************/
	@RequestMapping(value="/toAddUser")
	@Role(value="XTGL_YHXZ")
	public ModelAndView toAddUser(HttpServletRequest request)throws Exception{
		return userService.UserAddPage(request);
	}
	
	
	/****************************************前往修改页面* @throws Exception **********************************************************/
	@RequestMapping(value="/toUpdateUser")
	@Role(value="XTGL_YHXG")
	public ModelAndView toUserUpdate(HttpServletRequest request) throws Exception{
		return userService.UserUpdatePage(request);
	}
	
	
	/***************************************添加或者修改方法，数据库* @throws Exception *************************************************************/
	@RequestMapping(value="/toSaveOrAddUser")
	@Role(value="XTGL_YHXZ")
	public ModelAndView toSaveOrAddUser(HttpServletRequest request,BaiseeUser baiseeUser) throws Exception{
		return userService.UserAddOrUpdateMethod(request, baiseeUser);
	}
	
	/*******************************************批量删除***************************************************************/
	@RequestMapping(value="/deleteCheckUser")
	@Role(value="XTGL_YHSC")
	public ModelAndView toDeleteCheckUser(HttpServletRequest request){
		return userService.UserCheckDelete(request);
	}
	
	/**************************************校验有没有此用户************************************************/
	@RequestMapping(value="/VerificationEmpID")
	@Role(ignore=true)
	@ResponseBody
	public Object VerificationEmpID(HttpServletRequest request){
		String userId = ParamUtils.getParameter(request, "userId");
		BaiseeUser user = userService.verificationEmpID(userId);
		if(user!=null && user.getLoginName().length()>0){
			return false;
		}else{
			return true;
		}
	}
	/***************************************校验有没有此登录名称***************************************************/
	@RequestMapping(value="/VerificationLoginName")
	@Role(ignore=true)
	@ResponseBody
	public Object VerificationLoginName(HttpServletRequest request){
		BaiseeUser baiseeUser = new BaiseeUser();
		String loginName = ParamUtils.getParameter(request, "loginName");
		String userId = ParamUtils.getParameter(request, "userId");
		if(userId == null || userId == ""){
			baiseeUser.setUserId("");
		}else{
			baiseeUser.setUserId(userId);
		}
		baiseeUser.setLoginName(loginName);
		BaiseeUser user = userService.verificationLoginName(baiseeUser);
		if(user!=null && user.getLoginName().length()>0){
			return false;
		}else{
			return true;
		}
	}
	
	
	@RequestMapping(value="changePwd")
	@ResponseBody
	@Role(ignore=true)
	public String changePwd( HttpServletRequest request, HttpServletResponse response) {
		String success = userService.updateChanagePwd(request);
		response.setContentType("text/html;charset=UTF-8");
		return success;
	}
	
	/**
	 * select2 下拉选择用户专用
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/select2UserList")
	@ResponseBody
	@Role(ignore=true)
	public List<BaiseeUser> select2UserList(HttpServletRequest request){
		Integer pageNum = ParamUtils.getPageNumParameters(request);
		Integer pageSize = ParamUtils.getPageSizeParameters(request);
		String itemlableSearch = ParamUtils.getParameter(request, "q");
		System.out.println(itemlableSearch);
		PageInfo<BaiseeUser> pageInfo = userService.selectBaiseeUserList(pageNum, pageSize, itemlableSearch);
		List<BaiseeUser> list = pageInfo.getList();
		System.out.println(list.size());
		return pageInfo.getList();
	}
	/**
	 * 清除所有用户缓存
	 * @param request
	 * @return
	 */
	@RequestMapping(value="clearAllMenuCache")
	@ResponseBody
	@Role(ignore=true)
	public String clearAllMenuCache(HttpServletRequest request) {
		
		List<BaiseeUser> list = userService.selectUserAll();
		
		for (BaiseeUser baiseeUser : list) {
			try {
				ExtCacheUtils.clearAllCache(baiseeUser.getUserId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			
		return "" + list.size();
	}
	/**
	 * 清除当前用户缓存
	 * @param request
	 * @return
	 */
	@RequestMapping(value="clearUserAllMenuCache")
	@ResponseBody
	@Role(ignore=true)
	public String clearUserAllMenuCache(HttpServletRequest request) {
		
		BaiseeUser currUser = (BaiseeUser)SessionUtil.getUserInfo(request);
		if(null != currUser) {
			ExtCacheUtils.clearAllCache(currUser.getUserId());
		}
			
		return "0";
			
	}
}
