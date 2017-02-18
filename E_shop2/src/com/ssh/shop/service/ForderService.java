package com.ssh.shop.service;

import com.ssh.shop.model.Forder;

public interface ForderService extends BaseService<Forder>{
	//计算购物车的价格
	public double cluTotal(Forder forder);

	/**
	 * @param valueOf
	 * @param i
	 */
	public void updateStatusById(int id, int sid);
 
}
