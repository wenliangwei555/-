package cn.baisee.oa.core.async;

/**
 *	异步任务抽象类 
 */
public abstract class Task<T> implements Runnable {

	private T context;

	protected T get() {
		return this.context;
	}

	public Task(T context) {
		this.context = context;
	}

	public void run() {
		execute();
	}

	protected abstract void execute();

}
