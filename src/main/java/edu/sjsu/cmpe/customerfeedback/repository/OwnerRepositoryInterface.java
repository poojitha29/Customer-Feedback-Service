package edu.sjsu.cmpe.customerfeedback.repository;

import edu.sjsu.cmpe.customerfeedback.domain.Owner;


public interface OwnerRepositoryInterface {

	
	Owner saveOwner(Owner newOwner);
	
	Owner getOwnerbyOwnerID(int OwnerId);
}
