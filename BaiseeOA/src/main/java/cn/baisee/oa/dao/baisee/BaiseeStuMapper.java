package cn.baisee.oa.dao.baisee;


import java.util.List;
import java.util.Map;

import cn.baisee.oa.model.BaiseeClassMerger;
import org.apache.ibatis.annotations.Param;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.BaiseeClazz;
import cn.baisee.oa.model.BaiseeEvaluateInfo;
import cn.baisee.oa.model.PhoneAndID;
import cn.baisee.oa.model.student.BaiseeStudent;
import cn.baisee.oa.model.student.BaiseeStudentEducation;
import cn.baisee.oa.model.student.BaiseeStudentFamily;
import cn.baisee.oa.model.student.BaiseeStudentRecord;
import cn.baisee.oa.model.student.BaiseeStudentSupplement;
import cn.baisee.oa.model.tuition.RefundRule;

/**
 * 学生mapper接口
 * @author sdx
 *
 */
@Datasource(DatasourceTypes.OA)
public interface BaiseeStuMapper {
	/**
	 * 根据学生id获取信息
	 * @param sid
	 * @return
	 */
	BaiseeStudent selectById(String sid);
	/**
	 * 根据学生姓名获取信息
	 * @param sname
	 * @return
	 */
	List<BaiseeStudent> selectByName(String sname);
	/**
	 * 查询学生姓名和ID
	 * @param map
	 * @return
	 */
	List<BaiseeStudent> selectAll(Map<String, Object> map);
	/**
	 * 根据姓名查询id
	 * @param map
	 * @return
	 */
	List<BaiseeStudent> selectNameById(Map<String, Object> map);
	/**
	 * 1.查询所有试听学生
	 * @return
	 */
	List<BaiseeStudent> selectAllAudStu(Map<String, Object> map);
	
	/**
	 * 2.查询所有正式学生
	 * @param itemlableSearch
	 * @return
	 */
	List<BaiseeStudent> selectAllForStu(Map<String, Object> map);
	
	/**
	 * 去操作表查询id
	 * @param stuId
	 * @return
	 */
	String selectSidFromOperation(String stuId);
	/**
	 * 去操作变查询最后一次操作时间
	 * @param stuId
	 * @return
	 */
	String selectUpdateTime(String stuId);
	/**
	 * 修改学生状态
	 * @param stuId
	 */  
	int updateStatus(@Param("stuId")String stuId,@Param("addParam")String addParam);
	/**
	 * 在操作表中新增记录
	 * @param stuId
	 * @param operator
	 */
	int insertOperation(@Param("stuId")String stuId,@Param("operator")String operator,@Param("operationStart")String operationStart,@Param("operationEnd")String operationEnd);
	/**
	 * 在操作表中修改
	 * @param stuId
	 * @param operator
	 */
	int updateOperation(@Param("stuId")String stuId,@Param("operator")String operator,@Param("operationStart")String operationStart,@Param("operationEnd")String operationEnd);
	/**
	 * 批量删除学员时，执行删除对应操作表
	 * @param ids
	 */
	void deleteOperation(String[] ids);
	/**
	 * 修改试听生试听班级为正式班级
	 * @param stuId
	 * @param claId
	 * @return
	 */
	int updateAudToFor(@Param("stuId")String stuId,@Param("claId")String claId,@Param("stuEnrolmentTime")String stuEnrolmentTime);
	
	/**
	 * 查询所有班级下拉显示 ,根据clatype查询
	 * @return
	 */
	List<BaiseeClazz> selectClass(@Param("claType")String claType);
	
	
	/**
	 * 校验学生身份证
	 * @param stuIdNumber
	 * @return
	 */
	String selectStudentIDNumberVerification(@Param("stuIdNumber")String stuIdNumber,@Param("stuId")String stuId);
	/**
	 * 校验学生身份证
	 * @param stuIdNumber
	 * @return
	 */
	String selectStuIDNumberVerification(@Param("stuIdNumber")String stuIdNumber);
	
	/**
	 * 校验员工身份证
	 * @param stuIdNumber
	 * @return
	 */
	String selectEmployeeIDNumberVerification(@Param("empIdNumber")String empIdNumber);
	
	/**
	 * 校验学生手机号
	 * @param stuIdNumber
	 * @return
	 */
	String selectStudentMobileVerification(@Param("stuMobile")String stuMobile,@Param("stuId")String stuId);
	
	/**
	 * 校验员工手机号
	 * @param empIdNumber
	 * @return
	 */
	String selectEmployeeMobileVerification(@Param("empMobile")String empMobile);
	
