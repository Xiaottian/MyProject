/**
 * ProjectName:    MyProject
 * PackageName:    tk.mybatis.simple.mapper
 * FileName：      UserMapperTest.java
 * Copyright:      Copyright(C) 2018
 * Company:        北京神州泰岳软件股份有限公司
 * Author:         JIT
 * CreateDate:     2018/11/9 11:27
 */

package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.mapper.impl.MyMapperProxy;
import tk.mybatis.simple.model.SysPrivilege;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

import java.lang.reflect.Proxy;
import java.util.*;

public class UserMapperTest extends BaseMapperTest {

	@Test
	public void testSelectById() {
		//获取sqlSession
		SqlSession sqlSession = getSqlSession();
		try {
			//获取UserMapper接口
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//调用selectById方法，查询id=1的用户
			SysUser sysUser = userMapper.selectById(1L);
			//user不为空
			Assert.assertNotNull(sysUser);
			//userName = admin
			Assert.assertNotNull("admin", sysUser.getUserName());
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testSelectAll() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//调用selectAll方法查询所有用户
			List<SysUser> userList = userMapper.selectAll();
			//结果不为空
			Assert.assertNotNull(userList);
			//用户数量大于0个
			Assert.assertNotNull(userList.size() > 0);
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testSelectRolesByUserId() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//调用selectAll方法查询所有用户
			List<SysRole> roleList = userMapper.selectRolesByUserId(1L);
			//结果不为空
			Assert.assertNotNull(roleList);
			//用户数量大于0个
			Assert.assertNotNull(roleList.size() > 0);
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testInsert() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//创建一个user对象
			SysUser user = new SysUser();
			//user.setId(10);
			user.setUserName("test1");
			user.setUserPassword("123456");
			user.setUserEmail("test@mybatis.tk");
			user.setUserInfo("test info");
			//正常情况下应该读入一张图片到byte数组中
			user.setHeadImg(new byte[]{1, 2, 3});
			user.setCreateTime(new Date());
			//将新建的对象输入到数据库中，特别注意这里的返回值result是执行SQL影响的行数
			int result = userMapper.insert(user);
			// 只插入一条数据
			Assert.assertEquals(1, result);
			// id为null，没有给id赋值，并且没有配置回写id的值
			Assert.assertNull(user.getId());
		} finally {
			//默认的SqlSession是不自动提交的
			//不手动执行commit不会提交到数据库
			sqlSession.rollback();
			sqlSession.close();
		}
	}

	@Test
	public void testInsert2() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//创建一个user对象
			SysUser user = new SysUser();
			//user.setId(10);
			user.setUserName("test1");
			user.setUserPassword("123456");
			user.setUserEmail("test@mybatis.tk");
			user.setUserInfo("test info");
			//正常情况下应该读入一张图片到byte数组中
			user.setHeadImg(new byte[]{1, 2, 3});
			user.setCreateTime(new Date());
			//将新建的对象输入到数据库中，特别注意这里的返回值result是执行SQL影响的行数
			int result = userMapper.insert2(user);
			// 只插入一条数据
			Assert.assertEquals(1, result);
			// id为null，没有给id赋值，并且没有配置回写id的值
			Assert.assertNotNull(user.getId());
		} finally {
			//默认的SqlSession是不自动提交的
			//不手动执行commit不会提交到数据库
			sqlSession.rollback();
			sqlSession.close();
		}
	}

	@Test
	public void testUpdateById() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//从数据库查询一个user对象
			SysUser user = userMapper.selectById(1L);
			//当前userName为admin
			Assert.assertEquals("admin", user.getUserName());
			//修改用户名
			user.setUserName("admin_test");
			//修改邮箱
			user.setUserEmail("test@mybatis.tk");
			//更新数据，返回的result是执行的sql影响的行数
			int result = userMapper.updateById(user);
			// 只更新一条数据
			Assert.assertEquals(1, result);
			user = userMapper.selectById(1L);
			// id为null，没有给id赋值，并且没有配置回写id的值
			Assert.assertEquals("admin_test", user.getUserName());
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
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//从数据库查询一个user对象，根据id=1查询
			SysUser user = userMapper.selectById(1L);
			//现在还能查询出user对象
			Assert.assertNotNull(user);
			//调用方法删除
			Assert.assertEquals(1, userMapper.deleteById(1L));
			//再次查询，这时应该没有值，为null
			Assert.assertNull(userMapper.selectById(1L));

			//使用SysUser参数在进行一次测试，根据id=1001查询
