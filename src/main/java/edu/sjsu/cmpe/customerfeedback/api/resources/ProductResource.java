/**
 * 
 */
package edu.sjsu.cmpe.customerfeedback.api.resources;


import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.customerfeedback.domain.Product;
import edu.sjsu.cmpe.customerfeedback.dto.LinkDto;
import edu.sjsu.cmpe.customerfeedback.dto.LinksDto;
import edu.sjsu.cmpe.customerfeedback.dto.ProductDto;
import edu.sjsu.cmpe.customerfeedback.dto.ProductsDto;
import edu.sjsu.cmpe.customerfeedback.repository.ProductRepositoryInterface;

/**
 * @author Rajiv
 *
 */

@Path("owners/{ownerId}/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class ProductResource {
	private final ProductRepositoryInterface productRepository;
	/**
	 * 
	 */
	public ProductResource(ProductRepositoryInterface productRepository) {
		this.productRepository = productRepository;
	}

	@POST
	@Timed(name = "create-product")
	public Response createProduct(@PathParam("ownerId") int ownerId,@Valid Product request){
		Product savedProduct = productRepository.saveProduct(request);
		savedProduct.setOwnerId(ownerId);
		int productId = savedProduct.getProductId();
		LinksDto links = new LinksDto();
		links.addLink(new LinkDto("view-product", "/owners/"+ownerId+"/products/"+productId, "GET"));
		return Response.status(201).entity(links).build();
	}
	
	@GET
	@Timed(name = "view-products-by-owner")
	public Response viewProductsOfOwner(@PathParam("ownerId") int ownerId) {
		List<Product> allProducts = productRepository.getallProducts();
		ProductsDto links = new ProductsDto(allProducts);
		for (int i = 0; i< allProducts.size(); i++)
		links.addLink(new LinkDto("view-product", "/owners/"+ownerId+"/products/"+allProducts.get(i).getProductId(), "GET"));
		return Response.ok().entity(allProducts).build();
	}
	
	@GET
	@Path("/{productId}")
	@Timed(name = "view-product-by-owner")
	public Response viewProductByOwner(@PathParam("ownerId") int ownerId, @PathParam("productId") int productId) {
		Product product = productRepository.getProductbyProductId(productId);
		ProductDto links = new ProductDto(product);
		links.addLink(new LinkDto("view-reviews", "/owners/"+ownerId+"/products/"+productId+"/reviews", "GET"));
		
		return Response.ok().entity(links).build();
	}
	
	@PUT
	@Path("/{productId}")
	@Timed(name = "set-reviewable")
	public Response setReviewable(@PathParam("productId") int productId, @PathParam("ownerId") int ownerId, @QueryParam("canReview") boolean value) {
		Product product = productRepository.getProductbyProductId(productId);
		product.setCanReview(value);
		LinksDto links = new LinksDto();
		links.addLink(new LinkDto("view-product-by-owner", "/owners/"+ownerId+"/products/"+productId , "GET"));
		return Response.ok().entity(links).build();
	}
	
	/*@GET
	@Path("/products/{productId}")
	@Timed(name = "view-product")
	public Response viewProduct(@PathParam("ownerId") int ownerId, @PathParam("productId") int productId) {
		Product product = productRepository.getProductbyProductId(productId);
		ProductDto links = new ProductDto(product);
		links.addLink(new LinkDto("view-reviews", "/owners/"+ownerId+"products"+productId+"reviews", "GET"));
		
		return Response.ok().entity(links).build();
	}
	*/
	

}
