/**
 * 
 */
package com.ssh.shop.action;

import javax.enterprise.inject.New;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ssh.shop.model.Forder;
import com.ssh.shop.model.Sorder;
import com.ssh.shop.model.Status;
import com.ssh.shop.model.User;

/**
 * @author Administrator
 *
 */
@Controller("forderAction")  
@Scope("prototype") 
public class ForderAction extends BaseAction<Forder>{
	
	
	
	@Override
	public Forder getModel() {
		model = (Forder) session.get("forder");
		return model;
	}

	public String save(){
		model.setUser((User)session.get("user"));
		model.setStatus(new Status(1));
		forderService.save(model);
		
		session.put("oldForder", session.get("forder"));//将oldForder保存起来 ，后面支付需要用到
		session.put("forder", new Forder());
		return "bank";
	}
}
