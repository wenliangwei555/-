package cn.baisee.oa.core.context.data;

/**
 * 描述:单个数据对象 单个数据对象是最小的数据对象, 用来保存单个数据 例如, 姓名可以用单个数据对象来保存, 其XML描述如下: 
 * <field id="name" value="张三" />
 * 
 */
public class FieldData extends ICIPData implements java.io.Serializable {

	private static final long serialVersionUID = -6398322743825112319L;

	// 值
	private Object value;

	// 默认值
	private Object defaultValue;

	// 描述
	private String description;

	// 类型
	private String dataType;

	/**
	 * 构造函数
	 * 
	 * @param name
	 *            数据名称
	 */
	public FieldData(String name) {
		super(name);
	}

	/**
	 * 构造函数
	 * 
	 * @param name
	 *            数据名称
	 * @param value
	 *            数据值
	 */
	public FieldData(String name, Object value) {
		super(name);
		setValue(value);
	}

	/**
	 * 设置值
	 * 
	 * @param value
	 *            值
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * 获取值
	 * 
	 * @return
	 */
	public Object getValue() {
		if (this.value == null) {
			return this.defaultValue;
		} else {
			return this.value;
		}
	}

	/**
	 * 设置默认值
	 * 
	 * @param value
	 *            默认值
	 */
	public void setDefaultValue(Object value) {
		this.defaultValue = value;
	}

	/**
	 * 获取默认值
	 * 
	 * @return 默认值
	 */
	public Object getDefaultValue() {
		return this.defaultValue;
	}

	/**
	 * 设置描述
	 * 
	 * @param desc
	 *            描述
	 */
	public void setDescription(String desc) {
		this.description = desc;
	}

	/**
	 * 获取描述
	 * 
	 * @return 描述
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * 设置类型
	 * 
	 * @param type
	 *            类型
	 */
	public void setDataType(String type) {
		this.dataType = type;
	}

	/**
	 * 获取类型
	 * 
	 * @return 类型
	 */
	public String getDataType() {
		return this.dataType;
	}

	/**
	 * 克隆单个数据对象
	 */
	public FieldData clone() {
		FieldData field = new FieldData(getName());
		field.setValue(this.value);

		field.setDataType(this.dataType);
		field.setAppend(isAppend());
		return field;
	}

	/**
	 * 转换成字符串
	 * 
	 * @return 字符串
	 */
	public String toString() {
		return toString(0);
	}

	/**
	 * 转换成字符串
	 * 
	 * @return 字符串
	 */
	public String toString(int tabCount) {
		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < tabCount; i++) {
			buf.append("\t");
		}

		buf.append("<field id=\"");
		buf.append(getName());

		if (this.value != null) {
			buf.append("\" value=\"");
			buf.append(formatXML(this.value.toString()));
		}

		if (this.dataType != null) {
			buf.append("\" dataType=\"");
			buf.append(this.dataType);
		}

		buf.append("\"/>");

		return buf.toString();
	}

	/**
	 * 格式化XML
	 * 
	 * @param str
	 *            XML
	 * @return 字符串
	 */
	private String formatXML(String str) {
		if ((str == null) || (str.length() == 0)) {
			return str;
		}

		String result = str;
		result = result.replaceAll("\\&", "&amp;");
		result = result.replaceAll("\\<", "&lt;");
		result = result.replaceAll("\\>", "&gt;");

		return result;
	}

}
