package cn.baisee.oa.utils.fileupload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import cn.baisee.oa.model.fileupload.BaiseeClassVideo;
import cn.baisee.oa.utils.DateUtil;

//文件上传工具
public class FileUploadTool {

// 文件最大500M
	private static long upload_maxsize = 5000 * 1024 * 1024;
	
	 // 设置第一次登陆的时候提示，可选值：(ask | yes | no)
    private static final String SESSION_CONFIG_STRICT_HOST_KEY_CHECKING = "StrictHostKeyChecking";
	
// 文件允许格式
	private static String[] allowFiles = { ".rar", ".doc", ".docx", ".zip", ".pdf", ".txt", ".swf", ".xlsx", ".gif",
			".png", ".jpg", ".jpeg", ".bmp", ".xls", ".mp4", ".flv", ".ppt", ".avi", ".mpg", ".wmv", ".3gp", ".mov",
			".asf", ".asx", ".vob", ".wmv9", ".rm", ".rmvb", ".mkv" };
// 允许转码的视频格式（ffmpeg）
	private static String[] allowFLV = { ".avi", ".mpg", ".wmv", ".3gp", ".mov", ".asf", ".asx", ".vob" };
// 允许的视频转码格式(mencoder)
	private static String[] allowAVI = { ".wmv9", ".rm", ".rmvb" };
	
