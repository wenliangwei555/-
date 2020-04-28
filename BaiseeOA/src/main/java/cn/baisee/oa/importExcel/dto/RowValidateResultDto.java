package cn.baisee.oa.importExcel.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;

/**
 * 导入EXCEL数据时，行记录的校验结果DTO<br>
 * 该类同时实现了{@link Comparable}接口，用于按顺序展示所有的校验结果
 * 
 */
public class RowValidateResultDto extends ValidateResultDto implements
		Comparable<RowValidateResultDto> {
	// 行标记
	private String rowMarker;
	// 该行所有单元格的校验结果
	private List<CellValidateResultDto> cvrDtoList;

	public String getRowMarker() {
		return rowMarker;
	}

	public void setRowMarker(String rowMarker) {
		this.rowMarker = rowMarker;
	}

	// 此方法不可删除，如果没有此方法，前端页面无法展示该列表信息
	public List<CellValidateResultDto> getCvrDtoList() {
		return cvrDtoList;
	}

	@Override
	public String toString() {
		return "RowValidateResultDto [rowMarker=" + rowMarker + ", cvrDtoList="
				+ cvrDtoList + "]";
	}

	/**
	 * 该行记录是否合法<br>
	 * 
	 * @return 本身为null或该行下的所有单元格的校验都合法则返回true
	 */
	@Override
	public boolean isValid() {
		return this == null || cvrDtoList == null || cvrDtoList.isEmpty();
	}

	/**
	 * 保存该行下的单元格的校验结果至该行校验结果中
	 * 
	 * @param cvrDto
	 *            单元格校验结果
	 */
	public void addCellValidateResultList(CellValidateResultDto cvrDto) {
		if (cvrDtoList == null || cvrDtoList.isEmpty())
			cvrDtoList = new ArrayList<>();
		if (cvrDto != null && !cvrDto.isValid())
			cvrDtoList.add(cvrDto);
	}

	/**
	 * 重写compareTo方法，用于排序，依据的是行标记
	 * 
	 * @param other
	 *            另一个对象
	 * @return 对行标记进行比较，行标记大则返回正数
	 */
	public int compareTo(RowValidateResultDto other) {
		if (other == null)
			return -1;
		int x = NumberUtils.toInt(rowMarker);
		int y = NumberUtils.toInt(other.getRowMarker());
		return (x < y) ? -1 : ((x == y) ? 0 : 1);
	}

}
