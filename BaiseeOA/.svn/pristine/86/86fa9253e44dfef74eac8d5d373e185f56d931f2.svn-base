package cn.baisee.oa.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import cn.baisee.oa.annotation.Login;
import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.constants.AppConstants;
import cn.baisee.oa.constants.ExceptionConstants;
import cn.baisee.oa.core.context.FileConnParamLoadHelper;
import cn.baisee.oa.core.file.FileUploadUtil;
import cn.baisee.oa.core.file.SSHClientUtil;
import cn.baisee.oa.core.file.SSHParamModel;
import cn.baisee.oa.exception.OAServiceException;
import cn.baisee.oa.model.ReturnInfo;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;

@Controller
public class FileUploadController extends BaseController{
	private static final Log logger = LogFactory.getLog(FileUploadController.class);

	/**
	 * 文件上传预览
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value="/fileUpload")
	@Role(ignore = true)
	@Login(ignore = true)
	public Object fileUpload(HttpServletRequest req, HttpServletResponse resp) throws OAServiceException {
		ReturnInfo rtInfo = getReturnInfo();
		DefaultMultipartHttpServletRequest dmrq = null;
		if (req instanceof DefaultMultipartHttpServletRequest) {
			dmrq = (DefaultMultipartHttpServletRequest) req;
		} else {
			throw new OAServiceException(ExceptionConstants.METHOD_EXECUTE);
		}
		
		StringBuffer realPath = new StringBuffer(FileUploadUtil.getOSPath()).append("/"); //根据系统获取路径
		File dir = new File(realPath.toString());
		if(!dir.exists()) {//判断路径是否存在， 不存在则创建
			dir.mkdirs();
		}
		Iterator<String> it = dmrq.getFileNames();// 文件名集合
		List<String> successFile = new ArrayList<String>();
		List<String> errorFile = new ArrayList<String>();
		while (it.hasNext()) {
			String fileName = it.next();
			MultipartFile mf = dmrq.getFile(fileName);// 获取文件
			if (mf == null)
				continue;
			String fileSuffix = "";//文件后缀
			StringBuffer fileUrl = new StringBuffer();//文件相对路径
			fileUrl.append(UUID.randomUUID().toString().replaceAll("-", ""));// 生成临时文件名
			if (mf.getOriginalFilename().indexOf(".") > -1) {
				String[] temp = mf.getOriginalFilename().split("\\.");
				fileSuffix = temp[temp.length - 1];
				fileUrl.append(".").append(fileSuffix);
			}
			logger.debug("预览文件路径 ......" + fileUrl.toString());
			realPath.append(fileUrl);//临时文件物理路径  /mnt/fileupload/tempfile/文件名字.后缀
			logger.debug("预览文件 文件夹结构 ： ....................." + realPath);
			try {
				byte[] content = mf.getBytes();
				Session sshSession =  FileConnParamLoadHelper.getSshSession();
				ChannelSftp sftp = SSHClientUtil.openChannel(sshSession, "sftp");
				SSHClientUtil.upload(realPath.toString(), content, sftp);
			} catch (Exception e) {
				logger.error("上传失败", e);
				errorFile.add(fileName);
			}
			successFile.add("http://"+FileConnParamLoadHelper.getFILE_SERVER_IP()+"/" + "baiseefile/tempFile" + "/"+ fileUrl.toString());//将文件路径添加到
		}
		if (errorFile.isEmpty()) {
			rtInfo.setCode(AppConstants.SUCCESS_CODE);
			rtInfo.setResult(successFile);
		} else {
			rtInfo.setResult(successFile);
			rtInfo.setMessage("文件上传失败");
		}
		return rtInfo;
	}

	
	
	public static void main(String[] args) throws Exception {
		String ftpPost = "192.168.247.132";
		String ftpPort = "22";
		String ftpUsername = "root";
		String ftpPassword = "root";
		SSHParamModel model = new SSHParamModel();
		model.setServerType("sftp");
		model.setHost(ftpPost);
		model.setPort(Integer.valueOf(ftpPort));
		model.setUserName(ftpUsername);
		model.setPassword(ftpPassword);
		Session session = SSHClientUtil.getSshSession(model);
		ChannelSftp sftp = SSHClientUtil.openChannel(session, "sftp");
		 byte[] buffer = null;  
		 File file = new File("D:/pic.jpg");
		 FileInputStream fis = new FileInputStream(file);  
         ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
         byte[] b = new byte[1000];  
         int n;  
         while ((n = fis.read(b)) != -1) {  
             bos.write(b, 0, n);  
         }  
         fis.close();  
         bos.close();  
         buffer = bos.toByteArray();  
		
		SSHClientUtil.upload("/baiseefile/tempFile", buffer, sftp);
	}
	
	
}
