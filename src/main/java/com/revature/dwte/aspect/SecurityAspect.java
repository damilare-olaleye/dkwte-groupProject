package com.revature.dwte.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.revature.dwte.model.User;

@Aspect
@Component
public class SecurityAspect {

	@Autowired
	private HttpServletRequest req;

	@Around("@annotation(com.revature.dwte.annotation.Member)")
	public Object protectEndpointMemberOnly(ProceedingJoinPoint pjp) throws Throwable {

		HttpSession session = req.getSession();

		User currentlyLoggedInUser = (User) session.getAttribute("currentuser");

		if (currentlyLoggedInUser == null) {
			return ResponseEntity.status(401).body("You are not logged in. Must log in to access.");
		}

		if (!currentlyLoggedInUser.getRole().equals("Member")) {
			return ResponseEntity.status(401)
					.body("You are logged in, but only members are allowed to access this endpoint");
		}

		Object returnValue = pjp.proceed();

		return returnValue;

	}

	@Around("@annotation(com.revature.dwte.annotation.Admin)")
	public Object protectEndpointAdminOnly(ProceedingJoinPoint pjp) throws Throwable {

		HttpSession session = req.getSession();

		User currentlyLoggedInUser = (User) session.getAttribute("currentuser");

		if (currentlyLoggedInUser == null) {
			return ResponseEntity.status(401).body("You are not logged in. Must log in to access.");
		}

		if (!currentlyLoggedInUser.getRole().equals("Admin")) {
			return ResponseEntity.status(401)
					.body("You are logged in, but only Admins are allowed to access this endpoint");
		}

		Object returnValue = pjp.proceed();

		return returnValue;

	}

	@Around("@annotation(com.revature.dwte.annotation.AdminAndMember)")
	public Object protectEndpointAdminAndMemberOnly(ProceedingJoinPoint pjp) throws Throwable {

		HttpSession session = req.getSession();

		User currentlyLoggedInUser = (User) session.getAttribute("currentuser");

		if (currentlyLoggedInUser == null) {
			return ResponseEntity.status(401).body("You are not logged in. Must log in to access.");
		}

		if (!currentlyLoggedInUser.getRole().equals("Admin") && (!currentlyLoggedInUser.getRole().equals("Member"))) {
			return ResponseEntity.status(401)
					.body("You are logged in, but only Admins and Members are allowed to access this endpoint");
		}

		Object returnValue = pjp.proceed();

		return returnValue;

	}

}
