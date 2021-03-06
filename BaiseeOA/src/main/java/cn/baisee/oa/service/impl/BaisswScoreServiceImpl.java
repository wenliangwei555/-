package cn.baisee.oa.service.impl;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.baisee.oa.dao.baisee.BaiseeClazzMapper;
import cn.baisee.oa.dao.baisee.BaiseeStuMapper;
import cn.baisee.oa.dao.baiseew.BaiseewTermMapper;
import cn.baisee.oa.dao.baiseew.BaisswScoreMapper;
import cn.baisee.oa.exception.AppException;
import cn.baisee.oa.exception.OAServiceException;
import cn.baisee.oa.importExcel.exception.InvalidExcelTemplateException;
import cn.baisee.oa.importExcel.helper.ExcelToBeanConvertor;
import cn.baisee.oa.model.BaiseeAToken;
import cn.baisee.oa.model.BaiseeClazz;
import cn.baisee.oa.model.BaiseeParent;
import cn.baisee.oa.model.BaisswScore;
import cn.baisee.oa.model.StudentScore;
import cn.baisee.oa.model.baiseeTerm;
import cn.baisee.oa.model.student.BaiseeStudent;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeATokenService;
import cn.baisee.oa.service.BaiseeParentService;
import cn.baisee.oa.service.BaisswScoreService;
import cn.baisee.oa.service.StudentScoreListService;
import cn.baisee.oa.utils.TemplateUtil;
/**
 * 
 * @author jxx
 *
 */
@Service
public class BaisswScoreServiceImpl implements BaisswScoreService {

	@Autowired
	private BaisswScoreMapper scoreMapper;
	@Autowired
	private BaiseewTermMapper termMapper;
	@Autowired
	private BaiseeClazzMapper classMapper;
	@Autowired
	private BaiseeStuMapper studemtMapper;
	
	
	@Autowired
	private StudentScoreListService studentScoreListService;
	@Autowired
	private BaiseeParentService parentService;
	@Autowired
	private BaiseeATokenService aTokenService;
	
	@Override
	public PageInfo<BaiseeClazz> selectWScoreAll(Integer pageNum,Integer pageSize,String[] bsClass) {
		PageHelper.startPage(pageNum, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bsClass", bsClass);
		List<BaiseeClazz> list = classMapper.selectClazzByClaId(map);
		PageInfo<BaiseeClazz> page = new PageInfo<BaiseeClazz>(list);
		return page;
	}


	@Override
	public PageInfo<baiseeTerm> queryDetails(Integer pageNum,Integer pageSize, String classId) {
		PageHelper.startPage(pageNum, pageSize);
		List<baiseeTerm> list = termMapper.queryDetails(classId);
		PageInfo<baiseeTerm> page = new PageInfo<baiseeTerm>(list);
		return page;
	}


	@Override
	public List<BaisswScore> getTermByCId(String classId, String term) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("term", term);
		map.put("cid", classId);
		List<BaisswScore> scoreList = scoreMapper.getTermByCId( map);
		//List<Map<String,Object>> lmp = scoreMapper.getTermByCIDandTerm(map);
		return scoreList;
	}


