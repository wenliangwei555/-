package cn.baisee.oa.cache;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.baisee.oa.constants.CacheKeyEnum;
import cn.baisee.oa.dao.baisee.BaiseeSystemParamMapper;
import cn.baisee.oa.model.BaiseeSystemParam;
import cn.baisee.oa.utils.RedisUtils;
import redis.clients.jedis.Jedis;

/**
 * Ftp Server 缓存
 * 
 * @author l
 *
 */
@Component
public class BaiseeSysParamCache {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	// 服务器业务逻辑层
	@Autowired
	private BaiseeSystemParamMapper baiseeSystemParamMapper;

	// 缓存的工具类
	@Resource(name = "redisUtils")
	private RedisUtils redisUtils;

	// 当前缓存的一个对象
	public static BaiseeSysParamCache utils;

	/**
	 * 提供了一个模拟构造的注解
	 */
	@PostConstruct
	public void init() {
		logger.debug("初始化文件服务器参数开时");
		// 删除缓存的内容
		redisUtils.del(CacheKeyEnum.FTP_SERVER_LIST.getCacheKey());
		redisUtils.del(CacheKeyEnum.FTP_SERVER_ITEM.getCacheKey());
		// 读取所有的简单代码。
		List<BaiseeSystemParam> results = baiseeSystemParamMapper.listAll();
		// 设置全部的已配置服务器
		redisUtils.set(CacheKeyEnum.FTP_SERVER_LIST.getCacheKey(), results, null);
		// 循环设置Ftp服务器单个的缓存
		for (BaiseeSystemParam item : results) {
			redisUtils.mset(CacheKeyEnum.FTP_SERVER_ITEM.getCacheKey(), String.valueOf(item.getParamCode()), item);
		}
		utils = this;
		logger.debug("初始化文件服务器参数结束");
	}

	
 
    public static void main(String[] args){
//    	telnet 192.168.134.128 6379
    	Jedis jedis = new Jedis("192.168.1.27", 6379);
    	jedis.auth("password");
		jedis.set("shenlinnan", "hahahahahaha");
		System.out.println(jedis.get("shenlinnan"));
		jedis.close();
    }

}
