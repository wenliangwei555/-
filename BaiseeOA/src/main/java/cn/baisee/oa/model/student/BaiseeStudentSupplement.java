package cn.baisee.oa.model.student;
/**
 * 学生信息补充表
 * @author sdx
 *
 */
public class BaiseeStudentSupplement {
	/**
	 * 补充表主键
	 */
	private Integer pkBesi;
	/**
	 * 学生信息主键
	 */
	private String stuId;
	/**
	 * 学生邮箱
	 */
	private String stuMailBox;
	/**
	 * QQ
	 */
	private String stuQQNumber;
	/**
	 * 微信号码
	 */
	private String stuWecharNumber;
	/**
	 * 所属省份
	 */
	private String stuProvince;
	
	/**
	 * 所属城市
	 */
	private String stuCity;
	/**
	 * 所属区县
	 */
	private String stuArea;
	/**
	 * 真实省份
	 */
	private String stuRealProvince;
	/**
	 * 真实城市
	 */
	private String stuRealCity;
	/**
	 * 真实区县
	 */
	private String stuRealArea;
	/**
	 * 地址信息
	 */
	private String stuAddress;
	/**
	 * 详细住址
	 */
	private String stuHomeAddress;
	/**
	 * 头像
	 */
	private String stuHeadPhotoDir;
	
	public String getStuRealProvince() {
		return stuRealProvince;
	}
	public void setStuRealProvince(String stuRealProvince) {
		this.stuRealProvince = stuRealProvince;
	}
	public String getStuRealCity() {
		return stuRealCity;
	}
	public void setStuRealCity(String stuRealCity) {
		this.stuRealCity = stuRealCity;
	}
	public String getStuRealArea() {
		return stuRealArea;
	}
	public void setStuRealArea(String stuRealArea) {
		this.stuRealArea = stuRealArea;
	}
	public Integer getPkBesi() {
		return pkBesi;
	}
	public void setPkBesi(Integer pkBesi) {
		this.pkBesi = pkBesi;
	}
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId == null ? null : stuId.trim();
	}
	public String getStuMailBox() {
		return stuMailBox;
	}
	public void setStuMailBox(String stuMailBox) {
		this.stuMailBox = stuMailBox == null ? null : stuMailBox.trim();
	}
	public String getStuQQNumber() {
		return stuQQNumber;
	}
	public void setStuQQNumber(String stuQQNumber) {
		this.stuQQNumber = stuQQNumber == null ? null : stuQQNumber.trim();
	}
	public String getStuWecharNumber() {
		return stuWecharNumber;
	}
	public void setStuWecharNumber(String stuWecharNumber) {
		this.stuWecharNumber = stuWecharNumber == null ? null : stuWecharNumber.trim();
	}
	
	public String getStuHomeAddress() {
		return stuHomeAddress;
	}
	public void setStuHomeAddress(String stuHomeAddress) {
		this.stuHomeAddress = stuHomeAddress == null ? null : stuHomeAddress.trim();
	}
	public String getStuHeadPhotoDir() {
		return stuHeadPhotoDir;
	}
	public void setStuHeadPhotoDir(String stuHeadPhotoDir) {
		this.stuHeadPhotoDir = stuHeadPhotoDir == null ? null : stuHeadPhotoDir.trim();
	}
	
	
	public BaiseeStudentSupplement(){
		
	}
	public BaiseeStudentSupplement(String stuId){
		this.stuId=stuId;
	}
	//批量导入时使用
	public BaiseeStudentSupplement(String stuId,String stuHomeAddress){
		this.stuId=stuId;
		this.stuHomeAddress=stuHomeAddress;
	}
	
	public BaiseeStudentSupplement(String stuId, String stuArea,
			String stuHomeAddress) {
		super();
		this.stuId = stuId;
		this.stuArea = stuArea;
		this.stuHomeAddress = stuHomeAddress;
	}
	public String getStuProvince() {
		return stuProvince;
	}
	public void setStuProvince(String stuProvince) {
		this.stuProvince = stuProvince;
	}
	public String getStuCity() {
		return stuCity;
	}
	public void setStuCity(String stuCity) {
		this.stuCity = stuCity;
	}
	public String getStuArea() {
		return stuArea;
	}
	public void setStuArea(String stuArea) {
		this.stuArea = stuArea;
	}
	public String getStuAddress() {
		return stuAddress;
	}
	public void setStuAddress(String stuAddress) {
		this.stuAddress = stuAddress;
	}
	@Override
	public String toString() {
		return "BaiseeStudentSupplement [pkBesi=" + pkBesi + ", stuId=" + stuId
				+ ", stuMailBox=" + stuMailBox + ", stuQQNumber=" + stuQQNumber
				+ ", stuWecharNumber=" + stuWecharNumber + ", stuProvince="
				+ stuProvince + ", stuCity=" + stuCity + ", stuArea=" + stuArea
				+ ", stuAddress=" + stuAddress + ", stuHomeAddress="
				+ stuHomeAddress + ", stuHeadPhotoDir=" + stuHeadPhotoDir + "]";
	}

	
	
}
