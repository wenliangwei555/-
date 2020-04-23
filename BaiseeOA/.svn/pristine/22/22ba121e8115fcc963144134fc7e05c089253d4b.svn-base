package cn.baisee.oa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import cn.baisee.oa.annotation.Login;
import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.constants.AppConstants;
import cn.baisee.oa.model.ResponseResult;
import cn.baisee.oa.model.fileupload.BaiseeVideoDetails;
import cn.baisee.oa.service.BaiseeClassTypeService;
import cn.baisee.oa.vo.FileVO;

@Controller
@RequestMapping(value = "upload")
public class BaiseeClassTypeController {
	
	private static final Log logger = LogFactory.getLog(BaiseeClassTypeController.class);
	
	@Autowired
	private  BaiseeClassTypeService baiseeClassTypeService;
	
	/**
	 * 跳转到高中部开发页面
	 * @return
	 */
	/*@RequestMapping("/toDevelopFile.ht")
	@Role(value="WJGL_GZKFWJ")
	public ModelAndView toDevelopList(HttpServletRequest request){
		int pageNum = ParamUtils.getPageNumParameters(request); 
		int pageSize = ParamUtils.getPageSizeParameters(request); 
		ModelAndView model=new ModelAndView("fileupload/senior_develop");
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", "1");
		map.put("pid", "1");
		PageInfo<BaiseeClassType> info = baiseeClassTypeService.getClassTypeList(map,pageNum, pageSize);
		if (info.getTotal() == 0) {
			model.addObject("operationSuccess", "暂无数据");
		}
		model.addObject("pageResult", info);
		return model;
	}*/
	
	
	/**
	 * 查看高中开发部的老师
	 * @param request
	 * @param multipartFile
	 * @return
	 */
	@RequestMapping("/toDevelopFile.ht")
	@Role(value="WJGL_GZKFWJ")
	public ModelAndView seeSftpVideo(HttpServletRequest request){
		Vector<LsEntry> lsEntrys = baiseeClassTypeService.findCatalog("highDevelop");
		List<FileVO> fileVos = null;
		if (lsEntrys != null && lsEntrys.size() > 0) {
			fileVos = new ArrayList<FileVO>();
			FileVO fileVo = null;
			for (LsEntry ls : lsEntrys) {
				 if (!ls.getFilename().equals(".")&&!ls.getFilename().equals("..")){
					 fileVo = new FileVO();
					 fileVo.setName(ls.getFilename());
					 fileVos.add(fileVo);
				 }
			}
		}
		ModelAndView model=new ModelAndView("fileupload/senior_develop");
		if (fileVos == null) {
			model.addObject("operationSuccess", "暂无数据");
		}
		model.addObject("fileVos", fileVos);
		return model;
	}
	
	/**
	 * 跳转到高中部测试页面
	 * @return
	 */
	@RequestMapping("/toTestFile.ht")
	@Role(value="WJGL_GZCSWJ")
	public ModelAndView toTestList(HttpServletRequest request){
		/*int pageNum = ParamUtils.getPageNumParameters(request); 
		int pageSize = ParamUtils.getPageSizeParameters(request); 
		ModelAndView model=new ModelAndView("fileupload/senior_test");
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", "1");
		map.put("pid", "2");
		PageInfo<BaiseeClassType> info = baiseeClassTypeService.getClassTypeList(map,pageNum, pageSize);
		if (info.getTotal() == 0) {
			model.addObject("operationSuccess", "暂无数据");
		}
		model.addObject("pageResult", info);
		return model;*/
		Vector<LsEntry> lsEntrys = baiseeClassTypeService.findCatalog("highTest");
		List<FileVO> fileVos = null;
		if (lsEntrys != null && lsEntrys.size() > 0) {
			fileVos = new ArrayList<FileVO>();
			FileVO fileVo = null;
			for (LsEntry ls : lsEntrys) {
				 if (!ls.getFilename().equals(".")&&!ls.getFilename().equals("..")){
					 fileVo = new FileVO();
					 fileVo.setName(ls.getFilename());
					 fileVos.add(fileVo);
				 }
			}
		}
		ModelAndView model=new ModelAndView("fileupload/senior_test");
		if (fileVos == null) {
			model.addObject("operationSuccess", "暂无数据");
		}
		model.addObject("fileVos", fileVos);
		return model;
	}
	
	/**
	 * 跳转到初中部文化页面
	 * @return
	 */
	@RequestMapping("/toLiteracyFile.ht")
	@Role(value="WJGL_CZWHWJ")
	public ModelAndView toLiteracyList(HttpServletRequest request){
		/*int pageNum = ParamUtils.getPageNumParameters(request); 
		int pageSize = ParamUtils.getPageSizeParameters(request); 
		ModelAndView model=new ModelAndView("fileupload/junior_literacy");
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", "0");
		map.put("pid", "12");*/
		
		Vector<LsEntry> lsEntrys = baiseeClassTypeService.findCatalog("firstCulture");
		List<FileVO> fileVos = null;
		if (lsEntrys != null && lsEntrys.size() > 0) {
			fileVos = new ArrayList<FileVO>();
			FileVO fileVo = null;
			for (LsEntry ls : lsEntrys) {
				 if (!ls.getFilename().equals(".")&&!ls.getFilename().equals("..")){
					 fileVo = new FileVO();
					 fileVo.setName(ls.getFilename());
					 fileVos.add(fileVo);
				 }
			}
		}
		ModelAndView model=new ModelAndView("fileupload/junior_literacy");
		if (fileVos == null) {
			model.addObject("operationSuccess", "暂无数据");
		}
		model.addObject("fileVos", fileVos);
		return model;
	}
	
