package edu.sjsu.cmpe.customerfeedback.ui.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.sjsu.cmpe.customerfeedback.domain.Product;
import edu.sjsu.cmpe.customerfeedback.repository.ProductRepositoryInterface;
import edu.sjsu.cmpe.customerfeedback.repository.ReviewRepositoryInterface;
import edu.sjsu.cmpe.customerfeedback.ui.views.OwnerView;
import edu.sjsu.cmpe.customerfeedback.ui.views.ProductView;

@Path("/owners/{ownerId}/products")
	public class OwnerViewResource {

		private final ProductRepositoryInterface productRepository;
		private final ReviewRepositoryInterface reviewRepository;
		public OwnerViewResource(ProductRepositoryInterface productRepository, ReviewRepositoryInterface reviewRepository) {
			this.productRepository = productRepository;
			this.reviewRepository = reviewRepository;
		}
		
		@GET
		@Produces(MediaType.TEXT_HTML)
		public OwnerView getAllProductsbyOwner(@PathParam("ownerId") int ownerId) {		
			return new OwnerView(productRepository.getallProductsbyOwner(ownerId));
		}
		
		@POST
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		public void createProduct(@PathParam("ownerId") int ownerId,
				@FormParam("productName") String productName,
				@FormParam("coverImage") String coverImage ) {
			Product newProduct = new Product();
			if (!productName.isEmpty())
			newProduct.setProductName(productName);
			if(!coverImage.isEmpty())
			newProduct.setCoverImage(coverImage);
			newProduct.setOwnerId(ownerId);
			if(!(newProduct.getProductName().isEmpty()&&newProduct.getCoverImage().isEmpty()))
			productRepository.saveProduct(newProduct);
			
		}
		
		@GET
		@Path("/{productId}/reviews/default")
		public ProductView getProductbyOwner(@PathParam("productId") int productId){		
			return new ProductView(productRepository.getProductbyProductId(productId), reviewRepository.getAllReviews(productId));
		}
		
		@GET
		@Path("/{productId}/reviews/helpfulness")
		public ProductView getProductbyOwnerbyHelpulness(@PathParam("productId") int productId){		
			return new ProductView(productRepository.getProductbyProductId(productId), reviewRepository.getAllReviewsbyHelpfulness(productId));
		}
		
		@GET
		@Path("/{productId}/reviews/recent")
		public ProductView getProductbyOwnerbyRecent(@PathParam("productId") int productId){		
			return new ProductView(productRepository.getProductbyProductId(productId), reviewRepository.getAllReviewsbyRecent(productId));
		}
}
