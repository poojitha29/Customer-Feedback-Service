/**
 * 
 */
package edu.sjsu.cmpe.customerfeedback.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.customerfeedback.domain.Product;

/**
 * @author Rajiv
 *
 */
@JsonPropertyOrder({"product","links"})
public class ProductDto extends LinksDto {
	private Product product;
	/**
	 * 
	 */
	public ProductDto(Product product) {
		this.product = product;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	

}
