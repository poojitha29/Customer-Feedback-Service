package edu.sjsu.cmpe.customerfeedback.ui.views;

import java.util.List;

import edu.sjsu.cmpe.customerfeedback.domain.Review;

public class ReviewsView {
	private final List<Review> reviews;
	public ReviewsView(List<Review> reviews) {
		this.reviews = reviews;
	}

	public List<Review> getReviews() {
		return reviews;
	} 
}
