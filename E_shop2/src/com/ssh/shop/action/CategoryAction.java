package com.ssh.shop.action;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ssh.shop.model.Category;
 
@Controller("categoryAction")
@Scope("prototype")
public class CategoryAction extends  BaseAction<Category> {
	
	public String queryjoinAccount(){
		//用来存储分页的数据  
		pageMap = new HashMap<String, Object>(); 	
		//根据关键字和分页的参数查询相应的数据。这个方法我们在Service中写过了，当时完成级联查询  
		List<Category> categoryList = categoryService.queryJoinAccount(model.getType(), page, rows);
		//存储为json格式,一个关键字是rows另外一个是total
		pageMap.put("rows", categoryList);
		
		Long total = categoryService.getCount(model.getType()); //这个方法没写，我们等会儿去Service层完善一下 
		pageMap.put("total", total);
		return "jsonMap";
	}
	
	public String deleteByIds(){
		System.out.println(ids);
		categoryService.deleteByIds(ids); 
		inputStream = new ByteArrayInputStream("true".getBytes());
		return "stream";
	}
	
	public void save(){
		System.out.println(model.getType());
		categoryService.save(model);
	}
	
	
	public void update(){
		System.out.println(model);
		categoryService.update(model);
	}

	public String query(){
		jsonList = categoryService.query();
		return "jsonList";
	}

}
