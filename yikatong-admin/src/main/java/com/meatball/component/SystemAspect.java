package com.meatball.component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.meatball.log.service.SysLogService;

/**
 * ClassName: SystemAspect 
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(切点类). 
 * date: 2017年9月1日 上午11:15:39 
 *
 * @author xinagyu.zhang
 * @version 1.0
 * @since JDK 1.8
 */
@Aspect
@Component
public class SystemAspect {
	@Resource
	private SysLogService logService;
	
	// 本地异常日志记录对象
//	private static final Logger logger = LoggerFactory.getLogger(SystemAspect.class);
	
	/**
	 * @Title: controllerAspect 
	 * @Description: TODO(Controller层切点) 
	 * @param     设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	@Pointcut("@annotation(com.meatball.component.OperateLog)")
	public void controllerAspect() {
		
	}
	
	/**
	 * @Title: serviceAspect 
	 * @Description: TODO(Service层切点) 
	 * @return void    返回类型
	 */
	@Pointcut("@annotation(com.meatball.component.ErrorLog)")
	public void serviceAspect() {
		
	}
	
	/**
	 * @Title: messageAspect 
	 * @Description: TODO(微信消息发送) 
	 * @param     设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	@Pointcut("@annotation(com.meatball.component.SendWXMessage)")
	public void messageAspect() {
		
	}
	
	/**
	 * @Title: doBefore 
	 * @Description: TODO(前置通知 用于拦截Controller层记录用户的操作) 
	 * @param @param joinPoint    设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	@Before(value = "controllerAspect()")
	public void doBefore(JoinPoint joinPoint) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();    
		logService.save(request, joinPoint, null);
	}
	
	/**
	 * @Title: doAfterThrowing 
	 * @Description: TODO(异常通知 用于拦截service层记录异常日志) 
	 * @param @param joinPoint
	 * @param @param e    设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	@AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();    
		logService.save(request, joinPoint, e);
	}
	
	/**
	 * @Title: sendMsg 
	 * @Description: TODO(微信消息发送) 
	 * @param     设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
//	@After(value = "messageAspect()")
//	public void sendMsg(JoinPoint joinPoint) {
//		try {
//			//获取用户请求方法的参数并序列化为JSON格式字符串    
//			String params = "";    
//	        if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {    
//				for (int i = 0; i < joinPoint.getArgs().length; i++) {
//					String s = JSON.toJSONString(joinPoint.getArgs()[i]);
//					if(!StringUtils.isEmpty(s)) {
//						params = s;
//					}
//				}
//			}
//	        // 信息内容
////	        MessageVo content = (MessageVo) JsonMapper.fromJsonString(params, MessageVo.class);
//	        MessageVo content = JSON.parseObject(params, MessageVo.class);
//	        // 发送模版
////	        MessageVo template = SystemLogAspect.getSendWXMessageMsg(joinPoint);
//	        String template = "";
//	        if(!StringUtils.isEmpty(content.getName())) {
//	        	if(content.getStatus() == CurrencyParam.SYS_SUCCESS || content.getStatus() == CurrencyParam.SYS_FAILED) {
//		        	template = CurrencyParam.SYS_NOTE_MESSAGE_REFUSE_CONTENT;
//		        } else {
//		        	template = CurrencyParam.SYS_NOTE_MESSAGE_PASS_CONTENT;
//		        }
//	        } else {
//	        	if(content.getStatus() == CurrencyParam.SYS_SUCCESS) {
//		        	template = CurrencyParam.SYS_NOTE_PARTNER_MESSAGE_PASS_CONTENT;
//		        } else {
//		        	template = CurrencyParam.SYS_NOTE_PARTNER_MESSAGE_REFUSE_CONTENT;
//		        }
//	        }
	        
//	        SysNote note = new SysNote();
//	        if(!StringUtils.isEmpty(content.getName())) {
//	        	note.setTitle(MessageFormat.format(CurrencyParam.SYS_NOTE_MESSAGE_TITLE, content.getName()));
//	        } else {
//	        	note.setTitle("「合伙人」消息提示");
//	        }
//			note.setContent(MessageFormat.format(template, content.getName(), content.getOperateDesc()));
//			note.setSendTime(new Date());
//			note.setCreateTime(new Date());
//			note.setType(CurrencyParam.STATE_USE);
//			note.setStatus(CurrencyParam.STATE_MESSAGE_UNREAD);
//			sysNoteService.sendSysNoteInfo(note, content.getUserId(), content.getUserTel());
	        
//		} catch (Exception e) {
//			e.printStackTrace();
			//记录本地异常日志    
//            logger.error("==异常通知异常==");    
//            logger.error("异常信息:{}", e.getMessage());
//		}
//	}
	
	
	
	/**
	 * @Title: getServiceMthodDescription 
	 * @Description: TODO(获取注解中对方法的描述信息 用于service层注解 ) 
	 * @param @param joinPoint 切点
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
//	public static String getServiceMthodDescription(JoinPoint joinPoint) throws Exception {
//       String targetName = joinPoint.getTarget().getClass().getName();    
//       String methodName = joinPoint.getSignature().getName();    
//       Object[] arguments = joinPoint.getArgs();    
//       Class<?> targetClass = Class.forName(targetName);    
//       Method[] methods = targetClass.getMethods();    
//       String description = "";    
//        for (Method method : methods) {    
//            if (method.getName().equals(methodName)) {    
//               Class<?>[] clazzs = method.getParameterTypes();    
//                if (clazzs.length == arguments.length) {    
//                   description = method.getAnnotation(SystemServiceLog. class).description();    
//                    break;    
//               }    
//           }    
//       }    
//        return description;    
//   }  
	
	/**
	 * @Title: getSendWXMessageMsg 
	 * @Description: TODO(获取消息模版) 
	 * @param @param joinPoint
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	/** public  static MessageVo getSendWXMessageMsg(JoinPoint joinPoint)  throws Exception {
		MessageVo msg = new MessageVo();
        String targetName = joinPoint.getTarget().getClass().getName();    
        String methodName = joinPoint.getSignature().getName();    
        Object[] arguments = joinPoint.getArgs();    
        Class<?> targetClass = Class.forName(targetName);    
        Method[] methods = targetClass.getMethods();    
         for (Method method : methods) {    
             if (method.getName().equals(methodName)) {    
                Class<?>[] clazzs = method.getParameterTypes();    
                 if (clazzs.length == arguments.length) {    
                	 msg.setTitle(method.getAnnotation(SendWXMessage. class).title());    
                	 msg.setContent(method.getAnnotation(SendWXMessage. class).content());
                     break;    
                }    
            }    
        }    
         return msg;    
    } **/
}
