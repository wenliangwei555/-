package cn.baisee.oa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.constants.AppConstants;
import cn.baisee.oa.dao.baisee.BaiseeClazzMapper;
import cn.baisee.oa.dao.stu.StuEvaluateMapper;
import cn.baisee.oa.exception.OAServiceException;
import cn.baisee.oa.model.BaiseeClazz;
import cn.baisee.oa.model.BaiseeUser;
import cn.baisee.oa.model.evaluate.BaiseeStuEva;
import cn.baisee.oa.model.evaluate.BaiseeStuEvaone;
import cn.baisee.oa.model.evaluate.EvaAndControl;
import cn.baisee.oa.model.evaluate.EvaGenTeb;
import cn.baisee.oa.model.evaluate.Problem;
import cn.baisee.oa.model.evaluate.SuPr;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.StuEvaluateService;
import cn.baisee.oa.utils.ParamUtils;
import cn.baisee.oa.utils.ProductionIdUtils;
import cn.baisee.oa.utils.SessionUtil;


/**
 * 学生评价   serviceImpl
 * @author yzk
 *
 */
@Service
public class StuEvaluateServiceImpl implements StuEvaluateService{

	@Autowired
	private StuEvaluateMapper stuEvaMapper;
	
	@Autowired
	private BaiseeClazzMapper claMapper;
	
	/**
	 * 根据班级查询 学生评级的信息
	
	@Override
	public PageInfo<BaiseeStuEva> selectStuEvaPage(HttpServletRequest request) {
		
		String param = ParamUtils.getParameter(request, "claName");
		String state = ParamUtils.getParameter(request, "state");
		String isGraduate = ParamUtils.getParameter(request, "isGraduate");
		if(state == null || "".equals(state))  {
			state = "0";
		}
		Map<String, Object> map = new HashMap<>();
		
		
		List<BaiseeStuEva> list = stuEvaMapper.selectStuEvaPage(map);
		List<BaiseeStuEva> lis =  stuEvaMapper.selClaTit(param);
		List<BaiseeStuEva> list =new ArrayList<BaiseeStuEva>();
		
		if(lis!=null && lis.size() > 0){
			for (BaiseeStuEva baiseeStuEva : lis) {
				map.put("param", param);
				map.put("tit", baiseeStuEva.getTitleName());
				map.put("state", state);
				BaiseeStuEva eva = stuEvaMapper.selectStuEvaPage(map);
				if(eva != null || !"".equals(eva)){
					 list.add(eva);
				}
			}
		}
		System.out.println(list.size());
		PageInfo<BaiseeStuEva> page=new PageInfo<BaiseeStuEva>(list);
		return page;
	} */
	
	
	
/*	@Override
	public List<BaiseeStuEva> selectStuEvaPage1(HttpServletRequest request) {
		Integer num = ParamUtils.getPageNumParameters(request);
		Integer size = ParamUtils.getPageSizeParameters(request);
		String param = ParamUtils.getParameter(request, "claName");
		String state = ParamUtils.getParameter(request, "state");
		String isGraduate = ParamUtils.getParameter(request, "isGraduate");
		if(state == null || "".equals(state))  {
			state = "0";
		}
		Map<String, Object> map = new HashMap<>();
		
		
		List<BaiseeStuEva> list = stuEvaMapper.selectStuEvaPage(map);
		List<BaiseeStuEva> lis =  stuEvaMapper.selClaTit(param);
		List<BaiseeStuEva> list = new ArrayList<BaiseeStuEva>();
		if(lis!=null && lis.size() > 0){
			for (BaiseeStuEva baiseeStuEva : lis) {
				PageHelper.startPage(num, size);  分页
				map.put("param", param);
				map.put("tit", baiseeStuEva.getTitleName());
				map.put("state", state);
			    list = stuEvaMapper.selectStuEvaPage(map);
			}
		}
		return list;
	}*/
		
	/**
	 *添加学生评价内容*/
	 
