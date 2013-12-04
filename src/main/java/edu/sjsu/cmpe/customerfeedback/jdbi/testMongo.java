package edu.sjsu.cmpe.customerfeedback.jdbi;
import java.net.UnknownHostException;
import java.util.Collection;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;

import edu.sjsu.cmpe.customerfeedback.api.resources.OwnerResource;
import edu.sjsu.cmpe.customerfeedback.repository.OwnerRepository;
import edu.sjsu.cmpe.customerfeedback.repository.OwnerRepositoryInterface;


/**
 * 
 */

/**
 * @author Rajiv
 *
 */
public class testMongo {
	
	//final OwnerRepositoryInterface owner;
	/**
	 * 
	 */
	private final OwnerRepositoryInterface ownerRepository;
	
	/**
	 * 
	 */
	public testMongo(OwnerRepositoryInterface owner) {
		this.ownerRepository = owner;	
	}
	public static void main(String[] args) {
		try {
			MongoClient mongoclient = new MongoClient("localhost", 27017);
			DB db = mongoclient.getDB("testLib");
			DBCollection tables = db.getCollection("ownerTable");
			for(int i =0; i<5; i++) {
			BasicDBObject document = new BasicDBObject();
			document.put("A", "Rajiv");
			document.put("B", "R");
			document.put("C", "A");
			document.put("D", "J");
			document.put("E", "I");
			document.put("F", "V");
			tables.insert(document);
			System.out.println(tables);
			System.out.println(document);
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
