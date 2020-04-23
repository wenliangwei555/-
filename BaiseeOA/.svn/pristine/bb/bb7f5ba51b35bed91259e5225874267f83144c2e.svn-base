package cn.baisee.oa.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.ChannelSftp.LsEntry;

import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.core.context.FileConnParamLoadHelper;
import cn.baisee.oa.core.file.SSHClientUtil;
import cn.baisee.oa.model.BaiseeVideoSubject;
import cn.baisee.oa.model.SessionUserInfo;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeVideoSubjectService;
import cn.baisee.oa.utils.ParamUtils;
import cn.baisee.oa.utils.PropertiesUtil;
import cn.baisee.oa.utils.SessionUtil;
import org.springframework.web.servlet.tags.Param;

@Controller
@RequestMapping("videoSubject")
public class BaiseeVideoSubjectController {

	@Autowired
	private BaiseeVideoSubjectService videoSubjectService;
	
	/**
	 * 分页
	 * @param request
	 * @return
	 */
	@RequestMapping("subjects")
	@Role(value="SPKMGL")
	public ModelAndView subjects(HttpServletRequest request,String itemlableSearch) {
		ModelAndView mv = new ModelAndView("fileupload/subject_manage");
		int pageNum = ParamUtils.getPageNumParameters(request);
		int pageSize = ParamUtils.getPageSizeParameters(request);
		Map<String, String> map = new HashMap<>();
		map.put("itemlableSearch",itemlableSearch == null?"":itemlableSearch);
		PageInfo<BaiseeVideoSubject> page = videoSubjectService.getVideoSubjects(pageNum, pageSize, map);
		mv.addObject("pageResult", page);
		mv.addObject("itemlableSearch", itemlableSearch);
		return mv;
	}
	
	/**
	 * 跳转添加页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toAddSubject")
	@Role(value="SPKM_XZKM")
	public ModelAndView toAddSubject(HttpServletRequest request, String deptType){
		ModelAndView model = new ModelAndView();
		model.setViewName("fileupload/subject_add");//设置跳转页面
		model.addObject("deptType",deptType);
		return model;
	}
	
	/**
	 * 添加高中开发科目
	 * @param request
	 * @param videoSubject
	 * @return
	 */
	@RequestMapping("/addSubject/develop")
	@Role(value="SPKM_XZKM")
	public ModelAndView addSubject(HttpServletRequest request, BaiseeVideoSubject videoSubject){
		//获取当前用户名称
		SessionUserInfo user = SessionUtil.getUserInfo(request);
		videoSubject.setcId(user.getUserId());
		videoSubject.setcName(user.getUserName());
		videoSubject.setcTime (new Date());
		videoSubjectService.saveOrUpdateVideoSubject(videoSubject);
		return subjects(request, null);
	}
	
	/**
	 * 分页	高中开发
	 * @param request
	 * @return
	 */
	@RequestMapping("highDevelop")
	@Role(value="WJGL_GZKFWJ")
	public ModelAndView highDevelop(HttpServletRequest request,String itemlableSearch) {
		ModelAndView mv = new ModelAndView("fileupload/subject_list");
		int pageNum = ParamUtils.getPageNumParameters(request);
		int pageSize = ParamUtils.getPageSizeParameters(request);
		BaiseeVideoSubject subject = new BaiseeVideoSubject();
		Map<String, String> map = new HashMap<>();
		map.put("itemlableSearch",itemlableSearch == null?"":itemlableSearch);
		map.put("deptType","highDevelop");
		PageInfo<BaiseeVideoSubject> page = videoSubjectService.getVideoSubjects(pageNum, pageSize, map);
		mv.addObject("pageResult", page);
		mv.addObject("deptType", "highDevelop");
		mv.addObject("itemlableSearch",itemlableSearch);
		return mv;
	}
	
	/**
	 * 分页	初中文化
	 * @param request
	 * @return
	 */
	@RequestMapping("firstCulture")
	@Role(value="WJGL_CZWHWJ")
	public ModelAndView firstCulture(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("fileupload/subject_list");
		int pageNum = ParamUtils.getPageNumParameters(request);
		int pageSize = ParamUtils.getPageSizeParameters(request);
		Map<String, String> map = new HashMap<>();
		map.put("deptType","firstCulture");
		PageInfo<BaiseeVideoSubject> page = videoSubjectService.getVideoSubjects(pageNum, pageSize, map);
		mv.addObject("pageResult", page);
		mv.addObject("deptType", "firstCulture");
		return mv;
	}
	
