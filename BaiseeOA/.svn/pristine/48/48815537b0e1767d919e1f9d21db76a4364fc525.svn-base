package cn.baisee.oa.core.async;

import java.util.Collection;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *	任务队列 
 */
public class TaskQueue {

	private static final Log logger = LogFactory.getLog(TaskQueue.class);

	private static volatile boolean refuse = false;

	private static LinkedBlockingQueue<Task<?>> queue = new LinkedBlockingQueue<Task<?>>();

	/**
	 *	不阻塞插入队列尾
	 *	如果队列满抛出异常 
	 */
	public static void offer(Task<?> task) {
		if (!refuse)
			queue.offer(task);
	}

	/**
	 *	插入队列尾
	 *	如果队列满阻塞等待 
	 */
	public static void put(Task<?> task) {
		try {
			if (!refuse)
				queue.put(task);
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
	}

	/**
	 * 从队列头去除任务
	 * 如无任务阻塞等待
	 */
	public static Task<?> take() {
		try {
			if (!refuse)
				return queue.take();
			else
				throw new Exception("队列已经拒绝接收任务");
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		return null;
	}

	/**
	 * 从队列头去除任务
	 * 如无任务返回null
	 */
	public static Task<?> poll() {
		try {
			if (!refuse)
				return queue.poll();
			else
				throw new Exception("队列已经拒绝接收任务");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 清空队列存入collection
	 */
	public static void drainTo(Collection<Task<?>> collection) {
		queue.drainTo(collection);
	}

	public static void refused(boolean refuse) {
		TaskQueue.refuse = refuse;
	}

}
