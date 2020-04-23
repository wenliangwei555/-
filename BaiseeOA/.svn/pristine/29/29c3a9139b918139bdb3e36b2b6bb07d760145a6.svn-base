package cn.baisee.oa.service;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.model.ReturnInfo;
import cn.baisee.oa.model.examination.BaiseeItemBank;
import cn.baisee.oa.model.examination.BaiseeItembankTestpaper;
import cn.baisee.oa.page.pagehelper.PageInfo;

public interface BaiseeItemBankService {
	
	List<BaiseeItemBank> getCheckitemBankById( List<String> iId);
	
	int getCheckCountItemBankById( List<String> iId);
	
	/**
	 * 查询题库信息
	 * @return
	 */
	PageInfo<BaiseeItemBank> selectitemBankList( Map<String, Object> map);
	/**
	 *  根据Id查询题库信息
	 * @param iId
	 * @return
	 */
	BaiseeItemBank getitemBankById(String iId);

	/**
	 *  新增试题
	 * @param itemBank
	 * @return
	 */
	int saveitemBank(BaiseeItemBank itemBank);
	/**
	 * 根据Id修改试题
	 * @param itemBank
	 * @return
	 */
	int updateItemBank(BaiseeItemBank itemBank);
	/**
	 * 修改的时候判断题目是否有重复数据
	 * @param subject
	 * @param iId
	 * @return
	 */
	boolean getitemBankBySubject1(String subject, String iId);
	/**
	 *  根据Id删除试题
	 * @param ids
	 * @return
	 */
	ReturnInfo delitemBankById(String[] ids);
	/**
	 * 根据试卷Id查询关联试题
	 * @param tid
	 * @return
	 */
	PageInfo<BaiseeItemBank> selectItemBankListById(String tid, Integer pageNum, Integer pageSize);
	
	/**
	 * 根据类型查询试题 
	 * @param did
	 * @return
	 */
	List<BaiseeItemBank> selectTypeQuery(String did);
	
	List<BaiseeItemBank> selectTypeQuery1(String did, List<String> iIds);
	
	/**
	 *  所有试卷 js需要使用
	 * @return
	 */
	List<BaiseeItemBank> selectItem();
	/**
	 *  查询当前试题是否在被试卷使用着
	 * @return
	 */
	boolean whetherToOccupyOrNot(String iId);

}
