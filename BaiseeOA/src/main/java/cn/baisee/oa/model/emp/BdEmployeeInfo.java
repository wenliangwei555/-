package cn.baisee.oa.model.emp;

/**
 * 员工信息实体
 *
 */
public class BdEmployeeInfo {
    private String eid;

    private String eName;

    private String eSex;

    private String eDepartment;

    private String ePosition;

    private String eRole;

    private String eState;

    private String eHireDate;
    
    private String communityName;
    
    private String villageName;

    public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String geteHireDate() {
		return eHireDate;
	}

	public void seteHireDate(String eHireDate) {
		this.eHireDate = eHireDate;
	}

	public String geteWorkingTime() {
		return eWorkingTime;
	}

	public void seteWorkingTime(String eWorkingTime) {
		this.eWorkingTime = eWorkingTime;
	}

	private String eWorkingTime;

    private String eNativePlace;

    private String eCertNum;

    private String ePResidence;

    private String eDegree;

    private String eGraduatedSchool;

    private String ePoliticalDegree;

    private String pcid;

    private String cid;

    private String updateTime;
    
    private String companyName;
    
    private String mobile;
    
    private String chargeState;
    //是否收费员
    private String isCharger;
    
    private String ePhotoDir;
    
    
    public String getePhotoDir() {
		return ePhotoDir;
	}

	public void setePhotoDir(String ePhotoDir) {
		this.ePhotoDir = ePhotoDir;
	}

	public String getIsCharger() {
		return isCharger;
	}

	public void setIsCharger(String isCharger) {
		this.isCharger = isCharger;
	}

	public String getChargeState() {
		return chargeState;
	}

	public void setChargeState(String chargeState) {
		this.chargeState = chargeState;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	private BdEmployeeContacts employeeContacts = new BdEmployeeContacts();

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid == null ? null : eid.trim();
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName == null ? null : eName.trim();
    }

    public String geteSex() {
        return eSex;
    }

    public void seteSex(String eSex) {
        this.eSex = eSex == null ? null : eSex.trim();
    }

    public String geteDepartment() {
        return eDepartment;
    }

    public void seteDepartment(String eDepartment) {
        this.eDepartment = eDepartment == null ? null : eDepartment.trim();
    }

    public String getePosition() {
        return ePosition;
    }

    public void setePosition(String ePosition) {
        this.ePosition = ePosition == null ? null : ePosition.trim();
    }

    public String geteRole() {
        return eRole;
    }

    public void seteRole(String eRole) {
        this.eRole = eRole == null ? null : eRole.trim();
    }

    public String geteState() {
        return eState;
    }

    public void seteState(String eState) {
        this.eState = eState == null ? null : eState.trim();
    }

    public String geteNativePlace() {
        return eNativePlace;
    }

    public void seteNativePlace(String eNativePlace) {
        this.eNativePlace = eNativePlace == null ? null : eNativePlace.trim();
    }

    public String geteCertNum() {
        return eCertNum;
    }

    public void seteCertNum(String eCertNum) {
        this.eCertNum = eCertNum == null ? null : eCertNum.trim();
    }

    public String getePResidence() {
        return ePResidence;
    }

    public void setePResidence(String ePResidence) {
        this.ePResidence = ePResidence == null ? null : ePResidence.trim();
    }

    public String geteDegree() {
        return eDegree;
    }

    public void seteDegree(String eDegree) {
        this.eDegree = eDegree == null ? null : eDegree.trim();
    }

    public String geteGraduatedSchool() {
        return eGraduatedSchool;
    }

    public void seteGraduatedSchool(String eGraduatedSchool) {
        this.eGraduatedSchool = eGraduatedSchool == null ? null : eGraduatedSchool.trim();
    }

    public String getePoliticalDegree() {
        return ePoliticalDegree;
    }

    public void setePoliticalDegree(String ePoliticalDegree) {
        this.ePoliticalDegree = ePoliticalDegree == null ? null : ePoliticalDegree.trim();
    }

    public String getPcid() {
        return pcid;
    }

    public void setPcid(String pcid) {
        this.pcid = pcid == null ? null : pcid.trim();
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

		public void setEmployeeContacts(BdEmployeeContacts employeeContacts) {
			this.employeeContacts = employeeContacts;
		}

		public BdEmployeeContacts getEmployeeContacts() {
			return employeeContacts;
		}
}