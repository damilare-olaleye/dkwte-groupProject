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

	/*-	*******************
	 * 	getAllReviews Tests
	 * 	*******************
	 */
	@Test
	@Transactional
	public void testGetAllReviews_positive() {
		Review review = new Review("4 starts", "");
	}
}
