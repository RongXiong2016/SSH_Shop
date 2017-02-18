/**
 * 
 */
package juint;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author Administrator
 *
 */
public class SendEmailDemo {
	public static void main(String[] args) {
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
			message.setContent("顾客您好，欢迎您光顾网上商城，订单" + 111 + "已支付成功！", "text/html;charset=utf-8");
			// 发件人地址
            message.setFrom(new InternetAddress("13588034686@163.com"));
            transport = session.getTransport();
            //    aesnnsttciwleich jmivlhoujiatfaac
            transport.connect("smtp.163.com", "13588034686", "fzr125318");
            // 设置收件人地址，并发送邮件
            transport.sendMessage(message, new InternetAddress[] {new InternetAddress("1790329815@qq.com") });
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
