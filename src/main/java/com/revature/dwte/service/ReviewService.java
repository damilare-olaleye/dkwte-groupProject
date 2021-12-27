package com.revature.dwte.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.revature.dwte.dao.ReviewsDao;
import com.revature.dwte.exception.InvalidParameterException;
import com.revature.dwte.exception.ReviewDoesNotExist;
import com.revature.dwte.model.Review;
import com.revature.dwte.model.User;

@Service
public class ReviewService implements ReviewServiceInterface {

	private Logger logger = LoggerFactory.getLogger(ReviewService.class);

	private SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

	@Autowired
	private ReviewsDao reviewDao;

	public List<Review> getAllReview() throws ReviewDoesNotExist, InvalidParameterException {
		logger.info("ReviewService.getAllReview() invoked");

		List<Review> reviews = this.reviewDao.getAllReviews();

		try {
			if (reviews.isEmpty()) {
				throw new ReviewDoesNotExist("No reviews on file.");
			}

			return reviews;

		} catch (DataAccessException e) {

			throw new InvalidParameterException("No reviews on file.");
		}

	}

	public Review addNewReview(User currentlyLoggedInUser, String rating, String review, String review_Title, String restaurantName) {
		logger.info("ReviewService.addNewReview() invoked");

		String ratingInput = rating.trim() + " stars";
		String experienceReview = review.trim();
		String reviewTitle = review_Title.trim();
		String restaurant_name = restaurantName.trim();
		String submittedDate = dateFormat.format(new Date(System.currentTimeMillis()));

		int authorId = currentlyLoggedInUser.getUserId();

		Review addedReview = this.reviewDao.addNewReview(ratingInput, experienceReview, submittedDate, authorId, reviewTitle, restaurant_name);

		return addedReview;
	}

	public void deleteReviews(int reviewId) {
		logger.info("ReviewService.deleteReviews() invoked");

		this.reviewDao.deleteReviews(reviewId);
	}

	public Review getReviewByReviewId(int reviewId) {
		logger.info("ReviewService.getReviewByReviewId() invoked");

		Review review = this.reviewDao.getReviewsByReviewId(reviewId);

		return review;
	}

	public List<Review> getReviewsByRestaurantId(int restaurantId)
			throws ReviewDoesNotExist, InvalidParameterException {
		logger.info("ReviewService.getReviewsByRestaurantId() invoked");

		List<Review> reviews = this.reviewDao.getReviewsByRestaurantId(restaurantId);

		try {
			if (reviews.isEmpty()) {
				throw new ReviewDoesNotExist("No reviews on file for restaurant with Id of " + restaurantId + ".");
			}

			return reviews;

		} catch (DataAccessException e) {

			throw new InvalidParameterException("No reviews on file for restaurant with Id of " + restaurantId + ".");
		}
	}

}
