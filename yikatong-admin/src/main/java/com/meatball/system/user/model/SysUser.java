package com.meatball.system.user.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.meatball.system.menu.model.SysMenu;
import com.meatball.system.role.model.SysRole;
import com.meatball.utils.DateUtil;
import com.meatball.vo.Page;

public class SysUser extends Page implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// 主键
	private Long id;
	
	// 头像
    private String avatar;
    
    // 账号
    private String account;

    // 密码
    private String password;
    //vo 是否重置密码
    private boolean resetPwd = false;

    // md5密码盐
    private String salt;

    // 名字
    private String name;

    // 生日
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    // vo 生日文字描述
    private String birthdayText;

    // 性别（1：男 2：女）
    private Integer sex;
    // vo 性别文字描述
    private String sexName;

    // 电子邮件
    private String email;

    // 电话
    private String phone;

    // 角色id
    private String roleid;

    // 部门id
    private Integer deptid;

    // 状态(1：启用  0：冻结）
    private Integer status;
    // vo 状态文字描述
    private String statusName;
    // vo 状态Boolean类型描述
    private boolean statusType;

    // 创建时间
    private Date createtime;
    // vo 时间文字描述
    private String createtimeText;

    // 保留字段
    private Integer version;
    
    // vo 用户验证用户信息
    private String verify;
    
    // 是否记住密码
    private boolean rememberMe;
    
    // vo 角色列表
    private List<SysRole> rolesVo;
    
    // vo菜单列表
    private List<SysMenu> menusVo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public boolean isResetPwd() {
		return resetPwd;
	}

	public void setResetPwd(boolean resetPwd) {
		this.resetPwd = resetPwd;
	}

	public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
    	if(birthday != null || "".equals(birthday)) {
    		this.birthdayText = DateUtil.getDay(birthday);
    	}
    	
        this.birthday = birthday;
    }

    public String getBirthdayText() {
		return birthdayText;
	}

	public void setBirthdayText(String birthdayText) {
		this.birthdayText = birthdayText;
	}

	public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
    	if(sex.equals(1)) {
    		this.sexName = "男";
    	} else if(sex.equals(2)) {
    		this.sexName = "女";
    	} else {
    		this.sexName = "保密";
    	}
        this.sex = sex;
    }

    public String getSexName() {
		return sexName;
	}

	public void setSexName(String sexName) {
		this.sexName = sexName;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid == null ? null : roleid.trim();
    }

    public Integer getDeptid() {
        return deptid;
    }

    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
    	if(status.equals(1)) {
    		this.statusName = "启用";
    	} else {
    		this.statusName = "冻结";
    	}
        this.status = status;
    }

    public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public boolean isStatusType() {
		return statusType;
	}

	public void setStatusType(boolean statusType) {
		if(statusType) {
			this.status = 1;
		} else {
			this.status = 0;
		}
		this.statusType = statusType;
	}

	public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
    	this.createtimeText = DateUtil.getTime(createtime);
        this.createtime = createtime;
    }

    public String getCreatetimeText() {
		return createtimeText;
	}

	public void setCreatetimeText(String createtimeText) {
		this.createtimeText = createtimeText;
	}

	public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

	public String getVerify() {
		return verify;
	}

	public void setVerify(String verify) {
		this.verify = verify;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public List<SysRole> getRolesVo() {
		return rolesVo;
	}

	public void setRolesVo(List<SysRole> rolesVo) {
		this.rolesVo = rolesVo;
	}

	public List<SysMenu> getMenusVo() {
		return menusVo;
	}

	public void setMenusVo(List<SysMenu> menusVo) {
		this.menusVo = menusVo;
	}

}