package com.revature.dwte.dao;

import java.util.List;

import com.revature.dwte.dto.AddReviewDTO;
import com.revature.dwte.model.Review;

public interface ReviewsDaoInterface {

	public List<Review> getAllReviews();

	public Review addNewReview(int userIdOfCurrentlyLoggedInUser, AddReviewDTO dto);

//	public List<Review> getAllReviewsByResturantId(int resturantId);

}
