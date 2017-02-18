package com.ssh.shop.listener;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ssh.shop.utils.FileUpload;
import com.ssh.shop.utils.ProductTimerTask;

import javassist.tools.framedump;

//监听器是web层的组件，它是tomcat实例化的，不是Spring实例化的。不能放到Spring中 
public class InitDataListener implements ServletContextListener{
	
	private ProductTimerTask productTimerTask=null;
	private ApplicationContext ctx = null; 
	private FileUpload fileUpload;


	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		ctx = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext()); 
		fileUpload = (FileUpload) ctx.getBean("fileUpload");
		event.getServletContext().setAttribute("bankImageList", fileUpload.getBankImage());
	        productTimerTask = (ProductTimerTask) ctx.getBean("productTimerTask");//从配置文件中获取ProductTimerTask对象  
	        productTimerTask.setApplication(event.getServletContext());
	    new Timer(true).schedule(productTimerTask,0, 1000*60*60);
	}


}
