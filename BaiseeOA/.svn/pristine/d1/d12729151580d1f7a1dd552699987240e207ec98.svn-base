package cn.baisee.oa.dao.baisee;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.fileupload.BaiseeClassInfo;
import cn.baisee.oa.model.fileupload.BaiseeClassType;
import cn.baisee.oa.model.fileupload.BaiseeClassVideo;
import cn.baisee.oa.model.fileupload.BaiseeVideoDetails;

@Datasource(DatasourceTypes.OA)
public interface BaiseeClassTypeMapper {

	
	List<BaiseeClassType> getClassTypeList(Map<String, String> map);

	
	List<BaiseeVideoDetails> getVideoDetails(String typeID);

	String findByName(String userId);

	void insertByInfo(BaiseeClassInfo info);

	void insertByVideo(BaiseeClassVideo video);

	Long findByOrderNum(Map<String, Object> map);
	
	String findCoursesName(String id);
}
