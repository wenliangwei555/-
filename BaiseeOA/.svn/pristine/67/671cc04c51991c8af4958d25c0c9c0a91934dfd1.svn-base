package cn.baisee.oa.core;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.util.ObjectUtils;
import org.springframework.util.PropertyPlaceholderHelper;

import cn.baisee.oa.utils.StringUtil;

/**
 * 加密属性密码。
 * 
 * <pre>
 * 扩展PropertyPlaceholderConfigurer用于加密密码。
 * 配置在：
 * &lt;bean id="propertyConfigurer"  class="com.sf.core.util.ExtpPropertyPlaceholderConfiger">
 *        &lt;property name="properties" ref="configproperties"/>
 *  &lt;/bean>
 * </pre>
 * 
 * @author ray
 * 
 */
public class ExtpPropertyPlaceholderConfiger extends PropertyPlaceholderConfigurer {

	private String[] encryptNames = { "jdbc.password" };

	private static final Map<String, String> properties = new HashMap<String, String>();

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
		PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper(DEFAULT_PLACEHOLDER_PREFIX, DEFAULT_PLACEHOLDER_SUFFIX, DEFAULT_VALUE_SEPARATOR, false);
		for (Entry<Object, Object> entry : props.entrySet()) {
			String stringKey = (String)entry.getKey();
			String stringValue = (String)entry.getValue();
			stringValue = helper.replacePlaceholders(stringValue, props);
			properties.put(stringKey, stringValue);
		}
		super.processProperties(beanFactoryToProcess, props);
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected void convertProperties(Properties props) {
		Enumeration propertyNames = props.propertyNames();
		while (propertyNames.hasMoreElements()) {
			String propertyName = (String) propertyNames.nextElement();
			String propertyValue = props.getProperty(propertyName);

			String convertedValue = convertPropertyValue(propertyValue);
			/*if (isEncryptProp(propertyName)) {
				try {
					convertedValue = EncryptUtil.decrypt(convertedValue);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}*/
			if (!ObjectUtils.nullSafeEquals(propertyValue, convertedValue)) {
				props.setProperty(propertyName, convertedValue);
			}
		}
	}
/*
	private boolean isEncryptProp(String propertyName) {
		for (String name : encryptNames) {
			if (!StringUtil.isEmpty(propertyName) && propertyName.contains(name)) {
				return true;
			}
		}
		return false;
	}*/

	public static String getString(String key) {
		return properties.get(key);
	}
	
	public static boolean getBoolean(String key) {
		String value = getString(key);
		return Boolean.valueOf(StringUtil.isEmpty(value) ? "false" : value);
	}
	
	public static int getInteger(String key) {
		String val = properties.get(key);
		if(!StringUtil.isEmpty(val)) {
			return Integer.valueOf(val);
		}
		return 0;
	}
}
