package cn.baisee.oa.thymeleaf.dialect;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.thymeleaf.context.IProcessingContext;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IExpressionEnhancingDialect;
import org.thymeleaf.processor.IProcessor;

import cn.baisee.oa.thymeleaf.dataProcessor.DateProcessor;
import cn.baisee.oa.thymeleaf.dataProcessor.MenuIterator;
import cn.baisee.oa.thymeleaf.dataProcessor.PersonNameProcessor;
import cn.baisee.oa.thymeleaf.dataProcessor.RoleBtnShowProcessor;
import cn.baisee.oa.utils.ExtCacheUtils;

public class OaDialect  extends AbstractDialect implements IExpressionEnhancingDialect {
	
	private final ConcurrentMap<String, Object> OA_BASE_OBJECT = new ConcurrentHashMap<String, Object>();
	
	private static final String CACHE_OBJECT = "sysCache";//起名不要与SpringDialect的冲突
	
	@Override
	public Map<String, Object> getExecutionAttributes() {
		// TODO Auto-generated method stub
		return super.getExecutionAttributes();
	}

	public OaDialect() {
		super();
	}

	@Override
	public Set<IProcessor> getProcessors() {
		final Set<IProcessor> processors = new HashSet<IProcessor>();
		processors.add(new MenuIterator());
		processors.add(new DateProcessor());
		processors.add(new RoleBtnShowProcessor());
		processors.add(new PersonNameProcessor());
		return processors;
	}
 
	@Override
	public String getPrefix() {
		return "baisee";
	}

	@Override
	public Map<String, Object> getAdditionalExpressionObjects(IProcessingContext processingContext) {
		OA_BASE_OBJECT.put(CACHE_OBJECT, new ExtCacheUtils());
		return OA_BASE_OBJECT;
	}
}
