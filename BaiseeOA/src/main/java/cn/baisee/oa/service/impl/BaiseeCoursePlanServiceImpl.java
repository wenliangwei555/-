package cn.baisee.oa.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.baisee.BaiseeClassCourseMapper;
import cn.baisee.oa.dao.baisee.BaiseeClassSystemMapper;
import cn.baisee.oa.dao.baisee.BaiseeCourseMapper;
import cn.baisee.oa.dao.baisee.BaiseeCourseTeacherMapper;
import cn.baisee.oa.model.course.BaiseeClassCourseId;
import cn.baisee.oa.model.course.BaiseeClassSystem;
import cn.baisee.oa.model.course.BaiseeCourseTeacher;
import cn.baisee.oa.service.BaiseeCoursePlanService;
import cn.baisee.oa.utils.StringUtil;
@Service
public class BaiseeCoursePlanServiceImpl implements BaiseeCoursePlanService {

	@Autowired
	private BaiseeClassSystemMapper classSystemMapper;//班级课制mapper
	@Autowired
	private BaiseeClassCourseMapper classCourseMapper;//班级课程的mapper
	@Autowired
	private BaiseeCourseTeacherMapper courseTeacherMapper;//课程老师表
	@Autowired
	private BaiseeCourseMapper courseMapper;//课程mapper

	
	@Override
	public String insertCourseAllInfo(BaiseeClassSystem clsSys, BaiseeClassCourseId claCour,
			BaiseeCourseTeacher couTea,String classId) {
		int r =0;//标识是否添加成功
		if(StringUtil.isNotEmpty(clsSys.getCsId()) && StringUtil.isNotEmpty(clsSys.getCtId())){
			//添加班级课制
			clsSys.setCid(classId);
			r+=classSystemMapper.insertClassSystem(clsSys);
		}
		Boolean index = setCourseTeacher(couTea,claCour);//标识课程老师是否有值 有则为true 否则为false
		if(index){
			//可以新增
			if(StringUtil.isNotEmpty(claCour.getCrId()) && StringUtil.isNotEmpty(claCour.getTsId())
					&& StringUtil.isNotEmpty(claCour.getWeek())){
				claCour.setCid(classId);
				r+=classCourseMapper.insertClassCourseId(claCour);
			}
			if(r>0){
				//新增成功
				return "添加课表成功";
			}
		}
		return "添加失败";
	}

	@Override
	public BaiseeClassCourseId getClassCourseIdByInfo(Map<String, String> map) {
		return classCourseMapper.selectClassCourseIdByInfo(map);
	}
	
	private Boolean setCourseTeacher( BaiseeCourseTeacher couTea,BaiseeClassCourseId claCour){
		//根据课程和老师的id查 课程老师表，如果有对应的课程老师id ，就将该id赋值到班级课程当中，如果没有就创建课程老师表
		if(StringUtil.isNotEmpty(couTea.getCiId()) && StringUtil.isNotEmpty(couTea.getEmpId())){
			List<BaiseeCourseTeacher> list = courseTeacherMapper.getCourseTeacherByIds(couTea);
			if(list != null && list.size()>0){
				claCour.setCtId(list.get(0).getCid());//将课程老师表的id传入
				Map<String, String> map = new HashMap<>();
				map.put("status", "1");//改成使用
				map.put("id",list.get(0).getCiId());
				courseMapper.updCourseStatus(map);
			}else{
				//无数据，新增课程老师表，修改课程状态 
				courseTeacherMapper.insertCourseTeacher(couTea);//添加课程老师表
				claCour.setCtId(couTea.getCid());//将课程老师表的id传入
				Map<String, String> map = new HashMap<>();
				map.put("status", "1");//改成使用
				map.put("id", couTea.getCiId());
				courseMapper.updCourseStatus(map);
			}
			return true;
		}
		return false;
	}
	@Override
	public String updateCourseAll(BaiseeClassCourseId claCour, BaiseeCourseTeacher couTea, String classId,String courseId) {
		//根据课程和老师的id查 课程老师表，如果有对应的课程老师id ，就将该id赋值到班级课程当中，如果没有就创建课程老师表
		   Boolean index = setCourseTeacher(couTea,claCour);//标识课程老师是否有值 有则为true 否则为false
		   claCour.setCid(classId);//班级id传入
		   if(index){
				int r = classCourseMapper.updateClassCourse(claCour);
				if(r>0){
					//修改课程状态
					List<String> ctids = classCourseMapper.getListCtid(courseId); 
 					if(ctids != null && ctids.size() == 0){
						//则要改状态，要将原先的课程改成未使用
						Map<String, String> map = new HashMap<>();
						map.put("status", "0");//改成未使用
						map.put("id", courseId);
						courseMapper.updCourseStatus(map);
					}
					return "修改成功";
				}
				return "修改失败";
		   }
		   		return "无数据，不能修改";
	}

	@Override
	public String delClassCourse(String ccId,String courseTeacherId,BaiseeCourseTeacher couTea) {
		if(StringUtil.isEmpty(ccId) && StringUtil.isEmpty(courseTeacherId)){
			return "信息有误不能修改";
		}
		int index =0;
		
		//查询课程老师表中是否还有该课程
		List<String> ctids = classCourseMapper.getListCtid(courseTeacherId); 
		if(ctids != null && ctids.size() == 1 ){
			//当只有一条记录时，证明只有删除的课程在使用该主键，那么就可以 从课程老师表中删除该条对应的记录
			courseTeacherMapper.delCourseTeacher(courseTeacherId);//删除课程老师表
			//证明没有记录再使用该课程,要将该课程改为未使用
			Map<String, String> map = new HashMap<>();
			map.put("status", "0");//改成未使用
			map.put("id", couTea.getCiId());
			courseMapper.updCourseStatus(map);
		}	
		index += classCourseMapper.delClassCourseByCcId(ccId);//删除班级课程表
		if(index > 0){
			return "删除成功";
		}
		return "删除失败";
	}

	@Override
	public String getCourseIdByInfo(Map<String, String> map) {
		return classCourseMapper.getCourseIdByInfo(map);
	}

}
