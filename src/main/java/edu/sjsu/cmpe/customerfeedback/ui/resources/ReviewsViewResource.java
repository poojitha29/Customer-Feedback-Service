package edu.sjsu.cmpe.customerfeedback.ui.resources;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.sjsu.cmpe.customerfeedback.domain.Review;
import edu.sjsu.cmpe.customerfeedback.repository.ReviewRepositoryInterface;


	@Path("/users/{userName}/products/{productId}/canReview/{canReview}/reviews")
	@Produces(MediaType.TEXT_HTML)
	public class ReviewsViewResource {

		private final ReviewRepositoryInterface reviewRepository;
		public ReviewsViewResource (ReviewRepositoryInterface reviewRepository) {
			this.reviewRepository = reviewRepository;
		}
		
		/*@GET
		public ReviewsView getAllReviews(@PathParam("productId") int productId) {		
			return new ReviewsView(reviewRepository.getAllReviews(productId));
		}*/
		
		
		@POST
		public Response createReview(@PathParam("canReview") boolean canReview,
				@PathParam("productId") int productId,
				@PathParam("userName") String reviewer,
				@FormParam("reviewText") String reviewText, 
				@FormParam("templateText") String templateText){
			if (canReview) {
				Review newReview = new Review();
				newReview.setReviewText(reviewText);
				newReview.setTemplateText(templateText);
				if(!reviewer.isEmpty())
				newReview.setReviewer(reviewer);
				newReview.setProductId(productId);
				if(!(newReview.getReviewText().isEmpty()&&newReview.getTemplateText().isEmpty()))
				reviewRepository.saveReview(newReview);				
				return Response.status(201).build();
			}
			String Message = "Cannot add reviews to this product!";
			return Response.status(405).entity(Message).build();
		}	
		
		@PUT
		@Path("/{reviewId}")
		public Response updateHelpfulness(@PathParam("reviewId") int reviewId, @QueryParam("isHelpful") boolean isHelpful) {
			System.out.println("Put for helpfulness");
			reviewRepository.updateHelpfulness(isHelpful, reviewId);
			return Response.ok().build();			
		}
	}


