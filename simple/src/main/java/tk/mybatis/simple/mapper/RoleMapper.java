/**
 * ProjectName:    MyProject
 * PackageName:    tk.mybatis.simple.mapper
 * FileName：      RoleMapper.java
 * Copyright:      Copyright(C) 2018
 * Company:        北京神州泰岳软件股份有限公司
 * Author:         JIT
 * CreateDate:     2018/11/8 15:01
 */

package tk.mybatis.simple.mapper;

import org.apache.ibatis.annotations.*;
import tk.mybatis.simple.model.SysRole;

import java.util.List;

public interface RoleMapper {

	@Select({"select id,role_name,enabled,create_by,create_time","from sys_role","where id = #{id}"})
	SysRole selectById(Long id);

	@Results(id = "roleResultMap",value = {
			@Result(property = "id",column = "id",id = true),
			@Result(property = "roleName",column = "role_name"),
			@Result(property = "enabled",column = "enabled"),
			@Result(property = "createBy",column = "create_by"),
			@Result(property = "createTime",column = "create_time")
	})
	@Select("select id,role_name,enabled,create_by,create_time from sys_role where id = #{id}")
	SysRole selectById2(Long id);

	@ResultMap("roleResultMap")
	@Select("select * from sys_role")
	List<SysRole> selectAll();

	@Insert({"insert into sys_role(id,role_name,enabled,create_by,create_time)",
	"values(#{id},#{roleName},#{enabled},#{createBy},#{createTime,jdbcType=TIMESTAMP})"})
	int insert(SysRole sysRole);

	@Insert({"insert into sys_role(role_name,enabled,create_by,create_time)",
			"values(#{roleName},#{enabled},#{createBy},#{createTime,jdbcType=TIMESTAMP})"})
	@Options(useGeneratedKeys = true,keyProperty = "id")
	int insert2(SysRole sysRole);

	@Insert({"insert into sys_role(id,role_name,enabled,create_by,create_time)",
			"values(#{id},#{roleName},#{enabled},#{createBy},#{createTime,jdbcType=TIMESTAMP})"})
	@SelectKey(statement = "select LAST_INSERT_ID()",keyProperty = "id",resultType = Long.class,before = false)
	int insert3(SysRole sysRole);

	@Update("update sys_role set role_name = #{roleName},enabled = #{enabled},create_by = #{createBy},create_time = #{createTime,jdbcType=TIMESTAMP} WHERE id = #{id}")
	int updateById(SysRole sysRole);

	@Delete("delete from sys_role where id = #{id}")
	int deleteById(SysRole sysRole);

	/**
	 * 通过角色id查询角色信息
	 *
	 * @param id
	 * @return
	 */
	SysRole selectRoleById(Long id);

	/**
	 * 查询所有的角色和权限信息
	 *
	 * @return
	 */
	List<SysRole> selectAllRoleAndPrivileges();

	List<SysRole> selectRoleByUserId(Long id);

	/***
	 * 根据用户ID获取用户的角色信息
	 *
	 * @param userId
	 * @return
	 */
	List<SysRole> selectRoleByUserIdChoose(Long userId);

}
