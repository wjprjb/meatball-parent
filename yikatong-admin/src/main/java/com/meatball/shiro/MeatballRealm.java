/**
 * Project Name:meatball-admin
 * File Name:MeatballRealm.java
 * Package Name:com.meatball.shiro
 * Date:2017年10月10日上午11:59:11
 * Copyright (c) 2017, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.meatball.login.service.LoginService;
import com.meatball.system.role.model.SysRole;
import com.meatball.system.user.model.SysUser;

/**   
 * @Title: MeatballRealm.java 
 * @Package com.meatball.shiro 
 * @Description: TODO(权限处理) 
 * @author 張翔宇  
 * @date 2017年10月10日 上午11:59:11 
 * @version V1.0   
 */
public class MeatballRealm extends AuthorizingRealm {
	private static final Logger log = LoggerFactory.getLogger(MeatballRealm.class);
	@Resource
	private LoginService loginService;
	
	/**
	 * 权限验证
	 * 此处以“:”划分层级如：system:user:index, 如果授权为system，则冒号以后的部分都默认拥有操作权限
	 * 所以，细权限必须划分清楚
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		@SuppressWarnings("unchecked")
		List<SysRole> roles = (List<SysRole>) SecurityUtils.getSubject().getSession().getAttribute("roles");
		SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();
		Set<String> permissions = new HashSet<String>();
		roles.forEach(role -> {
			auth.addRole(role.getRoleName());
			String[] array= role.getRoleSign().split(",");
			for(String info : array) {
				permissions.add(info);
			}
		});
		auth.addStringPermissions(permissions);
		return auth;
	}

	/**
	 * 对当前登录的用户进行身份认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 根据刚刚传过来的token获取用户名
//		String account = (String) token.getPrincipal();
		//只是根据用户名查询出，不涉及密码
         SysUser sysUser = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("sysUser");
        if (sysUser != null) {
            log.debug("验证信息：" + JSON.toJSON(sysUser));
            // 把获取到的用户存到session中
            SecurityUtils.getSubject().getSession().setAttribute("sysUser", sysUser);
            // 把从数据库中查询出来的用户信息放到AuthenticationInfo中,
            // 即把正确的用户名，密码，交给shiro,再和前台输入的校验。
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(sysUser.getVerify(), sysUser.getPassword(), "MeatballRealm");
            return authenticationInfo;
        } else {
            return null;
        }
	}
	
	@Override
	protected void doClearCache(PrincipalCollection principals) {
		super.doClearCache(principals);
	}


}
