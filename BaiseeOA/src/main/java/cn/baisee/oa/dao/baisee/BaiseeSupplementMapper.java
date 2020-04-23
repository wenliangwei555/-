package cn.baisee.oa.dao.baisee;

import cn.baisee.oa.model.student.BaiseeStudentSupplement;

/**
 *  学生详细信息
 * @author ningmeng
 *
 */
public interface BaiseeSupplementMapper {
	/**
	 * 根据学生ID查询学生的详细地址
	 * @param stuId
	 * @return
	 */
	BaiseeStudentSupplement selectDetailedById(String stuId);

}
