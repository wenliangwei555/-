package cn.baisee.oa.service;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.springframework.web.multipart.MultipartFile;

import com.jcraft.jsch.ChannelSftp.LsEntry;

import cn.baisee.oa.model.BaiseeDossierFile;
import cn.baisee.oa.page.pagehelper.PageInfo;
/**
 * 档案文件管理 业务逻辑层
 * @author liangfeng
 */
public interface BaiseeDossierFileService {

	/**
	 * 查询档案文件管理表
	 * @param pageNum	每页显示多少条
	 * @param pageSize	显示第几页
	 * @param map		查询条件
	 * @return
	 */
	public PageInfo<BaiseeDossierFile> getDossierFiles(int pageNum,int pageSize, Map<String, String> map);
	
	/**
	 * 查询档案管理表
	 * @return
	 */
	public List<BaiseeDossierFile> getDossierFileAll();
	
	/**
	 * 查询档案管理表
	 * map 查询条件
	 * @return
	 */
	public List<BaiseeDossierFile> getDossierFileAll(Map<String, String> map);
	
	/**
	 * 根据id查询档案管理表表详情
	 * @param id	任务id
	 * @return
	 */
	public BaiseeDossierFile getById(String id);
	
	/**
	 * 新增或修改任务
	 * @param dossierFile	任务对象
	 * @return
	 */
	public Integer saveOrUpdateTask( MultipartFile input_file, BaiseeDossierFile dossierFile);
	
	
	/**
	 * 批量删除任务
	 * @param ids	数组中装的是任务id
	 * @return
	 */
	public Integer delDossierFiles(String [] ids);
	
	/**
	 * 查询该路径下是否已有此档案文件名称
	 * @param fName	校区名称
	 * @return 档案文件管理集合
	 */
	public List<BaiseeDossierFile> checkFileName(String fName);
	
	
	/**
	 *	根据查询条件查询数据
	 * @param	itemlableSearch	名称搜索条件
	 * @param	classInfo	班级名称搜索条件
	 * @param	type	档案类型搜索条件
	 * @return
	 */
	public Vector<LsEntry> findClassAll(String itemlableSearch, String classInfo, String type);
	
	/**
	 * 根据任务id，转换该任务的档案文件
	 * @param id
	 * @return
	 */
	public String convertFile(String id);
	
	/**
	 * 查询上传文档的数量
	 * @return
	 */
	public int countTaskNun();
	/*
	*
	* 查询档案名字是否存在
	* */
    public int getName(String name);
}
