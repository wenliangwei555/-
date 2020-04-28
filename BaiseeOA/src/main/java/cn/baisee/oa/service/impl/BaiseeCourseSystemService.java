package cn.baisee.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.baisee.BaiseeCourseSystemMapper;
import cn.baisee.oa.model.course.BaiseeCourseSystem;

@Service
public class BaiseeCourseSystemService implements cn.baisee.oa.service.BaiseeCourseSystemService {

	@Autowired
	private BaiseeCourseSystemMapper courseSystemMapper;

	@Override
	public List<BaiseeCourseSystem> selectAllCourseSystem() {
		return courseSystemMapper.selectAllCourseSystem();
	}
}
