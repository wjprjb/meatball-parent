/**
 * Project Name:meatball-admin
 * File Name:RoleServiceImpl.java
 * Package Name:com.meatball.system.role.service.impl
 * Date:2018年1月29日下午12:44:48
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.system.role.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.meatball.system.menu.model.SysMenu;
import com.meatball.utils.DateUtil;
import com.meatball.vo.Page;

/**   
 * @Title: RoleServiceImpl.java 
 * @Package com.meatball.system.role.service.impl 
 * @Description: TODO(系统管理-角色) 
 * @author 張翔宇  
 * @date 2018年1月29日 下午12:44:48 
 * @version V1.0   
 */
public class SysRole extends Page implements Serializable {
	private static final long serialVersionUID = 1L;

	// 主键
	private Long id;

	// 角色名称
    private String roleName;

    // 角色标识
    private String roleSign;
    
    // 显示拥有权限
    private String roleView;

    // 备注
    private String remark;

    // 创建时间
    private Date createTime;
    private String createTimeVo;

    // 更新时间
    private Date updateTime;
    
    //vo 
    private Boolean checked = Boolean.FALSE;
    
    //vo
    private List<SysMenu> menus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleSign() {
        return roleSign;
    }

    public void setRoleSign(String roleSign) {
        this.roleSign = roleSign;
    }

    public String getRoleView() {
		return roleView;
	}

	public void setRoleView(String roleView) {
		this.roleView = roleView;
	}

	public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
    	this.createTimeVo = DateUtil.getTime(createTime);
        this.createTime = createTime;
    }

    public String getCreateTimeVo() {
		return createTimeVo;
	}

	public void setCreateTimeVo(String createTimeVo) {
		this.createTimeVo = createTimeVo;
	}

	public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public List<SysMenu> getMenus() {
		return menus;
	}

	public void setMenus(List<SysMenu> menus) {
		this.menus = menus;
	}
}