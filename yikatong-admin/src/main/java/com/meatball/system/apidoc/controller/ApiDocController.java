/**
 * Project Name:meatball-admin
 * File Name:ApiDocController.java
 * Package Name:com.meatball.system.apidoc.controller
 * Date:2018年3月13日上午9:17:33
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.system.apidoc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**   
 * @Title: ApiDocController.java 
 * @Package com.meatball.system.apidoc.controller 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 張翔宇  
 * @date 2018年3月13日 上午9:17:33 
 * @version V1.0   
 */
@Controller
@RequestMapping("/system/apidoc")
public class ApiDocController {
	
	/**
	 * @Title: index 
	 * @Description: TODO(api接口文档) 
	 * @return String    返回类型
	 */
	@GetMapping("/index")
	public String index() {
		return "system/apidoc/index";
	}
	
	@GetMapping("/doc")
	public String doc() {
		return "system/apidoc/doc";
	}
}
