package cn.baisee.oa.importExcel.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;

import cn.baisee.oa.exception.OAServiceException;
import cn.baisee.oa.importExcel.exception.InvalidExcelTemplateException;
import cn.baisee.oa.importExcel.util.ExcelUtil;
import cn.baisee.oa.model.BaisswScore;


/**
 * Excel行数据转对象实例转换器
 * 
 * @param <T>
 *            要转换的对象实例的类型
 */
public class ExcelToBeanConvertor<T> {

	/**
	 * 按照指定的模板格式读取excel数据，封装至Map<String, List<T>>对象集合中，所有符合模板格式的sheet页的数据都会被读入<br>
	 * map的键值对的格式是：<sheet页名称, List<T>>
	 * 
	 * @param input
	 *            输入流
	 * @param titles
	 *            模板格式信息
	 * @param clazz
	 *            类实例
	 * @return 当excel数据不符合指定的模板的格式时将抛出
	 *         <code>com.icip.framework.service.importData.exception.InvalidExcelTemplateException</code>
	 *         异常
	 * @throws OAServiceException 
	 */
	public Map<String, List<T>> readToBeanMap(InputStream input,
			String[] titles, Class<T> clazz) throws OAServiceException {
		if (ArrayUtils.isEmpty(titles))
			return null;

		Map<String, List<Object[]>> dataMap = ExcelUtil.readToMap(input);
		List<String> validSheets = validSheet(dataMap, titles);

		if (CollectionUtils.isEmpty(validSheets))
			throw new InvalidExcelTemplateException();

		Map<String, List<T>> map = new HashMap<String, List<T>>();

		List<T> list = null;

		BeanGenerator<T> beanGenerator = new BeanGenerator<>();

		List<Object[]> dataList = null;
		T bean = null;
		for (Map.Entry<String, List<Object[]>> entry : dataMap.entrySet()) {
			String sheetName = entry.getKey();
			if (validSheets.contains(sheetName)) {
				dataList = entry.getValue();
				dataList.remove(0); // 去掉头信息
				list = new ArrayList<>();
				for (Object[] rowData : dataList) {
					bean = beanGenerator.generateBean(clazz, rowData);
					if (bean != null)
						list.add(bean);
				}
				map.put(sheetName, list);
			}
		}

		return map;
	}

	/**
	 * 获取符合模板格式的sheet页名称列表
	 * 
	 * @param dataMap
	 *            excel文件数据Map集合(键值对形式：<sheet名称, List<Object[]>>)
	 * @param titles
	 *            模板格式信息
	 * @return 所有的sheet页都不符合模板格式时返回null
	 */
	private List<String> validSheet(Map<String, List<Object[]>> dataMap,
			String[] titles) {
		List<String> validSheetList = null;
		if (MapUtils.isNotEmpty(dataMap)) {
			validSheetList = new ArrayList<String>();
			for (Map.Entry<String, List<Object[]>> entry : dataMap.entrySet()) {
				List<Object[]> rowDatas = entry.getValue();
				if (CollectionUtils.isNotEmpty(rowDatas)) {
					Object[] arr = ArrayUtils.subarray(rowDatas.get(0), 0,
							titles.length);
					if (ArrayUtils.isEquals(titles, arr))
						validSheetList.add(entry.getKey());
				}
			}
		}
		return validSheetList;
	}
	/**
	 * 动态表头
	 * @param input
	 * @param titles
	 * @param clazz
	 * @return
	 * @throws OAServiceException
	 */
	public Map<String, List<T>> readToBeanMap2(InputStream input, Class<T> clazz) throws OAServiceException {
		Map<String, List<Object[]>> dataMap = ExcelUtil.readToMap(input);
		Map<String, List<T>> map = new HashMap<String, List<T>>();
		List<T> list = null;
		BeanGenerator<T> beanGenerator = new BeanGenerator<>();
		List<Object[]> dataList = null;
		T bean = null;
		for (Map.Entry<String, List<Object[]>> entry : dataMap.entrySet()) {
			String sheetName = entry.getKey();
			list = new ArrayList<>();
			dataList = entry.getValue();
				Object[] title = dataList.get(0);
				dataList.remove(0);
				for(int i=0;i<dataList.size();i++) {
					Object[] rowData = dataList.get(i);
					Object name = rowData[0];
					for(int y=1;y<rowData.length;y++) {
						Object subject = title[y];
						Object scoer = rowData[y];
						Object[] row = new Object[] {name,subject,scoer};
						bean = beanGenerator.generateBean(clazz, row);
						if (bean != null)
							list.add(bean);
					}
					
				}
				
				map.put(sheetName, list);
		}

		return map;
	}
	
	
	public static void main(String[] args) throws Exception {
		/*Class<?> clazz = Class.forName("cn.baisee.oa.importExcel.dto.BaseDto");
		Field[] fields = clazz.getDeclaredFields(); 
		Object bean = clazz.newInstance();
		for(int i =0; i<fields.length;i++){
			BeanUtils.setProperty(bean, fields[i].getName(), "1");
		}*/
		ExcelToBeanConvertor<BaisswScore> convertor = new ExcelToBeanConvertor<BaisswScore>();
		File file = new File("D:\\socer.xlsx");
		InputStream is = new FileInputStream(file);
		
		convertor.readToBeanMap2(is, BaisswScore.class);
	}

}
