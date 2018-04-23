package com.meatball.system.menu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.meatball.system.menu.model.SysMenu;

public interface SysMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
    
    List<SysMenu> selectAllMenu();
    
    List<SysMenu> selectAllTreeMenu();
    
    int deleteByPid(Long id);
    
    List<SysMenu> findPageList(SysMenu record);
    
    List<SysMenu> selectMenusByPerms(@Param("_perms") String[] _perms);
}