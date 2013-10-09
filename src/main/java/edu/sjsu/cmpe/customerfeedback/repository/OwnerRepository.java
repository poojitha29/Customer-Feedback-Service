package edu.sjsu.cmpe.customerfeedback.repository;

import java.util.concurrent.ConcurrentHashMap;

import edu.sjsu.cmpe.customerfeedback.domain.Owner;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;


public class OwnerRepository implements OwnerRepositoryInterface {
	
	private final ConcurrentHashMap<Integer, Owner> ownerInMemoryMap;
	private int ownerId;

	public OwnerRepository(ConcurrentHashMap<Integer, Owner> concurrentHashMap) {
		checkNotNull(concurrentHashMap, "ownerMap must not be NULL");
		ownerInMemoryMap = concurrentHashMap;
		ownerId = 0;
		
	}
	
	private final int generateOwnerId()	{
		return ++ownerId;
	}

	@Override
	public Owner saveOwner(Owner newOwner) {
		checkNotNull(newOwner, "newOwner instance cannot be null");
		int id = generateOwnerId();
		newOwner.setOwnerId(id);
		ownerInMemoryMap.putIfAbsent(id, newOwner);
		return newOwner ;
	}

	@Override
	public Owner getOwnerbyOwnerID(int ownerId) {
		// TODO Auto-generated method stub
		checkArgument(ownerId>0,"ownerId was "+ownerId+", but expected a greater than zero value");
		return ownerInMemoryMap.get(ownerId);
		
	}

}
