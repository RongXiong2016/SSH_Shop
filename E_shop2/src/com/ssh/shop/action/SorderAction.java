package com.ssh.shop.action;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.ssh.shop.model.Forder;
import com.ssh.shop.model.Product;
import com.ssh.shop.model.Sorder;
@Controller
@Scope("prototype")
public class SorderAction extends BaseAction<Sorder>{

	public String addSorder(){
		//1. 根据product.id获取相应的商品数据 
		Product product = productService.get(model.getProduct().getId());
		//2. 判断当前session是否有购物车，如果没有则创建  
		if (session.get("forder")==null) {
			session.put("forder", new Forder(new ArrayList<Sorder>()));
		}
		//3. 把商品信息转化为sorder,并且添加到购物车中（判断购物项是否重复） 
		Forder forder = (Forder) session.get("forder");
		forder = sorderService.addSorder(forder, product);
		
		forder.setTotal(forderService.cluTotal(forder));
		session.put("forder", forder);
		return "showCart";
	}
	
	public String updateSorder(){
		Forder forder = (Forder) session.get("forder");
		//更新购物项，传进来的product.id被封装到了model中
		 forder = sorderService.updateSorder(model, forder);
		 forder.setTotal(forderService.cluTotal(forder));
		 session.put("forder", forder);
		//以流的形式返回新的总价格
		 inputStream =new ByteArrayInputStream((forder.getTotal()+"").getBytes()); 
		return "stream";
	}
}
