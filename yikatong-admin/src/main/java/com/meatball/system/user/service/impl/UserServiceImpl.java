/**
 * Project Name:meatball-admin
 * File Name:UserServiceImpl.java
 * Package Name:com.meatball.system.user.service.impl
 * Date:2018年1月13日下午2:30:23
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.system.user.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meatball.system.role.dao.SysRoleMapper;
import com.meatball.system.role.dao.SysUserRoleLinksMapper;
import com.meatball.system.role.model.SysRole;
import com.meatball.system.user.dao.SysUserMapper;
import com.meatball.system.user.model.SysUser;
import com.meatball.system.user.service.UserService;
import com.meatball.utils.MD5Util;
import com.meatball.vo.ResultMsg;

/**   
 * @Title: UserServiceImpl.java 
 * @Package com.meatball.system.user.service.impl 
 * @Description: TODO(用户管理) 
 * @author 張翔宇  
 * @date 2018年1月13日 下午2:30:23 
 * @version V1.0   
 */
@Service
public class UserServiceImpl implements UserService {
	@Resource
	private SysUserMapper mapper;
	@Resource
	private SysRoleMapper roleMapper;
	@Resource
	private SysUserRoleLinksMapper linksMapper;
	
	@Override
	public PageInfo<SysUser> table(SysUser record) {
		PageInfo<SysUser> list = PageHelper.startPage(record.getOffset(), record.getLimit()).doSelectPageInfo(() -> mapper.selectList(record));
		return list;
	}
	
	@Override
	public ResultMsg add(SysUser record, Long[] roles) {
		int status  = mapper.selectByAccount(record.getAccount());
		if(status > 0) {
			return new ResultMsg(401, "该用账号经存在，请重新设置。");
		} else {
			String salt = MD5Util.getRandomString(45);
			record.setPassword(MD5Util.md5(record.getPassword(), salt));
			record.setSalt(salt);
			record.setStatus(1);
			record.setCreatetime(new Date());
			mapper.insertSelective(record);
			// 保存与角色关联信息
			if(roles[0] != -1) {
				linksMapper.insertUserRoleLinks(record.getId(), roles);
			}
			return new ResultMsg(200, "新增成功。");
		}
		
	}
	
	@Override
	public SysUser info(Long id) {
		SysUser sysUser = mapper.selectByPrimaryKey(id);
		List<SysRole> roles = roleMapper.selectRoleByUserId(id);
		sysUser.setRolesVo(roles);
		return sysUser;
	}
	
	@Override
	public ResultMsg update(SysUser record, Long[] roles) {
		String msg = "";
		if(record.isResetPwd()) {
			String salt = MD5Util.getRandomString(45);
			record.setPassword(MD5Util.md5("88888888", salt));
			record.setSalt(salt);
			msg += "密码已经重置为[88888888]，请提醒用户尽快修改。";
		}
		mapper.updateByPrimaryKeySelective(record);
		// 删除角色关联信息
		linksMapper.deleteUserRoleLinksByUserId(record.getId());
		// 新增关联信息
		if(roles[0] != -1) {
			linksMapper.insertUserRoleLinks(record.getId(), roles);
		}
		return new ResultMsg(200, "更新成功。" + msg);
	}
	
	@Override
	public ResultMsg delete(Long id) {
//		mapper.deleteByPrimaryKey(id);
		// 删除角色关联信息
//		linksMapper.deleteUserRoleLinksByUserId(id);
		return new ResultMsg(200, "删除成功。");
	}
}
