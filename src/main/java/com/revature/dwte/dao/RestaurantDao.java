package com.revature.dwte.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.revature.dwte.model.Restaurant;
import com.revature.dwte.model.RestaurantCompositeKey;

@Repository
public class RestaurantDao implements RestaurantDaoInterface {

	private static Logger logger = LoggerFactory.getLogger(RestaurantDao.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public Restaurant addRestaurant(String restaurantName, String restaurantAddress) {
		logger.info("RestaurantDao.addRestaurant() invoked");

		RestaurantCompositeKey restaurantToAdd = new RestaurantCompositeKey(restaurantName, restaurantAddress);
		Restaurant restaurant = new Restaurant();
		restaurant.setRestaurantCompositeKey(restaurantToAdd);

		entityManager.persist(restaurant);

		return restaurant;
	}

	@Transactional
	public Restaurant getRestaurantByRestaurantId(int restaurantId) {
		logger.info("RestaurantDao.getRestaurantByRestaurantId() invoked");

		try {

			Restaurant restaurant = (Restaurant) entityManager
					.createQuery("FROM Restaurant r WHERE r.restaurantId = :restaurantId", Restaurant.class)
					.setParameter("restaurantId", restaurantId).getSingleResult();

			return restaurant;

		} catch (NoResultException e) {
			return null;
		}

	}

	@Transactional
	public Restaurant getRestaurantByRestaurantNameAndAddress(String restaurantName, String restaurantAddress) {
		logger.info("RestaurantDao.getRestaurantByRestaurantNameAndAddress() invoked");

		try {

			Restaurant restaurant = (Restaurant) entityManager.createQuery(
					"FROM Restaurant r WHERE r.restaurantCompositeKey.restaurantName = :restaurantName AND r.restaurantCompositeKey.restaurantAddress = :restaurantAddress",
					Restaurant.class).setParameter("restaurantName", restaurantName)
					.setParameter("restaurantAddress", restaurantAddress).getSingleResult();

			logger.info("restaurant {}", restaurant);
			return restaurant;
		} catch (NoResultException e) {
			return null;
		}

	}

}
