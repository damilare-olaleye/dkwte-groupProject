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
	private EntityManagerFactory enitityManagerFactory;

	@Autowired
	private ObjectMapper objMapper;

	@BeforeEach
	public void setUp() {

		EntityManager entityManager = enitityManagerFactory.createEntityManager();
		Session session = entityManager.unwrap(Session.class);
		Transaction tx = session.beginTransaction();

		User jane = new User("Jane", "Doe", "jane_doe1@gmail.com",
				"679F2B2219FB2CAE95F01730A4BAA03C25C94681BEF694827CBEDAD4562C8C8B", "5712561234", "Admin");
		session.persist(jane);

		User john = new User("John", "Doe", "john_doe@gmail.com",
				"BFBFE08EDB20E274563CD69250F4E1BE3C17BBB919A68082B7FB82802E10FFE0", "7034567890", "Member");
		session.persist(john);

		tx.commit();

		session.close();

	}

	/*- ************
	 *  signUp Tests
	 *  ************
	 */
	@Test
	public void testSignUp_admin_positive() throws Exception {

		/*-
		 *  ARRANGE
		 */
		User user = new User("Jenny", "Doe", "jenny_doe@gmail.com", "Jenny123", "1234567890", "Admin");
		String jsonToSend = objMapper.writeValueAsString(user);

		/*-
		 *  ACT and ASSERT
		 */
		// send fake http request to /signup
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/signup").content(jsonToSend)
				.contentType(MediaType.APPLICATION_JSON);

		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().string("Successfully signed up."));
	}

	@Test
	public void testSignUp_member_positive() throws Exception {

		/*-
		 *  ARRANGE
		 */
		User user = new User("Jinx", "Rocket", "jinx_rocket1@gmail.com", "Jinx123456789", "9786543210", "Member");
		String jsonToSend = objMapper.writeValueAsString(user);

		/*-
		 *  ACT and ASSERT
		 */
		// send fake http request to /signup
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/signup").content(jsonToSend)
				.contentType(MediaType.APPLICATION_JSON);

		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().string("Successfully signed up."));
	}

	@Test
	public void testSignUp_invalidFirstName_negative() throws Exception {

		/*-
		 *  ARRANGE
		 */
		User user = new User("123", "Doe", "123_doe@gmail.com", "John1234", "4561230789", "Member");
		String jsonToSend = objMapper.writeValueAsString(user);

		/*-
		 *  ACT and ASSERT
		 */
		// send fake http request to /signup
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/signup").content(jsonToSend)
				.contentType(MediaType.APPLICATION_JSON);

		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers
				.content().string("First name invalid. Names cannot contain any symbols or numbers."));
	}

	@Test
	public void testSignUp_invalidLastName_negative() throws Exception {

		/*-
		 *  ARRANGE
		 */
		User user = new User("John", "123", "john_123@gmail.com", "John1234", "9876543210", "Member");
		String jsonToSend = objMapper.writeValueAsString(user);

		/*-
		 *  ACT and ASSERT
		 */
		// send fake http request to /signup
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/signup").content(jsonToSend)
				.contentType(MediaType.APPLICATION_JSON);

		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers
				.content().string("Last name invalid. Names cannot contain any symbols or numbers."));
	}

	@Test
	public void testSignUp_invalidFirstAndLastName_negative() throws Exception {

		/*-
		 *  ARRANGE
		 */
		User user = new User("123", "123", "123_123@gmail.com", "John1234", "1231230456", "Member");
		String jsonToSend = objMapper.writeValueAsString(user);

		/*-
		 *  ACT and ASSERT
		 */
		// send fake http request to /signup
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/signup").content(jsonToSend)
				.contentType(MediaType.APPLICATION_JSON);

		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers
				.content().string("First name, last name invalid. Names cannot contain any symbols or numbers."));
	}

	@Test
	public void testSignUp_invalidEmail_negative() throws Exception {

		/*-
		 *  ARRANGE
		 */
		User user = new User("Yennifer", "Vengerburg", "Yennifer_Vengerburg", "Yennifer", "1234567899", "Member");
		String jsonToSend = objMapper.writeValueAsString(user);

		/*-
		 *  ACT and ASSERT
		 */
		// send fake http request to /signup
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/signup").content(jsonToSend)
				.contentType(MediaType.APPLICATION_JSON);

		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400))
				.andExpect(MockMvcResultMatchers.content().string("Invalid Email."));
	}

	@Test
	public void testSignUp_invalidPhoneNumber_negative() throws Exception {

		/*-
		 *  ARRANGE
		 */
		User user = new User("Geralt", "Rivia", "geralt_rivia@gmail.com", "geraltrivia123", "123", "Admin");
		String jsonToSend = objMapper.writeValueAsString(user);

		/*-
		 *  ACT and ASSERT
		 */
		// send fake http request to /signup
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/signup").content(jsonToSend)
				.contentType(MediaType.APPLICATION_JSON);

		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers
				.content().string("Invalid phone number. Phone number must be 10 digits and no symbols."));
	}

	@Test
	public void testSignUp_invalidUserRole_negative() throws Exception {

		/*-
		 *  ARRANGE
		 */
		User user = new User("James", "Bond", "jamesbond@gmail.com", "jamesbond", "0070070007", "Collateral Damage");
		String jsonToSend = objMapper.writeValueAsString(user);

		/*-
		 *  ACT and ASSERT
		 */
		// send fake http request to /signup
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/signup").content(jsonToSend)
				.contentType(MediaType.APPLICATION_JSON);

		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400))
				.andExpect(MockMvcResultMatchers.content().string("User role must be 'member' or 'admin'."));
	}

	@Test
	public void testSignUp_blankFirstName_negative() throws Exception {

		/*-
		 *  ARRANGE
		 */
		User user = new User("", "Claus", "santaclaus@gmail.com", "santaClaus123", "1225122521", "Member");
		String jsonToSend = objMapper.writeValueAsString(user);

		/*-
		 *  ACT and ASSERT
		 */
		// send fake http request to /signup
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/signup").content(jsonToSend)
				.contentType(MediaType.APPLICATION_JSON);

		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400))
				.andExpect(MockMvcResultMatchers.content().string("First name cannot be blank."));
	}

	@Test
	public void testSignUp_blankLastName_negative() throws Exception {

		/*-
		 *  ARRANGE
		 */
		User user = new User("Santa", "", "santaclaus@gmail.com", "santaClaus123", "1225122521", "Member");
		String jsonToSend = objMapper.writeValueAsString(user);

		/*-
		 *  ACT and ASSERT
		 */
		// send fake http request to /signup
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/signup").content(jsonToSend)
				.contentType(MediaType.APPLICATION_JSON);

		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400))
				.andExpect(MockMvcResultMatchers.content().string("Last name cannot be blank."));
	}

	@Test
	public void testSignUp_blankEmail_negative() throws Exception {

		/*-
		 *  ARRANGE
		 */
		User user = new User("Santa", "Claus", "", "santaClaus123", "1225122521", "Member");
		String jsonToSend = objMapper.writeValueAsString(user);

		/*-
		 *  ACT and ASSERT
		 */
		// send fake http request to /signup
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/signup").content(jsonToSend)
				.contentType(MediaType.APPLICATION_JSON);

		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400))
				.andExpect(MockMvcResultMatchers.content().string("Email cannot be blank."));
	}

	@Test
	public void testSignUp_blankPassword_negative() throws Exception {

		/*-
		 *  ARRANGE
		 */
		User user = new User("Santa", "Claus", "santaclaus@gmail.com", "", "1225122521", "Member");
		String jsonToSend = objMapper.writeValueAsString(user);

		/*-
		 *  ACT and ASSERT
		 */
		// send fake http request to /signup
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/signup").content(jsonToSend)
				.contentType(MediaType.APPLICATION_JSON);

		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400))
				.andExpect(MockMvcResultMatchers.content().string("Password cannot be blank."));
	}

	@Test
	public void testSignUp_blankPhoneNumber_negative() throws Exception {

		/*-
		 *  ARRANGE
		 */
		User user = new User("Santa", "Claus", "santaclaus@gmail.com", "santaClaus123", "", "Member");
		String jsonToSend = objMapper.writeValueAsString(user);

		/*-
		 *  ACT and ASSERT
		 */
		// send fake http request to /signup
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/signup").content(jsonToSend)
				.contentType(MediaType.APPLICATION_JSON);

		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400))
				.andExpect(MockMvcResultMatchers.content().string("Phone number cannot be blank."));
	}

	@Test
	public void testSignUp_blankUserRole_negative() throws Exception {

		/*-
		 *  ARRANGE
		 */
		User user = new User("Santa", "Claus", "santaclaus@gmail.com", "santaClaus123", "1225122521", "");
		String jsonToSend = objMapper.writeValueAsString(user);

		/*-
		 *  ACT and ASSERT
		 */
		// send fake http request to /signup
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/signup").content(jsonToSend)
				.contentType(MediaType.APPLICATION_JSON);

		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400))
				.andExpect(MockMvcResultMatchers.content().string("User role cannot be blank."));
	}

	@Test
	public void testSignUp_allInputsBlank_negative() throws Exception {

		/*-
		 *  ARRANGE
		 */
		User user = new User("", "", "", "", "", "");
		String jsonToSend = objMapper.writeValueAsString(user);

		/*-
		 *  ACT and ASSERT
		 */
		// send fake http request to /signup
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/signup").content(jsonToSend)
				.contentType(MediaType.APPLICATION_JSON);

		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400)).andExpect(MockMvcResultMatchers
				.content().string("First name, last name, email, password, phone number, user role cannot be blank."));
	}

	/*- ***********
	 * 	logIn Tests
	 * 	***********
	 */
