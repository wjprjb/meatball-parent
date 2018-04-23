/**
 * Project Name:meatball-admin
 * File Name:MenuServiceImpl.java
 * Package Name:com.meatball.system.menu.service.impl
 * Date:2018年1月13日下午2:14:46
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.system.menu.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meatball.system.menu.dao.SysMenuMapper;
import com.meatball.system.menu.model.SysMenu;
import com.meatball.system.menu.service.MenuService;
import com.meatball.vo.ResultMsg;

/**   
 * @Title: MenuServiceImpl.java 
 * @Package com.meatball.system.menu.service.impl 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 張翔宇  
 * @date 2018年1月13日 下午2:14:46 
 * @version V1.0   
 */
@Service
public class MenuServiceImpl implements MenuService {
	@Resource
	private SysMenuMapper mapper;
	
	@Override
	public List<SysMenu> selectAllTreeMenu() {
		List<SysMenu> menus = mapper.selectAllTreeMenu();
		this.deleteNullChildren(menus);
		return menus;
	}
	
	@Override
	public SysMenu info(Long id) {
		return mapper.selectByPrimaryKey(id);
	}
	
	@Override
	public ResultMsg add(SysMenu record) {
		if(record.getType() == 0) {
			record.setPid((long) 0);
		} else {
			if(record.getHierarchy().intValue() == 1) {
				record.setPid(record.getId());
			}
		}
		record.setId(null);
		record.setCreateTime(new Date());
		mapper.insertSelective(record);
		return new ResultMsg(200, "新增成功");
	}
	
	@Override
	public ResultMsg updata(SysMenu record) {
		mapper.updateByPrimaryKeySelective(record);
		return new ResultMsg(200, "更新成功");
	}
	
	@Override
	public ResultMsg del(Long id) {
		// 删除菜单
		mapper.deleteByPrimaryKey(id);
		// 删除子菜单
		mapper.deleteByPid(id);
		return new ResultMsg(200, "删除成功");
	}

	@Override
	public PageInfo<SysMenu> table(SysMenu record) {
		PageInfo<SysMenu> list = PageHelper.startPage(record.getOffset(), record.getLimit()).doSelectPageInfo(() -> mapper.findPageList(record));
		return list;
	}
	
	/**
	 * @Title: deleteNullChildren 
	 * @Description: TODO(递归清除空children) 
	 * @param menus
	 * @return List<SysMenu>    返回类型
	 */
	private List<SysMenu> deleteNullChildren(List<SysMenu> menus) {
		for(SysMenu info : menus) {
			if(info.getChildren().isEmpty()) {
				info.setChildren(null);
			} else {
				this.deleteNullChildren(info.getChildren());
			}
		}
		return menus;
	}
}
