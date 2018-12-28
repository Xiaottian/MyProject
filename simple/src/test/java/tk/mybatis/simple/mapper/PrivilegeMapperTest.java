/**
 * ProjectName:    MyProject
 * PackageName:    tk.mybatis.simple.mapper
 * FileName：      PrivilegeMapperTest.java
 * Copyright:      Copyright(C) 2018
 * Company:        北京神州泰岳软件股份有限公司
 * Author:         JIT
 * CreateDate:     2018/11/14 14:41
 */

package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysPrivilege;

public class PrivilegeMapperTest extends BaseMapperTest {

	@Test
	public void testSelectById(){
		//获取sqlSession
		SqlSession sqlSession = getSqlSession();
		try {
			//获取PrivilegeMapper接口
			PrivilegeMapper PrivilegeMapper = sqlSession.getMapper(PrivilegeMapper.class);
			//调用selectById方法，查询id=1的用户
			SysPrivilege sysPrivilege = PrivilegeMapper.selectById(1L);
			//Privilege不为空
			Assert.assertNotNull(sysPrivilege);
			//PrivilegeName = 用户管理
			Assert.assertNotNull("用户管理", sysPrivilege.getPrivilegeName());
		} finally {
			sqlSession.close();
		}
	}
}
