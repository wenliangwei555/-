package cn.baisee.oa.cache;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * 学费信息缓存（学费，杂费，学制）
 * @author Kha'Zix
 *
 */

import cn.baisee.oa.constants.CacheKeyEnum;
import cn.baisee.oa.model.BaiseeSysParam;
import cn.baisee.oa.service.SysParamService;
import cn.baisee.oa.utils.RedisUtils;
@Component
public class BaiseeTuitionCache {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 服务器业务逻辑层
	 */
	@Autowired
	private SysParamService	sysParamService;
	
	// 缓存的工具类
	@Resource(name = "redisUtils")
	private RedisUtils redisUtils;
	
	//当前缓存的一个对象
	public static BaiseeTuitionCache utils;
	
	/**
	 * 提供一个模拟构造的注解
	 */
	@PostConstruct
	public	void init() {
		logger.debug("开始加载学费信息");
		redisUtils.del(CacheKeyEnum.TUITION.getCacheKey());
		
		List<BaiseeSysParam> tuition=sysParamService.queryAll();
		
		redisUtils.set(CacheKeyEnum.TUITION.getCacheKey(), tuition, null);
		utils =this;
		logger.debug("结束加载学费信息");
	}
}
