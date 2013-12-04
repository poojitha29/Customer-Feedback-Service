/**
 * 
 */
package edu.sjsu.cmpe.customerfeedback.domain;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Rajiv
 *
 */
public class Review {
	
	private int productId;
	@NotEmpty
	private String templateText;
	@NotEmpty
	private String reviewText;
	private String reviewer = "Anonymous";
	
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
	 * @return the reviewer
	 */
	public String getReviewer() {
		return reviewer;
	}
	/**
	 * @param reviewer the reviewer to set
	 */
	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
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
