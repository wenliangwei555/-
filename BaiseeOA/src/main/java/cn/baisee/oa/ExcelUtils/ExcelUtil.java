package cn.baisee.oa.ExcelUtils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * excel工具类
 * 
 * @author zhonglinsen
 *
 */
public class ExcelUtil {

	private static final Logger log = LoggerFactory.getLogger(ExcelUtil.class);
	private static final String dateFormat = "yyyy-MM-dd";

	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

	/**
	 * excel sheet填充数据
	 * 
	 * @param dataList
	 * @param wb
	 * @param headers
	 * @param sheetName
	 */
	public static void fillExcelSheetData(List<Map<Integer, Object>> dataList, Workbook wb, String[] headers,
			String sheetName) {
		Sheet sheet = wb.createSheet(sheetName);
		
		// TODO：创建sheet的第一行数据-即excel的头部信息
		Row headerRow = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			headerRow.createCell(i).setCellValue(headers[i]);
		}

		// TODO：从第二行开始塞入真正的数据列表
		int rowIndex = 1;
		Row row;
		Object obj;
		for (Map<Integer, Object> rowMap : dataList) {
			try {
				row = sheet.createRow(rowIndex++);
				for (int i = 0; i < headers.length; i++) {
					sheet.setColumnWidth(i, 20*256);
					obj = rowMap.get(i);
					if (obj == null) {
						row.createCell(i).setCellValue("");
					} else if (obj instanceof Date) {
						String tempDate = simpleDateFormat.format((Date) obj);
						row.createCell(i).setCellValue((tempDate == null) ? "" : tempDate);
					} else {
						row.createCell(i).setCellValue(String.valueOf(obj));
					}
				}
			} catch (Exception e) {
				log.debug("excel sheet填充数据 发生异常： ", e.fillInStackTrace());
			}
		}

	}

}