/*			SysUser user2 = userMapper.selectById(1001L);
			//现在还能查询出user对象
			Assert.assertNotNull(user2);
			//调用方法删除
			Assert.assertEquals(1,userMapper.deleteById(user2));
			//再次查询，这时应该没有值，为null
			Assert.assertNull(userMapper.selectById(1001L));*/
		} finally {
			//默认的SqlSession是不自动提交的
			//不手动执行commit不会提交到数据库
			sqlSession.rollback();
			sqlSession.close();
		}
	}

	@Test
	public void testSelectRolesByUserIdAndRoleEnabled() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//调用方法查询用户的角色
			SysUser user = new SysUser();
			user.setId(1L);
			SysRole role = new SysRole();
			//role.setEnabled(1L);
			List<SysRole> userList = userMapper.selectRolesByUserIdAndRoleEnabled(user, role);
			//结果不为空
			Assert.assertNotNull(userList);
			//角色数量大于0个
			Assert.assertTrue(userList.size() > 0);
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testMyMapperProxy() {
		//获取SqlSession
		SqlSession sqlSession = getSqlSession();
		MyMapperProxy userMapperProxy = new MyMapperProxy(UserMapper.class, sqlSession);
		UserMapper userMapper = (UserMapper) Proxy.newProxyInstance(
				Thread.currentThread().getContextClassLoader(),
				new Class[]{UserMapper.class}, userMapperProxy);
		//调用selectAll方法
		List<SysUser> user = userMapper.selectAll();
	}

	@Test
	public void testSelectByUser() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			// 只查询用户名
			SysUser query = new SysUser();
			query.setUserName("ad");
			List<SysUser> userList = userMapper.selectByUser(query);
			Assert.assertTrue(userList.size() > 0);

			// 只查询用户邮箱时
			query = new SysUser();
			query.setUserEmail("test@mybatis.tk");
			userList = userMapper.selectByUser(query);
			Assert.assertTrue(userList.size() > 0);

			//当同时查询用户名和邮箱时
			query = new SysUser();
			query.setUserName("ad");
			query.setUserEmail("test@mybatis.tk");
			userList = userMapper.selectByUser(query);
			//由于没有同时符合这两个条件的用户，因此查询结果为0
			Assert.assertTrue(userList.size() == 0);
		} finally {
			sqlSession.close();
		}
	}


	@Test
	public void testUpdateByIdSelective() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			// 创建一个新的user对象
			SysUser user = new SysUser();
			// 更新id =1 的用户
			user.setId(1L);
			//修改邮箱
			user.setUserEmail("test@mybatis.tk");
			//更新邮箱，特别注意，这里的返回值result是执行SQL影响的行数
			int result = userMapper.updateByIdSelective(user);
			// 只更新一条数据
			Assert.assertEquals(1, result);
			//根据当前id查询修改后的数据
			user = userMapper.selectById(1L);
			//修改后的名字保持不变，但是邮箱变成了新的
			Assert.assertEquals("admin", user.getUserName());
			Assert.assertEquals("test@mybatis.tk", user.getUserEmail());
		} finally {
			sqlSession.close();
		}
	}


	@Test
	public void testInsert2Selective() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			// 创建一个user对象
			SysUser user = new SysUser();
			user.setUserName("test-selective");
			user.setUserPassword("123456");
			user.setUserInfo("test info");
			user.setCreateTime(new Date());
			//插入数据库
			userMapper.insert2(user);
			//获取插入的数据
			user = userMapper.selectById(user.getId());
			Assert.assertEquals("test@mybatis.tk", user.getUserEmail());
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testSelectByIdOrUserName() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			// 只查询用户名
			SysUser query = new SysUser();
			query.setId(1L);
			query.setUserName("admin");
			SysUser user = userMapper.selectByIdOrUserName(query);
			Assert.assertNotNull(user);

			// 当没有id时
			query.setId(null);
			user = userMapper.selectByIdOrUserName(query);
			Assert.assertNotNull(user);

			//当id和name都为空时
			query.setUserName(null);
			user = userMapper.selectByIdOrUserName(query);
			Assert.assertNull(user);
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testSelectByIdList() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List idList = new ArrayList<Long>();
			idList.add(1L);
			idList.add(1001L);
			//业务逻辑中必须校验idList.size > 0
			List<SysUser> list = userMapper.selectByIdList(idList);
			Assert.assertEquals(2, list.size());
		} finally {
			//不要忘记关闭SqlSession
			sqlSession.close();
		}
	}

	@Test
	public void testInsertList() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//创建一个user对象
			List<SysUser> userList = new ArrayList<>();
			for (int i = 0; i < 2; i++) {
				SysUser user = new SysUser();
				user.setUserName("test" + i);
				user.setUserPassword("123456");
				user.setUserEmail("test@mybatis.tk");
				userList.add(user);
			}
			//将新建的对象批量插入数据库中
			//特别注意，这里返回值result是执行SQL影响的行数
			int result = userMapper.insertList(userList);
			for (SysUser user : userList) {
				System.out.println(user.getId());
			}
			Assert.assertEquals(2, result);
		} finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}

	@Test
	public void testUpdateByMap() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			Map<String, Object> map = new HashMap<>();
			//查询条件，同样也是更新字段，必须保证该值存在
			map.put("id", 1L);
			//要更新的其他字段
			map.put("user_email", "test@mybatis.tk");
			map.put("user_password", "12345678");
			//更新数据
			userMapper.updateByMap(map);
			//根据当前id查询修改后的数据
			SysUser user = userMapper.selectById(1L);
			Assert.assertEquals("test@mybatis.tk", user.getUserEmail());
		} finally {
			sqlSession.rollback();
			sqlSession.close();
		}
	}

	@Test
	public void testSelectUserAndRoleById() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//注意，在测试数据中，id=1L的用户有两个角色，不适合此例子
			//这里使用只有一个角色的用户(id=1001L)
			SysUser user = userMapper.selectUserAndRoleById(1001L);
			//user不为空
			Assert.assertNotNull(user);
			//user.role也不为空
			Assert.assertNotNull(user.getRole());
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testSelectUserAndRoleById2() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//注意，在测试数据中，id=1L的用户有两个角色，不适合此例子
			//这里使用只有一个角色的用户(id=1001L)
			SysUser user = userMapper.selectUserAndRoleById2(1001L);
			//user不为空
			Assert.assertNotNull(user);
			//user.role也不为空
			Assert.assertNotNull(user.getRole());
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testSelectUserAndRoleByIdSelect() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//使用只有一个角色的用户(id =1001L)
			SysUser user = userMapper.selectUserAndRoleByIdSelect(1001L);
			//user不为空
			Assert.assertNotNull(user);
			//user.role也不为空
			System.out.println("调用user.getRole()");
			Assert.assertNotNull(user.getRole());
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testselectAllUserAndRole() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<SysUser> userList = userMapper.selectAllUserAndRoles();
			System.out.println("用户数：" + userList.size());
			for (SysUser user : userList) {
				System.out.println("用户名：" + user.getUserName());
				for (SysRole role : user.getRoleList()) {
					System.out.println("角色名：" + role.getRoleName());
					for (SysPrivilege privilege : role.getPrivilegeList()) {
						System.out.println("权限名：" + privilege.getPrivilegeName());
					}
				}
			}
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 测试调用存储过程查询用户信息
	 */
	@Test
	public void testSelectUserById() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = new SysUser();
			user.setId(1L);
			userMapper.selectUserById(user);
			Assert.assertNotNull(user.getUserName());
			System.out.println("用户名：" + user.getUserName());
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testSelectUserPage() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			Map<String, Object> params = new HashMap<>();
			params.put("userName", "ad");
			params.put("offset", "0");
			params.put("limit", 10);
			List<SysUser> userList = userMapper.selectUserPage(params);
			Long total = (Long) params.get("total");
			System.out.println("总数：" + total);
			for (SysUser user : userList) {
				System.out.println("用户名：" + user.getUserName());
			}
		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testInsertAndDelete() {
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			SysUser user = new SysUser();
			user.setUserName("test1");
			user.setUserPassword("123456");
			user.setUserEmail("test@mybatis.tk");
			user.setUserInfo("test info");
			user.setHeadImg(new byte[]{1, 2, 3});
			//插入用户信息和角色关联信息
			userMapper.insertUserAndRoles(user, "1,2");
			Assert.assertNotNull(user.getId());
			Assert.assertNotNull(user.getCreateTime());
			//执行下面的commit后再查看数据库中的数据
			//sqlSession.commit();
			userMapper.deleteUserById(user.getId());
		} finally {
			sqlSession.close();
		}
	}
}