	/**添加
	 * 1.添加学生基本信息
	 * @param student
	 */
	void addStudent(BaiseeStudent student);
	/**
	 * 2.添加学生附加信息
	 * @param supplement
	 */
	void addStudentSupplement(BaiseeStudentSupplement supplement);
	/**
	 * 3.添加学生教育信息
	 * @param education
	 */
	void addStudentEducation(BaiseeStudentEducation education);
	/**
	 *	4.添加学生家庭信息
	 * @param family
	 */
	void addStudentFamily(BaiseeStudentFamily family);
	
	
	/**
	 * 通过stuid查询
	 * 1.通过stuid查询学生基本信息
	 * @param stuId
	 * @return
	 */
	BaiseeStudent selectByStudentId(String stuId);
	/**
	 * 2.通过stuid查询学生家庭信息
	 * @param stuId
	 * @return
	 */
	List<BaiseeStudentFamily> selectByStudentIdQueryFamily(String stuId);
	/**
	 * 3.通过stuid查询学生教育信息
	 * @param stuId
	 * @return
	 */
	BaiseeStudentEducation selectByStudentIdQueryEducation(String stuId);
	/**
	 * 4.通过stuid查询学生附加信息
	 * @param stuId
	 * @return
	 */
	BaiseeStudentSupplement selectByStudentIdQuerySupplement(String stuId);
	
	/**
	 * 通过id修改
	 * 1.通过id修改学生基本信息
	 * @param student
	 */
	void updateStudentById(BaiseeStudent student);
	/**
	 * 2.通过id修改学生附加信息
	 * @param supplement
	 */
	void updateStudentSupById(BaiseeStudentSupplement supplement);
	/**
	 * 3.通过id修改学生教育信息
	 * @param education
	 */
	void updateStudentEduById(BaiseeStudentEducation education);
	/**
	 * 4.通过pkBefi修改学生家庭信息
	 * @param family
	 */
	void updateStudentFamById(BaiseeStudentFamily family);
	
	
	/**
	 * 批量删除
	 * 1.删除学员基本信息
	 * @param id
	 */
	void deleteStudent(String[] ids);
	/**
	 * 2.删除学员附加信息
	 * @param ids
	 */
	void deleteStudentSupplement(String[] ids);
	/**
	 * 3.删除学员教育信息
	 * @param ids
	 */
	void deleteStudentEducation(String[] ids);
	/**
	 * 4.删除学员家庭信息
	 * @param ids
	 */
	void deleteStudentFamily(String[] ids);
	/**
	 * 获得学生的手机号和身份证
	 * @return
	 */
	List<PhoneAndID> readPhoneAndID();
	/**
	 * 批量正式学生信息
	 */
	
	void addImportExcel(BaiseeStudent student);
	/**
	 * 批量导入试听学院
	 * @param student
	 */
	void addAudImportExcel(BaiseeStudent student);
	
	/**
	 * 修改这个学生的缴学费状态  1：缴纳清楚，2：未缴纳清楚
	 * @param map
	 */
	void updateStudentTuitionStatusById(Map<String, String> map);
 
	/**
	 * 根据学费Id查询退费公式和失效时间，查询退费规则主键，失效时间标识
	 * @param trId
	 * @return
	 */
	RefundRule selectFormulaInfoByTrid(@Param("tuId")String tuId);
 
	
	/**
	 *  修改学生返费状态
	 * @param map
	 */
	int updateThtStudentStatus(Map<String, Object> map);
	
	/**
	 * 根据班级ID查询班级内所有学生的ID
	 * @param map
	 * @return
	 */
	List<String> getStudentId(Map<String, Object> map);
	/**
	 * 修改所有学生为毕业状态
	 * @param list
	 * @return
	 */
	int updateStudentStatus(List<String> list);
	/**
	 * 
	 * @param map
	 * @return
	 */
	public List<BaiseeStudent> selectForTuitionList(Map<String, Object> map);
	/**
	 *  根据班级Id和学生姓名获取学生信息
	 * @param maps
	 * @return
	 */
	BaiseeStudent selectStuByCidAndName(Map<String, Object> maps);
	
	/**
	 * 查询学生和班级（固定条件学生状态是正式，学生类型是正式）
	 * @param map 可以根据学生姓名筛选学生
	 * @return
	 */
	public List<BaiseeStudent> selectStuAndClass(Map<String, String> map);
	/**
	 * 档案查询
	 * @param stuId
	 * @return
	 */
	public BaiseeStudentRecord  selectStuRecord(Map<String, String> map);
	/**
	 * 查询学生评定
	 * @param map
	 * @return
	 */
	public BaiseeStudentRecord selectStuAssess(Map<String, String> map);
	
	/**
	 * 学生开户
	 * @param sId
	 * @return
	 */
	int updateStuState(Map<String, Object> map);
	
	/**
	 * 学生选择框
	 * @return
	 */
	List<BaiseeStudent> selectStuForSel(Map<String, Object> map);



	public int addMerger(BaiseeClassMerger baiseeClassMerger);

	public void updataMerger(Map<String, String> map);


	public void updateMerger1(Map<String, String> map);

	public List<BaiseeClassMerger> selectMerger(Map<String, String> map);
}
