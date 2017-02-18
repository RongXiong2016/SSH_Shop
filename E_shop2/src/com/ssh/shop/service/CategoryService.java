package com.ssh.shop.service;

import java.util.List;

import com.ssh.shop.model.Category;

public interface CategoryService extends BaseService<Category>{
	public void save(Category category); //插入
	public void update(Category category);//更新
	public void delete(int id);//删除category
	public Category get(int id);//获得一个Category
	public List<Category> query();//获取全部category
	
	//查询类别信息，级联管理员  
    public List<Category> queryJoinAccount(String type,int page,int size); //使用类别的名称查询
	public Long getCount(String type);
    
	//根据id批量删除
	public void deleteByIds(String ids);
    //根据boolean查询热点
	public List<Category> queryByHot(boolean hot);
	
}
