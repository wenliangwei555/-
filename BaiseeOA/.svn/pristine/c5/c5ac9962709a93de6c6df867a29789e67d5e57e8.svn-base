package cn.baisee.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.baisee.BaiseeDicMapper;
import cn.baisee.oa.dao.baisee.BaiseeUserMapper;
import cn.baisee.oa.dao.stu.BaiseeExaminationMapper;
import cn.baisee.oa.dao.stu.BaiseeItemBankMapper;
import cn.baisee.oa.dao.stu.BaiseeTestpaperMapper;
import cn.baisee.oa.model.BaiseeUser;
import cn.baisee.oa.model.IcipBusDict;
import cn.baisee.oa.model.ReturnInfo;
import cn.baisee.oa.model.course.BaiseeCourse;
import cn.baisee.oa.model.examination.BaiseeExamination;
import cn.baisee.oa.model.examination.BaiseeItemBank;
import cn.baisee.oa.model.examination.BaiseeTestpaper;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeCourseService;
import cn.baisee.oa.service.BaiseeTestpaperService;

@Service
public class BaiseeTestpaperServiceImpl implements BaiseeTestpaperService {
	
	@Autowired
	private BaiseeTestpaperMapper testpaperMapper;
	@Autowired
	private BaiseeItemBankMapper itemBankMapper;
	@Autowired
	private BaiseeUserMapper userMapper;
	@Autowired
	private BaiseeExaminationMapper examinationMapper;
	@Autowired 
	private BaiseeCourseService courseService;
	
	@Override
	public PageInfo<BaiseeTestpaper> testPaperList(Map<String, Object> maps) {
		// 查询所有试题
		PageHelper.startPage(Integer.valueOf(maps.get("pageNum").toString()), Integer.valueOf(maps.get("pageSize").toString()));
		List<BaiseeTestpaper> list = testpaperMapper.testPaperList(maps);
		for (BaiseeTestpaper testpaper : list) {
			BaiseeUser user = userMapper.selectByPrimaryKey(testpaper.getCreateUser());
			testpaper.setCreateUser(user.getUserName());
		}
		PageInfo<BaiseeTestpaper> info = new PageInfo<BaiseeTestpaper>(list);
		return info;
	}

	@Override
	public ReturnInfo delTestPaper(String[] ids) {
		ReturnInfo info = new ReturnInfo();
		List<BaiseeExamination> examination = null;
		for (int i = 0; i < ids.length; i++) {
			// 查询考试表 判断试卷是否正在被班级使用中
			examination = examinationMapper.getExaminationById(ids[i]);
			if(CollectionUtils.isNotEmpty(examination)) {
				info.setCode(1);
				info.setMessage("试卷使用中,不能删除");
				return info;
			}
		}
		try {
			// 删除试卷表数据
			testpaperMapper.delTestPaperByIds( ids);
			// 删除试卷跟试题中间表数据
			testpaperMapper.delItembankTestpaperByTid( ids);
			info.setCode(2);
			info.setMessage("删除成功!");
			return info;
		} catch (Exception e) {
			info.setCode(1);
			info.setMessage("删除失败");
			return info;
		}
	}
	
	@Override			
	public List<BaiseeItemBank> selectTestPaperListById(String customoldid) {
		List<BaiseeItemBank> list = itemBankMapper.selectItemBankListById( customoldid);
		BaiseeUser user = null;
		BaiseeCourse selectCourseById = null;
		// 遍历题库信息 
		StringBuilder sbd = null;
		for (BaiseeItemBank baiseeItemBank : list) {
			// 根据字典Id 查询考题类别名称
			selectCourseById = courseService.selectCourseById(baiseeItemBank.getDid());
			
			baiseeItemBank.setDictValue(selectCourseById.getCourseTtitle());
			user = userMapper.selectByPrimaryKey(baiseeItemBank.getCreateName());
			baiseeItemBank.setCreateName(user.getUserName());
			String str = baiseeItemBank.getCreateTs().substring(0, 8);
			sbd = new StringBuilder(str);
			StringBuilder insert = sbd.insert(4, "-").insert(7,"-");
			String success = insert.toString();
			baiseeItemBank.setCreateTs(success);
		}
		return list;
	}

	@Override
	public int addTestPaer(String iIds,String paperName, String userId, String examinationType, String userType) {
		Map<String, Object> map = new HashMap<String, Object>();
		String tId = userMapper.createId("TPID");
		map.put("tId", tId);
		map.put("paperName", paperName);
		map.put("userId", userId);
		map.put("examinationType", examinationType);  
		map.put("userType", userType);  
		try {
			testpaperMapper.addTestPaer( map);// 新增试卷信息
			String[] split = iIds.split(",");
			for (int i = 0; i < split.length; i++) {
				map.put("iId", split[i].trim());
				map.put("tId", tId);
				map.put("sort", i);
				testpaperMapper.addItembankTestpaper( map); // 试卷中间表数据
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public boolean checkName(String paperName, String tid) {
		if("".equals(tid) || tid == null) {
			int checkName = testpaperMapper.checkName( paperName);
			return checkName > 0 ? false : true;
		}else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("paperName", paperName);
			map.put("tid", tid);
			int checkName2 = testpaperMapper.checkName2( map);
			return checkName2 > 0 ? false : true;
		}
	}

	@Override
	public List<BaiseeTestpaper> selectTestPaperList(String userType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userType", userType);
		return testpaperMapper.testPaperList(map);
	}

	@Override
	public BaiseeTestpaper getTestpaperById(String tid) {
		return testpaperMapper.getTestpaperBytid(tid);
	}

	@Override
	public ReturnInfo checkId(String paperName, String tid, String id) {
		ReturnInfo info = new ReturnInfo();
		if("".equals(tid)) {
			boolean flag = false;
			String[] split = id.split(",");
			for (int i = 0; i < split.length; i++) {
				for (int j = 0; j < split.length; j++) {
					if(i!=j && split[i].equals(split[j])) {
						flag = true;
					}
				}
			}
			if(flag == true) {
				info.setMessage("试题存在重复数据!");
				info.setCode(0);
			}else {
				info.setCode(1);
			}
			
			return info;
		}
		List<BaiseeExamination> examination = examinationMapper.getExaminationById(tid);
		if(CollectionUtils.isNotEmpty(examination)){
			info.setMessage("试卷正在被使用当中不能修改");
			info.setCode(0);
		}else {
			boolean flag = false;
			String[] split = id.split(",");
			for (int i = 0; i < split.length; i++) {
				for (int j = 0; j < split.length; j++) {
					if(i!=j && split[i].equals(split[j])) {
						flag = true;
					}
				}
			}
			if(flag == true) {
				info.setMessage("试题存在重复数据!");
				info.setCode(0);
			}else {
				info.setCode(1);
			}
		}
		
		return info;
	}

	@Override
	public int updateTestPaer(String iIds, String paperName, String userId, String examinationType, String tid) {
		// 先删除再新增
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tid", tid);
		map.put("examinationType", examinationType);
		map.put("paperName", paperName);
		try {
			testpaperMapper.updateTestPaer( map);// 修改试卷信息
			testpaperMapper.delItembankTestpaperByTid(new String[] {tid});		//删除中间表数据
			String[] split = iIds.split(",");// 新增数据中间表
			for (int i = 0; i < split.length; i++) {
					map.put("iId", split[i].trim());
					map.put("tId", tid);
					map.put("sort", i);
					testpaperMapper.addItembankTestpaper( map); // 试卷中间表数据
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
}