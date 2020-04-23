package cn.baisee.oa.service;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.springframework.web.multipart.MultipartFile;

import com.jcraft.jsch.ChannelSftp.LsEntry;

import cn.baisee.oa.model.fileupload.BaiseeClassType;
import cn.baisee.oa.model.fileupload.BaiseeVideoDetails;
import cn.baisee.oa.page.pagehelper.PageInfo;

/**
 * 科目业务逻辑层
 * @author Administrator
 *
 */
public interface BaiseeClassTypeService {

	/**
	 * 查询所有相关科目
	 * @param map 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageInfo<BaiseeClassType> getClassTypeList(Map<String, String> map, int pageNum, int pageSize);

	/**
	 * 根据科目编号查询视频
	 * @param typeID
	 * @return
	 */
	List<BaiseeVideoDetails> getVideoDetails(String typeID);

	Boolean fileByUpload(MultipartFile multipartFile, String typeId, String videName,String userId,String grade);
	
	Vector<LsEntry> findCatalog(String fileCatalog);
	
	Vector<LsEntry> findCatalog(String fileCatalog,String teacherName);
	
	
	/**
	 * @param department 所属部门
	 * @param name	老师名称
	 * @param catalog	科目名称
	 * @return
	 */
	List<BaiseeVideoDetails> findCatalogVideo(String path);
		
	

}
