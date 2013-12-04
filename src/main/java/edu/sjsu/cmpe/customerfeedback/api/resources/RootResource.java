package edu.sjsu.cmpe.customerfeedback.api.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.customerfeedback.domain.Product;
import edu.sjsu.cmpe.customerfeedback.dto.LinkDto;
import edu.sjsu.cmpe.customerfeedback.dto.LinksDto;
import edu.sjsu.cmpe.customerfeedback.dto.ProductsDto;
import edu.sjsu.cmpe.customerfeedback.repository.ProductRepositoryInterface;



@Path("/v1/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RootResource {
	
	private final ProductRepositoryInterface productRepository;

    public RootResource(ProductRepositoryInterface productRepository) {
	this.productRepository = productRepository;
    }

    @GET
    @Timed(name = "get-root")
    public Response getRoot() {
	LinksDto links = new LinksDto();
	links.addLink(new LinkDto("create-owner", "/owners", "POST"));

	return Response.ok(links).build();
    }
    
    @GET
    @Path("products")
    @Timed(name = "get-all-products")
    public Response getAllProducts(@PathParam("ownerId") int ownerId) {
		List<Product> allProducts = productRepository.getallProducts();
		ProductsDto links = new ProductsDto(allProducts);
		for (int i = 0; i< allProducts.size(); i++)
		links.addLink(new LinkDto("view-product", "/owners/"+ownerId+"/products/"+allProducts.get(i).getProductId(), "GET"));
		return Response.ok().entity(allProducts).build();
	}
}
