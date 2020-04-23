package cn.baisee.oa.importExcel.splitor;

import java.util.Collection;

/**
 * 数据拆分器
 * 
 * 
 * @param <T>
 *            待拆分的数据的类型
 */
public interface Splitor<T> {
	/**
	 * 拆分数据
	 * 
	 * @param coll
	 *            原始数据集合
	 * @return 拆分完成后的数据集合
	 * @throws Exception
	 *             执行过程过有异常时统一抛出<code>java.lang.Exception</code>
	 */
	Collection<T> doSplit(Collection<T> coll) throws Exception;
}
