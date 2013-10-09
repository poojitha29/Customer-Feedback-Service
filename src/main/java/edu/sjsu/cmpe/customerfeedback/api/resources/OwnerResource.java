package edu.sjsu.cmpe.customerfeedback.api.resources;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.customerfeedback.domain.Owner;
import edu.sjsu.cmpe.customerfeedback.dto.LinkDto;
import edu.sjsu.cmpe.customerfeedback.dto.LinksDto;
import edu.sjsu.cmpe.customerfeedback.dto.OwnerDto;
import edu.sjsu.cmpe.customerfeedback.repository.OwnerRepositoryInterface;

@Path("/owners")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class OwnerResource {

	private final OwnerRepositoryInterface ownerRepository;
	public OwnerResource(OwnerRepositoryInterface ownerRepository) {
		// TODO Auto-generated constructor stub
		this.ownerRepository = ownerRepository;
	}
	
	@POST
	@Timed(name = "create-owner")
	public Response createOwner(@Valid Owner request) {
		Owner savedOwner = ownerRepository.saveOwner(request);
		int ownerId = savedOwner.getOwnerId();
		LinksDto links = new LinksDto();
		links.addLink(new LinkDto("view-owner", "/owners/"+ownerId, "GET"));
		
		return Response.status(201).entity(links).build();
		
	}
	
	@GET
	@Path("/{ownerId}")
	@Timed(name = "view-owner")
	public Response viewOwner(@PathParam("ownerId") int ownerId) {
		Owner owner = ownerRepository.getOwnerbyOwnerID(ownerId);
		OwnerDto links = new OwnerDto(owner);
		links.addLink(new LinkDto("view-products-by-owner", "/owners/"+ownerId+"/products", "GET"));
		
		return Response.ok().entity(links).build();
	}
	
	

}
