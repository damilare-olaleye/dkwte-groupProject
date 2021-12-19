package com.revature.dwte.controller;

import javax.servlet.http.HttpServletRequest;
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
import com.revature.dwte.exception.FailedAuthenticationException;
import com.revature.dwte.exception.InvalidLoginException;
import com.revature.dwte.exception.InvalidParameterException;
import com.revature.dwte.exception.NotFoundException;
import com.revature.dwte.model.User;
import com.revature.dwte.service.AuthenticationService;

@RestController
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class AuthenticationController {

	private Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private AuthenticationService authService;

	@Autowired
	private HttpServletRequest req;

	private static final String CURRENTUSER = "currentuser";

	@GetMapping(path = "/loginstatus")
	public ResponseEntity<Object> loginStatus() {
		User currentlyLoggedInUser = (User) req.getSession().getAttribute(CURRENTUSER);

		if (currentlyLoggedInUser != null) {
			return ResponseEntity.status(200).body(currentlyLoggedInUser);
		}

		return ResponseEntity.status(401).body("Not logged in");
	}

	@PostMapping(path = "/login")
	public ResponseEntity<Object> login(@RequestBody LoginDTO dto) {

		logger.info("login user ...");

		try {
			User user = this.authService.setLoginUser(dto.getEmail(), dto.getPassword());

			HttpSession session = req.getSession();
			session.setAttribute(CURRENTUSER, user);

			return ResponseEntity.status(200).body(user);

		} catch (InvalidLoginException e) {

			return ResponseEntity.status(400).body(e.getMessage());

		}

	}

	@PostMapping(path = "/logout")
	public ResponseEntity<String> logout() {
		req.getSession().invalidate(); // Invalidate the session (logging out)

		return ResponseEntity.status(200).body("Successfully logged out");
	}

	@PostMapping(path = "/signup")
	public ResponseEntity<Object> login(@RequestBody User dto)
			throws InvalidParameterException, NotFoundException, FailedAuthenticationException {

		try {

			logger.info("signup user ...");

			this.authService.setSignupUser(dto.getFirst_name(), dto.getLast_name(), dto.getEmail(), dto.getPassword(),
					dto.getPhone_number(), dto.getRole());

			return ResponseEntity.status(200).body("You have successfully signed up!");

		} catch (FailedAuthenticationException e) {

			return ResponseEntity.status(400).body(e.getMessage());

		}

	}

}
