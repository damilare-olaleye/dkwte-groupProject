package com.revature.dwte.controller;

import java.security.NoSuchAlgorithmException;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dwte.dto.LoginDTO;
import com.revature.dwte.exception.InvalidLoginException;
import com.revature.dwte.exception.InvalidParameterException;
import com.revature.dwte.exception.NotFoundException;
import com.revature.dwte.model.User;
import com.revature.dwte.service.AuthenticationService;
import com.revature.dwte.utility.ValidateUtil;

@RestController
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class AuthenticationController {

	private Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private AuthenticationService authService;

	@Autowired
	private HttpServletRequest req;
	
	@Autowired
	private HttpServletResponse res;

	@Autowired
	private ValidateUtil validateUtil;

	private static final String CURRENTUSER = "currentuser";

	@GetMapping(path = "/loginstatus")
	public ResponseEntity<Object> loginStatus() {
		logger.info("AuthenticationController.logiinStatus() invoked");

		User currentlyLoggedInUser = (User) req.getSession().getAttribute(CURRENTUSER);

		if (currentlyLoggedInUser != null) {
			return ResponseEntity.status(200)
					.body("You are currently log in as " + currentlyLoggedInUser.getFirst_name() + " "
							+ currentlyLoggedInUser.getLast_name() + ", " + currentlyLoggedInUser.getRole() + ".");
		}

		return ResponseEntity.status(401).body("You are currently not logged in.");
	}

	@PostMapping(path = "/login")
	public ResponseEntity<Object> login(@RequestBody LoginDTO dto)
			throws NoSuchAlgorithmException, InvalidParameterException, InvalidLoginException {
		logger.info("AuthenticationController.login() invoked");

		try {

			validateUtil.verifyLoginInput(dto);

			User user = this.authService.getUserByEmailAndPassword(dto.getEmail(), dto.getPassword());

			HttpSession session = req.getSession();
			session.setAttribute(CURRENTUSER, user);
			
//			/*
//			 * SameSite=None requires Https to be enabled for the backend
//			 */
//			String originalSetCookieHeader = res.getHeader("Set-Cookie");
//			String newCookieHeader = originalSetCookieHeader + "; SameSite=None; Secure";
//			res.setHeader("Set-Cookie", newCookieHeader);
			

			return ResponseEntity.status(200).body(user);

		} catch (NoResultException e) {
			return ResponseEntity.status(400).body(e.getMessage());

		} catch (InvalidLoginException e) {
			return ResponseEntity.status(400).body(e.getMessage());
		} catch (InvalidParameterException e) {
			return ResponseEntity.status(400).body(e.getMessage());
		}

	}

	@PostMapping(path = "/logout")
	public ResponseEntity<String> logout() {
		req.getSession().invalidate(); // Invalidate the session (logging out)

		return ResponseEntity.status(200).body("Successfully logged out.");
	}

	@PostMapping(path = "/signup")
	public ResponseEntity<Object> signUp(@RequestBody User user) throws NoSuchAlgorithmException, NotFoundException {
		logger.info("AuthenticationController.signUp() invoked");

		try {

			// validate all the input for sign up
			validateUtil.verifySignUp(user);

			this.authService.signUpUser(user.getFirst_name(), user.getLast_name(), user.getEmail(), user.getPassword(),
					user.getPhone_number(), user.getRole());

			return ResponseEntity.status(200).body("Successfully signed up.");

		} catch (InvalidParameterException e) {

			return ResponseEntity.status(400).body(e.getMessage());

		}

	}

}
