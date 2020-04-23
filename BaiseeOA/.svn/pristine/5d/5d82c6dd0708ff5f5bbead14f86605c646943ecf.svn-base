package cn.baisee.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.baisee.BaiseeTimeStageMapper;
import cn.baisee.oa.model.course.BaiseeTimeStage;
import cn.baisee.oa.service.BaiseeTimeStageService;

@Service
public class BaiseeTimeStageServiceImpl implements BaiseeTimeStageService {

	@Autowired
	private BaiseeTimeStageMapper timeStageMapper;//时间段的mapper
	@Override
	public List<BaiseeTimeStage> selectTimeStages() {
		return timeStageMapper.selectTimeStages();
	}

}
