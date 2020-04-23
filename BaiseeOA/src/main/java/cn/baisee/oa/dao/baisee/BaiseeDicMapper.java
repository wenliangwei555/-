package cn.baisee.oa.dao.baisee;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.IcipBusDict;

@Datasource(DatasourceTypes.OA)
public interface BaiseeDicMapper {
	/**
	 * 添加字典
	 * @param dic
	 * @return
	 */
	int insertDic(IcipBusDict dic);
	/**
	 * 查询所有字典,可以传参数
	 * @param map
	 * @return
	 */
	List<IcipBusDict> selectAllDic(Map<String, Object> map);
	/**
	 * 根据id查询所有
	 */
	IcipBusDict selectDicById(Map<String, Object> map);
	/**
	 * 修改
	 * @param dic
	 * @return
	 */
	int updateDicById(IcipBusDict dic);
	/**
	 * 根据字典值查询
	 * @param dictValue
	 * @return
	 */
	 IcipBusDict selectDicByValue(Map<String, Object> map);
	/**
	 * 批量删除
	 * @param dictIds
	 * @return
	 */
	Integer deleteDic(String[] dictIds);
	/**
	 *       根据字典名称查询值
	 * @param name
	 * @return
	 */
	List<IcipBusDict> selectIcipBusDictByDictName(@Param("dictName")String dictName);
	
	/**
	 *       根据字典名称查询值
	 * @param name
	 * @return
	 */
	List<IcipBusDict> getDepartments(Map<String, Object> map);
	
	/**
	 * @return
	 */
	List<IcipBusDict> getDepartmentList();
	
	/**
	 * 校验部门名称
	 * @param dictDestination
	 * @return
	 */
	List<IcipBusDict> checkDeptName(@Param("dictDestination")String dictDestination);
	
	/**
	 * 修改
	 * @param dic
	 * @return
	 */
	int update(IcipBusDict dic);
	
	/**
	 * @return
	 */
	List<IcipBusDict> getRoles(Map<String, Object> map);
	
	List<IcipBusDict> selectListByMap(Map<String, Object> map);
	/**
	 * liangfeng
	 * @return
	 */
	List<IcipBusDict> getRoleByDId(@Param("dId")String dId);
	
	/**
	 * 根据部门value查询角色
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 * liangfeng
	 */
	List<IcipBusDict> getDpetValue(@Param("dValue")String dValue);
	
	/**
	 * 添加部门
	 * @param dic
	 * @return
	 * liangfeng
	 */
	int insertDept(IcipBusDict dic);
}
