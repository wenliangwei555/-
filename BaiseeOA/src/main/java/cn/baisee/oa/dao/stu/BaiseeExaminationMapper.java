package cn.baisee.oa.dao.stu;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.examination.BaiseeExamination;

@Datasource(DatasourceTypes.STU)
public interface BaiseeExaminationMapper {
	
	/**
	 *  查询试卷是否正在被使用着
	 * @param ids
	 * @return
	 */
	List<BaiseeExamination> getExaminationById(String ids);
	
	/**
	 *  查询所有的考试信息
	 * @return
	 */
	List<BaiseeExamination> selectExaminationList( Map<String, Object> map);
	
	/**
	 * 新增 考试信息
	 * @param examination
	 * @return
	 */
	int addExamination(BaiseeExamination examination);
	
	/**
	 * 根据考试Id查询详细信息 
	 * @param eid
	 * @return
	 */
	BaiseeExamination getExaminatioByEid(String eid);
	
	/**
	 * 根据Id修改考试信息 
	 * @param examination
	 */
	void updateExaminationById(BaiseeExamination examination);
	
	/**
	 * 删除试卷信息
	 * @param parameterValues
	 */
	void delExaminationById(String[] ids);
	

}
