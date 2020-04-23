package cn.baisee.oa.service;

import cn.baisee.oa.model.examination.BaiseeExamination;
import cn.baisee.oa.page.pagehelper.PageInfo;

public interface BaiseeExaminationService {

	/**
	 *  查询所有考试信息
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageInfo<BaiseeExamination> eInformationList(Integer pageNum, Integer pageSize, String userType);
	
	/**
	 *  根据Id查询考试信息
	 * @param tId
	 * @return
	 */
	BaiseeExamination getExaminatioByEid(String eid);
	
	/**
	 * 添加考试信息
	 * @param examination
	 * @return
	 */
	int addExamination(BaiseeExamination examination);
	
	/**
	 * 更新考试信息
	 * @param examination
	 * @return
	 */
	int updateExaminationById(BaiseeExamination examination);
	 
	/**
	 *  根据Id删除考试信息
	 * @param parameterValues
	 * @return
	 */
	int delExaminationById(String[] parameterValues);
	/**
	 *  校验试卷是否在使用当中
	 * @param eid
	 * @return
	 */
	boolean whetherToOccupyOrNot(String eid);
}
