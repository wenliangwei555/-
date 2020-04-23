package cn.baisee.oa.importExcel.dto;

/**
 * 导入EXCEL数据时，行下的单元格数据的校验结果DTO<br>
 * 
 * 
 */
public class CellValidateResultDto extends ValidateResultDto {
	// 单元格数据校验的错误信息
	private String errorMsg;

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public CellValidateResultDto(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Override
	public String toString() {
		return "CellValidateResultDto [errorMsg=" + errorMsg + "]";
	}

	/**
	 * 单元格数据是否合法
	 * 
	 * @return 校验后该单元格数据没有错误信息就说明该单元格数据是合法的，返回true
	 */
	@Override
	public boolean isValid() {
		return this == null || this.errorMsg == null
				|| this.errorMsg.length() == 0;
	}

}
