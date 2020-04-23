package cn.baisee.oa.dao.baisee;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.BaiseeRole;
import cn.baisee.oa.model.BaiseeRoleQueryEntity;

@Datasource(DatasourceTypes.OA)
public interface BaiseeRoleMapper {

	List<BaiseeRole> selectAll(BaiseeRoleQueryEntity queryInfo);

	List<BaiseeRole> selectByName(String roleName);
	
	int selectCountByName(Map<String, Object> params);

	int deleteByPrimaryKey(String roleId);

	int insert(BaiseeRole record);

	int insertSelective(BaiseeRole record);

	BaiseeRole selectByPrimaryKey(String roleId);

	int updateByPrimaryKeySelective(BaiseeRole record);

	int updateByPrimaryKey(BaiseeRole record);

	List<BaiseeRole> selectAllByCreateUser();
	
	List<BaiseeRole> selectAllRole(Map<String, Object> map);
	
	int delRole(Map<String, Object> map);
	
	void delAllRole(String[] ids);

	void insertRoleUser(Map<String, String> map);

	void deleteByUserIds(List<String> idsList);
}