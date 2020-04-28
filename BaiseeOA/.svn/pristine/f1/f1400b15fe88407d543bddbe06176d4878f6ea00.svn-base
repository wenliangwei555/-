package cn.baisee.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.baisee.BaiseeClassCourseMapper;
import cn.baisee.oa.model.course.BaiseeClassCourse;
import cn.baisee.oa.service.BaiseeClassCourseService;
import cn.baisee.oa.utils.StringUtil;
@Service
public class BaiseeClassCourseServiceImpl implements BaiseeClassCourseService {

	@Autowired
	private BaiseeClassCourseMapper classCourseMapper;
	
	@Override
	public List<BaiseeClassCourse> selectClassCourseByCid(String cid) {
		if(StringUtil.isNotEmpty(cid)){
			return classCourseMapper.selectClassCourseByCid(cid);
		}
			return null;
	}

}
