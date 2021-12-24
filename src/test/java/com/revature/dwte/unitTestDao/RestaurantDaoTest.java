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

		Restaurant restaurant = this.restaurantDao.addRestaurant("Thai Deelish", "Sterling,VA");

		this.entityManager.persist(restaurant);

		Restaurant actual = this.restaurantDao.getRestaurantByRestaurantId(1);

		RestaurantCompositeKey expectedRestaurant = new RestaurantCompositeKey("Thai Deelish", "Sterling,VA");
		Restaurant expected = new Restaurant();
		expected.setRestaurantCompositeKey(expectedRestaurant);
		expected.setRestaurantId(1);

		Assertions.assertEquals(expected, actual);

		this.entityManager.flush();

	}

	@Test
	@Transactional
	public void testAddRestaurant_blankRestaurantName_negative() {
		Restaurant restaurant = this.restaurantDao.addRestaurant("Thai Deelish", "Sterling,VA");

		this.entityManager.persist(restaurant);

		Restaurant actual = this.restaurantDao.getRestaurantByRestaurantId(1);

		RestaurantCompositeKey expectedRestaurant = new RestaurantCompositeKey(null, "Sterling,VA");
		Restaurant expected = new Restaurant();
		expected.setRestaurantCompositeKey(expectedRestaurant);
		expected.setRestaurantId(1);

		Assertions.assertNotEquals(expected, actual);

		this.entityManager.flush();

	}

	@Test
	@Transactional
	public void testAddRestaurant_blankRestaurantAddress_negative() {
		Restaurant restaurant = this.restaurantDao.addRestaurant("Thai Deelish", "Sterling,VA");

		this.entityManager.persist(restaurant);

		Restaurant actual = this.restaurantDao.getRestaurantByRestaurantId(1);

		RestaurantCompositeKey expectedRestaurant = new RestaurantCompositeKey("Thai Deelish", null);
		Restaurant expected = new Restaurant();
		expected.setRestaurantCompositeKey(expectedRestaurant);
		expected.setRestaurantId(1);

		Assertions.assertNotEquals(expected, actual);

		this.entityManager.flush();

	}

	@Test
	@Transactional
	public void testAddRestaurant_blankRestaurantNameAndAddress_negative() {
		Restaurant restaurant = this.restaurantDao.addRestaurant("Thai Deelish", "Sterling,VA");

		this.entityManager.persist(restaurant);

		Restaurant actual = this.restaurantDao.getRestaurantByRestaurantId(1);

		RestaurantCompositeKey expectedRestaurant = new RestaurantCompositeKey(null, null);
		Restaurant expected = new Restaurant();
		expected.setRestaurantCompositeKey(expectedRestaurant);
		expected.setRestaurantId(1);

		Assertions.assertNotEquals(expected, actual);

		this.entityManager.flush();

	}

	/*-	*********************************
	 * 	getRestaurantByRestaurantId Tests
	 * 	*********************************
	 */
	@Test
	@Transactional
	public void testGetRestaurantByRestaurantId_positive() {
		RestaurantCompositeKey restaurantToAdd = new RestaurantCompositeKey("Thai Deelish", "Sterling,VA");
		Restaurant restaurant = new Restaurant();
		restaurant.setRestaurantCompositeKey(restaurantToAdd);

		this.entityManager.persist(restaurant);

		Restaurant actual = this.restaurantDao.getRestaurantByRestaurantId(1);

		RestaurantCompositeKey expectedRestaurant = new RestaurantCompositeKey("Thai Deelish", "Sterling,VA");
		Restaurant expected = new Restaurant();
		expected.setRestaurantCompositeKey(expectedRestaurant);
		expected.setRestaurantId(1);

		Assertions.assertEquals(expected, actual);

	}

	@Test
	@Transactional
	public void testGetRestaurantByRestaurantId_restaurantIdBlank_negative() {
		RestaurantCompositeKey restaurantToAdd = new RestaurantCompositeKey("Thai Deelish", "Sterling,VA");
		Restaurant restaurant = new Restaurant();
		restaurant.setRestaurantCompositeKey(restaurantToAdd);

		this.entityManager.persist(restaurant);

		Restaurant actual = this.restaurantDao.getRestaurantByRestaurantId(0);

		RestaurantCompositeKey expectedRestaurant = new RestaurantCompositeKey("Thai Deelish", "Sterling,VA");
		Restaurant expected = new Restaurant();
		expected.setRestaurantCompositeKey(expectedRestaurant);
		expected.setRestaurantId(1);

		Assertions.assertNotEquals(expected, actual);

	}

	@Test
	@Transactional
	public void testGetRestaurantByRestaurantId_restaurantDoesNotExist_negative() {
		RestaurantCompositeKey restaurantToAdd = new RestaurantCompositeKey("Thai Deelish", "Sterling,VA");
		Restaurant restaurant = new Restaurant();
		restaurant.setRestaurantCompositeKey(restaurantToAdd);

		this.entityManager.persist(restaurant);

		Restaurant expected = this.restaurantDao.getRestaurantByRestaurantId(1);

		Restaurant actual = new Restaurant();

		Assertions.assertNotEquals(expected, actual);

	}

	/*-	*********************************************
	 * 	getRestaurantByRestaurantNameAndAddress Tests
	 * 	*********************************************
	 */
	@Test
	@Transactional
	public void testGetRestaurantByRestaurantNameAndAddress_posotive() {
		RestaurantCompositeKey restaurantToAdd = new RestaurantCompositeKey("Thai Deelish", "Sterling,VA");
		Restaurant restaurant = new Restaurant();
		restaurant.setRestaurantCompositeKey(restaurantToAdd);

		this.entityManager.persist(restaurant);

		Restaurant actual = this.restaurantDao.getRestaurantByRestaurantNameAndAddress("Thai Deelish", "Sterling,VA");

		RestaurantCompositeKey expectedRestaurant = new RestaurantCompositeKey("Thai Deelish", "Sterling,VA");
		Restaurant expected = new Restaurant();
		expected.setRestaurantCompositeKey(expectedRestaurant);
		expected.setRestaurantId(1);

		Assertions.assertEquals(expected, actual);
	}

	@Test
	@Transactional
	public void testGetRestaurantByRestaurantNameAndAddress_blankRestaurantName_negative() {
		RestaurantCompositeKey restaurantToAdd = new RestaurantCompositeKey("Thai Deelish", "Sterling,VA");
		Restaurant restaurant = new Restaurant();
		restaurant.setRestaurantCompositeKey(restaurantToAdd);

		this.entityManager.persist(restaurant);

		Restaurant actual = this.restaurantDao.getRestaurantByRestaurantNameAndAddress(null, "Sterling,VA");

		RestaurantCompositeKey expectedRestaurant = new RestaurantCompositeKey("Thai Deelish", "Sterling,VA");
		Restaurant expected = new Restaurant();
		expected.setRestaurantCompositeKey(expectedRestaurant);
		expected.setRestaurantId(1);

		Assertions.assertNotEquals(expected, actual);
	}

	@Test
	@Transactional
	public void testGetRestaurantByRestaurantNameAndAddress_blankRestaurantAddress_negative() {
		RestaurantCompositeKey restaurantToAdd = new RestaurantCompositeKey("Thai Deelish", "Sterling,VA");
		Restaurant restaurant = new Restaurant();
		restaurant.setRestaurantCompositeKey(restaurantToAdd);

		this.entityManager.persist(restaurant);

		Restaurant actual = this.restaurantDao.getRestaurantByRestaurantNameAndAddress("Thai Deelish", null);

		RestaurantCompositeKey expectedRestaurant = new RestaurantCompositeKey("Thai Deelish", "Sterling,VA");
		Restaurant expected = new Restaurant();
		expected.setRestaurantCompositeKey(expectedRestaurant);
		expected.setRestaurantId(1);

		Assertions.assertNotEquals(expected, actual);
	}

	@Test
	@Transactional
	public void testGetRestaurantByRestaurantNameAndAddress_blankRestaurantNameAndAddress_negative() {
		RestaurantCompositeKey restaurantToAdd = new RestaurantCompositeKey("Thai Deelish", "Sterling,VA");
		Restaurant restaurant = new Restaurant();
		restaurant.setRestaurantCompositeKey(restaurantToAdd);

		this.entityManager.persist(restaurant);

		Restaurant actual = this.restaurantDao.getRestaurantByRestaurantNameAndAddress(null, null);

		RestaurantCompositeKey expectedRestaurant = new RestaurantCompositeKey("Thai Deelish", "Sterling,VA");
		Restaurant expected = new Restaurant();
		expected.setRestaurantCompositeKey(expectedRestaurant);
		expected.setRestaurantId(1);

		Assertions.assertNotEquals(expected, actual);
	}

	@Test
	@Transactional
	public void testGetRestaurantByRestaurantNameAndAddress_restaurantDoesNotExist_negative() {
		RestaurantCompositeKey restaurantToAdd = new RestaurantCompositeKey("Thai Deelish", "Sterling,VA");
		Restaurant restaurant = new Restaurant();
		restaurant.setRestaurantCompositeKey(restaurantToAdd);

		this.entityManager.persist(restaurant);

		Restaurant expected = this.restaurantDao.getRestaurantByRestaurantNameAndAddress("Thai Deelish", "Sterling,VA");

		Restaurant actual = new Restaurant();

		expected.setRestaurantId(1);

		Assertions.assertNotEquals(expected, actual);
	}
}
