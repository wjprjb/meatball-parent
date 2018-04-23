/**
 * Project Name:meatball-admin
 * File Name:UserService1.java
 * Package Name:com.meatball.system.user.service
 * Date:2018年1月13日下午2:31:12
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.system.user.service;

import com.github.pagehelper.PageInfo;
import com.meatball.system.user.model.SysUser;
import com.meatball.vo.ResultMsg;

/**   
 * @Title: UserService1.java 
 * @Package com.meatball.system.user.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 張翔宇  
 * @date 2018年1月13日 下午2:31:12 
 * @version V1.0   
 */
public interface UserService {
	
	/**
	 * @Title: tableDate 
	 * @Description: TODO(获取表格数据) 
	 * @param page
	 * @return PageInfo<SysUser>    返回类型 
	 */
	public PageInfo<SysUser> table(SysUser record);
	
	/**
	 * @Title: add 
	 * @Description: TODO(新增用户) 
	 * @param record
	 * @return ResultMsg    返回类型
	 */
	public ResultMsg add(SysUser record, Long[] roles);
	
	/**
	 * @Title: info 
	 * @Description: TODO(获取) 
	 * @param id
	 * @return SysUser    返回类型
	 */
	public SysUser info(Long id);
	
	/**
	 * @Title: update 
	 * @Description: TODO(更新) 
	 * @param record
	 * @return ResultMsg    返回类型
	 */
	public ResultMsg update(SysUser record, Long[] roles);
	
	/**
	 * @Title: delete 
	 * @Description: TODO(删除成功) 
	 * @param id
	 * @return ResultMsg    返回类型
	 */
	public ResultMsg delete(Long id);
}