	/**
	 * 跳转到初中部技术页面
	 * @return
	 */
	@RequestMapping("/toTechnicalFile.ht")
	@Role(value="WJGL_CZJSWJ")
	public ModelAndView toTechnicalList(HttpServletRequest request){
		/*int pageNum = ParamUtils.getPageNumParameters(request); 
		int pageSize = ParamUtils.getPageSizeParameters(request); 
		ModelAndView model=new ModelAndView("fileupload/junior_technical");
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", "0");
		map.put("pid", "13");
		PageInfo<BaiseeClassType> info = baiseeClassTypeService.getClassTypeList(map,pageNum, pageSize);
		if (info.getTotal() == 0) {
			model.addObject("operationSuccess", "暂无数据");
		}
		model.addObject("pageResult", info);
		return model;*/
		Vector<LsEntry> lsEntrys = baiseeClassTypeService.findCatalog("firstTechnology");
		List<FileVO> fileVos = null;
		if (lsEntrys != null && lsEntrys.size() > 0) {
			fileVos = new ArrayList<FileVO>();
			FileVO fileVo = null;
			for (LsEntry ls : lsEntrys) {
				 if (!ls.getFilename().equals(".")&&!ls.getFilename().equals("..")){
					 fileVo = new FileVO();
					 fileVo.setName(ls.getFilename());
					 fileVos.add(fileVo);
				 }
			}
		}
		ModelAndView model=new ModelAndView("fileupload/junior_technical");
		if (fileVos == null) {
			model.addObject("operationSuccess", "暂无数据");
		}
		model.addObject("fileVos", fileVos);
		return model;
	}
	
	/**
	 * 查询该科目视频详情
	 * @param userID
	 * @param type
	 * @return
	 */
	@RequestMapping("/showVideoDetails.ht")
	@Login(ignore=true)
	@Role(ignore=true)
	@ResponseBody
	public ResponseResult<List<BaiseeVideoDetails>> showVideoDetails(String path){	
		ResponseResult<List<BaiseeVideoDetails>> rr=new ResponseResult<List<BaiseeVideoDetails>>(1, "成功");		
		List<BaiseeVideoDetails> list =  baiseeClassTypeService.findCatalogVideo(path);
		rr.setData(list);
		return rr;
	}
	
	/**
	 * 上传视频
	 * @param request
	 * @param multipartFile
	 * @return
	 */
	
	@RequestMapping("/fileUpload")
	@Login(ignore=true)
	@Role(ignore=true)
	@ResponseBody
	public Boolean fileByupload(HttpServletRequest request,MultipartFile multipartFile,@RequestParam(value="grade",defaultValue="1")String grade) {
		
		String userId = (String)request.getSession().getAttribute(AppConstants.SESSION_USER_ID);
		String typeId = request.getParameter("typeID");
		String videName = request.getParameter("videName");
		Boolean isupload = baiseeClassTypeService.fileByUpload(multipartFile,typeId,videName,userId,grade);
		return isupload;
	}
	
	
	/**
	 * 查询该科目视频详情
	 * @param userID
	 * @param type
	 * @return
	 */
	@RequestMapping("/findByName.ht")
	@Login(ignore=true)
	@Role(ignore=true)
	@ResponseBody
	public List<FileVO> findByName(String name){
		String[] strs = name.split("/");
		Vector<LsEntry> lsEntrys = baiseeClassTypeService.findCatalog(strs[1], strs[0]);
		List<FileVO> fileVos = null;
		if (lsEntrys != null && lsEntrys.size() > 0) {
			fileVos = new ArrayList<FileVO>();
			FileVO fileVo = null;
			for (LsEntry ls : lsEntrys) {
				 if (!ls.getFilename().equals(".")&&!ls.getFilename().equals("..")){
					 fileVo = new FileVO();
					 fileVo.setName(strs[0]);
					 fileVo.setSubject(ls.getFilename());
					 fileVos.add(fileVo);
				 }
			}
		}
		return fileVos;
	}
	/**
	 * 视频播放页面
	 * @return
	 */
	@RequestMapping("/play")
	@Login(ignore=true)
	@Role(ignore=true)
	public ModelAndView toAddRepair(HttpServletRequest request, String url){
		
		ModelAndView mav=new ModelAndView("fileupload/play_m3u8");
		mav.addObject("url",url);
		return mav;
	}
	
}
