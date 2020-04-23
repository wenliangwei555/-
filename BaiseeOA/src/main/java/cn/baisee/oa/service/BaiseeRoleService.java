package cn.baisee.oa.service;

import java.util.List;

import cn.baisee.oa.exception.OAServiceException;
import cn.baisee.oa.model.BaiseeRole;
import cn.baisee.oa.model.BaiseeRoleMenu;
import cn.baisee.oa.page.pagehelper.PageInfo;

public interface BaiseeRoleService {

	public void insertRole(BaiseeRole roleInfo, String[] menuIds) throws OAServiceException;
	
	public void updateRole(BaiseeRole roleInfo, String[] menuIds) throws OAServiceException;

	public boolean roleNameCheck(BaiseeRole roleInfo, String userId) throws OAServiceException;
	
	public PageInfo<BaiseeRole> getRoleList(int pageNum,int pageSize,String itemlableSearch);
	
	public boolean delRole(String roleId) throws OAServiceException ;
	
	public void delAllRole(String[] ids) throws OAServiceException;

	public List<BaiseeRoleMenu> getRoleMenusByRoleId(String roleId)  throws OAServiceException;

	public BaiseeRole getRoleById(String roleId)  throws OAServiceException;

	public List<BaiseeRole> selectRolesByUserId(String userId)  throws OAServiceException;

	public void saveRoleInfo(String userId, String roleName)  throws OAServiceException;
}
