package com.revature.dwte.integrationtest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dwte.model.Restaurant;
import com.revature.dwte.model.RestaurantCompositeKey;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class RestaurantControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private EntityManagerFactory enitityManagerFactory;

	@Autowired
	private ObjectMapper objMapper;

	@BeforeEach
	public void setUp() {

		EntityManager entityManager = enitityManagerFactory.createEntityManager();
		Session session = entityManager.unwrap(Session.class);
		Transaction tx = session.beginTransaction();

		RestaurantCompositeKey restaurantToAdd = new RestaurantCompositeKey("Thai Deelish", "Reston, VA");
		Restaurant restaurant = new Restaurant();
		restaurant.setRestaurantCompositeKey(restaurantToAdd);
		session.persist(restaurant);

		tx.commit();

		session.close();
	}

	/*-	******************
	 *  addRestaurant Test
	 *  ******************
	 */
//	@Test
//	public void testAddRestaurant_positive() throws Exception {
//
//		/*-
//		 *  ARRANGE
//		 */
//		RestaurantCompositeKey restaurantToAdd = new RestaurantCompositeKey("Thai Deelish", "Reston, VA");
//		Restaurant restaurant = new Restaurant();
//		restaurant.setRestaurantCompositeKey(restaurantToAdd);
//		String jsonToSend = objMapper.writeValueAsString(restaurant);
//
//		/*-
//		 *  ACT and ASSERT
//		 */
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/restaurant").content(jsonToSend)
//				.contentType(MediaType.APPLICATION_JSON);
//
//		RestaurantCompositeKey expectedRestaurantToAdd = new RestaurantCompositeKey("Thai Deelish", "Reston, VA");
//		Restaurant expectedRestaurant = new Restaurant();
//		restaurant.setRestaurantCompositeKey(expectedRestaurantToAdd);
//		expectedRestaurant.setRestaurantId(1);
//
//		String expectedJsonRestaurant = objMapper.writeValueAsString(expectedRestaurant);
//
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200))
//				.andExpect(MockMvcResultMatchers.content().json(expectedJsonRestaurant));
//
//	}

	/*-	******************
	 * 	getRestaurant Test
	 * 	******************
	 */
	@Test
	public void testGetRestaurant_positive() throws JsonProcessingException {

		/*-
		 *  ARRANGE
		 */
		RestaurantCompositeKey restaurantToAdd = new RestaurantCompositeKey("Thai Deelish", "Reston, VA");
		Restaurant restaurant = new Restaurant();
		restaurant.setRestaurantCompositeKey(restaurantToAdd);
		String jsonToSend = objMapper.writeValueAsString(restaurant);

		/*-
		 *  ACT and ASSERT
		 */
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/restaurant").content(jsonToSend)
				.contentType(MediaType.APPLICATION_JSON);
		
		
	}
}
