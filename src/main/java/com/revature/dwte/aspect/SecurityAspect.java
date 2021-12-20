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
			return ResponseEntity.status(401).body("You are not logged in");
		}

		if (!currentlyLoggedInUser.getRole().equals("Member")) {
			return ResponseEntity.status(401)
					.body("You are logged in, but only members are allowed to access this endpoint");
		}

		Object returnValue = pjp.proceed();

		return returnValue;

	}

}
