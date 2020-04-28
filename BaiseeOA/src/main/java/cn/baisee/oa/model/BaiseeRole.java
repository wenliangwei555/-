package cn.baisee.oa.model;

public class BaiseeRole {
    private String roleId;

    private String roleName;
    
    private String createUser;
    
    private String updateUser;
    
    private int roleLevel;
    
    private String updateTime;
    
    private BaiseeUser createUserInfo;
    
    private BaiseeUser updateUserInfo; 

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

		public String getCreateUser() {
			return createUser;
		}

		public String getUpdateUser() {
			return updateUser;
		}

		public String getUpdateTime() {
			return updateTime;
		}

		public void setCreateUser(String createUser) {
			this.createUser = createUser;
		}

		public void setUpdateUser(String updateUser) {
			this.updateUser = updateUser;
		}

		public void setUpdateTime(String updateTime) {
			this.updateTime = updateTime;
		}

		public BaiseeUser getCreateUserInfo() {
			return createUserInfo;
		}

		public BaiseeUser getUpdateUserInfo() {
			return updateUserInfo;
		}

		public void setCreateUserInfo(BaiseeUser createUserInfo) {
			this.createUserInfo = createUserInfo;
		}

		public void setUpdateUserInfo(BaiseeUser updateUserInfo) {
			this.updateUserInfo = updateUserInfo;
		}

		public void setRoleLevel(int roleLevel) {
			this.roleLevel = roleLevel;
		}

		public int getRoleLevel() {
			return roleLevel;
		}
}