/** 
 * Project Name:meatball-admin 
 * File Name:LoginController.java 
 * Package Name:com.meatball.login.controller 
 * Date:2017年10月6日上午12:28:08 
 * Copyright (c) 2017, zhang.xiangyu@foxmail.com All Rights Reserved. 
 */  
package com.meatball.system.user.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.meatball.system.role.service.RoleService;
import com.meatball.system.user.model.SysUser;
import com.meatball.system.user.service.UserService;
import com.meatball.vo.ResultMsg;

/**
 * @ClassName: UserController 
 * @Description: TODO(用户管理) 
 * @author 張翔宇
 * @date 2017年10月6日 上午2:52:14
 */
@Controller
@RequestMapping("/system/user")
public class UserController {
	@Resource
	private UserService service;
	@Resource
	private RoleService roleService;
	
	/**
	 * @Title: index 
	 * @Description: TODO(用户管理首页) 
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	@GetMapping("/index")
	@RequiresPermissions("system:user:index")
	public String index(ModelMap map) {
		map.addAttribute("allRoles", roleService.findRoles());
		return "system/user/user";
	}
	
	/**
	 * @Title: table 
	 * @Description: TODO(表格数据) 
	 * @param page
	 * @return PageInfo<SysUser>    返回类型
	 */
	@GetMapping("/table")
	@ResponseBody
	@RequiresPermissions("system:user:index")
	public PageInfo<SysUser> table(SysUser record) {
		return service.table(record);
	}
	
	/**
	 * @Title: add 
	 * @Description: TODO(新增用户) 
	 * @param record
	 * @return ResultMsg    返回类型
	 */
	@PostMapping("/")
	@ResponseBody
	@RequiresPermissions("system:user:add")
	public ResultMsg add(SysUser record, @RequestParam(value = "roles[]", defaultValue = "-1") Long[] roles) {
		return service.add(record, roles);
	}
	
	/**
	 * @Title: info 
	 * @Description: TODO(详情) 
	 * @param id
	 * @return SysUser    返回类型
	 */
	@GetMapping("/{id}")
	@ResponseBody
	@RequiresPermissions("system:user:info")
	public SysUser info(@PathVariable Long id) {
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
	@RequiresPermissions("system:user:edit")
	public ResultMsg update(SysUser record, @RequestParam( value = "roles[]", defaultValue = "-1") Long[] roles) {
		return service.update(record, roles);
	}
	
	/**
	 * @Title: delete 
	 * @Description: TODO(删除) 
	 * @param id
	 * @return ResultMsg    返回类型
	 */
	@DeleteMapping("/{id}")
	@ResponseBody
	@RequiresPermissions("system:user:delete")
	public ResultMsg delete(@PathVariable Long id) {
		return service.delete(id);
	}
}
