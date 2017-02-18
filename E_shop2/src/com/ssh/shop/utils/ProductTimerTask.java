package com.ssh.shop.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;

import com.ssh.shop.model.Category;
import com.ssh.shop.model.Product;
import com.ssh.shop.service.CategoryService;
import com.ssh.shop.service.ProductService;
@Component
public class ProductTimerTask extends TimerTask{
	@Resource
	private ProductService productService = null;  
	@Resource
	private CategoryService categoryService = null;  
	//定义一个ServletContext对象，因为我们更新了后台数据后，需要存入application域里面  
	private ServletContext application = null; 

	public void setApplication(ServletContext application) {  
		this.application = application; //通过监听器将这个application对象set进来，因为这里是无法拿application对象的  
	}  

	/*@Override
	public void run() {
		System.out.println("--run--");
		//bigList中存放一个装有Category类的list
		List<List<Product>> bigList = new ArrayList<List<Product>>();
		//1.查询出热点类别
		for(Category category:categoryService.queryByHot(true)){
			List<Product> lst = productService.queryByCategoryId(category.getId());
			System.out.println("11111111");
			bigList.add(lst);
		}
		System.out.println("22222222");
		//2.把查询出来的biglist交给内置对象Aplication
		application.setAttribute("bigList", bigList);
	}*/
	@Override
	//和监听器在项目启动的时候数据初始化的逻辑一样
	public void run() {
		System.out.println("----run----");
		List<List<Product>> bigList = new ArrayList<List<Product>>(); //bigList中存放一个装有Category类的list
		// 1. 查询出热点类别
		for(Category category : categoryService.queryByHot(true)) {
			//根据热点类别id获取推荐商品信息
			List<Product> lst = productService.queryByCategoryId(category.getId());
			
			bigList.add(lst); //将装有category的list放到bigList中
		}
		// 2. 把查询的bigList交给application内置对象
		System.out.println("11111111");
		application.setAttribute("bigList", bigList);
	}
 
	
}
