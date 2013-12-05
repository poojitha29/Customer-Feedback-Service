/**
 * 
 */
package edu.sjsu.cmpe.customerfeedback.ui.resources;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import edu.sjsu.cmpe.customerfeedback.domain.Owner;
import edu.sjsu.cmpe.customerfeedback.domain.User;
import edu.sjsu.cmpe.customerfeedback.repository.OwnerRepositoryInterface;
import edu.sjsu.cmpe.customerfeedback.repository.UserRepositoryInterface;
import edu.sjsu.cmpe.customerfeedback.ui.views.AuthenticateView;
import edu.sjsu.cmpe.customerfeedback.ui.views.HomeView;

/**
 * @author Rajiv
 *
 */

@Path("/")
@Produces(MediaType.TEXT_HTML)

public class HomeResource {
	
	private final UserRepositoryInterface userRepository;
	private final OwnerRepositoryInterface ownerRepository;
	
	public HomeResource(UserRepositoryInterface userRepository, OwnerRepositoryInterface ownerRepository) {
		this.ownerRepository = ownerRepository;
		this.userRepository = userRepository;
	}

	@GET
	public HomeView getHome() {	        
	    return new HomeView();
	        }	
	
	@POST
	public void register(@FormParam("userName") String userName, @FormParam("phoneNumber") String phoneNumber, @FormParam("password") String password, @FormParam("userType") String userType ) {
		System.out.println(userType);
		User user = new User();
		Owner owner = new Owner();
		user.setPassword(password);
		user.setUserName(userName.toLowerCase());
		if(userType.equals("owner")) {
			user.setOwner(true);
			owner.setOwnerName(userName);
			if(phoneNumber.length() == 10)
				owner.setPhoneNumber(phoneNumber);
			ownerRepository.saveOwner(owner);
		}
		else
			user.setOwner(false);
		if(phoneNumber.length() == 10)
			user.setPhoneNumber(phoneNumber);
		System.out.println(user);
		if(!(userName.isEmpty()||password.isEmpty()))
			userRepository.saveUser(user);
	}
	
	@POST
	@Path("authenticate/")
	public Response login(@FormParam("userName") String userName,  @FormParam("password") String password) {
		System.out.println("hey! it's working");
		System.out.println(userName+password);
		User user = userRepository.getUserbyUserName(userName);
		System.out.println(user);
		System.out.println("from DB"+user.getPassword()+password);
		if (!(user.getUserName() == null))
			if(user.getPassword().equals(password)) 
				return Response.ok().build();
		return Response.status(Status.UNAUTHORIZED).build();		
	}
	
	@GET
	@Path("authenticate/{userName}")
	public AuthenticateView authenticate(@PathParam("userName") String userName) {
		System.out.println("hey! it's working");
		User user = userRepository.getUserbyUserName(userName);
		Owner owner = ownerRepository.getOwnerbyOwnerName(userName);
		return new AuthenticateView(user, owner);	
	}
	
}
