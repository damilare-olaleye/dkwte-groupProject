package com.revature.dwte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dwte.dao.ReviewsDao;
import com.revature.dwte.dto.AddReviewDTO;
import com.revature.dwte.model.Review;
import com.revature.dwte.model.User;

@Service
public class ReviewService {

	@Autowired
	private ReviewsDao reviewDao;

	public List<Review> getReviews(User currentlyLoggedInUser) {

		return reviewDao.getAllReviews();

	}

	public Review getAddNewReview(User currentlyLoggedInUser, AddReviewDTO dto) {

		dto.setReview(dto.getReview().trim());

		return reviewDao.addNewReview(currentlyLoggedInUser.getUserId(), dto);

	}
}
