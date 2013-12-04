package edu.sjsu.cmpe.customerfeedback.jdbi;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

/**
 * 
 */

/**
 * @author Rajiv
 *
 */
public class mongoConnect {
	
	public static void main(String[] args) {
		try {
			MongoClient client = new MongoClient(new ServerAddress(
					"ds053638.mongolab.com", 53638));
			DB database = client.getDB("customerfeedback");
			String username = "rajiv";
			char[] password = "rajiv".toCharArray();
			if(database.authenticate(username, password))
				System.out.println("Connection Successful");
			DBCollection collection = database.getCollection("Team");
			BasicDBObject document = new BasicDBObject();
			document.put("SPAARK1", "Rajiv Annapragada");
			collection.insert(document);
			com.mongodb.DBCursor cursor = collection.find();
			while (cursor.hasNext())
				System.out.println(cursor.next());
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
