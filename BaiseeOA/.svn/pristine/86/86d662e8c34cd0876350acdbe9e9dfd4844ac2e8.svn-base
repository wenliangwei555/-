package cn.baisee.oa.utils.poi;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
public class test {
/*@RequestMapping("exportWordData")*/
	public void exportWordData(HttpServletRequest request,HttpServletResponse response){
	    WordUtils wordUtil=new WordUtils();
	    Map<String, Object> params = new HashMap<String, Object>();
	    params.put("${position}", "java开发");
	    params.put("${name}", "段然涛");
	    params.put("${sex}", "男");
	    params.put("${national}", "汉族");
	    params.put("${birthday}", "生日");
	    params.put("${address}", "许昌");
	    params.put("${height}", "165cm");
	    params.put("${biYeDate}", "1994-02-03");
	    params.put("${landscape}", "团员");
	    params.put("${zhuanYe}", "社会工作");
	    params.put("${xueLi}", "本科");
	    params.put("${school}", "江西科技师范大学");
	    params.put("${phone}", "177");
	    params.put("${eMail}", "157");

	    try{
	        Map<String,Object> header = new HashMap<String, Object>();
	        header.put("width", 100);
	        header.put("height", 150);
	        header.put("type", "jpg");
	        header.put("content", WordUtils.inputStream2ByteArray(new FileInputStream("C:/Users/Administrator/Desktop/jar包/11.jpg"), true));
	        params.put("${header}",header);
	        Map<String,Object> header2 = new HashMap<String, Object>();
	        header2.put("width", 100);
	        header2.put("height", 150);
	        header2.put("type", "jpg");
	        header2.put("content", WordUtils.inputStream2ByteArray(new FileInputStream("C:/Users/Administrator/Desktop/jar包/22.jpg"), true));
	        params.put("${header2}",header2);
	        List<String[]> testList = new ArrayList<String[]>();
	        testList.add(new String[]{"1","1AA","1BB","1CC"});
	        testList.add(new String[]{"2","2AA","2BB","2CC"});
	        testList.add(new String[]{"3","3AA","3BB","3CC"});
	        testList.add(new String[]{"4","4AA","4BB","4CC"});
	        String path="C:/Users/Administrator/Desktop/jar包/mobanFile.docx";  //模板文件位置
	        String fileName= new String("测试文档.docx".getBytes("UTF-8"),"iso-8859-1");    //生成word文件的文件名
	        wordUtil.getWord(path,params,testList,fileName,response);

	    }catch(Exception e){
	        e.printStackTrace();
	    }
	}

}
