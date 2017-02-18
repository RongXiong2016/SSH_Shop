package com.ssh.shop.service.impl;
import java.util.List;
import org.springframework.stereotype.Service;
import com.ssh.shop.model.Category;
import com.ssh.shop.service.CategoryService;
@Service("categoryServie")
public class CategoryServiceImpl extends BaseServiceImpl<Category>  implements CategoryService{


	@Override
	public void save(Category category) {
		 getSession().save(category);
	}
	@Override
	public void update(Category category) {
		getSession().update(category);
	}
	@Override
	public void delete(int id) {
		String hql = "delete Category where id=:id";  
        getSession().createQuery(hql)   
                .setInteger("id", id)
                .executeUpdate();  
	}
	@Override
	public Category get(int id) {
		return (Category) getSession().get(Category.class, id);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> query() {
		String hql = "from Category";  
        return getSession().createQuery(hql).list();  
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> queryJoinAccount(String type,int page,int size) {
		 String hql = "from Category c left join fetch c.account where c.type like :type";  
	        return getSession()
	        		.createQuery(hql).setString("type", "%" + type + "%")
	        		.setFirstResult((page-1)*size)
	        		.setMaxResults(size)
	        		.list();
	}
	@Override
	public Long getCount(String type) {
		String hql = "select count(c) from Category c where c.type like :type";
		return (Long) getSession().createQuery(hql).setString("type", "%"+type+"%").uniqueResult();
	}
	@Override
	public void deleteByIds(String ids) {
		String hql = "delete from Category c where c.id in("+ids+")";
		getSession().createQuery(hql).executeUpdate();
	}
	
	//根据boolean查询热点类别
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> queryByHot(boolean hot) {
		String hql = "from Category c where c.hot =:hot";
		return getSession()
				.createQuery(hql)
				.setBoolean("hot", hot)
				.list();
	}
  
}
