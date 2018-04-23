/**
 * Project Name:meatball-admin
 * File Name:SendEmail.java
 * Package Name:com.meatball.test
 * Date:2018年2月7日下午5:19:33
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**   
 * @Title: SendEmail.java 
 * @Package com.meatball.test 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 張翔宇  
 * @date 2018年2月7日 下午5:19:33 
 * @version V1.0   
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = MeatballApplication.class)
public class SendEmail {
	@Resource
	private JavaMailSender mailSender;
	
	@Test
	public void sendEmail() {
		// 发送邮件测试
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("13882006101@sina.cn");
		mailMessage.setTo("84556290@qq.com");
		mailMessage.setSubject("1232222");
		mailMessage.setText("2224344");
		mailSender.send(mailMessage);
		System.out.println("邮件发送成功");
	}
}
