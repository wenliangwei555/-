package cn.baisee.oa.service;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.model.StudentScore;

public interface StudentScoreListService {
	public Map<String,List<StudentScore>> selectScoer(String sid);
	public Map<String,  List<StudentScore>> getScoerBysid(Map<String, Object> maps);
	public List<StudentScore> selectBySid(String sid, String classId) ;
	public List<StudentScore> selectDescBySid(String sid, String classId,String term) ;
}
