package com.revature.dwte.model;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reiviewId;

	private String ratings;
	private String review;
	private Timestamp submittedDate;

	@ManyToOne
	private Restaurant resturantsId;

	@ManyToOne
	private User authorId;

	public Review() {
		super();
	}

	public Review(int reiviewId, String ratings, String review, Timestamp submittedDate, Restaurant resturantsId,
			User authorId) {
		super();
		this.reiviewId = reiviewId;
		this.ratings = ratings;
		this.review = review;
		this.submittedDate = submittedDate;
		this.resturantsId = resturantsId;
		this.authorId = authorId;
	}

	public int getReiviewId() {
		return reiviewId;
	}

	public void setReiviewId(int reiviewId) {
		this.reiviewId = reiviewId;
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
		return Objects.hash(authorId, ratings, reiviewId, resturantsId, review, submittedDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		return Objects.equals(authorId, other.authorId) && Objects.equals(ratings, other.ratings)
				&& reiviewId == other.reiviewId && Objects.equals(resturantsId, other.resturantsId)
				&& Objects.equals(review, other.review) && Objects.equals(submittedDate, other.submittedDate);
	}

	@Override
	public String toString() {
		return "Review [reiviewId=" + reiviewId + ", ratings=" + ratings + ", review=" + review + ", submittedDate="
				+ submittedDate + ", resturantsId=" + resturantsId + ", authorId=" + authorId + "]";
	}

	
}
