/**
 * ProjectName:    MyProject
 * PackageName:    tk.mybatis.simple.mapper
 * FileName：      RoleMapperTest.java
 * Copyright:      Copyright(C) 2018
 * Company:        北京神州泰岳软件股份有限公司
 * Author:         JIT
 * CreateDate:     2018/11/14 10:20
 */

package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysPrivilege;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.type.Enabled;

import javax.management.relation.Role;
import java.util.Date;
import java.util.List;

public class RoleMapperTest extends BaseMapperTest {

	@Test
	public void testSelectById() {
		//获取sqlSession
		SqlSession sqlSession = getSqlSession();
		try {
			//获取RoleMapper接口
			RoleMapper RoleMapper = sqlSession.getMapper(RoleMapper.class);
			//调用selectById方法，查询id=1的用户
			SysRole sysRole = RoleMapper.selectById(1L);
			//Role不为空
			Assert.assertNotNull(sysRole);
			//roleName = 管理员
			Assert.assertNotNull("管理员", sysRole.getRoleName());
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testSelectById2() {
		//获取sqlSession
		SqlSession sqlSession = getSqlSession();
		try {
			//获取RoleMapper接口
			RoleMapper RoleMapper = sqlSession.getMapper(RoleMapper.class);
			//调用selectById2方法，查询id=1的用户
			SysRole sysRole = RoleMapper.selectById2(1L);
			//Role不为空
			Assert.assertNotNull(sysRole);
			//roleName = 管理员
			Assert.assertNotNull("管理员", sysRole.getRoleName());
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testSelectAll() {
		//获取sqlSession
		SqlSession sqlSession = getSqlSession();
		try {
			//获取RoleMapper接口
			RoleMapper RoleMapper = sqlSession.getMapper(RoleMapper.class);
			//调用selectAll方法，查询所有的用户
			List<SysRole> roleList = RoleMapper.selectAll();
			//结果不为空
			Assert.assertNotNull(roleList);
			//角色数量大于0个
			Assert.assertTrue(roleList.size() > 0);
			//验证下划线字段是否映射成功
			Assert.assertNotNull(roleList.get(0).getRoleName());
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testInsert() {
		//获取sqlSession
		SqlSession sqlSession = getSqlSession();
		try {
			//获取RoleMapper接口
			RoleMapper RoleMapper = sqlSession.getMapper(RoleMapper.class);
			//创建一个Role对象
			SysRole role = new SysRole();
			role.setRoleName("test");
			//role.setEnabled(1L);
			//role.setCreateBy(1);
			//role.setCreateTime(new Date());
			//将新建的对象输入到数据库中，特别注意这里的返回值result是执行SQL影响的行数
			int result = RoleMapper.insert(role);
			// 只插入一条数据
			Assert.assertEquals(1, result);
			// id为null，没有给id赋值，并且没有配置回写id的值
			Assert.assertNull(role.getId());
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testInsert2() {
		//获取sqlSession
		SqlSession sqlSession = getSqlSession();
		try {
			//获取RoleMapper接口
			RoleMapper RoleMapper = sqlSession.getMapper(RoleMapper.class);
			//创建一个Role对象
			SysRole role = new SysRole();
			role.setRoleName("test2");
			//role.setEnabled(1L);
			//role.setCreateBy(1);
			//role.setCreateTime(new Date());
			//将新建的对象输入到数据库中，特别注意这里的返回值result是执行SQL影响的行数
			int result = RoleMapper.insert2(role);
			System.out.println(role.getId());
			// 只插入一条数据
			Assert.assertEquals(1, result);
			// id为null，没有给id赋值，并且没有配置回写id的值
			Assert.assertNotNull(role.getId());
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testUpdateById() {
		SqlSession sqlSession = getSqlSession();
		try {
			RoleMapper RoleMapper = sqlSession.getMapper(RoleMapper.class);
			//从数据库查询一个Role对象
			SysRole Role = RoleMapper.selectById(2L);
			//当前roleName为管理员
			Assert.assertEquals(Enabled.enabled, Role.getEnabled());
			Role.setEnabled(Enabled.disabled);

			//更新数据，返回的result是执行的sql影响的行数
			int result = RoleMapper.updateById(Role);
			// 只更新一条数据
			Assert.assertEquals(1, result);

		} finally {
			//默认的SqlSession是不自动提交的
			//不手动执行commit不会提交到数据库
			sqlSession.rollback();
			sqlSession.close();
		}
	}

	@Test
	public void testDeleteById() {
		SqlSession sqlSession = getSqlSession();
		try {
			RoleMapper RoleMapper = sqlSession.getMapper(RoleMapper.class);
			//从数据库查询一个Role对象，根据id=1查询
			SysRole Role = RoleMapper.selectById(1L);
			//现在还能查询出Role对象
			Assert.assertNotNull(Role);
			//调用方法删除
			Assert.assertEquals(1, RoleMapper.deleteById(Role));
			//再次查询，这时应该没有值，为null
			Assert.assertNull(RoleMapper.selectById(1L));

			//使用SysRole参数在进行一次测试，根据id=1001查询
/*			SysRole Role2 = RoleMapper.selectById(1001L);
			//现在还能查询出Role对象
			Assert.assertNotNull(Role2);
			//调用方法删除
			Assert.assertEquals(1,RoleMapper.deleteById(Role2));
			//再次查询，这时应该没有值，为null
			Assert.assertNull(RoleMapper.selectById(1001L));*/
		} finally {
			//默认的SqlSession是不自动提交的
			//不手动执行commit不会提交到数据库
			sqlSession.rollback();
			sqlSession.close();
		}
	}


	@Test
	public void testSelectAllRoleAndPrivileges() {
		SqlSession sqlSession = getSqlSession();
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			List<SysRole> roleList = roleMapper.selectAllRoleAndPrivileges();
			System.out.println("角色数：" + roleList.size());
			for (SysRole role : roleList) {
				System.out.println("角色名：" + role.getRoleName());
				for (SysPrivilege privilege : role.getPrivilegeList()) {
					System.out.println("权限名：" + privilege.getPrivilegeName());
				}
			}

		} finally {
			sqlSession.close();
		}

	}

	@Test
	public void testSelectRoleByUserIdChoose() {
		//获取SqlSession
		SqlSession sqlSession = getSqlSession();
		try {
			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			SysRole role = roleMapper.selectById(2L);
			//role.setEnabled(0);
			roleMapper.updateById(role);
			//获取用户1的角色
			List<SysRole> roleList = roleMapper.selectRoleByUserIdChoose(1L);
			for (SysRole r : roleList) {
				System.out.println("角色名：" + r.getRoleName());
				if (r.getId() == 1L) {
					//第一个角色存在权限信息
					Assert.assertNotNull(r.getPrivilegeList());
				} else if (r.getId() == 2L) {
					// 第二个角色的权限为null
					Assert.assertNull(r.getPrivilegeList());
					continue;
				}
				for (SysPrivilege privilege : r.getPrivilegeList()) {
					System.out.println("权限名：" + privilege.getPrivilegeName());
				}
			}
		} finally {
			sqlSession.close();
		}
	}
}
