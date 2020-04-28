package cn.baisee.oa.dao.baisee;

import java.util.List;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.exception.DaoException;
import cn.baisee.oa.model.BaiseeRoleMenu;

@Datasource(DatasourceTypes.OA)
public interface BaiseeRoleMenuMapper {
	/**
	 * 根据权限ID删除角色
	 * @param roleId
	 * @throws DaoException
	 */
	int deleteByRoleId(String roleId);
	/**
	 * 新增关联关系
	 * @param rm
	 * @throws DaoException
	 */
	void insertRoleMenu(BaiseeRoleMenu rm);
	/**
	 * 根据权限ID查询关联关系
	 * @param roleId
	 * @return
	 */
	List<BaiseeRoleMenu> selectRoleMenuByRoleId(String roleId);
	/**
	 *  根据权限ID批量删除
	 * @param ids
	 * @return
	 */
	int delAllByRoleId(String[] ids);
}