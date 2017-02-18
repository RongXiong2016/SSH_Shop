package com.ssh.shop.service.impl;

import java.util.PrimitiveIterator.OfDouble;

import org.springframework.stereotype.Service;

import com.ssh.shop.model.Forder;
import com.ssh.shop.model.Product;
import com.ssh.shop.model.Sorder;
import com.ssh.shop.service.SorderService;
@Service("sorderService")
public class SorderServiceImpl extends BaseServiceImpl<Sorder> implements SorderService {


	@Override
	//将购物项转化为购物车
	public Forder addSorder(Forder forder, Product product) {
		boolean isHave = false;
		//拿到当前的购物项
		Sorder sorder = productToSorder(product);
		
		   //判断当前购物项是否重复，如果重复，则添加数量即可
		for (Sorder old : forder.getSorders()) {
			if (old.getProduct().getId().equals(sorder.getProduct().getId())) {
				//重复 则添加数量
				old.setNumber(old.getNumber()+sorder.getNumber());
				isHave = true;
				break;
			}
		}
		//如果购物项不重复，添加则可
		if (!isHave) {
			//在入库的时候先建立购物车与购物项的联系，
			sorder.setForder(forder);
			forder.getSorders().add(sorder);
		}
		return forder;
	}

	@Override
	public Sorder productToSorder(Product product) {
		Sorder sorder = new Sorder();
		sorder.setName(product.getName());
		sorder.setNumber(1);
	    sorder.setPrice(product.getPrice());
	    sorder.setProduct(product);
		return sorder;
	}

 
	@Override
	public Forder updateSorder(Sorder sorder, Forder forder) {
		for(Sorder temp :forder.getSorders()){
			if (temp.getProduct().getId().equals(sorder.getProduct().getId())) {
				temp.setNumber(sorder.getNumber());
			}
		}
		return forder;
	}

}
