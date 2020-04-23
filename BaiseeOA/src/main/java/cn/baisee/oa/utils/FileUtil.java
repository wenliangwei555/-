package cn.baisee.oa.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.baisee.oa.exception.FileRuntimeException;


/**
 * 业务无关的文件解析静态工具
 */
public class FileUtil {

	private static final Log logger = LogFactory.getLog(FileUtil.class);

	/** 文件路径分隔符 */
	public static final String fileSplit = "/";

	/**
	 * 将byte数组信息写入到目标文件中去,如果目标文件路径不存在，则提示错误
	 * 
	 * @param data
	 *            byte数组信息
	 * @param goalPath
	 *            目标文件目录
	 * @return 返回true表示操作成功，返回false表示操作失败
	 */
	public static boolean byteToFile(byte[] data, String goalPath) {
		boolean resBool = false;
		File file = new File(goalPath);
		OutputStream output = null;
		BufferedOutputStream bufferedOutput = null;
		try {
			output = new FileOutputStream(file);
			bufferedOutput = new BufferedOutputStream(output);
			bufferedOutput.write(data);
			resBool = true;
		} catch (Exception e) {
			throw new FileRuntimeException(e);
		} finally {
			try {
				if (bufferedOutput != null) {
					bufferedOutput.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}

			try {
				if (output != null) {
					output.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return resBool;
	}

	/**
	 * 将文件信息转为byte数组输出
	 * 
	 * @param filePath
	 *            要转换的本地文件全路径
	 * @return 返回文件内容的byte数组
	 */
	public static byte[] fileToByte(String filePath) {
		byte[] resData = null;
		File file = new File(filePath);
		InputStream input = null;
		try {
			input = new FileInputStream(file);
			resData = new byte[input.available()];
			input.read(resData);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return resData;
	}

	/**
	 * 通过文件路径得到该文件所在目录 如果文件文件路径不正确则返回null
	 * 
	 * @param filePath
	 *            文件路径
	 * @return 返回文件所在目录
	 */
	public static String getDirByFilePath(String filePath) {
		if (StringUtil.isStrEmpty(filePath)) {
			return null;
		}
		int lastSplit = filePath.lastIndexOf(fileSplit);
		if (lastSplit == -1) {
			return null;
		} else {
			return filePath.substring(0, lastSplit);
		}
	}

	/**
	 * 根据文件夹路径得到文件夹名称顺序数组 例如传入：D:/dir1/dir2/,则返回{"D:","dir1","dir2"}
	 * 
	 * @param dirPath
	 *            传入的文件夹路径
	 * @return 返回文件夹顺序数组
	 */
	public static String[] getDirArr(String dirPath) {
		if (dirPath == null) {
			return null;
		}
		LinkedList<String> dirListName = new LinkedList<String>();
		String[] dirArr = dirPath.split(fileSplit);
		for (int i = 0; i < dirArr.length; i++) {
			String currStr = dirArr[i];
			if (StringUtil.isStrEmpty(currStr) || fileSplit.equals(currStr)) {
				continue;
			} else {
				dirListName.add(currStr);
			}
		}
		String[] resultArr = new String[dirListName.size()];
		return dirListName.toArray(resultArr);
	}

}
