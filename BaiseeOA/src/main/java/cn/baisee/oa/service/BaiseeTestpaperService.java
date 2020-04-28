package cn.baisee.oa.service;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.model.ReturnInfo;
import cn.baisee.oa.model.examination.BaiseeItemBank;
import cn.baisee.oa.model.examination.BaiseeTestpaper;
import cn.baisee.oa.page.pagehelper.PageInfo;

public interface BaiseeTestpaperService {
	/**
	 * 查询试卷
	 * @return
	 */
	PageInfo<BaiseeTestpaper> testPaperList(Map<String, Object> maps);
	/**
	 *  删除试卷
	 * @param ids
	 * @return
	 */
	ReturnInfo delTestPaper(String[] ids);
	/**
	 *  根据Id查询试题
	 * @param customoldid
	 * @param standAlone
	 * @return
	 */
	List<BaiseeItemBank> selectTestPaperListById(String customoldid);
	/**
	 *  新增试题
	 * @param createId 自定义的Id
	 * @param iIds
	 * @return
	 */
	int addTestPaer(String iIds,String paperName, String userId, String examinationType, String userType);
	
	/**
	 *  校验试卷名称是否已存在
	 * @param paperName
	 * @return
	 */
	boolean checkName(String paperName, String tid);
	
	/**
	 *  查询所有的试卷
	 * @return
	 */
	List<BaiseeTestpaper> selectTestPaperList(String userType);
	/**
	 * 根据Id查询试卷信息	
	 * @param tid
	 * @return
	 */
	BaiseeTestpaper getTestpaperById(String tid);
	/**
	 * 校验信息
	 * @param paperName
	 * @param tid
	 * @param id
	 * @return
	 */
	ReturnInfo checkId(String paperName, String tid, String id);
	/**
	 *  
	 * @param iIds 试题表ID
	 * @param paperName 试卷名称
	 * @param userId 用户ID
	 * @param examinationType 试卷类型
	 * @param tid 主键
	 * @return
	 */
	int updateTestPaer(String iIds, String paperName, String userId, String examinationType, String tid);

}
