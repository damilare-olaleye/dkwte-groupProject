package com.revature.dwte.service;

import java.util.List;

import com.revature.dwte.dto.AddReviewDTO;
import com.revature.dwte.model.Restaurant;
import com.revature.dwte.model.Review;
import com.revature.dwte.model.User;

public interface ReviewServiceInterface {

	public List<Review> getAllReview();

	public Review addNewReview(User currentlyLoggedInUser, String rating, String review, String restaurantId);

	public void deleteReviews(int reviewId);

	public Review getReviewByReviewId(int reviewId);

	public List<Review> getReviewsByRestaurantId(int restaurantId);

}
