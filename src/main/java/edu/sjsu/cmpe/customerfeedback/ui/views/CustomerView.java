package edu.sjsu.cmpe.customerfeedback.ui.views;

import java.util.List;

import com.yammer.dropwizard.views.View;

import edu.sjsu.cmpe.customerfeedback.domain.Product;

public class CustomerView extends View{
	
	private final List<Product> products;
    public CustomerView (List<Product> products) {
              super("customer.mustache");
              this.products = products;
    }
    
    public List<Product> getProducts() {
              return products;
    }
}
