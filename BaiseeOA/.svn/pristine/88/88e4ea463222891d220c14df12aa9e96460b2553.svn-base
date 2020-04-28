package cn.baisee.oa.core.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.IOUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import cn.baisee.oa.constants.ExceptionConstants;
import cn.baisee.oa.core.context.FileConnParamLoadHelper;
import cn.baisee.oa.exception.OAServiceException;
import cn.baisee.oa.utils.ParamUtils;
import cn.baisee.oa.utils.StringUtil;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;

/**
 * 文件上传工具类
 * @author Administrator
 *
 */
public class FileUploadUtil {
	/**
	 * 上传单文件
	 * 
	 * @param file
	 *            待上传文件
	 * @param url
	 *            原文件地址 为空则重新生成
	 * @param fd
	 *            文件路劲生成规则
	 * @param refId
	 *            文件名称（查看FileDir中规则）
	 * @return 文件地址
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String uploadFile(File file, String url, FileDir fd,
			String refId) throws FileNotFoundException, IOException {
		if (file == null)
			return null;

		if (StringUtil.isEmpty(url)) {
			url = createFileDir(fd, refId);
			url += "." + getFileExt(file);
		}
		Session sshSession =  FileConnParamLoadHelper.getSshSession();
	    ChannelSftp sftp = SSHClientUtil.openChannel(sshSession, "sftp");
	    String path = file.getPath().replace("\\", "/");
		byte[] b =  
		SSHClientUtil.download(path, sftp, false);
		String str = uploadFile(b, url);
		if (!StringUtil.isEmpty(str)) {
			SSHClientUtil.delete(path, sftp, true);
			return str;
		}
		return null;
	}
	/**
	 * 上传并删除临时文件
	 */
	public static String uploadFile(byte[] b, String url,String path)
			throws FileNotFoundException, IOException {
		Session sshSession =  FileConnParamLoadHelper.getSshSession();
		ChannelSftp sftp = SSHClientUtil.openChannel(sshSession, "sftp");
		path = "/mnt/fileupload/tempFile/"+path;
		url = uploadFile(b, url);
		if (!StringUtil.isEmpty(url)) {    //             /mnt/fileupload/tempFile/dc848ca04581437d9b76295f79c462c6.png
			SSHClientUtil.delete(path, sftp, true);    // /mnt/fileupload/tempFile/25648c73ae9e4c5d87f159f66b90a57a.png
		}
		return url.replace("/mnt/fileupload", "");
	}
	
	
	/**
	 * 上传单文件
	 * 
	 * @param file
	 *            待上传文件
	 * @param url
	 *            原文件地址 为空则重新生成
	 * @param fd
	 *            文件路劲生成规则
	 * @param refId
	 *            文件名称（查看FileDir中规则）
	 * @return 文件地址
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String uploadFileNoDelete(File file, String url, FileDir fd,
			String refId) throws FileNotFoundException, IOException {
		if (file == null)
			return null;

		if (StringUtil.isEmpty(url)) {
			url = createFileDir(fd, refId);
			url += "." + getFileExt(file);
		}
		Session sshSession =  FileConnParamLoadHelper.getSshSession();
	    ChannelSftp sftp = SSHClientUtil.openChannel(sshSession, "sftp");
	    String path = file.getPath().replace("\\", "/");
		byte[] b =  
		SSHClientUtil.download(path, sftp, false);
		String str = uploadFile(b, url);
		if (!StringUtil.isEmpty(str)) {
//			SSHClientUtil.delete(path, sftp, true);
			return str;
		}
		return null;
	}

	
	
	/**
	 * 
	 * @param path  临时文件目录
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void DeleteTempFile(String path) throws FileNotFoundException, IOException {
		Session sshSession =  FileConnParamLoadHelper.getSshSession();
	    ChannelSftp sftp = SSHClientUtil.openChannel(sshSession, "sftp");
	    SSHClientUtil.delete(path, sftp, true);
	}
	
	
	
	
	
	

	/**
	 * 上传单文件
	 * 
	 * @param file
	 *            待上传文件
	 * @param url
	 *            原文件地址 为空则重新生成
	 * @param fd
	 *            文件路劲生成规则
	 * @param refId
	 *            文件名称（查看FileDir中规则）
	 * @return 文件地址
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String uploadFile(MultipartFile file, String url, FileDir fd,
			String refId) throws FileNotFoundException, IOException {
		if (file == null)
			return null;

		if (StringUtil.isEmpty(url)) {
			url = createFileDir(fd, refId);
			url += "." + getFileExt(file.getOriginalFilename());
		}

		return uploadFile(file.getBytes(), url);
	}

	/**
	 * 上传单文件 访问路径 /mnt/icipFile_sit/
	 * 
	 * @param content
	 * @param url
	 * @param fd
	 * @param refId
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String uploadFile(byte[] content, String url)
			throws FileNotFoundException, IOException {
		Session sshSession =  FileConnParamLoadHelper.getSshSession();
		ChannelSftp sftp = SSHClientUtil.openChannel(sshSession, "sftp");
		SSHClientUtil.upload(url, content, sftp);
		return url;
	}

	/**
	 * 上传文件 访问路径 /mnt/local
	 * 
	 * @param content
	 * @param url
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	 

	public static String deleteFile(byte[] content, String url)
			throws FileNotFoundException, IOException {
		return null;
	}

	/**
	 * 上传单文件
	 * 
	 * @param file
	 *            待上传文件
	 * @param url
	 *            原文件地址 为空则重新生成
	 * @param fd
	 *            文件路劲生成规则
	 * @param refId
	 *            文件名称（查看FileDir中规则）
	 * @return 文件地址
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String uploadFileByRootMNT(File file, String url, FileDir fd,
			String refId) throws FileNotFoundException, IOException {
		 
		return null;
	}

	public static String deleteFile(File file, String url, FileDir fd,
			String refId) throws FileNotFoundException, IOException {
		if (file == null)
			return null;

		if (StringUtil.isEmpty(url)) {
			url = createFileDir(fd, refId);  // mnt/fileupload/emp/pcid1029384
			url += "." + getFileExt(file);// mnt/fileupload/emp/pcid1029384.jpg
		}

		byte[] b = IOUtils.toByteArray(new FileInputStream(file));
		String str = deleteFile(b, url);
		if (!StringUtil.isEmpty(str)) {
			file.deleteOnExit();// 删除临时文件
			return str;
		}
		return null;
	}

	/**
	 * 获取本地临时文件
	 * 
	 * @param req
	 * @param paramName
	 * @return
	 * @throws IOException
	 */
	public static File getSingleFile(HttpServletRequest req, String paramName)
			throws IOException {
		String fileName = ParamUtils.getParameter(req, paramName);
		if (StringUtil.isEmpty(fileName))
			return null;
		if (fileName.indexOf("/") > -1) {
			String[] array = fileName.split("/");
			fileName = array[array.length - 1];
		}
		StringBuffer realPath = new StringBuffer(getOSPath()).append(
				"/").append(fileName);
		File file = new File(realPath.toString());
		
		Session sshSession =  FileConnParamLoadHelper.getSshSession();
	    ChannelSftp sftp = SSHClientUtil.openChannel(sshSession, "sftp");
		Boolean flag =SSHClientUtil.isSftpFileExist(realPath.toString(), sftp);
		if (!flag)
			return null;
		return file;
	}

	
	
