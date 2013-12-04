/**
 * 
 */
package edu.sjsu.cmpe.customerfeedback.domain;

/**
 * @author Rajiv
 *
 */
public class Review {
	
	private int productId;
	private String templateText;
	private String reviewText;
	private int reviewId;
	
	/**
	 * @return the reviewId
	 */
	public int getReviewId() {
		return reviewId;
	}
	/**
	 * @param reviewId the reviewId to set
	 */
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	/**
	 * @return the productId
	 */
	public int getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}
	/**
	 * @return the templateText
	 */
	public String getTemplateText() {
		return templateText;
	}
	/**
	 * @param templateText the templateText to set
	 */
	public void setTemplateText(String templateText) {
		this.templateText = templateText;
	}
	/**
	 * @return the reviewText
	 */
	public String getReviewText() {
		return reviewText;
	}
	/**
	 * @param reviewText the reviewText to set
	 */
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	
	
	

}
