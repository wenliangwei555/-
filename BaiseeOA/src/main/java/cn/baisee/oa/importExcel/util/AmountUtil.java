package cn.baisee.oa.importExcel.util;

import java.text.DecimalFormat;

/**
 * 金额工具类
 *
 */
public class AmountUtil {
	/** 格式化金额的格式 */
	private static final String DECIMAL_FORMAT_PATTERN = "##,###,###,#####0.00";

	public static boolean isPositive(Object amount) {
		return formatAmount(amount) > 0;
	}

	public static boolean isNonNegative(Object amount) {
		return formatAmount(amount) >= 0;
	}

	public static double formatAmount(Object amount) {
		if (amount == null)
			return 0;
		try {
			double d = Double.parseDouble(amount.toString());
			return d;
		} catch (NumberFormatException e) {
		}
		return 0;
	}

	public static String divAmount(Object amount, int parts) {
		DecimalFormat df = new DecimalFormat(DECIMAL_FORMAT_PATTERN);
		return df.format(formatAmount(amount) / parts);
	}
}
