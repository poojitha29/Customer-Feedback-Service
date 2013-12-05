/**
 * 
 */
package edu.sjsu.cmpe.customerfeedback.repository;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
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
		mongo = new CustomMongo();
		try {
			MongoClient mongoclient = new MongoClient(new ServerAddress(
					"ds053638.mongolab.com", 53638));
			db = mongoclient.getDB("customerfeedback");
			if(db.authenticate("rajiv", "rajiv".toCharArray()))
				System.out.println("Connection Successful");
			reviewTable = db.getCollection("reviewTable");
			reviewId = (int) reviewTable.getCount();
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
	
	@Override
	public List<String> generateReport(List<Integer> productIds) {
		List<String> Report = new ArrayList<String>();
		ConcurrentHashMap<Integer, Float> popularityIndex = new ConcurrentHashMap<Integer, Float>();
		ConcurrentHashMap<String, Integer> frequentReviewer = new ConcurrentHashMap<String, Integer>();
		int mostPopularItem =-1, leastPopularItem =-1;
		double leastPopularvalue = 5, mostPopularValue = 0, mostReviews=0;
		String freqReviewer = "";
		for (int i : productIds){
			popularityIndex.put(i, generatePopularityIndex(i));
		}	
		
		for (int i: productIds) {
			float value = popularityIndex.get(i);
			if(value<=leastPopularvalue) {
				leastPopularItem = i;
				leastPopularvalue = value;
			}
			else if(value>=mostPopularValue) {
				mostPopularItem = i;
				mostPopularValue = value;
			}
		}
		
		for (int i : productIds) {
			List<Review> reviews = getAllReviews(i);
			for(Review review: reviews) {
				if (frequentReviewer.containsKey(review.getReviewer()))
					frequentReviewer.put(review.getReviewer(), frequentReviewer.get(review.getReviewer())+1);
				else
					frequentReviewer.put(review.getReviewer(), 1);
			}			
		}

		for (String s: frequentReviewer.keySet()) {
			int value = frequentReviewer.get(s);
			System.out.println(value);
			if(value > mostReviews) {
				mostReviews = value;
				freqReviewer = s;
			}
		}
		System.out.println("Most"+mostPopularItem+mostPopularValue+"\tLeast"+leastPopularItem+leastPopularvalue+"\tFreq"+freqReviewer);		
		System.out.println(popularityIndex);
		System.out.println(frequentReviewer);
		Report.add(""+mostPopularItem);
		Report.add((""+mostPopularValue).substring(0, 3));
		Report.add(""+leastPopularItem);
		Report.add((""+leastPopularvalue).substring(0, 3));
		Report.add(freqReviewer);		
		return Report;
	}
	
	
	
	public float generatePopularityIndex( int productId) {
		float popularityIndex;//= 0.00;
		List<Review> reviews = getAllReviews(productId);
		System.out.println(reviews);
		Review tempReview = new Review();
		int total = 0;
		int rating,likes =0 ,unlikes =0;
		for (int i=0; i<reviews.size(); i++) {
			tempReview = reviews.get(i);
			if(tempReview.getTemplateText().length()<4) {
				rating = Integer.parseInt(tempReview.getTemplateText());
				total += rating;
			}
			else {
				if (tempReview.getTemplateText().equalsIgnoreCase("Like"))
					likes += 1;
				else 
					unlikes += 1;
			}
		}
		
		if(tempReview.getTemplateText().length()<4)
			popularityIndex = (float)total/reviews.size();
		else
			popularityIndex = (float)likes*5/(likes+unlikes);
		System.out.println("Likes:"+likes+ "Unlikes:"+unlikes+ "Sum:"+total);
		System.out.println("Popularity index"+popularityIndex);		
		return popularityIndex;
	} 
	
	

}
