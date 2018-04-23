/**
 * Project Name:meatball-admin
 * File Name:LoginService.java
 * Package Name:com.meatball.login.service
 * Date:2017年10月9日上午8:52:46
 * Copyright (c) 2017, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.login.service.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.binding.BindingException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.meatball.component.TokenComponent;
import com.meatball.login.service.LoginService;
import com.meatball.system.menu.dao.SysMenuMapper;
import com.meatball.system.menu.model.SysMenu;
import com.meatball.system.role.dao.SysRoleMapper;
import com.meatball.system.role.model.SysRole;
import com.meatball.system.user.dao.SysUserMapper;
import com.meatball.system.user.model.SysUser;
import com.meatball.utils.MD5Util;
import com.meatball.utils.PhoneAndEmailCheckUtil;
import com.meatball.vo.ResultMsg;

/**   
 * @Title: LoginService.java 
 * @Package com.meatball.login.service 
 * @Description: TODO(用户登陆服务类) 
 * @author 張翔宇  
 * @date 2017年10月9日 上午8:52:46 
 * @version V1.0   
 */
@Service
public class LoginServiceImpl implements LoginService {
	private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Resource
	private SysUserMapper sysUserMapper;
	@Resource
	private SysMenuMapper sysMenuMapper;
	@Resource
	private TokenComponent tokenComponent;
	@Resource
	private SysRoleMapper roleMapper;
	
	@Override
	public ResultMsg validate(SysUser user) throws BindingException {
		SysUser sysUser = new SysUser();
		String message = "";
		String token = "";
		int code = 200;
		
		if(PhoneAndEmailCheckUtil.isPhoneLegal(user.getAccount())) {
			sysUser.setPhone(user.getAccount());
			log.info("用户输入为手机号码！");
		} else if(PhoneAndEmailCheckUtil.isEmail(user.getAccount())) {
			sysUser.setEmail(user.getAccount());
			log.info("用户输入为邮箱！");
		} else {
			sysUser.setAccount(user.getAccount());
		}
		try {
			sysUser = sysUserMapper.selectByProperty(sysUser);
			sysUser.setVerify(user.getAccount());
			Subject subject = SecurityUtils.getSubject();
			// 保存用户信息进session
			subject.getSession().setAttribute("sysUser", sysUser);
			UsernamePasswordToken upt = new UsernamePasswordToken(user.getAccount(), MD5Util.md5(user.getPassword(), sysUser.getSalt()), false);
			subject.login(upt);
			// 获取权限信息
			List<SysRole> roles = roleMapper.selectRoleByUserId(sysUser.getId());
			subject.getSession().setAttribute("roles", roles);
			// 获取菜单列表
			// 获取权限字符串
			StringBuffer perms = new StringBuffer();
			roles.forEach(role -> {
				perms.append(role.getRoleSign());
				perms.append(",");
			});
			List<SysMenu> menus = sysMenuMapper.selectAllTreeMenu();
			menus = this.deleteNotPermisMenu(menus, perms.toString());
			subject.getSession().setAttribute("menus", menus);
			// 返回消息
			message = "验证成功";
			token = tokenComponent.createJWT(sysUser.getId());
			sysUser.setRolesVo(roles);
			sysUser.setMenusVo(menus);
		} catch (Exception e) {
			code = 401;
			log.error("错误的用户名或密码");
			message = "错误的用户名或密码";
			e.printStackTrace();
		}
		return new ResultMsg(code, message, token, null);
	}
	
	/**
	 * @Title: deleteNullChildren 
	 * @Description: TODO(递归清除空children) 
	 * @param menus
	 * @return List<SysMenu>    返回类型
	 */
	private List<SysMenu> deleteNotPermisMenu(List<SysMenu> menus, String paermis) {
		Iterator<SysMenu> iterator = menus.iterator();
		while(iterator.hasNext()) {
			SysMenu menu = iterator.next();
			if(!paermis.contains(menu.getPerms())) {
				iterator.remove();
			} else {
				if(!menu.getChildren().isEmpty()) {
					this.deleteNotPermisMenu(menu.getChildren(), paermis);
				}
			}
		}
		return menus;
	}
}
