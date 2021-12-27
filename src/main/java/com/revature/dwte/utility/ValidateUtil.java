package com.revature.dwte.utility;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.dwte.dto.LoginDTO;
import com.revature.dwte.exception.InvalidLoginException;
import com.revature.dwte.exception.InvalidParameterException;
import com.revature.dwte.model.Restaurant;
import com.revature.dwte.model.Review;
import com.revature.dwte.model.User;
import com.revature.dwte.service.AuthenticationService;
import com.revature.dwte.service.RestaurantService;
import com.revature.dwte.service.ReviewService;

public class ValidateUtil {

	Logger logger = LoggerFactory.getLogger(ValidateUtil.class);

	@Autowired
	AuthenticationService authenticationService;

	@Autowired
	RestaurantService restaurantService;

	@Autowired
	ReviewService reviewService;

	static List<String> userRoleList = Arrays.asList("member", "admin");
	static List<String> ratingList = Arrays.asList("1", "2", "3", "4", "5");

	public void verifySignUp(User user) throws InvalidParameterException {
		logger.info("ValidteUtil.verifySignUp() invoked");

		/*-
		 *  Check if inputs are blank
		 */
		logger.info("check if inputs are blank");

		boolean blankInputBoolean = false;
		StringBuilder blankInputString = new StringBuilder();

		if (StringUtils.isBlank(user.getFirst_name().trim())) {
			blankInputString.append("First name");
			blankInputBoolean = true;
		}
		if (StringUtils.isBlank(user.getLast_name().trim())) {
			if (blankInputBoolean) {
				blankInputString.append(", last name");
				blankInputBoolean = true;
			} else {
				blankInputString.append("Last name");
				blankInputBoolean = true;
			}
		}
		if (StringUtils.isBlank(user.getEmail().trim())) {
			if (blankInputBoolean) {
				blankInputString.append(", email");
				blankInputBoolean = true;
			} else {
				blankInputString.append("Email");
				blankInputBoolean = true;
			}
		}
		if (StringUtils.isBlank(user.getPassword().trim())) {
			if (blankInputBoolean) {
				blankInputString.append(", password");
				blankInputBoolean = true;
			} else {
				blankInputString.append("Password");
				blankInputBoolean = true;
			}
		}
		if (StringUtils.isBlank(user.getPhone_number().trim())) {
			if (blankInputBoolean) {
				blankInputString.append(", phone number");
				blankInputBoolean = true;
			} else {
				blankInputString.append("Phone number");
				blankInputBoolean = true;
			}
		}
		if (StringUtils.isBlank(user.getRole().trim())) {
			if (blankInputBoolean) {
				blankInputString.append(", user role");
				blankInputBoolean = true;
			} else {
				blankInputString.append("User role");
				blankInputBoolean = true;
			}
		}

		if (blankInputBoolean) {
			blankInputString.append(" cannot be blank.");
			// .toString turn StringBuilder into a string
			throw new InvalidParameterException(blankInputString.toString());
		}

		/*-
		 *  Check if email and phone number already exist
		 */
		logger.info("Check if email and phone number already exist");

		List<User> databaseUser = authenticationService.getUserByEmailAndPhoneNumber(user.getEmail(),
				user.getPhone_number());

		boolean emailPhoneNumberExistBoolean = false;
		StringBuilder emailPhoneNumberExistString = new StringBuilder();

		logger.debug("databaseUser {}", databaseUser);

		if (databaseUser != null) {

			for (User userElement : databaseUser) {

				if (StringUtils.equalsAnyIgnoreCase(userElement.getEmail().trim(), user.getEmail().trim())) {
					emailPhoneNumberExistString.append("Email");
					emailPhoneNumberExistBoolean = true;
				}

				logger.debug("database Phone number, {}", userElement.getPhone_number());
				logger.debug("phone number, {}", user.getPhone_number().trim());

				if (StringUtils.equalsAnyIgnoreCase(userElement.getPhone_number().trim(),
						user.getPhone_number().trim())) {

					if (emailPhoneNumberExistBoolean) {
						emailPhoneNumberExistString.append(", phone number");
						emailPhoneNumberExistBoolean = true;
					} else {
						emailPhoneNumberExistString.append("Phone number");
						emailPhoneNumberExistBoolean = true;
					}
				}
				if (emailPhoneNumberExistBoolean) {
					emailPhoneNumberExistString.append(" already exist.");
					// .toString turn StringBuilder into a string
					throw new InvalidParameterException(emailPhoneNumberExistString.toString());
				}
			}

		}

		/*-
		 *  phone number verification
		 */
		logger.info("phone number verification");

		Pattern p = Pattern.compile("^\\d{10}$");
		Matcher m = p.matcher(user.getPhone_number().trim());

		if (!m.matches() && user.getPhone_number().trim().matches("[0-9]+")) {
			throw new InvalidParameterException("Invalid phone number. Phone number must be 10 digits and no symbols.");
		}

		/*-
		 *  email verification
		 */
		logger.info("email verification");

		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

		if (!user.getEmail().matches(regex)) {
			throw new InvalidParameterException("Invalid Email.");
		}

		/*-
		 *  user role verification
		 */
		logger.info("user role verification");

		if (user.getRole().trim().toLowerCase() != null
				&& !userRoleList.contains(user.getRole().trim().toLowerCase())) {
			throw new InvalidParameterException("User role must be 'member' or 'admin'.");
		}

		/*-
		 *  validate names
		 */
		logger.info("validate names");

		boolean validateNamesBoolean = false;
		StringBuilder validateNameString = new StringBuilder();

		if (!(user.getFirst_name().trim().matches("[A-Z][a-z]*"))) {
			validateNameString.append("First name");
			validateNamesBoolean = true;
		}
		if (!(user.getLast_name().trim().matches("[A-Z][a-z]*"))) {
			if (validateNamesBoolean) {
				validateNameString.append(", last name");
				validateNamesBoolean = true;
			} else {
				validateNameString.append("Last name");
				validateNamesBoolean = true;
			}
		}
		if (validateNamesBoolean) {
			validateNameString.append(" invalid. Names cannot contain any symbols or numbers.");
			// .toString turn StringBuilder into a string
			throw new InvalidParameterException(validateNameString.toString());
		}

		/*-
		 *  validate password
		 *  
		 */
		logger.info("validate password");

		if (!(user.getPassword().matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}") && user.getPassword().length() < 20)) {
			throw new InvalidParameterException(
					"Password must contain at least one number and one uppercase and lowercase letter, "
							+ "and between 8 to 20 characters.");
		}

	}

