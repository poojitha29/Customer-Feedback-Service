/**
 * 
 */
package edu.sjsu.cmpe.customerfeedback.jdbi;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import edu.sjsu.cmpe.customerfeedback.domain.Owner;
import edu.sjsu.cmpe.customerfeedback.domain.Product;
import edu.sjsu.cmpe.customerfeedback.domain.Review;
import edu.sjsu.cmpe.customerfeedback.domain.User;

/**
 * @author Rajiv
 *
 */
public class CustomMongo {
	
	public DBObject toDbObject(Owner owner) {
		DBObject tempDbObject = new BasicDBObject();
		tempDbObject.put("ownerId", owner.getOwnerId());
		tempDbObject.put("ownerName", owner.getOwnerName());	
		tempDbObject.put("phoneNumber", owner.getPhoneNumber());
		return tempDbObject;	
	}
	
	public DBObject toDbObject(Product product) {
		DBObject tempDbObject = new BasicDBObject();
		tempDbObject.put("productId", product.getProductId());
		tempDbObject.put("ownerId", product.getOwnerId());
		tempDbObject.put("coverImage", product.getCoverImage());
		tempDbObject.put("productName", product.getProductName());
		tempDbObject.put("canReview", product.isCanReview());
		tempDbObject.put("whichTemplate", product.isWhichTemplate());
		return tempDbObject;
	}
	
	public DBObject toDbObject(Review review) {
		DBObject tempDbObject = new BasicDBObject();
		tempDbObject.put("reviewId", review.getReviewId());
		tempDbObject.put("productId", review.getProductId());
		tempDbObject.put("templateText", review.getTemplateText());
		tempDbObject.put("reviewText", review.getReviewText());
		tempDbObject.put("reviewer", review.getReviewer());
		tempDbObject.put("helpfulness", review.getHelpfulness());
		tempDbObject.put("helpful", review.getHelpful());
		tempDbObject.put("unhelpful", review.getUnhelpful());		
		return tempDbObject;
	}
	
	public DBObject toDbObject(User newUser) {
		DBObject tempDbObject = new BasicDBObject();
		tempDbObject.put("userName", newUser.getUserName());
		tempDbObject.put("owner", newUser.isOwner());
		tempDbObject.put("password", newUser.getPassword());
		tempDbObject.put("phoneNumber", newUser.getPhoneNumber());
		return tempDbObject;
	}
	
	public Product toProductObject(String jsonString){
		Product tempProduct = new Product();
		try {
			tempProduct = new ObjectMapper().readValue(jsonString, Product.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tempProduct;
	}

	public Owner toOwnerObject(String jsonString){
		Owner tempOwner = new Owner();
		try {
			tempOwner = new ObjectMapper().readValue(jsonString, Owner.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tempOwner;
	}
	
	public Review toReviewObject(String jsonString){
		Review tempReview = new Review();
		try {
			tempReview = new ObjectMapper().readValue(jsonString, Review.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tempReview;
	}

	public User toUserObject(String jsonString) {
		User tempUser = new User();
		try {
			tempUser = new ObjectMapper().readValue(jsonString, User.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tempUser;
	}
}
