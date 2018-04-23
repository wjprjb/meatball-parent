/**
 * Project Name:meatball-admin
 * File Name:ShiroConfig.java
 * Package Name:com.meatball.shiro
 * Date:2017年10月10日上午11:54:26
 * Copyright (c) 2017, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.shiro;

import java.util.LinkedHashMap;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**   
 * @Title: ShiroConfig.java 
 * @Package com.meatball.shiro 
 * @Description: TODO(权限控制) 
 * @author 張翔宇  
 * @date 2017年10月10日 上午11:54:26 
 * @version V1.0   
 */
@Configuration
public class ShiroConfig {
	private static final Logger log = LoggerFactory.getLogger(ShiroConfig.class);
	
	/**
	 * @Title: shiroFilterFactoryBean 
	 * @Description: TODO(设置过滤器) 
	 * @param manager
	 * @return ShiroFilterFactoryBean    返回类型
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager manager) {
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        // 过滤链
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        //配置记住我或认证通过可以访问的地址
        filterChainDefinitionMap.put("/", "user");
        //authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
        // 表示可以匿名访问
        //filterChainDefinitionMap.put("/swagger/**", "anon"); 
        //filterChainDefinitionMap.put("/druid/*", "anon");
        filterChainDefinitionMap.put("/api/**", "anon");
        filterChainDefinitionMap.put("/v2/**", "anon");
        // 管理员权限需要验证过滤
        filterChainDefinitionMap.put("/system/**", "authc");
        
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        bean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        bean.setSuccessUrl("/");
        
        // 未授权界面
        bean.setUnauthorizedUrl("/403");

        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
	}
	
	/**
	 * @Title: myRealm 
	 * @Description: TODO(注册自定义的Realm) 
	 * @return MeatballRealm    返回类型
	 */
    @Bean
    public MeatballRealm meatballRealm() {
    	MeatballRealm myRealm = new MeatballRealm();
        return myRealm;
    }

    /**
     * @Title: securityManager 
     * @Description: TODO(声明SecurityManager) 
     * @param meatballRealm
     * @return SecurityManager    返回类型
     */
    @Bean
    public SecurityManager securityManager(@Qualifier("meatballRealm") MeatballRealm meatballRealm) {
        // System.err.println("加载shiro");
        log.info("The authorization module loads successfully. (权限认证模块加载成功。)");
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(meatballRealm);
        //注入记住我管理器;
        manager.setRememberMeManager(rememberMeManager());
        return manager;
    }    

    /**
     * @Title: lifecycleBeanPostProcessor 
     * @Description: TODO(保证实现了shiro内部lifecycle函数的bean执行) 
     * @return LifecycleBeanPostProcessor    返回类型
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


    /**
     * @Title: defaultAdvisorAutoProxyCreator 
     * @Description: TODO(开启shiro,首先创建代理) 
     * @return DefaultAdvisorAutoProxyCreator    返回类型
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator=new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    /**
     * @Title: authorizationAttributeSourceAdvisor 
     * @Description: TODO(再加载securityManager, 开启权限认证支持) 
     * @param manager
     * @return AuthorizationAttributeSourceAdvisor    返回类型
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }
    
    /**
     * @Title: rememberMeCookie 
     * @Description: TODO(cookie对象;
     * 		这个参数是cookie的名称，对应前端的checkbox的name = rememberMe)
     * @return SimpleCookie    返回类型
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
    	log.info("RememberMeCookie write successfully. (记住我模块cookie写入成功。)");
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //记住我cookie生效时间10天 ,单位秒;
        simpleCookie.setMaxAge(864000);
        return simpleCookie;
    }
    
    /**
     * @Title: rememberMeManager 
     * @Description: TODO(cookie管理对象
     * 		rememberMeManager()方法是生成rememberMe管理器，
     * 		而且要将这个rememberMe管理器设置到securityManager中) 
     * @return CookieRememberMeManager    返回类型
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
    	log.info("RememberMeManager loads successfully. ( 记住我管理模块加载成功。)");
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        return cookieRememberMeManager;
    }
}
