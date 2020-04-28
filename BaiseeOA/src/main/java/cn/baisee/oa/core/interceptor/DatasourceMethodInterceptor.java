package  cn.baisee.oa.core.interceptor;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.core.datasource.MultipleDataSource;
import cn.baisee.oa.exception.DaoException;

/**
 * 拦截数据库操作方法，根据DatasourceType切换相应数据源
 * 
 */
public class DatasourceMethodInterceptor implements MethodInterceptor,
		InitializingBean {

	private Logger log = LoggerFactory
			.getLogger(DatasourceMethodInterceptor.class);

	public Object invoke(MethodInvocation mi) throws Throwable {
		Method method = mi.getMethod();
		Class<?> declaringClass = method.getDeclaringClass();

		if (method.isAnnotationPresent(Datasource.class)) {
			changeDatasrouce(method.getAnnotation(Datasource.class));
		} else if (declaringClass.isAnnotationPresent((Datasource.class))) {
			changeDatasrouce(declaringClass.getAnnotation(Datasource.class));
		}

		try {
			return mi.proceed();
		} catch (Exception e) {
			String className = declaringClass.getSimpleName();
			String methodName = method.getName();
			StringBuffer sb = new StringBuffer("[").append(className)
					.append(".").append(methodName).append("]方法执行异常--------")
					.append(e.getMessage());
			log.error(sb.toString(), e);
			throw new DaoException(sb.toString(), e);
		}
	}

	private void changeDatasrouce(Datasource ds) {
		DatasourceTypes dt = ds.value();
		MultipleDataSource.setDataSourceKey(dt.getSourceName());
	}

	@Override
	public void afterPropertiesSet() throws Exception {

	}

}
