package cn.baisee.oa.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.baisee.BaiseeClassroomMapper;
import cn.baisee.oa.dao.baisee.BaiseeClazzMapper;
import cn.baisee.oa.dao.baisee.BaiseeEmployeeMapper;
import cn.baisee.oa.dao.baisee.BaiseeUserMapper;
import cn.baisee.oa.dao.baiseew.BaiseewTermMapper;
import cn.baisee.oa.dao.baiseew.BaisswScoreMapper;
import cn.baisee.oa.dao.stu.BaiseeExaminationMapper;
import cn.baisee.oa.dao.stu.BaiseeTestpaperMapper;
import cn.baisee.oa.model.BaiseeClazz;
import cn.baisee.oa.model.BaisswScore;
import cn.baisee.oa.model.baiseeTerm;
import cn.baisee.oa.model.course.BaiseeClassroom;
import cn.baisee.oa.model.examination.BaiseeExamination;
import cn.baisee.oa.model.examination.BaiseeTestpaper;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeExaminationService;
import cn.baisee.oa.utils.DateUtil;
import cn.baisee.oa.utils.StringUtil;

@Service
public class BaiseeExaminationServiceImpl implements BaiseeExaminationService {
	
	@Autowired
	private BaiseeExaminationMapper examinationMapper; // 考试信息Service
	@Autowired
	private BaiseeClazzMapper classMapper;			//班级信息Service
	@Autowired
	private BaiseeTestpaperMapper testpaperMapper;	//试卷信息Service
	@Autowired
	private BaiseeClassroomMapper classroomMapper; // 机房信息Service
	@Autowired 
	private BaiseeEmployeeMapper employeeMapper;		// 用户信息Service
	@Autowired 
	private BaiseewTermMapper termMapper;		// 成绩Service
	@Autowired 
	private BaisswScoreMapper scoreMapper;  
	@Autowired
	private BaiseeUserMapper userMapper;
	
	
	@Override
	public PageInfo<BaiseeExamination> eInformationList(Integer pageNum, Integer pageSize, String userType) {
		PageHelper.startPage(pageNum, pageSize);
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("userType", userType);
		List<BaiseeExamination> list = examinationMapper.selectExaminationList(map1);
		Map<String, Object> map = new HashMap<String, Object>();
		BaiseeClazz clazz = null;
		BaiseeTestpaper testpaper = null;
		BaiseeClassroom classroom = null;
		if(CollectionUtils.isNotEmpty(list)) {
			for (BaiseeExamination baiseeExamination : list) {
				map.put("cId", baiseeExamination.getCid()); 
				clazz = classMapper.getClaById(map);//获取班级名称
				baiseeExamination.setCid(clazz.getcName());
				testpaper = testpaperMapper.getTestpaperBytid( baiseeExamination.getTid());// 获取试卷名称
				if(testpaper != null) {
					baiseeExamination.setTid(testpaper.getPaperName());
				}
				String empName = employeeMapper.getEmpName(baiseeExamination.getTeacher());
				if(StringUtil.isNotEmpty(empName)) {
					baiseeExamination.setTeacher(empName);// 获取监考老师的姓名
				}
				classroom = classroomMapper.getclassroomById(baiseeExamination.getExaminationPlace());// 获取教室机房
				baiseeExamination.setExaminationPlace(classroom.getRoomName());
			}
		}
		PageInfo<BaiseeExamination> pageResult = new PageInfo<BaiseeExamination>( list);
		return pageResult;
	}

	@Override
	public BaiseeExamination getExaminatioByEid(String eid) {
		return examinationMapper.getExaminatioByEid(eid);
	}

	@Override
	public int addExamination(BaiseeExamination examination) {
		baiseeTerm term = new baiseeTerm();
		try {
			examination.setEid(userMapper.createId("EXID"));
			String termByCid = termMapper.getTermByCid(examination.getCid());// 查询班级第几期考试
			Integer valueOf = Integer.valueOf("".equals(termByCid)?"":(termByCid==null? "0" : termByCid));
			term.setTerm((valueOf+1));//添加期数
			term.setCid(examination.getCid());//添加班级信息
			BaiseeTestpaper testpaper = testpaperMapper.getTestpaperBytid( examination.getTid());// 获取试卷名称
			term.setDes(testpaper.getPaperName()); 	
			termMapper.addTerm(term);	// 添加成绩总表
			examination.setTerm(""+(valueOf+1));
			examinationMapper.addExamination( examination); //添加考试信息
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	
	@Override
	public int updateExaminationById(BaiseeExamination examination) {
		 try {
			examinationMapper.updateExaminationById( examination);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int delExaminationById(String[] parameterValues) {
		BaiseeExamination examinatio = null;
		Map<String, Object> map = new HashMap<String, Object>();
		List<BaisswScore> termByCId = null;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    	try {
	    		for (int i = 0; i < parameterValues.length; i++) {
	    			examinatio = examinationMapper.getExaminatioByEid(parameterValues[i]); // 查询出考试信息根据考试信息查询是否已经考完试
	    			if("1".equals(examinatio.getExaminationStatic())) {
	    				return 0;
	    			}
					boolean equalDate = DateUtil.equalDate(sf.format(new Date()), examinatio.getStartTime(), "yyyy-MM-dd HH:mm");
					if(equalDate == true) { //当前时间大于考试时间的话 就不能删除
						return 0;
					}
					map.put("term", examinatio.getTerm()==null? 0 : examinatio.getTerm());
					map.put("cid", examinatio.getCid());
					termByCId = scoreMapper.getTermByCId(map);
					if(CollectionUtils.isNotEmpty(termByCId)) {
						return 0;
					}
	    		}
			} catch (ParseException e) {
				e.printStackTrace();
			}
	    	
	    try {
	    	Map<String, Object> maps = new HashMap<String, Object>();
	    	  for (int i = 0; i < parameterValues.length; i++) { //都是没考过的
	    		  examinatio = examinationMapper.getExaminatioByEid(parameterValues[i]); 
	    		  maps.put("cid", examinatio.getCid());
	    		  maps.put("trem", examinatio.getTerm());
	    		  termMapper.deleteTermByIdorClaId( maps);
	    	  }
			examinationMapper.delExaminationById( parameterValues);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	    
	}

	@Override
	public boolean whetherToOccupyOrNot(String eid) {
		BaiseeExamination examinatioByEid = examinationMapper.getExaminatioByEid(eid); //查出试卷信息 根据试卷信息查询试卷是否已经考完试
		if("1".equals(examinatioByEid.getExaminationStatic())) {
			return false;
		}else{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("term", examinatioByEid.getTerm());
			map.put("cid", examinatioByEid.getCid());
			List<BaisswScore> termByCId = scoreMapper.getTermByCId(map);	
			if(CollectionUtils.isNotEmpty(termByCId)) {
				return false;
			}else {
				String startTime = examinatioByEid.getStartTime();
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String format = sf.format(new Date());
				try {
					boolean equalDate = DateUtil.equalDate(format, startTime, "yyyy-MM-dd HH:mm");
					return equalDate == true ? false : true;
				} catch (ParseException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		
		
	}

}
