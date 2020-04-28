package cn.baisee.oa.utils;

import java.util.concurrent.TimeUnit;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * redis 缓存工具
 * 
 * @author l
 *
 */
public class RedisUtils {

	// redis 操作的工具类
	private RedisTemplate<String, Object> redisTemplate;

	public static RedisUtils utils;

	/**
	 * 存储
	 * 
	 * @param key      存储Key
	 * @param value    存储值
	 * @param liveTime 存活时间--以秒计算 如果不设置存活时间直接设置null
	 */
	public void set(final String key, final Object value, Long liveTime) {

		if (liveTime == null) {
			redisTemplate.opsForValue().set(key, value);
		} else {
			redisTemplate.opsForValue().set(key, value, liveTime, TimeUnit.SECONDS);
		}

	}

	public void set(final String key, final Object value) {
		set( key, value, null);
	}

	/**
	 * 获取
	 * 
	 * @param key
	 * @param value
	 */
	public Object get(final String key) {
		return redisTemplate.opsForValue().get(key);
	}

	/**
	 * 删除 -- 单个值，集合
	 * 
	 * @param key
	 * @param value
	 */
	public void del(String key) {
		redisTemplate.delete(key);
	}

	/**
	 * 追加
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public Integer append(String key, String value) {
		Integer result = redisTemplate.opsForValue().append(key, value);
		return result;
	}

	/**
	 * 设置Map值
	 * 
	 * @param redisKey 存储Key
	 * @param mapKey   Map结构Key
	 * @param value    Map值
	 */
	public void mset(String redisKey, String mapKey, Object value) {
		redisTemplate.opsForHash().put(redisKey, mapKey, value);
	}

	/**
	 * 获取指定Map的Key值
	 * 
	 * @param redisKey
	 * @param mapKey
	 * @return
	 */
	public Object mget(String redisKey, String mapKey) {
		Object object = redisTemplate.opsForHash().get(redisKey, mapKey);
		return object;
	}

	/**
	 * 删除Map指定key
	 * 
	 * @param redisKey
	 * @param mapKey
	 */
	public void mdel(String redisKey, String mapKey) {
		redisTemplate.opsForHash().delete(redisKey, mapKey);
	}

	/**
	 * 检测是否存在
	 * 
	 * @param key
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public Boolean exists(final String key) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.exists(key.getBytes());
			}
		});
	}

	/**
	 * 
	 * 清空数据库所有keys
	 * 
	 * @return
	 */
	public String flushDB() {
		return redisTemplate.execute(new RedisCallback<String>() {
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				connection.flushDb();
				return "ok";
			}
		});
	}

	/**
	 * 查询一共多少Key
	 * 
	 * @return
	 */
	public long dbSize() {
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.dbSize();
			}
		});
	}

	/**
	 * 
	 * ping命令
	 * 
	 * @return
	 */
	public String ping() {
		return redisTemplate.execute(new RedisCallback<String>() {
			public String doInRedis(RedisConnection connection) throws DataAccessException {

				return connection.ping();
			}
		});
	}

	/**
	 * 增加大小
	 * 
	 * @param key
	 * @param add
	 */
	public void set(final String key, final Integer value, final Integer liveTime) {

		redisTemplate.execute(new RedisCallback<Integer>() {
			public Integer doInRedis(RedisConnection connection) throws DataAccessException {
				// 设置值
				connection.set(key.getBytes(), redisTemplate.getStringSerializer().serialize(String.valueOf(value)));
				// 设置过期时间
				if (liveTime != null) {
					Boolean status = connection.expire(key.getBytes(), liveTime);
					if (!status) {
						return 1;
					}
				}
				return 0;
			}
		});

	}

	/**
	 * 获取字符串
	 * 
	 * @return
	 */
	public String getString(final String key) {
		return redisTemplate.execute(new RedisCallback<String>() {
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				return redisTemplate.getStringSerializer().deserialize(connection.get(key.getBytes()));
			}
		});
	}

	// 获取操作对象+1
	public Long incr(final String key) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.incr(key.getBytes());
			}
		});
	}

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
		utils = this;
	}

}
