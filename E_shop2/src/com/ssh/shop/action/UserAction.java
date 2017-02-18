package com.ssh.shop.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ssh.shop.model.User;
@Controller("userAction")
@Scope("prototype")
public class UserAction extends BaseAction<User>{
	
	public String login(){
		model = userService.login(model);
		if (model == null) {
			session.put("error", "登录失败");
			return "login";
		}else {  
            //登录成功，先将用户存储到session中  
            session.put("user", model);  
            //根据session中goURL是否有值而决定页面的跳转  
		  
		if(session.get("goUrl") == null) {  
			return "index"; //跳到首页  
		} else {  
			return "goUrl";  
		}  
	}
	}
}
