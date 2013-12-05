package edu.sjsu.cmpe.customerfeedback.ui.resources;

import java.util.ArrayList;
import java.util.List;

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
import edu.sjsu.cmpe.customerfeedback.ui.views.ReportView;

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
		
		@GET
		@Path("/generateReport")
		public ReportView generateReport(@PathParam("ownerId") int ownerId){
			List<String> reportValues = new ArrayList<String>();
			List<Product> products = productRepository.getallProductsbyOwner(ownerId);
			List<Integer> productIds = new ArrayList<Integer>();
			for (Product product : products) {
				productIds.add(product.getProductId());
			}
			reportValues = reviewRepository.generateReport(productIds);
			int product1Id = Integer.parseInt(reportValues.get(0));
			int product2Id = Integer.parseInt(reportValues.get(2));
			
			String Product1Name;
			String Product2Name;
			if (product1Id>0&&product2Id>0) {
				Product1Name = productRepository.getProductbyProductId(
						product1Id).getProductName();
				Product2Name = productRepository.getProductbyProductId(
						product2Id).getProductName();
				reportValues.remove(0);
				reportValues.add(0, Product1Name);
				reportValues.remove(2);
				reportValues.add(2, Product2Name);
			}
			else {
				reportValues.clear();
				reportValues.add("None");
				reportValues.add("0");
				reportValues.add("None");
				reportValues.add("0");
				reportValues.add("None");
			}
			System.out.println(reportValues);
			
			System.out.println(reportValues);
			return new ReportView(reportValues);
		} 
}
