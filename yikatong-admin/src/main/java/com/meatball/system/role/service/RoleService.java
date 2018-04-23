/**
 * Project Name:meatball-admin
 * File Name:RoleService.java
 * Package Name:com.meatball.system.role.service
 * Date:2018年1月29日下午12:43:45
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.system.role.service;
/**   
 * @Title: RoleService.java 
 * @Package com.meatball.system.role.service 
 * @Description: TODO(系统管理-角色) 
 * @author 張翔宇  
 * @date 2018年1月29日 下午12:43:45 
 * @version V1.0   
 */

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.meatball.system.role.model.SysRole;
import com.meatball.vo.ResultMsg;

public interface RoleService {
	
	/**
	 * @Title: table 
	 * @Description: TODO(分页列表) 
	 * @param record
	 * @return PageInfo<SysRole>    返回类型
	 */
	public PageInfo<SysRole> table(SysRole record);
	
	/**
	 * @Title: roles 
	 * @Description: TODO(获取所有角色) 
	 * @return List<SysRole>    返回类型
	 */
	public List<SysRole> findRoles();
	
	/**
	 * @Title: add 
	 * @Description: TODO(新增) 
	 * @param record
	 * @return ResultMsg    返回类型
	 */
	public ResultMsg add(SysRole record);
	
	/**
	 * @Title: info 
	 * @Description: TODO(详情) 
	 * @param id
	 * @return
	 * @return SysRole    返回类型
	 */
	public SysRole info(Long id);
	
	/**
	 * @Title: update 
	 * @Description: TODO(更新) 
	 * @param record
	 * @return ResultMsg    返回类型
	 */
	public ResultMsg update(SysRole record);
	
	/**
	 * @Title: del 
	 * @Description: TODO(删除) 
	 * @param id
	 * @return ResultMsg    返回类型
	 */
	public ResultMsg del(Long id);
}
