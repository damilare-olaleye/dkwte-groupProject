package com.revature.dwte.service;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.revature.dwte.dao.AuthenticationDao;
import com.revature.dwte.exception.FailedAuthenticationException;
import com.revature.dwte.exception.InvalidLoginException;
import com.revature.dwte.exception.InvalidParameterException;
import com.revature.dwte.exception.NotFoundException;
import com.revature.dwte.model.User;

@Service
public class AuthenticationService {

	@Autowired
	private AuthenticationDao authenticationDao;

	private Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

	public User setLoginUser(String email, String password) throws InvalidLoginException {

		try {
			logger.info("email, password");

			User user = this.authenticationDao.getLoginUser(email, password);

			return user;
		} catch (DataAccessException e) {
			throw new InvalidLoginException("Username and/or password is incorrect");
		}
	}

	public void setSignupUser(String firstName, String lastName, String email, String password, String phoneNumber,
			String role) throws InvalidParameterException, NotFoundException, FailedAuthenticationException {

		Set<String> userRole = new HashSet<>();
		userRole.add("Member");
		userRole.add("member");
		userRole.add("Admin");
		userRole.add("admin");

		try {

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(password);

			User user = new User(firstName, lastName, email, encodedPassword, phoneNumber, role);

			logger.info("setSignupUser(firstName, lastName, ...");

			if (!(userRole.contains(role))) {
				throw new InvalidParameterException("You selected an invalid role");
			}

			if (!(firstName.matches("[A-Z][a-z]*"))) {
				throw new InvalidParameterException("You entered an invalid first name");
			}

			if (firstName.trim().equals("")) {
				throw new NotFoundException("first name cannot be blank");
			}

			if (!(lastName.matches("[A-Z][a-z]*"))) {
				throw new InvalidParameterException("You entered an invalid last name");
			}

			if (lastName.trim().equals("")) {
				throw new NotFoundException("last name cannot be blank");
			}

//			if (!(email.equals("/.+@[^@]+\\.[^@]{2,}$/"))) {
//				throw new InvalidParameterException("You entered an invalid email");
//			}

			if (email.trim().equals("")) {
				throw new NotFoundException("email cannot be blank");
			}

			if (password.trim().equals("")) {
				throw new NotFoundException("email cannot be blank");
			}

			if (!(password.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"))) {
				throw new InvalidParameterException(
						"Password must contain at least one number and one uppercase and lowercase letter, "
								+ "and at least 8 or more characters");

			}

			if (!(phoneNumber.matches("^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$"))) {
				throw new InvalidParameterException("Phone number input is not valid, enter correct input");
			}

			this.authenticationDao.getSignupUser(user);

//			if (userd == null) {
//				throw new FailedAuthenticationException("Cannot sign up user! Try again later");
//
//			}

//			return userd;

		} catch (NumberFormatException e) {

			throw new NotFoundException("Invalid parameter was thrown");
		}

	}
}
