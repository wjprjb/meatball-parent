/** 
 * Project Name:meatball-admin 
 * File Name:LoginController.java 
 * Package Name:com.meatball.login.controller 
 * Date:2017年10月6日上午12:28:08 
 * Copyright (c) 2017, zhang.xiangyu@foxmail.com All Rights Reserved. 
 */  
package com.meatball.login.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meatball.component.OperateLog;
import com.meatball.login.service.LoginService;
import com.meatball.system.menu.service.MenuService;
import com.meatball.system.user.model.SysUser;
import com.meatball.vo.ResultMsg;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

/**   
 * @Title: LoginController.java 
 * @Package com.meatball.login.controller 
 * @Description: TODO(登陆) 
 * @author 張翔宇  
 * @date 2017年10月6日 上午12:28:08 
 * @version V1.0   
 */
@Api(tags = "用户登录")
@Controller
//@RequestMapping("/api/v1")
public class LoginController {
	@Resource
	private LoginService loginService;
	@Resource
	private MenuService menuService;
	
	/**
	 * @Title: index 
	 * @Description: TODO(返回登陆首页) 
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	@GetMapping("/login")
	@ApiIgnore
	public String login() {
		return "login";
	}
	
	/**
	 * @Title: validate 
	 * @Description: TODO(验证用户，并返回token) 
	 * @param user
	 * @param request @RequestBody 必须指定参数获取位置，否则无法获取到参数
	 * @return boolean    返回类型
	 */
	@ApiOperation(value = "用户登陆验证", notes = "验证用户名与密码是否正确")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "account", value = "用户名", required = true),
		@ApiImplicitParam(name = "password", value = "密码", required = true)
	})
	@ApiResponses(value = { 
		@ApiResponse(code = 401, message = "用户名或密码错", response = Void.class)
	})
	@PostMapping("/validate")
	@ResponseBody
	@OperateLog("用户登陆")
	public ResultMsg validate(SysUser user) {
		ResultMsg resultMsg = loginService.validate(user);
		return resultMsg;
	}
	
	/**
	 * @Title: index 
	 * @Description: TODO(主页) 
	 * @return
	 * @return String    返回类型
	 */
	@ApiIgnore
	@GetMapping("/")
	public String index(HttpServletRequest request) {
		return "index";
	}
}
  