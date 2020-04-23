package cn.baisee.oa.core.context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.baisee.oa.core.context.data.GroupData;
import cn.baisee.oa.core.context.data.ICIPData;
import cn.baisee.oa.core.context.data.InvalidArgumentException;
import cn.baisee.oa.core.context.data.ObjectNotFoundException;

/**
 * 描述:统一资源上下文 统一资源上下文是用来保存数据的地方, 它是一个树形的结构, 每个统一资源上下文都 可以拥有一个父节点和多个子节点
 * 
 */
public class ICIPContext implements Serializable, Map<String, ICIPData> {

	private static final Log logger = LogFactory.getLog(ICIPContext.class);

	private static final long serialVersionUID = 3826727331658990270L;

	// 名称
	private String name;

	// 父节点名称
	private String parentContextName;

	// 父节点
	private ICIPContext parent;

	// 子节点列表
	private List<ICIPContext> childs;

	// 上下文数据
	private GroupData dataElement;

	/**
	 * 构造函数
	 */
	public ICIPContext() {
		this.dataElement = new GroupData();
	}

	/**
	 * 构造函数
	 * 
	 * @param name
	 *            名称
	 */
	public ICIPContext(String name) {
		this.name = name;
		this.dataElement = new GroupData();
	}

	/**
	 * 获取名称
	 * 
	 * @return 名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * 
	 * @param name
	 *            名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取父节点名称
	 * 
	 * @return the parentContextName 父节点名称
	 */
	public String getParentContextName() {
		return parentContextName;
	}

	/**
	 * 设置父节点名称
	 * 
	 * @param parentContextName
	 *            父节点名称
	 */
	public void setParentContextName(String parentContextName) {
		this.parentContextName = parentContextName;
	}

	/**
	 * 获取父节点
	 * 
	 * @return the parent 父节点上下文
	 */
	public ICIPContext getParent() {
		return parent;
	}

	/**
	 * 设置父节点
	 * 
	 * @param parent
	 *            父节点上下文
	 */
	public void setParent(ICIPContext parent) {
		this.parent = parent;
	}

	/**
	 * 获取上下文数据
	 * 
	 * @return the dataElement 上下文数据
	 */
	public GroupData getDataElement() {
		return dataElement;
	}

	/**
	 * 设置上下文数据
	 * 
	 * @param dataElement
	 *            上下文数据
	 */
	public void setDataElement(GroupData dataElement) {
		this.dataElement = dataElement;
	}

	/**
	 * 将当前上下文关联到指定的父节点上下文
	 * 
	 * @param context
	 *            父节点
	 */
	public void chainedTo(ICIPContext context) {
		this.parent = context;
		context.addChildContext(this);
	}

	/**
	 * 取消当前上下文和其父节点的关联
	 */
	public void unChain() {
		if (this.parent == null) {
			return;
		}

		this.parent.removeChildContext(this);
		this.parent = null;
	}

	/**
	 * 根据名称获取指定的子节点
	 * 
	 * @param ctxName
	 *            指定的名称
	 * @return 子节点
	 */
	public ICIPContext getChildContext(String ctxName) {
		if (this.childs == null) {
			return null;
		}

		for (int i = 0; i < this.childs.size(); i++) {
			ICIPContext aCtx = this.childs.get(i);
			if (ctxName.equals(aCtx.getName())) {
				return aCtx;
			}

			aCtx = aCtx.getChildContext(ctxName);
			if (aCtx != null) {
				return aCtx;
			}
		}

		return null;
	}

	/**
	 * 添加子节点
	 * @param context
	 *            子节点
	 */
	public synchronized void addChildContext(ICIPContext context) {
		if (this.childs == null) {
			this.childs = new ArrayList<ICIPContext>();
		}

		if (this.childs.contains(context)) {
			return;
		}

		this.childs.add(context);
	}

	/**
	 * 删除子节点
	 * 
	 * @param context
	 */
	public synchronized void removeChildContext(ICIPContext context) {
		if (this.childs != null) {
			this.childs.remove(context);
		}
	}

	/**
	 * 统计子节点数目
	 * 
	 * @return 子节点数目
	 */
	public int getChildCount() {
		return this.childs.size();
	}

