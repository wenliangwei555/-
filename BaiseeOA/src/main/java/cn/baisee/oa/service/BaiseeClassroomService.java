package cn.baisee.oa.service;

import java.util.List;

import cn.baisee.oa.model.course.BaiseeClassroom;

public interface BaiseeClassroomService {

	List<BaiseeClassroom> selectClassroomList(String userType);

}
