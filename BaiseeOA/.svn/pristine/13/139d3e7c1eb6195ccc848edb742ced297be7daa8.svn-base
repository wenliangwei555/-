package cn.baisee.oa.dao.baisee;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.BaiseeDossierCategory;

import java.util.List;
import java.util.Map;

@Datasource(DatasourceTypes.OA)
public interface BaiseeDossierCategoryMapper {
	/**
	 * 批量删除任务
	 * @param ids	数组中装的是任务id
	 * @return
	 */
    public Integer delDossierCategory(String[] ids);
    /**
	 * 查询档案类别表
	 * @param map		查询条件
	 * @return
	 */
    public List<BaiseeDossierCategory> getDossierCategor(Map<String, String> map);
	/**
	 * 根据报id查询档案类别表
	 * @param id	任务id
	 * @return
	 */
    public BaiseeDossierCategory getById(String id);
    /**
	 * 修改任务
	 * @param baiseeDossierCategory	任务对象
	 * @return
	 */
    public Integer updateBaiseeCategory(BaiseeDossierCategory baiseeDossierCategory);
    /**
	 * 新增任务
	 * @param baiseeDossierCategory	任务对象
	 * @return
	 */
    public Integer saveOrUpdateAssigmentManage(BaiseeDossierCategory baiseeDossierCategory);
    /**
	 * 查询档案类别表中是否已有此类别名称
	 * @param cName	类别名称
	 * @return 
	 */
    public List<BaiseeDossierCategory> checkPname(String cName);
    
    /**
     * 获取所有的档案类型
     * @return
     * liangfeng
     */
    List<BaiseeDossierCategory> getCategoryAll();
}

