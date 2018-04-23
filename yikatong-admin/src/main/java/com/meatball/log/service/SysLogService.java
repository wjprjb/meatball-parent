/**
 * Project Name:meatball-admin
 * File Name:SysLogService.java
 * Package Name:com.meatball.log.service
 * Date:2017年10月11日下午3:40:23
 * Copyright (c) 2017, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.log.service;

import java.lang.reflect.Method;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.meatball.component.OperateLog;
import com.meatball.log.dao.SysLogMapper;
import com.meatball.log.model.SysLog;
import com.meatball.system.user.model.SysUser;

/**   
 * @Title: SysLogService.java 
 * @Package com.meatball.log.service 
 * @Description: TODO(日志记录服务类) 
 * @author 張翔宇  
 * @date 2017年10月11日 下午3:40:23 
 * @version V1.0   
 */
@Service
public class SysLogService {
	private static final Logger log = LoggerFactory.getLogger(SysLogService.class);
	@Resource
	private SysLogMapper sysLogMapper;
	
	public void save(HttpServletRequest request, JoinPoint joinPoint, Throwable e) {
		HttpSession session = request.getSession();
		//读取session中的用户    
        SysUser user = (SysUser) session.getAttribute("sysUser");
        if(user == null) {
        	try {
        		user = (SysUser) joinPoint.getArgs()[0];
			} catch (Exception e2) {
				user = new SysUser();
	        	BeanUtils.copyProperties(joinPoint.getArgs()[0], user);
			}
        }
        //请求的IP    
        String ip = request.getRemoteAddr();  
        
        try {
			// 控制台输出
            log.info("=======================通知开始=======================");
            if(e != null) {
            	log.info("异常代码:" + e.getClass().getName());
            	log.info("异常信息:" + e.getMessage());
            	log.info("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            }
            log.info("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            log.info("方法描述:" + getMethodDescription(joinPoint));
            log.info("请求人:" + user.getAccount());
            log.info("请求IP:" + ip);
            
            // 数据库日志
            SysLog sysLog = new SysLog(); 
            sysLog.setDescription(getMethodDescription(joinPoint));    
            sysLog.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));    
            sysLog.setRequestIp(ip);    
            sysLog.setOperateUser(user.getAccount());    
            sysLog.setOperateDate(new Date()); 
            
         // 获取用户请求方法的参数并序列化为JSON格式字符串
            String params = "";
			if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
				for (int i = 0; i < joinPoint.getArgs().length; i++) {
					params += JSON.toJSONString(joinPoint.getArgs()[i]) + ";";
				}
			}
			sysLog.setParams(params);
            
			if (e != null) {
				
				// 0: 普通日志；1: 异常日志
				sysLog.setType(1);
				sysLog.setExceptionCode(e.getClass().getName());    
	            sysLog.setExceptionDetail(e.getMessage());    
	            
	            log.error("异常方法:{}异常代码:{}异常信息:{}参数:{}", joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName(), e.getClass().getName(), e.getMessage(), params);
			} else {
				sysLog.setType(0);
			}
			// 保存数据库
			sysLogMapper.insertSelective(sysLog);
			log.info("=======================通知结束=======================");
		} catch (Exception e1) {
			//记录本地异常日志    
			log.error("==前置通知异常==");    
			log.error("异常信息:{}", e1.getMessage()); 
		}
	}
	
	/**
	 * @Title: getControllerMethodDescription 
	 * @Description: TODO(获取注解中对方法的描述信息 用于Controller层注解) 
	 * @param @param joinPoint 切点
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	private String getMethodDescription(JoinPoint joinPoint)  throws Exception {    
        String targetName = joinPoint.getTarget().getClass().getName();    
        String methodName = joinPoint.getSignature().getName();    
        Object[] arguments = joinPoint.getArgs();    
        Class<?> targetClass = Class.forName(targetName);    
        Method[] methods = targetClass.getMethods();    
        String description = "";    
         for (Method method : methods) {    
             if (method.getName().equals(methodName)) {    
                Class<?>[] clazzs = method.getParameterTypes();    
                 if (clazzs.length == arguments.length) {    
                    description = method.getAnnotation(OperateLog. class).value();
                     break;    
                }    
            }    
        }    
         return description;    
    }
}
