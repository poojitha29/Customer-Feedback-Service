/**
 * 
 */
package edu.sjsu.cmpe.customerfeedback.repository;

import java.util.ArrayList;

import edu.sjsu.cmpe.customerfeedback.domain.Review;

/**
 * @author Rajiv
 *
 */
public class ReviewRepositoryInterface {

	/**
	 * @param request
	 * @return
	 */
	public void saveReview(Review request) {
		// TODO Auto-generated method stub
	}

	/**
	 * @return
	 */
	public ArrayList<Review> getAllReviews(int productId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param reviewId
	 * @return
	 */
	public Review getReview(int reviewId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateHelpfulness(boolean helpful, int productId) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Review> getAllReviewsbyHelpfulness(int productId) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Review> getAllReviewsbyRecent(int productId) {
		// TODO Auto-generated method stub
		return null;
	}

}