//	@Test
//	public void testLogin_admin_positive() throws Exception {
//
//		// ARRANGE
//		LoginDTO user = new LoginDTO("jane_doe1@gmail.com", "Jane!123");
//		String jsonToSend = objMapper.writeValueAsString(user);
//
//		/*-
//		 *  ACT and ASSERT
//		 */
//		// send fake http request to /login
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/login").content(jsonToSend)
//				.contentType(MediaType.APPLICATION_JSON);
//
//		User expectedObject = new User("Jane", "Doe", "jane_doe1@gmail.com", "Jane!123", "5712561234", "Admin");
//		expectedObject.setUserId(1);
//
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200)).andExpect(
//				MockMvcResultMatchers.content().string("Sucessfully logged in as " + expectedObject.getFirst_name()
//						+ " " + expectedObject.getLast_name() + ", " + expectedObject.getRole()));
//
//	}
//
//	@Test
//	public void testLogin_member_positive() throws Exception {
//
//		/*-
//		 *  ARRANGE
//		 */
//		LoginDTO user = new LoginDTO("john_doe@gmail.com", "John1234");
//		String jsonToSend = objMapper.writeValueAsString(user);
//
//		/*-
//		 *  ACT and ASSERT
//		 */
//		// send fake http request to /login
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/login").content(jsonToSend)
//				.contentType(MediaType.APPLICATION_JSON);
//
//		User expectedObject = new User("John", "Doe", "john_doe@gmail.com", "John1234", "7034567890", "Member");
//		expectedObject.setUserId(1);
//
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200)).andExpect(
//				MockMvcResultMatchers.content().string("Sucessfully logged in as " + expectedObject.getFirst_name()
//						+ " " + expectedObject.getLast_name() + ", " + expectedObject.getRole()));

//	} 

	@Test
	public void testLogIn_invalidEmail_negative() throws Exception {

		/*-
		 *  ARRANGE
		 */
		LoginDTO user = new LoginDTO("notJohnDoe@gmail.com", "John1234");
		String jsonToSend = objMapper.writeValueAsString(user);

		/*-
		 *  ACT and ASSERT
		 */
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/login").content(jsonToSend)
				.contentType(MediaType.APPLICATION_JSON);

		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400))
				.andExpect(MockMvcResultMatchers.content().string("Incorrect email and/or password"));
	}

	@Test
	public void testLogIn_invalidPassword_negative() throws Exception {

		/*-
		 *  ARRANGE
		 */
		LoginDTO user = new LoginDTO("john_doe@gmail.com", "0123456789");
		String jsonToSend = objMapper.writeValueAsString(user);

		/*-
		 *  ACT and ASSERT
		 */
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/login").content(jsonToSend)
				.contentType(MediaType.APPLICATION_JSON);

		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400))
				.andExpect(MockMvcResultMatchers.content().string("Incorrect email and/or password"));
	}

	@Test
	public void testLogIn_invalidEmailAndPassword_negative() throws Exception {

		/*-
		 *  ARRANGE
		 */
		LoginDTO user = new LoginDTO("notJohnDoe@gmail.com", "0123456789");
		String jsonToSend = objMapper.writeValueAsString(user);

		/*-
		 *  ACT and ASSERT
		 */
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/login").content(jsonToSend)
				.contentType(MediaType.APPLICATION_JSON);

		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400))
				.andExpect(MockMvcResultMatchers.content().string("Incorrect email and/or password"));
	}

	@Test
	public void testLogIn_blankEmail_negative() throws Exception {

		/*-
		 *  ARRANGE
		 */
		LoginDTO user = new LoginDTO(null, "John1234");
		String jsonToSend = objMapper.writeValueAsString(user);

		/*-
		 *  ACT and ASSERT
		 */
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/login").content(jsonToSend)
				.contentType(MediaType.APPLICATION_JSON);

		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400))
				.andExpect(MockMvcResultMatchers.content().string("Email cannot be blank."));
	}

	@Test
	public void testLogIn_blankPassword_negative() throws Exception {

		/*-
		 *  ARRANGE
		 */
		LoginDTO user = new LoginDTO("john_doe@gmail.com", null);
		String jsonToSend = objMapper.writeValueAsString(user);

		/*-
		 *  ACT and ASSERT
		 */
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/login").content(jsonToSend)
				.contentType(MediaType.APPLICATION_JSON);

		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400))
				.andExpect(MockMvcResultMatchers.content().string("Password cannot be blank."));
	}

	@Test
	public void testLogIn_blankEmailAndPassword_negative() throws Exception {

		/*-
		 *  ARRANGE
		 */
		LoginDTO user = new LoginDTO(null, null);
		String jsonToSend = objMapper.writeValueAsString(user);

		/*-
		 *  ACT and ASSERT
		 */
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/login").content(jsonToSend)
				.contentType(MediaType.APPLICATION_JSON);

		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(400))
				.andExpect(MockMvcResultMatchers.content().string("Email, password cannot be blank."));
	}

	/*- ************
	 * 	logout Tests
	 *  ************
	 */
	@Test
	public void testLogout_positive() throws Exception {
		/*-
		 *  ARRANGE
		 */
		User user = new User("Jenny", "Doe", "jenny_doe@gmail.com", "Jenny123", "1234567890", "Admin");
		String jsonToSend = objMapper.writeValueAsString(user);

		/*-
		 *  ACT and ASSERT
		 */
		// send fake http request to /loginstatus
		MockHttpServletRequestBuilder builder1 = MockMvcRequestBuilders.post("/logout").content(jsonToSend)
				.contentType(MediaType.APPLICATION_JSON);

		this.mvc.perform(builder1).andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().string("Successfully logged out."));
	}

	/*- *****************
	 *  loginStatus Tests
	 *  *****************
	 */
//	@Test
//	public void testLoginStatus_currentlyLogin_positive() throws Exception {
//		LoginDTO user = new LoginDTO("jane_doe1@gmail.com", "Jane!123");
//		String jsonToSend = objMapper.writeValueAsString(user);
//
//		MockMvcRequestBuilders.post("/login").content(jsonToSend).contentType(MediaType.APPLICATION_JSON);
//
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/loginstatus").content(jsonToSend)
//				.contentType(MediaType.APPLICATION_JSON);
//
//		User expectedObject = new User("Jane", "Doe", "jane_doe1@gmail.com", "Jane!123", "5712561234", "Admin");
//		expectedObject.setUserId(1);
//
//		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(200)).andExpect(
//				MockMvcResultMatchers.content().string("Sucessfully logged in as " + expectedObject.getFirst_name()
//						+ " " + expectedObject.getLast_name() + ", " + expectedObject.getRole()));
//
//	}

	@Test
	public void testLoginStatus_currentlyNotLogin_negative() throws Exception {

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/loginstatus")
				.contentType(MediaType.APPLICATION_JSON);

		this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().is(401))
				.andExpect(MockMvcResultMatchers.content().string("You are currently not logged in."));
	}

}
