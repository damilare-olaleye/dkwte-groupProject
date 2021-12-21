package com.revature.dwte.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.revature.dwte.controller.ReviewsController;
import com.revature.dwte.dto.AddReviewDTO;
import com.revature.dwte.model.Review;
import com.revature.dwte.model.User;

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

		} catch (DataAccessException e) {

			e.printStackTrace();
			return null;
		}

	}

	@Transactional
	public Review addNewReview(int userIdOfCurrentlyLoggedInUser, AddReviewDTO dto) {
		logger.info("ReviewsDao.addNewReview() invoked");

		try {
			User currentlyLoggedInUser = entityManager.find(User.class, userIdOfCurrentlyLoggedInUser);

			Review reviewToAdd = new Review(dto.getRatings(), dto.getReview(), dto.getSubmittedDate(),
					dto.getResturantsId(), currentlyLoggedInUser);

			entityManager.persist(reviewToAdd);

			return reviewToAdd;
		} catch (DataAccessException e) {

			e.printStackTrace();
			return null;
		}

	}

	@Transactional
	public void deleteReviews(int userIdOfCurrentlyLoggedInUser, int reviewId) {
		try {
			Review r = entityManager.find(Review.class, reviewId);

			entityManager.remove(r);

		} catch (DataAccessException e) {

			e.printStackTrace();

		}

	}

	@Transactional
	public int getReviewsById(int reviewId) {

		int getReviewById = entityManager.createQuery("FROM Review r WHERE r.reiviewId=:reiviewId", Review.class)
				.setParameter("reiviewId", reviewId).executeUpdate();

		return getReviewById;

	}

//	@Transactional
//	public List<Review> getAllReviewsByResturantId(int resturantId) {
//
//		List<Review> reviews = entityManager
//				.createQuery("SELECT r FROM Review r JOIN r.authorId re WHERE re.resturantsId = :resturantsId",
//						Review.class)
//				.setParameter("resturantsId", resturantId).getResultList();
//
//		return reviews;
//
//	}

}
