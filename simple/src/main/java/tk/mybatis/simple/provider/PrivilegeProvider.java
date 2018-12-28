/**
 * ProjectName:    MyProject
 * PackageName:    tk.mybatis.simple.provider
 * FileName：      PrivilegeProvider.java
 * Copyright:      Copyright(C) 2018
 * Company:        北京神州泰岳软件股份有限公司
 * Author:         JIT
 * CreateDate:     2018/11/14 14:34
 */

package tk.mybatis.simple.provider;

import org.apache.ibatis.jdbc.SQL;

public class PrivilegeProvider {
	public String selectById(final Long id) {
		return new SQL() {
			{
				SELECT("id,privilege_name,privilege_url");
				FROM("sys_privilege");
				WHERE("id = #{id}");
			}

		}.toString();

	}

	public String selectById1(final Long id){
		return "select id,privilege_name,privilege_url from sys_privilege where id = #{id}";
	}
}
