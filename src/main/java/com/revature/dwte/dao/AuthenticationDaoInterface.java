package com.revature.dwte.dao;

import java.util.List;

import com.revature.dwte.model.User;

public interface AuthenticationDaoInterface {

	public List<User> getUserByEmailAndPhoneNumber(String email, String password);

	public void signUpUser(User user);

	public User getUserByUserId(int id);
}
