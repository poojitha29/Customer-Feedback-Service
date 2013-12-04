/**
 * 
 */
package edu.sjsu.cmpe.customerfeedback.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.customerfeedback.domain.Review;

/**
 * @author Rajiv
 *
 */
@JsonPropertyOrder({"reviews","links"})
public class ReviewsDto extends LinksDto{
	List<Review> reviews;
	/**
	 * 
	 */
	public ReviewsDto(List<Review> reviews) {
		this.reviews = reviews;
	}
	/**
	 * @return the reviews
	 */
	public List<Review> getReviews() {
		return reviews;
	}
	/**
	 * @param reviews the reviews to set
	 */
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
}
