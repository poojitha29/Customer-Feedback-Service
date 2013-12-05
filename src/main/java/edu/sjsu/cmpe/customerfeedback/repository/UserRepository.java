package edu.sjsu.cmpe.customerfeedback.repository;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.twilio.sdk.TwilioRestException;

import edu.sjsu.cmpe.customerfeedback.domain.User;
import edu.sjsu.cmpe.customerfeedback.jdbi.CustomMongo;
import edu.sjsu.cmpe.customerfeedback.notification.Notification;

public class UserRepository implements UserRepositoryInterface {
		
	private DB db;
	private DBCollection userTable;
	private CustomMongo mongo;
	private final Notification notification;
	ExecutorService executor;
	
	public UserRepository() {
		mongo = new CustomMongo();
		try {
			MongoClient mongoclient = new MongoClient("localhost", 27017);
			db = mongoclient.getDB("testLib");
			userTable = db.getCollection("userTable");
		} catch (Exception e) {
			System.out.println("Can't connect");
		}
		executor = Executors.newFixedThreadPool(20);
		notification = new Notification();	
	}
	
	public boolean saveUser(User newUser) {
		checkNotNull(newUser, "newProduct instance cannot be null");
		DBObject tempProduct = mongo.toDbObject(newUser);
		boolean isUniqueUserName = true;
		User checkUser = getUserbyUserName(newUser.getUserName());
		
		if(checkUser.getUserName().equals(newUser.getUserName())) {
			System.out.println("succesful check");
			isUniqueUserName = false;
		}			
		
		if (isUniqueUserName) {
			userTable.insert(tempProduct);
			final User user = newUser;
			Runnable notify = new Runnable() {
				@Override
				public void run() {
					try {
						notification.notifyProductInfo(1, 1);
					} catch (TwilioRestException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			//executor.execute(notify);
			return true;
		}
		return false;
	}
	
	public User getUserbyUserName(String userName) {
		checkNotNull(userName, "User Name cannot be null");
		User tempUser = new User();
		DBObject user = userTable.findOne(new BasicDBObject("userName", userName), new BasicDBObject("_id", false));
		if(!(user == null))
			tempUser = mongo.toUserObject(user.toString());
		return tempUser;
	}

}
