package cn.baisee.oa.core.context.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 描述:重复数据对象
 * 重复数据对象是数组结构, 它包含一个或多个结构一致的分组数据对象
 * 例如, 多个账户信息, 可以用重复数据对象保存, 其XML描述如下: 
 * <repeat id="accInfoList">
 *   <group>
 *     <field id="accNo" value="11111111" />
 *     <field id="accName"  value="活期账户" />
 *   </group>
 *   <group>
 *     <field id="accNo"  value="22222222" />
 *     <field id="accName"  value="定期账户" />
 *   </group>
 *   <group>
 *     <field id="accNo"  value="33333333" />
 *     <field id="accName"  value="美元账户" />
 *   </group>
 * </repeat>
 * 重复数据对象的下分组数据对象只能通过下标进行访问, 下标从0开始
 * 例如, accInfoList.get(0)拿到的就是第一个账户, 其accNo的值为"11111111"
 * 
 */
public class RepeatData extends ICIPData implements List<GroupData> {

	// 分组数据对象集合
	private List<GroupData> datas;

	/**
	 * 构造函数
	 */
	public RepeatData() {
		this.datas = new ArrayList<GroupData>();
	}

	/**
	 * 构造函数
	 * 
	 * @param name
	 *            名称
	 */
	public RepeatData(String name) {
		super(name);
		this.datas = new ArrayList<GroupData>();
	}

	/**
	 * 获取指定序号的分组数据对象
	 * 
	 * @param idx
	 *            指定的序号
	 * @return 要获取的分组数据对象
	 * @throws InvalidArgumentException
	 *             序号超过重复数据对象的长度时, 抛出参数异常错误
	 */
	public GroupData getGroupDataAt(int idx) {
		if (idx >= this.datas.size()) {
			throw new InvalidArgumentException(
					"idx out of bound while get dataElement from RepeatData!");
		}

		return this.datas.get(idx);
	}

	/**
	 * 移除指定序号的分组数据对象 如果序号超过重复数据对象的长度, 则返回NULL
	 * 
	 * @param idx
	 *            指定的序号
	 * @return 被移除的分组数据对象
	 */
	public GroupData removeGroupDataAt(int idx) {
		if (idx >= this.datas.size()) {
			return null;
		}

		return this.datas.remove(idx);
	}

	/**
	 * 移除全部分组数据对象
	 */
	public void removeAll() {
		this.datas.clear();
	}

	/**
	 * 添加分组数据对象
	 * 
	 * @param element
	 *            分组数据对象
	 */
	public void addGroupData(GroupData element) {
		if (isAppend()) {
			element.setAppend(isAppend());
		}

		this.datas.add(element);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.icip.framework.context.data.ICIPData#clone()
	 */
	@Override
	public RepeatData clone() {
		RepeatData repeat = new RepeatData(getName());
		repeat.setAppend(isAppend());

		for (int i = 0; i < this.datas.size(); i++) {
			GroupData element = this.datas.get(i);
			repeat.addGroupData(element.clone());
		}

		return repeat;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.icip.framework.context.data.ICIPData#toString()
	 */
	@Override
	public String toString() {
		return toString(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.icip.framework.context.data.ICIPData#toString(int)
	 */
	@Override
	public String toString(int tabCount) {
		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < tabCount; i++) {
			buf.append("\t");
		}

		buf.append("<repeat id=\"");
		buf.append(getName());
		buf.append("\" append=\"" + isAppend() + "\">\n");

		if (this.datas.size() > 0) {
			for (int i = 0; i < this.datas.size(); i++) {
				ICIPData element = this.datas.get(i);
				buf.append(element.toString(tabCount + 1));
				buf.append("\n");
			}
		}

		for (int i = 0; i < tabCount; i++) {
			buf.append("\t");
		}

		buf.append("</repeat>");

		return buf.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.icip.framework.context.data.ICIPData#setAppend(java.lang.String)
	 */
	@Override
	public void setAppend(boolean append) {
		super.setAppend(append);
		for (int i = 0; i < this.datas.size(); i++) {
			this.datas.get(i).setAppend(append);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#size()
	 */
	@Override
	public int size() {
		return this.datas.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return this.datas.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object o) {
		return this.datas.contains(o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#iterator()
	 */
	@Override
	public Iterator<GroupData> iterator() {
		return this.datas.iterator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#toArray()
	 */
	@Override
	public Object[] toArray() {
		return this.datas.toArray();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#toArray(T[])
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		return this.datas.toArray(a);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#add(java.lang.Object)
	 */
	@Override
	public boolean add(GroupData e) {
		return this.datas.add(e);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object o) {
		return this.datas.remove(o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		return this.datas.containsAll(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends GroupData> c) {
		Iterator<?> list = c.iterator();
		while (list.hasNext()) {
			Object obj = list.next();
			if (obj instanceof ICIPData) {
				((ICIPData) obj).setAppend(isAppend());
			}
		}

		return this.datas.addAll(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#addAll(int, java.util.Collection)
	 */
	@Override
	public boolean addAll(int index, Collection<? extends GroupData> c) {
		Iterator<?> list = c.iterator();
		while (list.hasNext()) {
			Object obj = list.next();
			if (obj instanceof ICIPData) {
				((ICIPData) obj).setAppend(isAppend());
			}
		}

		return this.datas.addAll(index, c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		return this.datas.removeAll(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		return this.datas.retainAll(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#clear()
	 */
	@Override
	public void clear() {
		this.datas.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#get(int)
	 */
	@Override
	public GroupData get(int index) {
		return this.datas.get(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#set(int, java.lang.Object)
	 */
	@Override
	public GroupData set(int index, GroupData element) {
		return this.datas.set(index, element);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#add(int, java.lang.Object)
	 */
	@Override
	public void add(int index, GroupData element) {
		this.datas.add(index, element);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#remove(int)
	 */
	@Override
	public GroupData remove(int index) {
		return this.datas.remove(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#indexOf(java.lang.Object)
	 */
	@Override
	public int indexOf(Object o) {
		return this.datas.indexOf(o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#lastIndexOf(java.lang.Object)
	 */
	@Override
	public int lastIndexOf(Object o) {
		return this.datas.lastIndexOf(o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#listIterator()
	 */
	@Override
	public ListIterator<GroupData> listIterator() {
		return this.datas.listIterator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#listIterator(int)
	 */
	@Override
	public ListIterator<GroupData> listIterator(int index) {
		return this.datas.listIterator(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#subList(int, int)
	 */
	@Override
	public List<GroupData> subList(int fromIndex, int toIndex) {
		return this.datas.subList(fromIndex, toIndex);
	}

}
