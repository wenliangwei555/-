package cn.baisee.oa.model.evaluate;


public class BaiseeStuEvaone {
	private String cs_Name; /*课程名*/
	private String content; /*内容*/
	private String className;  /*班级名*/
	private int	numa;  /*总数*/
	private int numb; /*答案数*/
	private int numc;   /*更新时间*/
	private String titleName; /*标题名*/
	

	public String getCs_Name() {
		return cs_Name;
	}


	public void setCs_Name(String cs_Name) {
		this.cs_Name = cs_Name;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getClassName() {
		return className;
	}


	public void setClassName(String className) {
		this.className = className;
	}


	public int getNuma() {
		return numa;
	}


	public void setNuma(int numa) {
		this.numa = numa;
	}


	public int getNumb() {
		return numb;
	}


	public void setNumb(int numb) {
		this.numb = numb;
	}


	public int getNumc() {
		return numc;
	}


	public void setNumc(int numc) {
		this.numc = numc;
	}


	public String getTitleName() {
		return titleName;
	}


	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}


	@Override
	public String toString() {
		return "BaiseeStuEva [cs_Name=" + cs_Name + ", content=" + content
				+ ", className=" + className + ", numa=" + numa + ", numb=" + numb + ", numc="
				+ numc + ", titleName=" + titleName + "]";
	}
}

