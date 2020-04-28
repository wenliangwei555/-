package cn.baisee.oa.model;

import java.io.Serializable;
import java.util.List;

public class BaiseeMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	private String menuId;
	/**
	 * 菜单名称
	 */
	private String menuName;
	/**
	 * 上级菜单
	 */
	private String pId;
	/**
	 * 菜单地址
	 */
	private String menuUrl;

	/**
	 * 后端拦截，权限
	 */
	private String menuCode;

	/**
	 * 阶数（排序）
	 */
	private int orderNum;

	/**
	 * 菜单图标
	 */
	private String menuIcon;
	
	private List<BaiseeMenu> childs;

	private String isMenu;

	/**
	 * 是否启用0否1是
	 */
	private String isShow;
	
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId == null ? null : menuId.trim();
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName == null ? null : menuName.trim();
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId == null ? null : pId.trim();
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl == null ? null : menuUrl.trim();
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setChilds(List<BaiseeMenu> childs) {
		this.childs = childs;
	}

	public List<BaiseeMenu> getChilds() {
		return childs;
	}

	public boolean hasChild() {
		if (childs != null && childs.size() > 0)
			return true;
		return false;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setIsMenu(String isMenu) {
		this.isMenu = isMenu;
	}

	public String getIsMenu() {
		return isMenu;
	}
	
	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	@Override
	public String toString() {
		return new StringBuffer("menuId:").append(menuId).append(";menuName:").append(menuName).append(";pId:").append(pId).append(";menuUrl:").append(menuUrl).append(";menuCode:")
				.append(menuCode).append(";orderNum:").append(orderNum).append(";isMenu:").append(isMenu).toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj || obj.toString().equals(this.toString()))
			return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	
	
	
}
