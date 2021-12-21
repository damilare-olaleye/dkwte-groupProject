package com.revature.dwte.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dwte.dao.RestaurantDao;
import com.revature.dwte.model.Restaurant;
import com.revature.dwte.model.User;

@Service
public class RestaurantService {

	private Logger logger = LoggerFactory.getLogger(RestaurantService.class);

	@Autowired
	private RestaurantDao restaurantDao;

	public Restaurant addRestaurant(Restaurant restaurant) {
		logger.info("RestaurantService.addRestaurant() invoked");

		restaurant.setRestaurantName(restaurant.getRestaurantName().trim());
		restaurant.setRestaurantAddress(restaurant.getRestaurantAddress().trim());

		Restaurant addedRestaurant = this.restaurantDao.addRestaurant(restaurant);

		return addedRestaurant;
	}

}
