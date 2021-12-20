package com.revature.dwte.dto;

import java.util.Objects;

public class DeleteReviewDTO {

	private int reviewId;
	private int authorId;
	private String review;

	public DeleteReviewDTO() {
		super();
	}

	public DeleteReviewDTO(int reviewId, int authorId, String review) {
		super();
		this.reviewId = reviewId;
		this.authorId = authorId;
		this.review = review;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	@Override
	public int hashCode() {
		return Objects.hash(authorId, review, reviewId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeleteReviewDTO other = (DeleteReviewDTO) obj;
		return authorId == other.authorId && Objects.equals(review, other.review) && reviewId == other.reviewId;
	}

	@Override
	public String toString() {
		return "DeleteReviewDTO [reviewId=" + reviewId + ", authorId=" + authorId + ", review=" + review + "]";
	}

	
}
