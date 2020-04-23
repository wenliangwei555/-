package cn.baisee.oa.importExcel.filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

/**
 * 过滤器链<br>
 * 待过滤的数据依次经过链上的各个过滤器，层层过滤
 * 
 * @param <T>
 *            待过滤的数据的类型
 * @param <R>
 *            过滤完成后返回的数据的类型
 */
public abstract class AbstractFilterChain<T, R> {
	/** 过滤器集合 */
	protected List<Filter<T, R>> filters = new ArrayList<>();

	/**
	 * 过滤数据
	 * 
	 * @param coll
	 *            待过滤的数据集合
	 * @return 将各个过滤器过滤后返回的数据集合汇总并返回
	 */
	public List<R> doFilter(List<T> list) {
		if (CollectionUtils.isEmpty(list))
			return null;
		List<R> returnList = new ArrayList<R>();
		for (Filter<T, R> filter : filters) {
			// 由各个过滤器进行层层过滤
			Collection<R> _returnList = filter.doFilter(list);
			if (CollectionUtils.isNotEmpty(_returnList))
				returnList.addAll(_returnList); // 汇总各个过滤器返回的数据
		}
		return returnList;
	}

}