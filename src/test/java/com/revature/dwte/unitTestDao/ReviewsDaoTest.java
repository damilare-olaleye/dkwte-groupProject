package com.revature.dwte.unitTestDao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
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
		Review review = new Review("4 stars", "this place is great", "12-22-21", 1, "good food", "Panda Express");
		this.entityManager.persist(review);

		this.entityManager.flush();
		// ACT
		List<Review> actual = this.reviewDao.getAllReviews();

		// ASSERT
		Review review1 = new Review("4 stars", "this place is great", "12-22-21", 1, "good food", "Panda Express");
		review1.setReviewId(1);

		List<Review> expected = new ArrayList<Review>();
		expected.add(review1);

		Assertions.assertEquals(expected, actual);
	}

	@Test
	@Transactional
	public void testGetAllReviews_reviewsDoesNotExist_negative() {

		// ARRANGE - not required to arrange

		// ACT
		List<Review> actual = this.reviewDao.getAllReviews();

		// ASSERT
		List<Review> expected = new ArrayList<>();

		Assertions.assertEquals(expected, actual);
	}

	/*-	******************
	 * 	addNewReview Tests
	 * 	******************
	 */
	@Test
	@Transactional
	public void testAddNewReview_withReviewDescritpion_positive() {
		Review actual = reviewDao.addNewReview("4 stars", "this place is great", "12-22-21", 1, "good food", "Panda Express");

		this.entityManager.flush();

		Review expected = new Review("4 stars", "this place is great", "12-22-21", 1, "good food", "Panda Express");
		expected.setReviewId(1);

		Assertions.assertEquals(expected, actual);

	}

	@Test
	@Transactional
	public void testAddNewReview_withNoReviewDescritpion_positive() {
		Review actual = reviewDao.addNewReview("4 stars", null, "12-22-21", 1, "good food", "Panda Express");

		this.entityManager.flush();

		Review expected = new Review("4 stars", null, "12-22-21", 1, "good food", "Panda Express");
		expected.setReviewId(1);

		Assertions.assertEquals(expected, actual);

	}

	@Test
	@Transactional
	public void testAddNewReview_blankRating_negative() {

		Assertions.assertThrows(DataAccessException.class, () -> {
			this.reviewDao.addNewReview(null, "this place is great", "12-22-21", 1, "good food", "Panda Express");
		});

	}

	@Test
	@Transactional
	public void testAddNewReview_blankSubmittedDate_negative() {

		Assertions.assertThrows(DataAccessException.class, () -> {
			this.reviewDao.addNewReview("4 stars", "this place is great", null, 1, "good food", "Panda Express");
		});

	}

	@Test
	@Transactional
	public void testAddNewReview_blankRatingAndSubmittedDate_negative() {

		Assertions.assertThrows(DataAccessException.class, () -> {
			this.reviewDao.addNewReview(null, "this place is great", null, 1, "good food", "Panda Express");
		});

	}

	@Test
	@Transactional
	public void testAddNewReview_blankRatingAndReviewDescriptionAndSubmittedDate_negative() {

		Assertions.assertThrows(DataAccessException.class, () -> {
			this.reviewDao.addNewReview(null, null, null, 1, "good food", "Panda Express");
		});

	}

	/*-	*************
	 * 	deleteReviews
	 * 	*************
	 */
	@Test
	@Transactional
	public void testDeleteRevies_positive() {
		Review review = reviewDao.addNewReview("4 stars", "this place is great", "12-22-21", 1, "good food", "Panda Express");
		review.setReviewId(1);
		this.entityManager.persist(review);

		this.entityManager.flush();

		reviewDao.deleteReviews(1);

		List<Review> deletedReview = reviewDao.getAllReviews();

		Assertions.assertTrue(deletedReview.isEmpty());
	}

	/*- *******************
	 *  getReviewByReviewId
	 *  *******************
	 */
	@Test
	@Transactional
	public void testGetReviewByReviewId_possitive() {
		Review review = reviewDao.addNewReview("4 stars", "this place is great", "12-22-21", 1, "good food", "Panda Express");
		review.setReviewId(1);
		this.entityManager.persist(review);

		this.entityManager.flush();

		Review actual = this.reviewDao.getReviewsByReviewId(1);

		Review expected = reviewDao.addNewReview("4 stars", "this place is great", "12-22-21", 1, "good food", "Panda Express");
		expected.setReviewId(1);

		Assertions.assertEquals(expected, actual);
	}

	@Test
	@Transactional
	public void testGetReviewByReviewId_reviewDoesNotExist_negative() {

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			this.reviewDao.getReviewsByReviewId(1);
		});

	}

	/*-	**********************************
	 * 	getReviewsByRestaurantId Tests
	 * 	**********************************
	 */
//	@Test 
//	@Transactional
//	public void testGetReviewByRestaurantId_possitive() {
//		Review review = new Review("4 stars", "this place is great", "12-22-21", 1, "good food", "Panda Express");
//		this.entityManager.persist(review);
//
//		this.entityManager.flush();
//
//		List<Review> actual = this.reviewDao.getReviewsByRestaurantId(1);
//
//		Review review1 = new Review("4 stars", "this place is great", "12-22-21", 1, "good food", "Panda Express");
//		review1.setReviewId(1);
//
//		List<Review> expected = new ArrayList<Review>();
//		expected.add(review1);
//
//		Assertions.assertEquals(expected, actual);
//
//	}
	// for now

//	@Test
//	@Transactional
//	public void testGetReviewByRestaurantId_reviewDoesNotExist_negative() {
//
//		List<Review> expected = this.reviewDao.getReviewsByRestaurantId(1);
//
//		List<Review> actual = new ArrayList<>();
//
//		Assertions.assertEquals(expected, actual);
//
//	}

}
