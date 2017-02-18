package com.ssh.shop.service.impl;


import org.springframework.stereotype.Service;

import com.ssh.shop.model.Forder;
import com.ssh.shop.model.Sorder;
import com.ssh.shop.service.ForderService;
@Service("forderService")
public class ForderServiceImpl extends BaseServiceImpl<Forder> implements ForderService{

	@Override
	public double cluTotal(Forder forder) {
		double total = 0.0;
		for(Sorder sorder:forder.getSorders()){
			total += sorder.getNumber() * sorder.getPrice();  
		}
		return total;
	}

	//根据订单编号，更新订单状态
	@Override
	public void updateStatusById(int id, int sid) {
		String hql = "update Forder f set f.status.id=:sid where f.id=:id";
		getSession().createQuery(hql)
		.setInteger("sid", sid)
		.setInteger("id", id)
		.executeUpdate();
	}

}
