package com.revature.dwte.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dwte.dao.RestaurantDao;
import com.revature.dwte.model.Restaurant;

@Service
public class RestaurantService implements RestaurantServiceInterface {

	private Logger logger = LoggerFactory.getLogger(RestaurantService.class);

	@Autowired
	private RestaurantDao restaurantDao;

	public Restaurant addRestaurant(String restaurantName, String restaurantAddress) {
		logger.info("RestaurantService.addRestaurant() invoked");

		restaurantName = restaurantName.trim();
		restaurantAddress = restaurantAddress.trim();

		Restaurant addedRestaurant = this.restaurantDao.addRestaurant(restaurantName, restaurantAddress);

		return addedRestaurant;
	}

	public Restaurant getRestaurantByRestaurantId(int restaurantId) {
		logger.info("RestaurantService.getRestaurantByRestaurantId() invoked");

		Restaurant restaurant = this.restaurantDao.getRestaurantByRestaurantId(restaurantId);

		return restaurant;
	}

	public Restaurant getRestaurantByRestaurantNameAndAddress(String restaurantName, String restaurantAddress) {
		logger.info("RestaurantService.getRestaurantByRestaurantNameAndAddress() invoked");

		Restaurant restaurant = this.restaurantDao.getRestaurantByRestaurantNameAndAddress(restaurantName,
				restaurantAddress);
		return restaurant;
	}

}
