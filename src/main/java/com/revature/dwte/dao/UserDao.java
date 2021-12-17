package com.revature.dwte.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao implements UserDaoInterface {

	private Logger logger = LoggerFactory.getLogger(UserDaoInterface.class);

	@PersistenceContext
	private EntityManager entityManager;

}
