package cn.baisee.oa.service;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import cn.baisee.oa.model.BaiseeClazz;
import cn.baisee.oa.model.BaisswScore;
import cn.baisee.oa.model.baiseeTerm;
import cn.baisee.oa.page.pagehelper.PageInfo;
/**
 * 
 * @author jxx
 *
 */
public interface BaisswScoreService {
	/**
	 *  根据登录人的班级名称,查询所有的班级成绩
	 * @param bsClass
	 * @return
	 */
	PageInfo<BaiseeClazz> selectWScoreAll(Integer pageNum,Integer pageSize,String[] bsClass);
	/**
	 *  根据班级查询详细的考试记录
	 * @param classId
	 */
	 PageInfo<baiseeTerm> queryDetails(Integer pageNum,Integer pageSize, String classId);
	 /**
	  * 根据班级成绩期数查询详细信息
	  * @param classId
	  * @return
	  */
	List<BaisswScore> getTermByCId(String classId, String term);
	/**
	 *  批量导入数据
	 * @param inputStream
	 * @param request
	 * @return
	 */
	Map<String, Object> importStuScore(MultipartFile file, HttpServletRequest request, String classId);
	/**
	 *  根据Id删除成绩表中的数据
	 * @param parameterValues
	 * @return
	 */
	boolean deleteScore(String[] parameterValues, String classId);
	
	List<Map<String,Object>> getClassScore(String classId, String term);
}
