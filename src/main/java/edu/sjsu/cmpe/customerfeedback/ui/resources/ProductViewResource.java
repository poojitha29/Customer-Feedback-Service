package edu.sjsu.cmpe.customerfeedback.ui.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.sjsu.cmpe.customerfeedback.repository.ProductRepositoryInterface;
import edu.sjsu.cmpe.customerfeedback.ui.views.ProductView;

@Path("/owners/{ownerId}/products/{productId}")
@Produces(MediaType.TEXT_HTML)
public class ProductViewResource {

	private final ProductRepositoryInterface productRepository;
	public ProductViewResource(ProductRepositoryInterface productRepository) {
		this.productRepository = productRepository;
	}
	
	/*@GET
	public ProductView getProductbyOwner(@PathParam("productId") int productId){		
		return new ProductView(productRepository.getProductbyProductId(productId));
	}*/	
}