	@Override
	public int insertEvaInfo(HttpServletRequest request,Problem thesaurus) {
		BaiseeUser user = (BaiseeUser) request.getSession().getAttribute(AppConstants.SESSION_USER_INFO);
		thesaurus.setFounder(user.getUserName());/* 创建人的姓名*/
		thesaurus.setModifier(user.getUserName());/*修改人的姓名*/
		return stuEvaMapper.insertEvaInfo(thesaurus);
	}

	
	/**
	 * 
	 *分页查询所有问题的信息
	 */
	@Override
	public List<Problem> selectTgesPage(HttpServletRequest request) {
		Integer size = ParamUtils.getPageSizeParameters(request);
		Integer num = ParamUtils.getPageNumParameters(request);
		PageHelper.startPage(num, size);
		BaiseeUser user  = (BaiseeUser)request.getSession().getAttribute(AppConstants.SESSION_USER_INFO);
		Map<String, Object> map = new HashMap<>();
		map.put("userName", user.getUserName());
		map.put("userType", user.getUserType());
		return stuEvaMapper.selectTgesPage(map);
	}

	/**
	 * 删除单个评价信息
	 */
	@Override
	public int delThes(String tId) {
		return stuEvaMapper.delThes(tId);
	}
	
	/**
	 * 上线评价信息
	 */
	@Override
	public int goOnline(HttpServletRequest request,String tId) {
		BaiseeUser user = (BaiseeUser) request.getSession().getAttribute(AppConstants.SESSION_USER_INFO);
		Map<String, Object> map = new HashMap<>();
		map.put("tId", tId);
		map.put("userName", user.getUserName());
		int i = stuEvaMapper.goOnline(map);
		return i;
	}
	
	/**下线评价信息*/
	@Override
	public int Offline(HttpServletRequest request,String tId) {
		BaiseeUser user = (BaiseeUser) request.getSession().getAttribute(AppConstants.SESSION_USER_INFO);
		Map<String, Object> map = new HashMap<>();
		map.put("tId", tId);
		map.put("userName", user.getUserName());
		return stuEvaMapper.Offline(map);
	}
	
	/**批量删除设置的评价信息*/
	@Override
	public void delAllThes(HttpServletRequest requests,String [] ids)throws OAServiceException {
			List<SuPr> list =  stuEvaMapper.getDelThesInfo(ids);
			ModelAndView model = new ModelAndView();
			if(list != null && list.size() > 0) {
				requests.setAttribute("Result", "error");
			}else {
				try {
					stuEvaMapper.delAllThes(ids);
				} catch (Exception e) {
					throw new OAServiceException("批量删除失败", e);
				}
			}
		
	}
	
	
	/**批量删除设置的评价信息*/
	@Override
	public int delAllEvaGens(String [] ids)throws OAServiceException {
		try {
			return stuEvaMapper.delAllEvaGens(ids);
		} catch (Exception e) {
			throw new OAServiceException("批量删除失败", e);
		}
	}
	
	
	/**
	 * 批量删除学生评价信息
	 */
	@Override
	public void delAllEva(HttpServletRequest request)throws OAServiceException {
		try {
			String[] ids = ParamUtils.getParameters(request,"ids");
			stuEvaMapper.delAllEva(ids);
		} catch (Exception e) {
			throw new OAServiceException("批量删除失败", e);
		}
	}
	
	/**
	 * 更新评价信息
	 */
	@Override
	public int updateState(String ecid) {
		return stuEvaMapper.updateState(ecid);
	}

	/**根据评价ID查询评价内容*/
	@Override
	public List<BaiseeStuEva> getEidStuEva(HttpServletRequest request) {
		String eid = ParamUtils.getParameter(request, "id");
		List<BaiseeStuEva> eidStuEva = stuEvaMapper.getEidStuEva(eid);
		if(eidStuEva != null || eidStuEva.size() > 0) {
			stuEvaMapper.updateState(eid);
		}
		return eidStuEva;
	}

	/**
	 *  预览  问题向详细信息
	 */
	@Override
	public Problem getIdProblemInfo(HttpServletRequest request) {
		String id = ParamUtils.getParameter(request, "stID");
		return stuEvaMapper.getIdProblemInfo(id);
	}

