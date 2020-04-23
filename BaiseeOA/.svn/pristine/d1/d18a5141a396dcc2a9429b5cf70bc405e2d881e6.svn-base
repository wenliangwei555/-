package cn.baisee.oa.dao.stu;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.examination.BaiseeItemBank;
import cn.baisee.oa.model.examination.BaiseeItembankTestpaper;
/**
 * 试题Mapper
 * @author jxx
 *
 */
@Datasource(DatasourceTypes.STU)
public interface BaiseeItemBankMapper {
	
	List<BaiseeItemBank> getCheckitemBankById( List<String> iId);
	
	int getCheckCountItemBankById( List<String> iId);
	
	/**
	 * 根据tid查询题目的数量 
	 * @param tid
	 * @return
	 */
	int getItemBankCount(String tid);
	
	/**
	 *  查询所有的题库信息
	 * @return
	 */
	List<BaiseeItemBank> selectitemBankList( Map<String, Object> maps);
	
	/**
	 * 根据主键查询详细题库信息
	 * @param iId主键
	 * @return
	 */
	BaiseeItemBank getitemBankById(String iId);
	
	/**
	 *  根据题目查询数据是否存在重复
	 * @param subject 题目
	 * @return
	 */
	int getitemBankBySubject(String subject);
	
	/**
	 *  新增试题
	 * @param itemBank
	 * @return
	 */
	int saveitemBank(BaiseeItemBank itemBank);
	
	/**
	 *   根据题目查询数据是否存在重复数据2
	 * @param map
	 * @return
	 */
	int getitemBankBySubject1(Map<String, Object> map);
	
	/**
	 *  修改试题
	 * @param itemBank
	 * @return
	 */
	int updateItemBank(BaiseeItemBank itemBank);
	
	/**
	 *  查询试题是否被试卷使用着
	 * @param string
	 * @return
	 */
	List<BaiseeItembankTestpaper> selectItembankTestpaper(String ids);
	
	/**
	 *  根据Id删除试题
	 * @param ids
	 */
	void delitemBankById(String ids);
	
	/**
	 * 根据试卷Id查询关联试题
	 * @param tid
	 * @return
	 */
	List<BaiseeItemBank> selectItemBankListById(String tid);
	
	/**
	 *  根据类型查看试题
	 * @param did
	 * @return
	 */
	List<BaiseeItemBank> selectTypeQuery(String did);
	
	List<BaiseeItemBank> selectTypeQuery1(Map<String, Object> map);
	
}
