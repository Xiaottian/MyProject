/**
 * ProjectName:    MyProject
 * PackageName:    tk.mybatis.simple.model
 * FileName：      CreateInfo.java
 * Copyright:      Copyright(C) 2018
 * Company:        北京神州泰岳软件股份有限公司
 * Author:         JIT
 * CreateDate:     2018/11/19 11:03
 */

package tk.mybatis.simple.model;

import java.util.Date;

/**
 * 创建信息
 */
public class CreateInfo {
	/**
	 * 创建人
	 */
	private String createBy;
	/**
	 * 创建时间
	 */
	private Date createTime;

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
