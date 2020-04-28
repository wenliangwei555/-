package cn.baisee.oa.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.Session;

import cn.baisee.oa.constants.UploadFileVideo;
import cn.baisee.oa.core.context.FileConnParamLoadHelper;
import cn.baisee.oa.core.file.SSHClientUtil;
import cn.baisee.oa.dao.baisee.BaiseeClassTypeMapper;
import cn.baisee.oa.model.fileupload.BaiseeClassInfo;
import cn.baisee.oa.model.fileupload.BaiseeClassType;
import cn.baisee.oa.model.fileupload.BaiseeClassVideo;
import cn.baisee.oa.model.fileupload.BaiseeVideoDetails;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeClassTypeService;
import cn.baisee.oa.utils.DateUtil;
import cn.baisee.oa.utils.PropertiesUtil;

@Service
public class BaiseeClassTypeServiceImpl implements BaiseeClassTypeService {
	private Logger log = Logger.getLogger(BaiseeClassTypeServiceImpl.class);
	@Autowired
	private BaiseeClassTypeMapper baiseeClassTypeMapper;
	
	@Override
	public PageInfo<BaiseeClassType> getClassTypeList(Map<String, String> map, int pageNum, int pageSize) {
		
		//开始分页
		PageHelper.startPage(pageNum, pageSize);
		//查询数据
		List<BaiseeClassType> list = baiseeClassTypeMapper.getClassTypeList(map);
		PageInfo<BaiseeClassType> info = new PageInfo<BaiseeClassType>(list);
		return info;
	}

	@Value("${video.reader.url}")
	private String ip;
	@Override
	public List<BaiseeVideoDetails> getVideoDetails(String typeID) {
		// ip = baiseeSystemParamMapper.findParamByCode("FILE_READER_URL").getParamValue();
		List<BaiseeVideoDetails> list = baiseeClassTypeMapper.getVideoDetails(typeID);
		for (BaiseeVideoDetails videoDetails : list) {
			String url = videoDetails.getVideoUrl();
			videoDetails.setVideoUrl(ip+"/"+url);
		}
		return list;
	}
	
	
	
	
	
