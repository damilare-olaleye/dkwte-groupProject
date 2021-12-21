package com.revature.dwte.unitTestDao;

import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.revature.dwte.dao.ReviewsDao;
import com.revature.dwte.model.Review;
import com.revature.dwte.model.User;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ReviewsDaoTest {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private ReviewsDao reviewDao;

	// POSTIVE TEST
//	@Test
//	@Transactional
//	public Review getAddNewReviewPostive() {
//
//		User currentlyLoggedInUser = new User("Jane", "Doe", "nedoe@yahoo.com", "disIsMyPassword13", "2039008372",
//				"Member");
//		this.entityManager.persist(currentlyLoggedInUser);
//
//		this.entityManager.flush();
//
//	}

}
