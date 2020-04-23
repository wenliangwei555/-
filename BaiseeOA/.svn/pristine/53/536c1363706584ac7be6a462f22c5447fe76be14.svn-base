package cn.baisee.oa.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

/**
 * 功能:解析velocity模板
 * 
 */
public class VelocityParserUtil {
	 

	private static VelocityParserUtil instance = new VelocityParserUtil();

	private VelocityEngine engine = null;

	private VelocityParserUtil() {
		engine = new VelocityEngine();
		try {
			engine.init();
		} catch (Exception e) { 

		}
	}

	/**
	 * 返回VelocityParserUtil实例
	 * 
	 * @return
	 */
	public static VelocityParserUtil getInstance() {
		return instance;
	}

	/**
	 * 解析velocity模板
	 * 
	 * @param vtl
	 * @param model
	 * @return String
	 * @throws ParseErrorException
	 * @throws MethodInvocationException
	 * @throws ResourceNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public String parseVelocityTemplate(String vtl, Map model)
			throws Exception {

		VelocityContext velocityContext = new VelocityContext(model);
		StringWriter result = new StringWriter();
		engine.evaluate(velocityContext, result, null, vtl);

		String returnString = result.toString();
		return returnString;

	}

	static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
	public static void main(String[] args) throws Exception {
		//已交学费 - (((学费 - 杂费 )/6 )* 月数 + 杂费)
		String fomula = "$paidFee - ((($fee - $sundryFees )/$schoolSystem )* $month - $sundryFees)";
		Pattern p = Pattern.compile("\\{.*?\\}");
		Matcher m = p.matcher(fomula);
		while (m.find()) {
			String param = m.group().replaceAll("\\{", ""); 
			param = param.replace("}", "");
		}
		fomula = fomula.replaceAll("\\{", "");
		fomula = fomula.replaceAll("}", "");
		System.out.println(fomula);
		
		Map map = new HashMap();
		map.put("paidFee", 19800);
		map.put("fee", 19800);
		map.put("sundryFees", 1800);
		map.put("schoolSystem", 6);
		map.put("month", 2);
		Object a = jse.eval(VelocityParserUtil.getInstance()
				.parseVelocityTemplate(fomula, map));
	 
		System.out.println(a);
		 
	}

}
