package edu.sjsu.cmpe.customerfeedback.repository;

import java.util.List;

import edu.sjsu.cmpe.customerfeedback.domain.Product;

public interface ProductRepositoryInterface {
	
	Product saveProduct(Product newProduct);
	
	Product getProductbyProductId(int productId);
	
	List<Product> getallProducts();

}
