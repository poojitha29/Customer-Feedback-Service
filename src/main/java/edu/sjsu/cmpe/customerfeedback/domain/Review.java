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
	private int reviewId;
	private int productId;
	@NotEmpty
	private String templateText;
	@NotEmpty
	private String reviewText;
	private String reviewer = "Anonymous";
	private int helpfulness=0;
	private int helpful=0;
	private int unhelpful=0;
	
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
	/**
	 * @return the helpfulness
	 */
	public int getHelpfulness() {
		return helpfulness;
	}
	/**
	 * @param helpfulness the helpfulness to set
	 */
	public void setHelpfulness(int helpfulness) {
		this.helpfulness = helpfulness;
	}
	/**
	 * @return the helpful
	 */
	public int getHelpful() {
		return helpful;
	}
	/**
	 * @param helpful the helpful to set
	 */
	public void setHelpful(int helpful) {
		this.helpful = helpful;
	}
	/**
	 * @return the unhelpful
	 */
	public int getUnhelpful() {
		return unhelpful;
	}
	/**
	 * @param unhelpful the unhelpful to set
	 */
	public void setUnhelpful(int unhelpful) {
		this.unhelpful = unhelpful;
	}	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", productId=" + productId
				+ ", templateText=" + templateText + ", reviewText="
				+ reviewText + ", reviewer=" + reviewer + ", helpfulness="
				+ helpfulness + ", helpful=" + helpful + ", unhelpful="
				+ unhelpful + "]";
	}

}
