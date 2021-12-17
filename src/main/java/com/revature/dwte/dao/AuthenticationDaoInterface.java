package com.revature.dwte.dao;

import com.revature.dwte.model.User;

public interface AuthenticationDaoInterface {

	public User getLoginUser(String email, String password);

	public User getSignupUser(String firstName, String lastName, String email, String phoneNumber, String role,
			String password);

	public User getUserById(int id);
}
