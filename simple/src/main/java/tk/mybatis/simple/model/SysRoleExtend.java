/**
 * ProjectName:    MyProject
 * PackageName:    tk.mybatis.simple.model
 * FileName：      SysRoleExtend.java
 * Copyright:      Copyright(C) 2018
 * Company:        北京神州泰岳软件股份有限公司
 * Author:         JIT
 * CreateDate:     2018/11/13 14:25
 */

package tk.mybatis.simple.model;

public class SysRoleExtend extends SysRole {
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
