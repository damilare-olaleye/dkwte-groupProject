package com.revature.dwte.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dwte.annotation.Member;
import com.revature.dwte.dto.AddReviewDTO;
import com.revature.dwte.model.Review;
import com.revature.dwte.model.User;
import com.revature.dwte.service.ReviewService;

@RestController
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class ReviewsController {

	private Logger logger = LoggerFactory.getLogger(ReviewsController.class);

	@Autowired
	private HttpServletRequest req;

	@Autowired
	private ReviewService reviewService;

	private static final String CURRENTUSER = "currentuser";

	@GetMapping(path = "/reviews")
	public ResponseEntity<Object> getAllReviews() {

		logger.info("getting all reviews ...");

		HttpSession session = req.getSession();

		User currentlyLoggedInUser = (User) session.getAttribute(CURRENTUSER);

		if (currentlyLoggedInUser == null) {
			return ResponseEntity.status(401).body("You are not logged in, please log in to continue");

		}

		List<Review> reviewsByMembers = reviewService.getReviews(currentlyLoggedInUser);

		return ResponseEntity.status(200).body(reviewsByMembers);

	}

	@PostMapping(path = "/newreviews")
	@Member
	public ResponseEntity<Object> addNewReviews(@RequestBody AddReviewDTO dto) {

		User currentlyLoggedInUser = (User) req.getSession().getAttribute(CURRENTUSER);
		Review addedReview = reviewService.getAddNewReview(currentlyLoggedInUser, dto);

		return ResponseEntity.status(201).body(addedReview);

	}

}