	public void verifyAddRestaurant(String restaurantName, String restaurantAddress) throws InvalidParameterException {
		logger.info("ValidteUtil.verifyAddRestaurant() invoked");

		/*-
		 *  Check if inputs are blank
		 */
		logger.info("check if restaurant name and address are blank");

		boolean blankInputBoolean = false;
		StringBuilder blankInputString = new StringBuilder();

		if (StringUtils.isBlank(restaurantName)) {
			blankInputString.append("Restaurant name");
			blankInputBoolean = true;
		}
		if (StringUtils.isBlank(restaurantAddress)) {
			if (blankInputBoolean) {
				blankInputString.append(", restaurant address");
				blankInputBoolean = true;
			} else {
				blankInputString.append("Restaurant address");
				blankInputBoolean = true;
			}
		}
		if (blankInputBoolean) {
			blankInputString.append(" cannot be blank.");
			// .toString turn StringBuilder into a string
			throw new InvalidParameterException(blankInputString.toString());
		}

		/*-
		 *  Check if restaurant already exist
		 */
		logger.info("Check if restaurant already exist");

		Restaurant databaseRestaurant = restaurantService.getRestaurantByRestaurantNameAndAddress(restaurantName,
				restaurantAddress);

		if (databaseRestaurant != null) {
			throw new InvalidParameterException(
					restaurantName + " with address of " + restaurantAddress + " already exist.");
		}

	}

