package edu.sjsu.cmpe.customerfeedback.domain;

public class Product {

	public Product() {
		
	}
	private int productId;
	private int ownerId;
	private String productName;
	private boolean canReview = false;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public boolean isCanReview() {
		return canReview;
	}
	public void setCanReview(boolean canReview) {
		this.canReview = canReview;
	}
	
	

}
