package com.revature.dwte.integrationtest;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dwte.dto.LoginDTO;
import com.revature.dwte.model.Review;
import com.revature.dwte.model.User;

import net.minidev.json.writer.ArraysMapper;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ReviewControllerTest {

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

		Review goodReview = new Review("4", "the food is really good", "12-23-21", 1, "good food", "Panda Express");
		session.persist(goodReview);

		tx.commit();

		session.close();
	}

	/*-	*******************
	 * 	getAllReviews Tests
	 * 	*******************
	 */ // commented this out for now
//	@Test
//	public void testGetAllReviews_positive() throws Exception {
//		Review review = new Review("4", "the food is really good", "12-23-21", 1, "good food", "Panda Express");
//		review.setReviewId(1);
//		Review review1 = new Review("3", "the food is okay", "11-11-21", 1, "good food", "Panda Express");
//		review1.setReviewId(2);
//
//		List<Review> reviewsList = new ArrayList<>();
//		reviewsList.add(review);
//		reviewsList.add(review1);
//
//		System.out.println("reviewsList" + reviewsList);
//
//		String jsonToSend = objMapper.writeValueAsString(reviewsList);
//
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/reviews").content(jsonToSend)
//				.contentType(MediaType.APPLICATION_JSON);
//
//		Review expectedReview = new Review("4", "the food is really good", "12-23-21", 1, "good food", "Panda Express");
//		expectedReview.setReviewId(1);
//		Review expectedReview1 = new Review("3", "the food is okay", "11-11-21", 1, "good food", "Panda Express");
//		review1.setReviewId(2);
//
//		List<Review> expectedReviewsList = new ArrayList<>();
//		expectedReviewsList.add(expectedReview);
//		expectedReviewsList.add(expectedReview1);
//
//		System.out.println("expectedReviewsList" + expectedReviewsList);
//
//		String expectedJsonReviewList = objMapper.writeValueAsString(expectedReviewsList);
//
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200))
//				.andExpect(MockMvcResultMatchers.content().json(expectedJsonReviewList));
//	}

	// commenting this out for now
//	@Test
//	public void testGetAllReviews_positiveasdf() throws Exception {
//		Review review = new Review("4", "the food is really good", "12-23-21", 1, "good food", "Panda Express");
//		review.setReviewId(1);
//		Review review1 = new Review("3", "the food is okay", "11-11-21", 1, "good food", "Panda Express");
//		review1.setReviewId(2);
//
//		List<Review> reviewsList = new ArrayList<>();
//		reviewsList.add(review);
//		reviewsList.add(review1);
//
//		System.out.println("reviewsList" + reviewsList);
//
//		String jsonToSend = objMapper.writeValueAsString(reviewsList);
//
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/reviews").content(jsonToSend)
//				.contentType(MediaType.APPLICATION_JSON);
//
//		Review expectedReview = new Review("4", "the food is really good", "12-23-21", 1, "good food", "Panda Express");
//		expectedReview.setReviewId(1);
//		Review expectedReview1 = new Review("3", "the food is okay", "11-11-21", 1, "good food", "Panda Express");
//		review1.setReviewId(2);
//
//		List<Review> expectedReviewsList = new ArrayList<>();
//		expectedReviewsList.add(expectedReview);
//		expectedReviewsList.add(expectedReview1);
//
//		System.out.println("expectedReviewsList" + expectedReviewsList);
//
//		String expectedJsonReviewList = objMapper.writeValueAsString(expectedReviewsList);
//
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200))
//				.andExpect(MockMvcResultMatchers.content().json(expectedJsonReviewList));
//	}

	/*-	*******************
	 *	addNewReviews Tests
	 *	*******************
	 */ //Can you double check this? thank you 
//	@Test
//	public void testAddNewReviews_positive() throws Exception {
//		MockHttpSession session = new MockHttpSession();
//
//		User user = new User();
//		user.setEmail("jane_doe@gmail.com");
//		user.setPassword("Jane!123");
//		session.setAttribute("user", user);
//
//		Review review = new Review("4", "the food is really good", "12-23-21", 1, "good food", "Panda Express");
//		String jsonTosend = objMapper.writeValueAsString(review);
//
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/newreviews").content(jsonTosend)
//				.contentType(MediaType.APPLICATION_JSON);
//
//		Review expectedReview = new Review("4", "the food is really good", "12-23-21", 1, "good food", "Panda Express");
//		expectedReview.setReviewId(1);
//
//		String expectedJsonReview = objMapper.writeValueAsString(expectedReview);
//
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200))
//				.andExpect(MockMvcResultMatchers.content().json(expectedJsonReview));
//	}
}
