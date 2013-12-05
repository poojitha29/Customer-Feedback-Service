package edu.sjsu.cmpe.customerfeedback.ui.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.sjsu.cmpe.customerfeedback.repository.ProductRepositoryInterface;
import edu.sjsu.cmpe.customerfeedback.ui.views.ProductsView;

@Path("users/{userName}/products")
@Produces(MediaType.TEXT_HTML)
public class ProductsViewResource {

	private final ProductRepositoryInterface productRepository;
	public ProductsViewResource(ProductRepositoryInterface productRepository) {
		this.productRepository = productRepository;
	}
	
	@GET
	public ProductsView getAllProducts() {		
		return new ProductsView(productRepository.getallProducts());
	}	
}
