package com.revature.dwte.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.revature.dwte.model.User;

@Repository
public class AuthenticationDao implements AuthenticationDaoInterface {

	@PersistenceContext
	private EntityManager entityManager;

	private Logger logger = LoggerFactory.getLogger(AuthenticationDao.class);

	@Transactional
	public User getLoginUser(String email, String password) {
		logger.info("AuthenticationDao.getLoginUser() invoked");

		try {
			User user = entityManager
					.createQuery("FROM User u WHERE u.email = :userEmail AND u.password = :userPassword", User.class)
					.setParameter("userEmail", email).setParameter("userPassword", password).getSingleResult();

			return user;
		} catch (DataAccessException e) {

			e.printStackTrace();
			return null;
		}

	}

	@Transactional
	public void getSignupUser(User user) {
		logger.info("AuthenticationDao.getSignupUser() invoked");

		try {
			this.entityManager.persist(user);

		} catch (DataAccessException e) {

			e.printStackTrace();
		}

	}

	@Transactional
	public User getUserById(int id) {
		logger.info("AuthenticationDao.getUserById() invoked");

		User user = entityManager.createQuery("FROM User u WHERE u.userId = :userId", User.class)
				.setParameter("userId", id).getSingleResult();

		return user;

	}

	@Transactional
	public List<User> getUserByEmailAndPhoneNumber(String email, String phone_number) {
		logger.info("AuthenticationDao.getUserByEmailAndPhoneNumber() invoked");

		try {
			List<User> users = entityManager
					.createQuery("FROM User u WHERE u.email = :userEmail OR u.phone_number = :userPhone_number",
							User.class)
					.setParameter("userEmail", email).setParameter("userPhone_number", phone_number).getResultList();

			return users;
		} catch (DataAccessException e) {

			e.printStackTrace();
			return null;
		}
	}

	@Transactional
	public User getUserByEmail(String email) {
		logger.info("AuthenticationDao.getUserByEmail() invoked");

		try {
			User user = entityManager.createQuery("FROM User u WHERE u.email = :email", User.class)
					.setParameter("email", email).getSingleResult();

			logger.info("users {}", user);

			return user;
		} catch (DataAccessException e) {
			return null;
		}
	}

}
