package com.revature.dwte.controller;

import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dwte.annotation.Admin;
import com.revature.dwte.annotation.AdminAndMember;
import com.revature.dwte.exception.InvalidParameterException;
import com.revature.dwte.exception.ReviewDoesNotExist;
import com.revature.dwte.model.Review;
import com.revature.dwte.model.User;
import com.revature.dwte.service.ReviewService;
import com.revature.dwte.utility.ValidateUtil;

@RestController
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class ReviewsController {

	private Logger logger = LoggerFactory.getLogger(ReviewsController.class);

	@Autowired
	private HttpServletRequest req;

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private ValidateUtil validateUtil;

	private static final String CURRENTUSER = "currentuser";

	// everyone can access
	@GetMapping(path = "/reviews")
	public ResponseEntity<Object> getAllReviews() throws ReviewDoesNotExist, InvalidParameterException {
		logger.info("ReviewsController.getAllReviews() invoked");

		try {
			List<Review> allReviews = reviewService.getAllReview();
			logger.debug("reviews {} ", allReviews);

			if (allReviews.isEmpty()) {
				throw new ReviewDoesNotExist();
			}

			return ResponseEntity.status(200).body(allReviews);

		} catch (ReviewDoesNotExist e) {

			return ResponseEntity.status(400).body(e.getMessage());

		}

	}

	@PostMapping(path = "/newreviews")
	@AdminAndMember
	public ResponseEntity<Object> addNewReviews(@RequestBody Map<String, String> json)
			throws InvalidParameterException {
		logger.info("ReviewsController.addNewReviews() invoked");

		try {
			User currentlyLoggedInUser = (User) req.getSession().getAttribute(CURRENTUSER);

			logger.debug("restaurant id from review {}", json.get("restaurantId"));

			validateUtil.verifyNewReview(json.get("rating"), json.get("review"), json.get("reviewTitle"), json.get("restaurant_name"));

			if (currentlyLoggedInUser == null) {
				return ResponseEntity.status(401).body("You are not logged in, please log in to continue");

			}

			Review addedReview = reviewService.addNewReview(currentlyLoggedInUser, json.get("rating"),
					json.get("review"), json.get("reviewTitle"), json.get("restaurant_name"));

			return ResponseEntity.status(201).body(addedReview);

		} catch (InvalidParameterException e) {

			return ResponseEntity.status(400).body(e.getMessage());

		}

	}

	@DeleteMapping(path = "/deletereviews/{reviewId}")
	@Admin
	public ResponseEntity<Object> deleteReviews(@PathVariable int reviewId) throws InvalidParameterException {
		logger.info("ReviewsController.deleteReviews() invoked");

		try {

			validateUtil.verifyRevieId(reviewId);

			this.reviewService.deleteReviews(reviewId);

			return ResponseEntity.status(201).body("Succesfully deleted review with id of " + reviewId);

		} catch (InvalidParameterException e) {

			return ResponseEntity.status(400).body(e.getMessage());
		}

	}

	// everyone can access
	@GetMapping(path = "/getReviewsByRestaurantId/{restaurantId}")
	public ResponseEntity<Object> getReviewsByRestaurantId(@PathVariable int restaurantId)
			throws ReviewDoesNotExist, NoResultException {
		logger.info("ReviewsController.getReviewsByRestaurantId() invoked");

		try {

			validateUtil.verifyRestaurantId(restaurantId);

			List<Review> reviews = reviewService.getReviewsByRestaurantId(restaurantId);

			return ResponseEntity.status(200).body(reviews);

		} catch (InvalidParameterException e) {

			return ResponseEntity.status(400).body(e.getMessage());
		} catch (NoResultException e) {
			return ResponseEntity.status(400).body(e.getMessage());

		} catch (ReviewDoesNotExist e) {
			return ResponseEntity.status(400).body(e.getMessage());

		}

	}

}
