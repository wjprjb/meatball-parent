/**
 * Project Name:meatball-admin
 * File Name:MenuService1.java
 * Package Name:com.meatball.system.menu.service
 * Date:2018年1月13日下午2:15:46
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.system.menu.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.meatball.system.menu.model.SysMenu;
import com.meatball.vo.ResultMsg;

/**   
 * @Title: MenuService1.java 
 * @Package com.meatball.system.menu.service 
 * @Description: TODO(菜单管理) 
 * @author 張翔宇  
 * @date 2018年1月13日 下午2:15:46 
 * @version V1.0   
 */
public interface MenuService {
	
	/**
	 * @Title: table 
	 * @Description: TODO(获取分页列表) 
	 * @param id
	 * @return
	 * @return List<SysMenu>    返回类型
	 */
	public PageInfo<SysMenu> table(SysMenu record);
	
	/**
	 * @Title: selectAllMenu 
	 * @Description: TODO(获取菜单集合) 
	 * @return List<SysMenu>    返回类型
	 */
	public List<SysMenu> selectAllTreeMenu();
	
	/**
	 * @Title: info 
	 * @Description: TODO(菜单详情) 
	 * @param id
	 * @return SysMenu    返回类型
	 */
	public SysMenu info(Long id);
	
	/**
	 * @Title: add 
	 * @Description: TODO(菜单) 
	 * @param record
	 * @return ResultMsg    返回类型
	 */
	public ResultMsg add(SysMenu record);
	
	/**
	 * @Title: updata 
	 * @Description: TODO(更新菜单) 
	 * @param record
	 * @return ResultMsg    返回类型
	 */
	public ResultMsg updata(SysMenu record);
	
	/**
	 * @Title: del 
	 * @Description: TODO(删除) 
	 * @param id
	 * @return ResultMsg    返回类型
	 */
	public ResultMsg del(Long id);
}
