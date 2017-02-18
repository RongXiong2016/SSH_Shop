/**
 * 
 */
package com.ssh.shop.utils.impl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

import com.ssh.shop.utils.EmailUtil;

/**
 * @author Administrator
 *
 */
@Component("emailUtil")
public class EmailUtilImpl implements EmailUtil{

	@Override
	public void sendEmail(String emailAddress, String id) {
		Properties prop = new Properties();
		Session session = null;
		Message message = null;
		Transport transport = null;
		try {
		prop.setProperty("mail.transport.protocol", "smtp");
		// 创建了session会话
		session = Session.getDefaultInstance(prop);
		// 设置debug模式来调试发送信息
		session.setDebug(true);

		// 创建一封邮件对象
		message = new MimeMessage(session);

		//创建邮件主题
		
			message.setSubject("易购商城");
			message.setContent("顾客您好，欢迎您光顾网上商城，订单" + id + "已支付成功！", "text/html;charset=utf-8");
			// 附件人地址
            message.setFrom(new InternetAddress("13588034686@163.com"));
            transport = session.getTransport();
            transport.connect("smtp.163.com", "13588034686", "fzr125318");
            // 设置收件人地址，并发送邮件
            transport.sendMessage(message, new InternetAddress[] { new InternetAddress(emailAddress) });
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			try {
				transport.close();
			} catch (MessagingException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		


	}

}
