package com.revature.dwte.service;

import java.util.List;

import com.revature.dwte.exception.FailedAuthenticationException;
import com.revature.dwte.exception.InvalidLoginException;
import com.revature.dwte.exception.InvalidParameterException;
import com.revature.dwte.exception.NotFoundException;
import com.revature.dwte.model.User;

public interface AuthenticationServiceInterface {

	public User setLoginUser(String email, String password) throws InvalidLoginException;

	public void setSignupUser(String firstName, String lastName, String email, String password, String phoneNumber,
			String role) throws InvalidParameterException, NotFoundException, FailedAuthenticationException;

	public List<User> getUserByEmailAndPhoneNumber(String email, String phone_number);
}
