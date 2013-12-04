/**
 * 
 */
package edu.sjsu.cmpe.customerfeedback.dto;

import java.util.List;

import edu.sjsu.cmpe.customerfeedback.domain.Review;

/**
 * @author Rajiv
 *
 */
public class ReviewsDto extends LinksDto{
	List<Review> reviews;
	/**
	 * 
	 */
	public ReviewsDto(List<Review> reviews) {
		this.reviews = reviews;
	}
	public List<Review> getProducts() {
		return reviews;
	}
	public void setProducts(List<Review> reviews) {
		this.reviews = reviews;
	}

}
