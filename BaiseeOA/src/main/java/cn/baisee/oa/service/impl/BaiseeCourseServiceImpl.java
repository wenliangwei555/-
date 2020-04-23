package cn.baisee.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.baisee.BaiseeCourseMapper;
import cn.baisee.oa.dao.baisee.BaiseeRoleMapper;
import cn.baisee.oa.model.BaiseeRole;
import cn.baisee.oa.model.course.BaiseeCourse;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeCourseService;
import cn.baisee.oa.utils.StringUtil;

@Service
public class BaiseeCourseServiceImpl implements BaiseeCourseService {
	@Autowired
	private BaiseeCourseMapper baiseeCourseMapper;
	
	@Override
	public PageInfo<BaiseeCourse> selectCourseListByInfo(int pageNum, int pageSize, BaiseeCourse course) {
		PageHelper.startPage(pageNum, pageSize);
		List<BaiseeCourse> list = baiseeCourseMapper.selectPageCourse(course);
		PageInfo<BaiseeCourse> page = new PageInfo<BaiseeCourse>(list);
		return page;
	}

	@Override
	public String addOrUpdateCourse(BaiseeCourse course) {
		int index = 0;//标识是否成功
		if(StringUtil.isNotEmpty(course.getCiId())){//修改
			//从数据库查询要修改的课程
			index = baiseeCourseMapper.updateCourse(course);
		}else{//新增课程
			index = baiseeCourseMapper.addCourse(course);
		}
		if(index > 0){
			return "操作成功";
		}
			return "操作失败";
	}

	@Override
	public BaiseeCourse selectCourseById(String ciId) {
		return baiseeCourseMapper.selectCourseById(ciId);
	}

	@Override
	public String deleteCourseByIds(String[] ids) {
		if(ids !=null && ids.length>0 ){
			int r = baiseeCourseMapper.deleteCourseByIds(ids);
			if(r >0){
				return "操作成功";
			}
				return "操作失败";
		}
		return "请选择课程!";
	}

	@Override
	public List<BaiseeCourse> selectCourseListByType(String courseType) {
		BaiseeCourse course = new BaiseeCourse();
		course.setCourseType(courseType);
		return baiseeCourseMapper.selectPageCourse(course);
	}

	@Override
	public List<BaiseeCourse> validateCourse(String courseType, String courseTtitle,String ciId) {
		BaiseeCourse course = new BaiseeCourse();
		course.setCourseType(courseType);
		course.setCourseTtitle(courseTtitle);
		if(StringUtil.isEmpty(courseTtitle) || StringUtil.isEmpty(courseType)){
			return null;
		}
		if(StringUtil.isNotEmpty(ciId)){
			course.setCiId(ciId);
		}
		return baiseeCourseMapper.selectCoursesByInfo(course);
	}
	

}
