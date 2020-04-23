package cn.baisee.oa.core.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.constants.AppConstants;
import cn.baisee.oa.model.BaiseeMenu;
import cn.baisee.oa.utils.StringUtil;

public class RoleInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession(false);
		if(session != null)
			session.removeAttribute(AppConstants.CUR_MENU_CODE);
		if (handler instanceof HandlerMethod) {
			Method method = ((HandlerMethod) handler).getMethod();
			Class<?> declareClass = method.getDeclaringClass();

			if (method.isAnnotationPresent(Role.class)) {
				Role methodRole = method.getAnnotation(Role.class);
				if (methodRole.ignore())
					return true;
			}

			if (declareClass.isAnnotationPresent(Role.class)) {
				Role classRole = declareClass.getAnnotation(Role.class);
				if (classRole.ignore())
					return true;
			}

			Role role = null;
			String[] menuCodes = {};

			if (method.isAnnotationPresent(Role.class)) {
				role = method.getAnnotation(Role.class);
				menuCodes = role.value();
			}

			int codeIndex = getCodeIndex(request, method);

			if (codeIndex > menuCodes.length - 1) {
				showErrorDialog(request, response);
				return false;
			}

			if (!allowBrowse(menuCodes[codeIndex], request)) {
				showErrorDialog(request, response);
				return false;
			}
		}
		return true;
	}

	private void showErrorDialog(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		PrintWriter out = null;
		if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
			response.setHeader("executeStatus", "unallowed");// 在响应头设置session状态
			return;
		}
		response.setHeader("content-type", "text/html;charset=UTF-8");
		try {
			out = response.getWriter();
			StringBuilder builder = new StringBuilder();
			builder.append("<script type=\"text/javascript\" charset=\"UTF-8\" src=\"").append(request.getContextPath()).append("/js/jquery-1.11.3.min.js\">").append("</script>");
			builder.append("<script type=\"text/javascript\" charset=\"UTF-8\" src=\"").append(request.getContextPath()).append("/js/plugins/layer/layer.js\">").append("</script>");
			builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");
			builder.append("$(function(){layer.msg(\"权限不足无法访问\", {icon:5,time:2000}, function(){window.top.location.href='").append(request.getContextPath()).append("/").append("indexpage.ht").append("'});});");
			builder.append("</script>");
			out.print(builder.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private int getCodeIndex(HttpServletRequest request, Method method) {
		String uri = request.getRequestURI();
		RequestMapping rm = method.getAnnotation(RequestMapping.class);
		String[] mappings = rm.value();
		for (int i = 0; i < mappings.length; i++) {
			if (uri.contains(mappings[i])) {
				return i;
			}
		}
		return -1;
	}

	@SuppressWarnings("unchecked")
	private boolean allowBrowse(String menuCode, HttpServletRequest request) {
		boolean rt = false;
		List<BaiseeMenu> menus = (List<BaiseeMenu>) request.getSession().getAttribute(AppConstants.SESSION_MENU_INFO);
		BaiseeMenu selectMenu = null;
		for (BaiseeMenu menu : menus) {
			if (!StringUtil.isEmpty(menu.getMenuCode()) && menuCode.equals(menu.getMenuCode())) {
				selectMenu = menu;
				rt = true;
				break;
			}
		}

		if (selectMenu != null) {
			Map<String, String> map = new HashMap<String, String>();
			getMenuCode(menus, selectMenu, map);
			request.getSession(false).setAttribute(AppConstants.CUR_MENU_CODE, map.get("subCode"));
			request.getSession(false).setAttribute(AppConstants.CUR_MENU_PCODE, map.get("code"));
		}
		return rt;
	}

	private void getMenuCode(List<BaiseeMenu> menus, BaiseeMenu selectMenu, Map<String, String> map) {
		for (BaiseeMenu menu : menus) {
			if(menu.getMenuId().equals(selectMenu.getpId())) {
				if("0".equals(menu.getpId())) {
					map.put("subCode", selectMenu.getMenuCode());
					map.put("code", menu.getMenuCode());
				} else {
					getMenuCode(menus, menu, map);
				}
			}
		}
	}
	
	 
}