	public BaiseeClassVideo createFile(MultipartFile multipartFile) throws IOException {
		BaiseeClassVideo video = new BaiseeClassVideo();
		InputStream stream = multipartFile.getInputStream();
		boolean bflag = false;
		String fileName = multipartFile.getOriginalFilename().toString();
		// 判断文件不为空
		if (multipartFile.getSize() != 0 && !multipartFile.isEmpty()) {
			bflag = true;
			// 判断文件大小
			if (multipartFile.getSize() <= upload_maxsize) {
				bflag = true;
				// 文件类型判断
				if (this.checkFileType(fileName)) {
					bflag = true;
				} else {
					bflag = false;
				}
			} else {
				bflag = false;
			}
		} else {
			bflag = false;
		}
		if (bflag) {
			String name = fileName.substring(0, fileName.lastIndexOf("."));
			// 新的文件名
			String newFileName = this.getName(name);
			// 文件扩展名
			String fileEnd = this.getFileExt(fileName);
			// 绝对路径
			String fileDir = "video/" + newFileName + fileEnd;
			File filedirs = new File(fileDir);
			// 转入文件
			try {
				uploadFile(fileDir, stream);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 相对路径
			//entity.setType(fileEnd);
			// size存储为String
			String size = this.getSize(filedirs);
			// 源文件保存路径
			String aviPath = filedirs.getAbsolutePath();
			if (aviPath != null) {
				video.setUrl(fileDir);
				video.setId("VIDEO"+new Date().getTime());
				video.setUpdateTime(DateUtil.getReqDate());
				return video;
			}
		} else {
			return null;
		}
		return null;
		
	}

	/**
	 * 文件类型判断
	 *
	 * @param fileName
	 * @return
	 */
	private boolean checkFileType(String fileName) {
		Iterator<String> type = Arrays.asList(allowFiles).iterator();
		while (type.hasNext()) {
			String ext = type.next();
			if (fileName.toLowerCase().endsWith(ext)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 视频类型判断(flv)
	 *
	 * @param fileName
	 * @return
	 */
	private boolean checkMediaType(String fileEnd) {
		Iterator<String> type = Arrays.asList(allowFLV).iterator();
		while (type.hasNext()) {
			String ext = type.next();
			if (fileEnd.equals(ext)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 视频类型判断(AVI)
	 *
	 * @param fileName
	 * @return
	 */
	private boolean checkAVIType(String fileEnd) {
		Iterator<String> type = Arrays.asList(allowAVI).iterator();
		while (type.hasNext()) {
			String ext = type.next();
			if (fileEnd.equals(ext)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取文件扩展名
	 *
	 * @return string
	 */
	private String getFileExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * 依据原始文件名生成新文件名 UUID：全局唯一标识符，由一个十六位的数字组成,由三部分组成：当前日期和时间、时钟序列、全局唯一的IEEE机器识别号
	 * 
	 * @return string
	 */
	private String getName(String fileName) {
		Random random = new Random();
		return "" + random.nextInt(10000) + System.currentTimeMillis();
//return UUID.randomUUID().toString() + "_" + fileName;

	}

	/**
	 * 文件大小，返回kb.mb ?
	 * 
	 * @return
	 */
	private String getSize(File file) {
		String size = "";
		long fileLength = file.length();
		DecimalFormat df = new DecimalFormat("#.00");
		if (fileLength < 1024) {
			size = df.format((double) fileLength) + "BT";
		} else if (fileLength < 1048576) {
			size = df.format((double) fileLength / 1024) + "KB";
		} else if (fileLength < 1073741824) {
			size = df.format((double) fileLength / 1048576) + "MB";
		} else {
			size = df.format((double) fileLength / 1073741824) + "GB";
		}

		return size;

	}

	 /**
     * 创建SFTP连接
     * @return
     * @throws Exception
     */
    private ChannelSftp createSftp() throws Exception {
        JSch jsch = new JSch();
 
        Session session = createSession(jsch, "192.168.0.78", "mysftp", 22);
        session.setPassword("zhangchuang123");
        session.connect(15000);
 
        Channel channel = session.openChannel("sftp");
        channel.connect(15000);
  
        return (ChannelSftp) channel;
    }
	
    /**
     * 创建session
     * @param jsch
     * @param host
     * @param username
     * @param port
     * @return
     * @throws Exception
     */
    private Session createSession(JSch jsch, String host, String username, Integer port) throws Exception {
        Session session = null;
 
        if (port <= 0) {
            session = jsch.getSession(username, host);
        } else {
            session = jsch.getSession(username, host, port);
        }
        
        if (session == null) {
            throw new Exception(host + " session is null");
        }
 
        session.setConfig(SESSION_CONFIG_STRICT_HOST_KEY_CHECKING, "no");
        return session;
    }
    
    /**
     * 关闭连接
     * @param sftp
     */
    private void disconnect(ChannelSftp sftp) {
        try {
            if (sftp != null) {
                if (sftp.isConnected()) {
                    sftp.disconnect();
                } else if (sftp.isClosed()) {
                }
                if (null != sftp.getSession()) {
                    sftp.getSession().disconnect();
                }
            }
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }
    
    public boolean uploadFile(String targetPath, InputStream inputStream) throws Exception {
        ChannelSftp sftp = this.createSftp();
        try {
        	sftp.cd("/upload");
            int index = targetPath.lastIndexOf("/");
            String fileDir = targetPath.substring(0, index);
            String fileName = targetPath.substring(index + 1);
            boolean dirs = this.createDirs(fileDir, sftp);
            if (!dirs) {
                throw new Exception("Upload File failure");
            }
            sftp.put(inputStream, fileName);
            return true;
        } catch (Exception e) {
            throw new Exception("Upload File failure");
        } finally {
            this.disconnect(sftp);
        }
    }
    
    private boolean createDirs(String dirPath, ChannelSftp sftp) {
        if (dirPath != null && !dirPath.isEmpty()
                    && sftp != null) {
           /* String[] dirs = Arrays.stream(dirPath.split("/"))
                   .filter(StringUtils::isNotBlank)
                   .toArray(String[]::new)*/;
                 String[] split = dirPath.split("/");
                   
             String dirs[] = new String[split.length];      
                 
             for (int i = 0; i < split.length; i++) {
 				if(StringUtils.isNotBlank(split[i])) {
 				      String work = split[i];
 				      dirs[i] = work;
 				}
 			}
             	
             
                   
            for (String dir : dirs) {
                try {
                    sftp.cd(dir);
                } catch (Exception e) {
                    try {
                        sftp.mkdir(dir);
                    } catch (SftpException e1) {
                         e1.printStackTrace();
                    }
                    try {
                        sftp.cd(dir);
                    } catch (SftpException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            return true;
        }
        return false;
    }
    
}
