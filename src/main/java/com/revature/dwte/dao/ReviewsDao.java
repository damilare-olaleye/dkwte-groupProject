package com.revature.dwte.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.revature.dwte.model.Review;

@Repository
public class ReviewsDao implements ReviewsDaoInterface {

	private Logger logger = LoggerFactory.getLogger(ReviewsDao.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public List<Review> getAllReviews() {
		logger.info("ReviewsDao.getAllReviews() invoked");

		try {
			List<Review> reviews = entityManager.createQuery("FROM Review r", Review.class).getResultList();

			return reviews;

		} catch (NoResultException e) {

			e.printStackTrace();

			return null;
		}

	}

	@Transactional
	public Review addNewReview(String rating, String experienceReview, String submittedDate,
			int authorId, String reviewTitle, String restaurant_name) {
		logger.info("ReviewsDao.addNewReview() invoked");

		try {
			Review reviewToAdd = new Review();

			reviewToAdd.setRatings(rating);
			reviewToAdd.setReview(experienceReview);
			reviewToAdd.setSubmittedDate(submittedDate);
			reviewToAdd.setAuthorId(authorId);
			reviewToAdd.setReviewTitle(reviewTitle);
			reviewToAdd.setRestaurant_name(restaurant_name);

			this.entityManager.persist(reviewToAdd);

			return reviewToAdd;

		} catch (DataAccessException e) {

			e.printStackTrace();

			return null;
		}

	}

	@Transactional
	public void deleteReviews(int reviewId) {
		logger.info("ReviewsDao.deleteReviews() invoked");

		Review reviewToDelete = entityManager.find(Review.class, reviewId);

		entityManager.remove(reviewToDelete);

	}

	@Transactional
	public Review getReviewsByReviewId(int reviewId) {
		logger.info("ReviewsDao.getReviewsByReviewId() invoked");

		try {
			Review review = entityManager.createQuery("FROM Review r WHERE r.reviewId = :reviewId", Review.class)
					.setParameter("reviewId", reviewId).getSingleResult();

			return review;
		} catch (DataAccessException e) {
			return null;
		}

	}

	@Transactional
	public List<Review> getReviewsByRestaurantId(int restaurantId) {
		logger.info("ReviewsDao.getReviewsByRestaurantId() invoked");

		try {

			List<Review> reviews = entityManager
					.createQuery("FROM Review r WHERE r.restaurantId = :restaurantId", Review.class)
					.setParameter("restaurantId", restaurantId).getResultList();

			return reviews;
		} catch (DataAccessException e) {
			return null;
		} catch (NoResultException e) {
			return null;

		}
	}
}
