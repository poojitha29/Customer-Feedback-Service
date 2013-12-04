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



import edu.sjsu.cmpe.customerfeedback.domain.Review;
import edu.sjsu.cmpe.customerfeedback.jdbi.CustomMongo;
import edu.sjsu.cmpe.customerfeedback.notification.Notification;

/**
 * @author Rajiv
 *
 */
public class ReviewRepository extends ReviewRepositoryInterface {
	
	private DB db;
	private DBCollection reviewTable;
	private CustomMongo mongo;
	/**
	 * @param reviewInMemoryMap
	 */
	public ReviewRepository() {
		mongo = new CustomMongo();
		try {
			MongoClient mongoclient = new MongoClient("localhost", 27017);
			db = mongoclient.getDB("testLib");
			reviewTable = db.getCollection("reviewTable");
		} catch (Exception e) {
			System.out.println("Can't connect");
		}
	}
	
	@Override
	public void saveReview(Review newReview) {
		checkNotNull(newReview, "The Review cannot be null");
		DBObject tempReview = mongo.toDbObject(newReview);
		reviewTable.insert(tempReview);
		final Review review = newReview;
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
	public ArrayList<Review> getAllReviews(){
		ArrayList<Review> tempReviews = new ArrayList<Review>();
		DBCursor tempCursor = reviewTable.find(new BasicDBObject(), new BasicDBObject("_id",false));
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

}
