package tk.mybatis.simple.model;


public class SysRolePrivilege {

  private long roleId;
  private long privilegeId;


  public long getRoleId() {
    return roleId;
  }

  public void setRoleId(long roleId) {
    this.roleId = roleId;
  }


  public long getPrivilegeId() {
    return privilegeId;
  }

  public void setPrivilegeId(long privilegeId) {
    this.privilegeId = privilegeId;
  }

}
