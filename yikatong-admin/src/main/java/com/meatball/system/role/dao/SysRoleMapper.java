/**
 * Project Name:meatball-admin
 * File Name:RoleServiceImpl.java
 * Package Name:com.meatball.system.role.service.impl
 * Date:2018年1月29日下午12:44:48
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.system.role.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.meatball.system.role.model.SysRole;

/**   
 * @Title: RoleServiceImpl.java 
 * @Package com.meatball.system.role.service.impl 
 * @Description: TODO(系统管理-角色) 
 * @author 張翔宇  
 * @date 2018年1月29日 下午12:44:48 
 * @version V1.0   
 */
public interface SysRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
    
    List<SysRole> selectPage(SysRole record);
    
    List<SysRole> selectRoleByUserId(@Param("userId") Long userId);
}