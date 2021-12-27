package com.revature.dwte.dto;

import java.sql.Timestamp;
import java.util.Objects;

import com.revature.dwte.model.Restaurant;
import com.revature.dwte.model.User;

public class AddReviewDTO {

	private String ratings;
	private String review;
	private Timestamp submittedDate;
	private Restaurant restaurant_name;
	private User authorId;
	
	public AddReviewDTO() {
		super();
	}

	public AddReviewDTO(String ratings, String review, Timestamp submittedDate, Restaurant restaurant_name,
			User authorId) {
		super();
		this.ratings = ratings;
		this.review = review;
		this.submittedDate = submittedDate;
		this.restaurant_name = restaurant_name;
		this.authorId = authorId;
	}

	public String getRatings() {
		return ratings;
	}

	public void setRatings(String ratings) {
		this.ratings = ratings;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Timestamp getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(Timestamp submittedDate) {
		this.submittedDate = submittedDate;
	}

	public Restaurant getRestaurant_name() {
		return restaurant_name;
	}

	public void setRestaurant_name(Restaurant restaurant_name) {
		this.restaurant_name = restaurant_name;
	}

	public User getAuthorId() {
		return authorId;
	}

	public void setAuthorId(User authorId) {
		this.authorId = authorId;
	}

	@Override
	public String toString() {
		return "AddReviewDTO [ratings=" + ratings + ", review=" + review + ", submittedDate=" + submittedDate
				+ ", restaurant_name=" + restaurant_name + ", authorId=" + authorId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(authorId, ratings, restaurant_name, review, submittedDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddReviewDTO other = (AddReviewDTO) obj;
		return Objects.equals(authorId, other.authorId) && Objects.equals(ratings, other.ratings)
				&& Objects.equals(restaurant_name, other.restaurant_name) && Objects.equals(review, other.review)
				&& Objects.equals(submittedDate, other.submittedDate);
	}

	
	
}