	/**
	 * 分页	初中技术
	 * @param request
	 * @return
	 */
	@RequestMapping("firstTechnology")
	@Role(value="WJGL_CZJSWJ")
	public ModelAndView firstTechnology(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("fileupload/subject_list");
		int pageNum = ParamUtils.getPageNumParameters(request);
		int pageSize = ParamUtils.getPageSizeParameters(request);
		Map<String, String> map = new HashMap<>();
		map.put("deptType","firstTechnology");
		PageInfo<BaiseeVideoSubject> page = videoSubjectService.getVideoSubjects(pageNum, pageSize, map);
		mv.addObject("pageResult", page);
		mv.addObject("deptType", "firstTechnology");
		return mv;
	}
	
	/**
	 * 分页	高中测试
	 * @param request
	 * @return
	 */
	@RequestMapping("highTest")
	@Role(value="WJGL_GZCSWJ")
	public ModelAndView highTest(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("fileupload/subject_list");
		int pageNum = ParamUtils.getPageNumParameters(request);
		int pageSize = ParamUtils.getPageSizeParameters(request);
		Map<String, String> map = new HashMap<>();
		map.put("deptType","highTest");
		PageInfo<BaiseeVideoSubject> page = videoSubjectService.getVideoSubjects(pageNum, pageSize, map);
		mv.addObject("pageResult", page);
		mv.addObject("deptType", "highTest");
		return mv;
	}
	
	/**
	 * 将目录下的MP4转换为m3u8
	 * @param request
	 * @return
	 */
	@RequestMapping("convertVideo")
	@Role(value="SPZM")
	public ModelAndView convertVideo(HttpServletRequest request) {
		Session sshSession =  FileConnParamLoadHelper.getSshSession("sftpConfig02");
		ChannelSftp sftp = SSHClientUtil.openChannel(sshSession, "sftp");
		//获取存放MP4的目录
		List<BaiseeVideoSubject> videoSubjects = videoSubjectService.getVideoSubjectAll();
		List<String> subjects = null;
		if (videoSubjects != null  && videoSubjects.size() > 0) {
			subjects = new ArrayList<>();
			String str = null;
			for (BaiseeVideoSubject videoSubject : videoSubjects) {
				str = videoSubject.getSubjectPath();
				subjects.add(str.substring(0, str.lastIndexOf("/")));
			}
		}
		Map<String, Object> map = null;
		//使用map封装数据，key：MP4存放路径，value：MP4视频名称集合
		if (subjects != null && subjects.size() > 0) {
			map = new HashMap<>();
			for (String path : subjects) {
				//进入MP4存放的目录，并获取该目录下的所有文件
				Vector<LsEntry> res =SSHClientUtil.listFiles(path, sftp, false);
				if (res != null && res.size() > 0) {
					List<String> names = new ArrayList<>();
					for (LsEntry ls : res) {
						if (!ls.getFilename().equals(".")&&!ls.getFilename().equals("..")&&ls.getFilename().contains("mp4")){
							names.add(ls.getFilename());
						}
					}
					map.put(path, names);
				}
			}
		}
		//获取ffmpeg安装路径
		String ffmpeg =  new PropertiesUtil("ftp.properties").get("ffmpeg.install.path");
		//拼装转换视频的命令
		String cmd = "";
		for (String path : map.keySet()) {
			cmd += "cd "+path+";";
			List<String> names = (List<String>) map.get(path);
			for (String name : names) {
				cmd += ffmpeg+" -i "+name+" -hls_time 30 -hls_list_size 0  -hls_segment_filename m3u8/"+name.substring(0, name.lastIndexOf("."))
				+"_%05d.ts m3u8/"+name.substring(0, name.lastIndexOf("."))+".m3u8;";
			}
			cmd += "rm -rf *.mp4;";
		}
		SSHClientUtil.execCmd(cmd, sshSession, true);
		
		return highDevelop(request,"");
	}
	
}
