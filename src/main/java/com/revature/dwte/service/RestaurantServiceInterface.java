package com.revature.dwte.service;

import com.revature.dwte.model.Restaurant;

public interface RestaurantServiceInterface {

	public Restaurant addRestaurant(String restaurantName, String restaurantAddress);

	public Restaurant getRestaurantByRestaurantId(Integer restaurantId);

	public Restaurant getRestaurantByRestaurantNameAndAddress(String restaurantName, String restaurantAddress);
}
