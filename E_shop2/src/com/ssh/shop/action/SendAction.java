package com.ssh.shop.action;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
@Controller("sendAction")
public class SendAction extends ActionSupport {
 
	private static final long serialVersionUID = 1L;

	 public String execute() {  
	        return "send";  
	    }  
}
