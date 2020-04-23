package cn.baisee.oa.model.goods;
/**
 * 物品类型
 * @author Administrator
 *
 */
public class BaiseeGoodsType{
	/**
	 * 物品父id
	 */
	private String typeId;
	/**
	 * 物品名称
	 */
	private String typeName;
	/**
	 * 物品子id
	 */
	private String pTypeId;
	/**
	 * 排序
	 */
	private int orderNum;
	/**
	 * 分类菜单等级0是物品等级1是物品类型等级
	 */
	private String isMune;
	
	
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getpTypeId() {
		return pTypeId;
	}
	public void setpTypeId(String pTypeId) {
		this.pTypeId = pTypeId;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public String getIsMune() {
		return isMune;
	}
	public void setIsMune(String isMune) {
		this.isMune = isMune;
	}
	
	
	
	
	
	
}
