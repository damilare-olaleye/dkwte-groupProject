package com.revature.dwte.unitTestDao;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.revature.dwte.dao.RestaurantDao;
import com.revature.dwte.model.Restaurant;
import com.revature.dwte.model.RestaurantCompositeKey;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class RestaurantDaoTest {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private RestaurantDao restaurantDao;

	/*-	*******************
	 * 	addRestaurant Tests
	 * 	*******************
	 */
	@Test
	@Transactional
	public void testAddRestaurant_possitive() {
		RestaurantCompositeKey restaurantToAdd = new RestaurantCompositeKey("Thai Deelish", "Sterling,VA");
		Restaurant restaurant = new Restaurant();
		restaurant.setRestaurantCompositeKey(restaurantToAdd);

		this.entityManager.persist(restaurant);

		Restaurant actual = this.restaurantDao.getRestaurantByRestaurantId(1);

		RestaurantCompositeKey expectedRestaurant = new RestaurantCompositeKey("Thai Deelish", "Sterling,VA");
		Restaurant expected = new Restaurant();
		expected.setRestaurantCompositeKey(expectedRestaurant);

		Assertions.assertEquals(expected, actual);

		this.entityManager.flush();

	}
}
