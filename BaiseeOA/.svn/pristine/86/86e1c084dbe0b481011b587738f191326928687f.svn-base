package cn.baisee.oa.service;
import cn.baisee.oa.model.student.BaiseeStudentApplicants;
import cn.baisee.oa.page.pagehelper.PageInfo;

/**
 * 预报名学生
 * @author chenxuegang
 *
 */
public interface BaiseeStudentApplicantsService {
	/**
	 * 添加预报名学生
	 * @param studentApplicants
	 */
	int addStudentApplicants(BaiseeStudentApplicants studentApplicants);
	/**
	 * 查看所有预报名的学生
	 * @param map
	 * @return
	 */
	PageInfo<BaiseeStudentApplicants> getAllStudentApplicants(int pageNum,
			int pageSize, String startTime, String endTime,String stuName,String areas,String status);
	/**
	 * 根据ID查看预报名学生的信息
	 * @param id
	 * @return
	 */
	BaiseeStudentApplicants getStudentApplicantsByid(String id);
	/**
	 * 查看身份证是否存在用于验证
	 * @param IdNumber
	 * @return
	 */
	String getIDNumberVerification(String IdNumber);
	/**
	 * 修改预报名学生信息
	 * @param studentApplicants
	 * @return
	 */
	int alterApplicants(BaiseeStudentApplicants studentApplicants);
	/**
	 * 根据ID修改预报名学生状态
	 * @param id
	 * @param status
	 * @return
	 */
	int updateApplicantsStuStatus(String id,String status);
	
	
	

}
