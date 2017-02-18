package com.ssh.shop.service.impl;

import org.springframework.stereotype.Service;

import com.ssh.shop.model.User;
import com.ssh.shop.service.UserService;
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{

	@Override
	public User login(User user) {
		String hql = "from User u where u.login =:login and u.pass =:pass";
		
		return (User) getSession()
				.createQuery(hql)
				.setString("login", user.getLogin())
				.setString("pass", user.getPass())
				.uniqueResult();
	}

}
