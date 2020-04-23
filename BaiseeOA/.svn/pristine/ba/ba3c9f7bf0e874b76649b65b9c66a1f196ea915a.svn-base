package cn.baisee.oa.importExcel.filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import cn.baisee.oa.importExcel.dto.ValidateResultDto;


/**
 * 数据校验器
 * 
 * 
 * @param <T>
 *            待校验的数据的类型
 * @param <R>
 *            校验完成后返回的数据的类型，是
 *            <code>com.icip.framework.service.importData.dto.ValidateResultDto</code>
 *            类的子类
 */
public abstract class AbstractValidator<T, R extends ValidateResultDto>
		implements Filter<T, R> {

	/**
	 * 对待过滤的数据进行校验，不合法的数据将会被过滤掉，同时将校验的结果作为一个集合返回
	 * 
	 * @param coll
	 *            待过滤的数据集合
	 * @return 校验结果的集合
	 */
	@Override
	public Collection<R> doFilter(Collection<T> coll) {
		if (CollectionUtils.isEmpty(coll))
			return null;
		List<R> returnList = new ArrayList<>();
		for (Iterator<T> iter = coll.iterator(); iter.hasNext();) {
			T t = (T) iter.next();
			// 开始对待过滤的集合中的每条数据进行校验
			R r = validate(t);
			if (!isValid(r)) { // 如果该条数据不合法
				returnList.add(r); // 将校验结果保存到要返回的集合中
				iter.remove(); // 从待过滤的集合中将该条数据移除
			}
		}
		return returnList;
	}

	/**
	 * 针对于每条数据进行校验，返回该条数据的校验结果
	 * 
	 * @param dto
	 *            待校验的数据
	 * @return 由于此方法需要被子类重写，因此这里只是返回null
	 */
	protected R validate(T dto) {
		return null;
	}

	/**
	 * 是否合法，这里针对的是每一条数据
	 * 
	 * @param r
	 *            数据的校验结果
	 * @return 依据数据的校验结果判断是否合法，合法则返回true
	 */
	protected boolean isValid(R r) {
		return r == null || r.isValid();
	}
}
