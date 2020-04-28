package cn.baisee.oa.utils;

/**
 * 开始日期结束日期DTO<br>
 * 将开始日期结束日期合并作为一个DTO，可以做为一个日期区间<br>
 * 同时实现了{@link Comparable}接口，用于排序，排序时以开始日期为标准
 * 
 */
public class StartEndDateDto implements Comparable<StartEndDateDto> {
	// 开始日期
	private String startDate;
	// 结束日期
	private String endDate;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public StartEndDateDto(String startDate, String endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}

	@Override
	public int compareTo(StartEndDateDto o) {
		if (o == null)
			return -1;
		return this.startDate.compareTo(o.startDate);
	}

	@Override
	public String toString() {
		return "StartEndDateDto [startDate=" + startDate + ", endDate="
				+ endDate + "]";
	}

}
