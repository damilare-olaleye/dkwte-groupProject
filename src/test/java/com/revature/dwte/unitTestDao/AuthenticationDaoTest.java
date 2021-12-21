package com.revature.dwte.unitTestDao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.revature.dwte.dao.AuthenticationDao;
import com.revature.dwte.model.User;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class AuthenticationDaoTest {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private AuthenticationDao authDao;

	// POSTIVE TEST
	@Test
	@Transactional
	public void testGetUserByEmailAndPassword_positive() {
		User user = new User("Jane", "Doe", "nedoe@yahoo.com", "disIsMyPassword13", "2039008372", "Member");
		this.entityManager.persist(user);

		this.entityManager.flush();

		// ACT
		User actual = this.authDao.getLoginUser("nedoe@yahoo.com", "disIsMyPassword13");

		// ASSERT
		User expected = new User("Jane", "Doe", "nedoe@yahoo.com", "disIsMyPassword13", "2039008372", "Member");
		expected.setUserId(1);

		Assertions.assertEquals(expected, actual);
	}

	// NEGATIVE TEST
	@Test
	@Transactional
	public void testGetUserByEmailAndPassword_incorrectPassword() {
		User user = new User("Jane", "Doe", "nedoe@yahoo.com", "disIsMyPassword13", "2039008372", "Member");
		this.entityManager.persist(user);

		this.entityManager.flush();

		// ACT AND ASSERT
		Assertions.assertThrows(DataAccessException.class, () -> {
			this.authDao.getLoginUser("nedoe@yahoo.com", "DisIsmyPass13");
		});

	}

	// NEGATIVE TEST
	@Test
	@Transactional
	public void testGetUserByEmailAndPassword_incorrectEmail() {
		User user = new User("Jane", "Doe", "nedoe@yahoo.com", "disIsMyPassword13", "2039008372", "Member");
		this.entityManager.persist(user);

		this.entityManager.flush();

		// ACT AND ASSERT
		Assertions.assertThrows(DataAccessException.class, () -> {
			this.authDao.getLoginUser("nodoe@yahoo.com", "disIsMyPassword13");
		});

	}

	// NEGATIVE TEST
	@Test
	@Transactional
	public void testGetUserByEmailAndPassword_incorrectEmail_incorrectPassword() {
		User user = new User("Jane", "Doe", "nedoe@yahoo.com", "disIsMyPassword13", "2039008372", "Member");
		this.entityManager.persist(user);

		this.entityManager.flush();

		// ACT AND ASSERT
		Assertions.assertThrows(DataAccessException.class, () -> {
			this.authDao.getLoginUser("nodoe@yahoo.com", "DisIsmyPass13");
		});

	}

	// POSITVE TEST
	@Test
	@Transactional
	public void testGetUserByEmail() {
		User user = new User("Jane", "Doe", "nedoe@yahoo.com", "disIsMyPassword13", "2039008372", "Member");
		this.entityManager.persist(user);

		this.entityManager.flush();

		// ACT
		User actual = this.authDao.getUserByEmail("nedoe@yahoo.com");

		// ASSERT
		User expected = new User("Jane", "Doe", "nedoe@yahoo.com", "disIsMyPassword13", "2039008372", "Member");
		expected.setUserId(1);

		Assertions.assertEquals(expected, actual);

	}

	// NEGATIVE TEST
	@Test
	@Transactional
	public void testGetUserByEmail_incorrectGetUserByEmail() {
		User user = new User("Jane", "Doe", "nedoe@yahoo.com", "disIsMyPassword13", "2039008372", "Member");
		this.entityManager.persist(user);

		this.entityManager.flush();

		// ACT AND ASSERT
		Assertions.assertThrows(DataAccessException.class, () -> {
			this.authDao.getUserByEmail("nodoe@yahoo.com");
		});

	}

	// NEGATIVE TEST
	@Test
	@Transactional
	public void testGetUserByEmail_noInputInEmail() {
		User user = new User("Jane", "Doe", "nedoe@yahoo.com", "disIsMyPassword13", "2039008372", "Member");
		this.entityManager.persist(user);

		this.entityManager.flush();

		// ACT AND ASSERT
		Assertions.assertThrows(DataAccessException.class, () -> {
			this.authDao.getUserByEmail(" ");
		});

	}

	// POSITVE TEST
	@Test
	@Transactional
	public void testGetUserByEmailAndPhoneNumber() {
		User user1 = new User("Jane", "Doe", "nedoe@yahoo.com", "disIsMyPassword13", "2039008372", "Member");
		this.entityManager.persist(user1);

		this.entityManager.flush();

		// ACT
		List<User> actual = this.authDao.getUserByEmailAndPhoneNumber("nedoe@yahoo.com", "2039008372");

		// ASSERT
		User expected = new User("Jane", "Doe", "nedoe@yahoo.com", "disIsMyPassword13", "2039008372", "Member");
		expected.setUserId(1);

		Assertions.assertEquals(expected, actual);

	}

	// NEGATIVE TEST
	@Test
	@Transactional
	public void testGetUserByEmailAndPhoneNumber_incorrectEmail() {
		User user = new User("Jane", "Doe", "nedoe@yahoo.com", "disIsMyPassword13", "2039008372", "Member");
		this.entityManager.persist(user);

		this.entityManager.flush();

		// ACT AND ASSERT
		Assertions.assertThrows(DataAccessException.class, () -> {
			this.authDao.getLoginUser("nedo@yahoo.com", "2039008372");
		});

	}

	// NEGATIVE TEST
	@Test
	@Transactional
	public void testGetUserByEmailAndPhoneNumber_incorrectPhoneNumber() {
		User user = new User("Jane", "Doe", "nedoe@yahoo.com", "disIsMyPassword13", "2039008372", "Member");
		this.entityManager.persist(user);

		this.entityManager.flush();

		// ACT AND ASSERT
		Assertions.assertThrows(DataAccessException.class, () -> {
			this.authDao.getLoginUser("nedoe@yahoo.com", "2009008372");
		});

	}

	// NEGATIVE TEST
	@Test
	@Transactional
	public void testGetUserByEmailAndPhoneNumber_blankPhoneNumber() {
		User user = new User("Jane", "Doe", "nedoe@yahoo.com", "disIsMyPassword13", "2039008372", "Member");
		this.entityManager.persist(user);

		this.entityManager.flush();

		// ACT AND ASSERT
		Assertions.assertThrows(DataAccessException.class, () -> {
			this.authDao.getLoginUser("nedoe@yahoo.com", "");
		});

	}

	// NEGATIVE TEST
	@Test
	@Transactional
	public void testGetUserByEmailAndPhoneNumber_blankEmail() {
		User user = new User("Jane", "Doe", "nedoe@yahoo.com", "disIsMyPassword13", "2039008372", "Member");
		this.entityManager.persist(user);

		this.entityManager.flush();

		// ACT AND ASSERT
		Assertions.assertThrows(DataAccessException.class, () -> {
			this.authDao.getLoginUser("", "2039008372");
		});

	}

	// NEGATIVE TEST
	@Test
	@Transactional
	public void testGetUserByEmailAndPhoneNumber_blankEmailAndblankPhoneNumber() {
		User user = new User("Jane", "Doe", "nedoe@yahoo.com", "disIsMyPassword13", "2039008372", "Member");
		this.entityManager.persist(user);

		this.entityManager.flush();

		// ACT AND ASSERT
		Assertions.assertThrows(DataAccessException.class, () -> {
			this.authDao.getLoginUser("", "");
		});

	}

	// NEGATIVE TEST
	@Test
	@Transactional
	public void testGetUserByEmailAndPhoneNumber_incorrectPhoneNumberAndEmail() {
		User user = new User("Jane", "Doe", "nedoe@yahoo.com", "disIsMyPassword13", "2039008372", "Member");
		this.entityManager.persist(user);

		this.entityManager.flush();

		// ACT AND ASSERT
		Assertions.assertThrows(DataAccessException.class, () -> {
			this.authDao.getLoginUser("nedo@yahoo.com", "2009038372");
		});

	}

}
