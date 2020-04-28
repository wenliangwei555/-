package com.msghub.msghub.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 获取ApplicationContext， 调用方法 SpringContextUtil.getContext();<br>
 * 在spring文件中配置方法：<br>
 * <bean id="springContextUtil" class="com.sf.core.util.AppUtil" />
 * @author sf
 *
 */
public class AppUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;
	
	private static ServletContext servletContext;
	
	
	/**
	 * 黑名单用户
	 */
	private static Map<String,Date> blacklist=new LinkedHashMap<String, Date>();
	public static Map<String, Date> getBlacklist() {
		return blacklist;
	}

	/**
	 * 
	 * @param servletContext
	 */
	public static void init(ServletContext _servletContext)
	{
		servletContext=_servletContext;
	}

	/**
	 * spring启动时注入context
	 */
	@Override
	public void setApplicationContext(ApplicationContext contex) throws BeansException {
		applicationContext=contex;
	}

	/**
	 * 获取spring的上下文。
	 * @return
	 */
	public static ApplicationContext getContext(){
		return applicationContext;
	}
	
	/**
	 * 获取web应用的ServletContext对象。
	 * @return
	 * @throws Exception
	 */
	public static ServletContext getServletContext() throws Exception{
		return servletContext;
	}

	/**
	 * 获取spring的上下文。
	 * @return
	 */
	public static void setContext(ApplicationContext ac){
		applicationContext = ac;
	}
	
	
	/**
	 * 根据类从spring上下文获取bean。
	 * @param cls
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  static Object getBean(Class cls){
		return applicationContext.getBean(cls);
	}
	
	/**
	 * 根据类名从spring上下文获取bean。
	 * @param cls
	 * @return
	 */
	public static Object getBean(String  beanId){
		return applicationContext.getBean(beanId);
	}

	/**
	 * 取得应用程序的绝对路径
	 * @return
	 */
	public static String getAppAbsolutePath(){
		return servletContext.getRealPath("/");
	}
	
	/**
	 * 在web环境中根据web页面的路径获取对应页面的绝对路径。
	 * @param path
	 * @return
	 */
	public static String getRealPath(String path){
		return servletContext.getRealPath(path);
	}
	
	/**
	 * 获取Classpath物理路径
	 * @return
	 */
	public static String getClasspath(){
		 String classPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		 String rootPath  = "";
		  //windows下
		  if("\\".equals(File.separator)){   
		   rootPath  = classPath.substring(1);
		   rootPath = rootPath.replace("/", "\\");
		  }
		  //linux下
		  if("/".equals(File.separator)){   
		   rootPath  = classPath.substring(1);
		   rootPath = rootPath.replace("\\", "/");
		  }
		  return rootPath;
	}
	



}
