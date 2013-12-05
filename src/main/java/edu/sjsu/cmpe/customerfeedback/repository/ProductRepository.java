/**
 * 
 */
package edu.sjsu.cmpe.customerfeedback.repository;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.twilio.sdk.TwilioRestException;

import edu.sjsu.cmpe.customerfeedback.domain.Product;
import edu.sjsu.cmpe.customerfeedback.jdbi.CustomMongo;
import edu.sjsu.cmpe.customerfeedback.notification.Notification;


/**
 * @author Rajiv
 * 
 */
public class ProductRepository implements ProductRepositoryInterface {
	
	private int productId;
	private DB db;
	private DBCollection productTable;
	private CustomMongo mongo;
	private final Notification notification;
	ExecutorService executor;
	
	/**
	 * 
	 */
	public ProductRepository() {
		mongo = new CustomMongo();
		try {
			MongoClient mongoclient = new MongoClient(new ServerAddress(
					"ds053638.mongolab.com", 53638));
			db = mongoclient.getDB("customerfeedback");
			if(db.authenticate("rajiv", "rajiv".toCharArray()))
				System.out.println("Connection Successful");
			productTable = db.getCollection("productTable");
			productId = (int) productTable.getCount();
		} catch (Exception e) {
			System.out.println("Can't connect");
		}
		executor = Executors.newFixedThreadPool(20);
		notification = new Notification();
	}

	private final int generateProductId() {
		return ++productId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.sjsu.cmpe.customerfeedback.repository.ProductRepositoryInterface#
	 * saveProduct(edu.sjsu.cmpe.customerfeedback.domain.Product)
	 */

	@Override
	public Product saveProduct(Product newProduct) {
		checkNotNull(newProduct, "newProduct instance cannot be null");
		int id = generateProductId();
		newProduct.setProductId(id);
		//newProduct.setCoverImage(new URL("http://goo.gl/N96GJN"));
		DBObject tempProduct = mongo.toDbObject(newProduct);
		productTable.insert(tempProduct);
		final Product product = newProduct;
		Runnable notify  = new Runnable() {			
			@Override
			public void run() {
				try {
					notification.notifyProductInfo(product.getProductId(),1);
				} catch (TwilioRestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		};
		//executor.execute(notify);
		return newProduct;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.sjsu.cmpe.customerfeedback.repository.ProductRepositoryInterface#
	 * getProductbyProductId(int)
	 */
	@Override
	public Product getProductbyProductId(int productId) {
		checkArgument(productId > 0, "productId was " + productId+ ", but expected a greater than zero value");
		Product tempProduct = new Product();
		DBObject product = productTable.findOne(new BasicDBObject("productId", productId), new BasicDBObject("_id", false));
		tempProduct = mongo.toProductObject(product.toString());
		return tempProduct;
	}

	@Override
	public List<Product> getallProducts() {
		DBCursor tempCursor = productTable.find(new BasicDBObject(),
				new BasicDBObject("_id", false));
		ArrayList<Product> tempProducts = new ArrayList<Product>();
		while (tempCursor.hasNext()) {
			DBObject product = tempCursor.next();
			Product tempProduct = new Product();
			tempProduct = mongo.toProductObject(product.toString());
			tempProducts.add(tempProduct);
		}
		return tempProducts;
	}
	
	@Override
	public List<Product> getallProductsbyOwner(int ownerId) {
		DBCursor tempCursor = productTable.find(new BasicDBObject("ownerId",ownerId),
				new BasicDBObject("_id", false));
		ArrayList<Product> tempProducts = new ArrayList<Product>();
		while (tempCursor.hasNext()) {
			DBObject product = tempCursor.next();
			Product tempProduct = new Product();
			tempProduct = mongo.toProductObject(product.toString());
			tempProducts.add(tempProduct);
		}
		return tempProducts;
	}
	
	@Override
	public void updateReviewable (boolean isReviewable, int productId) {
		BasicDBObject updateReviewable = new BasicDBObject();
		updateReviewable.append("$set", new BasicDBObject().append("canReview", isReviewable));
		BasicDBObject searchQuery = new BasicDBObject().append("productId", productId);
		productTable.update(searchQuery, updateReviewable);
		final int id = productId;
		Runnable notify  = new Runnable() {			
			@Override
			public void run() {
				try {
					notification.notifyProductInfo(id,2);
				} catch (TwilioRestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		};
		//executor.execute(notify);
	}
	
	@Override
	public void updateTemplate (boolean setTemplate, int productId) {
		BasicDBObject updateReviewable = new BasicDBObject();
		updateReviewable.append("$set", new BasicDBObject().append("whichTemplate", setTemplate));
		BasicDBObject searchQuery = new BasicDBObject().append("productId", productId);
		productTable.update(searchQuery, updateReviewable);
		final int id = productId;
		Runnable notify  = new Runnable() {			
			@Override
			public void run() {
				try {
					notification.notifyProductInfo(id,3);
				} catch (TwilioRestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		};
		//executor.execute(notify);
	}
	

}
