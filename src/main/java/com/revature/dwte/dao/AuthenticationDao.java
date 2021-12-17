package com.revature.dwte.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.revature.dwte.model.User;

@Repository
public class AuthenticationDao implements AuthenticationDaoInterface {

	@PersistenceContext
	private EntityManager entityManager;

	private Logger logger = LoggerFactory.getLogger(AuthenticationDao.class);

	@Transactional
	public User getLoginUser(String email, String password) {

		logger.info("getLoginUser(email, password");

		User user = entityManager
				.createQuery("FROM User u WHERE u.email = :userEmail AND u.password = :userPassword", User.class)
				.setParameter("userEmail", email).setParameter("userPassword", password).getSingleResult();

		return user;

	}

	@Transactional
	public User getSignupUser(String firstName, String lastName, String email, String phoneNumber, String role,
			String password) {

		logger.info("getSignupUser(email, password, ...");

		User user = (User) entityManager.createQuery(
				"INSERT INTO user (firstName, lastName, email, password, phoneNumber, role) VALUES (?,?,?,?,?,?)")

				.setParameter(1, firstName).setParameter(2, lastName).setParameter(3, email)
				.setParameter(4, phoneNumber).setParameter(5, role).setParameter(6, password).getSingleResult();

		return user;
	}

	@Transactional
	public User getUserById(int id) {

		logger.info("getUserById(id");

		User user = entityManager.createQuery("FROM User u WHERE u.userId = :userId", User.class)
				.setParameter("userId", id).getSingleResult();

		return user;

	}

}