	public void verifyNewReview(String rating, String review, String reviewTitle, String restaurant_name) throws InvalidParameterException {
		logger.info("ValidteUtil.verifyNewReview() invoked");

		/*-
		 *  Check if inputs are blank
		 */
		logger.info("check if rating and restaurant id are blank");

		boolean blankInputBoolean = false;
		StringBuilder blankInputString = new StringBuilder();

		if (StringUtils.isBlank(rating)) {
			blankInputString.append("Rating");
			blankInputBoolean = true;
		}
		if (StringUtils.isBlank(reviewTitle)) {
			if (blankInputBoolean) {
				blankInputString.append(", review Title");
				blankInputBoolean = true;
			} else {
				blankInputString.append("review Title");
				blankInputBoolean = true;
			}
		}
		if (StringUtils.isBlank(restaurant_name)) {
			if (blankInputBoolean) {
				blankInputString.append(", restaurant Name");
				blankInputBoolean = true;
			} else {
				blankInputString.append("restaurant Name");
				blankInputBoolean = true;
			}
		}
		if (blankInputBoolean) {
			blankInputString.append(" cannot be blank.");
			// .toString turn StringBuilder into a string
			throw new InvalidParameterException(blankInputString.toString());
		}

		/*-
		 *  check if restaurant exist
		 */
		logger.info("check if restaurant exist");

//		Restaurant databaseRestaurant = restaurantService.getRestaurantByRestaurantId(restaurantId);

//		if (databaseRestaurant == null) {
//			throw new InvalidParameterException("Restaurant with the ID of " + restaurantId + " does not exist.");
//		}

		/*-
		 *  verify rating
		 */
		logger.info("verify rating");

		if (!ratingList.contains(rating)) {
			throw new InvalidParameterException(
					"Invalid rating. Please enter a number between 1-5 to represent the number of start(s).");
		}

	}

	public void verifyRevieId(int reviewId) throws InvalidParameterException {
		logger.info("ValidteUtil.verifyRevieId() invoked");

		Review databaseReview = reviewService.getReviewByReviewId(reviewId);

		if (databaseReview == null) {
			throw new InvalidParameterException("Review with the ID of " + reviewId + " does not exist.");
		}

	}

	public void verifyRestaurantId(int restaurantId) throws InvalidParameterException {
		logger.info("ValidteUtil.verifyRestaurantId() invoked");

		Restaurant databaseRestaurant = restaurantService.getRestaurantByRestaurantId(restaurantId);

		if (databaseRestaurant == null) {
			throw new InvalidParameterException("Restaurant with the ID of " + restaurantId + " does not exist.");
		}

	}

	public void verifyLoginInput(LoginDTO dto)
			throws NoSuchAlgorithmException, InvalidLoginException, InvalidParameterException {
		logger.info("ValidteUtil.verifyRestaurantId() invoked");

		/*-
		 *  Check if inputs are blank
		 */
		logger.info("check if email and password are blank");

		boolean blankInputBoolean = false;
		StringBuilder blankInputString = new StringBuilder();

		if (StringUtils.isBlank(dto.getEmail())) {
			blankInputString.append("Email");
			blankInputBoolean = true;
		}
		if (StringUtils.isBlank(dto.getPassword())) {
			if (blankInputBoolean) {
				blankInputString.append(", password");
				blankInputBoolean = true;
			} else {
				blankInputString.append("Password");
				blankInputBoolean = true;
			}
		}
		if (blankInputBoolean) {
			blankInputString.append(" cannot be blank.");
			// .toString turn StringBuilder into a string
			throw new InvalidParameterException(blankInputString.toString());
		}

	}

	public void verifyRestaurantNameAndAddress(String restaurantName, String restaurantAddress)
			throws InvalidParameterException {
		logger.info("ValidteUtil.verifyRestaurantNameAndAddress() invoked");

		/*-
		 *  Check if inputs are blank
		 */
		logger.info("check if restaurant name and address are blank");

		boolean blankInputBoolean = false;
		StringBuilder blankInputString = new StringBuilder();

		if (StringUtils.isBlank(restaurantName)) {
			blankInputString.append("Restaurant name");
			blankInputBoolean = true;
		}
		if (StringUtils.isBlank(restaurantAddress)) {
			if (blankInputBoolean) {
				blankInputString.append(", restaurant address");
				blankInputBoolean = true;
			} else {
				blankInputString.append("Restaurant address");
				blankInputBoolean = true;
			}
		}
		if (blankInputBoolean) {
			blankInputString.append(" cannot be blank.");
			// .toString turn StringBuilder into a string
			throw new InvalidParameterException(blankInputString.toString());
		}

	}

	public void verifyIfRestaurantExist(String restaurantName, String restaurantAddress)
			throws InvalidParameterException {
		logger.info("ValidteUtil.verifyRestaurantNameAndAddress() invoked");
		/*-
		 *  Check if restaurant already exist
		 */
		logger.info("check if restaurant already exist");

		Restaurant databaseRestaurant = restaurantService.getRestaurantByRestaurantNameAndAddress(restaurantName,
				restaurantAddress);

		if (databaseRestaurant == null) {
			throw new InvalidParameterException(restaurantName + " with address of " + restaurantAddress
					+ "does not exist in the database. Please add the restaurant");
		}
	}

}
