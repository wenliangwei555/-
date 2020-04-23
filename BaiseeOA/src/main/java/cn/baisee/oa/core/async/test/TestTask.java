package cn.baisee.oa.core.async.test;

import java.util.Map;

import cn.baisee.oa.core.async.Task;

public class TestTask extends Task<Object>  {
	public TestTask(Object context) {
		super(context);
	}


	@Override
	protected void execute() {
		Map<String, Object> map = (Map<String, Object>)this.get();
		System.out.println(map.get("message"));
	}

}
