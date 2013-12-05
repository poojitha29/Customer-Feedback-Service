package edu.sjsu.cmpe.customerfeedback.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class Owner {
	
	int ownerId;
	@NotEmpty
	String ownerName;
	@NotEmpty
	String phoneNumber="";

	public Owner() {
		
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	

	
	
}