	/**
	 *上传视频
	 */
	@Override
	public Boolean fileByUpload(MultipartFile multipartFile, String typeId, String videName,String userId,String grade) {
		BaiseeClassVideo video = null;
		
		String coursesName = baiseeClassTypeMapper.findCoursesName(typeId);
		   try {
			video = fileUpload(multipartFile,grade,coursesName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(video !=null) {
			Map<String, Object> map=new HashMap<>();
			map.put("userId", userId);
			map.put("typeId", typeId);
			Long orderNum = baiseeClassTypeMapper.findByOrderNum(map);
			System.out.println(orderNum);
			if(orderNum==null) {
				video.setOrderNum("0");
			}else {
				orderNum++;
				video.setOrderNum(String.valueOf(orderNum));
			}
			String userName = baiseeClassTypeMapper.findByName(userId);
			
			
			
			Date date = new Date();
			BaiseeClassInfo info = new BaiseeClassInfo();
			info.setId("INFO"+date.getTime());
			video.setInfoId("INFO"+date.getTime());
			video.setVideoName(videName);
			video.setOrderNum("0");
			info.setLecturerId(userId);
			info.setLecturerName(userName);
			info.setTypeId(typeId);
			info.setUpdateTime(DateUtil.getReqDate());
			try {
				baiseeClassTypeMapper.insertByInfo(info);
				baiseeClassTypeMapper.insertByVideo(video);
				return true;
			} catch (Exception e) {
				System.out.println("存入失败");
				e.printStackTrace();
			}
		}else {
			return false;
		}
		return false;
	}

	private BaiseeClassVideo fileUpload(MultipartFile multipartFile,String grade,String coursName)throws IOException {
		BaiseeClassVideo video = new BaiseeClassVideo();
		Boolean is  = null;
		InputStream inputStream = multipartFile.getInputStream();
		String fileName = multipartFile.getOriginalFilename().toString();
		if (multipartFile.getSize() != 0 && !multipartFile.isEmpty()) {
			if (multipartFile.getSize()<= UploadFileVideo.upload_maxsize) {
				if (this.checkName(fileName.substring(fileName.lastIndexOf(".")))) {
					is = true;
				}else {
					is = false;
					log.error("该后缀名不能上传");
				}
			}else {
				is = false;
				log.error("该文件超过500MB");
			}
		}else {
			is = false;
			log.error("没有文件");
		}
		
		if (is) {
			
			//开始生成视频名称
			String name = this.getFileName(fileName);
			//生成后缀名
			String fileEndName = this.fileNameEnd(fileName);
			//绝对路径
			String FileUrl = "video/"+grade+"/"+coursName+"/"+name+fileEndName;
			boolean isUpload = uploadByFile(FileUrl,inputStream,multipartFile);
			if (isUpload) {
				video.setUrl(FileUrl);
				video.setId("VIDEO"+new Date().getTime());
				video.setUpdateTime(DateUtil.getReqDate());
				return video;
			}else {
				return null;
			}
		}else {
			return null;
		}
	}

	/**
	 * 
	 * 上传文件
	 * @param fileUrl
	 * @param inputStream
	 * @return
	 */
	private boolean uploadByFile(String fileUrl, InputStream in,MultipartFile multipartFile ) {
		Session session = FileConnParamLoadHelper.getSshSession("sftpConfig02");
		
		try {
			return SSHClientUtil.upload(UploadFileVideo.UPLOADADDRESS+fileUrl, multipartFile.getBytes(), SSHClientUtil.openChannel(session, UploadFileVideo.CHANNELTYPE));
		} catch (IOException e) {
			e.printStackTrace();
			log.error("上传文件错误");
		}
		return false;
	}

	/**
	 * 生成视频名称
	 * @param fileName
	 * @return
	 */
	private String getFileName(String fileName) {
		Random random = new Random();
		return "" + random.nextInt(10000) + System.currentTimeMillis();
	}
	/**
	 * 获取后缀名
	 * @param fileName
	 * @return
	 */
	private String fileNameEnd(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * 判断是否能上传
	 * @param fileName
	 * @return
	 */
	private boolean checkName(String fileName) {
	for (int i = 0; i < UploadFileVideo.allowFiles.length; i++) {
		if (UploadFileVideo.allowFiles[i].equalsIgnoreCase(fileName)) {
			return true;
		}
	}
		return false;
	}

	
	/**
	 * 获取video目录 下所有子文件夹名称
	 */
	@Override
	public Vector<LsEntry> findCatalog(String fileCatalog) {
		Session sshSession =  FileConnParamLoadHelper.getSshSession("sftpConfig02");
		ChannelSftp sftp = SSHClientUtil.openChannel(sshSession, "sftp");
		 Vector<LsEntry> res = null;
		 //从配置文件中获取要查看的文件路径
		 PropertiesUtil ftpConf = new PropertiesUtil("ftp.properties");
		 String prefix = ftpConf.get("file.server.catalog");
		 String highDevelop = ftpConf.get("video.department.highDevelop");
		 String highTest = ftpConf.get("video.department.highTest");
		 String firstCulture = ftpConf.get("video.department.firstCulture");
		 String firstTechnology = ftpConf.get("video.department.firstTechnology");
		 if(fileCatalog.equals("highDevelop")) {
			  res =SSHClientUtil.listFiles(prefix+highDevelop, sftp); 
		 }else if(fileCatalog.equals("highTest")) {
			  res =SSHClientUtil.listFiles(prefix+highTest, sftp); 
		 }else if(fileCatalog.equals("firstCulture")) {
			  res =SSHClientUtil.listFiles(prefix+firstCulture, sftp); 
		 }else if(fileCatalog.equals("firstTechnology")) {
			  res =SSHClientUtil.listFiles(prefix+firstTechnology, sftp); 
		 }else {
			  res =SSHClientUtil.listFiles(prefix+fileCatalog, sftp); 
		 }
		return res;
		
	}
	/**
	 * 获得某个老师文件下所有文件夹名称
	 */
	@Override
	public Vector<LsEntry> findCatalog(String fileCatalog, String teacherName) {
		Session sshSession =  FileConnParamLoadHelper.getSshSession("sftpConfig02");
		ChannelSftp sftp = SSHClientUtil.openChannel(sshSession, "sftp");
		 Vector<LsEntry> res = null;
		//从配置文件中获取要查看的文件路径
		 PropertiesUtil ftpConf = new PropertiesUtil("ftp.properties");
		 String prefix = ftpConf.get("file.server.catalog");
		 String highDevelop = ftpConf.get("video.department.highDevelop");
		 String highTest = ftpConf.get("video.department.highTest");
		 String firstCulture = ftpConf.get("video.department.firstCulture");
		 String firstTechnology = ftpConf.get("video.department.firstTechnology");
		 if(fileCatalog.equals("highDevelop")) {
			  res =SSHClientUtil.listFiles(prefix+highDevelop+"/"+teacherName, sftp); 
		 }else if(fileCatalog.equals("highTest")) {
			  res =SSHClientUtil.listFiles(prefix+highTest+"/"+teacherName, sftp); 
		 }else if(fileCatalog.equals("firstCulture")) {
			  res =SSHClientUtil.listFiles(prefix+firstCulture+"/"+teacherName, sftp); 
		 }else if(fileCatalog.equals("firstTechnology")) {
			  res =SSHClientUtil.listFiles(prefix+firstTechnology+"/"+teacherName, sftp); 
		 }
		return res;
	}
	
	
	
	@Override
	public List<BaiseeVideoDetails> findCatalogVideo(String path) {
		//从配置文件中获取要查看的文件路径
		PropertiesUtil ftpConf = new PropertiesUtil("ftp.properties");
		String host = ftpConf.get("video.reader.url");
		Session sshSession =  FileConnParamLoadHelper.getSshSession("sftpConfig02");
		ChannelSftp sftp = SSHClientUtil.openChannel(sshSession, "sftp");
		Vector<LsEntry> res =SSHClientUtil.listFiles(path, sftp);
		 String[] strs = path.split("/");
		 List<BaiseeVideoDetails> list = null;
			if (res != null && res.size() > 0) {
				list = new ArrayList<BaiseeVideoDetails>();
				BaiseeVideoDetails baiseeVideoDetails = null;
				for (LsEntry ls : res) {
					String fileName = ls.getFilename();
					 if (!fileName.equals(".")&&!fileName.equals("..")&&fileName.substring(fileName.lastIndexOf(".")+1).equals("m3u8")){
						 baiseeVideoDetails = new BaiseeVideoDetails();
						 baiseeVideoDetails.setId(ls.getFilename());
						 baiseeVideoDetails.setName(strs[8]);
						 baiseeVideoDetails.setVideoName(ls.getFilename());
						 //host是Nginx请求转发的根路径，strs[6]-[8]是视频的相对具体路径
						 baiseeVideoDetails.setVideoUrl(host+"/"+strs[6]+"/"+strs[7]+"/"+strs[8]+"/"+strs[9]+"/"+ls.getFilename());
						 baiseeVideoDetails.setLecturerName(strs[7]);
						 list.add(baiseeVideoDetails);					 
					 }
				}
			}
		 
		return list;
		
	}
	
}
