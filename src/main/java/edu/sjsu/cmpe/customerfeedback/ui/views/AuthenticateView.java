package edu.sjsu.cmpe.customerfeedback.ui.views;

import com.yammer.dropwizard.views.View;

import edu.sjsu.cmpe.customerfeedback.domain.Owner;
import edu.sjsu.cmpe.customerfeedback.domain.User;

public class AuthenticateView extends View{
	private final User user;
	private final Owner owner;
	private boolean AuthenticUser = false;
	public AuthenticateView(User user, Owner owner) {
		super("authenticate.mustache");
		this.user = user;
		if(!(user == null))
			this.AuthenticUser = true;	
		this.owner = owner;
			
	}
	/**
	 * @return the owner
	 */
	public Owner getOwner() {
		return owner;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @return the isAuthenticUser
	 */
	public boolean isAuthenticUser() {
		return AuthenticUser;
	}
	

}
