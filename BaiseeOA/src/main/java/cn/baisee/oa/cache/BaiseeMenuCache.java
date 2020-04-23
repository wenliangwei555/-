package cn.baisee.oa.cache;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.baisee.oa.constants.CacheKeyEnum;
import cn.baisee.oa.model.BaiseeMenu;
import cn.baisee.oa.service.MenuService;
import cn.baisee.oa.utils.RedisUtils;

/**
 * 左侧菜单启动加载进入缓存
 * @author Administrator
 *
 */
@Component
public class BaiseeMenuCache {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	// 服务器业务逻辑层
	@Autowired
	private MenuService menuService;

	// 缓存的工具类
	@Resource(name = "redisUtils")
	private RedisUtils redisUtils;

	// 当前缓存的一个对象
	public static BaiseeMenuCache utils;

	/**
	 * 提供了一个模拟构造的注解
	 */
	@PostConstruct
	public void init() {
		
		logger.debug("开始加载菜单缓存信息");
		// 删除缓存的内容
		redisUtils.del(CacheKeyEnum.MENU_ALL.getCacheKey());
		// 读取所有的简单代码。
		List<BaiseeMenu> results = menuService.selectChilds("0");
		// 设置全部的已配置服务器
		redisUtils.set(CacheKeyEnum.MENU_ALL.getCacheKey(), results, null);
		utils = this;
		logger.debug("结束加载菜单缓存信息");
	}

}