	@Override
	public Map<String, Object> importStuScore(MultipartFile file, HttpServletRequest request, String classId) {
		//String[] titles = {"学生姓名","科目","分数"};//excel中的表头
		ExcelToBeanConvertor<BaisswScore> convertor = new ExcelToBeanConvertor<BaisswScore>();
		// 1.读取excel数据至对象map集合中
		/*******************************************************************/
		Map<String, List<BaisswScore>> map = null;
		try {
			//   去掉表头
			map = convertor.readToBeanMap2(file.getInputStream(), BaisswScore.class);
		} catch (InvalidExcelTemplateException e) { // 模板不正确则抛出异常
			throw new AppException("IICIPBMS00022");
		} catch (OAServiceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (MapUtils.isEmpty(map))
			return null;
		Map<String, Object> returnMap = new HashMap<String, Object>();
		// 总记录数
		int totalCount = 0;
		// 错误的条数
		int count = 0;
		List<String> rvrDtoList = new ArrayList<String>();
		for (Map.Entry<String, List<BaisswScore>> entry : map.entrySet()) {
			List<BaisswScore> list = entry.getValue();
			totalCount += list.size();
			// 2.准备好过滤时需要使用到的数据，放入上下文中
			Map<String, Object> maps = new HashMap<>();
			if (CollectionUtils.isNotEmpty(list)) { // 如果拆分后的数据集合不为空
				maps.put("cId", classId);
				BaiseeClazz claById = classMapper.getClaById(maps);
				String term = termMapper.getTermByCid(classId);
				term="".equals(term)||term==null?""+1:(Integer.valueOf(term)+1)+"";
				String tampterm = String.valueOf(term);
				//String tampterm = term.intValue()==1?'1':(term.intValue()+1)+"";
				for (BaisswScore baisswScore : list) {
					// 做添加操作
					maps.put("sname", baisswScore.getSname());
					BaiseeStudent stu = studemtMapper.selectStuByCidAndName(maps);
					if( stu == null) {
						count = count + 1;
						rvrDtoList.add("学生"+baisswScore.getSname() +"信息错误，请检查后重新导入！");
						continue;
					}
				}
				if(count==0) {
					//classId   des    term
					String classId1 = "";
					String des = "";
					String term1 = tampterm;
					for (BaisswScore baisswScore : list) {
						// 做添加操作
						maps.put("sname", baisswScore.getSname());
						BaiseeStudent stu = studemtMapper.selectStuByCidAndName(maps);
						classId1 = stu.getClaId();
						baisswScore.setSid(stu.getStuId());
						baisswScore.setClassid(stu.getClaId());
						baisswScore.setCname(claById.getcName());
						baisswScore.setStype(stu.getStuType());
						baisswScore.setTerm(tampterm);
						// 新增一条数据  成绩
						scoreMapper.addScore(baisswScore);
					}
					baiseeTerm termTow = new baiseeTerm();
					termTow.setTerm(Integer.valueOf(tampterm));
					termTow.setCid(classId);
					String fileName = file.getOriginalFilename();
					termTow.setDes(fileName.substring(0, fileName.indexOf(".")));
					des = termTow.getDes();
					termMapper.addTerm(termTow);
					
//					getPostMsg(classId,des,term1);
				}
			}
		}
		returnMap.put("totalCount", totalCount);
		returnMap.put("successCount", totalCount - count);
		returnMap.put("rvrDtoList", rvrDtoList);
		returnMap.put("errorCount", count);
		return returnMap;
	}
	
	public void getPostMsg(String classId,String des,String term) {//classId   des    term
		Pattern jisuanji = Pattern.compile("[a-zA-z]");
		Map<String, Object> maps = new HashMap<>();
		maps.put("classId",classId);
		maps.put("des", des);
		maps.put("term", term);
		BaiseeAToken aToken = aTokenService.getToken();
		//获取成绩   
		//map：整个成绩    key：学生id   value：sid对应的成绩
		studentScoreListService.getScoerBysid(maps);
		Map<String, List<StudentScore>> map = studentScoreListService.getScoerBysid(maps);
		for(Entry<String,List<StudentScore>> entry : map.entrySet()){
			//用每一个学生的sid,获取她所有的家长信息
			List<BaiseeParent> list = parentService.getParentBySid(entry.getKey());
			//遍及家长
			for (BaiseeParent baiseeParent : list) {
				List<StudentScore> studentScores = entry.getValue();
				String sname = "";
				String sid = "";	
				double scoer11 = 0; //计算机分数
				double scoer21 = 0;	//语文分数
				double scoer31 = 0;	//数学分数
				double scoer41 = 0;	//英语分数
				Date time = null;
				//遍历成绩
				for (StudentScore studentScore : studentScores) {
					sname = studentScore.getSname();
					sid = studentScore.getSid();
					time = studentScore.getUpdateTime1();
					//对科目名进行统治
					if(studentScore.getSubject().indexOf("计算机")!=-1 || jisuanji.matcher(studentScore.getSubject()).find()) {
						scoer11 = studentScore.getScoer();
					}
					if(studentScore.getSubject().indexOf("语文")!=-1) {
						scoer21 = studentScore.getScoer();
					}
					if(studentScore.getSubject().indexOf("数学")!=-1) {
						scoer31 = studentScore.getScoer();
					}
					if(studentScore.getSubject().indexOf("英语")!=-1) {
						scoer41 = studentScore.getScoer();
					}
				}
				//推送
				/*String r = TemplateUtil.postScore(sname, scoer11, scoer21, scoer31, scoer41, baiseeParent.getF_openid(),aToken.getaToken(),time);
				System.out.println(r);*/
			}
		}
		
	}
	
	
	@Override
	public boolean deleteScore(String[] parameterValues, String classId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("term", parameterValues);
		map.put("classId", classId);
 		int success = scoreMapper.deleteScoreById( map);
 		int successTow = termMapper.deleteTermById( map);
		return success+successTow==2;
	}


	@Override
	public List<Map<String, Object>> getClassScore(String classId, String term) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("term", term);
		map.put("cid", classId);
		List<Map<String,Object>> lmp = scoreMapper.getTermByCIDandTerm(map);
		return lmp;
	}
}