	/**修改问题信息*/
	@Override
	public int updateEvaInfo(HttpServletRequest request, Problem thesaurus) {
		BaiseeUser user = (BaiseeUser) request.getSession().getAttribute(AppConstants.SESSION_USER_INFO);
		thesaurus.setModifier(user.getUserName());   /*修改人*/
		return stuEvaMapper.updateEvaInfo(thesaurus);
	}

	/**生成评价表*/
	@Override
	public int saveCreTeb(HttpServletRequest request) {
		String uspID = ParamUtils.getParameter(request, "spID");
		int cou =0;
			if(uspID == null || "".equals(uspID)) {  /*添加*/
				String[] ids = ParamUtils.getParameters(request, "ids"); 	  /*获取选中的问题ID*/
				String claName = ParamUtils.getParameter(request, "claName");   /*获取要评价的班级*/
				String evaName = ParamUtils.getParameter(request, "empName");	/*获取评价对象*/
				String TitleName = ParamUtils.getParameter(request, "TitleName");
				Map<String, Object> map = new HashMap<>();
				/*String cName  = claName.substring(claName.indexOf("("), claName.indexOf(")")+1);
				claName = claName.replace(cName, "");*/
				map.put("ids", ids);
				map.put("claName", claName);
				map.put("evaName", evaName);
				map.put("TitleName", TitleName);
				System.err.println(TitleName);
				Map<String, Object> map1 = new HashMap<>();
				String suID = ProductionIdUtils.getCode();    /*suID*/
				int ii = 0;
				for (int i = 0; i < ids.length; i++) {
					map1.put("suID", suID);
					map1.put("ids", ids[i]);
					ii = stuEvaMapper.saveCreTeb(map1);   
					ii +=ii;
				}
				if(ii > 0) {
					String spID = ProductionIdUtils.getCode();    /*spID*/
					map.put("spID", spID);   
					map.put("suID", suID);
					BaiseeUser user = (BaiseeUser)request.getSession().getAttribute(AppConstants.SESSION_USER_INFO);
					map.put("userName", user.getUserName());
					cou = stuEvaMapper.saveEvaGen(map);
				}
			}
			/*else {
				 	EvaGenTeb idEvaGenInfo = stuEvaMapper.getIdEvaGenInfo(uspID); 根据spid  查suid
				 	String claName = ParamUtils.getParameter(request, "claName");   获取要评价的班级
					String evaName = ParamUtils.getParameter(request, "empName");	获取评价对象
					BaiseeUser user = (BaiseeUser)request.getSession().getAttribute(AppConstants.SESSION_USER_INFO);
					Map<String, Object> map = new HashMap<>();
					map.put("userName", user.getUserName());   修改人的姓名
					map.put("claName", claName);
					map.put("evaName", evaName);
					map.put("uspID", uspID);
				 	stuEvaMapper.updateEvaGenInfo(map);  修改生产评价表
				 	String[] ids = ParamUtils.getParameters(request, "ids"); 	  获取选中的问题ID
					
				 	if(idEvaGenInfo != null) {
				 		String suID = idEvaGenInfo.getSuID();
				 		int i = stuEvaMapper.deleteSuPr(suID);    	删除旧的
				 		if(i > 0) {
							for (int j = 0; j < ids.length; j++) {
								map.put("suID", suID);
								map.put("stID", ids[j]);
								cou =  stuEvaMapper.insertSuPr(map);   添加新的
							}
				 			
				 		}
				 	}
				 	
			
			}*/
		return cou;
	}

	@Override
	public List<EvaGenTeb> selectListPage(HttpServletRequest request) {
		BaiseeUser user = (BaiseeUser)request.getSession().getAttribute(AppConstants.SESSION_USER_INFO);
		Integer num = ParamUtils.getPageNumParameters(request);
		Integer size = ParamUtils.getPageSizeParameters(request);
		PageHelper.startPage(num, size);
		Map<String, Object> map = new HashMap<>();
		map.put("userName", user.getUserName());
		map.put("userType", user.getUserType());
		return stuEvaMapper.selectListPage(map);
	}
	
