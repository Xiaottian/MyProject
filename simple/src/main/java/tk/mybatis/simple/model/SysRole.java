package tk.mybatis.simple.model;


import tk.mybatis.simple.type.Enabled;

import java.util.Date;
import java.util.List;

public class SysRole {

  private long id;
  private String roleName;
  private Enabled enabled;
  private long createBy;
  private Date createTime;
  /**
   * 创建信息
   */
  private CreateInfo createInfo;
  /**
   * 用户信息
   */
  private SysUser user;

  /**
   * 角色包含的权限列表
   */
  private List<SysPrivilege> privilegeList;

  public List<SysPrivilege> getPrivilegeList() {
    return privilegeList;
  }

  public void setPrivilegeList(List<SysPrivilege> privilegeList) {
    this.privilegeList = privilegeList;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }


  public Enabled getEnabled() {
    return enabled;
  }

  public void setEnabled(Enabled enabled) {
    this.enabled = enabled;
  }

  public long getCreateBy() {
    return createBy;
  }

  public void setCreateBy(long createBy) {
    this.createBy = createBy;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public SysUser getUser() {
    return user;
  }

  public void setUser(SysUser user) {
    this.user = user;
  }

  public CreateInfo getCreateInfo() {
    return createInfo;
  }

  public void setCreateInfo(CreateInfo createInfo) {
    this.createInfo = createInfo;
  }
}
