/**
 * Project Name:meatball-admin
 * File Name:RoleController.java
 * Package Name:com.meatball.system.role
 * Date:2018年1月14日下午9:31:24
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.system.role.controller;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.meatball.system.menu.service.MenuService;
import com.meatball.system.role.model.SysRole;
import com.meatball.system.role.service.RoleService;
import com.meatball.vo.ResultMsg;

/**   
 * @Title: RoleController.java 
 * @Package com.meatball.system.role 
 * @Description: TODO(系统管理-角色) 
 * @author 張翔宇  
 * @date 2018年1月14日 下午9:31:24 
 * @version V1.0   
 */
@Controller
@RequestMapping("/system/role")
public class RoleController {
	@Resource
	private RoleService service;
	@Resource
	private MenuService menuService;
	
	/**
	 * @Title: index 
	 * @Description: TODO(主页) 
	 * @return String    返回类型
	 */
	@GetMapping("/index")
	@RequiresPermissions("system:role:index")
	public String index(ModelMap map) {
		map.addAttribute("roleMenuTree", menuService.selectAllTreeMenu());
		return "system/role/role";
	}
	
	/**
	 * @Title: table 
	 * @Description: TODO(分页列表) 
	 * @param record
	 * @return PageInfo<SysRole>    返回类型
	 */
	@GetMapping("/table")
	@ResponseBody
	@RequiresPermissions("system:role:index")
	public PageInfo<SysRole> table(SysRole record) {
		return service.table(record);
	}
	
	/**
	 * @Title: add 
	 * @Description: TODO(新增) 
	 * @param record
	 * @return ResultMsg    返回类型
	 */
	@PostMapping("/")
	@ResponseBody
	@RequiresPermissions("system:role:add")
	public ResultMsg add(SysRole record) {
		return service.add(record);
	}
	
	/**
	 * @Title: info 
	 * @Description: TODO(详情) 
	 * @param id
	 * @return SysRole    返回类型
	 */
	@GetMapping("/{id}")
	@ResponseBody
	@RequiresPermissions("system:role:info")
	public SysRole info(@PathVariable Long id) {
		return service.info(id);
	}
	
	/**
	 * @Title: update 
	 * @Description: TODO(更新) 
	 * @param record
	 * @return ResultMsg    返回类型
	 */
	@PutMapping("/")
	@ResponseBody
	@RequiresPermissions("system:role:edit")
	public ResultMsg update(SysRole record) {
		return service.update(record);
	}
	
	/**
	 * @Title: del 
	 * @Description: TODO(删除) 
	 * @param id
	 * @return ResultMsg    返回类型
	 */
	@DeleteMapping("/{id}")
	@ResponseBody
	@RequiresPermissions("system:role:delete")
	public ResultMsg del(@PathVariable Long id) {
		return service.del(id);
	}
}
