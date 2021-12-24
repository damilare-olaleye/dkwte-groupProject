package com.revature.dwte.integrationtest;

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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dwte.model.Review;

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

	@Autowired
	private ArraysMapper<Review> arrayMapper;

	@BeforeEach
	public void setUp() {
		EntityManager entityManager = enitityManagerFactory.createEntityManager();
		Session session = entityManager.unwrap(Session.class);
		Transaction tx = session.beginTransaction();

		Review review = new Review("4", "the food is really good", "12-23-21", 1, 1);
		session.persist(review);

		Review review1 = new Review("3", "the food is okay", "11-11-21", 1, 1);
		session.persist(review1);
		tx.commit();

		session.close();
	}

	/*-	*******************
	 * 	getAllReviews Tests
	 * 	*******************
	 */
	@Test
	public void testGetAllReviews_posotive() throws Exception {
		Review review = new Review("4", "the food is really good", "12-23-21", 1, 1);
		String jsonToSend = objMapper.writeValueAsString(review);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/reviews").content(jsonToSend)
				.contentType(MediaType.APPLICATION_JSON);

		Review expectedReview = new Review("4", "the food is really good", "12-23-21", 1, 1);
		expectedReview.setReviewId(1);

		String expectedJsonReview = arrayMapper.(expectedReview);

		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().json(expectedJsonReview));

	}
}
