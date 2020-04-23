package cn.baisee.oa.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.constants.AppConstants;

/**
 * Controller异常处理
 * 
 */
public class CustomExceptionHandler implements HandlerExceptionResolver {
	protected final transient Log log = LogFactory.getLog(CustomExceptionHandler.class);

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) {
		
		ModelAndView mav = new ModelAndView("/error");
		
		String errorCode = null;
		if (exception instanceof BaseException) {
			BaseException be = (BaseException) exception;
			errorCode = be.getCode();
		} else {
			errorCode = "E9000";
		}
		mav.addObject(AppConstants.HOME_PAGE_URL, AppConstants.HOME_PAGE_URI);
		mav.addObject("errorCode", errorCode);
		mav.addObject("errorMsg", errorCode);
		// TODO 临时加入异常信息
		if(exception.getCause() != null) {
			mav.addObject("exceptionMsg", exception.getCause().getMessage());
		}
		log.error("处理Controller异常............" + errorCode, exception);
		
		return mav;
	}

}