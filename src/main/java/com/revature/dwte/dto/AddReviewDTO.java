package com.revature.dwte.dto;

import java.sql.Timestamp;
import java.util.Objects;

import com.revature.dwte.model.Restaurant;
import com.revature.dwte.model.User;

public class AddReviewDTO {

	private String ratings;
	private String review;
	private Timestamp submittedDate;
	private Restaurant resturantsId;
	private User authorId;
	
	public AddReviewDTO() {
		super();
	}

	public AddReviewDTO(String ratings, String review, Timestamp submittedDate, Restaurant resturantsId,
			User authorId) {
		super();
		this.ratings = ratings;
		this.review = review;
		this.submittedDate = submittedDate;
		this.resturantsId = resturantsId;
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

	public Restaurant getResturantsId() {
		return resturantsId;
	}

	public void setResturantsId(Restaurant resturantsId) {
		this.resturantsId = resturantsId;
	}

	public User getAuthorId() {
		return authorId;
	}

	public void setAuthorId(User authorId) {
		this.authorId = authorId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(authorId, ratings, resturantsId, review, submittedDate);
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
				&& Objects.equals(resturantsId, other.resturantsId) && Objects.equals(review, other.review)
				&& Objects.equals(submittedDate, other.submittedDate);
	}

	@Override
	public String toString() {
		return "AddReviewDTO [ratings=" + ratings + ", review=" + review + ", submittedDate=" + submittedDate
				+ ", resturantsId=" + resturantsId + ", authorId=" + authorId + "]";
	}

	
}
