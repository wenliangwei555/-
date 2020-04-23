package cn.baisee.oa.importExcel.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import cn.baisee.oa.exception.OAServiceException;
import cn.baisee.oa.importExcel.exception.InvalidExcelTemplateException;
import cn.baisee.oa.utils.ArrayUtil;


/**
 * 读取Excel文件工具类
 * 
 */
public class ExcelUtil {
	private static final Log LOGGER = LogFactory.getLog(ExcelUtil.class);

	/**
	 * 创建Apache POI WorkBook对象
	 * 
	 * @param input
	 *            输入流
	 * @return 由excel文件对应创建的WorkBook对象
	 * @throws OAServiceException 
	 */
	private static Workbook getExcelWorkBook(InputStream input) throws InvalidExcelTemplateException {
		Workbook workbook = null;
		try {
			workbook = WorkbookFactory.create(input);
			return workbook;
		} catch (InvalidFormatException | IOException e) {
			LOGGER.error("create Workbook failed", e);
			throw new InvalidExcelTemplateException();
		} finally {
			IOUtils.closeQuietly(input);
		}
	}

	/**
	 * 读取excel文件数据至Map集合中，键值对形式：<sheet名称, List<Object[]>>
	 * 
	 * @param input
	 *            输入流
	 * @return 返回诸如如下形式的Map集合：<br>
	 *         {<br>
	 *         <"第1个sheet页名称", {["row1col1", "row1col2", "row1col3"],
	 *         ["row2col1", "row2col2", "row2col3"]}>,<br>
	 *         <"第2个sheet页名称", {["row1col1", "row1col2", "row1col3"]}><br>
	 *         }
	 * @throws OAServiceException 
	 */
	public static Map<String, List<Object[]>> readToMap(InputStream input)  {
		Map<String, List<Object[]>> map = new HashMap<String, List<Object[]>>();
		Workbook workbook = getExcelWorkBook(input);
		int sheetNumber = workbook.getNumberOfSheets();
		List<Object[]> list = null;
		for (int i = 0; i < sheetNumber; i++) {
			Sheet sheet = workbook.getSheetAt(i);
			list = new ArrayList<Object[]>();

			int firstRowNum = sheet.getFirstRowNum();
			int lastRowNum = sheet.getLastRowNum();
			for (int j = firstRowNum; j <= lastRowNum; j++) {
				Row row = sheet.getRow(j);
				if (row == null)
					continue;

				int firstCellNum = row.getFirstCellNum();
				int lastCellNum = row.getLastCellNum();

				int length = lastCellNum - firstCellNum;
				Object[] cellArr = new String[length];

				for (int k = 0; k < length; k++) {
					Cell cell = row.getCell(k);
					cellArr[k] = getCellValue(cell);
				}

				// 如果读取的行的所有单元格数据为空
				if (ArrayUtil.isAllElemEmpty(cellArr))
					continue;

				list.add(cellArr);
			}

			if (CollectionUtils.isNotEmpty(list))
				map.put(sheet.getSheetName().trim(), list);
		}
		return map;
	}

	/**
	 * 读取excel单元格的值
	 * 
	 * @param cell
	 *            单元格对象
	 * @return
	 */
	private static Object getCellValue(Cell cell) {
		if (cell == null)
			return "";
		Object cellValue = null;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BLANK:
			cellValue = "";
			break;
		case Cell.CELL_TYPE_ERROR:
			cellValue = Byte.toString(cell.getErrorCellValue());
			break;
		case Cell.CELL_TYPE_STRING:
			cellValue = cell.getRichStringCellValue().getString();
			// 如果是日期格式的文本，则统一转换为"yyyyMMdd"格式
			cellValue = cn.baisee.oa.importExcel.util.DateUtil.converFormat(ObjectUtils.toString(cellValue));
			break;
		/** 在excel中日期也是数字,在此要进行判断 */
		case Cell.CELL_TYPE_NUMERIC:
			Double d = cell.getNumericCellValue();
			if (DateUtil.isCellDateFormatted(cell)) {
				SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
				cellValue = fmt.format(HSSFDateUtil.getJavaDate(d));
			} else
				cellValue = d.toString();
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			cellValue = Boolean.toString(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_FORMULA:
			cellValue = cell.getCellFormula();
			break;
		default:
			cellValue = "";
		}
		return cellValue;
	}

}
