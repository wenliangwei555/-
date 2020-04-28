package cn.baisee.oa.core.async;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

 /**
  * 初始化异步队列
  */
@Component
public class AsyncProcessorHelper  {

	private static final Log logger = LogFactory
			.getLog(AsyncProcessorHelper.class);
	/**
	 * 提供了一个模拟构造的注解
	 */
	@PostConstruct
	public void init() {
		initAsyncComponent();
	}
	
	private void initAsyncComponent() {
		logger.info("初始化异步队列: initAsyncComponent- #启动异步监听  开始------------------");
		AsyncProcessorHolder.start();
		logger.info("初始化异步队列: initAsyncComponent- #启动异步监听  结束------------------");
	}
}
