package com.revature.dwte.dao;

import java.util.List;

import com.revature.dwte.dto.AddReviewDTO;
import com.revature.dwte.model.Restaurant;
import com.revature.dwte.model.Review;

public interface ReviewsDaoInterface {

	public List<Review> getAllReviews();

	public Review addNewReview(String rating, String experienceReview, String submittedDate, int restaurantId,
			int authorId);

	public void deleteReviews(int reviewId);

	public Review getReviewsByReviewId(int reviewId);

	public List<Review> getReviewByRestaurantId(int restaurantId);

}
