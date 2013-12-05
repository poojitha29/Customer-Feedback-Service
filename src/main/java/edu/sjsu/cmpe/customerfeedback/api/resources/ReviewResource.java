/**
 * 
 */
package edu.sjsu.cmpe.customerfeedback.api.resources;

import java.util.ArrayList;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.twilio.sdk.TwilioRestException;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.customerfeedback.domain.Review;
import edu.sjsu.cmpe.customerfeedback.dto.LinkDto;
import edu.sjsu.cmpe.customerfeedback.dto.LinksDto;
import edu.sjsu.cmpe.customerfeedback.dto.ReviewsDto;
import edu.sjsu.cmpe.customerfeedback.repository.ReviewRepositoryInterface;

/**
 * @author Rajiv
 *
 */

@Path("/v1/owners/{ownerId}/products/{productId}/canReview/{canReview}/reviews/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class ReviewResource {

	/**
	 * 
	 */
	private final ReviewRepositoryInterface reviewRepository;
	public ReviewResource(ReviewRepositoryInterface reviewRepository) {
		this.reviewRepository = reviewRepository;
	}
	
	@POST
	//@Path("")
	@Timed(name = "create-review")
	public Response createReview(@PathParam("ownerId") int ownerId, @PathParam("canReview") boolean canReview, @PathParam("productId") int productId,@Valid Review request) throws TwilioRestException{
		if (canReview) {
			request.setProductId(productId);
			reviewRepository.saveReview(request);			
			LinksDto links = new LinksDto();
			//notification.notifyProductReviewed(request);
			links.addLink(new LinkDto("view-all-reviews", "/owners/"+ownerId+"/products/"+productId+"/canReview/"+canReview+"/reviews/", "GET"));
			return Response.status(201).entity(links).build();
		}
		String Message = "Cannot add reviews to this product!";
		return Response.status(405).entity(Message).build();
	}	
	
	
	@GET
	@Timed(name = "view-all-reviews")
	public Response getAllReviews(@PathParam("productId") int productId) {
		ArrayList<Review> reviews = reviewRepository.getAllReviews(productId); 
		ReviewsDto reviewResponse = new ReviewsDto(reviews);
		return Response.status(200).entity(reviewResponse).build();
	}
	
}
