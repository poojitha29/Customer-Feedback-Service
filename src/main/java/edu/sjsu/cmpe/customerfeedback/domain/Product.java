package edu.sjsu.cmpe.customerfeedback.domain;

//import java.net.URL;

import org.hibernate.validator.constraints.NotEmpty;

public class Product {

	public Product() {
		
	}
	private int productId;
	private int ownerId;
	@NotEmpty
	private String productName;
	@NotEmpty
	private String coverImage;
	private boolean canReview = false;
	private boolean whichTemplate;
	
	

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
	 * @return the ownerId
	 */
	public int getOwnerId() {
		return ownerId;
	}
	/**
	 * @param ownerId the ownerId to set
	 */
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the coverImage
	 */
	public String getCoverImage() {
		return coverImage;
	}
	/**
	 * @param coverImage the coverImage to set
	 */
	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}	
	
	/**
	 * @return the canReview
	 */
	public boolean isCanReview() {
		return canReview;
	}
	/**
	 * @param canReview the canReview to set
	 */
	public void setCanReview(boolean canReview) {
		this.canReview = canReview;
	}
	/**
	 * @return the whichTemplate
	 */
	public boolean isWhichTemplate() {
		return whichTemplate;
	}
	/**
	 * @param whichTemplate the whichTemplate to set
	 */
	public void setWhichTemplate(boolean whichTemplate) {
		this.whichTemplate = whichTemplate;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", ownerId=" + ownerId
				+ ", productName=" + productName + ", canReview=" + canReview
				+ ", whichTemplate=" + whichTemplate + "]";
	}
	
	
	

}
