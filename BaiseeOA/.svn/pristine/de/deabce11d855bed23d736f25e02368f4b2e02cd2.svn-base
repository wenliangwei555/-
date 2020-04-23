package cn.baisee.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.baisee.BaiseeClassroomMapper;
import cn.baisee.oa.model.course.BaiseeClassroom;
import cn.baisee.oa.service.BaiseeClassroomService;

@Service
public class BaiseeClassroomServiceImpl implements BaiseeClassroomService {

	@Autowired
	private BaiseeClassroomMapper classroomMapper;
	@Override
	public List<BaiseeClassroom> selectClassroomList(String userType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roomType", userType);
		return classroomMapper.selectClassroomList( map);
	}

}
