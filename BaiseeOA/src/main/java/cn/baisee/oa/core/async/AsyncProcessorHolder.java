package cn.baisee.oa.core.async;

import java.util.concurrent.ThreadFactory;

/**
 *	ayncProcessor管理类 
 */
public final class AsyncProcessorHolder {

	private AsyncProcessorHolder() {
	}

	private static AsyncProcessor processor = new AsyncProcessor();

	public static AsyncProcessor instance() {
		return processor;
	}

	public static AsyncProcessor instance(int maxthreads) {
		processor = new AsyncProcessor(maxthreads);
		return processor;
	}

	public static AsyncProcessor instance(int minPoolSize, int maxPoolSize) {
		processor = new AsyncProcessor(minPoolSize, maxPoolSize);
		return processor;
	}

	public static AsyncProcessor instance(int minPoolSize, int maxPoolSize,
			ThreadFactory threadFactory) {
		processor = new AsyncProcessor(minPoolSize, maxPoolSize, threadFactory);
		return processor;
	}

	public static void start() {
		processor.start();
	}

	public static void dispose() {
		processor.dispose();
		try {
			processor.interrupt();
		} catch (SecurityException e) {

		}
	}

}
