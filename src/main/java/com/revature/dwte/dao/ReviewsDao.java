package com.revature.dwte.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.revature.dwte.dto.AddReviewDTO;
import com.revature.dwte.model.Review;
import com.revature.dwte.model.User;

@Repository
public class ReviewsDao implements ReviewsDaoInterface {
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public List<Review> getAllReviews() {
		List<Review> reviews = entityManager.createQuery("FROM Review r", Review.class).getResultList();

		return reviews;

	}

	@Transactional
	public Review addNewReview(int userIdOfCurrentlyLoggedInUser, AddReviewDTO dto) {

		User currentlyLoggedInUser = entityManager.find(User.class, userIdOfCurrentlyLoggedInUser);

		Review reviewToAdd = new Review(dto.getRatings(), dto.getReview(), dto.getSubmittedDate(),
				dto.getResturantsId(), currentlyLoggedInUser);

		entityManager.persist(reviewToAdd);

		return reviewToAdd;

	}

	@Transactional
	public void deleteReviews(int userIdOfCurrentlyLoggedInUser, int reviewId) {

		Review r = entityManager.find(Review.class, reviewId);

		entityManager.remove(r);
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
