package cn.baisee.oa.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 描述:Bean操作 装配Spring容器的ApplicationContext对象
 */
public class ApplicationContextUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	/**
	 * 实现ApplicationContextAware接口的回调方法，设置上下文环
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		ApplicationContextUtil.applicationContext = applicationContext;
	}

	/**
	 * 获取bean实例
	 * 
	 * @param Class
	 *            beanclass类型
	 * @return Object bean实例
	 * @throws BeansException
	 */
	public static Object getBean(Class<?> beanclass) throws BeansException {
		return applicationContext.getBean(beanclass);
	}

	/**
	 * 获取bean实例
	 * 
	 * @param beanid
	 *            bean标识
	 * @return Object bean实例
	 * @throws BeansException
	 */
	public static Object getBean(String beanId) throws BeansException {
		if (beanId == null || beanId.trim().length() < 1) {
			return null;
		}
		//删除小写转换
//		beanId = StringUtil.firstLowerCase(beanId);
		return applicationContext.getBean(beanId);
	}

	/**
	 * 获取bean实例
	 * 
	 * @param Class beanclass类型
	 * @return  Map<String, ?>  bean实例
	 * @throws BeansException
	 */
	public static Object getBeansOfType(Class<?> beanclass) throws BeansException {
		return applicationContext.getBeansOfType(beanclass);
	}
	
}
