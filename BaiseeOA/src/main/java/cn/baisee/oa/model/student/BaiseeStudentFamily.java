package cn.baisee.oa.model.student;

/**
 * 学生家庭信息
 *
 * @author sdx
 */
public class BaiseeStudentFamily {

    @Override
    public String toString() {
        return "BaiseeStudentFamily{" +
                "pkBefi=" + pkBefi +
                ", stuId='" + stuId + '\'' +
                ", stuFamName='" + stuFamName + '\'' +
                ", stuFamSex='" + stuFamSex + '\'' +
                ", stuFamTel='" + stuFamTel + '\'' +
                ", stuFamIdNumber='" + stuFamIdNumber + '\'' +
                ", stuFamRelation='" + stuFamRelation + '\'' +
                ", stuFamOtherRelation='" + stuFamOtherRelation + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }

    /**
     * 家庭信息主键
     */
    private Integer pkBefi;
    /**
     * 学生信息主键
     */
    private String stuId;
    /**
     * 学生家长姓名
     */
    private String stuFamName;
    /**
     * 学生家长性别
     */
    private String stuFamSex;
    /**
     * 家长联系方式
     */
    private String stuFamTel;
    /**
     * 家长身份证
     */
    private String stuFamIdNumber;
    /**
     * 家长主关系（父亲母亲）
     */
    private String stuFamRelation;
    /**
     * 其他家长关系（叔叔舅舅）
     */
    private String stuFamOtherRelation;
    /**
     * 最后更新时间
     */
    private String updateTime;

    public Integer getPkBefi() {
        return pkBefi;
    }

    public void setPkBefi(Integer pkBefi) {
        this.pkBefi = pkBefi;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId == null ? null : stuId.trim();
    }

    public String getStuFamName() {
        return stuFamName;
    }

    public void setStuFamName(String stuFamName) {
        this.stuFamName = stuFamName == null ? null : stuFamName.trim();
    }

    public String getStuFamSex() {
        return stuFamSex;
    }

    public void setStuFamSex(String stuFamSex) {
        this.stuFamSex = stuFamSex == null ? null : stuFamSex.trim();
    }

    public String getStuFamTel() {
        return stuFamTel;
    }

    public void setStuFamTel(String stuFamTel) {
        this.stuFamTel = stuFamTel == null ? null : stuFamTel.trim();
    }

    public String getStuFamIdNumber() {
        return stuFamIdNumber;
    }

    public void setStuFamIdNumber(String stuFamIdNumber) {
        this.stuFamIdNumber = stuFamIdNumber;
    }

    public String getStuFamRelation() {
        return stuFamRelation;
    }

    public void setStuFamRelation(String stuFamRelation) {
        this.stuFamRelation = stuFamRelation == null ? null : stuFamRelation.trim();
    }

    public String getStuFamOtherRelation() {
        return stuFamOtherRelation;
    }

    public void setStuFamOtherRelation(String stuFamOtherRelation) {
        this.stuFamOtherRelation = stuFamOtherRelation;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }


    public BaiseeStudentFamily() {

    }

    public BaiseeStudentFamily(String stuId) {
        this.stuId = stuId;
    }

    /**
     * 批量导入正式
     *
     * @param stuId
     * @param stuFamTel
     * @param stuFamRelation
     * @param stuFamIdNumber
     * @param stuFamName
     * @param stuFamOtherRelation
     */
    public BaiseeStudentFamily(String stuId, String stuFamTel, String stuFamRelation, String stuFamIdNumber, String stuFamName, String stuFamOtherRelation) {
        this.stuId = stuId;
        this.stuFamTel = stuFamTel;
        this.stuFamRelation = stuFamRelation;
        this.stuFamIdNumber = stuFamIdNumber;
        this.stuFamName = stuFamName;
        this.stuFamOtherRelation = stuFamOtherRelation;
    }

    /**
     * 批量导入试听
     *
     * @param stuId
     * @param stuFamTel
     * @param stuFamRelation
     * @param stuFamIdNumber
     * @param stuFamName
     * @param stuFamOtherRelation
     */
    public BaiseeStudentFamily(String stuId, String stuFamTel, String stuFamRelation, String stuFamName) {
        this.stuId = stuId;
        this.stuFamTel = stuFamTel;
        this.stuFamRelation = stuFamRelation;
        this.stuFamName = stuFamName;
    }
}
