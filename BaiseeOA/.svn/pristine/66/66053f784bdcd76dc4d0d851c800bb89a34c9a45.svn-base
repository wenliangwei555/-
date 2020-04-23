package cn.baisee.oa.utils;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 读取配置文件的工具类
 * @author liangfeng
 *
 */
public class PropertiesUtil {

	private Properties props;

    public PropertiesUtil(String fileName) {
        readProperties(fileName);
    }

    /**
     * 加载配置文件
     *
     * @param fileName
     */
    private void readProperties(String fileName) {
        try {
            props = new Properties();
            InputStreamReader inputStream = new InputStreamReader(
                    this.getClass().getClassLoader().getResourceAsStream(fileName), "UTF-8");
            props.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 根据key读取对应的value
     *
     * @param key
     * @return
     */
    public String get(String key) {
        return props.getProperty(key);
    }
   
}
