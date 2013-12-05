package edu.sjsu.cmpe.customerfeedback.ui.views;

import java.util.List;

import com.yammer.dropwizard.views.View;

import edu.sjsu.cmpe.customerfeedback.domain.Product;
import edu.sjsu.cmpe.customerfeedback.domain.Review;
import edu.sjsu.cmpe.customerfeedback.domain.User;

public class ProductView extends View{

	private final Product product;
	
	private final List<Review> reviews;
	public ProductView(Product product, List<Review> reviews, User user) {
		super("product.mustache");
		this.product = product;
		this.reviews = reviews;
	}

	public ProductView(Product product, List<Review> reviews) {
		super("ownerproduct.mustache");
		this.product = product;
		this.reviews = reviews;
	}
	
	public Product getProduct(){
		return product;
	}
	
	/**
	 * @return the reviews
	 */
	public List<Review> getReviews() {
		return reviews;
	}

}
