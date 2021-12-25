package com.revature.dwte.dao;

import com.revature.dwte.model.Restaurant;

public interface RestaurantDaoInterface {

	public Restaurant addRestaurant(String restaurantName, String restaurantAddress);

	public Restaurant getRestaurantByRestaurantId(int restaurantId);

	public Restaurant getRestaurantByRestaurantNameAndAddress(String restaurantName, String restaurantAddress);
}
