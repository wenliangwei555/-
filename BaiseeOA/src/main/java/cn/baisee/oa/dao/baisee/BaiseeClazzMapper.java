package cn.baisee.oa.dao.baisee;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.BaiseeClazz;
import cn.baisee.oa.model.baiseeTerm;
import cn.baisee.oa.model.empinfo.Employee;
import cn.baisee.oa.model.student.BaiseeStudent;
/**
 * 班级的mapper接口
 * @author wangbaoxiang
 *
 */
@Datasource(DatasourceTypes.OA)
public interface BaiseeClazzMapper {
	/**
	 * 查询所有班级
	 * @return
	 */
	List<BaiseeClazz> selectCnames();
	/**
	 * 根据id查询班级名称
	 * @param cid
	 * @return
	 */
	BaiseeClazz selectCNAME(String cid);
	/**
	 * 查询班级的ID
	 * @return
	 */
	List<BaiseeClazz> selectCID(String cname);
	/**
	 * 查询所有班级信息
	 * @param cla
	 * @return
	 */
	List<BaiseeClazz> getCla(Map<String, Object> map);
	/**
	 * 添加班级
	 * @param cla
	 */
	Integer addCla(BaiseeClazz cla);
	/**
	 * 添加班级
	 * @param cla
	 */
	Integer addClassifyCla(BaiseeClazz cla);
	/**
	 * 批量删除班级
	 * @param ids
	 * @return
	 */
	Integer delAllCla(String[] ids);
	/**
	 * 通过班级Id查询班级
	 * @param map
	 * @return
	 */
	BaiseeClazz getClaById(Map<String, Object> map);
	/**
	 * 修改班级信息
	 * @param cla
	 * @return
	 */
	Integer updateCla(BaiseeClazz cla);
	/**
	 * 查询该班级是否有学生
	 * @param map
	 * @return
	 */
	List<BaiseeStudent> getStudentByCID(Map<String, Object> map);
	/**
	 * 删除班级
	 * @param map
	 * @return
	 */
	Integer delCla(Map<String, Object> map);
	/**
	 * 添加和修改时查询是否已有该班级
	 * @param map
	 * @return
	 */
	BaiseeClazz getClaNameList(Map<String, Object> map);
	
	List<Employee> getEmployeeName();
	/**
	 * 根据班级id获取这个班级的所属学籍
	 * @param claId
	 * @return
	 */
	String selectClaStatusByClaId(String claId);
	/**
	 * 设置该班级为毕业状态
	 * @param map
	 * @return
	 */
	int updClaIsGra(Map<String, Object> map);
	int getStudentCount(Map<String, Object> map);
	/**
	 *  根据登录用户查询对应的班级信息
	 * @param userId
	 * @return
	 */
	String[] selectById(String userId);
	/**
	 * 根据班级Id查询班级
	 * @param claId
	 * @return
	 */
	BaiseeClazz selectDetailedById(String claId);
	/**
	 *  查询出来未记载成绩的班级
	 * @param term 所记载成绩的班级
	 * @return
	 */
	List<BaiseeClazz> selectClazzByCid(List<baiseeTerm> term);
	/**
	 *  查询当前登录人的班级信息
	 * @param bsClass
	 * @return
	 */
	List<BaiseeClazz> selectClazzByClaId(Map<String, Object> bsClass);
	
	/**
	 *  查询班级 班级是正式班级1 并且是毕业班级0 
	 * @return
	 */
	List<BaiseeClazz> selectClass( Map<String, Object> map);
	
	/**
	 * 查询所有班级信息
	 * @param cla
	 * @return
	 */
	List<BaiseeClazz> getCla1(Map<String, Object> map);
	
	List<BaiseeClazz> getByIdCname(Map<String, Object> map);
	
	/**
	 * 查询所有班级信息
	 * @return
	 * liangfeng
	 */
	public List<BaiseeClazz> findAllClazzInfo();
	
}