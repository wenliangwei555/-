package cn.baisee.oa.utils;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.baisee.oa.constants.CacheKeyEnum;
import cn.baisee.oa.dao.baisee.BaiseeSystemParamMapper;
import cn.baisee.oa.model.BaiseeMenu;
import cn.baisee.oa.model.BaiseeSystemParam;
import cn.baisee.oa.service.MenuService;
/**
 * 缓存操作类
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("unchecked")
public class ExtCacheUtils {
	
	private final static Logger log = LoggerFactory
			.getLogger(ExtCacheUtils.class);
	/**
	 * 清除 缓存
	 */
	public static void clearAllCache(String userId) {
		
		System.out.println("开始刷新菜单缓存");
		RedisUtils.utils.del(CacheKeyEnum.MENU_USER_INFO.getCacheKey() + userId);
		
		MenuService menuService = (MenuService) ApplicationContextUtil.getBean(MenuService.class);
		List<BaiseeMenu> cacheMenu = menuService.selectChilds("0");
		RedisUtils.utils.set(CacheKeyEnum.MENU_ALL.getCacheKey(), cacheMenu, null);
		System.out.println("结束刷新菜单缓存");
	}
	/**
	 * 获取缓存中 按照父子结构进行排列的菜单信息
	 * @return
	 */
	public static List<BaiseeMenu> selectAllMenu() {
		List<BaiseeMenu> cacheMenu = (List<BaiseeMenu>) RedisUtils.utils.get(CacheKeyEnum.MENU_ALL.getCacheKey());
		if (cacheMenu == null) {
			// 重新加载全部菜单
			MenuService menuService = (MenuService) ApplicationContextUtil.getBean(MenuService.class);
			cacheMenu = menuService.selectChilds("0");
			RedisUtils.utils.set(CacheKeyEnum.MENU_ALL.getCacheKey(), cacheMenu, null);
		}
		return cacheMenu;
	}

	/**
	 * 获取缓存中
	 * @param userId
	 * @return
	 */
	public static List<BaiseeMenu> selectMenuByUserId(String userId) {
		List<BaiseeMenu> userMenuInfo = (List<BaiseeMenu>) RedisUtils.utils
				.get(CacheKeyEnum.MENU_USER_INFO.getCacheKey() + userId);
		if (userMenuInfo == null) {
			// 重新加载全部菜单
			MenuService menuService = (MenuService) ApplicationContextUtil.getBean(MenuService.class);
			userMenuInfo = menuService.getMenusByUserId(userId);
			RedisUtils.utils.set(CacheKeyEnum.MENU_ALL.getCacheKey() + userId, userMenuInfo, null);
		}
		return userMenuInfo;
	}
	
	public static String getSysParamString(String paramCode) {
		BaiseeSystemParam param = getSysParam(paramCode);
		if (param != null)
			return param.getParamValue();
		log.debug("---------------------------------- [" + paramCode
				+ "]系统参数不存在 --------------------------------");
		return null;
	}
	
	public static BaiseeSystemParam getSysParam(String paramCode) {
		Object o = RedisUtils.utils.mget(CacheKeyEnum.FTP_SERVER_ITEM.getCacheKey(), paramCode);
		if (o != null)
			return (BaiseeSystemParam) o;
		log.debug("===================== (缓存)系统参数[" + paramCode + "]为空, 从数据库中取");
		final BaiseeSystemParamMapper mapper = (BaiseeSystemParamMapper) ApplicationContextUtil
				.getBean(BaiseeSystemParamMapper.class);
		o = mapper.findParamByCode(paramCode);
		if (o != null)
			return (BaiseeSystemParam) o;
		return null;
	}
}
