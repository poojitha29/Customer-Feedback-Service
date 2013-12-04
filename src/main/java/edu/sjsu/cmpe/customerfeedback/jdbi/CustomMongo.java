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
		tempDbObject.put("productId", review.getProductId());
		tempDbObject.put("templateText", review.getTemplateText());
		tempDbObject.put("reviewText", review.getReviewText());
		tempDbObject.put("reviewer", review.getReviewer());
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
}
