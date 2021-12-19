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
	public void getSignupUser(User user) {

		logger.info("getSignupUser(email, password, ...");

		System.out.println(user);

		this.entityManager.persist(user);

	}

	@Transactional
	public User getUserById(int id) {

		logger.info("getUserById(id");

		User user = entityManager.createQuery("FROM User u WHERE u.userId = :userId", User.class)
				.setParameter("userId", id).getSingleResult();

		return user;

	}

}
