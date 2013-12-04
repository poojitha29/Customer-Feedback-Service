package edu.sjsu.cmpe.customerfeedback.ui.views;

import com.yammer.dropwizard.views.View;

import edu.sjsu.cmpe.customerfeedback.domain.Product;

public class ProductView extends View{

	final Product product;
	public ProductView(Product product) {
		super("product.mustache");
		this.product = product;
	}

	public Product getProduct(){
		return product;
	}
}
