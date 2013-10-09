package edu.sjsu.cmpe.customerfeedback.repository;

import edu.sjsu.cmpe.customerfeedback.domain.Product;

public interface ProductRepositoryInterface {
	
	Product saveProduct(Product newProduct);
	
	Product getProductbyProductId(int productId);

}
