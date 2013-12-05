package edu.sjsu.cmpe.customerfeedback.notification;

import static com.google.common.base.Preconditions.checkArgument;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.twilio.sdk.TwilioRestException;

import edu.sjsu.cmpe.customerfeedback.domain.Owner;
import edu.sjsu.cmpe.customerfeedback.domain.Product;
import edu.sjsu.cmpe.customerfeedback.domain.Review;
import edu.sjsu.cmpe.customerfeedback.jdbi.CustomMongo;

 
public class Notification {
	
	private CustomMongo mongo;
	private DB db;
	private DBCollection ownerTable;
	private DBCollection productTable;
	//private DBCollection reviewTable;
	private Product product;
	private Owner owner;
	private String reviewer;
	private String reviewText;
	private final TwilioNotification twilio;

	public Notification() {
		product = new Product();
		owner = new Owner();
		twilio = new TwilioNotification();
		mongo = new CustomMongo();
		try {
			MongoClient mongoclient = new MongoClient("localhost", 27017);
			db = mongoclient.getDB("testLib");
			ownerTable = db.getCollection("ownerTable");
			productTable = db.getCollection("productTable");
		} catch (Exception e) {
			System.out.println("Can't connect");
		}
		
	}
	
	public void notifyProductReviewed(Review review) throws TwilioRestException {
		product = getProductbyProductId(review.getProductId());
		owner = getOwnerbyOwnerId(product.getOwnerId());
		reviewer = review.getReviewer();
		reviewText = review.getReviewText();
		String recipient = owner.getPhoneNumber();
		String body = "\n \n Your product has been reviewed.";
		body = body + "\n Product name: "+product.getProductName();
		body = body + "\n Reviewer name: "+reviewer;
		body = body + "\n Review : "+reviewText;
		twilio.notify(body, recipient);
	}
	
	public void notifyProductInfo(int productId, int choice) throws TwilioRestException {
		product = getProductbyProductId(productId);
		owner = getOwnerbyOwnerId(product.getOwnerId());
		String template;
		String recipient = owner.getPhoneNumber();
		String body = "-\n";
		if (product.isWhichTemplate())
			template = "LIKE/UNLIKE";
		else
			template = "STAR rating"; 
		
		switch(choice) {
		case 1:
			body = "Congratulations! You have added a new product.\nProduct Name : "+product.getProductName();
			break;
		case 2:
			body = "You have made your product "+product.getProductName()+" \"Reviewable\" by customers";
			break;
		case 3:
			body = "You have changed the template of the reviews for the product "+product.getProductName()+ " to "+template;
			break;
			
		}
		
		twilio.notify(body, recipient);
		
	}
	
	public Product getProductbyProductId(int productId) {
		checkArgument(productId > 0, "productId was " + productId+ ", but expected a greater than zero value");
		Product tempProduct = new Product();
		DBObject product = productTable.findOne(new BasicDBObject("productId", productId), new BasicDBObject("_id", false));
		tempProduct = mongo.toProductObject(product.toString());
		return tempProduct;
	}
	
	public Owner getOwnerbyOwnerId(int ownerId) {
		checkArgument(ownerId > 0, "ownerId was " + ownerId+ ", but expected a greater than zero value");
		Owner tempOwner = new Owner();
		DBObject owner = ownerTable.findOne(new BasicDBObject("ownerId", ownerId), new BasicDBObject("_id", false));
		tempOwner = mongo.toOwnerObject(owner.toString());
		return tempOwner;
	}
	

  
}