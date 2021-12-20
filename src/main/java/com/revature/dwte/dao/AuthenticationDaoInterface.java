package com.revature.dwte.dao;

import com.revature.dwte.model.User;

public interface AuthenticationDaoInterface {

	public User getLoginUser(String email, String password);

	public void getSignupUser(User user);

	public User getUserById(int id);
}
