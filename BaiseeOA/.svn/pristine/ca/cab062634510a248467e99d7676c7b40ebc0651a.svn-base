package cn.baisee.oa.core.async.test;

import cn.baisee.oa.core.async.QueueHolder;
import cn.baisee.oa.core.async.TaskQueue;

public class TestHolder extends QueueHolder<Object>  {

	public static final TestHolder INSTANCE = new TestHolder();
	
	@Override
	public void put(final Object arg0) {
		if (null != arg0) {
			new Thread(new Runnable() {
				public void run() {
					TaskQueue.put(new TestTask(arg0));
				}
			}).start();
		}
		
	}
			
}
