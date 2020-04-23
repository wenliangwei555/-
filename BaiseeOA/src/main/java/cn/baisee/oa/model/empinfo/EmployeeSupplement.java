package cn.baisee.oa.model.empinfo;
/**
 * 员工补充表
 * @author LiFQ
 *
 */
public class EmployeeSupplement {
	/**
	 * 本张表的主键
	 */
	private Integer PK_BESI;//补充表主键
	/**
	 * 员工信息表主键
	 */
	private String empId;//员工信息表主键 EID
	
	/**
	 * 员工位置的座机号码
	 */
	private String empSeatNumber;//座机号码 员工位置的座机号码 E_SEAT_NUMBER
	
	/**
	 * 员工邮箱
	 */
	private String empMailbox;//员工邮箱 E_MAILBOX
	/**
	 * 员工QQ号码
	 */
	private String empQQNumber;//员工QQ号码 E_QQ_NUMBER
	/**
	 * 员工微信号码
	 */
	private String empWeChatNumber;//员工微信号码 E_WECHAT_NUMBER
	
	/**
	 * 员工家长住址
	 */
	private String empHomeAddress;//员工家庭住址 E_HOME_ADDRESS
	/**
	 * 员工共籍贯
	 */
	private String empPlace;//员工籍贯E_PLACE
	/**
	 * 政治面貌
	 */
	private String empPolicalOutlook;//政治面貌 E_POLITICAL_OUTLOOK
	/**
	 * 户口所在地
	 */
	private String empRegisteredResidence;//户口所在地 E_REGISTERED_RESIDENCE
	public Integer getPK_BESI() {
		return PK_BESI;
	}
	public void setPK_BESI(Integer pK_BESI) {
		PK_BESI = pK_BESI;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId == null ? null : empId.trim();
	}
	public String getEmpSeatNumber() {
		return empSeatNumber;
	}
	public void setEmpSeatNumber(String empSeatNumber) {
		this.empSeatNumber = empSeatNumber == null ? null : empSeatNumber.trim();
	}
	public String getEmpMailbox() {
		return empMailbox;
	}
	public void setEmpMailbox(String empMailbox) {
		this.empMailbox = empMailbox == null ? null : empMailbox.trim();
	}
	public String getEmpQQNumber() {
		return empQQNumber;
	}
	public void setEmpQQNumber(String empQQNumber) {
		this.empQQNumber = empQQNumber == null ? null : empQQNumber.trim();
	}
	public String getEmpWeChatNumber() {
		return empWeChatNumber;
	}
	public void setEmpWeChatNumber(String empWeChatNumber) {
		this.empWeChatNumber = empWeChatNumber == null ? null : empWeChatNumber.trim();
	}
	public String getEmpHomeAddress() {
		return empHomeAddress;
	}
	public void setEmpHomeAddress(String empHomeAddress) {
		this.empHomeAddress = empHomeAddress == null ? null : empHomeAddress.trim();
	}
	public String getEmpPlace() {
		return empPlace;
	}
	public void setEmpPlace(String empPlace) {
		this.empPlace = empPlace == null ? null : empPlace.trim();
	}
	public String getEmpPolicalOutlook() {
		return empPolicalOutlook;
	}
	public void setEmpPolicalOutlook(String empPolicalOutlook) {
		this.empPolicalOutlook = empPolicalOutlook == null ? null : empPolicalOutlook.trim();
	}
	public String getEmpRegisteredResidence() {
		return empRegisteredResidence;
	}
	public void setEmpRegisteredResidence(String empRegisteredResidence) {
		this.empRegisteredResidence = empRegisteredResidence == null ? null : empRegisteredResidence.trim();
	}
	
	public EmployeeSupplement(String empId) {
		super();
		this.empId = empId;
	}
	public EmployeeSupplement() {
		super();
	}
	
}
