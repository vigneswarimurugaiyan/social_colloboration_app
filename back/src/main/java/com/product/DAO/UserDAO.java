package com.product.DAO;

import java.util.List;

import com.product.model.User;

public interface UserDAO {
	public void adduser(User user);
	public List<User> getList();

}
