package cn.baisee.oa.service;

import cn.baisee.oa.model.BaiseeDossierCategory;
import cn.baisee.oa.page.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;

public interface BaiseeDossierCategoryService {
	
	/**
	 * 查询类别名称表
	 * @param pageNum	每页显示多少条
	 * @param pageSize	显示第几页
	 * @param map		查询条件
	 * @return
	 */
    public PageInfo<BaiseeDossierCategory> getDossierCategory(Integer pageNum, Integer pageSize, Map<String, String> map);
    
    /**
	 * 批量删除任务
	 * @param ids	数组中装的是任务id
	 * @return
	 */
    public Integer delDossierCategory(String[] ids);
    /**
	 * 根据报id查询类别名称表
	 * @param id	任务id
	 * @return
	 */
    public BaiseeDossierCategory getById(String id);
    /**
	 * 新增或修改任务
	 * @return
	 */
    public Integer saveOrUpdateAssigmentManage(BaiseeDossierCategory baiseeDossierCategory);
    /**
	 * 查询档案类别表中是否已有此类别名称
	 * @param cName	类别名称
	 * @return 类别集合
	 *///        判读是否有数据
	public Map<String, String> checkAssignmentPerson(java.lang.String cName);
	
	/**
	 * 获取所有的档案类型
	 * @return
	 * liangfeng
	 */
	public List<BaiseeDossierCategory> getCategoryAll();
}
