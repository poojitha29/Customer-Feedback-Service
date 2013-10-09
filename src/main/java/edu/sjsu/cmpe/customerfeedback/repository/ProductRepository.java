/**
 * 
 */
package edu.sjsu.cmpe.customerfeedback.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import edu.sjsu.cmpe.customerfeedback.domain.Product;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Rajiv
 *
 */
public class ProductRepository implements ProductRepositoryInterface {
	private final ConcurrentHashMap<Integer, Product> productInMemoryMap;
	private int productId;
	/**
	 * 
	 */
	public ProductRepository(ConcurrentHashMap<Integer, Product> concurrentHashMap) {
		checkNotNull(concurrentHashMap, "ownerMap must not be NULL");
		productInMemoryMap = concurrentHashMap;
		productId = 0;
	}

	/* (non-Javadoc)
	 * @see edu.sjsu.cmpe.customerfeedback.repository.ProductRepositoryInterface#saveProduct(edu.sjsu.cmpe.customerfeedback.domain.Product)
	 */
	
	private final int generateProductId() {
		return ++productId;		
	}
	@Override
	public Product saveProduct(Product newProduct) {
		checkNotNull(newProduct , "newProduct instance cannot be null");
		int id = generateProductId();
		newProduct.setProductId(id);
		productInMemoryMap.putIfAbsent(id, newProduct);
		return newProduct;
	}

	/* (non-Javadoc)
	 * @see edu.sjsu.cmpe.customerfeedback.repository.ProductRepositoryInterface#getProductbyProductId(int)
	 */
	@Override
	public Product getProductbyProductId(int productId) {
		checkArgument(productId>0, "productId was "+productId+", but expected a greater than zero value");
		return productInMemoryMap.get(productId);
	}
	
	public List<Product> getallProducts() {
		List<Product> allProducts = new ArrayList<Product>();
		for (int i = 1; i <= productInMemoryMap.size(); i++) {
			allProducts.add(productInMemoryMap.get(i));
		}
		return allProducts;
		
	}

}
