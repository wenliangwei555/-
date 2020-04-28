package cn.baisee.oa.thymeleaf.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.Arguments;
import org.thymeleaf.Configuration;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring3.context.SpringWebContext;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;

public class OaDialectUtil {
	/**
	 * 从上下文中获取对应参数值
	 * @param arguments
	 * @param paramName
	 * @return
	 */
	public static String getValue(Arguments arguments, String paramName) {
		Object o = null;
		if((o = getObject(arguments, paramName)) == null)
			return null;
		return o.toString();
	}
	
	public static Object getObject(Arguments arguments, String paramName) {
		final Configuration configuration = arguments.getConfiguration();
		final IStandardExpressionParser expressionParser = StandardExpressions.getExpressionParser(configuration);
		final IStandardExpression expression = expressionParser.parseExpression(configuration, arguments, paramName);
		return expression.execute(configuration, arguments);
	}
	
	@SuppressWarnings("rawtypes")
	public static List getList(Arguments arguments, String paramName) {
		Object sObject = getObject(arguments, paramName);
		if(sObject != null) {
			if(sObject instanceof List) {
				return (List)getObject(arguments, paramName);
			} else {
				if(sObject != ""){
					return Arrays.asList((Object[])sObject);
				}
				return new ArrayList();
			}
		} else {
			return new ArrayList();
		}
	}
	
	public static HttpServletRequest getRequest(Arguments arguments) {
		return getWebContext(arguments).getHttpServletRequest();
	}

	private static WebContext getWebContext(Arguments arguments) {
		return (SpringWebContext) arguments.getContext();
	}
	
	public static HttpServletResponse getResponse(Arguments arguments) {
		return getWebContext(arguments).getHttpServletResponse();
	}
}
