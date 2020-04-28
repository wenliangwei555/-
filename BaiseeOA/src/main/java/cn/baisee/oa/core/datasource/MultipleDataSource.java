package cn.baisee.oa.core.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 多数据源
 * @author Administrator
 *
 */
public class MultipleDataSource {
	
	private static final ThreadLocal<String> dataSourceKey = new InheritableThreadLocal<>();
	private static final Logger log = LoggerFactory.getLogger(MultipleDataSource.class);
	
	public static void setDataSourceKey(String dataSource) {
		//数据源相同时无须切换
		if(!dataSource.equals(dataSourceKey.get())) {
			log.debug("数据源切换为 ：" + dataSource);
			dataSourceKey.set(dataSource);
		}
	}
	
	public static String getDatasourceKey() {
		return dataSourceKey.get();
	}
	
	public static void cleaerDataSource() {
		dataSourceKey.remove();
	}

}
