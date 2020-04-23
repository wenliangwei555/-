package cn.baisee.oa.dao.baisee;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.student.BaiseeStudentApplicants;
@Datasource(DatasourceTypes.OA)
/**
 * 预报名学生DAO
 * @author chenxuegang
 *
 */
public interface BaiseeStudentApplicantsMapper {
	/**
	 * 添加预报名学生信息
	 * @param map
	 */
	int insertStudentApplicants(BaiseeStudentApplicants studentApplicants);
	/**
	 * 查看所有预报名的学生
	 * @param map
	 * @return
	 */
	List<BaiseeStudentApplicants> selectAllStudentApplicants(@Param("startTime")String startTime, @Param("endTime")String endTime,@Param("stuName")String stuName,@Param("areas")String areas,@Param("status")String status);
	/**
	 * 根据ID查看预报名学生的信息
	 * @param id
	 * @return
	 */
	BaiseeStudentApplicants selectStudentApplicantsByid(String id);
	/**
	 * 根据ID修改预报名学生的信息
	 * @param id
	 * @return
	 */
	
	int updateStudentApplicantsByid(BaiseeStudentApplicants studentApplicants);
	/**
	 * 修改预报名学生状态
	 * @param id
	 * @param status
	 * @return
	 */
	int updateStudentApplicantsStatus(@Param("id")String id,@Param("status")String status);
	/**
	 * 验证身份证号是否存在
	 * @param IdNumber
	 * @return
	 */
	String selectIDNumberVerification(String IdNumber);
	/**
	 * 根据ID删除预报名学生
	 * @param id
	 * @return
	 */
	int deleteStudentApplicantsByid(String id);
	
	

}
