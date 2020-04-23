package cn.baisee.oa.dao.baisee;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.Insurance.BaiseeInsurance;

@Datasource(DatasourceTypes.OA)
public interface BaiseeInsuranceMapper {

		/**
		 * 分页查询学生保险
		 * @param map
		 * @return
		 */
		List<BaiseeInsurance> selectStudentInsurance(BaiseeInsurance baiseeInsurance);

		/**
		 * 添加一条保险
		 * @param insurance
		 */
		void addInsurance(BaiseeInsurance insurance);
	
		/**
		 * 查询个人录入的信息
		 * */
		BaiseeInsurance getStuInsuInfo(String id);
		/**
		 * 根据学生ID查看保险是否存在
		 * @param stuid
		 * @return
		 */
		Integer selectStuInsuInfoByStuId(String stuId);
		/**
		 * 办理学生保险
		 * @param id
		 * @return
		 */
		int toAddStuInsurance(BaiseeInsurance baiseeInsurance);
		
		/**
		 * 校验保险单号
		 * @param map
		 * @return
		 */
		BaiseeInsurance selectcheckInsuranceNumber(Map<String, String> map);
		
		/**
		 * 查询所有人的保险到期信息
		 * @return
		 */
		List<BaiseeInsurance> queryAllInsurance();
		
		/**
		 * 修改到期保险的状态
		 */
		void updateExpireInsurance(@Param("id")String id,@Param("status")String status);
		/**
		 * 删除一条保险记录
		 * @param id
		 * @return
		 */
		int delInsurance(String id);

		/**
		 * 修改一条保险记录
		 * @param ins
		 * @return
		 */
		int updIns(BaiseeInsurance ins);

		/**
		 * 导出 excel 是修改导出学生的保险状态
		 */
		void updateStatus(Map<String, String> map);
		/**
		 * 根据学生ID修改学生班级
		 * @param stuID
		 * @param className
		 */
		void updateClassNameBystuID(@Param("stuID")String stuID,@Param("className")String className,@Param("classID")String classID);
		
	
}