	/**
	 * 从上下文中获取指定名称的数据对象 如果在当前上下文中没有找到, 则会沿着树形结构一直向父节点上下文查找, 直到找到为止
	 * 如果在最高父节点上下文中没有找到, 则返回NULL
	 * 
	 * @param dataName
	 *            指定的数据对象名称
	 * @return 要获取的数据对象
	 */
	public ICIPData getICIPData(String dataName) {
		ICIPData element = this.dataElement.getICIPData(dataName);

		if (element != null) {
			return element;
		}

		if (this.parent != null) {
			element = this.parent.getICIPData(dataName);
			if (element != null) {
				return element;
			}
		}

		return null;
	}

	/**
	 * 向上下文中添加数据对象 如果上下文中已经存在相同名称的数据对象, 则覆盖原来的数据对象
	 * 
	 * @param dataElement
	 *            要添加的数据对象
	 * @throws InvalidArgumentException
	 *             如果数据对象的名称为空则返回参数错误异常
	 */
	public void addICIPData(ICIPData dataElement) {
		this.dataElement.addICIPData(dataElement);
	}

	/**
	 * 向上下文中添加单个数据 如果上下文中已经存在相同名称的单个数据, 则覆盖原来的单个数据
	 * 
	 * @param name
	 *            单个数据的名称
	 * @param value
	 *            单个数据的值
	 * @throws InvalidArgumentException
	 *             如果数据对象的名称为空则返回参数错误异常
	 */
	public void addFieldData(String name, Object value) {
		this.dataElement.addFieldData(name, value);
	}

	/**
	 * 从上下文中移除指定名称的数据对象 如果指定名称的数据对象不存在, 则返回NULL
	 * 
	 * @param dataName
	 *            指定的数据对象名称
	 * @return 被移除的数据对象
	 */
	public ICIPData removeICIPData(String dataName) {
		return this.dataElement.removeICIPData(dataName);
	}

	/**
	 * 从上下文中获取指定名称的单个数据的值 如果指定名称的单个数据不存在, 或者指定名称的数据对象不是单个数据, 则返回NULL
	 * 
	 * @param dataName
	 *            指定的单个数据名称
	 * @return 要获取的单个数据的值
	 */
	public Object getFieldDataValue(String dataName) {
		return this.dataElement.getFieldDataValue(dataName);
	}

	/**
	 * 设置上下文中指定名称的单个数据的值 新设置的值会覆盖旧值
	 * 
	 * @param name
	 *            指定的单个数据名称
	 * @param value
	 *            要设置的值
	 * @throws ObjectNotFoundException
	 *             如果指定名称的单个数据不存在, 或者指定名称的数据对象不是单个数据, 则返回对象未找到异常
	 */
	public void setFieldDataValue(String name, Object value) {
		this.dataElement.setFieldDataValue(name, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		ICIPContext context = new ICIPContext(this.name);
		context.setDataElement((GroupData) this.dataElement.clone());
		context.setParentContextName(this.parentContextName);

		if (this.parent != null) {
			context.chainedTo(parent);
		}

		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<context id=\"" + getName() + "\"");
		if (getParentContextName() != null) {
			buffer.append(" parent=\"");
			buffer.append(getParentContextName());
			buffer.append("\"");
		}
		buffer.append(">\n");

		if (this.dataElement != null) {
			buffer.append("\t<data refId=\"");
			buffer.append(this.dataElement.getName());
			buffer.append("\"/>");
			buffer.append("\n");
		}

		buffer.append("</context>");

		return buffer.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#size()
	 */
	@Override
	public int size() {
		if (this.dataElement == null) {
			return 0;
		}
		return this.dataElement.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		if (this.dataElement == null) {
			return true;
		}
		return this.dataElement.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	@Override
	public boolean containsKey(Object key) {
		try {
			ICIPData data = getICIPData(key.toString());
			return data != null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	@Override
	public boolean containsValue(Object value) {
		// if (this.dataElement == null) {
		// return true;
		// }
		// return this.dataElement.containsValue(value);
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public ICIPData get(Object key) {
		return getICIPData(key.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public ICIPData put(String key, ICIPData element) {
		element.setName(key);
		addICIPData(element);

		return element;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public ICIPData remove(Object key) {
		return removeICIPData(key.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends String, ? extends ICIPData> m) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#clear()
	 */
	@Override
	public void clear() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<String> keySet() {
		if (this.dataElement == null) {
			return null;
		}
		return this.dataElement.keySet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<ICIPData> values() {
		if (this.dataElement == null) {
			return null;
		}
		return this.dataElement.values();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Map#entrySet()
	 */
	@Override
	public Set<java.util.Map.Entry<String, ICIPData>> entrySet() {
		if (this.dataElement == null) {
			return null;
		}
		return this.dataElement.entrySet();
	}

}
