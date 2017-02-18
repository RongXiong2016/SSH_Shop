package com.ssh.shop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssh.shop.model.Product;
import com.ssh.shop.service.ProductService;

@Service("productService")
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService{

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> queryJoinCategory(String name, int page, int size) {
		String hql ="from Product pro left join fetch pro.category where pro.name like :name";
		return getSession().createQuery(hql)
				.setString("name", "%"+name+"%")
				.setFirstResult((page-1)*size)
				.setMaxResults(size).list();

	}

	@Override
	public Long getCount(String name) {
		String hql = "select count(pro) from Product pro where pro.name like :name ";
				return (Long) getSession().createQuery(hql)  
						.setString("name", "%" + name + "%")  
						.uniqueResult(); //返回一条记录:总记录数  ;
	}

	@Override
	public void deleteByIds(String ids) {
		String hql = "delete from Product p where p.id in("+ids+")";
		getSession().createQuery(hql).executeUpdate();
		
	}
	//根据热点类别查询商品
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> queryByCategoryId(int cid) {
		/*String hql = "from Product p join fetch p.category "
				+ "where p.commend = true and p.open = true and p.category.id = :cid order by p.date desc";
		return getSession()
				.createQuery(hql)
				.setInteger("cid", cid)
				.setFirstResult(0)  
	            .setMaxResults(4)  
	            .list();*/
		String hql = "from Product p join fetch p.category "
				+ "where p.commend=true and p.open=true and p.category.id=:cid order by p.date desc";
		return getSession().createQuery(hql)
			.setInteger("cid", cid)
			.setFirstResult(0)
			.setMaxResults(4)
			.list();
	}

}
