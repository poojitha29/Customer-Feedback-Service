package edu.sjsu.cmpe.customerfeedback.ui.views;

import java.util.List;

import com.yammer.dropwizard.views.View;

import edu.sjsu.cmpe.customerfeedback.domain.Review;

public class ReviewsView extends View {
	private final List<Review> reviews;
	public ReviewsView(List<Review> reviews) {
		super("reviews.mustache");
		this.reviews = reviews;
	}

	public List<Review> getReviews() {
		return reviews;
	} 
}
