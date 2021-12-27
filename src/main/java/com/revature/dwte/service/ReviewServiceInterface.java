package com.revature.dwte.service;

import java.util.List;

import com.revature.dwte.exception.InvalidParameterException;
import com.revature.dwte.exception.ReviewDoesNotExist;
import com.revature.dwte.model.Review;
import com.revature.dwte.model.User;

public interface ReviewServiceInterface {

	public List<Review> getAllReview() throws ReviewDoesNotExist, InvalidParameterException;

	public Review addNewReview(User currentlyLoggedInUser, String rating, String review, String review_Title, String restaurantName);

	public void deleteReviews(int reviewId);

	public Review getReviewByReviewId(int reviewId);

	public List<Review> getReviewsByRestaurantId(int restaurantId) throws ReviewDoesNotExist, InvalidParameterException;

}
