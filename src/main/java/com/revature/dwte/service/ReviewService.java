package com.revature.dwte.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dwte.dao.ReviewsDao;
import com.revature.dwte.dto.AddReviewDTO;
import com.revature.dwte.model.Review;
import com.revature.dwte.model.User;

@Service
public class ReviewService implements ReviewServiceInterface {

	private Logger logger = LoggerFactory.getLogger(ReviewService.class);

	@Autowired
	private ReviewsDao reviewDao;

	public List<Review> getReviews(User currentlyLoggedInUser) {
		logger.info("ReviewService.getReviews() invoked");

		return reviewDao.getAllReviews();

	}

	public Review getAddNewReview(User currentlyLoggedInUser, AddReviewDTO dto) {
		logger.info("ReviewService.getAddNewReview() invoked");

		dto.setReview(dto.getReview().trim());

		return reviewDao.addNewReview(currentlyLoggedInUser.getUserId(), dto);

	}

	public void deleteReviews(User currentlyLoggedInUser, int reviewId) {
		logger.info("ReviewService.deleteReviews() invoked");

		this.reviewDao.deleteReviews(currentlyLoggedInUser.getUserId(), reviewId);
	}

}
