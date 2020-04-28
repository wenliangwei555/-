package cn.baisee.oa.dao.baisee;

import java.util.List;

import cn.baisee.oa.model.course.BaiseeTimeStage;

public interface BaiseeTimeStageMapper {


	/**
	 * 查询所有的时间段（以及时间段对应的每节课的时间）
	 * @return
	 */
	List<BaiseeTimeStage> selectTimeStages();

}
