package com.revature.dwte.model;

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
	@Column(name = "review_id")
	private int reviewId;

	@Column(name = "rating", nullable = false)
	private String ratings;

	@Column(name = "review")
	private String review;

	@Column(name = "submit_date", updatable = false)
	private String submittedDate;

	@ManyToOne
	private Restaurant restaurant;

	// creating column for restaurant ID and make it a foreign key
	@Column(name = "restaurant_id")
	private int restaurantId;

	@ManyToOne
	private User author;

	// creating column for author ID and make it a foreign key
	@Column(name = "author_id")
	private int authorId;

	public Review() {
		super();
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

	public String getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(String submittedDate) {
		this.submittedDate = submittedDate;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + authorId;
		result = prime * result + ((ratings == null) ? 0 : ratings.hashCode());
		result = prime * result + ((restaurant == null) ? 0 : restaurant.hashCode());
		result = prime * result + restaurantId;
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
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (authorId != other.authorId)
			return false;
		if (ratings == null) {
			if (other.ratings != null)
				return false;
		} else if (!ratings.equals(other.ratings))
			return false;
		if (restaurant == null) {
			if (other.restaurant != null)
				return false;
		} else if (!restaurant.equals(other.restaurant))
			return false;
		if (restaurantId != other.restaurantId)
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
				+ submittedDate + ", restaurant=" + restaurant + ", restaurantId=" + restaurantId + ", author=" + author
				+ ", authorId=" + authorId + "]";
	}

}
