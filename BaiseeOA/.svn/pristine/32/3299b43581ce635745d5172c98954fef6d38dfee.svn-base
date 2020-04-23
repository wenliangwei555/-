package cn.baisee.oa.core.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务测试
 * @author Administrator
 *
 */
@Component("taskTest")
public class TaskTest {

	private static final Logger log = LoggerFactory.getLogger(TaskTest.class);
	
	/**
	 * 每天下午5点35分执行
	 */
	@Scheduled(cron="0 35 17 ? * *")
	public void doJob(){
		log.debug("测试日志");
	}
}
