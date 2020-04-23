package cn.baisee.oa.model.evaluate;

public class EvaAndControl {
		private int ecID;   /*ID*/
		private String TitleName;  /*标题名*/
		private String claName;  /*评价班级名*/
		private String empName;   /*评价对象名*/
		private String creationTime;  /*开启时间*/
		private String openName;  /*开启人*/
		
		public String getOpenName() {
			return openName;
		}
		public void setOpenName(String openName) {
			this.openName = openName;
		}
		public int getEcID() {
			return ecID;
		}
		public void setEcID(int ecID) {
			this.ecID = ecID;
		}
		public String getTitleName() {
			return TitleName;
		}
		public void setTitleName(String titleName) {
			TitleName = titleName;
		}
		public String getClaName() {
			return claName;
		}
		public void setClaName(String claName) {
			this.claName = claName;
		}
		public String getEmpName() {
			return empName;
		}
		public void setEmpName(String empName) {
			this.empName = empName;
		}
		public String getCreationTime() {
			return creationTime;
		}
		public void setCreationTime(String creationTime) {
			this.creationTime = creationTime;
		}
		@Override
		public String toString() {
			return "EvaAndControl [ecID=" + ecID + ", TitleName=" + TitleName + ", claName=" + claName + ", empName="
					+ empName + ", creationTime=" + creationTime + ", openName=" + openName + "]";
		}
		
}
