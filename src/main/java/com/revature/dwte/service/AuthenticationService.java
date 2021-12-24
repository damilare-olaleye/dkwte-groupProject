package com.revature.dwte.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.revature.dwte.dao.AuthenticationDao;
import com.revature.dwte.exception.InvalidLoginException;
import com.revature.dwte.exception.InvalidParameterException;
import com.revature.dwte.exception.NotFoundException;
import com.revature.dwte.model.User;
import com.revature.dwte.utility.HashUtil;

@Service
public class AuthenticationService {

	@Autowired
	private AuthenticationDao authenticationDao;

	private Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

	public User getUserByEmailAndPassword(String email, String password)
			throws InvalidLoginException, NoSuchAlgorithmException {
		logger.info("AuthenticationService.getUserByEmailAndPassword() invoked");

		User user = this.authenticationDao.getUserByEmail(email);

		try {

			if (user != null) {
				String algorithm = "SHA-256";
				String hashedInputPassword = HashUtil.hashInputPassword(password.trim(), algorithm);

				logger.debug("hashedInputPassword {}", hashedInputPassword);
				logger.debug("user.getPassword {}", user.getPassword());

				Boolean isCorrectPassword = hashedInputPassword.equals(user.getPassword());

				if (isCorrectPassword) {
					return user;
				} else {
					throw new InvalidLoginException("Incorrect email and/or password");
				}
			} else {
				throw new NoResultException("Incorrect email and/or password");
			}

		} catch (DataAccessException e) {
			throw new InvalidLoginException("Incorrect email and/or password");
		}
	}

	public void signUpUser(String firstName, String lastName, String email, String password, String phoneNumber,
			String role) throws InvalidParameterException, NotFoundException, NoSuchAlgorithmException {
		logger.info("AuthenticationService.signUpUser() invoked");

		String algorithm = "SHA-256";
		String hashedPassword = HashUtil.hashPassword(password.trim(), algorithm);

		// capitalize first letter of user role
		String firstNameInput = firstName.trim();
		String firstNameCap = firstNameInput.substring(0, 1).toUpperCase() + firstNameInput.substring(1);

		String lastNameInput = lastName.trim();
		String lastNameCap = lastNameInput.substring(0, 1).toUpperCase() + lastNameInput.substring(1);

		String roleInput = role.trim();
		String userRole = roleInput.substring(0, 1).toUpperCase() + roleInput.substring(1);

		User user = new User(firstNameCap, lastNameCap, email.trim(), hashedPassword, phoneNumber.trim(), userRole);

		this.authenticationDao.signUpUser(user);

	}

	public List<User> getUserByEmailAndPhoneNumber(String email, String phone_number) {
		logger.info("AuthenticationService.getUserByEmailAndPassword() invoked");

		List<User> users = this.authenticationDao.getUserByEmailAndPhoneNumber(email, phone_number);

		return users;
	}
}
