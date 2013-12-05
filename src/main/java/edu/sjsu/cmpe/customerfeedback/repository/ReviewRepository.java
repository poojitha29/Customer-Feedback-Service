/**
 * 
 */
package edu.sjsu.cmpe.customerfeedback.repository;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.twilio.sdk.TwilioRestException;



import edu.sjsu.cmpe.customerfeedback.domain.Review;
import edu.sjsu.cmpe.customerfeedback.jdbi.CustomMongo;
import edu.sjsu.cmpe.customerfeedback.notification.Notification;

/**
 * @author Rajiv
 *
 */
public class ReviewRepository extends ReviewRepositoryInterface {
	
	private int reviewId;
	private DB db;
	private DBCollection reviewTable;
	private CustomMongo mongo;
	private Notification notification;
	ExecutorService executor;
	/**
	 * @param reviewInMemoryMap
	 */
	public ReviewRepository() {
		reviewId = 0;
		mongo = new CustomMongo();
		try {
			MongoClient mongoclient = new MongoClient("localhost", 27017);
			db = mongoclient.getDB("testLib");
			reviewTable = db.getCollection("reviewTable");
		} catch (Exception e) {
			System.out.println("Can't connect");
		}
		executor = Executors.newFixedThreadPool(10);
		notification = new Notification();
	}
	
	private final int generateReviewId()	{
		return ++reviewId;
	}
	
	@Override
	public void saveReview(Review newReview) {
		checkNotNull(newReview, "The Review cannot be null");
		int id = generateReviewId();
		newReview.setReviewId(id);
		DBObject tempReview = mongo.toDbObject(newReview);
		reviewTable.insert(tempReview);
		final Review review = newReview;
		Runnable notify  = new Runnable() {			
			@Override
			public void run() {
				try {
					notification.notifyProductReviewed(review);
				} catch (TwilioRestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		};
		//executor.execute(notify);
	 }
	 
	@Override
	public Review getReview(int reviewId){
		checkArgument(reviewId>0,"reviewId was "+reviewId+", but expected a greater than zero value");
		Review tempReview = new Review();
		DBObject review = reviewTable.findOne(new BasicDBObject("reviewId", reviewId), new BasicDBObject("_id",false));
		tempReview = mongo.toReviewObject(review.toString());
		//return reviewInMemoryMap.get(reviewId);
		return tempReview;
	 }
	 
	@Override
	public ArrayList<Review> getAllReviews(int productId){
		ArrayList<Review> tempReviews = new ArrayList<Review>();
		DBCursor tempCursor = reviewTable.find(new BasicDBObject("productId",productId), new BasicDBObject("_id",false));
		while(tempCursor.hasNext()) {
			//Review tempReview = new Review();
			//DBObject review = tempList.next();
			//tempReview = mongo.toReviewObject(review.toString());
			tempReviews.add(mongo.toReviewObject((tempCursor.next()).toString()));
			//tempReviews.add(tempReview);
		}
		return tempReviews;
		//return new ArrayList<Review>(reviewInMemoryMap.values());
	 }
	
	@Override
	public ArrayList<Review> getAllReviewsbyHelpfulness (int productId){
		ArrayList<Review> tempReviews = new ArrayList<Review>();
		DBCursor tempCursor = reviewTable.find(new BasicDBObject("productId",productId), new BasicDBObject("_id",false)).sort(new BasicDBObject("helpfulness", -1));
		while(tempCursor.hasNext()) {
			tempReviews.add(mongo.toReviewObject((tempCursor.next()).toString()));
		}
		System.out.println(tempReviews);
		return tempReviews;
	 }
	
	@Override
	public ArrayList<Review> getAllReviewsbyRecent (int productId){
		ArrayList<Review> tempReviews = new ArrayList<Review>();
		DBCursor tempCursor = reviewTable.find(new BasicDBObject("productId",productId), new BasicDBObject("_id",false)).sort(new BasicDBObject("_id", -1));
		while(tempCursor.hasNext()) {
			tempReviews.add(mongo.toReviewObject((tempCursor.next()).toString()));
		}
		System.out.println(tempReviews);
		return tempReviews;
	 }
	
	@Override
	public void updateHelpfulness (boolean isHelpful , int reviewId) {
		checkArgument(reviewId>0,"reviewId was "+reviewId+", but expected a greater than zero value");		
		int helpful, unhelpful, helpfulness;
		Review tempReview = new Review();
		DBObject review = reviewTable.findOne(new BasicDBObject("reviewId", reviewId), new BasicDBObject("_id",false));
		tempReview = mongo.toReviewObject(review.toString());
		helpfulness = tempReview.getHelpfulness();
		helpful = tempReview.getHelpful();
		unhelpful = tempReview.getUnhelpful();
		if(isHelpful)
			helpful +=1;
		else
			unhelpful +=1;
		helpfulness = helpful*100/(helpful+unhelpful);
		
		BasicDBObject updateHelpfulness = new BasicDBObject();
		BasicDBObject updateHelpful = new BasicDBObject();
		BasicDBObject updateUnhelpful = new BasicDBObject();
		
		updateHelpfulness.append("$set", new BasicDBObject().append("helpfulness", helpfulness ));
		updateHelpful.append("$set", new BasicDBObject().append("helpful", helpful ));
		updateUnhelpful.append("$set", new BasicDBObject().append("unhelpful", unhelpful ));
		BasicDBObject searchQuery = new BasicDBObject().append("reviewId", reviewId);
		reviewTable.update(searchQuery, updateHelpfulness);
		reviewTable.update(searchQuery, updateHelpful);
		reviewTable.update(searchQuery, updateUnhelpful);		
	}

}
