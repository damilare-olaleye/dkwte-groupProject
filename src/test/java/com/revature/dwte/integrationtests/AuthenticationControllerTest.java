package com.revature.dwte.integrationtests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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
import com.revature.dwte.model.User;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class AuthenticationControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private EntityManagerFactory emf;

	@Autowired
	private ObjectMapper mapper;

	@BeforeEach
	public void setUp() {

		EntityManager em = emf.createEntityManager();
		Session session = em.unwrap(Session.class);
		Transaction tx = session.beginTransaction();

		User user1 = new User("Luccas", "Poldoski", "yyJane@yahoo.com", "disIsMyPassword13", "25039008372", "Member");
		session.persist(user1);

		User user2 = new User("Jane", "Mary", "mjane@yahoo.com", "disIsMyPassword13", "4539008372", "Admin");
		session.persist(user2);

		tx.commit();

		session.close();

	}

	@Test
	public void testLogin_MemberPositive() throws Exception {

		// ARRANGE

		LoginDTO dto = new LoginDTO("yyJane@yahoo.com", "disIsMyPassword13");
		String jsonToSend = mapper.writeValueAsString(dto);

		// ACT AND ASSERT
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/login").content(jsonToSend)
				.contentType(MediaType.APPLICATION_JSON);

		User expectedObject = new User("Luccas", "Poldoski", "yyJane@yahoo.com", "disIsMyPassword13", "25039008372", "Member");
		expectedObject.setUserId(1);

		String expectedJson = mapper.writeValueAsString(expectedObject);

		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().json(expectedJson));

	}
}
