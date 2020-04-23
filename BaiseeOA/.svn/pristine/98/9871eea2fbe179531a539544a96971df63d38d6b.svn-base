package cn.baisee.oa.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import cn.baisee.oa.utils.AppUtil;
import cn.baisee.oa.utils.SessionUtil;

/**
 * 重写Spring Mvc Servlet，处理输入URL没有requestMapping处理的情况。
 */
public class SpringMvcServlet extends DispatcherServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger log = LoggerFactory.getLogger(SpringMvcServlet.class);

	@Override
	protected void noHandlerFound(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String requestURI = request.getRequestURI();
		logger.debug("not foud handle mapping for url: " + requestURI);
		if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
			if(SessionUtil.getUserInfo(request) != null) {
				log.debug("访问资源不存在");
				response.setHeader("executeStatus", "noResourceFound");// 在响应头设置session状态
			} else {
				log.debug("session 超时，请重新你登录");
				response.setHeader("executeStatus", "sessionTimeout");// 在响应头设置session状态
				response.setHeader("url", new StringBuffer(request.getContextPath()).append("/welcome.ht").toString());
			}
			return;
		}
//		request.getRequestDispatcher("/errorPage.ht?code=404").forward(request, response);
	}

	@Override
	protected void onRefresh(ApplicationContext context) {
		initStrategies(context);
		AppUtil.setContext(context);
	}

}