	public static File getSingleFile(HttpServletRequest req) throws IOException {
		return getSingleFile(req, "tempFileName");
	}

	/**
	 * 获取临时文件组
	 * 
	 * @param req
	 * @param paramName
	 * @return
	 * @throws IOException
	 */
	public static List<File> getFiles(HttpServletRequest req, String paramName)
			throws IOException {
		String[] filesName = ParamUtils.getParameters(req, paramName);
		List<File> files = new ArrayList<File>();
		if (filesName != null) {
			for (String fileName : filesName) {
				String realPath = req.getSession().getServletContext()
						.getRealPath(fileName);
				File file = new File(realPath);
				if (file.exists())
					files.add(file);
			}
		}
		return files;
	}

	public static List<File> getFiles(HttpServletRequest req)
			throws IOException {
		return getFiles(req, "tempFileName");
	}

	/**
	 * 文件目录声称规则
	 * 
	 * @param fd
	 * @param refId
	 * @return
	 */
	public static String createFileDir(FileDir fd, String refId) {
		StringBuffer dir = new StringBuffer();
		switch (fd) {
		case C_PICTURE_DIR:
			dir.append("/mnt/fileupload/").append("emp/").append(refId);
			break;
		case S_PICTURE_DIR:
			dir.append("/mnt/fileupload/").append("student/").append(refId);
			break;
		case F_PICTURE_DIR:
			dir.append("/mnt/fileupload/").append("file/").append(refId);
			break;
		case R_PICTURE_DIR:
			dir.append("/mnt/fileupload/").append("repair/").append(refId);
			break;
		}
		return dir.toString();
	}

	/**
	 * 下载文件处理
	 * 
	 * @param rtValue
	 * @param resp
	 * @param fileName
	 * @throws Exception
	 */
	public static void writeFile(byte[] rtValue, HttpServletResponse resp,
			String fileName) throws Exception {
		OutputStream os = null;
		BufferedOutputStream bos = null;
		resp.setContentType("application/x-msdownload;");
		resp.setHeader("Content-disposition", "attachment; filename="
				+ new String(fileName.getBytes("utf-8"), "ISO8859-1"));
		resp.setHeader("Content-Length", String.valueOf(rtValue.length));
		os = resp.getOutputStream();
		bos = new BufferedOutputStream(os);
		bos.write(rtValue);
		bos.close();
		os.close();
	}

	/**
	 * 获取上传文件
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws OAServiceException 
	 * @throws BMSServiceException
	 */
	public static List<MultipartFile> getFiles(HttpServletRequest req,
			HttpServletResponse resp) throws OAServiceException {
		List<MultipartFile> files = new ArrayList<MultipartFile>();
		DefaultMultipartHttpServletRequest dmrq = null;

		if (req instanceof DefaultMultipartHttpServletRequest) {
			dmrq = (DefaultMultipartHttpServletRequest) req;
		} else {
			throw new OAServiceException(ExceptionConstants.METHOD_EXECUTE);
		}

		Iterator<String> it = dmrq.getFileNames();// 文件名集合
		while (it.hasNext()) {
			String fileName = it.next();
			files.add(dmrq.getFile(fileName));// 获取单个文件
		}
		return files;
	}

	public static MultipartFile getFile(HttpServletRequest req,
			HttpServletResponse resp) {
		 
		return null;
	}

	/**
	 * 获取对应系统的临时文件路径
	 * 
	 * @return
	 */
	public static String getOSPath() {
		return   "/mnt/fileupload/tempFile";
	}

	/**
	 * 根据文件名获取扩展名称。
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileExt(String fileName) {
		if(StringUtils.isEmpty(fileName)){
			return "";
		}
		int pos = fileName.lastIndexOf(".");
		if (pos > -1) {
			return fileName.substring(pos + 1).toLowerCase();
		}
		return "";
	}

	/**
	 * 取得文件扩展名
	 * 
	 * @return
	 */
	public static String getFileExt(File file) {
		return getFileExt(file.getName());
	}
}
