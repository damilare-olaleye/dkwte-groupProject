package com.revature.dwte.unitTestDao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.revature.dwte.dao.ReviewsDao;
import com.revature.dwte.model.Review;

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
		Review review = new Review("4 starts", "this place is great", "12-22-21", 1, 1);

		this.entityManager.persist(review);

		// ACT
		List<Review> actual = this.reviewDao.getAllReviews();

		// ASSERT
		Review review1 = new Review("4 starts", "this place is great", "12-22-21", 1, 1);
		review.setReviewId(1);

		List<Review> expected = new ArrayList<Review>();
		expected.add(review1);

		Assertions.assertEquals(expected, actual);

		this.entityManager.flush();
	}
	
	
}
