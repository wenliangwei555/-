package cn.baisee.oa.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;

import cn.baisee.oa.constants.UploadFileVideo;
import cn.baisee.oa.core.context.FileConnParamLoadHelper;
import cn.baisee.oa.core.file.SSHClientUtil;
import cn.baisee.oa.dao.baisee.BaiseeVideoSubjectMapper;
import cn.baisee.oa.model.BaiseeVideoSubject;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeVideoSubjectService;
import cn.baisee.oa.utils.PropertiesUtil;
import cn.baisee.oa.utils.StringUtil;
/**
 * 科目管理业务逻辑层实现
 * @author liangfeng
 */
@Service
public class BaiseeVideoSubjectServiceImpl implements BaiseeVideoSubjectService {

	@Autowired
	private BaiseeVideoSubjectMapper videoSubjectMapper;

	
	@Override
	public PageInfo<BaiseeVideoSubject> getVideoSubjects(int pageNum, int pageSize, Map<String, String> map) {
		PageHelper.startPage(pageNum, pageSize);
		List<BaiseeVideoSubject> list=videoSubjectMapper.getVideoSubjects(map);
		PageInfo<BaiseeVideoSubject> page=new PageInfo<BaiseeVideoSubject>(list);
		return page;
	}

	@Override
	public List<BaiseeVideoSubject> getVideoSubjectAll() {
		// TODO Auto-generated method stub
		return videoSubjectMapper.getVideoSubjectAll();
	}

	@Override
	public List<BaiseeVideoSubject> getVideoSubjectById(String id) {
		// TODO Auto-generated method stub
		return videoSubjectMapper.getVideoSubjectById(id);
	}

	@Override
	public Integer saveOrUpdateVideoSubject(BaiseeVideoSubject videoSubject) {
		//获取视频目录
		String videoPath = new PropertiesUtil("ftp.properties").get("file.server.catalog");
		//拼装要创建的路径
		videoPath = videoPath+videoSubject.getDeptType()+"/"+videoSubject.getTeacherName()+"/"+videoSubject.getSubjectName()+"/m3u8";
		Session session = FileConnParamLoadHelper.getSshSession("sftpConfig02");/*""*/
		ChannelSftp sftp = SSHClientUtil.openChannel(session, UploadFileVideo.CHANNELTYPE);
		boolean flag = SSHClientUtil.makeDirs(videoPath, sftp);
		if (flag) {

			videoSubject.setSubjectPath(videoPath);
			//判断如果id为空就是新增操作，反之则是更新操作
			if (StringUtil.isNotEmpty(videoSubject.getId())) {
				return videoSubjectMapper.updateVideoSubject(videoSubject);
			}
			return videoSubjectMapper.saveVideoSubject(videoSubject);	
		}
		return null;
	}

	@Override
	public Integer delVideoSubject(String[] ids) {
		// TODO Auto-generated method stub
		return videoSubjectMapper.delVideoSubject(ids);
	}

	@Override
	public List<BaiseeVideoSubject> checkTname(String subjectName, String teacherName) {
		// TODO Auto-generated method stub
		return videoSubjectMapper.checkTname(subjectName, teacherName);
	}
	

}
