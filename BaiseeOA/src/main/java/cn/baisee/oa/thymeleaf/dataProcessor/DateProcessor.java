package cn.baisee.oa.thymeleaf.dataProcessor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.util.Assert;
import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;
import org.thymeleaf.processor.attr.AbstractTextChildModifierAttrProcessor;

import cn.baisee.oa.thymeleaf.utils.OaDialectUtil;
import cn.baisee.oa.utils.DateUtil;
import cn.baisee.oa.utils.StringUtil;
/**
 * 时间格式化
 * @author Administrator
 *
 */
public class DateProcessor extends AbstractTextChildModifierAttrProcessor {
	
	public DateProcessor() {
		this("fmt");
	}

	protected DateProcessor(String attributeName) {
		super(attributeName);
	}
	/**
	 * 格式化日期字符串(baisee:fmt="pattern,${dateStr}" 默认:yyyy-MM-dd HH:mm:ss)
	 */
	@Override
	protected String getText(Arguments paramArguments, Element paramElement, String attributeName) {
		String str = paramElement.getAttributeValue(attributeName);
		Assert.hasLength(str, "表达式不能为空");
		String datePattern = null;
		String paramStr = null;
		String patternIndex = null;
		if(str.indexOf(",") > -1) {
			String[] array = str.split(",");
			datePattern = array[0];
			Assert.hasLength(datePattern, "格式化表达式不能为空");
			paramStr = array[1];
			Assert.hasLength(paramStr, "参数表达式不能为空");
			if(array.length>2) {
				patternIndex = array[2];
			}
		} else {
			datePattern = DateUtil.DATE_FORMAT_FULL;
			paramStr = str;
		}
		String param = OaDialectUtil.getValue(paramArguments, paramStr.trim());
		if(StringUtil.isEmpty(param)) {
			return "—— ——";
		}
		if(StringUtil.isEmpty(patternIndex)) {
			Date d = DateUtil.parseDate(param);
			return DateUtil.formatDate(datePattern, d);
		}else {
			SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy",Locale.US);
			Date d = null;
			try {
				d = sdf.parse(param);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return DateUtil.formatDate(datePattern, d);
		}
		
	}

	@Override
	public int getPrecedence() {
		return 0;
	}

}
