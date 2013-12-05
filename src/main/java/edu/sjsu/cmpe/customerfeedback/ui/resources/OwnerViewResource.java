package edu.sjsu.cmpe.customerfeedback.ui.resources;

import java.net.URISyntaxException;

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
import edu.sjsu.cmpe.customerfeedback.ui.views.OwnerView;

@Path("/owners/{ownerId}/products")
	public class OwnerViewResource {

		private final ProductRepositoryInterface productRepository;
		public OwnerViewResource(ProductRepositoryInterface productRepository) {
			this.productRepository = productRepository;
		}
		
		@GET
		@Produces(MediaType.TEXT_HTML)
		public OwnerView getAllProductsbyOwner(@PathParam("ownerId") int ownerId) {		
			return new OwnerView(productRepository.getallProductsbyOwner(ownerId));
		}
		
		@POST
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		public void createProduct(@PathParam("ownerId") int ownerId,@FormParam("productName") String productName,@FormParam("coverImage") String coverImage ) throws URISyntaxException{
			Product newProduct = new Product();
			if (!productName.isEmpty())
			newProduct.setProductName(productName);
			if(!coverImage.isEmpty())
			newProduct.setCoverImage(coverImage);
			newProduct.setOwnerId(ownerId);
			if(!(newProduct.getProductName().isEmpty()&&newProduct.getCoverImage().isEmpty()))
			productRepository.saveProduct(newProduct);
			
		}
}
