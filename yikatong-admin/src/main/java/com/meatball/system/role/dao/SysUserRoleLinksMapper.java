package com.meatball.system.role.dao;

import org.apache.ibatis.annotations.Param;

import com.meatball.system.role.model.SysUserRoleLinks;

public interface SysUserRoleLinksMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUserRoleLinks record);

    int insertSelective(SysUserRoleLinks record);

    SysUserRoleLinks selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserRoleLinks record);

    int updateByPrimaryKey(SysUserRoleLinks record);
    
    int insertUserRoleLinks(@Param("userId") Long userId, @Param("roles") Long[] roles);
    
    int deleteUserRoleLinksByUserId(@Param("userId") Long userId);
    
    int deleteUserRoleLinksByRoleId(@Param("roleId") Long roleId);
}