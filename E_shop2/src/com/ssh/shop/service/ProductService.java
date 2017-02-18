package com.ssh.shop.service;

import java.util.List;

import com.ssh.shop.model.Product;

public interface ProductService extends BaseService<Product> {
	//查询商品信息，级联类别  
    public List<Product> queryJoinCategory(String type, int page, int size); //使用商品的名称查询
    
  //根据关键字查询总记录数  
    public Long getCount(String type);  
    
    //根据id值删除商品
    public void deleteByIds(String ids);
    
    //根据热点类别查询推荐商品（仅仅查询前4个）
    public List<Product> queryByCategoryId(int cid);
}
