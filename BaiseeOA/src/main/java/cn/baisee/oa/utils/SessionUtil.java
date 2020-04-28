package cn.baisee.oa.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.baisee.oa.constants.AppConstants;
import cn.baisee.oa.core.OaSessionListener;
import cn.baisee.oa.model.SessionUserInfo;

public class SessionUtil {
	
	private static final Logger log = LoggerFactory.getLogger(SessionUtil.class);

	public static SessionUserInfo getUserInfo(HttpServletRequest req) {
		return (SessionUserInfo) getParameter(req, AppConstants.SESSION_USER_INFO);
	}

	private static boolean isNull(HttpServletRequest req) {
		if (req == null)
			return true;
		HttpSession session = req.getSession();
		if (session == null)
			return true;
		return false;
	}

	public static String getString(HttpServletRequest req, String paramName) {
		return (String) getParameter(req, paramName);
	}

	public static int getInteger(HttpServletRequest req, String paramName) {
		if (isNull(req))
			return -1;
		return (Integer) req.getSession().getAttribute(paramName);
	}

	public static Object getParameter(HttpServletRequest req, String paramName) {
		if (isNull(req))
			return null;
		return req.getSession().getAttribute(paramName);
	}

	public static void setUserInfo(HttpServletRequest req, SessionUserInfo userInfo) {
		req.getSession().setAttribute(AppConstants.SESSION_USER_INFO, userInfo);
	}

	
	
	public static void logoutUser(String userId, HttpServletRequest req) {
		log.debug("===================== 强制下线用户：" + userId + "=========================");
		HttpSession session = OaSessionListener.sessionContext.getSessionMap().get(userId);
		if(session != null) {
			String id = session.getId();
			OaSessionListener.sessionContext.getSessionMap().remove(id);
		}
		OaSessionListener.sessionContext.getSessionMap().remove(userId);
	}
	
}
