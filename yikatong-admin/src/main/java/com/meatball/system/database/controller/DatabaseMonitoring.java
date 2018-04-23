/**
 * Project Name:meatball-admin
 * File Name:DatabaseMonitoring.java
 * Package Name:com.meatball.system.database
 * Date:2018年1月13日下午12:57:57
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.system.database.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.meatball.component.OperateLog;

/**   
 * @Title: DatabaseMonitoring.java 
 * @Package com.meatball.system.database 
 * @Description: TODO(数据库监控) 
 * @author 張翔宇  
 * @date 2018年1月13日 下午12:57:57 
 * @version V1.0   
 */
@Controller
@RequestMapping("/system/dbmonitoring")
public class DatabaseMonitoring {
	
	@GetMapping("/index")
	@OperateLog("数据库监控")
	public String index() {
		return "system/database/index";
	}
	
	/**
	 * @Title: druid 
	 * @Description: TODO() 
	 * @return String    返回类型
	 */
	@GetMapping("/druid")
	public String druid() {
		return "redirect:/druid";
	}
}
