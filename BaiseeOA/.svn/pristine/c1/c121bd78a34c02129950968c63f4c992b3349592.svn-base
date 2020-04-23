package cn.baisee.oa.dao.stu;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.examination.BaiseeTestpaper;

@Datasource(DatasourceTypes.STU)
public interface BaiseeTestpaperMapper {
	/**
	 *  查询所有试卷
	 * @return
	 */
	List<BaiseeTestpaper> testPaperList( Map<String, Object> maps);
	
	/**
	 *  根据Id删除试卷
	 * @param ids
	 */
	void delTestPaperByIds(String[] ids);

	/**
	 *  删除试卷-试题中间表数据
	 * @param ids
	 */
	void delItembankTestpaperByTid(String[] ids);
	
	/**
	 *  
	 * @param createId
	 * @param paperName
	 * @return
	 */
	int addTestPaer(Map<String, Object> map);

	/**
	 *  新增试卷试题关联表
	 * @param map
	 */
	void addItembankTestpaper(Map<String, Object> map);
	
	/**
	 *  校验试卷名称是否存在重复的
	 * @param paperName
	 * @return
	 */
	int checkName(String paperName);
	
	/**
	 * 根据Id获取试卷信息
	 * @param tid
	 * @return
	 */
	BaiseeTestpaper getTestpaperBytid(String tid);
	/**
	 *  过滤当前ID的试卷名称再查询是否存在重复数据
	 * @param paperName
	 * @param tid
	 * @return
	 */
	int checkName2(Map<String, Object> map);
	
	/**
	 * 根据Id修改信息
	 * @param map
	 * @return
	 */
	int updateTestPaer(Map<String, Object> map);
}
