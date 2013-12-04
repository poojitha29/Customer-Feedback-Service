package edu.sjsu.cmpe.customerfeedback.domain;
import org.hibernate.validator.constraints.NotEmpty;
public class Owner {
	
	int ownerId;
	@NotEmpty
	String ownerName;

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

	
	
}
