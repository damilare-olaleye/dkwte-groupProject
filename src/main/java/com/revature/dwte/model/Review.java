package com.revature.dwte.model;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reviewId;

	@Column(name = "rating", nullable = false)
	private String ratings;

	@Column(name = "review", nullable = false)
	private String review;

	@Column(name = "submit_date", updatable = false, insertable = false)
	private Timestamp submittedDate;

	@ManyToOne
	private Restaurant resturantsId;

	@ManyToOne
	private User authorId;

	public Review() {
		super();
	}

	public Review(String ratings, String review, Timestamp submittedDate, Restaurant resturantsId, User authorId) {
		super();
		this.ratings = ratings;
		this.review = review;
		this.submittedDate = submittedDate;
		this.resturantsId = resturantsId;
		this.authorId = authorId;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorId == null) ? 0 : authorId.hashCode());
		result = prime * result + ((ratings == null) ? 0 : ratings.hashCode());
		result = prime * result + ((resturantsId == null) ? 0 : resturantsId.hashCode());
		result = prime * result + ((review == null) ? 0 : review.hashCode());
		result = prime * result + reviewId;
		result = prime * result + ((submittedDate == null) ? 0 : submittedDate.hashCode());
		return result;
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
		if (authorId == null) {
			if (other.authorId != null)
				return false;
		} else if (!authorId.equals(other.authorId))
			return false;
		if (ratings == null) {
			if (other.ratings != null)
				return false;
		} else if (!ratings.equals(other.ratings))
			return false;
		if (resturantsId == null) {
			if (other.resturantsId != null)
				return false;
		} else if (!resturantsId.equals(other.resturantsId))
			return false;
		if (review == null) {
			if (other.review != null)
				return false;
		} else if (!review.equals(other.review))
			return false;
		if (reviewId != other.reviewId)
			return false;
		if (submittedDate == null) {
			if (other.submittedDate != null)
				return false;
		} else if (!submittedDate.equals(other.submittedDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", ratings=" + ratings + ", review=" + review + ", submittedDate="
				+ submittedDate + ", resturantsId=" + resturantsId + ", authorId=" + authorId + "]";
	}

}
