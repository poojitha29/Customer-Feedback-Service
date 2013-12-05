package edu.sjsu.cmpe.customerfeedback.repository;

import static com.google.common.base.Preconditions.checkNotNull;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import edu.sjsu.cmpe.customerfeedback.domain.Owner;
import edu.sjsu.cmpe.customerfeedback.jdbi.CustomMongo;


public class OwnerRepository implements OwnerRepositoryInterface {
	
	private int ownerId;
	private DB db;
	private DBCollection ownerTable;
	private CustomMongo mongo;

	public OwnerRepository() {
		ownerId = 0;
		mongo = new CustomMongo();
		try {
			MongoClient mongoclient = new MongoClient("localhost", 27017);
			db = mongoclient.getDB("testLib");
			ownerTable = db.getCollection("ownerTable");
			ownerId = (int) ownerTable.getCount();
		} catch (Exception e) {
			System.out.println("Can't connect");
		}
		
	}
	
	private final int generateOwnerId()	{
		return ++ownerId;
	}

	@Override
	public Owner saveOwner(Owner newOwner) {
		checkNotNull(newOwner, "newOwner instance cannot be null");
		int id = generateOwnerId();
		newOwner.setOwnerId(id);		
		DBObject tempOwner = mongo.toDbObject(newOwner);
		ownerTable.insert(tempOwner);
		return newOwner ;
	}

	/*@Override
	public Owner getOwnerbyOwnerID(int ownerId) {
		checkArgument(ownerId>0,"ownerId was "+ownerId+", but expected a greater than zero value");
		return ownerInMemoryMap.get(ownerId);
		
	}*/
	
	@Override
	public Owner getOwnerbyOwnerName(String ownerName) {
		checkNotNull(ownerName, "Owner Name cannot be null");
		Owner tempOwner = new Owner();
		DBObject owner = ownerTable.findOne(new BasicDBObject("ownerName", ownerName), new BasicDBObject("_id", false));
		if(!(owner == null))
			tempOwner = mongo.toOwnerObject(owner.toString());
		return tempOwner;
	}

}