	/**
	 *根据ID 查询 生产评价表
	 */
	@Override
	public EvaGenTeb getIdEvaGenInfo(HttpServletRequest request) {
		String id = ParamUtils.getParameter(request, "spID");
		return stuEvaMapper.getIdEvaGenInfo(id);
	}

	@Override
	public List<Problem> getSuIdProInfos(String suID) {
		return stuEvaMapper.getSuIdProInfos(suID);
	}
	
	/*查询综合评价问题表*/
	@Override
	public List<Problem> selectByIdProblem(HttpServletRequest request) {
		String suID = ParamUtils.getParameter(request, "suID");
		List<Problem> proInfos =null;
		proInfos = stuEvaMapper.getSuIdProInfos(suID);
		Problem proInfo;
		List<Problem> list = new ArrayList<>();
		for (Problem problem : proInfos) {
			 proInfo = stuEvaMapper.getStidProInfo(problem.getStID());
			if(proInfo != null) {
				list.add(proInfo);
			}
		}
		return list;
	}
	
	/*校验评价班级*/
	@Override
	public EvaGenTeb validaClaName(HttpServletRequest request) {
		String claName = ParamUtils.getParameter(request, "claName");
		String spID  = ParamUtils.getParameter(request,"spID");
		Map<String, Object> map = new HashMap<>();
		map.put("claName", claName);
		map.put("spID", spID);
		return stuEvaMapper.validaClaName(map);
	}

	/**
	 * 修改评价问题使用状态
	 */
	@Override
	public int updateEvaGenState(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		String suID = ParamUtils.getParameter(request, "suID");
		String state = ParamUtils.getParameter(request, "state");
		BaiseeUser user = (BaiseeUser)request.getSession().getAttribute(AppConstants.SESSION_USER_INFO);
		if("0".equals(state)) {
			map.put("state", "1");
		}else {
			map.put("state", "0");
		}
		map.put("spID", suID);
		map.put("userName", user.getUserName());
		return stuEvaMapper.updateEvaGenState(map);
	}

	/**
	 * 添加  评价和控制评价
	 * @return
	 */
	@Override
	public int saveEvaAndEvaControlTable(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		String spID =  ParamUtils.getParameter(request, "spID");
		String claName = ParamUtils.getParameter(request, "claName");
		String empName  =ParamUtils.getParameter(request, "empName");
		BaiseeUser user = (BaiseeUser)request.getSession().getAttribute(AppConstants.SESSION_USER_INFO);
		map.put("spID", spID);
		map.put("claName", claName);
		map.put("empName", empName);
		map.put("openName", user.getUserName());
		return stuEvaMapper.saveEvaAndEvaControlTable(map);
	}

	/**
	 * 查询所有开启评价信息
	 */
	@Override
	public List<EvaAndControl> selectEvaConPage(HttpServletRequest request) {
		Integer num  = ParamUtils.getPageNumParameters(request);
		Integer size = ParamUtils.getPageSizeParameters(request);
		String [] cIds  = (String [])request.getSession().getAttribute("bsClass");
		String userType = SessionUtil.getUserInfo(request).getUserType();
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("cStatus", userType);
		map.put("cIds", cIds);
		List<BaiseeClazz> cla = claMapper.getByIdCname(map);
		PageHelper.startPage(num, size);
		List<EvaAndControl> evaCon = stuEvaMapper.selectEvaConPage(cla);
		return evaCon;
	}

	@Override
	public List<EvaAndControl> getEvaCons(String [] ids) {
		return stuEvaMapper.getEvaCons(ids);
	}
	
	/**
	 * 关闭评价
	 */
	@Override
	public int closeEva(String id) {
		return stuEvaMapper.closeEva(id);
	}

	@Override
	public List<BaiseeStuEva> selectStuEvaListPage(List<BaiseeClazz> list) {
		return stuEvaMapper.selectStuEvaListPage(list);
	}

