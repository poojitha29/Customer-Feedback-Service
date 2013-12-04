package edu.sjsu.cmpe.customerfeedback.jdbi;
import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;


/**
 * 
 */

/**
 * @author Rajiv
 *
 */
public class CopyOftestMongo {
	
	public static void main(String[] args) {
		try {
			MongoClient mongoclient = new MongoClient("localhost", 27017);
			DB db = mongoclient.getDB("test");
			DBCollection tables = db.getCollection("users");
			BasicDBObject document = new BasicDBObject();
			document.put("1", "Rajiv");
			document.put("2", "R");
			document.put("3", "A");
			document.put("4", "J");
			document.put("5", "I");
			document.put("6", "V");
			tables.insert(document);
			System.out.println(tables);
			System.out.println(document);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
