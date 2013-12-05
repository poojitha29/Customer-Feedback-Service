package edu.sjsu.cmpe.customerfeedback.ui.resources;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.sjsu.cmpe.customerfeedback.domain.Product;
import edu.sjsu.cmpe.customerfeedback.repository.ProductRepositoryInterface;
import edu.sjsu.cmpe.customerfeedback.ui.views.OwnerView;

	@Path("/owners/{ownerId}/products")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.TEXT_HTML)
	public class OwnerViewResource {

		private final ProductRepositoryInterface productRepository;
		public OwnerViewResource(ProductRepositoryInterface productRepository) {
			this.productRepository = productRepository;
		}
		
		@GET
		public OwnerView getAllProductsbyOwner(@PathParam("ownerId") int ownerId) {		
			return new OwnerView(productRepository.getallProductsbyOwner(ownerId));
		}
		
		@POST
		public Response createProduct(@PathParam("ownerId") int ownerId,@Valid Product request){
			request.setOwnerId(ownerId);
			productRepository.saveProduct(request);
			return Response.status(201).build();
		}
}
