package cn.baisee.oa.core.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.baisee.oa.annotation.Login;
import cn.baisee.oa.constants.AppConstants;
import cn.baisee.oa.core.OaSessionListener;
import cn.baisee.oa.utils.StringUtil;

/**
 * 登录拦截器 拦截未登录
 * 
 * @author think1
 * 
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	private static Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(handler instanceof HandlerMethod ) {
			HandlerMethod hm = (HandlerMethod)handler;
			
			if(hm.getMethod().isAnnotationPresent(Login.class))
				if(hm.getMethod().getAnnotation(Login.class).ignore())
					return true;
			
			if (!isLogin(request, response)) {// 是否已登录
				// ajax方式交互
				if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
					response.setHeader("executeStatus", "sessionTimeout");// 在响应头设置session状态
					response.setHeader("url", new StringBuffer(request.getContextPath()).append("/welcome.ht").toString());
					return false;
				}
				// 未登录  
				response.setHeader("content-type","text/html;charset=UTF-8");
	      PrintWriter out = response.getWriter();  
	      StringBuilder builder = new StringBuilder();  
	      builder.append("<script type=\"text/javascript\" charset=\"UTF-8\" src=\"").append(request.getContextPath()).append("/js/jquery-1.11.3.min.js\">").append("</script>");
	      builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");  
	      builder.append("$(function(){window.top.location.href='").append(request.getContextPath()).append("/welcome.ht';});");  
	      builder.append("</script>");  
	      out.print(builder.toString());  
	      out.close();
				return false;
			}
		}
		return true;
	}
	/**
	 * 判断是否登录
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	private boolean isLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = (String) request.getSession().getAttribute(AppConstants.SESSION_USER_ID);
		String sessionId = (String) request.getSession().getId();

		if (StringUtil.isEmpty(userId) || StringUtil.isEmpty(sessionId)) {
			log.error(this.getClass().getSimpleName() + " : SessionId为空");
			return false;
		}
		HttpSession userSession = (HttpSession) OaSessionListener.sessionContext.getSession(userId);
		if (userSession == null || !userSession.getId().equals(sessionId)) {
			log.error(this.getClass().getSimpleName() + " : Session为空或者SessionId匹配不正确");
			return false;
		}

		return true;
	}
}
