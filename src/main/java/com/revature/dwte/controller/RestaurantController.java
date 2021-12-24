package com.revature.dwte.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dwte.annotation.AdminAndMember;
import com.revature.dwte.exception.InvalidParameterException;
import com.revature.dwte.model.Restaurant;
import com.revature.dwte.model.User;
import com.revature.dwte.service.RestaurantService;
import com.revature.dwte.utility.ValidateUtil;

@RestController
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class RestaurantController {

	private Logger logger = LoggerFactory.getLogger(RestaurantController.class);

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private HttpServletRequest req;

	@Autowired
	private ValidateUtil validateUtil;

	private static final String CURRENTUSER = "currentuser";

	@PostMapping(path = "/restaurant")
	@AdminAndMember
	public ResponseEntity<Object> addRestaurant(@RequestBody Map<String, String> json)
			throws InvalidParameterException {
		logger.info("RestaurantController.addRestaurant() invoked");

		try {

			User currentlyLoggedInUser = (User) req.getSession().getAttribute(CURRENTUSER);

			if (currentlyLoggedInUser == null) {
				return ResponseEntity.status(401).body("You are not logged in, please log in to continue");
			}

			validateUtil.verifyAddRestaurant(json.get("restaurantName"), json.get("restaurantAddress"));

			Restaurant addedRestaurant = restaurantService.addRestaurant(json.get("restaurantName"),
					json.get("restaurantAddress"));

			return ResponseEntity.status(201).body(addedRestaurant);

		} catch (InvalidParameterException e) {

			return ResponseEntity.status(400).body(e.getMessage());
		}
	}
}
