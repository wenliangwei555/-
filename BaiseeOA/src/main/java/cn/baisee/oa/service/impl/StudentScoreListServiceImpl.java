package cn.baisee.oa.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.baiseew.StudentScoreListMapper;
import cn.baisee.oa.model.StudentScore;
import cn.baisee.oa.service.StudentScoreListService;
/**
 * 获取学生成绩单列表业务逻辑层
 * @author Administrator
 *
 */
@Service(value="studentScoreListService")
public class StudentScoreListServiceImpl implements StudentScoreListService {
	@Autowired
    private StudentScoreListMapper  studentScoreListMapper;
	
	/**
	 * 根据学生ID查询学生成绩
	 */
	public Map<String,List<StudentScore>> selectScoer(String sid){
		List<String> terms = studentScoreListMapper.getTermBySid(sid);
		List<String> classIds = studentScoreListMapper.getClassIdBySid(sid);
		List<String> subjects = studentScoreListMapper.getSubjectBySid(sid);
		System.out.println(terms.size()+"terms"+classIds.size()+"class");
		Map<String,List<StudentScore>> map1 = new HashMap<>();
		for (String term : terms) {
			for(String classId : classIds) {
				List<StudentScore> list = new ArrayList<>();
				for(String subject : subjects) {
					Map<String,Object> map = new HashMap<>();
					map.put("sid", sid);
					map.put("term", term);
					map.put("classId", classId);
					map.put("subject", subject);
					StudentScore score = studentScoreListMapper.selectScoer(map);
					if(score != null) {
						list.add(score);
					}
				}
				map1.put(list.get(0).getDes(), list);
			}
		}
		return map1;
	}
	
	
	/**
	 * 向家长推送成绩的成绩
	 */
	
	public Map<String,  List<StudentScore>> getScoerBysid(Map<String, Object> maps){
		Map<String,  List<StudentScore>> map = new HashMap<>();
		List<String> sids = studentScoreListMapper.getSidByClass(maps);
		for (String sid : sids) {
			maps.put("sid", sid);
			List<StudentScore> list = studentScoreListMapper.getScoerBysid(maps);
			map.put(sid, list);
		}
		return map;
	}
	
	/**
	 * 根据学生ID查询学生成绩
	 * @param sid
	 * @return
	 */
	public List<StudentScore> selectBySid(String sid, String classId) {
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("sId", sid);
		maps.put("classId", classId);
		return studentScoreListMapper.selectBySid(maps);
	}
	
	/**
	 * 根据学生ID查询学生成绩
	 * @param sid
	 * @return
	 */
	public List<StudentScore> selectDescBySid(String sid, String classId,String term) {
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("sId", sid);
		maps.put("classId", classId);
		maps.put("term", term);
		return studentScoreListMapper.selectDescBySid(maps);
	}
}
