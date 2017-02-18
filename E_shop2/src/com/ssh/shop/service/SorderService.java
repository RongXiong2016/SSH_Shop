package com.ssh.shop.service;

import com.ssh.shop.model.Forder;
import com.ssh.shop.model.Product;
import com.ssh.shop.model.Sorder;

public interface SorderService extends BaseService<Sorder>{
	//添加购物项，返回给购物车
	public Forder addSorder(Forder forder,Product product);
	
	//将商品项-->购物项
	public Sorder productToSorder(Product product);

	/**
	 * @param model
	 * @param forder
	 * @return
	 */
	public Forder updateSorder(Sorder sorder, Forder forder);
}
