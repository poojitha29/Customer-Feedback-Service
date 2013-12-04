package edu.sjsu.cmpe.customerfeedback.jdbi;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
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
public class testMongo2 {
	
	public static void main(String[] args) {
		try {
			MongoClient mongoclient = new MongoClient("localhost", 27017);
			DB db = mongoclient.getDB("students");
			DBCollection tables = db.getCollection("grades");
			//List<DBObject> list = new ArrayList<DBObject>();
			DBCursor dbCursor = tables.find();
			while (dbCursor.hasNext())
				System.out.println(dbCursor.next());
			List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
			for (int i = 0; i < 200; i++) {
				obj.add(new BasicDBObject("type", "homework"));
				obj.add(new BasicDBObject("student_id", i));
				//obj.add(new BasicDBObject("grades.$",1));	
				BasicDBObject andQuery = new BasicDBObject();
				andQuery.put("$and", obj);
				DBCursor dbCursor1 = tables.find(andQuery).sort(new BasicDBObject("score",1));
				tables.remove(dbCursor1.next());
				while (dbCursor1.hasNext())
					System.out.println(dbCursor1.next());
				
				andQuery.clear();
				obj.clear();
				System.out.println(i);
			}
			
			//System.out.println(tables);
			//System.out.println(document);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
