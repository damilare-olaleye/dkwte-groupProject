package com.revature.dwte.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.revature.dwte.model.Restaurant;

@Repository
public class RestaurantDao {

	private static Logger logger = LoggerFactory.getLogger(RestaurantDao.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public Restaurant addRestaurant(Restaurant restaurant) {
		logger.info("RestaurantDao.addRestaurant() invoked");

		Restaurant addNewRestaurant = new Restaurant();

		addNewRestaurant.setRestaurantName(restaurant.getRestaurantName());
		addNewRestaurant.setRestaurantAddress(restaurant.getRestaurantAddress());

		entityManager.persist(addNewRestaurant);

		return addNewRestaurant;
	}

}
