/**
 * Project Name:meatball-admin
 * File Name:MenuController.java
 * Package Name:com.meatball.system.menu.controller
 * Date:2018年1月1日下午5:04:03
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.system.menu.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.meatball.component.OperateLog;
import com.meatball.system.menu.model.SysMenu;
import com.meatball.system.menu.service.MenuService;
import com.meatball.vo.ResultMsg;

/**   
 * @Title: MenuController.java 
 * @Package com.meatball.system.menu.controller 
 * @Description: TODO(菜单) 
 * @author 張翔宇  
 * @date 2018年1月1日 下午5:04:03 
 * @version V1.0   
 */
@Controller
@RequestMapping("/system/menu")
public class MenuController {
	@Resource
	private MenuService service;
	
	@GetMapping("/index")
	@RequiresPermissions("system:menu:index")
	public String index() {
		return "system/menu/menu";
	}
	
	/**
	 * @Title: table 
	 * @Description: TODO(分页列表) 
	 * @param record
	 * @return PageInfo<SysMenu>    返回类型
	 */
	@GetMapping("/table")
	@ResponseBody
	@RequiresPermissions("system:menu:index")
	public PageInfo<SysMenu> table(SysMenu record) {
		return service.table(record);
	}
	
	/**
	 * @Title: selectAllMenu 
	 * @Description: TODO(返回菜单集合) 
	 * @return List<SysMenu>    返回类型 
	 */
	@PostMapping("/menus")
	@ResponseBody
	@RequiresPermissions("system:menu:index")
	public List<SysMenu> selectAllTreeMenu() {
		return service.selectAllTreeMenu();
	}
	
	/**
	 * @Title: add 
	 * @Description: TODO(新增) 
	 * @param record
	 * @return ResultMsg    返回类型
	 */
	@PostMapping("/")
	@ResponseBody
	@OperateLog("新增菜单")
	@RequiresPermissions("system:menu:add")
	public ResultMsg add(SysMenu record) {
		return service.add(record);
	}
	
	/**
	 * @Title: updata 
	 * @Description: TODO(更新菜单) 
	 * @param record
	 * @return ResultMsg    返回类型
	 */
	@PutMapping("/")
	@ResponseBody
	@OperateLog("更新菜单")
	@RequiresPermissions("system:menu:edit")
	public ResultMsg updata(SysMenu record) {
		return service.updata(record);
	}
	
	/**
	 * @Title: info 
	 * @Description: TODO(详情) 
	 * @param id
	 * @return SysMenu    返回类型
	 */
	@GetMapping("/{id}")
	@ResponseBody
	@RequiresPermissions("system:menu:info")
	public SysMenu info(@PathVariable Long id) {
		return service.info(id);
	}
	
	/**
	 * @Title: del 
	 * @Description: TODO(删除菜单及其子菜单) 
	 * @param id
	 * @return ResultMsg    返回类型
	 */
	@DeleteMapping("/{id}")
	@ResponseBody
	@RequiresPermissions("system:menu:delete")
	public ResultMsg del(@PathVariable Long id) {
		return service.del(id);
	}
}
