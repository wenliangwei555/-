package cn.baisee.oa.importExcel.filter;

import java.util.Collection;

/**
 * 数据过滤器
 * 
 * 
 * @param <T>
 *            待过滤的数据的类型
 * @param <R>
 *            过滤完成后返回的数据的类型
 */
public interface Filter<T, R> {
	/**
	 * 过滤数据
	 * 
	 * @param coll
	 *            待过滤的数据集合
	 * @return 参数中传递过来的集合的数据将会被部分过滤掉，返回的是另一种数据集合，这个集合中的数据的类型与待过滤的数据的类型可以不相同
	 */
	Collection<R> doFilter(Collection<T> coll);
}
