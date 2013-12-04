package edu.sjsu.cmpe.customerfeedback.ui.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.sjsu.cmpe.customerfeedback.repository.ReviewRepositoryInterface;
import edu.sjsu.cmpe.customerfeedback.ui.views.ReviewsView;


	@Path("/owners/{ownerId}/products/{productId}/reviews")
	@Produces(MediaType.TEXT_HTML)
	public class ReviewsViewResource {

		private final ReviewRepositoryInterface reviewRepository;
		public ReviewsViewResource (ReviewRepositoryInterface reviewRepository) {
			this.reviewRepository = reviewRepository;
		}
		
		@GET
		public ReviewsView getAllReviews() {		
			return new ReviewsView(reviewRepository.getAllReviews());
		}	
	}

