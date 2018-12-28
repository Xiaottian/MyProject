/**
 * ProjectName:    MyProject
 * PackageName:    tk.mybatis.simple.mapper
 * FileName：      UserMapper.java
 * Copyright:      Copyright(C) 2018
 * Company:        北京神州泰岳软件股份有限公司
 * Author:         JIT
 * CreateDate:     2018/11/8 15:00
 */

package tk.mybatis.simple.mapper;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

import java.util.List;
import java.util.Map;

public interface UserMapper {
	/**
	 *  通过id查询用户
	 *
	 * @param id
	 * @return
	 */
	SysUser selectById(Long id);

	/**
	 * 查询全部用户
	 *
	 * @return
	 */
	List<SysUser> selectAll();

	/**
	 * 根据用户id获取角色信息
	 *
	 * @param userId
	 * @return
	 */
	List<SysRole> selectRolesByUserId(Long userId);

	/**
	 * 新增用户
	 *
	 * @param sysUser
	 * @return
	 */
	int insert(SysUser sysUser);

	/**
	 * 新增用户-使用useGeneratedKeys方式
	 *
	 * @param sysUser
	 * @return
	 */
	int insert2(SysUser sysUser);

	/**
	 * 新增用户-使用selectKey方式
	 *
	 * @param sysUser
	 * @return
	 */
	int insert3(SysUser sysUser);

	/**
	 * 根据主键更新
	 *
	 * @param sysUser
	 * @return
	 */
	int updateById(SysUser sysUser);

	/**
	 * 通过主键删除
	 *
	 * 也可以把参数改成SysUser实体
	 * @param id
	 * @return
	 */
	int deleteById(Long id);

	/**
	 * 根据用户id和角色的enabled状态获取用户的角色
	 *
	 * @param user
	 * @param role
	 * @return
	 */
	List<SysRole> selectRolesByUserIdAndRoleEnabled(@Param("user") SysUser user, @Param("role") SysRole role);

	/**
	 * 根据动态条件查询用户信息
	 *
	 * @param sysUser
	 * @return
	 */
	List<SysUser> selectByUser(SysUser sysUser);

	/**
	 * 根据主键更新
	 *
	 * @param sysUser
	 * @return
	 */
	int updateByIdSelective(SysUser sysUser);

	/**
	 * 根据用户id或用户名查询
	 *
	 * @param sysUser
	 * @return
	 */
	SysUser selectByIdOrUserName(SysUser sysUser);

	/**
	 * 根据用户id集合查询
	 *
	 * @param idList
	 * @return
	 */
	List<SysUser> selectByIdList(List<Long> idList);

	/**
	 * 根据用户id集合查询
	 *
	 * @param idArray
	 * @return
	 */
	List<SysUser> selectByIdList(Long[] idArray);

	/**
	 * 批量插入用户信息
	 *
	 * @param userList
	 * @return
	 */
	int insertList(List<SysUser> userList);

	/**
	 * 通过map更新列
	 *
	 * @param map
	 * @return
	 */
	int updateByMap(Map<String,Object> map);

	/**
	 * 根据用户id获取用户信息和用户的角色信息
	 *
	 * @param id
	 * @return
	 */
	SysUser selectUserAndRoleById(Long id);

	/**
	 * 根据用户id获取用户信息和用户的角色信息
	 *
	 * @param id
	 * @return
	 */
	SysUser selectUserAndRoleById2(Long id);

	/**
	 * 根据角色id查询用户和角色信息
	 *
	 * @return
	 */
	SysUser selectUserAndRoleByIdSelect(Long roleId);

	/**
	 * 获取所有的用户以及对应的所有角色
	 *
	 * @return
	 */
	List<SysUser> selectAllUserAndRoles();

	/**
	 * 使用存储过程查询用户信息
	 *
	 * @param user
	 */
	void selectUserById(SysUser user);

	/**
	 * 使用存储过程分页查询
	 *
	 * @param params
	 * @return
	 */
	List<SysUser> selectUserPage(Map<String,Object> params);

	/**
	 * 保存用户信息和角色关联信息
	 *
	 * @param user
	 * @param roleIds
	 * @return
	 */
	int insertUserAndRoles(@Param("user")SysUser user,@Param("roleIds")String roleIds);

	/**
	 * 根据用户id删除用户和用户的角色信息
	 *
	 * @param id
	 * @return
	 */
	int deleteUserById(Long id);
}
