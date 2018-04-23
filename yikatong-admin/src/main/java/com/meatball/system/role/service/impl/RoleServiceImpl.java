/**
 * Project Name:meatball-admin
 * File Name:RoleServiceImpl.java
 * Package Name:com.meatball.system.role.service.impl
 * Date:2018年1月29日下午12:44:48
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.system.role.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meatball.shiro.MeatballRealm;
import com.meatball.system.menu.dao.SysMenuMapper;
import com.meatball.system.menu.model.SysMenu;
import com.meatball.system.role.dao.SysRoleMapper;
import com.meatball.system.role.dao.SysUserRoleLinksMapper;
import com.meatball.system.role.model.SysRole;
import com.meatball.system.role.service.RoleService;
import com.meatball.system.user.model.SysUser;
import com.meatball.vo.ResultMsg;

/**   
 * @Title: RoleServiceImpl.java 
 * @Package com.meatball.system.role.service.impl 
 * @Description: TODO(系统管理-角色) 
 * @author 張翔宇  
 * @date 2018年1月29日 下午12:44:48 
 * @version V1.0   
 */
@Service
public class RoleServiceImpl implements RoleService {
	@Resource
	private SysRoleMapper mapper;
	@Resource
	private SysUserRoleLinksMapper linksMapper;
	@Resource
	private SysMenuMapper menuMapper;
	@Resource
	private MeatballRealm meatballRealm;
	
	@Override
	public PageInfo<SysRole> table(SysRole record) {
		PageInfo<SysRole> page = PageHelper.startPage(record.getOffset(), record.getLimit()).doSelectPageInfo(() -> mapper.selectPage(record));
		return page;
	}
	
	@Override
	public List<SysRole> findRoles() {
		return mapper.selectPage(null);
	}

	@Override
	public ResultMsg add(SysRole record) {
		record.setCreateTime(new Date());
		mapper.insertSelective(record);
		return new ResultMsg(200, "新增成功。");
	}
	
	@Override
	public SysRole info(Long id) {
		SysRole role = mapper.selectByPrimaryKey(id);
		List<SysMenu> menus = menuMapper.selectAllTreeMenu();
		menus = this.checkedMenus(menus, role.getRoleSign());
		role.setMenus(menus);
		return role;
	}
	
	@Override
	public ResultMsg update(SysRole record) {
		record.setUpdateTime(new Date());
		mapper.updateByPrimaryKeySelective(record);
		// 更新角色信息成功后刷新session中权限
		SysUser sysUser = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("sysUser");
		List<SysRole> roles = mapper.selectRoleByUserId(sysUser.getId());
		SecurityUtils.getSubject().getSession().setAttribute("roles", roles);
		
		return new ResultMsg(200, "更新成功。");
	}

	@Override
	public ResultMsg del(Long id) {
		mapper.deleteByPrimaryKey(id);
		linksMapper.deleteUserRoleLinksByRoleId(id);
		return new ResultMsg(200, "删除成功。");
	}
	
	/**
	 * @Title: deleteNullChildren 
	 * @Description: TODO(递归清除空children) 
	 * @param menus
	 * @return List<SysMenu>    返回类型
	 */
	private List<SysMenu> checkedMenus(List<SysMenu> menus, String perms) {
		for(SysMenu info : menus) {
			if(perms.contains(info.getPerms())) {
				info.setChecked(true);
			}
			if(info.getChildren().isEmpty()) {
				info.setChildren(null);
			} else {
				this.checkedMenus(info.getChildren(), perms);
			}
		}
		return menus;
	}
}
