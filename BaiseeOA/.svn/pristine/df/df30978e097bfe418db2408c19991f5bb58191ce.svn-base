package cn.baisee.oa.core.context;

/**
 * 每一个请求线程对应的上下文，可以用于存放业务数据、异常信息等
 * 与拦截器配合使用，如：
 * 在处理请求之前为当前线程初始化一个上下文对象，在方法执行过程中，可以随时获取当前线程的上下文进行存取数据
 * 在处理请求结束之后需要释放当前线程对应的上下文
 * 
 * 
 * 
 * 
 * 
 * 
 * @author Administrator
 *
 */
public class OaThreadContext {
	
	private static final ThreadLocal<ICIPContext> LOCAL_CONTEXT = new ThreadLocal<ICIPContext>();

	public static ICIPContext getContext() {
		return LOCAL_CONTEXT.get();
	}

	public static void setContext(ICIPContext context) {
		LOCAL_CONTEXT.set(context);
	}

	public static void clearContext() {
		LOCAL_CONTEXT.remove();
	}
	
	
	
	
}
