/**
 * 
 */
package edu.sjsu.cmpe.customerfeedback.repository;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import edu.sjsu.cmpe.customerfeedback.domain.Review;

/**
 * @author Rajiv
 *
 */
public class ReviewRepository extends ReviewRepositoryInterface {
	
	private final ConcurrentHashMap<Integer, Review> reviewInMemoryMap;
	private int reviewId;
	/**
	 * @param reviewInMemoryMap
	 */
	public ReviewRepository(ConcurrentHashMap<Integer, Review> concurrentHashMap) {
		checkNotNull(concurrentHashMap, "ownerMap must not be NULL");
		reviewInMemoryMap = concurrentHashMap;
		reviewId = 0;
	}
	
	private final int generateReviewId() {
		return ++reviewId;		
	}
	
	 public void saveReview(Review newReview) {
		checkNotNull(newReview, "The Review cannot be null");
		int id = generateReviewId();
		newReview.setReviewId(id);
		reviewInMemoryMap.putIfAbsent(id, newReview);
		
	 }
	 
	 public Review getReview(int reviewId){
		checkArgument(reviewId>0,"ownerId was "+reviewId+", but expected a greater than zero value");
		return reviewInMemoryMap.get(reviewId); 
	 }
	 
	 public ArrayList<Review> getAllReviews(){
		 return new ArrayList<Review>(reviewInMemoryMap.values());
	 }

}
