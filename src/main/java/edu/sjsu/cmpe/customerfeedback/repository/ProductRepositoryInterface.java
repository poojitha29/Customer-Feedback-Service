package edu.sjsu.cmpe.customerfeedback.repository;

import java.util.List;

import edu.sjsu.cmpe.customerfeedback.domain.Product;

public interface ProductRepositoryInterface {
	
	Product saveProduct(Product newProduct);
	
	Product getProductbyProductId(int productId);
	
	List<Product> getallProducts();

	/**
	 * @param isReviewable
	 * @param productId
	 */
	void updateReviewable(boolean isReviewable, int productId);

	/**
	 * @param setTemplate
	 * @param productId
	 */
	void updateTemplate(boolean setTemplate, int productId);

	/**
	 * @param ownerId
	 * @return
	 */
	List<Product> getallProductsbyOwner(int ownerId);

}
