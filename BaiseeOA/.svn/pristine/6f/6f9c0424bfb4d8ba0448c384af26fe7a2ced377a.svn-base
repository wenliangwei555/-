package cn.baisee.oa.dao.stu;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.BaiseeClazz;
import cn.baisee.oa.model.evaluate.BaiseeStuEva;
import cn.baisee.oa.model.evaluate.BaiseeStuEvaone;
import cn.baisee.oa.model.evaluate.EvaAndControl;
import cn.baisee.oa.model.evaluate.EvaGenTeb;
import cn.baisee.oa.model.evaluate.Problem;
import cn.baisee.oa.model.evaluate.SuPr;
import cn.baisee.oa.model.student.BaiseeStuUser;


/**
 * 学生评价Mapper
 * @author yzk
 *
 */
@Datasource(DatasourceTypes.STU)
public interface StuEvaluateMapper {
	
	
	/* 查询每个班评价表下面问题*/
	List<Problem> selectEvapro(String ecid);
	/* 查询每个班评价表下面问题的答案比例*/
	List<BaiseeStuEvaone>selectratioEvapro(Map<String, Object> map);
	
	/*查看学生具体评价的内容*/
	List<BaiseeStuEva> selectEverEvaPage(Map<String, Object> map);
	
	/*根据班级选择学生评价的信息*/	
	List<BaiseeStuEva> selectTitlEvaPage(Map<String, Object> map);
	
	/*根据班级查询评价表的信息*/	
	List<BaiseeStuEva> selectEvaPage(Map<String, Object> map);
	
	/*根据班级选择学生评价的信息*/	
	List<BaiseeStuEva> selectStuEvaPage(@Param("state")String state,@Param("className")String className);
	
	/*添加学生评价信息*/
	int insertEvaInfo(Problem thesaurus);
	
	/*分页查询所有问题的信息*/
	List<Problem> selectTgesPage(Map<String, Object> map);
	
	/*单个删除评价信息*/
	int delThes(String tId);
	
	/*上线评价信息*/
	int goOnline(Map<String, Object> map);
	
	/*下线评价信息*/
	int Offline(Map<String, Object> map);
	
	/*批量删除设置的评价信息*/
	void delAllThes(String [] ids);
	
	/*批量删除学生评价信息*/
	void delAllEva(String[] ids);
	
	/*更新评价信息*/
	int updateState(String id);
	
	/*根据ID查询学生评价的内容*/
	List<BaiseeStuEva> getEidStuEva(String eId);
	
	/* 预览  问题向详细信息*/
	EvaGenTeb getIdEvaGenInfo(String id);
	
	/* 预览  问题向详细信息*/
	Problem getIdProblemInfo(String id);
	
	/*修改问题信息*/
	int updateEvaInfo(Problem thesaurus);
	
	/*添加开户人的信息*/
	int insertStuUser(BaiseeStuUser stuUser);
	
	/*删除学生账户*/
	int deleteStuUser(String sId);
	
	/*批量删除学生账户*/
	void deleteStuUsers(String[] ids);

	int saveCreTeb(Map<String, Object> map1);

	int saveEvaGen(Map<String, Object> map);

	List<EvaGenTeb> selectListPage(Map<String, Object> map);

	List<Problem> getSuIdProInfos(String suID);
/*
	List<Problem> getPros(String[] id);*/
	
	/*根据 SUID删除supr表的信息*/
	int deleteSuPr(String suID);
	
	/*修改supr 表*/
	int insertSuPr(Map<String, Object> map);
	
	/*修改 生产的评价表*/
	void updateEvaGenInfo(Map<String, Object> map);

	Problem getStidProInfo(int stID);
	
	/*校验评价班级*/
	EvaGenTeb validaClaName(Map<String, Object> map);
	
	/*修改评价使用状态*/
	int updateEvaGenState(Map<String, Object> map);
	
	/*删除生成的问题表*/
	int delAllEvaGens(String [] ids);
	
	/*删除前查看评价问题是否在使用中*/
	List<SuPr> getDelThesInfo(String[] ids);

	/**
	 * 添加  评价和控制评价
	 * @return
	 */
	int saveEvaAndEvaControlTable(Map<String, Object> map);
	
	/*查询所有开启的评价信息*/
	EvaAndControl selectEvaConPage(String claName);

	List<EvaAndControl> getEvaCons(String [] ids);
	
	/*关闭评价*/
	int closeEva(String id);

	List<EvaAndControl> selectEvaConPage(List<BaiseeClazz> cla);

	List<BaiseeStuEva> selectStuEvaListPage(List<BaiseeClazz> list);
	
	/*校验标题名*/
	EvaGenTeb validateTitleName(String titleName);
	/**
	 * 效验问题名不能重复
	 * @param stTitle
	 * @return
	 */
	int stTitleValidate(String stTitle);
	
	
	List<BaiseeStuEva> selClaTit(String param);
}
