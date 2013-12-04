/**
 * 
 */
package edu.sjsu.cmpe.customerfeedback.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.customerfeedback.domain.Product;

/**
 * @author Rajiv
 *
 */
@JsonPropertyOrder({"products","links"})
public class ProductsDto extends LinksDto {
	List<Product> products;
	/**
	 * 
	 */
	public ProductsDto(List<Product> products) {
		this.products = products;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	

}
