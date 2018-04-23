/**
 * Project Name:meatball-admin
 * File Name:MenuController.java
 * Package Name:com.meatball.system.menu.controller
 * Date:2018年1月1日下午5:04:03
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.system.menu.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.meatball.vo.Page;

/**
 * @Title: SysMenu.java 
 * @Package com.meatball.system.menu.model 
 * @Description: TODO(菜单) 
 * @author 張翔宇  
 * @date 2018年1月2日 下午10:33:42 
 * @version V1.0
 */
public class SysMenu extends Page implements Serializable {
	private static final long serialVersionUID = 1L;

	// 主键
	private Long id;

	// 父ID
    private Long pid;

    // 名称
    private String name;

    // 链接地址
    private String href;

    // 授权(如：user:list)
    private String perms;

    // 类型   0：目录   1：菜单   2：按钮
    private Integer type;
    private String typeVo;
    
    // 层级vo(0：同级；1：子级)
    private Integer hierarchy;

    // 菜单图标
    private String icon;

    // 排序
    private Integer orderNum;

    // 创建时间
    private Date createTime;
    
    // 默认展开
    private boolean spread;
    
    //vo 是否选中
    private Boolean checked = Boolean.FALSE;
    
    // 子菜单
    private List<SysMenu> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
    	//类型   0：目录   1：菜单   2：按钮
    	if(type.intValue() == 0) {
    		this.typeVo = "目录";
    	} else if(type.intValue() == 1) {
    		this.typeVo = "菜单";
    	} else {
    		this.typeVo = "按钮";
    	}
        this.type = type;
    }

    public String getTypeVo() {
		return typeVo;
	}

	public void setTypeVo(String typeVo) {
		this.typeVo = typeVo;
	}

	public Integer getHierarchy() {
		return hierarchy;
	}

	public void setHierarchy(Integer hierarchy) {
		this.hierarchy = hierarchy;
	}

	public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public List<SysMenu> getChildren() {
		return children;
	}

	public void setChildren(List<SysMenu> children) {
		this.children = children;
	}

	public boolean isSpread() {
		return spread;
	}

	public void setSpread(boolean spread) {
		this.spread = spread;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}