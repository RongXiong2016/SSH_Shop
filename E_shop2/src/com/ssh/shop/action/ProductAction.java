package com.ssh.shop.action;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ssh.shop.model.Product;


@SuppressWarnings("serial")
@Controller("productAction")
@Scope("prototype")
public class ProductAction extends BaseAction<Product>{
	//用来存储分页的数据  
	public String queryJoinCategory(){

		//System.out.println("name:" + model.getName());  
		//System.out.println("page：" + page);  
		//System.out.println("rows：" + rows);  

		pageMap = new HashMap<String, Object>();  
		List<Product> productList = productService.queryJoinCategory(model.getName(), page, rows);
		pageMap.put("rows", productList);

		Long total = productService.getCount(model.getName());

		pageMap.put("total", total);
		return "jsonMap";
	}

	public String deleteByIds(){
		System.out.println(ids);
		productService.deleteByIds(ids);		
		inputStream = new ByteArrayInputStream("true".getBytes());
		return "stream";
	}
	
	//	添加商品部分
	public void save() throws Exception{
		//fileUpload工具类被抽取了，uploadFile方法直接接受一个fileImage对象，返回新的图片名 
		String pic = fileUpload.uploadFile(fileImage);
		model.setPic(pic);

		model.setDate(new Date());
		System.out.println(model);
		//商品信息入库
		productService.save(model);
	}
	//	更新商品部分
	public void update(){
		String pic = fileUpload.uploadFile(fileImage);  
		model.setPic(pic);  

		model.setDate(new Date()); //设置一下当前时间，因为前台没有把时间字段传进来，这里自己设置一下即可  
		System.out.println(model);  
		//更新商品信息
		productService.update(model);
	}

}