	/*校验标题名*/
	@Override
	public EvaGenTeb validateTitleName(String titleName) {
		return stuEvaMapper.validateTitleName(titleName);
	}

	@Override
	public PageInfo<BaiseeStuEva> selectStuEvaPage(int pageNum, int pageSize,String state,String className) {
		PageHelper.startPage(pageNum, pageSize);
			List<BaiseeStuEva> stuEvaPage = stuEvaMapper.selectStuEvaPage(state,className);
		PageInfo<BaiseeStuEva> pageInfo=new PageInfo<BaiseeStuEva>(stuEvaPage);
		return  pageInfo;
	}
	
	
	/*每个班级*/
	@Override
	public List<BaiseeStuEva> selectEvaPage(HttpServletRequest request) {
		Map<String, Object> map =new HashMap<String, Object>();
		String param = ParamUtils.getParameter(request, "className");
//		List<BaiseeStuEva> list =  stuEvaMapper.selClaTit(param);
		String titleName = ParamUtils.getParameter(request, "titleName");
//		System.err.println("list"+list);
		String state = ParamUtils.getParameter(request, "state");
		int num = ParamUtils.getPageNumParameters(request);
		int size = ParamUtils.getPageSizeParameters(request);
		PageHelper.startPage(num, size);
//		for (BaiseeStuEva baiseeStuEva : list) {
//			map.put("tit", baiseeStuEva.getTitleName());
			map.put("titleName", titleName);
			map.put("param", param);
			map.put("state", state);
//		}
			List<BaiseeStuEva> list =  stuEvaMapper.selectTitlEvaPage(map);

		return  list;
	}
	
	/*评价表下面的每个人*/
	@Override
	public List<BaiseeStuEva> selectTitlEvaPage(HttpServletRequest request) {
		Map<String, Object> map =new HashMap<String, Object>();
		String param = ParamUtils.getParameter(request, "className");
//		List<BaiseeStuEva> list =  stuEvaMapper.selClaTit(param);
		String titleName = ParamUtils.getParameter(request, "titleName");
//		System.err.println("list"+list);
		String state = ParamUtils.getParameter(request, "state");
		int num = ParamUtils.getPageNumParameters(request);
		int size = ParamUtils.getPageSizeParameters(request);
		PageHelper.startPage(num, size);
//		for (BaiseeStuEva baiseeStuEva : list) {
//			map.put("tit", baiseeStuEva.getTitleName());
			map.put("titleName", titleName);
			map.put("param", param);
			map.put("state", state);
//		}
			List<BaiseeStuEva> list =  stuEvaMapper.selectTitlEvaPage(map);

		return  list;
	}
	
	/*问题的内容*/
	@Override
	public List<Problem> selectEvapro(String ecid) {
			  List<Problem> list = stuEvaMapper.selectEvapro(ecid);
			return  list;
	}
	
	
	/*评价详情*/
	/**
	 * titleName:  b
		className: 30(高中)
		csName: dsd
	 */
	@Override
	public List<BaiseeStuEvaone> selectratioEvapro(HttpServletRequest request) {
		String param = ParamUtils.getParameter(request, "className");
		String titleName = ParamUtils.getParameter(request, "titleName");
		String csName = ParamUtils.getParameter(request, "csName");
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("titleName", titleName);
		map.put("param", param);
		map.put("csName", csName);
		int num = ParamUtils.getPageNumParameters(request);
		int size = ParamUtils.getPageSizeParameters(request);
		PageHelper.startPage(num, size);
		List<BaiseeStuEvaone> list =  stuEvaMapper.selectratioEvapro(map);
			for (BaiseeStuEvaone baiseeStuEvaone : list) {
				System.err.println("888888888888888");
				System.err.println(baiseeStuEvaone.getTitleName());
				System.err.println(baiseeStuEvaone.getClassName());

			}
			return  list;
	}


	@Override
	public List<BaiseeStuEva> selectEverEvaPage(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int stTitleValidate(String stTitle) {
		 int i = stuEvaMapper.stTitleValidate(stTitle);
		return i;
	}





		

}
