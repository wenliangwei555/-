package cn.baisee.oa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.baisee.BaiseeRoleMapper;
import cn.baisee.oa.dao.baisee.BaiseeRoleMenuMapper;
import cn.baisee.oa.exception.OAServiceException;
import cn.baisee.oa.model.BaiseeRole;
import cn.baisee.oa.model.BaiseeRoleMenu;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeRoleService;
/**
 * 
 * @author Administrator
 *
 */
@Service
public class BaiseeRoleServiceImpl implements BaiseeRoleService {

	@Autowired
	private BaiseeRoleMenuMapper baiseeRoleMenuMapper;
	@Autowired
	private BaiseeRoleMapper baiseeRoleMapper;
	
	@Override
	public void insertRole(BaiseeRole roleInfo, String[] menuIds)
			throws OAServiceException {
		try {
			baiseeRoleMapper.insert(roleInfo);
			updateRelations(menuIds, roleInfo.getRoleId());
		} catch (Exception e) {
			throw new OAServiceException("新建权限失败", e);
		}

	}

	@Override
	public void updateRole(BaiseeRole roleInfo, String[] menuIds)
			throws OAServiceException {
		try {
			baiseeRoleMapper.updateByPrimaryKeySelective(roleInfo);
			updateRelations(menuIds, roleInfo.getRoleId());
		} catch (Exception e) {
			throw new OAServiceException("修改权限失败", e);
		}

	}
	
	private void updateRelations(String[] menuIds, String roleId) throws OAServiceException {
		if (menuIds == null || menuIds.length == 0)
			return;
		try {
			baiseeRoleMenuMapper.deleteByRoleId(roleId);
			for (String mId : menuIds) {
				BaiseeRoleMenu rm = new BaiseeRoleMenu();
				rm.setMenuId(mId);
				rm.setRoleId(roleId);
				baiseeRoleMenuMapper.insertRoleMenu(rm);
			}
		} catch (Exception e) {
			throw new OAServiceException("更新权限与菜单关联关系失败", e);
		}
	}

	public boolean roleNameCheck(BaiseeRole roleInfo, String userId) throws OAServiceException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleInfo", roleInfo);
		params.put("userId", userId);
		return baiseeRoleMapper.selectCountByName(params) > 0;
	}
	@Override
	public PageInfo<BaiseeRole> getRoleList(int pageNum, int pageSize,String itemlableSearch) {
		PageHelper.startPage(pageNum, pageSize);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("itemlableSearch", itemlableSearch);
		List<BaiseeRole> list=baiseeRoleMapper.selectAllRole(map);
		PageInfo<BaiseeRole> page=new PageInfo<BaiseeRole>(list);
		return page;
	}

	@Override
	public boolean delRole(String roleId) throws OAServiceException {
		try {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("roleId", roleId);
			int r=baiseeRoleMapper.delRole(map);
			int r1=baiseeRoleMenuMapper.deleteByRoleId(roleId);
			if (r>0 && r1>0) {
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			throw new OAServiceException("删除角色失败", e);
		}
	}

	@Override
	public void delAllRole(String[] ids) throws OAServiceException {
		try {
			baiseeRoleMapper.delAllRole(ids);
			baiseeRoleMenuMapper.delAllByRoleId(ids);
		} catch (Exception e) {
			throw new OAServiceException("批量删除角色失败", e);
		}
	}

	@Override
	public List<BaiseeRoleMenu> getRoleMenusByRoleId(String roleId) throws OAServiceException {
		try {
			return baiseeRoleMenuMapper.selectRoleMenuByRoleId(roleId);
		} catch (Exception e) {
			throw new OAServiceException("获取权限权限关系", e);
		}
	}

	@Override
	public BaiseeRole getRoleById(String roleId) throws OAServiceException  {
		try {
			return baiseeRoleMapper.selectByPrimaryKey(roleId);
		} catch (Exception e) {
			throw new OAServiceException("获取权限信息失败", e);
		}
	}

	@Override
	public List<BaiseeRole> selectRolesByUserId(String userId)
			throws OAServiceException {
		List<BaiseeRole> roles = new ArrayList<BaiseeRole>();
		List<String> lists = new ArrayList<String>();
		lists.add(userId);
		roles = baiseeRoleMapper.selectAllByCreateUser();
		return roles;
	}

	@Override
	public void saveRoleInfo(String userId, String roleId)
			throws OAServiceException {
		 Map<String, String> map = new HashMap<String, String>();
		 map.put("userId", userId);
		 map.put("roleId", roleId);
		 baiseeRoleMapper.insertRoleUser(map);
	}
}
