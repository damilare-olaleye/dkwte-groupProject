package com.revature.dwte.service;

import java.util.List;

import com.revature.dwte.dto.AddReviewDTO;
import com.revature.dwte.model.Review;
import com.revature.dwte.model.User;

public interface ReviewServiceInterface {

	public List<Review> getReviews(User currentlyLoggedInUser);

	public Review getAddNewReview(User currentlyLoggedInUser, AddReviewDTO dto);
}
