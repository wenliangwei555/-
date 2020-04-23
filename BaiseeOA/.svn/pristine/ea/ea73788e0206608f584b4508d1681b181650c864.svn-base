package cn.baisee.oa.thymeleaf.dataProcessor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.support.RequestDataValueProcessor;

import cn.baisee.oa.utils.StringUtil;

public class OaDataProcessor implements RequestDataValueProcessor{
	public static final String TOKEN = "web_token";
	public Map<String, String> getExtraHiddenFields(HttpServletRequest request) {
		String token = (String)request.getSession().getAttribute(TOKEN);
		Map<String, String> hiddenMap = new HashMap<String, String>();
		if(!StringUtil.isEmpty(token)) {
			hiddenMap.put(TOKEN, token);
			return hiddenMap;
		}
		return hiddenMap;
	}
	/**
	 * th:action
	 * 处理 form acion 
	 */
	public String processAction(HttpServletRequest request, String action) {
		if(StringUtil.isEmpty(action))
			return null;
		
		 
		
		StringBuffer sb = new StringBuffer(request.getContextPath());
		sb.append("/").append(action);
		return sb.toString();
	}
	
	/**
	 * 处理th:value th:field
	 */
	public String processFormFieldValue(HttpServletRequest request, String name, String value, String type) {
		if(!StringUtil.isEmpty(value))
			return value.trim();
		return "";
	}
	/**
	 * 处理 href linkSrc javascriptSrc
	 */
	public String processUrl(HttpServletRequest request, String url) {
		if(StringUtil.isEmpty(url))
			return null;
		if(url.indexOf("http://") > -1)
			return url;
		StringBuffer sb = new StringBuffer(request.getContextPath());
		sb.append("/").append(url);
		return sb.toString();
	}

}

