package com.meatball.system.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.meatball.system.user.model.SysUser;

public interface SysUserMapper {
	int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
    
    SysUser selectByProperty(SysUser record);
    
    int selectByAccount(@Param("account") String account);
    
    List<SysUser> selectList(SysUser record);
}