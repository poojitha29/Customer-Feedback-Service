package edu.sjsu.cmpe.customerfeedback.ui.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.sjsu.cmpe.customerfeedback.repository.ProductRepositoryInterface;
import edu.sjsu.cmpe.customerfeedback.repository.ReviewRepositoryInterface;
import edu.sjsu.cmpe.customerfeedback.repository.UserRepositoryInterface;
import edu.sjsu.cmpe.customerfeedback.ui.views.ProductView;
import edu.sjsu.cmpe.customerfeedback.ui.views.CustomerView;

@Path("users/{userName}/products")
@Produces(MediaType.TEXT_HTML)
public class CustomerViewResource {

	private final ProductRepositoryInterface productRepository;
	private final ReviewRepositoryInterface reviewRepository;
	private final UserRepositoryInterface userRepository;
	public CustomerViewResource(ProductRepositoryInterface productRepository, ReviewRepositoryInterface reviewRepository, UserRepositoryInterface userRepository) {
		this.productRepository = productRepository;
		this.reviewRepository = reviewRepository;
		this.userRepository = userRepository;
	}
	
	@GET
	public CustomerView getAllProducts() {		
		return new CustomerView(productRepository.getallProducts());
	}
	
	
	@GET
	@Path("/{productId}/reviews/default")
	public ProductView getProductbyOwner(@PathParam("productId") int productId, @PathParam("userName") String userName){		
		return new ProductView(productRepository.getProductbyProductId(productId), reviewRepository.getAllReviews(productId), userRepository.getUserbyUserName(userName));
	}
	
	@GET
	@Path("/{productId}/reviews/helpfulness")
	public ProductView getProductbyOwnerbyHelpulness(@PathParam("productId") int productId, @PathParam("userName") String userName){		
		return new ProductView(productRepository.getProductbyProductId(productId), reviewRepository.getAllReviewsbyHelpfulness(productId), userRepository.getUserbyUserName(userName));
	}
	
	@GET
	@Path("/{productId}/reviews/recent")
	public ProductView getProductbyOwnerbyRecent(@PathParam("productId") int productId, @PathParam("userName") String userName){		
		return new ProductView(productRepository.getProductbyProductId(productId), reviewRepository.getAllReviewsbyRecent(productId), userRepository.getUserbyUserName(userName));
	}
}
