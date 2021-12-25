package com.revature.dwte.service;

import com.revature.dwte.exception.InvalidParameterException;
import com.revature.dwte.model.Restaurant;

public interface RestaurantServiceInterface {

	public Restaurant addRestaurant(String restaurantName, String restaurantAddress);

	public Restaurant getRestaurantByRestaurantId(int restaurantId) throws InvalidParameterException;

	public Restaurant getRestaurantByRestaurantNameAndAddress(String restaurantName, String restaurantAddress);
}
