package cn.baisee.oa.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;

import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.constants.CacheKeyEnum;
import cn.baisee.oa.core.context.FileConnParamLoadHelper;
import cn.baisee.oa.core.file.FileDir;
import cn.baisee.oa.core.file.FileUploadUtil;
import cn.baisee.oa.core.file.SSHClientUtil;
import cn.baisee.oa.exception.OAServiceException;
import cn.baisee.oa.model.BaiseeSystemParam;
import cn.baisee.oa.model.IcipBusDict;
import cn.baisee.oa.model.ReturnInfo;
import cn.baisee.oa.model.student.BaiseeStudent;
import cn.baisee.oa.model.student.BaiseeStudentFile;
import cn.baisee.oa.model.student.BaiseeStudentRecord;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeFileService;
import cn.baisee.oa.service.DictionariesService;
import cn.baisee.oa.service.StudentService;
import cn.baisee.oa.utils.ParamUtils;
import cn.baisee.oa.utils.ParameterRequestWrapper;
import cn.baisee.oa.utils.RedisUtils;
import cn.baisee.oa.utils.SessionUtil;
import cn.baisee.oa.utils.poi.WordUtils;

@Controller
@RequestMapping("/file")
public class BaiseeFileController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private BaiseeFileService fileService;
	@Autowired
	private DictionariesService dictionariesService;

	@ResponseBody
	@RequestMapping(value = "/delet")
	@Role("XSDAGL_SC")
	public Integer delet(HttpServletRequest request) throws Exception {
		String fileList = ParamUtils.getParameter(request, "fileList");
		String[] files = fileList.split(",");
		for (String str : files) {
			String file = FileUploadUtil.createFileDir(FileDir.F_PICTURE_DIR, str.replace("/file/", ""));
			try{
				FileUploadUtil.DeleteTempFile(file);
			}catch(Exception e) {
				System.out.println("未找到文件");
				continue;
			}
		}
		return fileService.deletFile(request);
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/download")
	@Role("XSDAGL_DL")
	public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String fileList = ParamUtils.getParameter(request, "fileList");
		String fileType = ParamUtils.getParameter(request, "fileType");
		String sname = ParamUtils.getParameter(request, "sname");
		BaiseeSystemParam param = (BaiseeSystemParam) RedisUtils.utils.mget(CacheKeyEnum.FTP_SERVER_ITEM.getCacheKey(),
				"FILE_SERVER_IP");
		String[] files = fileList.split(",");
		if(files.length==1) {
			String path ="";
			if(param==null) {
				 path = "http://file.china-bs.com/baiseefile"+ fileList;
			}else {
				 path = "http://" + param.getParamValue() + "/baiseefile"+ fileList;
			}
			//path = "http://" + param.getParamValue() + "/baiseefile"+ fileList;
			File f = new File(fileList);
			byte[] buffer = WordUtils.inputStream2ByteArray2(path);
			FileUploadUtil.writeFile(buffer, response,f.getName());
		}else {
			List<InputStream> ss = new ArrayList<InputStream>();
			for (String str : files) {
				String path ="";
				if(param==null) {
					 path = "http://file.china-bs.com/baiseefile"+ str;
				}else {
					path = "http://" + param.getParamValue() + "/baiseefile"+ str;
				}
				//String path = "http://" + param.getParamValue() + "/baiseefile"+ str;
				URL url = new URL(path);
				ss.add(url.openStream());
			}
			String zipName = WordUtils.doZIP(fileType+"_"+sname+".zip",ss);
			InputStream inputStreamzip = new FileInputStream(new File(zipName));
			byte[] buffer = WordUtils.input2byte(inputStreamzip);
			FileUploadUtil.writeFile(buffer, response, fileType+"_"+sname+".zip");
		}
		
	}

	@RequestMapping("/insertfile")
	@Role("XSDAGL_DL")
	@ResponseBody
	public Integer insertfile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BaiseeSystemParam param = (BaiseeSystemParam) RedisUtils.utils.mget(CacheKeyEnum.FTP_SERVER_ITEM.getCacheKey(),
				"FILE_SERVER_IP");
		String fileList = ParamUtils.getParameter(request, "fileList");
		String fileType = ParamUtils.getParameter(request, "fileType");
		String sid = ParamUtils.getParameter(request, "stuId");
		String tampurl ="";
		if(param==null) {
			tampurl = "http://file.china-bs.com/" + "baiseefile/tempFile" + "/";
		}else {
			tampurl = "http://" + param.getParamValue() + "/" + "baiseefile/tempFile" + "/";
		}
		//String tampurl = "http://" + param.getParamValue() + "/" + "baiseefile/tempFile" + "/";
		String[] files = fileList.split(",");
		String file2 = "";
		for (String str : files) {
			String url = tampurl + str;
			String filePath = fileType + "/" + UUID.randomUUID().toString().replaceAll("-", "") + "."
					+ FileUploadUtil.getFileExt(new File(str));
			String nurl = FileUploadUtil.createFileDir(FileDir.F_PICTURE_DIR, filePath);
			byte[] content = WordUtils.inputStream2ByteArray2(url);
			file2 += FileUploadUtil.uploadFile(content, nurl, str) + ',';
		}
		ParameterRequestWrapper requestWrapper = new ParameterRequestWrapper((HttpServletRequest) request);
		requestWrapper.addParameter("fileList", file2.substring(0, file2.lastIndexOf(",")));
		requestWrapper.addParameter("fileType", fileType);
		requestWrapper.addParameter("stuId", sid);
		return fileService.insertFile(requestWrapper);
	}

	@RequestMapping("/upload")
	@Role("XSDAGL_SC")
	@ResponseBody // 批量导入照片
	public ReturnInfo importImg(HttpServletRequest request) throws Exception {
		ReturnInfo rInfo = new ReturnInfo();
		StringBuffer realPath = new StringBuffer(FileUploadUtil.getOSPath()).append("/"); // 根据系统获取路径
		File dir = new File(realPath.toString());
		if (!dir.exists()) {// 判断路径是否存在， 不存在则创建
			dir.mkdirs();
		}
		String fileSuffix = "";// 文件后缀
		StringBuffer fileUrl = new StringBuffer();// 文件相对路径
		List<String> successFile = new ArrayList<String>();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("file");
		fileUrl.append(UUID.randomUUID().toString().replaceAll("-", ""));// 生成临时文件名
		if (file.getOriginalFilename().indexOf(".") > -1) {
			String[] temp = file.getOriginalFilename().split("\\.");
			fileSuffix = temp[temp.length - 1];
			fileUrl.append(".").append(fileSuffix);
		}
		realPath.append(fileUrl);// 临时文件物理路径
		try {
			byte[] content = file.getBytes();
			Session sshSession = FileConnParamLoadHelper.getSshSession();
			ChannelSftp sftp = SSHClientUtil.openChannel(sshSession, "sftp");
			SSHClientUtil.upload(realPath.toString(), content, sftp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		successFile.add(fileUrl.toString());// 将文件路径添加到
		rInfo.setResult(successFile);
		return rInfo;
	}

	/**
	 * 文件列表页
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws OAServiceException
	 */
	@RequestMapping("/view")
	@Role("XSDAGL_DL")
	public ModelAndView showView(HttpServletRequest request, HttpServletResponse response) throws OAServiceException {
		System.out.println("++++++++++++++++++++++++++++++++++");
		String stuId = ParamUtils.getParameter(request, "stuId");
		String stuStatus = ParamUtils.getParameter(request, "stuStatus");
		PageInfo<BaiseeStudentFile> pageInfo = fileService.selectFile(request);
		ModelAndView mv = new ModelAndView("/file/stu_file");
		if (pageInfo.getTotal() == 0) {
			mv.addObject("operationSuccess", "暂无数据");
		}
		mv.addObject("pageResult", pageInfo);

		mv.addObject("stuId", stuId);
		mv.addObject("stuStatus", stuStatus);
		System.out.println("+++++++++++++++++++++++++++++++++++");
		return mv;
	}

	@RequestMapping("/selFile")
	@Role("XSDAGL_SC")
	public ModelAndView showUpload(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/file/sel_file");
		String stuId = ParamUtils.getParameter(request, "stuId");
		List<IcipBusDict> dicts = dictionariesService.selectIcipBusDictByDictName("文档类型");
		mv.addObject("dicts", dicts);
		mv.addObject("stuId", stuId);
		return mv;
	}

	/**
	 * 档案查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toStuInfoList")
	@Role("XSDAGL_ZS")
	public ModelAndView toStuInfoList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer pageNum = ParamUtils.getPageNumParameters(request);
		Integer pageSize = ParamUtils.getPageSizeParameters(request);
		String itemlableSearch = "";
		String status = "";
		String startTime = "";
		String endTime = "";
		String returnFeeStatus = "";
		String str = ParamUtils.getParameter(request, "itemlableSearch");
		String sta = ParamUtils.getParameter(request, "status");
		String stuStartTime = ParamUtils.getParameter(request, "stuStartTime");
		String stuEndTime = ParamUtils.getParameter(request, "stuEndTime");
		String feeStatus = ParamUtils.getParameter(request, "returnFeeStatus");
		String claStatus = ParamUtils.getParameter(request, "claStatus");
		String areas = ParamUtils.getParameter(request, "areas");
		String choiceStuState = ParamUtils.getParameter(request, "choiceStuState"); /* 学生状态 */
		if (str != null) {
			itemlableSearch = str;
		}
		if (sta != null) {
			status = sta;
		}
		if (stuStartTime != null) {
			startTime = stuStartTime.replace("-", "");
		}
		if (stuEndTime != null) {
			endTime = stuEndTime.replace("-", "");
		}
		if (feeStatus != null) {
			returnFeeStatus = feeStatus;
		}

		PageInfo<BaiseeStudent> pageInfo = studentService.getForList(pageNum, pageSize, itemlableSearch, status,
				stuStartTime, stuEndTime, returnFeeStatus, request, claStatus, areas, choiceStuState);

		ModelAndView mv = new ModelAndView("file/stu_InfoList");
		if (pageInfo.getTotal() == 0) {
			mv.addObject("operationSuccess", "暂无数据");
		}
		mv.addObject("areas", areas);
		mv.addObject("pageResult", pageInfo);
		mv.addObject("itemlableSearch", itemlableSearch);
		mv.addObject("status", status);
		mv.addObject("stuStartTime", startTime);
		mv.addObject("stuEndTime", endTime);
		mv.addObject("returnStatus", returnFeeStatus);
		mv.addObject("claStatus", claStatus);
		mv.addObject("userType", SessionUtil.getUserInfo(request).getUserType());
		// int i = Integer.parseInt(choiceStuState)
		// ;Integer.valueOf(choiceStuState).intValue()
		/*
		 * if(choiceStuState == null || "".equals(choiceStuState)) { choiceStuState =
		 * null; }
		 */
		if (choiceStuState == null || "".equals(choiceStuState)) {
			choiceStuState = "1";
		}
		mv.addObject("choiceStuState", choiceStuState);
		return mv;
	}

	/**
	 * 下载学生档案
	 * 
	 * @param request
	 */
	@ResponseBody
	@RequestMapping(value = "/studentword")
	@Role("XSDAGL_DL")
	public void studentWord(HttpServletRequest request, HttpServletResponse response) {
		String genpath = request.getSession().getServletContext().getRealPath("/");
		BaiseeSystemParam param = (BaiseeSystemParam) RedisUtils.utils.mget(CacheKeyEnum.FTP_SERVER_ITEM.getCacheKey(),
				"FILE_SERVER_IP");
		Map<String, Object> params = new HashMap<String, Object>();
		WordUtils wordUtil = new WordUtils();
		String id = ParamUtils.getParameter(request, "stuId");
		BaiseeStudentRecord record = studentService.selectStuRecord(id);
		try {
			Class clazz = Class.forName("cn.baisee.oa.model.student.BaiseeStudentRecord");
			Field[] fields = clazz.getDeclaredFields();
			for (Field f : fields) {
				Object value = null;
				String fname = f.getName();
				if ("photo".equals(fname)) {
					continue;
				}
				String method = "get" + fname.substring(0, 1).toUpperCase() + fname.substring(1, fname.length());
				value = clazz.getMethod(method).invoke(record);
				if (value == null) {
					value = "";
				}
				params.put("${" + fname + "}", String.valueOf(value));
			}
			// Map<String, Object> header1 = new HashMap<String, Object>();
			// header1.put("width", 100);
			// header1.put("height", 150);
			// header1.put("type", "jpg");
			// header1.put("content", WordUtils.inputStream2ByteArray(new
			// FileInputStream("D:\\bsjy.png"), true));
			// params.put("${stamper}", header1);
			if (record.getPhoto() != null) {
				Map<String, Object> header2 = new HashMap<String, Object>();
				String path ="";
				if(param==null) {
					path = "http://file.china-bs.com/baiseefile" + record.getPhoto();
				}else {
					path = "http://" + param.getParamValue() + "/baiseefile" + record.getPhoto();
				}
				//String path = "http://" + param.getParamValue() + "/baiseefile" + record.getPhoto();
				header2.put("width", 100);
				header2.put("height", 150);
				header2.put("type", "png");
				header2.put("content", WordUtils.inputStream2ByteArray2(path));
				params.put("${photo}", header2);
			}
			String path = genpath + "/doc/stuinfo.docx"; // 模板位置
			String fileName = new String("入学档案信息.docx".getBytes("UTF-8"), "iso-8859-1");
			List<String[]> testList = new ArrayList<String[]>();
			wordUtil.getWord(path, params, testList, fileName, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 下载学生在校表现评定表
	 * 
	 * @param request
	 */
	@ResponseBody
	@RequestMapping(value = "/studentAssess")
	@Role("XSDAGL_DL")
	public void studentAessess(HttpServletRequest request, HttpServletResponse response) {
		String genpath = request.getSession().getServletContext().getRealPath("/");
		Map<String, Object> params = new HashMap<String, Object>();
		WordUtils wordUtil = new WordUtils();
		String stuId = ParamUtils.getParameter(request, "stuId");
		BaiseeStudentRecord record = studentService.selectStuAssess(stuId);
		try {
			Class clazz = Class.forName("cn.baisee.oa.model.student.BaiseeStudentRecord");
			Field[] fields = clazz.getDeclaredFields();
			for (Field f : fields) {
				Object value = null;
				String fname = f.getName();
				String method = "get" + fname.substring(0, 1).toUpperCase() + fname.substring(1, fname.length());
				// System.out.println( f.getType());
				// System.err.println(method);
				value = clazz.getMethod(method).invoke(record);
				if (value == null) {
					value = "";
				}
				params.put("${" + fname + "}", String.valueOf(value));
			}

			String path = genpath + "/doc/stuassess.docx"; // 模板位置
			String fileName = new String("评分.docx".getBytes("UTF-8"), "iso-8859-1");
			List<String[]> testList = new ArrayList<String[]>();
			wordUtil.getWord(path, params, testList, fileName, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 下载学生退学申请
	 * 
	 * @param request
	 */
	@ResponseBody
	@RequestMapping(value = "/exportApply")
	@Role("XSDAGL_DL")
	public void studentApply(HttpServletRequest request, HttpServletResponse response) {
		BaiseeSystemParam param = (BaiseeSystemParam) RedisUtils.utils.mget(CacheKeyEnum.FTP_SERVER_ITEM.getCacheKey(),
				"FILE_SERVER_IP");
		String genpath = request.getSession().getServletContext().getRealPath("/");
		Map<String, Object> params = new HashMap<String, Object>();
		WordUtils wordUtil = new WordUtils();
		String stuId = ParamUtils.getParameter(request, "stuId");
		/* no： 档案编号 */
		String no = ParamUtils.getParameter(request, "no");
		BaiseeStudentRecord record = studentService.selectStuRecord(stuId);
		try {
			Class clazz = Class.forName("cn.baisee.oa.model.student.BaiseeStudentRecord");
			Field[] fields = clazz.getDeclaredFields();
			for (Field f : fields) {
				Object value = null;
				String fname = f.getName();
				String method = "get" + fname.substring(0, 1).toUpperCase() + fname.substring(1, fname.length());
				// System.out.println( f.getType());
				// System.err.println(method);
				value = clazz.getMethod(method).invoke(record);
				if ("photo".equals(fname)) {
					continue;
				}
				if (value == null) {
					value = "";
				}
				params.put("${" + fname + "}", String.valueOf(value));
			}
			if (record.getPhoto() != null) {
				Map<String, Object> header2 = new HashMap<String, Object>();
				String path ="";
				if(param==null) {
					path = "http://file.china-bs.com/baiseefile" + record.getPhoto();
				}else {
					path = "http://" + param.getParamValue() + "/baiseefile" + record.getPhoto();
				}
				//String path = "http://" + param.getParamValue() + "/baiseefile" + record.getPhoto();
				header2.put("width", 100);
				header2.put("height", 150);
				header2.put("type", "png");
				header2.put("content", WordUtils.inputStream2ByteArray2(path));
				params.put("${photo}", header2);
			}
			String path = ""; // 档案路径
			if ("1".equals(no)) {
				path = genpath + "/doc/stuapply1.docx"; // 模板位置
			} else if ("2".equals(no)) {
				path = genpath + "/doc/stuapply2.docx"; // 模板位置
			} else if ("3".equals(no)) {
				path = genpath + "/doc/stuapply3.docx"; // 模板位置s
			}
			String fileName = new String("申请.docx".getBytes("UTF-8"), "iso-8859-1");
			List<String[]> testList = new ArrayList<String[]>();
			wordUtil.getWord(path, params, testList, fileName, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 导出学生的就业保障书
	 */
	@ResponseBody
	@RequestMapping(value = "/studentAgreement")
	@Role("XSDAGL_DL")
	public void studentAgreement(HttpServletRequest request, HttpServletResponse response) {
		String genpath = request.getSession().getServletContext().getRealPath("/");
		WordUtils wordUtil = new WordUtils();
		Map<String, Object> params = new HashMap<String, Object>();
		String stuId = ParamUtils.getParameter(request, "stuId");
		BaiseeStudentRecord record = studentService.selectStuRecord(stuId);
		try {
			Class clazz = Class.forName("cn.baisee.oa.model.student.BaiseeStudentRecord");
			Field[] fields = clazz.getDeclaredFields();
			for (Field f : fields) {
				Object value = null;
				String fname = f.getName();
				String method = "get" + fname.substring(0, 1).toUpperCase() + fname.substring(1, fname.length());
				value = clazz.getMethod(method).invoke(record);
				if (value == null) {
					value = "";
				}
				params.put("${" + fname + "}", String.valueOf(value));
			}
			String path = ""; // 档案路径
			path = genpath + "/doc/stuAgreement.docx"; // 模板位置
			String fileName = new String("就业保障协议书.docx".getBytes("UTF-8"), "iso-8859-1");
			List<String[]> testList = new ArrayList<String[]>();
			wordUtil.getWord(path, params, testList, fileName, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
