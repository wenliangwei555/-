package cn.baisee.oa.core.async;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.baisee.oa.utils.RedisUtils;

/**
 * 监听线程，用于监听队列中的任务
 */
public class AsyncProcessor extends Thread {

	private static final Log logger = LogFactory.getLog(AsyncProcessor.class);

	private static final String UUID = "$$UUID$$";

	private ThreadPoolExecutor poolExecutor;

	private static ArrayList<Task<?>> taskList = new ArrayList<Task<?>>();
	
	public AsyncProcessor(int minPoolSize, int maxPoolSize,
			ThreadFactory threadFactory) {
		poolExecutor = new ThreadPoolExecutor(minPoolSize, maxPoolSize, 0L,
				TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(),
				threadFactory);
		RedisUtils.utils.set(UUID, taskList);
	}

	public AsyncProcessor(int minPoolSize, int maxPoolSize) {
		this(minPoolSize, maxPoolSize, Executors.defaultThreadFactory());
	}

	public AsyncProcessor(int poolSize) {
		this(poolSize, poolSize, Executors.defaultThreadFactory());
	}

	public AsyncProcessor() {
		poolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L,
				TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		while (!isShutdown()) {
			Task<?> task = TaskQueue.poll();
			if (null != task) {
				if (isMaxLimited()) {
					((ArrayList<Task<?>>) RedisUtils.utils.get(UUID)).add(task);
				} else {
					poolExecutor.execute(task);
				}
			} else {
				if (null != RedisUtils.utils.get(UUID)) {
					if (0 < ((ArrayList<Task<?>>) RedisUtils.utils.get(UUID)).size()) {
						ArrayList<Task<?>> temp = (ArrayList<Task<?>>) RedisUtils.utils
								.get(UUID);
						while (temp.size() > 0) {
							Task<?> exe = (Task<?>) temp.remove(0);
							if (null != exe)
								poolExecutor.execute(exe);
						}
					}
				} else {
					waitting();
				}
			}
		}
	}

	protected void waitting() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException ex) {
			logger.error(ex.getLocalizedMessage(), ex);
		}
	}

	protected boolean isMaxLimited() {
		return poolExecutor.getActiveCount() > poolExecutor
				.getMaximumPoolSize();
	}

	protected void dispose() {
		poolExecutor.shutdown();
		try {
			if (!poolExecutor.awaitTermination(1000, TimeUnit.SECONDS)) {
				poolExecutor.shutdown();
				if (!poolExecutor.awaitTermination(1000, TimeUnit.SECONDS)) {
					logger.error("异步处理组件  , 异步服务挂起失败 " + "E90008",
							new Throwable("异步服务挂起失败"));
				}
			}
		} catch (InterruptedException e) {
			poolExecutor.shutdownNow();
			Thread.currentThread().interrupt();
		}
		RedisUtils.utils.del(UUID);
	}

	protected boolean isShutdown() {
		return poolExecutor.isShutdown() || poolExecutor.isTerminated()
				|| poolExecutor.isTerminating();
	}

}
