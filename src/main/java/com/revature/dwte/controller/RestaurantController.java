package com.revature.dwte.controller;

import java.util.Map;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dwte.exception.InvalidParameterException;
import com.revature.dwte.model.Restaurant;
import com.revature.dwte.service.RestaurantService;
import com.revature.dwte.utility.ValidateUtil;

@RestController
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class RestaurantController {

	private Logger logger = LoggerFactory.getLogger(RestaurantController.class);

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private ValidateUtil validateUtil;

	@PostMapping(path = "/restaurant")
	public ResponseEntity<Object> addRestaurant(@RequestBody Map<String, String> json)
			throws InvalidParameterException {
		logger.info("RestaurantController.addRestaurant() invoked");

		try {

			validateUtil.verifyAddRestaurant(json.get("restaurantName"), json.get("restaurantAddress"));

			Restaurant addedRestaurant = restaurantService.addRestaurant(json.get("restaurantName"),
					json.get("restaurantAddress"));

			return ResponseEntity.status(201).body(addedRestaurant);

		} catch (InvalidParameterException e) {

			return ResponseEntity.status(400).body(e.getMessage());
		}
	}

	@GetMapping(path = "/restaurant")
	public ResponseEntity<Object> getRestaurantByRestaurantNameAndAddress(@RequestBody Map<String, String> json)
			throws InvalidParameterException {
		logger.info("RestaurantController.getRestaurantId() invoked");

		try {
			validateUtil.verifyIfRestaurantExist(json.get("restaurantName").trim(),
					json.get("restaurantAddress").trim());

			Restaurant restaurantId = restaurantService.getRestaurantByRestaurantNameAndAddress(
					json.get("restaurantName").trim(), json.get("restaurantAddress").trim());

			return ResponseEntity.status(200).body(restaurantId);
		} catch (NoResultException e) {
			return ResponseEntity.status(400).body(e.getMessage());

		} catch (InvalidParameterException e) {
			return ResponseEntity.status(400).body(e.getMessage());

		}
	}

	@GetMapping(path = "/restaruantById/{restaurantId}")
	public ResponseEntity<Object> getRestaruatnByRestaurantId(@PathVariable int restaurantId)
			throws InvalidParameterException {
		logger.info("RestaurantController.getRestaruatnByRestaurantId() invoked");

		try {
			validateUtil.verifyRestaurantId(restaurantId);

			Restaurant restaurant = restaurantService.getRestaurantByRestaurantId(restaurantId);

			return ResponseEntity.status(200).body(restaurant);
		} catch (NoResultException e) {
			return ResponseEntity.status(400).body(e.getMessage());

		} catch (InvalidParameterException e) {
			return ResponseEntity.status(400).body(e.getMessage());

		}

	}

}
