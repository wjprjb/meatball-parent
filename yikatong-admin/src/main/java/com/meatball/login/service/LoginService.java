/**
 * Project Name:meatball-admin
 * File Name:LoginService.java
 * Package Name:com.meatball.login.service.impl
 * Date:2018年1月13日下午2:34:27
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.login.service;

import com.meatball.system.user.model.SysUser;
import com.meatball.vo.ResultMsg;

/**   
 * @Title: LoginService.java 
 * @Package com.meatball.login.service.impl 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 張翔宇  
 * @date 2018年1月13日 下午2:34:27 
 * @version V1.0   
 */
public interface LoginService {
	/**
	 * @Title: validate 
	 * @Description: TODO(验证用户信息) 
	 * @param sysUser
	 * @return SysUser    返回类型
	 * @throws Exception 
	 */
	public ResultMsg validate(SysUser user);
}
