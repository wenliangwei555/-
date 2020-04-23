package cn.baisee.oa.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.baisee.oa.exception.OAServiceException;
import cn.baisee.oa.model.BaiseeClazz;
import cn.baisee.oa.model.evaluate.BaiseeStuEva;
import cn.baisee.oa.model.evaluate.BaiseeStuEvaone;
import cn.baisee.oa.model.evaluate.EvaAndControl;
import cn.baisee.oa.model.evaluate.EvaGenTeb;
import cn.baisee.oa.model.evaluate.Problem;
import cn.baisee.oa.page.pagehelper.PageInfo;

/**
 *  学生评价service
 * @author yzk
 *
 */
public interface StuEvaluateService {
	
	/* 查询每个班评价表下面问题*/
	List<Problem>selectEvapro(String ecid);
	/* 查询每个班评价表下面问题的答案比例*/
	List<BaiseeStuEvaone>	selectratioEvapro(HttpServletRequest request);
	
	/*查看学生具体评价的内容*/
	List<BaiseeStuEva> selectEverEvaPage(HttpServletRequest request);
	
	/*根据选择的班级查看学生的评价*/
	List<BaiseeStuEva> selectTitlEvaPage(HttpServletRequest request);
	
	/*根据班级查询评价表的信息*/	
	List<BaiseeStuEva> selectEvaPage(HttpServletRequest request);
	
	/*根据选择的班级查看学生的评价*/
	PageInfo<BaiseeStuEva> selectStuEvaPage(int pageNum, int pageSize,String state,String className);

	/*添加学生评价内容*/
	int insertEvaInfo(HttpServletRequest request,Problem thesaurus);
	
	/*分页查询所有问题的信息*/
	List<Problem> selectTgesPage(HttpServletRequest request);
	
	/*单个删除评价信息*/
	int delThes(String tId);
	
	/*上线评价信息*/
	int goOnline(HttpServletRequest request,String tId);
	
	/*下线评价信息*/
	int Offline(HttpServletRequest request,String tId);
	
	/*批量删除设置的评价信息*/
	void delAllThes(HttpServletRequest request, String [] ids) throws OAServiceException;
	
	/*批量删除学生评价信息*/
	void delAllEva(HttpServletRequest request)throws OAServiceException;
	
	/*更新浏览评价消息*/
	int updateState(String ecid);
	
	List<BaiseeStuEva> getEidStuEva(HttpServletRequest request);
	
	 /*预览  问题向详细信息*/
	Problem getIdProblemInfo(HttpServletRequest request);
	
	
	EvaGenTeb getIdEvaGenInfo(HttpServletRequest request);
	
	/*修改问题信息*/
	int updateEvaInfo(HttpServletRequest request, Problem thesaurus);
	
	/*生成评价表*/
	int saveCreTeb(HttpServletRequest request);
	
	
	List<EvaGenTeb> selectListPage(HttpServletRequest request);
	
	
	List<Problem> getSuIdProInfos(String suID);
	
	/*查询综合评价问题表*/
	List<Problem> selectByIdProblem(HttpServletRequest request);
	
	/*校验评价班级*/
	EvaGenTeb validaClaName(HttpServletRequest request);
	
	/*修改评价问题使用状态*/
	int updateEvaGenState(HttpServletRequest request);

	/**批量删除设置的评价信息
	 * @return */
	int delAllEvaGens(String [] ids)throws OAServiceException;
	
	/**
	 * 添加  评价和控制评价
	 * @return
	 */
	int saveEvaAndEvaControlTable(HttpServletRequest request);
	
	/*查詢所有开启评价的信息*/
	List<EvaAndControl> selectEvaConPage(HttpServletRequest request);

	List<EvaAndControl> getEvaCons(String [] ids);
	
	/*关闭评价*/
	int closeEva(String id);


	List<BaiseeStuEva> selectStuEvaListPage(List<BaiseeClazz> list);
	
	/*校验标题名*/
	EvaGenTeb validateTitleName(String titleName);
	/**
	 * 效验问题名不能重复 
	 * @param stTitle
	 * @return
	 */
	int stTitleValidate(String stTitle);

}
